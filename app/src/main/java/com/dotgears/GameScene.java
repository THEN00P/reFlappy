package com.dotgears;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.util.Log;

import com.dotgears.flappybird.R;
import com.google.android.gms.games.PlayGames;

import org.andengine.entity.scene.IOnSceneKeyListener;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.key.KeyEvent;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.TextureRegion;

/* renamed from: com.dotgears.c */
/* loaded from: classes.dex */
public class GameScene extends Scene implements IOnSceneTouchListener, IOnSceneKeyListener {

    /* renamed from: a */
    public static Sprite[] sprites;

    /* renamed from: c */
    static int spriteCount;

    /* renamed from: d */
    public GameActivity gameActivity;

    /* renamed from: e */
    public int highScore;

    /* renamed from: b */
    public final int MAX_SPRITES = 50;

    /* renamed from: f */
    boolean[] pressedKeys = new boolean[android.view.KeyEvent.getMaxKeyCode()];
    int pressedKeyCode = 0;
    boolean isKeyed = false;

    float[] touchX = new float[10];

    /* renamed from: g */
    float[] touchY = new float[10];

    /* renamed from: h */
    boolean isTouched = false;

    /* renamed from: i */
    int touchPointX = 0;

    /* renamed from: j */
    int touchPointY = 0;

    public GameScene(GameActivity gameActivity, TextureRegion textureRegion) {
        this.gameActivity = gameActivity;
        this.highScore = gameActivity.getSharedPreferences("FlappyBird", 0).getInt("score", 0);
        GameManager.instance = new com.dotgears.flappy.GameScene(this.highScore, 0, gameActivity.getApplication().getResources().openRawResource(R.raw.atlas));
        GameManager.instance.initialize();
        MathHelper.setSeed((int) System.currentTimeMillis());
        sprites = new Sprite[50];
        for (int i = 0; i < 50; i++) {
            sprites[i] = new Sprite(0.0f, 0.0f, textureRegion.deepCopy(), gameActivity.getEngine().getVertexBufferObjectManager());
            sprites[i].setVisible(false);
            attachChild(sprites[i]);
        }
        setOnSceneTouchListener((IOnSceneTouchListener) this);
        setOnSceneKeyListener((IOnSceneKeyListener) this);
    }

    /* renamed from: a */
    public static void drawSprite(int x, int y, int width, int height, float u, float v, float u2, float v2, float alpha) {
        Sprite sprite = sprites[spriteCount];

////        This region implements the weird vertex magic using unmodified andengine
////        For accuracy we do it like the original (The rotation is slighly different)
////        <editor-fold desc="Unwrapped setTextureCoordinates">
//        float wNew = width - x;
//        float hNew = height - y;
//
//        sprite.setPosition(x, y);
//
//        sprite.setSize(wNew, hNew);
//
//        sprite.setRotation(0);
//
//        float textureX = u*1024;
//        float textureY = v*1024;
//        float textureWidth = u2*1024 - textureX;
//        float textureHeight = v2*1024 - textureY;
//        sprite.getTextureRegion().set(textureX, textureY, textureWidth, textureHeight);
//
//        sprite.getVertexBufferObject().onUpdateTextureCoordinates(sprite);
////        </editor-fold>

        float textureX = u*1024;
        float textureY = v*1024;
        float textureWidth = u2*1024 - textureX;
        float textureHeight = v2*1024 - textureY;

        sprite.setTextureCoordinates(x, y, width, height, textureX, textureY, textureWidth, textureHeight);
//        sprite.setTextureCoordinates(x, y, width, height, u, v, u2, v2);
        sprite.setAlpha(alpha);
        sprite.setVisible(true);
        spriteCount++;
    }

