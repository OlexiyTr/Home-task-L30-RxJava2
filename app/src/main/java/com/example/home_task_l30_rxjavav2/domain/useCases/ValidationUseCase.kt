package com.example.home_task_l30_rxjavav2.domain.useCases

import com.example.home_task_l30_rxjavav2.domain.ConstantsForCheckError.Companion.BODY_MAX_LENGTH
import com.example.home_task_l30_rxjavav2.domain.ConstantsForCheckError.Companion.BODY_MIN_LENGTH
import com.example.home_task_l30_rxjavav2.domain.ConstantsForCheckError.Companion.TITLE_MAX_LENGTH
import com.example.home_task_l30_rxjavav2.domain.ConstantsForCheckError.Companion.TITLE_MIN_LENGTH
import com.example.home_task_l30_rxjavav2.domain.NewPostErrorType
import com.example.home_task_l30_rxjavav2.domain.model.NewPostModel
import javax.inject.Inject

class ValidationUseCase @Inject constructor(
    private val savePostUseCase: SavePostUseCase
) {
    fun validate(postForSaving: NewPostModel): Set<NewPostErrorType> {
        val errors: MutableSet<NewPostErrorType> = mutableSetOf()

        if (postForSaving.title.length < TITLE_MIN_LENGTH) {
            errors.add(NewPostErrorType.TITLE_MIN_ERROR)
        }
        if (postForSaving.title.length > TITLE_MAX_LENGTH) {
            errors.add(NewPostErrorType.TITLE_MAX_ERROR)
        }
        if (postForSaving.body.length < BODY_MIN_LENGTH) {
            errors.add(NewPostErrorType.BODY_MIN_ERROR)
        }
        if (postForSaving.body.length > BODY_MAX_LENGTH) {
            errors.add(NewPostErrorType.BODY_MAX_ERROR)
        }
        if (checkBadWords(postForSaving.title)) {
            errors.add(NewPostErrorType.BAD_WORDS_ERROR)
        }
        if (errors.isEmpty()) {
            savePostUseCase.savePost(postForSaving)
        }
        return errors
    }

    private fun checkBadWords(title: String): Boolean {
        val badWords = setOf("реклама", "куплю", "товар")
        val badRegex = badWords.joinToString(prefix = "(?i)", separator = "|").toRegex()
        if (title.contains(badRegex)) {
            return true
        }
        return false
    }
}