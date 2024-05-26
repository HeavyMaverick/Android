package com.example.cafeorder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginActivity extends AppCompatActivity {
    private EditText editTextName;
    private EditText editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        editTextName = findViewById(R.id.editTextName);
        editTextPassword = findViewById(R.id.editTextTextPassword);
    }
    public void onClickCreateOrder(View view){
        String name = editTextName.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        if (!name.isEmpty() && !password.isEmpty()){
            Intent intent = new Intent(this, CreateOrderActivity.class);
            intent.putExtra("password", password);
            intent.putExtra("name", name);
            startActivity(intent);
        } else {
            Toast.makeText(this, R.string.toast_fill_fields, Toast.LENGTH_SHORT).show();
        }
    }
}