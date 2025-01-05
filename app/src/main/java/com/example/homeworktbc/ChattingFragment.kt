package com.example.homeworktbc

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homeworktbc.databinding.FragmentChattingBinding


class ChattingFragment : Fragment() {
    private var _viewBinding: FragmentChattingBinding? = null
    private val viewBinding get() = _viewBinding!!

    private lateinit var adapterr: MessageAdapter
    private val messagesList = mutableListOf<Message>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _viewBinding = FragmentChattingBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapterr = MessageAdapter()
        setUp()
        listeners()
    }

    private fun listeners() {
        viewBinding.btnSend.setOnClickListener {
            addNewMessage()
        }
    }

    private fun addNewMessage() {
        val text = viewBinding.etMessage.text.toString()

        if (text.isNotEmpty()) {
            val newMessage = Message(text = text)

            messagesList.add(0, newMessage)
            adapterr.submitList(ArrayList(messagesList))

            viewBinding.apply {
                etMessage.text?.clear()
                recyclerView.scrollToPosition(0) }
        }
    }

    private fun setUp() {
        viewBinding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext()).apply {
                stackFromEnd = false
            }
            adapter = adapterr
        }
        adapterr.submitList(ArrayList(messagesList))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _viewBinding = null
    }
}