package com.dotgears;

/* loaded from: classes.dex */
// public class q extends m {
public class AnimatedSprite extends GameObject {
    // public int b;
    public int x;
    // public int c;
    public int y;
    // public int d;
    public int frameIndex;
    // public int e;
    public int width;
    // public int f;
    public int height;
    // public int g;
    public int offsetX;
    // public int h;
    public int offsetY;
    // public boolean k;
    public boolean isFlipped;
    // public boolean l;
    public boolean isRotated;
    // public int o;
    public int frameCount;
    // public i[] p;
    public Sprite[] frames;
    // d[] a = new d[10];
    Animation[] animations = new Animation[10];
    // public float i = 1.0f;
    public float alpha = 1.0f;
    // public d j = null;
    public Animation currentAnimation = null;
    // public boolean m = false;
    public boolean isActive = false;
    // public boolean n = false;
    public boolean isVisible = false;

    @Override // com.dotgears.m
    // public void a(float f) {
    public void a(float deltaTime) {
        // if (this.m && this.j != null) {
        if (this.isActive && this.currentAnimation != null) {
            // this.j.a(f);
            this.currentAnimation.update(deltaTime);
        }
    }

    // public void a(int i, int i2) {
    public void initialize(int x, int y) {
        // this.b = i;
        this.x = x;
        // this.c = i2;
        this.y = y;
        // this.d = 0;
        this.frameIndex = 0;
        // this.m = true;
        this.isActive = true;
        // this.n = true;
        this.isVisible = true;
        // this.k = false;
        this.isFlipped = false;
        // this.l = false;
        this.isRotated = false;
        // this.i = 1.0f;
        this.alpha = 1.0f;
    }

    // public void a(int i, String str, int[] iArr, int i2, int i3, boolean z) {
    public void defineAnimation(int id, String name, int[] frameSequence, int frameCount, int fps, boolean loop) {
        // this.a[i] = new d(i, str, iArr, i2, i3, z);
        this.animations[id] = new Animation(id, name, frameSequence, frameCount, fps, loop);
    }

    // public void a(int i, boolean z) {
    public void playAnimation(int animationId, boolean restart) {
        // if (z) {
        if (restart) {
            // this.a[i].a();
            this.animations[animationId].reset();
        }
        // this.a[i].b();
        this.animations[animationId].start();
        // this.j = this.a[i];
        this.currentAnimation = this.animations[animationId];
    }

    @Override // com.dotgears.m
    // public void a(g gVar) {
    public void draw(GameManager gameManager) {
        // if (this.n) {
        if (this.isVisible) {
            // i iVar = this.p[0];
            Sprite sprite = this.frames[0];
            // if (this.j != null) {
            if (this.currentAnimation != null) {
                // iVar = this.p[this.j.j];
                sprite = this.frames[this.currentAnimation.currentFrame];
            }
            // gVar.a(iVar.i, this.b - this.g, this.c - this.h, 1.0f, 1.0f, this.i);
            gameManager.drawRotatedSprite(sprite.id, this.x - this.offsetX, this.y - this.offsetY, 1.0f, 1.0f, this.alpha);
        }
    }

    // public void a(String str, int i, int i2, int i3, int i4) {
    public void loadFrames(String framePrefix, int width, int height, int offsetX, int offsetY) {
        // this.p = g.D.a(str);
        this.frames = GameManager.instance.getSpritesByPrefix(framePrefix);
        // this.o = this.p.length;
        this.frameCount = this.frames.length;
        // if (i == 0 || i2 == 0) {
        if (width == 0 || height == 0) {
            // this.e = this.p[0].b;
            this.width = this.frames[0].width;
            // this.f = this.p[0].c;
            this.height = this.frames[0].height;
        } else {
            // this.e = i;
            this.width = width;
            // this.f = i2;
            this.height = height;
        }
        // if (i3 == 0 || i4 == 0) {
        if (offsetX == 0 || offsetY == 0) {
            // this.g = (this.p[0].b - this.e) >> 1;
            this.offsetX = (this.frames[0].width - this.width) >> 1;
            // this.h = (this.p[0].c - this.f) >> 1;
            this.offsetY = (this.frames[0].height - this.height) >> 1;
        } else {
            // this.g = i3;
            this.offsetX = offsetX;
            // this.h = i4;
            this.offsetY = offsetY;
        }
    }
}