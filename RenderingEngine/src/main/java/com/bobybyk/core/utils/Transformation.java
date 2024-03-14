package com.bobybyk.core.utils;

import com.bobybyk.core.entity.Entity;
import org.joml.Matrix4f;

public class Transformation {

    public static Matrix4f createTransformationMatrix(Entity entity) {
        Matrix4f matrix = new Matrix4f();
        matrix.identity().translate(entity.getPosition())
                .rotateX((float) Math.toRadians(entity.getRotation().x))
                .rotateY((float) Math.toRadians(entity.getRotation().y))
                .rotateZ((float) Math.toRadians(entity.getRotation().z))
                .scale(entity.getScale());
        return matrix;
    }
}
