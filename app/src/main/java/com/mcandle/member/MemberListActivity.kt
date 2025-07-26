package com.mcandle.member

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.mcandle.member.databinding.ActivityMemberListBinding
import kotlinx.coroutines.launch
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import io.github.jan.supabase.postgrest.postgrest

class MemberListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMemberListBinding
    private lateinit var adapter: MemberAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMemberListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Toolbar Back(←) 클릭 시 메인으로
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }

        adapter = MemberAdapter { member ->
            MemberInfoDialog(member).show(supportFragmentManager, "member_dialog")
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        fetchMembers()
    }

    private fun fetchMembers() {
        lifecycleScope.launch {
            try {
                val members = SupabaseClientProvider.client.postgrest["user"]
                    .select().decodeList<MemberInfo>()
                adapter.submitList(members)
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(this@MemberListActivity, "멤버 조회 실패", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
