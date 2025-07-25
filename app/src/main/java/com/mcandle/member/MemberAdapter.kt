package com.mcandle.member

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mcandle.member.databinding.ItemMemberBinding

class MemberAdapter(
    private val onItemClick: (MemberInfo) -> Unit
) : ListAdapter<MemberInfo, MemberAdapter.MemberViewHolder>(DiffCallback) {

    object DiffCallback : DiffUtil.ItemCallback<MemberInfo>() {
        override fun areItemsTheSame(oldItem: MemberInfo, newItem: MemberInfo) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: MemberInfo, newItem: MemberInfo) = oldItem == newItem
    }

    inner class MemberViewHolder(private val binding: ItemMemberBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(member: MemberInfo) {
            binding.tvName.text = member.name
            binding.tvPhone.text = member.phone
            binding.tvCardNo.text = member.member_card_no

            binding.root.setOnClickListener {
                onItemClick(member)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemberViewHolder {
        val binding = ItemMemberBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MemberViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MemberViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
