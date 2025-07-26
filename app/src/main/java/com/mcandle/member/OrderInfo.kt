package com.mcandle.member

import kotlinx.serialization.Serializable

@Serializable
data class OrderInfo(
    val order_key: String,
    val store_name: String,
    val pos_id: String,
    val staff_name: String,
    val product_name: String,
    val product_qty: Int,
    val amount: Int,
    val promotions: String? = null,
    val recommended: String? = null,
    val pay_amount: Int,
    val user_id: String?
)
