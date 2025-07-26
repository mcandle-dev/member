package com.mcandle.member

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.mcandle.member.databinding.OrderDetailDialogBinding

class OrderDetailDialog(
    private val order: OrderInfo,
    private val onPayClicked: (OrderInfo) -> Unit
) : DialogFragment() {

    private var _binding: OrderDetailDialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = OrderDetailDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 데이터 바인딩
        binding.tvStoreName.text = "매장명: ${order.store_name}"
        binding.tvPosId.text = "POS ID: ${order.pos_id}"
        binding.tvStaffName.text = "직원: ${order.staff_name}"
        binding.tvProductName.text = "상품: ${order.product_name}"
        binding.tvProductQty.text = "수량: ${order.product_qty}개"
        binding.tvAmount.text = "금액: ${order.amount.toComma()}원"
        binding.tvPromotions.text = order.promotions?.replace(",", "\n") ?: "-"
        binding.tvRecommended.text = order.recommended?.replace(",", "\n") ?: "-"
        binding.tvPayAmount.text = "[결제금액] ${order.pay_amount.toComma()}원"

        binding.btnCancel.setOnClickListener { dismiss() }
        binding.btnPay.setOnClickListener {
            dismiss()
            onPayClicked(order)
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        return dialog
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun Int.toComma(): String = String.format("%,d", this)
}
