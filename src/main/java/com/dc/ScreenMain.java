package com.dc;

import com.dc.utils.AndroidAdb;

public class ScreenMain {

    public static void main(String[] args) {
        AndroidAdb adb = new AndroidAdb();
        if (args.length == 0) {
            adb.getScreenShot();
        } else {
            for (String arg : args) {
                adb.getScreenShot(arg);
            }
        }
    }
}
