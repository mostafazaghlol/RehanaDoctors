package com.mostafa.android.rehanadoctors.calculations;

/**
 * Created by mostafa on 2/11/18.
 */

public class calculatedate {
    String cost;
    String id;
    String image;
    String calid;

    calculatedate(String mcost, String mid, String mimage) {
        this.cost = mcost;
        this.id = mid;
        this.image = mimage;
    }

    calculatedate(String mcost, String mid, String mimage, String Id) {
        this.cost = mcost;
        this.id = mid;
        this.image = mimage;
        this.calid = Id;
    }
}
