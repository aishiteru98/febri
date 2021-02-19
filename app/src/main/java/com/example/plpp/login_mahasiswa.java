package com.example.plpp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
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

public class login_mahasiswa extends AppCompatActivity {
    EditText etnpm, etPassword1;
    ProgressBar progressBar2;
    ProgressDialog ProgressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_mahasiswa);

        if (SharedPrefManager.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(this, login_mahasiswa.class));
        }


        progressBar2 = findViewById(R.id.progressBar2);
        etnpm = findViewById(R.id.etnpm);
        etPassword1 = findViewById(R.id.etUserPassword1);



        //calling the method userLogin() for login the user
        findViewById(R.id.btnLogin2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userLogin1();
            }
        });

        //if user presses on textview not register calling RegisterActivity
        findViewById(R.id.tvRegister2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(getApplicationContext(), register_mahasiswa.class));
            }
        });
    }

    private void userLogin1() {
        //first getting the values
        final String npm = etnpm.getText().toString();
        final String password1 = etPassword1.getText().toString();
        //validating inputs
        if (TextUtils.isEmpty(npm)) {
            etnpm.setError("Please enter your npm");
            etnpm.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password1)) {
            etPassword1.setError("Please enter your password");
            etPassword1.requestFocus();
            return;
        }

        //if everything is fine
        StringRequest stringRequest = new StringRequest(Request.Method.POST,URLs1.URL_LOGIN1,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressBar2.setVisibility(View.GONE);

                        try {
                            //converting response to json object
                            JSONObject obj = new JSONObject(response);

                            //if no error in response
                            if (!obj.getBoolean("error")) {
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();

                                //getting the user from the response
                                JSONObject userJson = obj.getJSONObject("user");

                                //creating a new user object
                                user2 user2 = new user2(
                                        userJson.getInt("id"),
                                        userJson.getString("Nama"),
                                        userJson.getString("npm"),
                                        userJson.getString("Password")
                                );

                                //storing the user in shared preferences
                                SharedPrefManager1.getInstance(getApplicationContext()).userLogin1(user2);
                                //starting the profile activity
                                finish();
                                startActivity(new Intent(getApplicationContext(), jawab.class));
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
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("npm", npm);
                params.put("password", password1);
                return params;
            }
        };

        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }
}
