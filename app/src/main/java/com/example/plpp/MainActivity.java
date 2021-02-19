package com.example.plpp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView id,nama,nik;
    Button btnLogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        id = findViewById(R.id.textViewId);
        nama = findViewById(R.id.textViewUsername);
        nik = findViewById(R.id.textViewnik);

        user user = SharedPrefManager.getInstance(this).getUser();

        id.setText(String.valueOf(user.getId()));
        nama.setText(user.getnama());
        nik.setText(user.getnik());

        findViewById(R.id.buttonLogout) .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                SharedPrefManager.getInstance(getApplicationContext()).logout();
            }
        });
    }
}
