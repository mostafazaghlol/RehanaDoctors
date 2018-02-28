package com.mostafa.android.rehanadoctors.calculations;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.mostafa.android.rehanadoctors.R;
import com.mostafa.android.rehanadoctors.coupon.CustomDialogAddCoupon;
import com.mostafa.android.rehanadoctors.coupon.couponActivity;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mostafa on 2/11/18.
 */

public class RVAdapternewcal extends RecyclerView.Adapter<RVAdapternewcal.PersonViewHolder> {
    List<calculatedate> calculates;
    private Context mContext;
    String x11;
    Activity x = null;

    RVAdapternewcal(List<calculatedate> persons, Context context, Activity c) {
        this.calculates = persons;
        mContext = context;
        this.x = c;
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.customcardviewnewcal, viewGroup, false);
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
        personViewHolder.id.setText("Comment :  " + calculates.get(i).id);
        Picasso.with(mContext).load(calculates.get(i).image).into(personViewHolder.calphoto);
//        personViewHolder.addcost.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                cal.calculation_ID = x11;
//                Toast.makeText(mContext, ""+cal.calculation_ID, Toast.LENGTH_SHORT).show();
//                try {
//                    CustomDialogAddCost cdd2 = new CustomDialogAddCost(mContext);
//                    cdd2.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                    cdd2.show();
//                }catch (Exception e){
//                    Log.e("hi" ,""+e.getMessage());
//                }
//            }
//        });
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
        Button addcost;

        PersonViewHolder(View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.cv9);
            cv.setCardBackgroundColor(Color.WHITE);
            cv.setRadius(100);
            Cost = itemView.findViewById(R.id.cost2);
            id = itemView.findViewById(R.id.idtext2);
            calphoto = itemView.findViewById(R.id.person_photo2);
            addcost = itemView.findViewById(R.id.addcost);
        }
    }


}