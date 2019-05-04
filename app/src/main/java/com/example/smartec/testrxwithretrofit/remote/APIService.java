package com.example.smartec.testrxwithretrofit.remote;


import io.reactivex.Observable;

import com.example.smartec.testrxwithretrofit.model.Results;

import retrofit2.http.GET;

public interface APIService {


    @GET("top_rated?api_key=3c3d662f3dfecdd191da7642c1b1e2af")
    Observable<Results> getResultsList();
}
