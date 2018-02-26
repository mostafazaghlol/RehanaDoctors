package com.mostafa.android.rehanadoctors.login;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mostafa on 2/19/18.
 */

public class forgetRequest extends StringRequest {
    private static final String forget_request_url = "http://raihana-eg.com/site_api/api/foget_password_api";
    private Map<String,String> params;

    public forgetRequest(String username, String lang, Response.Listener<String> listener){
        super(Method.POST,forget_request_url,listener,null);
        params = new HashMap<>();
        params.put("username",username);
        params.put("lang",lang);
    }

    @Override
    protected Map<String, String> getParams()  {
        return params;
    }
}
