package com.entriver.fieldgeofencingandlocationupdates;


import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by TJM Solution on 5/28/2018.
 */

public interface ApiService {
    @POST("remote_service")
    Observable<ResponseBodyModel> postLocationData(@Body RequestBody body);
}
