package com.jupitorapps.cargame.data;


import com.jupitorapps.cargame.network.NetworkCall;
import com.jupitorapps.cargame.network.ServiceCallBack;

public interface DataSource {


    void getAdKeys(ServiceCallBack myAppointmentPresenter, NetworkCall networkCall);


}

