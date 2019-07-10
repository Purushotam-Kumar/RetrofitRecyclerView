package com.example.windows.retrofitrecyclerview;

import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

public interface GetDataService {

    @GET("cardData")
    Call<List<UserData>> getAllUsers();

}

