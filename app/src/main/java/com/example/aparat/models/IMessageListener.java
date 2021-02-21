package com.example.aparat.models;

public interface IMessageListener<T> {

    public void onSuccess(T responseMessage);
    public void onFailure(T errorResponseMessage);

}
