package com.kani.demo.http;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * Test url : http://localhost:8000/revenuecheck/KM4555
 */

public class ApiRequestListener {
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/revenuecheck", new ApiRequestHandler());
        server.setExecutor(null);
        server.start();
    }
}
