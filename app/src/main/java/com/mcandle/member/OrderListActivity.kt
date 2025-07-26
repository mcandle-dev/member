package com.mcandle.member

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.lifecycle.lifecycleScope
import com.mcandle.member.databinding.ActivityOrderListBinding
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.coroutines.launch

class OrderListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOrderListBinding
    private lateinit var adapter: OrderAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 툴바 네비게이션(←) 클릭 시 메인으로 이동
        binding.toolbar.setNavigationOnClickListener { finish() }

        adapter = OrderAdapter { order ->
            // 1. OrderConfirmDialog 표시
            OrderConfirmDialog(order) { confirmedOrder ->
                // 2. "확인" 클릭 시 Supabase에서 order_key + user_id로 상세 재조회
                lifecycleScope.launch {
                    try {
                        val orderDetail = SupabaseClientProvider.client.postgrest["order"]
                            .select {
                                eq("order_key", confirmedOrder.order_key)
                                eq("user_id", confirmedOrder.user_id ?: "")
                                limit(1)
                            }
                            .decodeSingle<OrderInfo>()

                        // 3. 상세 다이얼로그에 조회 데이터 전달/출력
                        OrderDetailDialog(orderDetail) { payOrder ->
                            // 결제 버튼 클릭시 처리 (필요 시 구현)
                        }.show(supportFragmentManager, "order_detail_dialog")
                    } catch (e: Exception) {
                        Toast.makeText(
                            this@OrderListActivity,
                            "상세 정보 조회 실패",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }.show(supportFragmentManager, "order_confirm_dialog")
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        fetchOrders()
    }

    private fun fetchOrders() {
        lifecycleScope.launch {
            try {
                val orders = SupabaseClientProvider.client.postgrest["order"]
                    .select()
                    .decodeList<OrderInfo>()
                adapter.submitList(orders)
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(
                    this@OrderListActivity,
                    "주문 목록 조회 실패",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}
