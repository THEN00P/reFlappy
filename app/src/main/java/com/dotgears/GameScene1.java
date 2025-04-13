package com.dotgears;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import com.dotgears.flappybird.R;

/* loaded from: classes.dex */
// public class c extends org.andengine.c.b.e implements org.andengine.c.b.c {
public class GameScene extends org.andengine.c.b.e implements org.andengine.c.b.c {
    // public static org.andengine.c.d.a[] a;
    public static org.andengine.c.d.a[] sprites;
    // static int c;
    static int spriteCount;
    // public GameActivity d;
    public GameActivity gameActivity;
    // public int e;
    public int highScore;
    // public final int b = 50;
    public final int MAX_SPRITES = 50;
    // float[] f = new float[10];
    float[] touchX = new float[10];
    // float[] g = new float[10];
    float[] touchY = new float[10];
    // boolean h = false;
    boolean isTouched = false;
    // int i = 0;
    int touchPointX = 0;
    // int j = 0;
    int touchPointY = 0;

    // public c(GameActivity gameActivity, org.andengine.opengl.c.c.c cVar) {
    public GameScene(GameActivity gameActivity, org.andengine.opengl.c.c.c textureRegion) {
        // this.d = gameActivity;
        this.gameActivity = gameActivity;
        // this.e = gameActivity.getSharedPreferences("FlappyBird", 0).getInt("score", 0);
        this.highScore = gameActivity.getSharedPreferences("FlappyBird", 0).getInt("score", 0);
        // g.D = new com.dotgears.flappy.c(this.e, 0, gameActivity.getApplication().getResources().openRawResource(R.raw.atlas));
        GameManager.instance = new com.dotgears.flappy.GameWorld(this.highScore, 0, gameActivity.getApplication().getResources().openRawResource(R.raw.atlas));
        // g.D.a();
        GameManager.instance.initialize();
        // j.a((int) System.currentTimeMillis());
        Random.setSeed((int) System.currentTimeMillis());
        // a = new org.andengine.c.d.a[50];
        sprites = new org.andengine.c.d.a[50];
        for (int i = 0; i < 50; i++) {
            // a[i] = new org.andengine.c.d.a(0.0f, 0.0f, cVar.i(), gameActivity.x().g());
            sprites[i] = new org.andengine.c.d.a(0.0f, 0.0f, textureRegion.i(), gameActivity.x().g());
            // a[i].a(false);
            sprites[i].a(false);
            // b(a[i]);
            b(sprites[i]);
        }
        // a((org.andengine.c.b.c) this);
        a((org.andengine.c.b.c) this);
    }

    // public static void a(int i, int i2, int i3, int i4, float f, float f2, float f3, float f4, float f5) {
    public static void drawSprite(int x, int y, int width, int height, float u, float v, float u2, float v2, float alpha) {
        // org.andengine.c.d.a aVar = a[c];
        org.andengine.c.d.a sprite = sprites[spriteCount];
        // aVar.b(i, i2, i3, i4, f, f2, f3, f4);
        sprite.b(x, y, width, height, u, v, u2, v2);
        // aVar.b(f5);
        sprite.b(alpha);
        // aVar.a(true);
        sprite.a(true);
        // c++;
        spriteCount++;
    }

    // public static void a(int i, int i2, int i3, int i4, float f, float f2, float f3, float f4, float f5, float f6) {
    public static void drawRotatedSprite(int x, int y, int width, int height, float u, float v, float u2, float v2, float alpha, float rotation) {
        // org.andengine.c.d.a aVar = a[c];
        org.andengine.c.d.a sprite = sprites[spriteCount];
        // j.a(i, i2, (i + i3) * 0.5f, (i2 + i4) * 0.5f, f6);
        Random.a(x, y, (x + width) * 0.5f, (y + height) * 0.5f, rotation);
        // float f7 = j.A - i;
        float rotX1 = Random.A - x;
        // float f8 = j.B - i2;
        float rotY1 = Random.B - y;
        // j.a(i, i4, (i + i3) * 0.5f, (i2 + i4) * 0.5f, f6);
        Random.a(x, height, (x + width) * 0.5f, (y + height) * 0.5f, rotation);
        // float f9 = j.A - i;
        float rotX2 = Random.A - x;
        // float f10 = j.B - i2;
        float rotY2 = Random.B - y;
        // j.a(i3, i2, (i + i3) * 0.5f, (i2 + i4) * 0.5f, f6);
        Random.a(width, y, (x + width) * 0.5f, (y + height) * 0.5f, rotation);
        // float f11 = j.A - i;
        float rotX3 = Random.A - x;
        // float f12 = j.B - i2;
        float rotY3 = Random.B - y;
        // j.a(i3, i4, (i + i3) * 0.5f, (i2 + i4) * 0.5f, f6);
        Random.a(width, height, (x + width) * 0.5f, (y + height) * 0.5f, rotation);
        // aVar.a(i, i2, i3, i4, f, f2, f3, f4, f7, f8, f9, f10, f11, f12, j.A - i, j.B - i2);
        sprite.a(x, y, width, height, u, v, u2, v2, rotX1, rotY1, rotX2, rotY2, rotX3, rotY3, Random.A - x, Random.B - y);
        // aVar.b(f5);
        sprite.b(alpha);
        // aVar.a(true);
        sprite.a(true);
        // c++;
        spriteCount++;
    }

    // public void a() {
    public void clearSprites() {
        for (int i = 0; i < 50; i++) {
            // a[i].a(false);
            sprites[i].a(false);
        }
        // c = 0;
        spriteCount = 0;
    }

