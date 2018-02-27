package com.mostafa.android.rehanadoctors.coupon;

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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mostafa.android.rehanadoctors.R;
import com.mostafa.android.rehanadoctors.images;
import com.mostafa.android.rehanadoctors.login.forgetRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mostafa on 2/19/18.
 */

public class CustomDialogAddCoupon extends Dialog {
    public Activity c;
    private EditText commentEditText, clientcountEditText, totalcountEditText;
    String comment, clientcount, totalcount;
    private Button search;
    String lan = "2";


    public CustomDialogAddCoupon(Activity a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog_for_coupon);
        commentEditText = (EditText) findViewById(R.id.comment);
        clientcountEditText = (EditText) findViewById(R.id.clientcount);
        totalcountEditText = (EditText) findViewById(R.id.totalcount);
        search = (Button) findViewById(R.id.button);

        search.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View view) {

                                          comment = commentEditText.getText().toString().trim();
                                          clientcount = clientcountEditText.getText().toString().trim();
                                          totalcount = totalcountEditText.getText().toString().trim();

                                          // Response received from the server
                                          Response.Listener<String> responseListener = new Response.Listener<String>() {
                                              @Override
                                              public void onResponse(String response) {
                                                  try {
                                                      JSONObject jsonResponse = new JSONObject(response);
                                                      JSONArray jsonArray = jsonResponse.getJSONArray("data");
                                                      for (int i = 0; i < jsonArray.length(); i++) {
                                                          JSONObject respons = jsonArray.getJSONObject(i);
                                                          String message = respons.getString("message");
                                                          Toast.makeText(c, " " + message, Toast.LENGTH_LONG).show();
                                                          commentEditText.setText("");
                                                          clientcountEditText.setText("");
                                                          totalcountEditText.setText("");
                                                      }


                                                  } catch (JSONException e) {
                                                      e.printStackTrace();
                                                  }
                                              }
                                          };
                                          addcouponRequest addcouponRequest = new addcouponRequest(images.lang, clientcount, totalcount, comment, responseListener);
                                          RequestQueue queue = Volley.newRequestQueue(c);
                                          queue.add(addcouponRequest);

                                      }


                                  }
        );


    }


    public class addcouponRequest extends StringRequest {
        private static final String addcoupon_url = "http://raihana-eg.com/site_api/api/Add_coupon";
        private Map<String, String> params;

        public addcouponRequest(String lang, String client_count, String total_count, String comment, Response.Listener<String> listener) {
            super(Method.POST, addcoupon_url, listener, null);
            params = new HashMap<>();
            params.put("client_count", client_count);
            params.put("total_count", total_count);
            params.put("comment", comment);
            params.put("lang", lang);
        }

        /*
        post('lang'); -------> lang (1 eng or 2 ar)
        post('client_count'); -------> client_count
        post('total_count'); -------> total_count
        post('comment'); -------> comment
         */
        @Override
        protected Map<String, String> getParams() {
            return params;
        }
    }


}
