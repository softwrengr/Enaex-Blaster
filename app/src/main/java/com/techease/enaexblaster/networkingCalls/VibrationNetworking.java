package com.techease.enaexblaster.networkingCalls;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.techease.remote.ApiClient;
import com.techease.remote.ApiServices;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VibrationNetworking {


    public static void insertVibrationData(final Context context, String name, String unit, String distance, String mic){

//        ApiServices services = ApiClient.getApiClient().create(ApiServices.class);
//        Call<LoginResponseModel> userLogin = services.userLogin(strEmail, strPassword);
//        userLogin.enqueue(new Callback<LoginResponseModel>() {
//            @Override
//            public void onResponse(Call<LoginResponseModel> call, Response<LoginResponseModel> response) {
//                if (response.body() == null) {
//                    try {
//                        JSONObject jObjError = new JSONObject(response.errorBody().string());
//                        Toast.makeText(context, jObjError.getString("message"), Toast.LENGTH_SHORT).show();
//                    } catch (Exception e) {
//                        Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//
//                } else if(response.body().getSuccess())  {
//                    NetworkUtilities.sendMail(getActivity(), "www.enaexusa.com/vibration?data=abdullah");
//                }
//            }
//
//            @Override
//            public void onFailure(Call<LoginResponseModel> call, Throwable t) {
//                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
    }
}
