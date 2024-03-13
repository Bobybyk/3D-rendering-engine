package com.bobybyk.core;

public interface Logic {
    public void init() throws  Exception;
    public void input();
    public void update();
    public void render();
    public void cleanup();
}
