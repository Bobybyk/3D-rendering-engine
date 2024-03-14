package com.bobybyk.test;

import com.bobybyk.core.*;
import com.bobybyk.core.entity.Entity;
import com.bobybyk.core.entity.Model;
import com.bobybyk.core.entity.Texture;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;

public class TestGame implements Logic {
    private static final float CAMERA_MOVE_SPEED = 0.05f;

    private final RenderManager renderer;
    private final ObjectLoader loader;
    private final WindowManager window;

    private Entity entity;
    private Camera camera;

    private Vector3f cameraIncrement;

    public TestGame() {
        renderer = new RenderManager();
        window = Launcher.getWindow();
        loader = new ObjectLoader();
        camera = new Camera();
        cameraIncrement = new Vector3f(0, 0, 0);
    }

    /**
     * @throws Exception
     */
    @Override
    public void init() throws Exception {
        renderer.init();

        float[] vertices = new float[] {
                -0.5f, 0.5f, 0.5f,
                -0.5f, -0.5f, 0.5f,
                0.5f, -0.5f, 0.5f,
                0.5f, 0.5f, 0.5f,
                -0.5f, 0.5f, -0.5f,
                0.5f, 0.5f, -0.5f,
                -0.5f, -0.5f, -0.5f,
                0.5f, -0.5f, -0.5f,
                -0.5f, 0.5f, -0.5f,
                0.5f, 0.5f, -0.5f,
                -0.5f, 0.5f, 0.5f,
                0.5f, 0.5f, 0.5f,
                0.5f, 0.5f, 0.5f,
                0.5f, -0.5f, 0.5f,
                -0.5f, 0.5f, 0.5f,
                -0.5f, -0.5f, 0.5f,
                -0.5f, -0.5f, -0.5f,
                0.5f, -0.5f, -0.5f,
                -0.5f, -0.5f, 0.5f,
                0.5f, -0.5f, 0.5f,
        };
        float[] textureCoords = new float[]{
                0.0f, 0.0f,
                0.0f, 0.5f,
                0.5f, 0.5f,
                0.5f, 0.0f,
                0.0f, 0.0f,
                0.5f, 0.0f,
                0.0f, 0.5f,
                0.5f, 0.5f,
                0.0f, 0.5f,
                0.5f, 0.5f,
                0.0f, 1.0f,
                0.5f, 1.0f,
                0.0f, 0.0f,
                0.0f, 0.5f,
                0.5f, 0.0f,
                0.5f, 0.5f,
                0.5f, 0.0f,
                1.0f, 0.0f,
                0.5f, 0.5f,
                1.0f, 0.5f,
        };
        int[] indices = new int[]{
                0, 1, 3, 3, 1, 2,
                8, 10, 11, 9, 8, 11,
                12, 13, 7, 5, 12, 7,
                14, 15, 6, 4, 14, 6,
                16, 18, 19, 17, 16, 19,
                4, 6, 7, 5, 4, 7,
        };

        Model model = loader.loadModel(vertices, textureCoords, indices);
        model.setTexture(new Texture(loader.loadTexture("assets/stone.png")));

        entity = new Entity(model, new Vector3f(0, 0, -5), new Vector3f(0, 0, 0), 1);
    }

    /**
     *
     */
    @Override
    public void input() {
        cameraIncrement.set(0, 0, 0);

        if(window.isKeyPressed(GLFW.GLFW_KEY_Z))
            cameraIncrement.z = -1;
        if(window.isKeyPressed(GLFW.GLFW_KEY_S))
            cameraIncrement.z = 1;

        if(window.isKeyPressed(GLFW.GLFW_KEY_A))
            cameraIncrement.y = -1;
        if(window.isKeyPressed(GLFW.GLFW_KEY_D))
            cameraIncrement.y = 1;
    }

    /**
     *
     */
    @Override
    public void update() {
        camera.movePosition(cameraIncrement.x * CAMERA_MOVE_SPEED, cameraIncrement.y * CAMERA_MOVE_SPEED, cameraIncrement.z * CAMERA_MOVE_SPEED);

        entity.incrementRotation(0.0f, 0.05f, 0.0f);
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

        window.setClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        renderer.render(entity, camera);
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
