package com.dotgears;

/* loaded from: classes.dex */
// public class i {
public class Sprite {
    // public String a;
    public String name;
    // public int b;
    public int width;
    // public int c;
    public int height;
    // public float d;
    public float u;
    // public float e;
    public float v;
    // public float f;
    public float u2;
    // public float g;
    public float v2;
    // public int h;
    public int frames;
    // public int i;
    public int id;

    // public i(String str, int i, int i2, float f, float f2, float f3, float f4) {
    public Sprite(String name, int width, int height, float u, float v, float width2, float height2) {
        // this.a = str;
        this.name = name;
        // this.b = i;
        this.width = width;
        // this.c = i2;
        this.height = height;
        // this.d = f;
        this.u = u;
        // this.e = f2;
        this.v = v;
        // this.f = f3;
        this.u2 = width2;
        // this.g = f4;
        this.v2 = height2;
        // this.h = 1;
        this.frames = 1;
        // this.i = str.hashCode();
        this.id = name.hashCode();
    }
}