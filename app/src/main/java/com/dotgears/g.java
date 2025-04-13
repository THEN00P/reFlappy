package com.dotgears;

import android.util.Log;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/* loaded from: classes.dex */
// public class g extends m {
public class GameManager extends GameObject {
    // public static g D;
    public static GameManager instance;
    // public int A;
    public int currentScore;
    // public l B;
    public ScoreRenderer scoreRenderer;
    // public p C;
    public FlashEffect flashEffect;
    // public int e;
    public int callbackIndex;
    // public r f;
    public Transition fadeTrans;
    // public int g;
    public int fadeCallbackId;
    // public r h;
    public Transition whiteFadeTrans;
    // public int i;
    public int shakeX;
    // public int j;
    public int shakeY;
    // public int k;
    public int shakeIntensity;
    // public int l;
    public int shakeDuration;
    // public n m;
    public ObjectPool pipesPool;
    // public n n;
    public ObjectPool emittersPool;
    // protected i o;
    protected Sprite blackSprite;
    // protected i p;
    protected Sprite whiteSprite;
    // public boolean r;
    public boolean isPaused;
    // protected int u;
    protected int maxTouches;
    // int v;
    int numCallbacks;
    // public int y;
    public int gameState;
    // public int z;
    public int bestScore;
    // public int[] b = new int[50];
    public int[] callbackDelays = new int[50];
    // public int[] c = new int[50];
    public int[] callbackIds = new int[50];
    // public m[] d = new m[50];
    public GameObject[] callbackObjects = new GameObject[50];
    // public int q = 1;
    public int gameSpeed = 1;
    // protected int[] s = new int[10];
    protected int[] touchX = new int[10];
    // protected int[] t = new int[10];
    protected int[] touchY = new int[10];
    // int[] w = new int[50];
    int[] callbackTypes = new int[50];
    // double[] x = new double[50];
    double[] callbackParams = new double[50];
    // public final float E = 0.015f;
    public final float DELTA_TIME = 0.015f;
    // public i[] a = new i[512];
    public Sprite[] sprites = new Sprite[512];

