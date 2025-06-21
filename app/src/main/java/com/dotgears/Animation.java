package com.dotgears;

/* renamed from: com.dotgears.d */
/* loaded from: classes.dex */
public class Animation {

    /* renamed from: a */
    public boolean isComplete;

    /* renamed from: b */
    public String name;

    /* renamed from: c */
    public int frameCount;

    /* renamed from: d */
    public int[] frameSequence;

    /* renamed from: e */
    public float frameTime;

    /* renamed from: f */
    public boolean isLooping;

    /* renamed from: g */
    public int id;

    /* renamed from: h */
    public int timer;

    /* renamed from: i */
    public int currentFrameIndex;

    /* renamed from: j */
    public int currentFrame;

    public Animation(int id, String name, int[] frames, int frameCount, int fps, boolean looping) {
        this.name = name;
        this.frameCount = frameCount;
        this.frameSequence = new int[this.frameCount];
        System.arraycopy(frames, 0, this.frameSequence, 0, this.frameCount);
        this.frameTime = 1000 / fps;
        this.isLooping = looping;
        this.id = id;
    }

    /* renamed from: a */
    public void reset() {
        this.timer = 0;
        this.currentFrameIndex = 0;
        this.currentFrame = this.frameSequence[0];
    }

    /* renamed from: a */
    public void update(float deltaTime) {
        if (this.isComplete) {
            return;
        }
        this.timer += 15;
        if (this.timer >= this.frameTime) {
            this.timer = 0;
            this.currentFrameIndex++;
            if (this.currentFrameIndex >= this.frameCount) {
                if (this.isLooping) {
                    this.currentFrameIndex = 0;
                } else {
                    this.isComplete = true;
                }
                this.currentFrameIndex = 0;
            }
            this.currentFrame = this.frameSequence[this.currentFrameIndex];
        }
    }

    /* renamed from: b */
    void start() {
        if (!this.isLooping && this.isComplete) {
            reset();
        }
        this.isComplete = false;
    }
}