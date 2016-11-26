package com.automic.nguyendhoang.productdiary.controller.application;

import java.util.HashMap;

/**
 * Created by Nguyen.D.Hoang on 11/24/2016.
 */

public class Application extends android.app.Application {
    public static HashMap<String, Object> container;

    @Override
    public void onCreate() {
        super.onCreate();
        container = new HashMap<>();
    }
}
