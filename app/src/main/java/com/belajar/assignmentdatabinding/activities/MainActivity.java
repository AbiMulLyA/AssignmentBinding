package com.belajar.assignmentdatabinding.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.belajar.assignmentdatabinding.R;

public class MainActivity extends AppCompatActivity {
    CheckBox cbTerm;
    Button btnNextToMain, btnToNumberTwo;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences(SplashScreen.my_shared, Context.MODE_PRIVATE);
        if (!SplashScreen.syarat){
            ShowSK();
        }
    }

    private void ShowSK(){
        View dialogView;
        LayoutInflater inflater;
        inflater     = getLayoutInflater();
        dialogView = inflater.inflate(R.layout.sk_dialog,null);
        final AlertDialog skDialog = new AlertDialog.Builder(MainActivity.this).create();
        skDialog.setTitle(R.string.sk_title);
        skDialog.setIcon(R.drawable.logo);
        skDialog.setCancelable(false);
        skDialog.setView(dialogView);

        cbTerm = dialogView.findViewById(R.id.checkBox);
        btnNextToMain = dialogView.findViewById(R.id.btnNextToMain);

        btnNextToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cbTerm.isChecked()){
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean(SplashScreen.SESS_SYARAT, true);
                    editor.apply();

                    skDialog.dismiss();
                }else{
                    Toast.makeText(MainActivity.this, R.string.sk_title, Toast.LENGTH_LONG).show();
                }
            }
        });
        skDialog.show();
    }

    public void clearSession(View view) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(SplashScreen.my_shared, false);
        editor.putBoolean(SplashScreen.SESS_SYARAT, false);
        editor.apply();

        if (editor.commit()){
            startActivity(new Intent(MainActivity.this, SplashScreen.class));
            finish();
        }
    }

    public void btnToNumberTwo(View view) {
        startActivity(new Intent(getApplicationContext(), SecondActivity.class));
    }

    public void btnToNumberThree(View view) {
        startActivity(new Intent(getApplicationContext(), ThirdActivity.class));
    }
}