package com.cat.girisekrani;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class giris extends AppCompatActivity {

    SharedPreferences preferences;//preferences referansı
    SharedPreferences.Editor editor;//preferences editor nesnesi referansı .prefernces nesnesine veri ekleyip cıkarmak için kullanılır
    EditText kullanici;
    EditText sifre;
    Button girisbutton;
    Button kayitol;
    String kullaniciismi_string ;
    String sifre_string ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giris);

        girisbutton = findViewById(R.id.giris);
        kayitol = findViewById(R.id.kayitol);
        kullanici= findViewById(R.id.kullaniciismi);
        sifre = findViewById(R.id.sifre);
        preferences= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());//preferences objesi
        editor = preferences.edit();//editor nesnesi oluşturma.

        final String kullanicibilgi=preferences.getString("kullanici", "");
        final String sifrebilgi= preferences.getString("Sifre", "");


        kullanici.setText(kullanicibilgi);//string değeri kullanıcı edittext'ine atanır
        sifre.setText(sifrebilgi);//string değeri sifre edittext'ine atanır



        girisbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                kullaniciismi_string =  kullanici.getText().toString();
                sifre_string = sifre.getText().toString();

                if (kullaniciismi_string.matches("") && sifre_string.matches("")){//bilgiler eksik ise uyarı verir

                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(giris.this);
                    alertDialog.setTitle("Uyarı");
                    alertDialog.setMessage("Eksiksiz Doldurunuz!");
                    alertDialog.setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    alertDialog.show();

                }
                else {

                    final String kullanicibilgi=preferences.getString("kullanici", "");//preferences objesinden kaydettiğimiz değerleri geri alıyoruz

                    final String sifrebilgi= preferences.getString("Sifre", "");//preferences objesinden kaydettiğimiz değerleri geri alıyoruz.

                    if(kullanicibilgi.matches(kullaniciismi_string) && sifrebilgi.matches(sifre_string)){//edittextlerden alınan şifre ve mail değerleri shared preferencesdan alınan değerlerle eşleşiyorsa

                        //Giriş başarılı ise sharedpreferences login değerini true yapıyoruz.
                        editor.putBoolean("login", true);
                        Intent i = new Intent(getApplicationContext(),Anasayfa.class);//Anasayfa aktivity si açılır
                        startActivity(i);
                        finish();
                    }else{//şifre ve mail uyuşmuyorsa hata bastırılır
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(giris.this);
                        alertDialog.setTitle("Hata");
                        alertDialog.setMessage("Kullanıcı İsmi veya Şifreniz Uyuşmuyor!");
                        alertDialog.setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int which) {

                            }
                        });
                        alertDialog.show();
                    }
                }



            }
        });

        kayitol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kullaniciismi_string =  kullanici.getText().toString();
                sifre_string = sifre.getText().toString();
               if (kullanicibilgi.matches(kullaniciismi_string) && sifrebilgi.matches(sifre_string)){
                   Toast.makeText(giris.this, "Daha önce aynı kayıt yapılmış", Toast.LENGTH_SHORT).show();

               }else{
                   editor.putString("kullanici",kullanici.getText().toString());
                   editor.putString("Sifre", sifre.getText().toString());
                   editor.commit();//3 satırdaki toplam işlem kullanıcı ve sifre yi kaydeder ve birdahaki uygulamanın açılışında edittextlere aktarır
                   Toast.makeText(giris.this, "Kayıt Yapıldı", Toast.LENGTH_SHORT).show();
               }



            }
        });
    }
}
