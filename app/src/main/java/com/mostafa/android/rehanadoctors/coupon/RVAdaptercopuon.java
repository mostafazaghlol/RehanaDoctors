package com.mostafa.android.rehanadoctors.coupon;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mostafa.android.rehanadoctors.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mostafa on 2/11/18.
 */

public class RVAdaptercopuon extends RecyclerView.Adapter<RVAdaptercopuon.PersonViewHolder> {
    List<coupon> coupons;
    private Context mContext;
    String id_client;

    RVAdaptercopuon(List<coupon> mcoupons, Context context){
        this.coupons= mcoupons;
        mContext = context;
    }
    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.customcardviewcoupon, viewGroup, false);
        SharedPreferences sharedPreferences = mContext.getSharedPreferences("pref", 0);
        id_client =sharedPreferences.getString("client_id","");
        PersonViewHolder pvh = new PersonViewHolder(v);
        return pvh;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public void onBindViewHolder(PersonViewHolder personViewHolder, int i) {
        personViewHolder.coupondata.setText(coupons.get(i).comment);
        final String id_coupon = coupons.get(i).id_coupon;
//        personViewHolder.pick.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Response.Listener<String> listener = new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        try {
//                            JSONObject jsonObject = new JSONObject(response);
//                            JSONArray jsonArray = jsonObject.getJSONArray("message");
//                            for (int i=0;i<jsonArray.length();i++){
//                                String message = jsonArray.getJSONObject(i).getString("message");
//                                Toast.makeText(mContext, ""+message, Toast.LENGTH_SHORT).show();
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
//                    }
//                };
//                couponpickRequest couponpickRequest = new couponpickRequest("1",id_client,id_coupon, listener);
//                RequestQueue queue = Volley.newRequestQueue(mContext);
//                queue.add(couponpickRequest);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return coupons.size();
    }

    public static class PersonViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView coupondata;
        Button pick;


        PersonViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cv10);
            cv.setCardBackgroundColor(Color.WHITE);
            cv.setRadius(100);
            coupondata = (TextView) itemView.findViewById(R.id.coupondata);
            pick = (Button)itemView.findViewById(R.id.pick);
        }
    }

    public class couponpickRequest extends StringRequest {
        private static final String pickurl="http://raihana-eg.com/site_api/api/activation_coupon";
        private Map<String,String> params;

        public couponpickRequest(String lang,String id_client,String id_coupon,Response.Listener<String> listener){
            super(Method.POST,pickurl,listener,null);
            params = new HashMap<>();
            params.put("lang",lang);
            params.put("id_client",id_client);
            params.put("id_coupon",id_coupon);

        }

        @Override
        public Map<String, String> getParams() {
            return params;
        }
    }
}