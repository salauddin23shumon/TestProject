package com.sss.testproject.networking;


import com.sss.testproject.listData.listDataModel.ListResponse;
import com.sss.testproject.userLogin.loginModel.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {



    @FormUrlEncoded
    @POST("api/v1/user")
    Call<LoginResponse> getUser(@Field("email") String email, @Field("password") String password);



    @GET("RecruitmentTest.json")
    Call<ListResponse> getAllData();


}
