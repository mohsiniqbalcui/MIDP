package com.app.midp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText v1, v2, v3, v4;
    PieChartView pieChartView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        v1 = findViewById(R.id.value1);
        v2 = findViewById(R.id.value2);
        v3 = findViewById(R.id.value3);
        v4 = findViewById(R.id.value4);
        pieChartView = findViewById(R.id.pieChartView);

        Button drawButton = findViewById(R.id.drawChart);
        drawButton.setOnClickListener(v -> {
            try {
                int[] values = new int[4];
                values[0] = getPositiveInt(v1);
                values[1] = getPositiveInt(v2);
                values[2] = getPositiveInt(v3);
                values[3] = getPositiveInt(v4);

                pieChartView.setData(values);

            } catch (IllegalArgumentException e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private int getPositiveInt(EditText e) {
        String input = e.getText().toString().trim();
        if (input.isEmpty()) throw new IllegalArgumentException("All fields must be filled");
        int value = Integer.parseInt(input);
        if (value <= 0) throw new IllegalArgumentException("Values must be greater than 0");
        return value;
    }

}