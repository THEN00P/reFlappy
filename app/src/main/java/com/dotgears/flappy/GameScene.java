package com.dotgears.flappy;

import com.dotgears.g;
import com.dotgears.h;
import com.dotgears.i;
import com.dotgears.j;
import com.dotgears.l;
import com.dotgears.m;
import java.io.InputStream;

/* loaded from: classes.dex */
// public class c extends g {
public class GameScene extends g {
    // boolean H;
    boolean isInMenu;
    // boolean I;
    boolean isGameOver;
    // a J;
    Bird bird;
    // com.dotgears.f K;
    com.dotgears.f playButton;
    // com.dotgears.f L;
    com.dotgears.f okButton;
    // com.dotgears.f M;
    com.dotgears.f pauseButton;
    // com.dotgears.f N;
    com.dotgears.f resumeButton;
    // com.dotgears.f O;
    com.dotgears.f menuButton;
    // com.dotgears.f P;
    com.dotgears.f scoreButton;
    // com.dotgears.f Q;
    com.dotgears.f shareButton;
    // com.dotgears.f R;
    com.dotgears.f rateButton;
    // l S;
    l scoreNumberRenderer;
    // i T;
    i backgroundSprite;
    // i U;
    i bgDaySprite;
    // i V;
    i bgNightSprite;
    // i W;
    i bgForestSprite;
    // i X;
    i landSprite;
    // i Y;
    i pipeUpSprite;
    // i Z;
    i pipeDownSprite;
    // i aa;
    i titleSprite;
    // i ab;
    i copyrightSprite;
    // int ac;
    int landOffset;
    // int ad;
    int pipe1Height;
    // int ae;
    int pipe2Height;
    // int af;
    int pipe3Height;
    // int ag;
    int pipe1X;
    // int ah;
    int pipe2X;
    // int ai;
    int pipe3X;
    // int aj;
    int pipeSpacing;
    // int ak;
    int pipeCountdown;
    // f al;
    ReadyScreen readyScreen;
    // e am;
    GameOverScreen gameOverScreen;
    // h an;
    ScoreFont scoreFont;
    // int ao;
    int gameSpeed;
    // private boolean ap;
    private boolean soundEnabled;
    
    // Static instance for access from other classes
    public static GameScene instance;

    public GameScene(int i, int i2, InputStream inputStream) {
        super(i, i2, inputStream);
        this.soundEnabled = true;
        instance = this;
    }

    public static boolean a(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        return i + i3 >= i5 && i <= i5 + i7 && i2 + i4 >= i6 && i2 <= i6 + i8;
    }

    @Override // com.dotgears.g
    public void a(int i, int i2) {
        if (this.isInMenu) {
            return;
        }
        if (i < this.pauseButton.b - 20 || i > this.pauseButton.b + this.pauseButton.d + 20 || i2 < this.pauseButton.c - 20 || i2 > this.pauseButton.c + this.pauseButton.e + 20) {
            if (!this.bird.isReady) {
                if (this.gameSpeed > 0) {
                    this.bird.b();
                }
            } else if (this.readyScreen.F && this.readyScreen.state == 1) {
                this.readyScreen.state = 2;
                this.readyScreen.transition.a(1.0f, 0.0f, 0, 0.5f);
                this.bird.isReady = false;
                this.bird.b();
            }
        }
    }

    @Override // com.dotgears.g
    public void a(int i, int i2, int i3, int i4) {
        if (!this.isInMenu || i4 < 482 - this.copyrightSprite.c || i4 > 482) {
            return;
        }
        a(7, (m) null, 5);
    }

    @Override // com.dotgears.g
    public void a(int i, m mVar) {
        switch (i) {
            case com.google.android.gms.e.MapAttrs_cameraTargetLat /* 2 */:
                this.isGameOver = true;
                c(0, this.currentScore);
                this.gameOverScreen.a();
                c(8, 0);
                break;
            case com.google.android.gms.e.MapAttrs_cameraTargetLng /* 3 */:
                c(11, 0);
                break;
            case com.google.android.gms.e.MapAttrs_cameraTilt /* 4 */:
                c(13, 0);
                break;
            case com.google.android.gms.e.MapAttrs_cameraZoom /* 5 */:
                e();
                this.playButton.F = false;
                this.scoreButton.F = false;
                this.rateButton.F = false;
                this.isInMenu = false;
                a(false, 0, 0.5f);
                this.landOffset = 0;
                this.bird.a();
                this.gameSpeed = 2;
                this.pipeCountdown = 1;
                this.currentScore = 0;
                this.readyScreen.a();
                this.A++;
                c(7, 0);
                break;
            case com.google.android.gms.e.MapAttrs_uiCompass /* 6 */:
                e();
                a(false, 0, 0.5f);
                c(6, 0);
                break;
            case com.google.android.gms.e.MapAttrs_uiRotateGestures /* 7 */:
                c(3, 0);
                break;
        }
    }

