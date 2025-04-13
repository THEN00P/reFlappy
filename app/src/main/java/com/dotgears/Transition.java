package com.dotgears;

/* loaded from: classes.dex */
// public class r {
public class Transition {
    // public float a;
    public float value;
    // public float b;
    public float startValue;
    // public float c;
    public float endValue;
    // public int d;
    public int type;
    // public float e;
    public float duration;
    // public float f;
    public float timer;
    // public boolean g;
    public boolean isActive;

    // public r() {
    public Transition() {
        // this.a = 0.0f;
        this.value = 0.0f;
        // this.g = true;
        this.isActive = true;
    }

    // public void a(float f) {
    public void update(float delta) {
        // if (this.d == 0) {
        if (this.type == 0) {
            // this.a = this.b + ((this.c - this.b) * (this.f / this.e));
            this.value = this.startValue + ((this.endValue - this.startValue) * (this.timer / this.duration));
            // this.f += f;
            this.timer += delta;
            // if (this.f >= this.e) {
            if (this.timer >= this.duration) {
                // this.f = this.e;
                this.timer = this.duration;
                // this.a = this.c;
                this.value = this.endValue;
            }
        } else {
            // switch (this.d) {
            switch (this.type) {
                case 1:
                    // this.a = this.b + ((this.c - this.b) * (((float) Math.sin((((double) this.f) * 3.141592653589793d) / ((double) (this.e * 2.0f)))) / 1.0f));
                    this.value = this.startValue + ((this.endValue - this.startValue) * (((float) Math.sin((((double) this.timer) * 3.141592653589793d) / ((double) (this.duration * 2.0f)))) / 1.0f));
                    break;
                case 2:
                    // this.a = this.c - ((this.c - this.b) * ((float) Math.cos((((double) this.f) * 3.141592653589793d) / ((double) (this.e * 2.0f)))));
                    this.value = this.endValue - ((this.endValue - this.startValue) * ((float) Math.cos((((double) this.timer) * 3.141592653589793d) / ((double) (this.duration * 2.0f)))));
                    break;
                case 3:
                    // float f2 = this.f / this.e;
                    float progress = this.timer / this.duration;
                    // this.a = this.b + ((this.c - this.b) * (f2 * f2));
                    this.value = this.startValue + ((this.endValue - this.startValue) * (progress * progress));
                    break;
                case 4:
                    // float f3 = this.f / this.e;
                    float progress2 = this.timer / this.duration;
                    // this.a = this.b + ((this.c - this.b) * (1.0f - ((1.0f - f3) * (1.0f - f3))));
                    this.value = this.startValue + ((this.endValue - this.startValue) * (1.0f - ((1.0f - progress2) * (1.0f - progress2))));
                    break;
                case 5:
                    // float f4 = this.f / (this.e / 2.0f);
                    float progress3 = this.timer / (this.duration / 2.0f);
                    // if (f4 < 1.0f) {
                    if (progress3 < 1.0f) {
                        // this.a = this.b + ((this.c - this.b) * 0.5f * (f4 * f4));
                        this.value = this.startValue + ((this.endValue - this.startValue) * 0.5f * (progress3 * progress3));
                        break;
                    } else {
                        // f4 -= 1.0f;
                        progress3 -= 1.0f;
                        // this.a = this.b + ((this.c - this.b) * (1.0f - (0.5f * ((f4 - 2.0f) * f4))));
                        this.value = this.startValue + ((this.endValue - this.startValue) * (1.0f - (0.5f * ((progress3 - 2.0f) * progress3))));
                        break;
                    }
                case 6:
                    // float f5 = this.f / this.e;
                    float progress4 = this.timer / this.duration;
                    // this.a = this.b + ((this.c - this.b) * (f5 * f5 * f5));
                    this.value = this.startValue + ((this.endValue - this.startValue) * (progress4 * progress4 * progress4));
                    break;
                case 7:
                    // float f6 = this.f / this.e;
                    float progress5 = this.timer / this.duration;
                    // f6 -= 1.0f;
                    progress5 -= 1.0f;
                    // this.a = this.b + ((this.c - this.b) * ((f6 * f6 * f6) + 1.0f));
                    this.value = this.startValue + ((this.endValue - this.startValue) * ((progress5 * progress5 * progress5) + 1.0f));
                    break;
                case 8:
                    // float f7 = this.f / (this.e / 2.0f);
                    float progress6 = this.timer / (this.duration / 2.0f);
                    // if (f7 < 1.0f) {
                    if (progress6 < 1.0f) {
                        // this.a = this.b + ((this.c - this.b) * 0.5f * (f7 * f7 * f7));
                        this.value = this.startValue + ((this.endValue - this.startValue) * 0.5f * (progress6 * progress6 * progress6));
                        break;
                    } else {
                        // f7 -= 2.0f;
                        progress6 -= 2.0f;
                        // this.a = this.b + ((this.c - this.b) * 0.5f * ((f7 * f7 * f7) + 2.0f));
                        this.value = this.startValue + ((this.endValue - this.startValue) * 0.5f * ((progress6 * progress6 * progress6) + 2.0f));
                        break;
                    }
                case 9:
                    // float f8 = this.f / this.e;
                    float progress7 = this.timer / this.duration;
                    // this.a = this.b + ((this.c - this.b) * (1.0f - ((float) Math.sqrt(1.0d - ((double) (f8 * f8))))));
                    this.value = this.startValue + ((this.endValue - this.startValue) * (1.0f - ((float) Math.sqrt(1.0d - ((double) (progress7 * progress7))))));
                    break;
                case 10:
                    // float f9 = this.f / this.e;
                    float progress8 = this.timer / this.duration;
                    // this.a = this.b + ((this.c - this.b) * ((float) Math.sqrt(1.0d - ((double) ((1.0f - f9) * (1.0f - f9))))));
                    this.value = this.startValue + ((this.endValue - this.startValue) * ((float) Math.sqrt(1.0d - ((double) ((1.0f - progress8) * (1.0f - progress8))))));
                    break;
                case 11:
                    // float f10 = this.f / (this.e / 2.0f);
                    float progress9 = this.timer / (this.duration / 2.0f);
                    // if (f10 < 1.0f) {
                    if (progress9 < 1.0f) {
                        // this.a = this.b + ((this.c - this.b) * 0.5f * (1.0f - ((float) Math.sqrt(1.0d - ((double) (f10 * f10))))));
                        this.value = this.startValue + ((this.endValue - this.startValue) * 0.5f * (1.0f - ((float) Math.sqrt(1.0d - ((double) (progress9 * progress9))))));
                        break;
                    } else {
                        // f10 -= 2.0f;
                        progress9 -= 2.0f;
                        // this.a = this.b + ((this.c - this.b) * (0.5f * (((float) Math.sqrt(1.0d - ((double) (f10 * f10)))) + 1.0f)));
                        this.value = this.startValue + ((this.endValue - this.startValue) * (0.5f * (((float) Math.sqrt(1.0d - ((double) (progress9 * progress9)))) + 1.0f)));
                        break;
                    }
            }
            // this.f += f;
            this.timer += delta;
            // if (this.f >= this.e) {
            if (this.timer >= this.duration) {
                // this.f = this.e;
                this.timer = this.duration;
                // this.a = this.c;
                this.value = this.endValue;
            }
        }
    }

    // public void a(float f, float f2, int i, float f3) {
    public void start(float startVal, float endVal, int transitionType, float durationVal) {
        // this.b = f;
        this.startValue = startVal;
        // this.c = f2;
        this.endValue = endVal;
        // this.d = i;
        this.type = transitionType;
        // this.e = f3;
        this.duration = durationVal;
        // this.f = 0.0f;
        this.timer = 0.0f;
    }
}