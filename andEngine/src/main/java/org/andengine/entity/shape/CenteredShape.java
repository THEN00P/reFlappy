package org.andengine.entity.shape;

import org.andengine.opengl.shader.ShaderProgram;

public abstract class CenteredShape extends Shape implements ICenteredShape {
    protected float a;
    protected float b;

    public CenteredShape(float pX, float pY, ShaderProgram pShaderProgram) {
        super(pX, pY, pShaderProgram);
    }
}