    /* renamed from: a */
    public static void drawRotatedSprite(int x, int y, int width, int height, float u, float v, float u2, float v2, float alpha, float rotation) {
        Sprite sprite = sprites[spriteCount];
        MathHelper.rotate(x, y, (x + width) * 0.5f, (y + height) * 0.5f, rotation);
        float rotX1 = MathHelper.rotatedX - x;
        float rotY1 = MathHelper.rotatedY - y;
        MathHelper.rotate(x, height, (x + width) * 0.5f, (y + height) * 0.5f, rotation);
        float rotX2 = MathHelper.rotatedX - x;
        float rotY2 = MathHelper.rotatedY - y;
        MathHelper.rotate(width, y, (x + width) * 0.5f, (y + height) * 0.5f, rotation);
        float rotX3 = MathHelper.rotatedX - x;
        float rotY3 = MathHelper.rotatedY - y;
        MathHelper.rotate(width, height, (x + width) * 0.5f, (y + height) * 0.5f, rotation);

////        This region implements the weird vertex magic using unmodified andengine
////        For accuracy we do it like the original (The rotation is slighly different)
////        <editor-fold desc="Unwrapped setTextureCoordinates">
//        float wNew = width - x;
//        float hNew = height - y;
//
//        sprite.setPosition(x, y);
//
//        sprite.setSize(wNew, hNew);
//        sprite.setRotationCenter(wNew / 2, hNew / 2);
//        sprite.setRotation(rotation);
//
//        float textureX = u*1024;
//        float textureY = v*1024;
//        float textureWidth = u2*1024 - textureX;
//        float textureHeight = v2*1024 - textureY;
//        sprite.getTextureRegion().set(textureX, textureY, textureWidth, textureHeight);
//
//        sprite.getVertexBufferObject().onUpdateTextureCoordinates(sprite);
////        </editor-fold>

        float textureX = u*1024;
        float textureY = v*1024;
        float textureWidth = u2*1024 - textureX;
        float textureHeight = v2*1024 - textureY;

        sprite.setTextureCoordinates(x, y, width, height, textureX, textureY, textureWidth, textureHeight, rotX1, rotY1, rotX2, rotY2, rotX3, rotY3, MathHelper.rotatedX - x, MathHelper.rotatedY - y);
//        sprite.setTextureCoordinates(x, y, width, height, u, v, u2, v2, rotX1, rotY1, rotX2, rotY2, rotX3, rotY3, MathHelper.rotatedX - x, MathHelper.rotatedY - y);
        sprite.setAlpha(alpha);
        sprite.setVisible(true);
        spriteCount++;
    }

//    @Override // org.andengine.entity.Entity
    /* renamed from: a */
    public void allocateChildren() {
        for (int i = 0; i < 50; i++) {
            sprites[i].setVisible(false);
        }
        spriteCount = 0;
    }

    @Override // org.andengine.entity.b.Scene, org.andengine.entity.Entity
    /* renamed from: a */
    protected void onManagedUpdate(float deltaTime) {
        super.onManagedUpdate(deltaTime);
        allocateChildren();

        // Mirrors the logic of the original touch input
        // The game is simple enough for it not to be an issue
        GameManager.instance.processKeyInput(this.pressedKeys);
        if(this.isKeyed) {
            GameManager.instance.handleKey(this.pressedKeyCode);
            this.isKeyed = false;
        }

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
                        PlayGames.getLeaderboardsClient(this.gameActivity)
                                .submitScore(this.gameActivity.getString(R.string.leaderboard_id), score);
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
                        PlayGames.getLeaderboardsClient(this.gameActivity)
                                .getLeaderboardIntent(this.gameActivity.getString(R.string.leaderboard_id))
                                .addOnSuccessListener(result -> {
                                    this.gameActivity.startActivityForResult(result, 1);
                                });
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

    @Override
    public boolean onSceneKeyEvent(Scene scene, KeyEvent pSceneKeyEvent) {
        if (pSceneKeyEvent.isActionDown()) {
            if (GameManager.instance != null) {
                this.isKeyed = true;
                this.pressedKeyCode = pSceneKeyEvent.getKeyCode();

                this.pressedKeys[pSceneKeyEvent.getKeyCode()] = true;
            }
        }
        if (pSceneKeyEvent.isActionUp()) {
            this.pressedKeys[pSceneKeyEvent.getKeyCode()] = false;
        }

        return true;
    }

    @Override // org.andengine.entity.b.IOnSceneTouchListener
    /* renamed from: a */
    public boolean onSceneTouchEvent(Scene scene, TouchEvent touchEvent) {
        if (touchEvent.isActionDown()) {
            if (GameManager.instance != null) {
                this.isTouched = true;
                this.touchPointX = (int) touchEvent.getX();
                this.touchPointY = (int) touchEvent.getY();
            }
            this.touchX[touchEvent.getPointerID() % 10] = touchEvent.getX();
            this.touchY[touchEvent.getPointerID() % 10] = touchEvent.getY();
        } else if (touchEvent.isActionUp() || touchEvent.isActionOutside() || touchEvent.isActionCancel()) {
            GameManager gameManager = GameManager.instance;
            this.touchX[touchEvent.getPointerID() % 10] = -100.0f;
            this.touchY[touchEvent.getPointerID() % 10] = -100.0f;
        }
        return true;
    }
}