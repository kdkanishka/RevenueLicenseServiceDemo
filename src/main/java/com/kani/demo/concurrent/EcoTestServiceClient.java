package com.kani.demo.concurrent;

import com.kani.demo.FakeAPICall;

public class EcoTestServiceClient implements Runnable{
    private String vehicleNumber;
    private boolean isEcoTestPassed;

    public EcoTestServiceClient(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public boolean isEcoTestPassed() {
        return isEcoTestPassed;
    }

    public void run() {
        System.out.println("Checking eco test status for :" + vehicleNumber);
        //mimic 3rd party api call
        FakeAPICall.fakeBlockingApiCall();
        isEcoTestPassed = true;
        System.out.println("Done eco test status for :" + vehicleNumber);
    }
}
