package com.example.remotecontroller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.awt.event.InputEvent;

@Slf4j
@RestController
@RequestMapping("control")
public class MouseController {

    private final Robot cursorRobot;

    @Autowired
    public MouseController(Robot cursorRobot) {
        this.cursorRobot = cursorRobot;
    }

    @GetMapping("move")
    public @ResponseBody void move(@RequestParam(value = "x") int x, @RequestParam(value = "y") int y) {
        int positionX = MouseInfo.getPointerInfo().getLocation().x;
        int positionY = MouseInfo.getPointerInfo().getLocation().y;

        cursorRobot.mouseMove(positionX + x, positionY + y);
    }

    @GetMapping("click")
    public @ResponseBody void click(@RequestParam(value = "button") String buttonType) {
        switch (buttonType) {
            case "MOUSE_LEFT" -> {
                cursorRobot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                cursorRobot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
            }
            case "MOUSE_RIGHT" -> {
                cursorRobot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
                cursorRobot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
            }
        }

        log.info(buttonType);
    }

}
