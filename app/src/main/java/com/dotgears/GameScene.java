package com.dotgears;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import com.dotgears.flappybird.R;

/* loaded from: classes.dex */
public class GameScene extends org.andengine.c.b.e implements org.andengine.c.b.c {
    public static org.andengine.c.d.a[] sprites;
    static int spriteCount;
    public GameActivity gameActivity;
    public int highScore;
    public final int MAX_SPRITES = 50;
    float[] touchX = new float[10];
    float[] touchY = new float[10];
    boolean isTouched = false;
    int touchPointX = 0;
    int touchPointY = 0;

    public GameScene(GameActivity gameActivity, org.andengine.opengl.c.c.c textureRegion) {
        this.gameActivity = gameActivity;
        this.highScore = gameActivity.getSharedPreferences("FlappyBird", 0).getInt("score", 0);
        GameManager.instance = new com.dotgears.flappy.GameWorld(this.highScore, 0, gameActivity.getApplication().getResources().openRawResource(R.raw.atlas));
        GameManager.instance.initialize();
        Random.setSeed((int) System.currentTimeMillis());
        sprites = new org.andengine.c.d.a[50];
        for (int i = 0; i < 50; i++) {
            sprites[i] = new org.andengine.c.d.a(0.0f, 0.0f, textureRegion.i(), gameActivity.x().g());
            sprites[i].a(false);
            b(sprites[i]);
        }
        a((org.andengine.c.b.c) this);
    }

    public static void drawSprite(int x, int y, int width, int height, float u, float v, float u2, float v2, float alpha) {
        org.andengine.c.d.a sprite = sprites[spriteCount];
        sprite.b(x, y, width, height, u, v, u2, v2);
        sprite.b(alpha);
        sprite.a(true);
        spriteCount++;
    }

    public static void drawRotatedSprite(int x, int y, int width, int height, float u, float v, float u2, float v2, float alpha, float rotation) {
        org.andengine.c.d.a sprite = sprites[spriteCount];
        Random.a(x, y, (x + width) * 0.5f, (y + height) * 0.5f, rotation);
        float rotX1 = Random.rotatedX - x;
        float rotY1 = Random.rotatedY - y;
        Random.a(x, height, (x + width) * 0.5f, (y + height) * 0.5f, rotation);
        float rotX2 = Random.rotatedX - x;
        float rotY2 = Random.rotatedY - y;
        Random.a(width, y, (x + width) * 0.5f, (y + height) * 0.5f, rotation);
        float rotX3 = Random.rotatedX - x;
        float rotY3 = Random.rotatedY - y;
        Random.a(width, height, (x + width) * 0.5f, (y + height) * 0.5f, rotation);
        sprite.a(x, y, width, height, u, v, u2, v2, rotX1, rotY1, rotX2, rotY2, rotX3, rotY3, Random.rotatedX - x, Random.rotatedY - y);
        sprite.b(alpha);
        sprite.a(true);
        spriteCount++;
    }

    public void clearSprites() {
        for (int i = 0; i < 50; i++) {
            sprites[i].a(false);
        }
        spriteCount = 0;
    }

    @Override // org.andengine.c.b.e, org.andengine.c.a
    protected void a(float deltaTime) {
        super.a(deltaTime);
        clearSprites();
        GameManager.instance.processTouchInput(this.touchX, this.touchY);
        if (this.isTouched) {
            GameManager.instance.handleTouch(this.touchPointX, this.touchPointY);
            GameManager.instance.handleTouchAt(this.touchPointX, this.touchPointY, this.touchPointX, this.touchPointY);
            this.isTouched = false;
        }
        GameManager.instance.update();
        for (int i = 0; i < GameManager.instance.numCallbacks; i++) {
            switch (GameManager.instance.callbackTypes[i]) {
                case com.google.android.gms.e.MapAttrs_mapType /* 0 */:
                    int score = (int) GameManager.instance.callbackParams[i];
                    if (this.gameActivity.isSignedIn()) {
                        this.gameActivity.gameHelper.b().a("CgkI5J2sk6QXEAIQAA", score);
                    }
                    if (score > this.highScore) {
                        SharedPreferences.Editor edit = this.gameActivity.getSharedPreferences("FlappyBird", 0).edit();
                        edit.clear();
                        edit.putInt("score", (int) GameManager.instance.callbackParams[i]);
                        edit.commit();
                        this.highScore = score;
                        break;
                    } else {
                        break;
                    }
                case com.google.android.gms.e.MapAttrs_cameraBearing /* 1 */:
                    if (this.gameActivity.isSignedIn()) {
                        this.gameActivity.startActivityForResult(this.gameActivity.getGamesClient().a("CgkI5J2sk6QXEAIQAA"), 1);
                        break;
                    } else {
                        this.gameActivity.beginUserInitiatedSignIn();
                        break;
                    }
                case com.google.android.gms.e.MapAttrs_cameraTargetLat /* 2 */:
                    Intent intent = new Intent("android.intent.action.VIEW");
                    intent.setData(Uri.parse("http://www.amazon.com/gp/mas/dl/android?p=com.dotgears.flappybird"));
                    this.gameActivity.startActivity(intent);
                    break;
                case com.google.android.gms.e.MapAttrs_uiCompass /* 6 */:
                    this.gameActivity.showAd();
                    break;
                case com.google.android.gms.e.MapAttrs_uiRotateGestures /* 7 */:
                    this.gameActivity.hideAd();
                    break;
                case com.google.android.gms.e.MapAttrs_uiScrollGestures /* 8 */:
                    this.gameActivity.showAd();
                    break;
                case com.google.android.gms.e.MapAttrs_uiTiltGestures /* 9 */:
                    this.gameActivity.playSoundPoint();
                    break;
                case com.google.android.gms.e.MapAttrs_uiZoomControls /* 10 */:
                    this.gameActivity.playSoundSwooshing();
                    break;
                case com.google.android.gms.e.MapAttrs_uiZoomGestures /* 11 */:
                    this.gameActivity.playSoundDie();
                    break;
                case com.google.android.gms.e.MapAttrs_useViewLifecycle /* 12 */:
                    this.gameActivity.playSoundHit();
                    break;
                case com.google.android.gms.e.MapAttrs_zOrderOnTop /* 13 */:
                    this.gameActivity.playSoundWing();
                    break;
            }
        }
    }

    @Override // org.andengine.c.b.c
    public boolean a(org.andengine.c.b.e scene, org.andengine.input.a.a event) {
        if (event.f()) {
            if (GameManager.instance != null) {
                this.isTouched = true;
                this.touchPointX = (int) event.b();
                this.touchPointY = (int) event.c();
            }
            this.touchX[event.d() % 10] = event.b();
            this.touchY[event.d() % 10] = event.c();
        } else if (event.g() || event.j() || event.i()) {
            GameManager gameManager = GameManager.instance;
            this.touchX[event.d() % 10] = -100.0f;
            this.touchY[event.d() % 10] = -100.0f;
        }
        return true;
    }
} 