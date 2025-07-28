package com.dotgears;

import android.util.Log;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/* renamed from: com.dotgears.g */
/* loaded from: classes.dex */
public class GameManager extends GameObject {

    /* renamed from: D */
    public static GameManager instance;

    /* renamed from: A */
    public int currentScore;

    /* renamed from: B */
    public ScoreRenderer scoreRenderer;

    /* renamed from: C */
    public ScorePanel scorePanel;

    /* renamed from: e */
    public int callbackIndex;

    /* renamed from: f */
    public Transition fadeTrans;

    /* renamed from: g */
    public int fadeCallbackId;

    /* renamed from: h */
    public Transition whiteFadeTrans;

    /* renamed from: i */
    public int shakeX;

    /* renamed from: j */
    public int shakeY;

    /* renamed from: k */
    public int shakeIntensity;

    /* renamed from: l */
    public int shakeDuration;

    /* renamed from: m */
    public ObjectPool pipesPool;

    /* renamed from: n */
    public ObjectPool emitterPool;

    /* renamed from: o */
    protected AtlasSprite blackSprite;

    /* renamed from: p */
    protected AtlasSprite whiteSprite;

    /* renamed from: r */
    public boolean isPaused;

    /* renamed from: u */
    protected int maxTouches;

    /* renamed from: v */
    int numCallbacks;

    /* renamed from: y */
    public int gameState;

    /* renamed from: z */
    public int bestScore;

    /* renamed from: b */
    public int[] callbackDelays = new int[50];

    /* renamed from: c */
    public int[] callbackIds = new int[50];
    public GameObject[] gameObjects = new GameObject[50];

    /* renamed from: q */
    public int gameSpeed = 1;

    protected boolean[] pressedKeys = new boolean[android.view.KeyEvent.getMaxKeyCode()];

    protected int[] touchX = new int[10];

    /* renamed from: t */
    protected int[] touchY = new int[10];

    /* renamed from: w */
    int[] callbackTypes = new int[50];

    /* renamed from: x */
    double[] callbackParams = new double[50];

    /* renamed from: E */
    public final float DELTA_TIME = 0.015f;

    /* renamed from: a */
    public AtlasSprite[] sprites = new AtlasSprite[512];

