package com.example.plpp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;



public class awal extends AppCompatActivity {

    @Override
    protected void onCreate (final Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.awal);
    findViewById(R.id.mhs).setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            finish();
            startActivity(new Intent(awal.this, login_mahasiswa.class));
            }
    });

    findViewById(R.id.dosn).setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            finish();
            startActivity(new Intent(awal.this, login.class));
        }
    });

    }

    //public void  clickmahasiswa(View view){
        //finish();
        //Intent intent = new Intent(awal.this, jawab.class);
      //  startActivity(new Intent(getApplicationContext(),jawab.class));
    //}
    //public void  clickdosen (View view){
        //finish();
        //Intent intent1 = new Intent(awal.this, login.class);
        //startActivity(new Intent(getApplicationContext(),login.class));
    //}

}
