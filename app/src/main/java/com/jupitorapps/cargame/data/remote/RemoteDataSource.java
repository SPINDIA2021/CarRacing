package com.jupitorapps.cargame.data.remote;




import com.jupitorapps.cargame.AdKeysResponse;
import com.jupitorapps.cargame.data.DataSource;
import com.jupitorapps.cargame.network.BaseResponse;
import com.jupitorapps.cargame.network.IApi;
import com.jupitorapps.cargame.network.NetworkCall;
import com.jupitorapps.cargame.network.ServiceCallBack;

import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;

public class RemoteDataSource implements DataSource {
    private static RemoteDataSource INSTANCE;

    public static RemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RemoteDataSource();
        }
        return INSTANCE;
    }





    @Override
    public void getAdKeys(ServiceCallBack myAppointmentPresenter, NetworkCall networkCall) {
        try{

            RequestBody api = RequestBody.create(MediaType.parse("text/plain"), "getapikeys");


            Call<BaseResponse<ArrayList<AdKeysResponse>>> responceCall = networkCall.getRetrofit(true, true).getAdKeys(api);
            networkCall.setServiceCallBack(myAppointmentPresenter);
            networkCall.setRequestTag(IApi.COMMON_TAG);
            responceCall.enqueue(networkCall.requestCallback());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}


