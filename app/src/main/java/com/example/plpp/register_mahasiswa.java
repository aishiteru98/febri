package com.example.plpp;

//import android.app.ProgressDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class register_mahasiswa extends AppCompatActivity {
    EditText editTextNama, editTextNpm, editTextPassword1;
    Button buttonRegister1;
    ProgressDialog ProgressDialog1;


    ProgressBar progressBar1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_mahasiswa);
        progressBar1 = findViewById(R.id.progressBar1);

        //if the user is already logged in we will directly start the MainActivity (profile) activity
        if (SharedPrefManager1.getInstance(this).isLoggedIn()) {
           finish();
            startActivity(new Intent(this, MainActivity2.class));
           return;
        }

        editTextNama = findViewById(R.id.editTextUsername1);
        editTextNpm = findViewById(R.id.editTextNpm);
        editTextPassword1 = findViewById(R.id.editTextPassword1);


        findViewById(R.id.buttonRegister1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //if user pressed on button register
                //here we will register the user to server
                registerUser1();
            }
        });

        findViewById(R.id.textViewLogin1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if user pressed on textview that already register open LoginActivity
                finish();
                startActivity(new Intent(register_mahasiswa.this, login_mahasiswa.class));
            }
        });

    }

    private void registerUser1() {
        final String Nama = editTextNama.getText().toString().trim();
        final String npm = editTextNpm.getText().toString().trim();
        final String password = editTextPassword1.getText().toString().trim();



        //first we will do the validations
        if (TextUtils.isEmpty(Nama)) {
            editTextNama.setError("Please enter username");
            editTextNama.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(npm)) {
            editTextNpm.setError("Please enter your NPM");
            editTextNpm.requestFocus();
            return;
        }


        if (TextUtils.isEmpty(password)) {
            editTextPassword1.setError("Enter a password");
            editTextPassword1.requestFocus();
            return;
        }

        final StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs1.URL_REGISTER1,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressBar1.setVisibility(View.GONE);

                        try {
                            //converting response to json object
                            JSONObject obj = new JSONObject(response);
                            //if no error in response
                            if (!obj.getBoolean("error")) {
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();

                                //getting the user from the response
                                JSONObject userJson1 = obj.getJSONObject("user2");

                                //creating a new user object
                                user2 user1 = new user2(
                                        userJson1.getInt("id"),
                                        userJson1.getString("Nama"),
                                        userJson1.getString("npm"),
                                        userJson1.getString("password")
                                );

                                //storing the user in shared preferences
                                SharedPrefManager1.getInstance(getApplicationContext()).userLogin1(user1);

                                //starting the profile activity
                                finish();
                                startActivity(new Intent(getApplicationContext(), MainActivity2.class));
                            } else {
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError{
                Map<String, String> params = new HashMap<>();
                params.put("Nama", Nama);
                params.put("npm", npm);
                params.put("password", password);

                return params;
            }
        };

        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }
}
