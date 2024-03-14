package com.bobybyk.core;

import com.bobybyk.test.Launcher;
import org.joml.Vector2d;
import org.joml.Vector2f;
import org.lwjgl.glfw.GLFW;

public class MouseInput {
    private final Vector2d previsousPosition, currentPosition;
    private final Vector2f displayVector;

    private boolean inWindow, leftButtonPress, rightButtonPress;

    public MouseInput() {
        inWindow = false;
        leftButtonPress = false;
        rightButtonPress = false;

        previsousPosition = new Vector2d(-1, -1);
        currentPosition = new Vector2d(0, 0);
        displayVector = new Vector2f();
    }

    public void init() {
        GLFW.glfwSetCursorPosCallback(Launcher.getWindow().getWindow(), (window, xPosition, yPosition) -> {
            currentPosition.x = xPosition;
            currentPosition.y = yPosition;
        });

        GLFW.glfwSetCursorEnterCallback(Launcher.getWindow().getWindow(), (window, entered) -> {
            inWindow = entered;
        });

        GLFW.glfwSetMouseButtonCallback(Launcher.getWindow().getWindow(), (window, button, action, mods) -> {
           leftButtonPress = button == GLFW.GLFW_MOUSE_BUTTON_1 && action == GLFW.GLFW_PRESS;
           rightButtonPress = button == GLFW.GLFW_MOUSE_BUTTON_2 && action == GLFW.GLFW_PRESS;
        });
    }

    public void input() {
        displayVector.x = 0;
        displayVector.y = 0;
        if (previsousPosition.x > 0 && previsousPosition.y > 0 && inWindow) {
            double x = currentPosition.x - previsousPosition.x;
            double y = currentPosition.y - previsousPosition.y;
            boolean rotateX = x != 0;
            boolean rotateY = y != 0;
            if(rotateX)
                displayVector.y = (float) x;
            if(rotateY)
                displayVector.x = (float) y;
        }
        previsousPosition.x = currentPosition.x;
        previsousPosition.y = currentPosition.y;
    }

    public Vector2f getDisplayVector() {
        return displayVector;
    }

    public boolean isLeftButtonPressed() {
        return leftButtonPress;
    }

    public boolean isRightButtonPressed() {
        return rightButtonPress;
    }
}
