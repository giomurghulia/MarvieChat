package com.example.marviechat.presentation

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.marviechat.R
import com.example.marviechat.databinding.LayoutChatItemBinding

class ChatAdapter : ListAdapter<ListItem, ChatAdapter.MyViewHolder>(MainDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutChatItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    inner class MyViewHolder(
        private val binding: LayoutChatItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n", "ResourceAsColor")
        fun bind(item: ListItem) {

            binding.nameText.text = item.firstName + " " + item.lastName
            binding.messageText.text = item.lastMessage
            binding.timeText.text = item.updatedDate

            Glide.with(itemView.context)
                .load(item.avatar)
                .circleCrop()
                .into(binding.iconImage)

            when (item.messageType) {
                MessageType.ATTACHMENT -> {

                    binding.messageTypeImage.visibility = View.VISIBLE


                    Glide.with(itemView.context)
                        .load(item.messageType.icon)
                        .into(binding.messageTypeImage)

                    binding.messageText.text = item.messageType.toString()

                }
                MessageType.VOICE -> {

                    binding.messageTypeImage.visibility = View.VISIBLE

                    Glide.with(itemView.context)
                        .load(item.messageType.icon)
                        .into(binding.messageTypeImage)

                    binding.messageText.text = item.messageType.toString()

                }
                else -> {

                    binding.messageTypeImage.visibility = View.GONE
                    binding.unreadMassageText.visibility = View.GONE

                }
            }

            if (item.isTyping) {
                Glide.with(itemView.context)
                    .load(R.drawable.ic_typing)
                    .into(binding.typingImage)

                binding.unreadMassageText.visibility = View.GONE
            } else if (item.unreadMessage > 0) {

                binding.messageText.setTextColor((ContextCompat.getColor(binding.messageText.context, R.color.white)))
                binding.messageText.setTextColor((ContextCompat.getColor(binding.messageText.context, R.color.white)))
                binding.lineView.setBackgroundColor((ContextCompat.getColor(binding.messageText.context, R.color.white)))
                binding.unreadMassageText.visibility = View.VISIBLE

                binding.unreadMassageText.text = item.unreadMessage.toString()
            }else{
                binding.unreadMassageText.visibility = View.GONE
            }

        }
    }
}