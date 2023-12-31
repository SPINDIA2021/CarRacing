package com.jupitorapps.cargame;

import android.content.Context;

import androidx.annotation.NonNull;

import com.jupitorapps.cargame.data.DataSource;
import com.jupitorapps.cargame.data.remote.RemoteDataSource;
import com.jupitorapps.cargame.network.BaseResponse;
import com.jupitorapps.cargame.network.IApi;
import com.jupitorapps.cargame.network.NetworkCall;
import com.jupitorapps.cargame.network.ServiceCallBack;

import java.util.ArrayList;

import retrofit2.Response;

public class AppKeyPresenter  implements AppKeyContact.Presenter, ServiceCallBack {

    private final DataSource loginDataSource;
    private final AppKeyContact.View mLoginView;
    private Context context;

    public AppKeyPresenter(@NonNull RemoteDataSource listDataSource, AppKeyContact.View loginFragment) {
        loginDataSource = listDataSource;
        mLoginView = loginFragment;
        mLoginView.setPresenter(this);

    }

    @Override
    public void onSuccess(int tag, Response<BaseResponse> baseResponse) {
        if (tag == IApi.COMMON_TAG) {
            BaseResponse response = baseResponse.body();
            if (response != null) {
                if (response.isResponseStatus()==true) {


                    ArrayList<AdKeysResponse> userData = (ArrayList<AdKeysResponse>) response.getResponsePacket();

                    mLoginView.adKeysResponse(userData);


                } else {
                    Util.showAlertBox(context, response.getResponseMessage(), null);
                }

            }
        }

    }

    @Override
    public void getAdKeys(Context context) {
        NetworkCall networkCall = new NetworkCall(context);
        this.context = context;
        loginDataSource.getAdKeys(  this, networkCall);
    }



    @Override
    public void onFail(int requestTag, Throwable t) {

    }
}
