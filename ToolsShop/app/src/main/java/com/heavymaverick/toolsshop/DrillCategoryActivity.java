package com.heavymaverick.toolsshop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class DrillCategoryActivity extends AppCompatActivity {
    private ListView listViewDrills;
    private ArrayList<Drill> drills;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_drill_category);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        drills = new ArrayList<>();
        drills.add(new Drill(getString(R.string.drill_makita_title), getString(R.string.drill_makita_info), R.drawable.drill));
        drills.add(new Drill(getString(R.string.drill_dewalt_title), getString(R.string.drill_dewalt_info), R.drawable.drill));
        drills.add(new Drill(getString(R.string.drill_interskol_title), getString(R.string.drill_interskol_info), R.drawable.drill));
        listViewDrills = findViewById(R.id.listViewDrills);
        ArrayAdapter<Drill> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, drills);
        listViewDrills.setAdapter(adapter);
        listViewDrills.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Drill drill = drills.get(position);
                Intent intent = new Intent(getApplicationContext(), DrillDetailActivity.class);
                intent.putExtra("title", drill.getTitle());
                intent.putExtra("info", drill.getInfo());
                intent.putExtra("resId", drill.getImageResourceId());
                startActivity(intent);
            }
        });

    }
}