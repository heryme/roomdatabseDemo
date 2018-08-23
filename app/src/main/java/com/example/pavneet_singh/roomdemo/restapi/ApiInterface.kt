package com.artproficiencyapp.restapi

import com.example.pavneet_singh.roomdemo.model.Response
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*


interface ApiInterface {

   // @FormUrlEncoded
    @GET("59c60b78400000a107b25e09")
    fun getData(): Call<Response>


    /*

    @FormUrlEncoded
    @Headers("accept:application/json")
    @POST("verify-otp")
    fun verifyOTP(@Header("authorization") inToken: String,@Field("student_id") student_id: String,@Field("otp_key") otp_key:String): Call<ResponseBody>

    //baki
    @FormUrlEncoded
    @Headers("accept:application/json")
    @POST("resend-otp")
    fun resendOTP(@Header("authorization") inToken: String,@Field("student_id") student_id: String): Call<ResponseBody>



    @GET("get-report-agency-list")
    @Headers("accept:application/json")
    fun reportAgency(@Query("token") token: String): Call<ReportModel>

    @FormUrlEncoded
    @Headers("accept:application/json")
    @POST("get-level-and-membership")
    fun levelMember(@Field("login") login: String): Call<LevelModel>

    @GET("category")
    fun getCountryss(): Call<CategoryModel>

    @GET("question/{id}")//team-mylist
    fun getQuestion(@Path("id") inId: String): Call<Question>

*/
}