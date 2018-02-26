package com.mostafa.android.rehanadoctors.login;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mostafa on 2/18/18.
 */

public class RegisterRequest extends StringRequest {
    private static final String Register_Request_Url = "http://raihana-eg.com/site_api/api/register_api";
    private Map<String,String> params;

    public RegisterRequest(String name, String phone, String email, String lang, String password, Response.Listener<String> listener){
        super(Method.POST,Register_Request_Url,listener,null);

        params = new HashMap<>();
        params.put("lang",lang);
        params.put("name",name);
        params.put("phone",phone);
        params.put("email",email);
        params.put("pass",password);
    }

    @Override
    protected Map<String, String> getParams()  {
        return params;
    }
}
