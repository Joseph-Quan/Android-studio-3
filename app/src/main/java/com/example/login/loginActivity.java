package com.example.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class loginActivity extends AppCompatActivity {

    private EditText edtUser, edtPass;
    private Button btnLogin;
    private TextView txtGoToRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        edtUser = findViewById(R.id.edtUserLogin);
        edtPass = findViewById(R.id.edtPassLogin);
        btnLogin = findViewById(R.id.btnLogin);
        txtGoToRegister = findViewById(R.id.txtGoToRegister);

        btnLogin.setOnClickListener(v -> {
            String inputUser = edtUser.getText().toString().trim();
            String inputPass = edtPass.getText().toString().trim();

            if (TextUtils.isEmpty(inputUser) || TextUtils.isEmpty(inputPass)) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông thông", Toast.LENGTH_SHORT).show();
                return;
            }

            // Lấy thông tin đã đăng ký từ SharedPreferences
            SharedPreferences pref = getSharedPreferences("USER_DATA", MODE_PRIVATE);
            String registeredUser = pref.getString("username", "admin"); // Mặc định vẫn có admin
            String registeredPass = pref.getString("password", "123456"); // Mặc định vẫn có 123456

            if (inputUser.equals(registeredUser) && inputPass.equals(registeredPass)) {
                Toast.makeText(this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(loginActivity.this, MainActivity.class);
                intent.putExtra("KEY_USERNAME", inputUser);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "Tài khoản hoặc mật khẩu không chính xác", Toast.LENGTH_SHORT).show();
            }
        });

        txtGoToRegister.setOnClickListener(v -> {
            Intent intent = new Intent(loginActivity.this, registerActivity.class);
            startActivity(intent);
        });
    }
}