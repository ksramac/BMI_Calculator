package com.kyleram.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;



public class MainActivity extends AppCompatActivity {

    private Button calcButton;
    private RadioButton radButtonMale;
    private TextView resultText;
    private RadioButton radButtonFemale;
    private EditText editTextAge;
    private EditText editTextFeet;
    private EditText editTextInches;
    private EditText editTextWeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        setupButtonClickListener();
    }

    private void setupButtonClickListener() {
        calcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this, "We can react to button clicks!", Toast.LENGTH_LONG).show();

                double bmiResult = calculateBMI();

                String ageText = editTextAge.getText().toString();
                int age = Integer.parseInt(ageText);

                if(age>=18){
                    displayResult(bmiResult);
                } else {
                    displayGuidance(bmiResult);
                }
            }
        });
    }

    private void displayGuidance(double BMI) {
        DecimalFormat decimalFormatter = new DecimalFormat("0.00");
        String bmiString = decimalFormatter.format(BMI);

        if(radButtonMale.isChecked()){
            resultText.setText(bmiString + " - You are under 18, please consult your doctor for the healthy BMI range for boys");
        } else if(radButtonFemale.isChecked()){
            resultText.setText(bmiString + " - You are under 18, please consult your doctor for the healthy BMI range for girls");
        } else {
            resultText.setText(bmiString + " - You are under 18, please consult your doctor for the healthy BMI range");
        }
    }

    private double calculateBMI() {
        String feetText = editTextFeet.getText().toString();
        String inchesText = editTextInches.getText().toString();
        String weightText = editTextWeight.getText().toString();

        double height = (Double.parseDouble(feetText)*12) + (Double.parseDouble(inchesText));
        double weight = Double.parseDouble(weightText);

        double BMI = (weight/Math.pow(height, 2))*703;

       return BMI;
    }

    private void displayResult(double BMI){
        DecimalFormat decimalFormatter = new DecimalFormat("0.00");
        String bmiString = decimalFormatter.format(BMI);

        if(BMI < 18.5){
            resultText.setText(bmiString + " - You are underweight... Go pack on some pounds!");
        } else if(BMI > 25){
            resultText.setText(bmiString + " - You are overweight... Go start a diet!");
        } else {
            resultText.setText(bmiString + " - You are a healthy weight! Keep it up! :)");
        }
    }

    private void findViews(){
        resultText = findViewById(R.id.text_view_result);

        radButtonMale = findViewById(R.id.radio_button_male);
        radButtonFemale = findViewById(R.id.radio_button_female);

        editTextAge = findViewById(R.id.edit_text_age);
        editTextFeet = findViewById(R.id.edit_text_feet);
        editTextInches = findViewById(R.id.edit_text_inches);
        editTextWeight = findViewById(R.id.edit_text_weight);

        calcButton = findViewById(R.id.button_calculate);
    }

}