package com.example.mad_question1_unitconverter;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

//package com.example.unitconverter;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    EditText quantityInput;
    Spinner fromUnitSpinner, toUnitSpinner;
    TextView convertedResult;
    Button convertBtn;

    String[] units = {"Meters", "Centimeters", "Feet", "Inches", "Yards"};
    HashMap<String, Double> toMeterFactor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        quantityInput = findViewById(R.id.quantityInput);
        fromUnitSpinner = findViewById(R.id.fromUnitSpinner);
        toUnitSpinner = findViewById(R.id.toUnitSpinner);
        convertedResult = findViewById(R.id.convertedResult);
        convertBtn = findViewById(R.id.convertBtn);

        // Populate Spinners
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, units);
        fromUnitSpinner.setAdapter(adapter);
        toUnitSpinner.setAdapter(adapter);

        // Conversion factors to meters
        toMeterFactor = new HashMap<>();
        toMeterFactor.put("Meters", 1.0);
        toMeterFactor.put("Centimeters", 0.01);
        toMeterFactor.put("Feet", 0.3048);
        toMeterFactor.put("Inches", 0.0254);
        toMeterFactor.put("Yards", 0.9144);

        convertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                convertLength();
            }
        });
    }

    private void convertLength() {
        String inputStr = quantityInput.getText().toString().trim();
        if (inputStr.isEmpty()) {
            Toast.makeText(this, "Please enter a quantity", Toast.LENGTH_SHORT).show();
            return;
        }

        double quantity = Double.parseDouble(inputStr);
        String fromUnit = fromUnitSpinner.getSelectedItem().toString();
        String toUnit = toUnitSpinner.getSelectedItem().toString();

        // Convert to meters
        double inMeters = quantity * toMeterFactor.get(fromUnit);

        // Convert to target unit
        double result = inMeters / toMeterFactor.get(toUnit);

        convertedResult.setText(String.format("%.4f", result));
    }
}
