package com.mcandle.member

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.mcandle.member.databinding.ActivityMainBinding
import kotlinx.coroutines.launch
import io.github.jan.supabase.postgrest.postgrest

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MemberAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d("SUPABASE_KEY", BuildConfig.SUPABASE_API_KEY)

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
                Toast.makeText(this@MainActivity, "멤버 조회 실패", Toast.LENGTH_SHORT).show()
            }
        }
    }
}