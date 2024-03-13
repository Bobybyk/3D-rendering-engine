package com.bobybyk.test;

import com.bobybyk.core.Logic;
import com.bobybyk.core.ObjectLoader;
import com.bobybyk.core.RenderManager;
import com.bobybyk.core.WindowManager;
import com.bobybyk.core.entity.Model;
import com.bobybyk.core.entity.Texture;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;

public class TestGame implements Logic {
    private int direction = 0;
    private float color = 0.0f;

    private final RenderManager renderer;
    private final ObjectLoader loader;
    private final WindowManager window;

    private Model model;
    public TestGame() {
        renderer = new RenderManager();
        window = Launcher.getWindow();
        loader = new ObjectLoader();
    }

    /**
     * @throws Exception
     */
    @Override
    public void init() throws Exception {
        renderer.init();
        float[] vertices = {
                -0.5f,  0.5f, 0f,
                -0.5f, -0.5f, 0f,
                0.5f, -0.5f, 0f,
                0.5f,  0.5f, 0f,
        };
        int[] indices = {
                0, 1, 3,
                3, 1, 2
        };

        float[] textureCoords = {
                0, 0,
                0, 1,
                1, 1,
                1, 0
        };
        model = loader.loadModel(vertices, textureCoords, indices);
        model.setTexture(new Texture(loader.loadTexture("assets/stone.png")));
    }

    /**
     *
     */
    @Override
    public void input() {
        if(window.isKeyPressed(GLFW.GLFW_KEY_UP))
            direction = 1;
        else if(window.isKeyPressed(GLFW.GLFW_KEY_DOWN))
            direction = -1;
        else
            direction = 0;
    }

    /**
     *
     */
    @Override
    public void update() {
        color += direction * 0.01f;
        if(color > 1)
            color = 1.0f;
        else if (color <= 0)
            color = 0.0f;
    }

    /**
     *
     */
    @Override
    public void render() {
        if(window.isResize()) {
            GL11.glViewport(0, 0, window.getWidth(), window.getHeight());
            window.setResize(true);
        }

        window.setClearColor(color, color, color, 0.0f);
        renderer.render(model);
    }

    /**
     *
     */
    @Override
    public void cleanup() {
        renderer.cleanup();
        loader.cleanup();
    }
}
