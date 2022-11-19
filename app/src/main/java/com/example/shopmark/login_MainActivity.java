package com.example.shopmark;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class login_MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
    }
    public void goToFirst(View view){
        Intent IrALogin = new Intent(this,first_MainActivity.class);
        startActivity(IrALogin);
    }

}