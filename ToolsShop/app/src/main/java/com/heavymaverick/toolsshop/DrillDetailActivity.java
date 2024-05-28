package com.heavymaverick.toolsshop;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DrillDetailActivity extends AppCompatActivity {
    private TextView textViewTitle;
    private TextView textViewInfo;
    private ImageView imageViewDrill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_drill_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        textViewInfo = findViewById(R.id.textViewInfo);
        textViewTitle = findViewById(R.id.textViewTitle);
        imageViewDrill = findViewById(R.id.imageViewDrill);
        Intent intent = getIntent();
        if (intent.hasExtra("title") && intent.hasExtra("info") && intent.hasExtra("resId")) {
            String title = intent.getStringExtra("title");
            String info = intent.getStringExtra("info");
            int resId = intent.getIntExtra("resId", -1);
            textViewTitle.setText(title);
            textViewInfo.setText(info);
            imageViewDrill.setImageResource(resId);
        } else {
            Intent backToCategory = new Intent(this, DrillDetailActivity.class);
            startActivity(intent);
        }
    }
}