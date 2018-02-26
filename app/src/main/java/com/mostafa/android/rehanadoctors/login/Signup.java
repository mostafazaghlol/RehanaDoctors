package com.mostafa.android.rehanadoctors.login;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.mostafa.android.rehanadoctors.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Signup extends AppCompatActivity {
    EditText etname, etphone, etemail, etpass, etrepeatpass;
    Button signup;
    String name, phone, password, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        etname = (EditText) findViewById(R.id.fullName);
        etphone = (EditText) findViewById(R.id.phone);
        etpass = (EditText) findViewById(R.id.password);
        etemail = (EditText) findViewById(R.id.Email);
        etrepeatpass = (EditText) findViewById(R.id.RepeatPassword);
        signup = (Button) findViewById(R.id.signup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = etname.getText().toString();
                phone = etphone.getText().toString();
                if (!etpass.getText().toString().equals(etrepeatpass.getText().toString())) {
                    Toast.makeText(Signup.this, "The password is not correct !", Toast.LENGTH_SHORT).show();
                } else {
                    password = etpass.getText().toString();
                }
                email = etemail.getText().toString();
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            JSONArray jsonArray = jsonResponse.getJSONArray("message");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject respons = jsonArray.getJSONObject(i);
                                String message = respons.getString("message");
                                int messageID = respons.getInt("messageID");
                                if (messageID == 0) {
                                    Toast.makeText(Signup.this, ""+message, Toast.LENGTH_SHORT).show();
                                } else if (messageID == 1) {
                                    Toast.makeText(Signup.this, ""+message, Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(Signup.this, "There is an error", Toast.LENGTH_SHORT).show();

                        }
                    }
                };

                RegisterRequest registerRequest = new RegisterRequest(name, phone, email, "2", password, responseListener);
                RequestQueue queue = Volley.newRequestQueue(Signup.this);
                queue.add(registerRequest);
            }
        });

    }

    public void backicon(View view) {
        onBackPressed();
    }
}
