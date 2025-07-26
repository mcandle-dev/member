package com.mcandle.member

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mcandle.member.databinding.OrderItemBinding

class OrderAdapter(
    private val onItemClick: (OrderInfo) -> Unit
) : ListAdapter<OrderInfo, OrderAdapter.OrderViewHolder>(DiffCallback) {

    object DiffCallback : DiffUtil.ItemCallback<OrderInfo>() {
        override fun areItemsTheSame(oldItem: OrderInfo, newItem: OrderInfo) = oldItem.order_key == newItem.order_key
        override fun areContentsTheSame(oldItem: OrderInfo, newItem: OrderInfo) = oldItem == newItem
    }

    inner class OrderViewHolder(private val binding: OrderItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(order: OrderInfo) {
            binding.tvOrderKey.text = "Order Key: ${order.order_key}"
            binding.tvUserId.text = "User ID: ${order.user_id ?: "-"}"
            // 다른 바인딩 코드는 모두 제거
            binding.root.setOnClickListener {
                onItemClick(order)
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val binding = OrderItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OrderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    // 천단위 콤마 함수(확장)
    private fun Int.toComma(): String = String.format("%,d", this)
}
