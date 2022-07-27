package com.yumin.airpollution.repository;

import com.yumin.airpollution.data.AirQuality;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface RemoteApiService {
    String BASE_URL =
            "https://data.epa.gov.tw/api/v2/";

    @GET("aqx_p_432?limit=1000&api_key=cebebe84-e17d-4022-a28f-81097fda5896&sort=ImportDate%20desc&format=json")
    Single<AirQuality> getAirQuality();
}
