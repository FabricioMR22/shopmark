package com.example.shopmark;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class buy_MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buy_activity);
    }

    public void goToMenu(View view){
        Intent IrALogin = new Intent(this,menu_MainActivity.class);
        startActivity(IrALogin);
    }
}