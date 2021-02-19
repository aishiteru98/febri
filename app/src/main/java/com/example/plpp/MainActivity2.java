package com.example.plpp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {
    TextView id,Nama,npm;
    Button btnLogout2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        id = findViewById(R.id.textViewId2);
        Nama = findViewById(R.id.textViewUsername2);
        npm = findViewById(R.id.textViewnpm);

        user2 user = SharedPrefManager1.getInstance(this).getUser1();

        id.setText(String.valueOf(user.getId()));
        Nama.setText(user.getNama());
        npm.setText(user.getnpm());

        findViewById(R.id.buttonLogout2) .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                SharedPrefManager1.getInstance(getApplicationContext()).logout();
            }
        });
    }

}
