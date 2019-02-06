package com.sreeyainfotech.mapexample.networkcall;

import com.sreeyainfotech.mapexample.model.Loaction_model;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Apiinterface {

    @GET("api/json/get/coTfiCEcgO?")
    Call<Loaction_model> doGetListResources(@Query("indent") int str);

    //GET
    //http://www.json-generator.com/api/json/get/coTfiCEcgO
}
