package com.mostafa.android.rehanadoctors.coupon;

/**
 * Created by mostafa on 2/25/18.
 */

public class coupon {
    String code_coupon,comment,id_coupon;

    coupon(String mcode_coupon, String mcomment, String id){
        this.code_coupon=mcode_coupon;
        this.comment = mcomment;
        this.id_coupon = id;
    }
    public String getCode_coupon() {
        return code_coupon;
    }

    public String getComment() {
        return comment;
    }

    public String getId_coupon() {
        return id_coupon;
    }
}
