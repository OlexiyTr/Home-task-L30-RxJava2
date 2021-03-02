package com.example.home_task_l30_rxjavav2.ui.inputNewPostScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.home_task_l30_rxjavav2.App
import com.example.home_task_l30_rxjavav2.databinding.InputNewPostFragmentBinding
import com.example.home_task_l30_rxjavav2.hideKeyboard
import javax.inject.Inject

class InputNewPostFragment : Fragment() {

    @Inject
    lateinit var viewModel: InputNewPostViewModel
    private lateinit var binding: InputNewPostFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = InputNewPostFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupDi()
        setupListeners()
        observeErrorInput()
    }

    private fun setupDi() {
        val app = requireActivity().application as App
        app.getComponent().inject(this)
    }

    private fun setupListeners() {
        binding.btnAddNewPost.setOnClickListener {
            viewModel.sendDataToCache(
                binding.etTitle.text.toString(),
                binding.etBody.text.toString()
            )
        }
    }

    private fun observeErrorInput() {
        viewModel.stringErrorLiveData.observe(viewLifecycleOwner) {
            requireActivity().supportFragmentManager.beginTransaction().remove(this).commit()
            this.hideKeyboard()
        }
    }

    companion object {
        fun newInstance() = InputNewPostFragment()
    }
}