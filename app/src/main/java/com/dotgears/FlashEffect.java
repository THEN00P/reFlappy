package com.dotgears;

/* loaded from: classes.dex */
// public class p extends m {
public class FlashEffect extends GameObject {
    // int a;
    int intensity;
    // float b;
    float duration;
    // float c;
    float alpha;
    // float d;
    float timer;
    // boolean e;
    boolean isFlashing;

    // public p() {
    public FlashEffect() {
        // this.a = 1;
        this.intensity = 1;
        // this.b = 0.0f;
        this.duration = 0.0f;
        // this.c = 0.0f;
        this.alpha = 0.0f;
        // this.d = 0.0f;
        this.timer = 0.0f;
        // this.e = false;
        this.isFlashing = false;
    }

    // public void a(float f) {
    public void update(float deltaTime) {
        if (this.isFlashing) {
            // this.d += f;
            this.timer += deltaTime;
            if (this.timer < this.duration * 0.6f) {
                // this.c = (this.d / (this.b * 0.6f)) * 0.6f;
                this.alpha = (this.timer / (this.duration * 0.6f)) * 0.6f;
            } else {
                // this.c = 0.6f - ((((this.d - (this.b * 0.6f)) / (this.b * 0.4f)) * 0.6f));
                this.alpha = 0.6f - ((((this.timer - (this.duration * 0.6f)) / (this.duration * 0.4f)) * 0.6f));
            }
            // if (this.d >= this.b) {
            if (this.timer >= this.duration) {
                // this.c = 0.0f;
                this.alpha = 0.0f;
                // this.e = false;
                this.isFlashing = false;
            }
        }
    }

    // public void a(g gVar) {
    public void draw(GameManager gameManager) {
        // if (this.e) {
        if (this.isFlashing) {
            // gVar.a(gVar.p.i, -144, -256, 864, 1536, this.c);
            gameManager.drawScaledSprite(gameManager.whiteSprite.id, -144, -256, 864, 1536, this.alpha);
        }
    }

    // public void a(int i, float f) {
    public void start(int intensity, float duration) {
        // this.a = i;
        this.intensity = intensity;
        // this.b = f;
        this.duration = duration;
        // this.e = true;
        this.isFlashing = true;
        // this.d = 0.0f;
        this.timer = 0.0f;
    }
}