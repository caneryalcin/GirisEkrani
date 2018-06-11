package com.cat.girisekrani;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class giris extends AppCompatActivity {

    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    EditText kullaniciismii;
    EditText sifree;
    Button giris;
    Button kayitol;
    String a ;
    String b ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giris);

        giris = findViewById(R.id.giris);
        kayitol = findViewById(R.id.kayitol);
        kullaniciismii= findViewById(R.id.kullaniciismi);
        sifree = findViewById(R.id.sifre);
        preferences= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor = preferences.edit();

        final String kullanicibilgi=preferences.getString("kullanici", "");

        final String sifrebilgi= preferences.getString("Sifre", "");
        kullaniciismii.setText(kullanicibilgi);
        sifree.setText(sifrebilgi);




        kayitol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a =  kullaniciismii.getText().toString();
                b = sifree.getText().toString();
               if (kullanicibilgi.matches(a) && sifrebilgi.matches(b)){
                   Toast.makeText(giris.this, "Daha önce aynı kayıt yapılmış", Toast.LENGTH_SHORT).show();

               }else{
                   editor.putString("kullanici", kullaniciismii.getText().toString());
                   editor.putString("Sifre", sifree.getText().toString());
                   editor.commit();
                   Toast.makeText(giris.this, "Kayıt Yapıldı", Toast.LENGTH_SHORT).show();
               }



            }
        });
    }
}
