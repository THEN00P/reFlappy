package com.dotgears;

/* renamed from: com.dotgears.r */
/* loaded from: classes.dex */
public class Transition {

    /* renamed from: b */
    public float currentValue;

    /* renamed from: c */
    public float startValue;

    /* renamed from: d */
    public float endValue;

    /* renamed from: e */
    public float distance;

    /* renamed from: f */
    public int type;

    /* renamed from: h */
    private int duration;

    /* renamed from: i */
    private int currentPercent;

    /* renamed from: j */
    private float durationFactor;

    /* renamed from: g */
    public boolean isActive = true;

    /* renamed from: a */
    public float value = 0.0f;

    /* renamed from: a */
    public void update(float deltaTime) {
        if (this.isActive) {
            return;
        }
        this.currentPercent++;
        this.currentValue = this.currentPercent * this.durationFactor;
        switch (this.type) {
            case com.google.android.gms.e.MapAttrs_cameraBearing /* 1 */:
                this.currentValue = MathHelper.getQuadraticEaseOut((int) (this.currentValue * 100.0f));
                break;
            case com.google.android.gms.e.MapAttrs_cameraTargetLat /* 2 */:
                this.currentValue = MathHelper.getQuadraticEaseOut2((int) (this.currentValue * 100.0f));
                break;
            case com.google.android.gms.e.MapAttrs_cameraTargetLng /* 3 */:
                this.currentValue = MathHelper.getQuadraticEaseInOut((int) (this.currentValue * 100.0f));
                break;
            case com.google.android.gms.e.MapAttrs_cameraTilt /* 4 */:
                this.currentValue = MathHelper.getCubicEaseIn((int) (this.currentValue * 100.0f));
                break;
            case com.google.android.gms.e.MapAttrs_cameraZoom /* 5 */:
                this.currentValue = MathHelper.getCubicEaseOut((int) (this.currentValue * 100.0f));
                break;
            case com.google.android.gms.e.MapAttrs_uiCompass /* 6 */:
                this.currentValue = MathHelper.getCubicEaseInOut((int) (this.currentValue * 100.0f));
                break;
            case com.google.android.gms.e.MapAttrs_uiRotateGestures /* 7 */:
                this.currentValue = MathHelper.getQuartEaseIn((int) (this.currentValue * 100.0f));
                break;
            case com.google.android.gms.e.MapAttrs_uiScrollGestures /* 8 */:
                this.currentValue = MathHelper.getQuartEaseOut((int) (this.currentValue * 100.0f));
                break;
            case com.google.android.gms.e.MapAttrs_uiTiltGestures /* 9 */:
                this.currentValue = MathHelper.getQuartEaseInOut((int) (this.currentValue * 100.0f));
                break;
            case com.google.android.gms.e.MapAttrs_uiZoomControls /* 10 */:
                this.currentValue = MathHelper.getQuintEaseIn((int) (this.currentValue * 100.0f));
                break;
            case com.google.android.gms.e.MapAttrs_uiZoomGestures /* 11 */:
                this.currentValue = MathHelper.getQuintEaseOut((int) (this.currentValue * 100.0f));
                break;
            case com.google.android.gms.e.MapAttrs_useViewLifecycle /* 12 */:
                this.currentValue = MathHelper.getQuintEaseInOut((int) (this.currentValue * 100.0f));
                break;
            case com.google.android.gms.e.MapAttrs_zOrderOnTop /* 13 */:
                this.currentValue = MathHelper.getSineEaseIn((int) (this.currentValue * 100.0f));
                break;
            case 14:
                this.currentValue = MathHelper.getSineEaseOut((int) (this.currentValue * 100.0f));
                break;
            case 15:
                this.currentValue = MathHelper.getSineEaseInOut((int) (this.currentValue * 100.0f));
                break;
            case 16:
                this.currentValue = MathHelper.getSineEaseIn2((int) (this.currentValue * 100.0f));
                break;
            case 17:
                this.currentValue = MathHelper.getSineEaseOut2((int) (this.currentValue * 100.0f));
                break;
            case 18:
                this.currentValue = MathHelper.getSineEaseInOut2((int) (this.currentValue * 100.0f));
                break;
            case 19:
                this.currentValue = MathHelper.getElasticEaseIn((int) (this.currentValue * 100.0f));
                break;
            case 20:
                this.currentValue = MathHelper.getElasticEaseOut((int) (this.currentValue * 100.0f));
                break;
            case 21:
                this.currentValue = MathHelper.getElasticEaseInOut((int) (this.currentValue * 100.0f));
                break;
        }
        this.value = (this.currentValue * this.distance) + this.startValue;
        if (this.currentPercent == this.duration) {
            this.isActive = true;
            this.value = this.endValue;
            this.currentValue = 1.0f;
        }
    }

    /* renamed from: a */
    public void start(float pStartValue, float pEndValue, int pType, float pFactor) {
        this.startValue = pStartValue;
        this.endValue = pEndValue;
        this.distance = this.endValue - this.startValue;
        this.duration = (int) (60.0f * pFactor);
        this.durationFactor = 1.0f / this.duration;
        this.currentPercent = 0;
        this.type = pType;
        this.isActive = false;
        this.value = this.startValue;
    }
}