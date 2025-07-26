package com.mcandle.member

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mcandle.member.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Order 버튼 클릭 시 OrderListActivity로 이동
        binding.btnOrder.setOnClickListener {
            val intent = Intent(this, OrderListActivity::class.java)
            startActivity(intent)
        }

        // Member 버튼 클릭 시 MemberListActivity로 이동 (기존 기능)
        binding.btnMember.setOnClickListener {
            val intent = Intent(this, MemberListActivity::class.java)
            startActivity(intent)
        }
    }
}