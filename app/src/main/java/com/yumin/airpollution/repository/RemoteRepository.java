package com.yumin.airpollution.repository;

import com.yumin.airpollution.data.AirQuality;

import io.reactivex.Single;

public class RemoteRepository {
    private RemoteApiService remoteApiService;

    public RemoteRepository(){
        remoteApiService = RemoteApiManager.newInstance().creat(RemoteApiService.class);
    }

    public Single<AirQuality> getAirQuality(){
        return remoteApiService.getAirQuality();
    }
}
