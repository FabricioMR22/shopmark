package com.example.shopmark;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class first_MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_activity);
    }

    public void goToLogin(View view){
        Intent IrALogin = new Intent(this,login_MainActivity.class);
        startActivity(IrALogin);
    }

    public void goToRegister(View view){
        Intent IrALogin = new Intent(this,register_MainActivity.class);
        startActivity(IrALogin);
    }

}