    @Override // org.andengine.c.b.e, org.andengine.c.a
    // protected void a(float f) {
    protected void a(float deltaTime) {
        // super.a(f);
        super.a(deltaTime);
        // a();
        clearSprites();
        // g.D.a(this.f, this.g);
        GameManager.instance.processTouchInput(this.touchX, this.touchY);
        if (this.isTouched) {
            // g.D.a(this.i, this.j);
            GameManager.instance.handleTouch(this.touchPointX, this.touchPointY);
            // g.D.a(this.i, this.j, this.i, this.j);
            GameManager.instance.handleTouchAt(this.touchPointX, this.touchPointY, this.touchPointX, this.touchPointY);
            // this.h = false;
            this.isTouched = false;
        }
        // g.D.b();
        GameManager.instance.update();
        for (int i = 0; i < GameManager.instance.v; i++) {
            // switch (g.D.w[i]) {
            switch (GameManager.instance.w[i]) {
                case com.google.android.gms.e.MapAttrs_mapType /* 0 */:
                    // int i2 = (int) g.D.x[i];
                    int score = (int) GameManager.instance.x[i];
                    // if (this.d.o()) {
                    if (this.gameActivity.o()) {
                        // this.d.g.b().a("CgkI5J2sk6QXEAIQAA", i2);
                        this.gameActivity.g.b().a("CgkI5J2sk6QXEAIQAA", score);
                    }
                    // if (i2 > this.e) {
                    if (score > this.highScore) {
                        // SharedPreferences.Editor edit = this.d.getSharedPreferences("FlappyBird", 0).edit();
                        SharedPreferences.Editor edit = this.gameActivity.getSharedPreferences("FlappyBird", 0).edit();
                        edit.clear();
                        // edit.putInt("score", (int) g.D.x[i]);
                        edit.putInt("score", (int) GameManager.instance.x[i]);
                        edit.commit();
                        // this.e = i2;
                        this.highScore = score;
                        break;
                    } else {
                        break;
                    }
                case com.google.android.gms.e.MapAttrs_cameraBearing /* 1 */:
                    // if (this.d.o()) {
                    if (this.gameActivity.o()) {
                        // this.d.startActivityForResult(this.d.n().a("CgkI5J2sk6QXEAIQAA"), 1);
                        this.gameActivity.startActivityForResult(this.gameActivity.n().a("CgkI5J2sk6QXEAIQAA"), 1);
                        break;
                    } else {
                        // this.d.p();
                        this.gameActivity.p();
                        break;
                    }
                case com.google.android.gms.e.MapAttrs_cameraTargetLat /* 2 */:
                    Intent intent = new Intent("android.intent.action.VIEW");
                    intent.setData(Uri.parse("http://www.amazon.com/gp/mas/dl/android?p=com.dotgears.flappybird"));
                    // this.d.startActivity(intent);
                    this.gameActivity.startActivity(intent);
                    break;
                case com.google.android.gms.e.MapAttrs_uiCompass /* 6 */:
                    // this.d.d();
                    this.gameActivity.d();
                    break;
                case com.google.android.gms.e.MapAttrs_uiRotateGestures /* 7 */:
                    // this.d.c();
                    this.gameActivity.c();
                    break;
                case com.google.android.gms.e.MapAttrs_uiScrollGestures /* 8 */:
                    // this.d.d();
                    this.gameActivity.d();
                    break;
                case com.google.android.gms.e.MapAttrs_uiTiltGestures /* 9 */:
                    // this.d.e();
                    this.gameActivity.e();
                    break;
                case com.google.android.gms.e.MapAttrs_uiZoomControls /* 10 */:
                    // this.d.h();
                    this.gameActivity.h();
                    break;
                case com.google.android.gms.e.MapAttrs_uiZoomGestures /* 11 */:
                    // this.d.f();
                    this.gameActivity.f();
                    break;
                case com.google.android.gms.e.MapAttrs_useViewLifecycle /* 12 */:
                    // this.d.g();
                    this.gameActivity.g();
                    break;
                case com.google.android.gms.e.MapAttrs_zOrderOnTop /* 13 */:
                    // this.d.i();
                    this.gameActivity.i();
                    break;
            }
        }
    }

    @Override // org.andengine.c.b.c
    // public boolean a(org.andengine.c.b.e eVar, org.andengine.input.a.a aVar) {
    public boolean a(org.andengine.c.b.e scene, org.andengine.input.a.a event) {
        // if (aVar.f()) {
        if (event.f()) {
            // if (g.D != null) {
            if (GameManager.instance != null) {
                // this.h = true;
                this.isTouched = true;
                // this.i = (int) aVar.b();
                this.touchPointX = (int) event.b();
                // this.j = (int) aVar.c();
                this.touchPointY = (int) event.c();
            }
            // this.f[aVar.d() % 10] = aVar.b();
            this.touchX[event.d() % 10] = event.b();
            // this.g[aVar.d() % 10] = aVar.c();
            this.touchY[event.d() % 10] = event.c();
        // } else if (aVar.g() || aVar.j() || aVar.i()) {
        } else if (event.g() || event.j() || event.i()) {
            // g gVar = g.D;
            GameManager gameManager = GameManager.instance;
            // this.f[aVar.d() % 10] = -100.0f;
            this.touchX[event.d() % 10] = -100.0f;
            // this.g[aVar.d() % 10] = -100.0f;
            this.touchY[event.d() % 10] = -100.0f;
        }
        return true;
    }
}