package com.example.aplicacion;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class DrawingAppActivity extends AppCompatActivity {

    private DrawView drawView;
    private Button clearButton;
    private Button btnBackToMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawing_app);

        Spinner colorSpinner = findViewById(R.id.colorSpinner);
        drawView = findViewById(R.id.drawView);
        clearButton = findViewById(R.id.clearButton);
        btnBackToMain = findViewById(R.id.btnBackToMainFromDrawing);

        String[] colors = {"Rojo", "Verde", "Azul", "Negro", "Amarillo"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, colors);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        colorSpinner.setAdapter(adapter);

        colorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        drawView.setPaintColor(Color.RED);
                        break;
                    case 1:
                        drawView.setPaintColor(Color.GREEN);
                        break;
                    case 2:
                        drawView.setPaintColor(Color.BLUE);
                        break;
                    case 3:
                        drawView.setPaintColor(Color.BLACK);
                        break;
                    case 4:
                        drawView.setPaintColor(Color.YELLOW);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawView.clearDrawing();
            }
        });

        btnBackToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
