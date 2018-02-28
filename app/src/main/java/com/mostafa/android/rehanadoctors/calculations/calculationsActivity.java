package com.mostafa.android.rehanadoctors.calculations;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class calculationsActivity extends AppCompatActivity {
    RecyclerView rv1, rv2;
    private List<calculatedate> calculates1, calculates2, Donecalculates1;
    private Context context;
    ProgressBar progressBar;
    TextView txNo, txNo2;
    Button newcal, oldcal;
    RVAdapteroldcal adapter;
    RVAdapternewcal adapter2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBarColored(this);

        setContentView(R.layout.activity_calculations);
        rv2 = (RecyclerView) findViewById(R.id.rv5);
        rv1 = (RecyclerView) findViewById(R.id.rv4);

        newcal = (Button) findViewById(R.id.newcalc);
        oldcal = (Button) findViewById(R.id.oldcalc);
        context = this;
        calculates1 = new ArrayList<>();
        calculates2 = new ArrayList<>();
        txNo = (TextView) findViewById(R.id.txno);
        txNo2 = (TextView) findViewById(R.id.option);
        progressBar = (ProgressBar) findViewById(R.id.progressoldcal);
        progressBar.setVisibility(View.INVISIBLE);
        final SharedPreferences sharedPreferences = getSharedPreferences("pref", 0);
        final String id_client = sharedPreferences.getString("client_id", " ");

        oldcal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculates1.clear();
                txNo2.setVisibility(View.INVISIBLE);
                progressBar.setVisibility(View.VISIBLE);
                rv1.setVisibility(View.VISIBLE);
                rv2.setVisibility(View.INVISIBLE);
                txNo.setVisibility(View.INVISIBLE);
                if (cal.x != 1) {
                    Response.Listener<String> listener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                progressBar.setVisibility(View.GONE);
                                JSONObject jsonObject = new JSONObject(response);
                                final JSONArray jsonArray = jsonObject.optJSONArray("data");
                                if (jsonArray.getJSONObject(0).has("cost")) {
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        String cost = jsonArray.getJSONObject(i).getString("cost");
                                        String calculation_id = jsonArray.getJSONObject(i).getString("calculation_id");
                                        String image_calculation = jsonArray.getJSONObject(i).getString("image_calculation");
                                        String comment = jsonArray.getJSONObject(i).getString("comment");
                                        calculates1.add(new calculatedate(cost, comment, image_calculation, calculation_id));
                                    }
                                } else {
                                    txNo.setVisibility(View.VISIBLE);
                                    txNo.setText(jsonArray.getJSONObject(0).getString("message"));
                                }
                                LinearLayoutManager llm = new LinearLayoutManager(calculationsActivity.this);
                                rv1.setLayoutManager(llm);
                                adapter = new RVAdapteroldcal(calculates1, context);
                                rv1.setAdapter(adapter);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    };

                    oldcalculations oldcalculations = new oldcalculations(images.lang, listener);
                    RequestQueue queue = Volley.newRequestQueue(calculationsActivity.this);
                    queue.add(oldcalculations);
                } else {
                    progressBar.setVisibility(View.GONE);
                    rv1.setAdapter(adapter);
                }
            }
        });


        newcal.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                calculates2.clear();
                txNo2.setVisibility(View.INVISIBLE);
                progressBar.setVisibility(View.VISIBLE);
                rv2.setVisibility(View.VISIBLE);
                rv1.setVisibility(View.INVISIBLE);
                txNo.setVisibility(View.INVISIBLE);
//               if(cal.y!=1) {
                Response.Listener<String> listener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            progressBar.setVisibility(View.GONE);
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.optJSONArray("data");

                            if (jsonArray.getJSONObject(0).has("cost")) {
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    String cost = jsonArray.getJSONObject(i).getString("cost");
                                    String comment = jsonArray.getJSONObject(i).getString("comment");
                                    String image_calculation = jsonArray.getJSONObject(i).getString("image_calculation");
                                    String calID = jsonArray.getJSONObject(i).getString("calculation_id");
                                    calculates2.add(new calculatedate(cost, comment, image_calculation, calID));
                                }
                            } else {
                                txNo.setVisibility(View.VISIBLE);
                                txNo.setText(jsonArray.getJSONObject(0).getString("message"));
                            }
                            LinearLayoutManager llm = new LinearLayoutManager(calculationsActivity.this);
                            rv2.setLayoutManager(llm);
                            adapter2 = new RVAdapternewcal(calculates2, context, getParent());
                            rv2.setAdapter(adapter2);
//                                   

                            rv2.addOnItemTouchListener(
                                    new RecyclerItemClickListener(context, rv2, new RecyclerItemClickListener.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(View view, int position) {
                                            // do whatever
                                            cal.calculation_ID = calculates2.get(position).calid;
                                            CustomDialogAddCost cdd2 = new CustomDialogAddCost(calculationsActivity.this);
                                            cdd2.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                                            cdd2.show();
                                            rv2.setAdapter(adapter2);
                                        }

                                        @Override
                                        public void onLongItemClick(View view, int position) {
                                            // do whatever
                                        }
                                    })
                            );

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                newcalculationsRequest newcalculations = new newcalculationsRequest(images.lang, listener);
                RequestQueue queue1 = Volley.newRequestQueue(calculationsActivity.this);
                queue1.add(newcalculations);

// else {
//                   progressBar.setVisibility(View.GONE);
//                   rv2.setAdapter(adapter2);
//               }

            }
        });


    }

    public static void setStatusBarColored(Activity context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = context.getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            int statusBarHeight = getStatusBarHeight(context);

            View view = new View(context);
            view.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            view.getLayoutParams().height = statusBarHeight;
            ((ViewGroup) w.getDecorView()).addView(view);
//            view.setBackground(context.getResources().getDrawable());
        }
    }

    public static int getStatusBarHeight(Activity context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public void backIcon(View view) {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        cal.y = 0;
        cal.x = 0;
        super.onBackPressed();
    }

    public class oldcalculations extends StringRequest {
        private final static String url = "http://raihana-eg.com/site_api/api/dr_myoldcalculation_api";
        private Map<String, String> params;

        public oldcalculations(String lang, Response.Listener<String> listener) {
            super(Method.POST, url, listener, null);
            params = new HashMap<>();
            params.put("lang", lang);
        }

        @Override
        protected Map<String, String> getParams() {
            return params;
        }
    }

    public class newcalculationsRequest extends StringRequest {
        private final static String url = "http://raihana-eg.com/site_api/api/dr_mynewcalculation_api";
        private Map<String, String> params;

        public newcalculationsRequest(String lang, Response.Listener<String> listener) {
            super(Method.POST, url, listener, null);
            params = new HashMap<>();
            params.put("lang", lang);
        }

        @Override
        protected Map<String, String> getParams() {
            return params;
        }
    }

}
