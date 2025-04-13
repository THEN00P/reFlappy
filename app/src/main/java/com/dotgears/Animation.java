package com.dotgears;

/* loaded from: classes.dex */
// public class d {
public class Animation {
    // public boolean a;
    public boolean isComplete;
    // public String b;
    public String name;
    // public int c;
    public int frameCount;
    // public int[] d;
    public int[] frameSequence;
    // public float e;
    public float frameTime;
    // public boolean f;
    public boolean isLooping;
    // public int g;
    public int id;
    // public int h;
    public int timer;
    // public int i;
    public int currentFrameIndex;
    // public int j;
    public int currentFrame;

    // public d(int i, String str, int[] iArr, int i2, int i3, boolean z) {
    public Animation(int id, String name, int[] frames, int frameCount, int fps, boolean looping) {
        // this.b = str;
        this.name = name;
        // this.c = i2;
        this.frameCount = frameCount;
        // this.d = new int[this.c];
        this.frameSequence = new int[this.frameCount];
        // System.arraycopy(iArr, 0, this.d, 0, this.c);
        System.arraycopy(frames, 0, this.frameSequence, 0, this.frameCount);
        // this.e = 1000 / i3;
        this.frameTime = 1000 / fps;
        // this.f = z;
        this.isLooping = looping;
        // this.g = i;
        this.id = id;
    }

    // public void a() {
    public void reset() {
        // this.h = 0;
        this.timer = 0;
        // this.i = 0;
        this.currentFrameIndex = 0;
        // this.j = this.d[0];
        this.currentFrame = this.frameSequence[0];
    }

    // public void a(float f) {
    public void update(float deltaTime) {
        // if (this.a) {
        if (this.isComplete) {
            return;
        }
        // this.h += 15;
        this.timer += 15;
        // if (this.h >= this.e) {
        if (this.timer >= this.frameTime) {
            // this.h = 0;
            this.timer = 0;
            // this.i++;
            this.currentFrameIndex++;
            // if (this.i >= this.c) {
            if (this.currentFrameIndex >= this.frameCount) {
                // if (this.f) {
                if (this.isLooping) {
                    // this.i = 0;
                    this.currentFrameIndex = 0;
                } else {
                    // this.a = true;
                    this.isComplete = true;
                }
                // this.i = 0;
                this.currentFrameIndex = 0;
            }
            // this.j = this.d[this.i];
            this.currentFrame = this.frameSequence[this.currentFrameIndex];
        }
    }

    // void b() {
    void start() {
        // if (!this.f && this.a) {
        if (!this.isLooping && this.isComplete) {
            // a();
            reset();
        }
        // this.a = false;
        this.isComplete = false;
    }
}