package com.mostafa.android.rehanadoctors.login;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
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

/**
 * Created by mostafa on 2/19/18.
 */

public class CustomDialogClass2 extends Dialog {
    public Activity c;
    private EditText userEditText;
    private Button search;
    String lan="2";

    public CustomDialogClass2(Activity a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog_for_coupon);
        userEditText = (EditText)findViewById(R.id.editText);
        search = (Button)findViewById(R.id.button);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Response received from the server
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            JSONArray jsonArray = jsonResponse.getJSONArray("message");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject respons = jsonArray.getJSONObject(i);
                                String message = respons.getString("message");
                                int messsageid = respons.getInt("messageID");
                                if(messsageid == 0){
                                    Toast.makeText(c, ""+message, Toast.LENGTH_SHORT).show();
                                }else if(messsageid == 1) {
                                    Toast.makeText(c, ""+message, Toast.LENGTH_SHORT).show();
                                }
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                forgetRequest forgetRequest= new forgetRequest(userEditText.getText().toString().trim(), lan, responseListener);
                RequestQueue queue = Volley.newRequestQueue(c);
                queue.add(forgetRequest);

            }


        }
        );


    }




}
