package com.kani.demo.services;

import com.kani.demo.FakeAPICall;

public class InsuranceServiceClient implements Runnable{
    private String vehicleNumber;
    private boolean isInsuranceValid;

    public InsuranceServiceClient(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public boolean isInsuranceValid() {
        return isInsuranceValid;
    }

    public void run() {
        System.out.println("Checking insurance status for :" + vehicleNumber);
        //mimic 3rd party api call
        FakeAPICall.fakeBlockingApiCall();
        isInsuranceValid = true;
        System.out.println("Done insurance status for :" + vehicleNumber);
    }
}
