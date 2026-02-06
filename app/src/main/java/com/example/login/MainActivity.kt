package com.example.login

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // SỬA LỖI TẠI ĐÂY: Phải là activity_main chứ không phải login
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 1. Nhận tên đăng nhập từ Intent
        val username = intent.getStringExtra("KEY_USERNAME") ?: "Người dùng"
        
        // 2. Hiển thị lời chào
        val txtWelcome = findViewById<TextView>(R.id.txtWelcomeUser)
        txtWelcome.text = "Chào mừng $username,\nBạn đã đăng nhập thành công!"

        // 3. Xử lý nút Đăng xuất
        val btnLogout = findViewById<Button>(R.id.btnLogout)
        btnLogout.setOnClickListener {
            Toast.makeText(this, "Đã đăng xuất", Toast.LENGTH_SHORT).show()
            
            val intent = Intent(this, loginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
    }
}