package com.example.test1;

import androidx.appcompat.app.AppCompatActivity;
import  com.example.test1.R.layout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener {
    TextView greetings;
    Spinner courseSpinner;
    TextView selectedFeeTV;
    TextView selectedHourTV;
    TextView totalHoursTV;
    TextView totalFeeTV;
    RadioGroup gradGroup;
    Button addBtn;
    String[] course = {"Java", "Swift", "iOS", "Android", "Database"};
    CheckBox accomodation;
    CheckBox medicalInsurance;
    int[] fee = {1300, 1500, 1350, 1400, 1000};
    int[] hours = {6, 5, 5, 7, 4};
    int totalHours = 0;
    int totalFee = 0;
    int feeSelected = 0;
    int hoursSelected = 0;
    int medicalAmount = 0;
    int accomodationAmount = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_home);

        String user = MainActivity.studentName;

        greetings = findViewById(R.id.welcomeText);
        courseSpinner=findViewById(R.id.courseSpinner);
        selectedFeeTV = findViewById(R.id.courseFeeTV);
        selectedHourTV = findViewById(R.id.courseHoursTV);
        totalHoursTV = findViewById(R.id.totalHoursTV);
        totalFeeTV = findViewById(R.id.totalFeeTV);
        gradGroup = findViewById(R.id.gradRadioGroup);
        addBtn = findViewById(R.id.addBtn);
        accomodation = findViewById(R.id.accomodation);
        medicalInsurance = findViewById(R.id.medicalInsurance);

        //Create the adapter for the spinner
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,course);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        courseSpinner.setAdapter(aa);//fill the spinner from the adapter

        courseSpinner.setOnItemSelectedListener(this);
        greetings.setText("Welcome " + user);
        addBtn.setOnClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        feeSelected = fee[position];
        hoursSelected = hours[position];
        selectedHourTV.setText(hoursSelected + "h");
        selectedFeeTV.setText("$" + feeSelected);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    @Override
    public void onClick(View view){
        calculateTotalHoursAndFee(feeSelected, hoursSelected);
    }

    public void calculateTotalHoursAndFee(int feeSelected, int hoursSelected) {
        totalFee += feeSelected;
        totalHours += hoursSelected;
        int maxHours = 0;
        if(gradGroup.getCheckedRadioButtonId() == R.id.graduate){
            maxHours = 21;
        }
        else {
            maxHours = 19;
        }
        if(totalHours > maxHours){
            totalHours -= hoursSelected;
            totalFee -= feeSelected;
            totalFeeTV.setText("$"+totalFee);
            totalHoursTV.setText(totalHours + "h");
            Toast toast = Toast.makeText(getApplicationContext(), "you can’t add" +
                    "this course", Toast.LENGTH_LONG);
            toast.show();
        }
        else {
            totalFeeTV.setText("$"+totalFee);
            totalHoursTV.setText(totalHours + "h");
        }
    }
    public void onAccomodationCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();
        if(checked){
            accomodationAmount = 1000;
            totalFee += accomodationAmount;
        }
        else {
            accomodationAmount = 0;
            totalFee -= 1000;
        }

    }
    public void onMedicalClicked(View view){
        boolean checked = ((CheckBox) view).isChecked();
        if(checked){
            medicalAmount = 700;
            totalFee += medicalAmount;
        }
        else {
            medicalAmount = 0;
            totalFee -= 700;
        }
    }
}