    public GameManager(int highScore, int initialScore, InputStream inputStream) {
        this.bestScore = highScore;
        this.currentScore = initialScore;
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    return;
                }
                if (readLine.length() > 1) {
                    String[] split = readLine.split("\\s+");
                    AtlasSprite atlasSprite = new AtlasSprite(split[0], Integer.parseInt(split[1]), Integer.parseInt(split[2]), Float.parseFloat(split[3]), Float.parseFloat(split[4]), Float.parseFloat(split[5]), Float.parseFloat(split[6]));
                    atlasSprite.u2 = atlasSprite.u + atlasSprite.u2;
                    atlasSprite.v2 = atlasSprite.v + atlasSprite.v2;
                    this.sprites[atlasSprite.id] = atlasSprite;
                    Log.i("FlappyBird", atlasSprite.name + " " + atlasSprite.width + " " + atlasSprite.height + " " + atlasSprite.u + " " + atlasSprite.v + " " + atlasSprite.u2 + " " + atlasSprite.v2);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public void initialize() {
        MathHelper.instance = new MathHelper();
        this.fadeTrans = new Transition();
        this.whiteFadeTrans = new Transition();
        this.blackSprite = findSpriteByName("black");
        this.whiteSprite = findSpriteByName("white");
        this.maxTouches = 0;
        this.pipesPool = new ObjectPool();
        for (int i = 0; i < 20; i++) {
            this.pipesPool.addObject(new Pipe());
        }
        this.emitterPool = new ObjectPool();
        for (int i2 = 0; i2 < 10; i2++) {
            this.emitterPool.addObject(new ParticleEmitter());
        }
        this.scoreRenderer = new ScoreRenderer("number_score");
        this.scorePanel = new ScorePanel();
        this.shakeDuration = 0;
        this.isPaused = false;
        this.numCallbacks = 0;
        resetGame();
        startGame();
        startFade(false, 0, 0.5f);
    }

    /* renamed from: a */
    public void addScore(int points) {
        registerCallback(0, points);
        if (points > this.bestScore) {
            this.bestScore = points;
        }
    }

    /* renamed from: a */
    public void handleCallback(int callbackType, float param) {
    }

    /* renamed from: a */
    public void handleTouch(int x, int y) {
    }

    /* renamed from: a */
    public void drawRotatedSprite(int spriteId, int x, int y, float scaleX, float scaleY, float alpha) {
        AtlasSprite atlasSprite = this.sprites[spriteId];
        drawSprite(x, y, (int) (x + (atlasSprite.width * scaleX)), (int) (y + (atlasSprite.height * scaleY)), atlasSprite.u, atlasSprite.v, atlasSprite.u2, atlasSprite.v2, alpha);
    }

    /* renamed from: a */
    public void handleTouchAt(int x1, int y1, int x2, int y2) {
    }

    /* renamed from: a */
    public void drawSprite(int x, int y, int width, int height, float u, float v, float u2, float v2, float alpha) {
        GameScene.drawSprite(x, y, width, height, u, v, u2, v2, alpha);
    }

    /* renamed from: a */
    public void drawRotatedSprite(int x, int y, int width, int height, float u, float v, float u2, float v2, float alpha, int angle) {
        GameScene.drawRotatedSprite(x, y, width, height, u, v, u2, v2, alpha, angle);
    }

    /* renamed from: a */
    public void drawScaledSprite(int spriteId, int x, int y, int width, int height, float alpha) {
        AtlasSprite atlasSprite = this.sprites[spriteId];
        drawSprite(x, y, x + width, y + height, atlasSprite.u, atlasSprite.v, atlasSprite.u2, atlasSprite.v2, alpha);
    }

    /* renamed from: a */
    public void handleCallbackCompletion(int i, GameObject gameObject) {
    }

    /* renamed from: a */
    public void scheduleCallback(int callbackId, GameObject gameObject, int delayFrames) {
        this.callbackIds[this.callbackIndex] = callbackId;
        this.gameObjects[this.callbackIndex] = gameObject;
        this.callbackDelays[this.callbackIndex] = delayFrames;
        this.callbackIndex++;
        if (this.callbackIndex >= 50) {
            this.callbackIndex = 0;
        }
    }

    /* renamed from: a */
    public void drawSprite(AtlasSprite atlasSprite, int x, int y, float alpha) {
        drawSprite(x, y, x + atlasSprite.width, y + atlasSprite.height, atlasSprite.u, atlasSprite.v, atlasSprite.u2, atlasSprite.v2, alpha);
    }

    /* renamed from: a */
    public void drawRotatedSprite(AtlasSprite atlasSprite, int x, int y, float alpha, int angle) {
        drawRotatedSprite(x, y, x + atlasSprite.width, y + atlasSprite.height, atlasSprite.u, atlasSprite.v, atlasSprite.u2, atlasSprite.v2, alpha, angle);
    }

    /* renamed from: a */
    public void startFade(boolean fadeIn, int callbackId, float speed) {
        if (this.fadeTrans.isActive) {
            if (fadeIn) {
                this.fadeTrans.start(0.0f, 1.0f, 5, speed);
            } else {
                this.fadeTrans.start(1.0f, 0.0f, 5, speed);
            }
            this.fadeTrans.update(0.0f);
            this.fadeCallbackId = callbackId;
        }
    }

    public void processKeyInput(boolean[] pressedKeysArr) {
        this.pressedKeys = pressedKeysArr;
    }

    /* renamed from: a */
    public void processTouchInput(float[] touchXArr, float[] touchYArr) {
        for (int i = 0; i < 10; i++) {
            this.touchX[i] = (int) touchXArr[i];
            this.touchY[i] = (int) touchYArr[i];
        }
        this.maxTouches = 10;
    }

    /* renamed from: a */
    public AtlasSprite[] getSpritesByPrefix(String prefix) {
        int i = 0;
        for (int i2 = 0; i2 < 512; i2++) {
            if (this.sprites[i2] != null && this.sprites[i2].name.startsWith(prefix)) {
                i++;
            }
        }
        if (i <= 0) {
            return null;
        }
        AtlasSprite[] atlasSpriteArr = new AtlasSprite[i];
        int i3 = 0;
        for (int i4 = 0; i4 < 512; i4++) {
            if (this.sprites[i4] != null && this.sprites[i4].name.startsWith(prefix)) {
                atlasSpriteArr[i3] = this.sprites[i4];
                i3++;
            }
        }
        return atlasSpriteArr;
    }

    /* renamed from: b */
    public AtlasSprite findSpriteByName(String name) {
        for (int i = 0; i < 512; i++) {
            if (this.sprites[i] != null && this.sprites[i].name.startsWith(name)) {
                return this.sprites[i];
            }
        }
        return null;
    }

    /* renamed from: b */
    public void update() {
        this.numCallbacks = 0;
        if (!this.isPaused) {
            for (int i = 0; i < 50; i++) {
                if (this.callbackDelays[i] > 0) {
                    this.callbackDelays[i] = callbackDelays[i] - 30;
                    if (this.callbackDelays[i] <= 0) {
                        handleCallbackCompletion(this.callbackIds[i], this.gameObjects[i]);
                    }
                }
            }
        }
        updateState(0.015f);
        if (!this.isPaused) {
            this.scorePanel.update(0.015f);
            this.emitterPool.update(0.015f);
            if (!this.fadeTrans.isActive || this.fadeTrans.value != 0.0f) {
                this.fadeTrans.update(0.015f);
                if (this.fadeTrans.isActive) {
                    handleCallbackCompletion(this.fadeCallbackId, this);
                }
            }
            if (!this.whiteFadeTrans.isActive || this.whiteFadeTrans.value != 0.0f) {
                this.whiteFadeTrans.update(0.015f);
            }
            if (this.shakeDuration > 0) {
                this.shakeDuration--;
                this.shakeX = MathHelper.randomRange(-this.shakeIntensity, this.shakeIntensity);
                this.shakeY = MathHelper.randomRange(-this.shakeIntensity, this.shakeIntensity);
            } else {
                this.shakeX = 0;
                this.shakeY = 0;
            }
        }
        this.scorePanel.draw(this);
        this.emitterPool.draw(this);
        if (!this.fadeTrans.isActive || this.fadeTrans.value != 0.0f) {
            drawScaledSprite(this.blackSprite.id, -144, -256, 864, 1536, this.fadeTrans.value);
        }
        if (this.whiteFadeTrans.isActive && this.whiteFadeTrans.value == 0.0f) {
            return;
        }
        drawScaledSprite(this.whiteSprite.id, -144, -256, 864, 1536, this.whiteFadeTrans.value);
    }

    /* renamed from: b */
    public void updateState(float deltaTime) {
    }

    /* renamed from: b */
    public void createParticleEmitter(int x, int y) {
        ((ParticleEmitter) this.emitterPool.getCurrentObject()).initialize(x, y);
    }

    /* renamed from: c */
    public void startGame() {
    }

    /* renamed from: c */
    public void startScreenShake(float intensity) {
        if (this.whiteFadeTrans.isActive) {
            this.whiteFadeTrans.start(1.0f, 0.0f, 11, intensity);
            this.whiteFadeTrans.update(0.0f);
        }
    }

    /* renamed from: c */
    public void registerCallback(int callbackType, int param) {
        this.callbackTypes[this.numCallbacks] = callbackType;
        this.callbackParams[this.numCallbacks] = param;
        this.numCallbacks++;
    }

    /* renamed from: d */
    public void resetGame() {
        this.callbackIndex = 0;
        for (int i = 0; i < 50; i++) {
            this.callbackDelays[i] = 0;
        }
    }
}