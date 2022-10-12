package com.example.marviechat.presentation

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.marviechat.Result
import com.example.marviechat.databinding.FragmentChatBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ChatFragment : Fragment() {
    private lateinit var binding: FragmentChatBinding

    private val viewModel: ChatViewModel by viewModels()

    private val adapter = ChatAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)

        binding = FragmentChatBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        handleWindowInset()

        binding.mainRecycleView.layoutManager = LinearLayoutManager(requireContext())
        binding.mainRecycleView.adapter = adapter

        viewModel.getChat()

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect {
                    when (it) {
                        is Result.Success -> {
                            adapter.submitList(it.data)

                            binding.loader.visibility = View.GONE

                        }
                        is Result.Loading -> {
                            binding.loader.visibility = View.VISIBLE
                        }
                        is Result.Error -> {
                            binding.loader.visibility = View.GONE

                            Log.d("error", "${it.throwable.message}")

                            val alert: AlertDialog = AlertDialog.Builder(requireContext()).create()
                            alert.setTitle("Chat")
                            alert.setMessage("Not Found Massage")
                            alert.show()
                        }

                    }
                }
            }
        }
    }

    private fun handleWindowInset() {
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { _, windowInsets ->
            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())

            binding.root.updatePadding(bottom = insets.bottom)

            windowInsets

        }
    }
}