package com.dotgears;

/* loaded from: classes.dex */
// public class e extends q {
public class ParticleEmitter extends AnimatedSprite {
    public ParticleEmitter() {
        // a("blink", 10, 10, 4, 4);
        loadFrames("blink", 10, 10, 4, 4);
        // a(0, "blink", new int[]{0, 1, 2, 1, 0}, 5, 10, false);
        defineAnimation(0, "blink", new int[]{0, 1, 2, 1, 0}, 5, 10, false);
        // this.m = false;
        this.isActive = false;
        // this.n = false;
        this.isVisible = false;
        // a(0, true);
        playAnimation(0, true);
    }

    @Override // com.dotgears.q, com.dotgears.m
    // public void a(float f) {
    public void a(float deltaTime) {
        // if (this.m) {
        if (this.isActive) {
            // super.a(f);
            super.a(deltaTime);
            // if (this.j == null || !this.j.a) {
            if (this.currentAnimation == null || !this.currentAnimation.isComplete) {
                return;
            }
            // this.m = false;
            this.isActive = false;
            // this.n = false;
            this.isVisible = false;
        }
    }

    @Override // com.dotgears.q
    // public void a(int i, int i2) {
    public void initialize(int x, int y) {
        // super.a(i, i2);
        super.initialize(x, y);
        // a(0, true);
        playAnimation(0, true);
    }

    @Override // com.dotgears.q, com.dotgears.m
    // public void a(g gVar) {
    public void a(GameManager gameManager) {
        // if (this.n) {
        if (this.isVisible) {
            // super.a(gVar);
            super.a(gameManager);
        }
    }
}