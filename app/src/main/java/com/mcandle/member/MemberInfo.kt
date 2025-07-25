package com.mcandle.member

import kotlinx.serialization.Serializable

@Serializable
data class MemberInfo(
    val id: String,
    val name: String,
    val grade: String,
    val phone: String,
    val kakao_use: Boolean,
    val member_card_no: String,
    val credit_card_name: String,
    val credit_card_no: String,
    val promotion: String
)