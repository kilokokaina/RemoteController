package com.example.remotecontroller;

import java.awt.*;

public class Test {

    public static void main(String[] args) throws AWTException, InterruptedException {
        Robot robot = new Robot();

        while(true) {
            robot.mouseWheel(-1);
            Thread.sleep(1000);
        }
    }

}