    @Override // com.dotgears.g
    public void b(float f) {
        a(this.backgroundSprite, 0, 0, 1.0f);
        this.landOffset -= this.gameSpeed;
        if (this.landOffset <= -24) {
            this.landOffset = 0;
        }
        if (!this.bird.isReady) {
            this.pipe1X -= this.gameSpeed;
            this.pipe2X -= this.gameSpeed;
            this.pipe3X -= this.gameSpeed;
            if (this.gameSpeed > 0 && this.pipeCountdown <= 0 && (this.pipe1X == this.bird.b || this.pipe1X == this.bird.b - 1)) {
                this.currentScore++;
                c(9, 0);
            }
            if (this.pipe1X < (-this.pipeUpSprite.b)) {
                this.pipe1X = this.pipe2X;
                this.pipe1Height = this.pipe2Height;
                this.pipe2X = this.pipe3X;
                this.pipe2Height = this.pipe3Height;
                this.pipe3X = this.pipe2X + this.pipeSpacing + this.pipeUpSprite.b;
                this.pipe3Height = j.a(180, 360);
                if (this.pipeCountdown > 0) {
                    this.pipeCountdown--;
                    if (this.pipeCountdown == 0) {
                        this.pipe2X = -this.pipeUpSprite.b;
                        this.pipe1X = -this.pipeUpSprite.b;
                    }
                }
            }
        }
        this.bird.a(f);
        if (this.isInMenu) {
            a(this.titleSprite, (288 - this.titleSprite.b) >> 1, 150, 1.0f);
            this.bird.b = (288 - this.bird.e) >> 1;
            this.bird.c = this.titleSprite.c + 150 + 20;
            this.bird.a(this);
            a(this.landSprite, this.landOffset, 512 - this.landSprite.c, 1.0f);
        } else {
            if (this.bird.c >= 400 - this.bird.f && this.gameSpeed > 0) {
                c(1.0f);
                a(4, 0.5f);
                this.gameSpeed = 0;
                c(12, 0);
                a(2, this.C, 1000);
            }
            if (!this.bird.isDead && this.pipeCountdown <= 0 && this.gameSpeed > 0) {
                if (a(this.bird.b, this.bird.c, this.bird.e, this.bird.f, this.pipe1X, (this.pipe1Height - this.pipeDownSprite.c) - 96, this.pipeDownSprite.b, this.pipeDownSprite.c)) {
                    c(1.0f);
                    a(4, 0.5f);
                    this.gameSpeed = 0;
                    c(12, 0);
                    a(3, (m) null, 500);
                    a(2, this.C, 1000);
                } else if (a(this.bird.b, this.bird.c, this.bird.e, this.bird.f, this.pipe1X, this.pipe1Height, this.pipeUpSprite.b, this.pipeUpSprite.c)) {
                    c(1.0f);
                    a(4, 0.5f);
                    this.bird.isDead = true;
                    this.gameSpeed = 0;
                    c(12, 0);
                    a(3, (m) null, 500);
                    a(2, this.C, 1000);
                }
                if (a(this.bird.b, this.bird.c, this.bird.e, this.bird.f, this.pipe2X, (this.pipe2Height - this.pipeDownSprite.c) - 96, this.pipeDownSprite.b, this.pipeDownSprite.c)) {
                    c(1.0f);
                    a(4, 0.5f);
                    this.bird.isDead = true;
                    this.gameSpeed = 0;
                    c(12, 0);
                    a(3, (m) null, 500);
                    a(2, this.C, 1000);
                } else if (a(this.bird.b, this.bird.c, this.bird.e, this.bird.f, this.pipe2X, this.pipe2Height, this.pipeUpSprite.b, this.pipeUpSprite.c)) {
                    c(1.0f);
                    a(4, 0.5f);
                    this.bird.isDead = true;
                    this.gameSpeed = 0;
                    c(12, 0);
                    a(3, (m) null, 500);
                    a(2, this.C, 1000);
                }
            }
            if (this.pipeCountdown <= 0) {
                a(this.pipeUpSprite, this.pipe1X, this.pipe1Height, 1.0f);
                a(this.pipeDownSprite, this.pipe1X, (this.pipe1Height - this.pipeDownSprite.c) - 96, 1.0f);
                a(this.pipeUpSprite, this.pipe2X, this.pipe2Height, 1.0f);
                a(this.pipeDownSprite, this.pipe2X, (this.pipe2Height - this.pipeDownSprite.c) - 96, 1.0f);
                if (this.pipe3X < 288) {
                    a(this.pipeUpSprite, this.pipe3X, this.pipe3Height, 1.0f);
                    a(this.pipeDownSprite, this.pipe3X, (this.pipe3Height - this.pipeDownSprite.c) - 96, 1.0f);
                }
            }
            if (this.C.F && this.C.k == 2 && !this.playButton.F) {
                this.playButton.a((288 - ((this.playButton.d + this.scoreButton.d) + 16)) >> 1, 340);
                this.scoreButton.a(this.playButton.b + this.playButton.d + 16, 340);
            }
            if (this.gameOverScreen.F) {
                this.gameOverScreen.a(f);
                this.gameOverScreen.a(this);
            } else {
                this.scoreFont.a(144, 100, 6, 1.0f);
                this.scoreFont.a(this.currentScore, 20);
                this.scoreFont.a(this);
            }
            this.bird.a(this);
            a(this.landSprite, this.landOffset, 512 - this.landSprite.c, 1.0f);
        }
        if (this.readyScreen.F) {
            this.readyScreen.a(f);
            this.readyScreen.a(this);
        }
        if (this.isInMenu) {
            a(this.copyrightSprite, (288 - this.copyrightSprite.b) >> 1, 432 - this.copyrightSprite.c, 1.0f);
        }
        if (this.playButton.F) {
            this.playButton.a(f);
            this.playButton.a(this);
            this.scoreButton.a(f);
            this.scoreButton.a(this);
            if (this.playButton.h) {
                a(true, 5, 0.5f);
                c(10, 0);
            }
            if (this.scoreButton.h) {
                c(1, 0);
                c(10, 0);
            }
            if (this.rateButton.F) {
                this.rateButton.a(f);
                this.rateButton.a(this);
                if (this.rateButton.h) {
                    c(2, 0);
                }
            }
        }
    }

