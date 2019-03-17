package com.kani.demo.services;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;

public class RevenueLicenceRequestProcessor implements Runnable {
    private HttpExchange exchange;

    public RevenueLicenceRequestProcessor(HttpExchange exchange) {
        this.exchange = exchange;
    }

    public void run() {
        String vehicleNum = exchange.getRequestURI().getPath()
                .replaceAll(exchange.getHttpContext().getPath(), "").replace("/", "");
        System.out.println("Ready to process : " + vehicleNum);

        InsuranceServiceClient insuranceServiceClient = new InsuranceServiceClient(vehicleNum);
        EcoTestServiceClient ecoTestServiceClient = new EcoTestServiceClient(vehicleNum);
        RevenueServiceClient revenueServiceClient = new RevenueServiceClient(vehicleNum);

        Thread insThread = new Thread(insuranceServiceClient);
        Thread ecoThread = new Thread(ecoTestServiceClient);
        Thread revThread = new Thread(revenueServiceClient);

        //run threads
        insThread.start();
        ecoThread.start();
        revThread.start();

        try {
            //wait for response from each
            insThread.join();
            ecoThread.join();
            revThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        boolean isValidInsurance = insuranceServiceClient.isInsuranceValid();
        boolean isEcoTestPassed = ecoTestServiceClient.isEcoTestPassed();
        double dueAmount = revenueServiceClient.getDueAmount();

        try {
            String response = "{validInsurance :" + isValidInsurance + " , validEcoTest:"
                    + isEcoTestPassed + ", due:" + dueAmount + "}";
            exchange.sendResponseHeaders(200, response.length());
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            exchange.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
