package com.example.test1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private String user = "student1";
    private String pass = "123456";
    private EditText userName;
    private EditText password;
    private EditText studentNameET;
    private Button signInBtn;


    public  static String studentName = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userName = findViewById(R.id.userNameET);
        password = findViewById(R.id.passwordET);
        studentNameET = findViewById(R.id.fullNameET);
        signInBtn = findViewById(R.id.signInBtn);
        signInBtn.setOnClickListener(this);

    }


    @Override
    public void onClick(View view)
    {
        if(userName.getText().toString().equalsIgnoreCase(user) && password.getText().toString().equalsIgnoreCase(pass)){
            studentName = studentNameET.getText().toString();
            Toast toast = Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG);
            toast.show();


            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(intent);

        }
        else {
            Toast toast = Toast.makeText(getApplicationContext(), "Fail", Toast.LENGTH_LONG);
            toast.show();
        }

    }
}
