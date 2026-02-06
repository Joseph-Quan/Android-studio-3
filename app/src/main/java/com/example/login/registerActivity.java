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

public class registerActivity extends AppCompatActivity {

    private EditText edtUser, edtEmail, edtPass, edtConfirmPass;
    private Button btnRegister;
    private TextView txtGoToLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        // Khởi tạo các view
        edtUser = findViewById(R.id.edtUserRegister);
        edtEmail = findViewById(R.id.edtEmailRegister);
        edtPass = findViewById(R.id.edtPassRegister);
        edtConfirmPass = findViewById(R.id.edtConfirmPassRegister);
        btnRegister = findViewById(R.id.btnRegister);
        txtGoToLogin = findViewById(R.id.txtGoToLogin);

        // Xử lý sự kiện nút Đăng ký
        btnRegister.setOnClickListener(v -> {
            String username = edtUser.getText().toString().trim();
            String email = edtEmail.getText().toString().trim();
            String password = edtPass.getText().toString().trim();
            String confirmPass = edtConfirmPass.getText().toString().trim();

            if (TextUtils.isEmpty(username) || TextUtils.isEmpty(email) || 
                TextUtils.isEmpty(password) || TextUtils.isEmpty(confirmPass)) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!password.equals(confirmPass)) {
                Toast.makeText(this, "Mật khẩu xác nhận không khớp", Toast.LENGTH_SHORT).show();
                return;
            }

            // Lưu thông tin vào SharedPreferences để giả lập cơ sở dữ liệu
            SharedPreferences pref = getSharedPreferences("USER_DATA", MODE_PRIVATE);
            SharedPreferences.Editor editor = pref.edit();
            editor.putString("username", username);
            editor.putString("password", password);
            editor.apply();

            Toast.makeText(this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();

            // Chuyển về màn hình đăng nhập
            Intent intent = new Intent(registerActivity.this, loginActivity.class);
            startActivity(intent);
            finish();
        });

        // Quay lại màn hình Đăng nhập
        txtGoToLogin.setOnClickListener(v -> {
            Intent intent = new Intent(registerActivity.this, loginActivity.class);
            startActivity(intent);
            finish();
        });
    }
}