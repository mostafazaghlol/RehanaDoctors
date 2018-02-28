package com.mostafa.android.rehanadoctors.calculations;

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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mostafa on 2/19/18.
 */

public class CustomDialogAddCost extends Dialog {
    public Activity c;
    private EditText addcostET;
    String cost;
    private Button search;
    String lan = "2";


    public CustomDialogAddCost(Activity a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog_for_addcost);
        addcostET = (EditText) findViewById(R.id.addcost2);
        search = (Button) findViewById(R.id.button);

        search.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View view) {

                                          cost = addcostET.getText().toString().trim();
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
                                                      }


                                                  } catch (JSONException e) {
                                                      e.printStackTrace();
                                                  }
                                              }
                                          };
                                          addcostRequest addcouponRequest = new addcostRequest(images.lang, cal.calculation_ID, cost, responseListener);
                                          RequestQueue queue = Volley.newRequestQueue(c);
                                          queue.add(addcouponRequest);

                                      }


                                  }
        );


    }


    public class addcostRequest extends StringRequest {
        private static final String url = "http://raihana-eg.com/site_api/api/add_cost_calculation_api";
        private Map<String, String> params;

        public addcostRequest(String lang, String calculation_id, String cost, Response.Listener<String> listener) {
            super(Method.POST, url, listener, null);
            params = new HashMap<>();
            params.put("lang", lang);
            params.put("calculation_id", calculation_id);
            params.put("cost", cost);
        }

        @Override
        public Map<String, String> getParams() {
            return params;
        }
    }


}