    // public g(int i, int i2, InputStream inputStream) {
    public GameManager(int highScore, int initialScore, InputStream inputStream) {
        // this.z = i;
        this.bestScore = highScore;
        // this.A = i2;
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
                    // i iVar = new i(split[0], Integer.parseInt(split[1]), Integer.parseInt(split[2]), Float.parseFloat(split[3]), Float.parseFloat(split[4]), Float.parseFloat(split[5]), Float.parseFloat(split[6]));
                    Sprite sprite = new Sprite(split[0], Integer.parseInt(split[1]), Integer.parseInt(split[2]), Float.parseFloat(split[3]), Float.parseFloat(split[4]), Float.parseFloat(split[5]), Float.parseFloat(split[6]));
                    // iVar.f = iVar.d + iVar.f;
                    sprite.u2 = sprite.u + sprite.width;
                    // iVar.g = iVar.e + iVar.g;
                    sprite.v2 = sprite.v + sprite.height;
                    // this.a[iVar.i] = iVar;
                    this.sprites[sprite.id] = sprite;
                    Log.i("FlappyBird", sprite.name + " " + sprite.width + " " + sprite.height + " " + sprite.u + " " + sprite.v + " " + sprite.u2 + " " + sprite.v2);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // public void a() {
    public void initialize() {
        // j.x = new j();
        Random.x = new Random();
        // this.f = new r();
        this.fadeTrans = new Transition();
        // this.h = new r();
        this.whiteFadeTrans = new Transition();
        // this.o = b("black");
        this.blackSprite = findSpriteByName("black");
        // this.p = b("white");
        this.whiteSprite = findSpriteByName("white");
        // this.u = 0;
        this.maxTouches = 0;
        // this.m = new n();
        this.pipesPool = new ObjectPool();
        for (int i = 0; i < 20; i++) {
            // this.m.a(new o());
            this.pipesPool.addObject(new Pipe());
        }
        // this.n = new n();
        this.emittersPool = new ObjectPool();
        for (int i2 = 0; i2 < 10; i2++) {
            // this.n.a(new e());
            this.emittersPool.addObject(new ParticleEmitter());
        }
        // this.B = new l("number_score");
        this.scoreRenderer = new ScoreRenderer("number_score");
        // this.C = new p();
        this.flashEffect = new FlashEffect();
        // this.l = 0;
        this.shakeDuration = 0;
        // this.r = false;
        this.isPaused = false;
        // this.v = 0;
        this.numCallbacks = 0;
        // d();
        resetGame();
        // c();
        startGame();
        // a(false, 0, 0.5f);
        startFade(false, 0, 0.5f);
    }

    // public void a(int i) {
    public void addScore(int points) {
        // c(0, i);
        registerCallback(0, points);
        // if (i > this.z) {
        if (points > this.bestScore) {
            // this.z = i;
            this.bestScore = points;
        }
    }

    // public void a(int i, float f) {
    public void handleCallback(int callbackType, float param) {
    }

    // public void a(int i, int i2) {
    public void handleTouch(int x, int y) {
    }

    // public void a(int i, int i2, int i3, float f, float f2, float f3) {
    public void drawRotatedSprite(int spriteId, int x, int y, float scaleX, float scaleY, float alpha) {
        // i iVar = this.a[i];
        Sprite sprite = this.sprites[spriteId];
        // a(i2, i3, (int) (i2 + (iVar.b * f)), (int) (i3 + (iVar.c * f2)), iVar.d, iVar.e, iVar.f, iVar.g, f3);
        drawSprite(x, y, (int) (x + (sprite.width * scaleX)), (int) (y + (sprite.height * scaleY)), sprite.u, sprite.v, sprite.u2, sprite.v2, alpha);
    }

    // public void a(int i, int i2, int i3, int i4) {
    public void handleTouchAt(int x1, int y1, int x2, int y2) {
    }

    // public void a(int i, int i2, int i3, int i4, float f, float f2, float f3, float f4, float f5) {
    public void drawSprite(int x, int y, int width, int height, float u, float v, float u2, float v2, float alpha) {
        // c.a(i, i2, i3, i4, f, f2, f3, f4, f5);
        GameScene.drawSprite(x, y, width, height, u, v, u2, v2, alpha);
    }

    // public void a(int i, int i2, int i3, int i4, float f, float f2, float f3, float f4, float f5, int i5) {
    public void drawRotatedSprite(int x, int y, int width, int height, float u, float v, float u2, float v2, float alpha, int angle) {
        // c.a(i, i2, i3, i4, f, f2, f3, f4, f5, i5);
        GameScene.drawRotatedSprite(x, y, width, height, u, v, u2, v2, alpha, angle);
    }

    // public void a(int i, int i2, int i3, int i4, int i5, float f) {
    public void drawScaledSprite(int spriteId, int x, int y, int width, int height, float alpha) {
        // i iVar = this.a[i];
        Sprite sprite = this.sprites[spriteId];
        // a(i2, i3, i2 + i4, i3 + i5, iVar.d, iVar.e, iVar.f, iVar.g, f);
        drawSprite(x, y, x + width, y + height, sprite.u, sprite.v, sprite.u2, sprite.v2, alpha);
    }

    // public void a(int i, m mVar) {
    public void handleCallbackCompletion(int callbackId, GameObject object) {
    }

    // public void a(int i, m mVar, int i2) {
    public void scheduleCallback(int callbackId, GameObject object, int delayFrames) {
        // this.c[this.e] = i;
        this.callbackIds[this.callbackIndex] = callbackId;
        // this.d[this.e] = mVar;
        this.callbackObjects[this.callbackIndex] = object;
        // this.b[this.e] = i2;
        this.callbackDelays[this.callbackIndex] = delayFrames;
        // this.e++;
        this.callbackIndex++;
        // if (this.e >= 50) {
        if (this.callbackIndex >= 50) {
            // this.e = 0;
            this.callbackIndex = 0;
        }
    }

    // public void a(i iVar, int i, int i2, float f) {
    public void drawSprite(Sprite sprite, int x, int y, float alpha) {
        // a(i, i2, i + iVar.b, i2 + iVar.c, iVar.d, iVar.e, iVar.f, iVar.g, f);
        drawSprite(x, y, x + sprite.width, y + sprite.height, sprite.u, sprite.v, sprite.u2, sprite.v2, alpha);
    }

    // public void a(i iVar, int i, int i2, float f, int i3) {
    public void drawRotatedSprite(Sprite sprite, int x, int y, float alpha, int angle) {
        // a(i, i2, i + iVar.b, i2 + iVar.c, iVar.d, iVar.e, iVar.f, iVar.g, f, i3);
        drawRotatedSprite(x, y, x + sprite.width, y + sprite.height, sprite.u, sprite.v, sprite.u2, sprite.v2, alpha, angle);
    }

    // public void a(boolean z, int i, float f) {
    public void startFade(boolean fadeIn, int callbackId, float speed) {
        // if (this.f.g) {
        if (this.fadeTrans.isActive) {
            if (fadeIn) {
                // this.f.a(0.0f, 1.0f, 5, f);
                this.fadeTrans.start(0.0f, 1.0f, 5, speed);
            } else {
                // this.f.a(1.0f, 0.0f, 5, f);
                this.fadeTrans.start(1.0f, 0.0f, 5, speed);
            }
            // this.f.a(0.0f);
            this.fadeTrans.update(0.0f);
            // this.g = i;
            this.fadeCallbackId = callbackId;
        }
    }

    // public void a(float[] fArr, float[] fArr2) {
    public void processTouchInput(float[] touchXArr, float[] touchYArr) {
        for (int i = 0; i < 10; i++) {
            // this.s[i] = (int) fArr[i];
            this.touchX[i] = (int) touchXArr[i];
            // this.t[i] = (int) fArr2[i];
            this.touchY[i] = (int) touchYArr[i];
        }
        // this.u = 10;
        this.maxTouches = 10;
    }

    // public i[] a(String str) {
    public Sprite[] getSpritesByPrefix(String prefix) {
        int i = 0;
        for (int i2 = 0; i2 < 512; i2++) {
            // if (this.a[i2] != null && this.a[i2].a.startsWith(str)) {
            if (this.sprites[i2] != null && this.sprites[i2].name.startsWith(prefix)) {
                i++;
            }
        }
        if (i <= 0) {
            return null;
        }
        // i[] iVarArr = new i[i];
        Sprite[] spriteArray = new Sprite[i];
        int i3 = 0;
        for (int i4 = 0; i4 < 512; i4++) {
            // if (this.a[i4] != null && this.a[i4].a.startsWith(str)) {
            if (this.sprites[i4] != null && this.sprites[i4].name.startsWith(prefix)) {
                // iVarArr[i3] = this.a[i4];
                spriteArray[i3] = this.sprites[i4];
                i3++;
            }
        }
        return spriteArray;
    }

    // public i b(String str) {
    public Sprite findSpriteByName(String name) {
        for (int i = 0; i < 512; i++) {
            // if (this.a[i] != null && this.a[i].a.startsWith(str)) {
            if (this.sprites[i] != null && this.sprites[i].name.startsWith(name)) {
                // return this.a[i];
                return this.sprites[i];
            }
        }
        return null;
    }

    // public void b() {
    public void update() {
        // this.v = 0;
        this.numCallbacks = 0;
        // if (!this.r) {
        if (!this.isPaused) {
            for (int i = 0; i < 50; i++) {
                // if (this.b[i] > 0) {
                if (this.callbackDelays[i] > 0) {
                    // this.b[i] = r4[i] - 30;
                    this.callbackDelays[i] = this.callbackDelays[i] - 30;
                    // if (this.b[i] <= 0) {
                    if (this.callbackDelays[i] <= 0) {
                        // a(this.c[i], this.d[i]);
                        handleCallbackCompletion(this.callbackIds[i], this.callbackObjects[i]);
                    }
                }
            }
        }
        // b(0.015f);
        updateState(0.015f);
        // if (!this.r) {
        if (!this.isPaused) {
            // this.C.a(0.015f);
            this.flashEffect.update(0.015f);
            // this.n.a(0.015f);
            this.emittersPool.update(0.015f);
            // if (!this.f.g || this.f.a != 0.0f) {
            if (!this.fadeTrans.isActive || this.fadeTrans.value != 0.0f) {
                // this.f.a(0.015f);
                this.fadeTrans.update(0.015f);
                // if (this.f.g) {
                if (this.fadeTrans.isActive) {
                    // a(this.g, this);
                    handleCallbackCompletion(this.fadeCallbackId, this);
                }
            }
            // if (!this.h.g || this.h.a != 0.0f) {
            if (!this.whiteFadeTrans.isActive || this.whiteFadeTrans.value != 0.0f) {
                // this.h.a(0.015f);
                this.whiteFadeTrans.update(0.015f);
            }
            // if (this.l > 0) {
            if (this.shakeDuration > 0) {
                // this.l--;
                this.shakeDuration--;
                // this.i = j.a(-this.k, this.k);
                this.shakeX = Random.getInt(-this.shakeIntensity, this.shakeIntensity);
                // this.j = j.a(-this.k, this.k);
                this.shakeY = Random.getInt(-this.shakeIntensity, this.shakeIntensity);
            } else {
                // this.i = 0;
                this.shakeX = 0;
                // this.j = 0;
                this.shakeY = 0;
            }
        }
        // this.C.a(this);
        this.flashEffect.draw(this);
        // this.n.a(this);
        this.emittersPool.draw(this);
        // if (!this.f.g || this.f.a != 0.0f) {
        if (!this.fadeTrans.isActive || this.fadeTrans.value != 0.0f) {
            // a(this.o.i, -144, -256, 864, 1536, this.f.a);
            drawScaledSprite(this.blackSprite.id, -144, -256, 864, 1536, this.fadeTrans.value);
        }
        // if (this.h.g && this.h.a == 0.0f) {
        if (this.whiteFadeTrans.isActive && this.whiteFadeTrans.value == 0.0f) {
            return;
        }
        // a(this.p.i, -144, -256, 864, 1536, this.h.a);
        drawScaledSprite(this.whiteSprite.id, -144, -256, 864, 1536, this.whiteFadeTrans.value);
    }

    // public void b(float f) {
    public void updateState(float delta) {
    }

    // public void b(int i, int i2) {
    public void createParticleEmitter(int x, int y) {
        // ((e) this.n.c()).a(i, i2);
        ((ParticleEmitter) this.emittersPool.getFreeObject()).initialize(x, y);
    }

    // public void c() {
    public void startGame() {
    }

    // public void c(float f) {
    public void startScreenShake(float intensity) {
        // this.k = (int) (f * 10.0f);
        this.shakeIntensity = (int) (intensity * 10.0f);
        // this.l = (int) (f * 20.0f);
        this.shakeDuration = (int) (intensity * 20.0f);
    }

    // public void c(int i, int i2) {
    public void registerCallback(int callbackType, int param) {
        // this.w[this.v] = i;
        this.callbackTypes[this.numCallbacks] = callbackType;
        // this.x[this.v] = i2;
        this.callbackParams[this.numCallbacks] = param;
        // this.v++;
        this.numCallbacks++;
    }

    // public void d() {
    public void resetGame() {
    }
}