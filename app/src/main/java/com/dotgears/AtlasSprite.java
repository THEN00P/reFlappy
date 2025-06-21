package com.dotgears;

/* renamed from: com.dotgears.i */
/* loaded from: classes.dex */
public class AtlasSprite {

    /* renamed from: h */
    public static int count = 0;

    /* renamed from: a */
    public String name;

    /* renamed from: b */
    public int width;

    /* renamed from: c */
    public int height;

    /* renamed from: d */
    public float u;

    /* renamed from: e */
    public float v;

    /* renamed from: f */
    public float u2;

    /* renamed from: g */
    public float v2;

    /* renamed from: i */
    public int id = count;

    public AtlasSprite(String name, int width, int height, float u, float v, float u2, float v2) {
        this.name = name;
        this.width = width;
        this.height = height;
        this.u = u;
        this.v = v;
        this.u2 = u2;
        this.v2 = v2;
        count++;
    }
}