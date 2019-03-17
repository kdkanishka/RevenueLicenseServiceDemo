package com.kani.demo;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.net.URL;

public class FakeAPICall {
    private FakeAPICall(){

    }

    public static void fakeBlockingApiCall(){
        try {
            ImageIO.read(new URL("https://www.google.lk/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
