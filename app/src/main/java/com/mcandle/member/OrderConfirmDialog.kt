package com.mcandle.member

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.mcandle.member.databinding.DialogOrderConfirmBinding

class OrderConfirmDialog(
    private val order: OrderInfo,
    private val onConfirm: (OrderInfo) -> Unit
) : DialogFragment() {

    private var _binding: DialogOrderConfirmBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogOrderConfirmBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 메시지
        binding.tvDialogMessage.text =
            "고객님이 구매하실려고 하는 제품의\n최적의 결제방식을 추천해 드립니다.\n\n확인 하시겠습니다."

        // "확인" 버튼 클릭시 콜백 호출
        binding.btnConfirm.setOnClickListener {
            dismiss()
            onConfirm(order)
        }

        // "취소" 버튼 클릭시 팝업 닫기
        binding.btnCancel.setOnClickListener {
            dismiss()
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
}
