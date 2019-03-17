package com.kani.demo.concurrent;

import com.kani.demo.FakeAPICall;

public class RevenueServiceClient implements Runnable {
    private String vehicleNumber;
    private double dueAmount;

    public RevenueServiceClient(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public double getDueAmount() {
        return dueAmount;
    }

    public void run() {
        System.out.println("Checking revenue licence due for : " + vehicleNumber);
        //mimic 3rd party api call
        FakeAPICall.fakeBlockingApiCall();
        dueAmount = 3000.0;
        System.out.println("Done revenue licence due for : " + vehicleNumber);
    }
}
