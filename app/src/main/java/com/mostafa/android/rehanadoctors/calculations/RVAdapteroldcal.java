package com.mostafa.android.rehanadoctors.calculations;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mostafa.android.rehanadoctors.R;
import com.mostafa.android.rehanadoctors.images;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mostafa on 2/11/18.
 */

public class RVAdapteroldcal extends RecyclerView.Adapter<RVAdapteroldcal.PersonViewHolder> {
    List<calculatedate> calculates;
    private Context mContext;
    String id_client;
    String id;

    RVAdapteroldcal(List<calculatedate> persons, Context context) {
        this.calculates = persons;
        mContext = context;
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.customcardviewoldcal, viewGroup, false);
        SharedPreferences sharedPreferences = mContext.getSharedPreferences("pref", 0);
        id_client = sharedPreferences.getString("client_id", "");
        PersonViewHolder pvh = new PersonViewHolder(v);
        return pvh;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public void onBindViewHolder(final PersonViewHolder personViewHolder, int i) {
        personViewHolder.Cost.setText("cost : " + calculates.get(i).cost);
        personViewHolder.id.setText("id : " + calculates.get(i).id);
        id = calculates.get(i).calid;
        Picasso.with(mContext).load(calculates.get(i).image).into(personViewHolder.calphoto);
        personViewHolder.oldButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Response.Listener<String> listener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("data");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                String message = jsonArray.getJSONObject(i).getString("message");
                                Toast.makeText(mContext, "" + message, Toast.LENGTH_SHORT).show();
//                                personViewHolder.linearLayout.setVisibility(View.GONE);
//                                Toast.makeText(mContext, ""+id, Toast.LENGTH_SHORT).show();
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }


                };
                deletecalculations deletecalculations1 = new deletecalculations(images.lang, id, listener);
                RequestQueue queue = Volley.newRequestQueue(mContext);
                queue.add(deletecalculations1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return calculates.size();
    }

    public static class PersonViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView Cost;
        TextView id;
        ImageView calphoto;
        ImageView oldButton;
        LinearLayout linearLayout;

        PersonViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cv4);
            cv.setCardBackgroundColor(Color.WHITE);
            cv.setRadius(100);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.linear22);
            Cost = (TextView) itemView.findViewById(R.id.cost);
            id = (TextView) itemView.findViewById(R.id.idtext);
            calphoto = (ImageView) itemView.findViewById(R.id.person_photo);
            oldButton = (ImageView) itemView.findViewById(R.id.delete);
        }
    }

    public class deletecalculations extends StringRequest {
        private final static String url = "http://raihana-eg.com/site_api/api/dr_delete_calculation_api";
        private Map<String, String> params;

        public deletecalculations(String lang, String calculationId, Response.Listener<String> listener) {
            super(Method.POST, url, listener, null);
            params = new HashMap<>();
            params.put("lang", lang);
            params.put("calculation_id", calculationId);
        }

        @Override
        public Map<String, String> getParams() {
            return params;
        }

    }
}