    @Override // com.dotgears.g
    public void c() {
        this.scoreFont = new ScoreFont();
        this.playButton = new com.dotgears.f();
        this.playButton.a("button_play");
        this.scoreButton = new com.dotgears.f();
        this.scoreButton.a("button_score");
        this.okButton = new com.dotgears.f();
        this.okButton.a("button_ok");
        this.menuButton = new com.dotgears.f();
        this.menuButton.a("button_menu");
        this.pauseButton = new com.dotgears.f();
        this.pauseButton.a("button_pause");
        this.resumeButton = new com.dotgears.f();
        this.resumeButton.a("button_resume");
        this.shareButton = new com.dotgears.f();
        this.shareButton.a("button_share");
        this.rateButton = new com.dotgears.f();
        this.rateButton.a("button_rate");
        this.scoreNumberRenderer = new l("number_score");
        this.bgDaySprite = b("bg_day");
        this.bgNightSprite = b("bg_night");
        this.bgForestSprite = b("bg_forest");
        this.landSprite = b("land");
        this.pipeUpSprite = b("pipe_up");
        this.pipeDownSprite = b("pipe_down");
        this.titleSprite = b("title");
        this.copyrightSprite = b("brand_copyright");
        this.pipeSpacing = (288 - ((this.pipeUpSprite.b * 3) / 2)) / 2;
        this.pipe1X = this.pipeSpacing - (this.pipeUpSprite.b >> 1);
        this.pipe1Height = 274;
        this.pipe2X = this.pipe1X + this.pipeSpacing + this.pipeUpSprite.b;
        this.pipe2Height = 274;
        this.pipe3X = this.pipe2X + this.pipeSpacing + this.pipeUpSprite.b;
        this.pipe3Height = 274;
        this.bird = new Bird();
        this.bird.a();
        this.readyScreen = new ReadyScreen();
        this.gameOverScreen = new GameOverScreen();
        this.isInMenu = true;
        a(6, this, 1);
    }

    public void e() {
        if (j.a() % 10 > 3) {
            this.backgroundSprite = this.bgDaySprite;
        } else {
            this.backgroundSprite = this.bgNightSprite;
        }
        this.emittersPool.b();
        this.playButton.a((288 - ((this.playButton.d + this.scoreButton.d) + 16)) >> 1, 340);
        this.scoreButton.a(this.playButton.b + this.playButton.d + 16, 340);
        this.rateButton.a((288 - this.rateButton.d) >> 1, 270);
        this.readyScreen.F = false;
        this.gameOverScreen.F = false;
        this.C.F = false;
        this.C.G = false;
        this.okButton.F = false;
        this.okButton.G = false;
        this.menuButton.F = false;
        this.menuButton.G = false;
        this.pauseButton.F = false;
        this.pauseButton.G = false;
        this.resumeButton.F = false;
        this.resumeButton.G = false;
        this.shareButton.F = false;
        this.shareButton.G = false;
        this.isInMenu = true;
        this.landOffset = 0;
        this.bird.a();
        this.gameSpeed = 2;
        this.pipeCountdown = 1;
        this.currentScore = 0;
    }
}