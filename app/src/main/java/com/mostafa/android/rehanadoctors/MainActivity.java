package com.mostafa.android.rehanadoctors;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.mostafa.android.rehanadoctors.coupon.coupon;
import com.mostafa.android.rehanadoctors.coupon.couponActivity;
import com.mostafa.android.rehanadoctors.login.Login;

public class MainActivity extends AppCompatActivity {
    Button coupons,calculates;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBarColored(this);
        setContentView(R.layout.activity_main);
        coupons = (Button)findViewById(R.id.coupon);
        calculates = (Button)findViewById(R.id.calculates);
        sharedPreferences = getSharedPreferences("pref", 0);
        final Intent loginIntent = new Intent(this,Login.class);
        final Intent couponIntent = new Intent(this, couponActivity.class);


        coupons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sharedPreferences.contains("user") && sharedPreferences.getInt("login", 0) == 1) {
                    startActivity(couponIntent);
                } else {
                    Toast.makeText(getApplicationContext(), "" + getString(R.string.mustlog), Toast.LENGTH_SHORT).show();
                    startActivity(loginIntent);
                }
            }
        });

        calculates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sharedPreferences.contains("user") && sharedPreferences.getInt("login", 0) == 1) {
//                    Intent ResIntent = new Intent(this, WaitedReservationsActivity.class);
//                    startActivity(ResIntent);
                } else {
                    Toast.makeText(getApplicationContext(), "" + getString(R.string.mustlog), Toast.LENGTH_SHORT).show();
                    startActivity(loginIntent);
                }
            }
        });
    }

    public static void setStatusBarColored(Activity context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
        {
            Window w = context.getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            int statusBarHeight = getStatusBarHeight(context);

            View view = new View(context);
            view.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            view.getLayoutParams().height = statusBarHeight;
            ((ViewGroup) w.getDecorView()).addView(view);
            view.setBackgroundColor(context.getResources().getColor(R.color.green));
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

}
