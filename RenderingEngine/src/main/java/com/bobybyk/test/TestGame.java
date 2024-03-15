package com.bobybyk.test;

import com.bobybyk.core.*;
import com.bobybyk.core.entity.Entity;
import com.bobybyk.core.entity.Model;
import com.bobybyk.core.entity.Texture;
import com.bobybyk.core.utils.Consts;
import org.joml.Vector2f;
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

        Model model = loader.loadOBJModel("/models/bottle.obj");
        model.setTexture(new Texture(loader.loadTexture("assets/green.png")), 1f);
        entity = new Entity(model, new Vector3f(0, 0, -5), new Vector3f(0, 0, 0), 1);
    }

    /**
     *
     */
    @Override
    public void input() {
        cameraIncrement.set(0, 0, 0);

        if(window.isKeyPressed(GLFW.GLFW_KEY_W))
            cameraIncrement.z = -1;
        if(window.isKeyPressed(GLFW.GLFW_KEY_S))
            cameraIncrement.z = 1;

        if(window.isKeyPressed(GLFW.GLFW_KEY_A))
            cameraIncrement.x = -1;
        if(window.isKeyPressed(GLFW.GLFW_KEY_D))
            cameraIncrement.x = 1;

        if(window.isKeyPressed(GLFW.GLFW_KEY_Z))
            cameraIncrement.y = -1;
        if(window.isKeyPressed(GLFW.GLFW_KEY_X))
            cameraIncrement.y = 1;
    }

    /**
     *
     */
    @Override
    public void update(float interval, MouseInput mouseInput) {
        camera.movePosition(cameraIncrement.x * Consts.CAMERA_STEP, cameraIncrement.y * Consts.CAMERA_STEP, cameraIncrement.z * Consts.CAMERA_STEP);
        if (mouseInput.isRightButtonPressed()) {
            Vector2f rotateVector = mouseInput.getDisplayVector();
            camera.moveRotation(rotateVector.x * Consts.MOUSE_SENITIVITY, rotateVector.y * Consts.MOUSE_SENITIVITY, 0);
        }
        entity.incrementRotation(0.0f, 0.25f, 0.0f);
    }

    /**
     *
     */
    @Override
    public void render() {

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
