package com.example.shopmark;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class client_MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_activity);
    }

    public void goToClient(View view){
        Intent IrALogin = new Intent(this,client_MainActivity.class);
        startActivity(IrALogin);
    }

    public void goToMenu(View view){
        Intent IrALogin = new Intent(this,menu_MainActivity.class);
        startActivity(IrALogin);
    }

    public void goToStoreHouse(View view){
        Intent IrALogin = new Intent(this,store_house_MainActivity.class);
        startActivity(IrALogin);
    }

}