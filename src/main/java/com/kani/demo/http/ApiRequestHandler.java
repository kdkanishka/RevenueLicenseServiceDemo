package com.kani.demo.http;

import com.kani.demo.services.RevenueLicenceRequestProcessor;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class ApiRequestHandler implements HttpHandler {
    public void handle(HttpExchange exchange) {
        Thread t1 = new Thread(new RevenueLicenceRequestProcessor(exchange));
        t1.start();
    }
}
