package com.dotgears.flappy;

import android.util.Log;
import android.view.KeyEvent;

import com.dotgears.AtlasSprite;
import com.dotgears.Button;
import com.dotgears.FontRenderer;
import com.dotgears.GameManager;
import com.dotgears.GameObject;
import com.dotgears.MathHelper;
import com.dotgears.ScoreRenderer;
import java.io.InputStream;

/* renamed from: com.dotgears.flappy.c */
/* loaded from: classes.dex */
public class GameScene extends GameManager {

    /* renamed from: H */
    boolean isInMenu;

    /* renamed from: I */
    boolean isGameOver;

    /* renamed from: J */
    Bird bird;

    /* renamed from: K */
    Button playButton;

    /* renamed from: L */
    Button okButton;

    /* renamed from: M */
    Button pauseButton;

    /* renamed from: N */
    Button resumeButton;

    /* renamed from: O */
    Button menuButton;

    /* renamed from: P */
    Button scoreButton;

    /* renamed from: Q */
    Button shareButton;

    /* renamed from: R */
    Button rateButton;

    /* renamed from: S */
    ScoreRenderer scoreNumberRenderer;

    /* renamed from: T */
    AtlasSprite backgroundSprite;

    /* renamed from: U */
    AtlasSprite bgDaySprite;

    /* renamed from: V */
    AtlasSprite bgNightSprite;

    /* renamed from: W */
    AtlasSprite bgForestSprite;

    /* renamed from: X */
    AtlasSprite landSprite;

    /* renamed from: Y */
    AtlasSprite pipeUpSprite;

    /* renamed from: Z */
    AtlasSprite pipeDownSprite;

    /* renamed from: aa */
    AtlasSprite titleSprite;

    /* renamed from: ab */
    AtlasSprite copyrightSprite;

    /* renamed from: ac */
    int landOffset;

    /* renamed from: ad */
    int pipe1Height;

    /* renamed from: ae */
    int pipe2Height;

    /* renamed from: af */
    int pipe3Height;

    /* renamed from: ag */
    int pipe1X;

    /* renamed from: ah */
    int pipe2X;

    /* renamed from: ai */
    int pipe3X;

    /* renamed from: aj */
    int pipeSpacing;

    /* renamed from: ak */
    int pipeCountdown;

    /* renamed from: al */
    ReadyScreen readyScreen;

    /* renamed from: am */
    GameOverScreen gameOverScreen;

    /* renamed from: an */
    FontRenderer scoreFont;

    /* renamed from: ao */
    int gameSpeed;

    /* renamed from: ap */
    private boolean initialized;

    public GameScene(int highScore, int currentScore, InputStream inputStream) {
        super(highScore, currentScore, inputStream);
        this.initialized = true;
    }

    /* renamed from: a */
    public static boolean checkCollision(int x1, int y1, int width1, int height1, int x2, int y2, int width2, int height2) {
        return x1 + width1 >= x2 && x1 <= x2 + width2 && y1 + height1 >= y2 && y1 <= y2 + height2;
    }

    @Override // com.dotgears.GameManager
    public void handleKey(int keyCode) {
        if (this.isInMenu) {
            return;
        }

        Log.d("GameScene", "pressedKeyCode: " + keyCode);

        if(keyCode == KeyEvent.KEYCODE_SPACE || keyCode == KeyEvent.KEYCODE_BUTTON_A) {
            tryFlap();
        }
    }

    @Override // com.dotgears.GameManager
    /* renamed from: a */
    public void handleTouch(int x, int y) {
        if (this.isInMenu) {
            return;
        }
        if (x < this.pauseButton.posX - 20 || x > this.pauseButton.posX + this.pauseButton.width + 20 || y < this.pauseButton.posY - 20 || y > this.pauseButton.posY + this.pauseButton.height + 20) {
            tryFlap();
        }
    }

    private void tryFlap() {
        if (!this.bird.isReady) {
            if (this.gameSpeed > 0) {
                this.bird.flap();
            }
        } else if (this.readyScreen.isActive && this.readyScreen.state == 1) {
            this.readyScreen.state = 2;
            this.readyScreen.transition.start(1.0f, 0.0f, 0, 0.5f);
            this.bird.isReady = false;
            this.bird.flap();
        }
    }

    @Override // com.dotgears.GameManager
    /* renamed from: a */
    public void handleTouchAt(int x1, int y1, int x2, int y2) {
        if (!this.isInMenu || y2 < 482 - this.copyrightSprite.height || y2 > 482) {
            return;
        }
        scheduleCallback(7, (GameObject) null, 5);
    }

    @Override // com.dotgears.GameManager
    /* renamed from: a */
    public void handleCallbackCompletion(int callbackId, GameObject gameObject) {
        switch (callbackId) {
            case com.google.android.gms.e.MapAttrs_cameraTargetLat /* 2 */:
                this.isGameOver = true;
                registerCallback(0, this.gameState);
                this.gameOverScreen.run();
                break;
            case com.google.android.gms.e.MapAttrs_cameraTargetLng /* 3 */:
                registerCallback(11, 0);
                break;
            case com.google.android.gms.e.MapAttrs_cameraTilt /* 4 */:
                registerCallback(13, 0);
                break;
            case com.google.android.gms.e.MapAttrs_cameraZoom /* 5 */:
                resetGameState();
                this.playButton.isActive = false;
                this.scoreButton.isActive = false;
                this.rateButton.isActive = false;
                this.isInMenu = false;
                startFade(false, 0, 0.5f);
                this.landOffset = 0;
                this.bird.init();
                this.gameSpeed = 2;
                this.pipeCountdown = 1;
                this.gameState = 0;
                this.readyScreen.run();
                this.currentScore++;
                break;
            case com.google.android.gms.e.MapAttrs_uiCompass /* 6 */:
                resetGameState();
                startFade(false, 0, 0.5f);
                break;
            case com.google.android.gms.e.MapAttrs_uiRotateGestures /* 7 */:
                registerCallback(3, 0);
                break;
        }
    }

    @Override // com.dotgears.GameManager
    /* renamed from: b */
    public void updateState(float deltaTime) {
        drawSprite(this.backgroundSprite, 0, 0, 1.0f);
        this.landOffset -= this.gameSpeed;
        if (this.landOffset <= -24) {
            this.landOffset = 0;
        }
        if (!this.bird.isReady) {
            this.pipe1X -= this.gameSpeed;
            this.pipe2X -= this.gameSpeed;
            this.pipe3X -= this.gameSpeed;
            if (this.gameSpeed > 0 && this.pipeCountdown <= 0 && (this.pipe1X == this.bird.x || this.pipe1X == this.bird.x - 1)) {
                this.gameState++;
                registerCallback(9, 0);
            }
            if (this.pipe1X < (-this.pipeUpSprite.width)) {
                this.pipe1X = this.pipe2X;
                this.pipe1Height = this.pipe2Height;
                this.pipe2X = this.pipe3X;
                this.pipe2Height = this.pipe3Height;
                this.pipe3X = this.pipe2X + this.pipeSpacing + this.pipeUpSprite.width;
                this.pipe3Height = MathHelper.randomRange(180, 360);
                if (this.pipeCountdown > 0) {
                    this.pipeCountdown--;
                    if (this.pipeCountdown == 0) {
                        this.pipe2X = -this.pipeUpSprite.width;
                        this.pipe1X = -this.pipeUpSprite.width;
                    }
                }
            }
        }
        this.bird.update(deltaTime);
        if (this.isInMenu) {
            drawSprite(this.titleSprite, (288 - this.titleSprite.width) >> 1, 150, 1.0f);
            this.bird.x = (288 - this.bird.width) >> 1;
            this.bird.y = this.titleSprite.height + 150 + 20;
            this.bird.draw(this);
            drawSprite(this.landSprite, this.landOffset, 512 - this.landSprite.height, 1.0f);
        } else {
            if (this.bird.y >= 400 - this.bird.height && this.gameSpeed > 0) {
                startScreenShake(1.0f);
                handleCallback(4, 0.5f);
                this.gameSpeed = 0;
                registerCallback(12, 0);
                scheduleCallback(2, this.scorePanel, 1000);
            }
            if (!this.bird.isDead && this.pipeCountdown <= 0 && this.gameSpeed > 0) {
                if (checkCollision(this.bird.x, this.bird.y, this.bird.width, this.bird.height, this.pipe1X, (this.pipe1Height - this.pipeDownSprite.height) - 96, this.pipeDownSprite.width, this.pipeDownSprite.height)) {
                    startScreenShake(1.0f);
                    handleCallback(4, 0.5f);
                    this.gameSpeed = 0;
                    registerCallback(12, 0);
                    scheduleCallback(3, (GameObject) null, 500);
                    scheduleCallback(2, this.scorePanel, 1000);
                } else if (checkCollision(this.bird.x, this.bird.y, this.bird.width, this.bird.height, this.pipe1X, this.pipe1Height, this.pipeUpSprite.width, this.pipeUpSprite.height)) {
                    startScreenShake(1.0f);
                    handleCallback(4, 0.5f);
                    this.bird.isDead = true;
                    this.gameSpeed = 0;
                    registerCallback(12, 0);
                    scheduleCallback(3, (GameObject) null, 500);
                    scheduleCallback(2, this.scorePanel, 1000);
                }
                if (checkCollision(this.bird.x, this.bird.y, this.bird.width, this.bird.height, this.pipe2X, (this.pipe2Height - this.pipeDownSprite.height) - 96, this.pipeDownSprite.width, this.pipeDownSprite.height)) {
                    startScreenShake(1.0f);
                    handleCallback(4, 0.5f);
                    this.bird.isDead = true;
                    this.gameSpeed = 0;
                    registerCallback(12, 0);
                    scheduleCallback(3, (GameObject) null, 500);
                    scheduleCallback(2, this.scorePanel, 1000);
                } else if (checkCollision(this.bird.x, this.bird.y, this.bird.width, this.bird.height, this.pipe2X, this.pipe2Height, this.pipeUpSprite.width, this.pipeUpSprite.height)) {
                    startScreenShake(1.0f);
                    handleCallback(4, 0.5f);
                    this.bird.isDead = true;
                    this.gameSpeed = 0;
                    registerCallback(12, 0);
                    scheduleCallback(3, (GameObject) null, 500);
                    scheduleCallback(2, this.scorePanel, 1000);
                }
            }
            if (this.pipeCountdown <= 0) {
                drawSprite(this.pipeUpSprite, this.pipe1X, this.pipe1Height, 1.0f);
                drawSprite(this.pipeDownSprite, this.pipe1X, (this.pipe1Height - this.pipeDownSprite.height) - 96, 1.0f);
                drawSprite(this.pipeUpSprite, this.pipe2X, this.pipe2Height, 1.0f);
                drawSprite(this.pipeDownSprite, this.pipe2X, (this.pipe2Height - this.pipeDownSprite.height) - 96, 1.0f);
                if (this.pipe3X < 288) {
                    drawSprite(this.pipeUpSprite, this.pipe3X, this.pipe3Height, 1.0f);
                    drawSprite(this.pipeDownSprite, this.pipe3X, (this.pipe3Height - this.pipeDownSprite.height) - 96, 1.0f);
                }
            }
            if (this.scorePanel.isActive && this.scorePanel.state == 2 && !this.playButton.isActive) {
                this.playButton.setPosition((288 - ((this.playButton.width + this.scoreButton.width) + 16)) >> 1, 340);
                this.scoreButton.setPosition(this.playButton.posX + this.playButton.width + 16, 340);
            }
            if (this.gameOverScreen.isActive) {
                this.gameOverScreen.update(deltaTime);
                this.gameOverScreen.draw(this);
            } else {
                this.scoreFont.setPosition(144, 100, 6, 1.0f);
                this.scoreFont.setNumber(this.gameState, 20);
                this.scoreFont.render(this);
            }
            this.bird.draw(this);
            drawSprite(this.landSprite, this.landOffset, 512 - this.landSprite.height, 1.0f);
        }
        if (this.readyScreen.isActive) {
            this.readyScreen.update(deltaTime);
            this.readyScreen.draw(this);
        }
        if (this.isInMenu) {
            drawSprite(this.copyrightSprite, (288 - this.copyrightSprite.width) >> 1, 432 - this.copyrightSprite.height, 1.0f);
        }
        if (this.playButton.isActive) {
            this.playButton.update(deltaTime);
            this.playButton.draw(this);
            this.scoreButton.update(deltaTime);
            this.scoreButton.draw(this);
            if (this.playButton.isJustReleased) {
                startFade(true, 5, 0.5f);
                registerCallback(10, 0);
            }
            if (this.scoreButton.isJustReleased) {
                registerCallback(1, 0);
                registerCallback(10, 0);
            }
            if (this.rateButton.isActive) {
                this.rateButton.update(deltaTime);
                this.rateButton.draw(this);
                if (this.rateButton.isJustReleased) {
                    registerCallback(2, 0);
                }
            }
        }
    }

    @Override // com.dotgears.GameManager
    /* renamed from: c */
    public void startGame() {
        this.scoreFont = new ScoreFont();
        this.playButton = new Button();
        this.playButton.setSprite("button_play");
        this.playButton.setHandledKeyCodes(new int[] {KeyEvent.KEYCODE_SPACE, KeyEvent.KEYCODE_BUTTON_A, KeyEvent.KEYCODE_BUTTON_START});
        this.scoreButton = new Button();
        this.scoreButton.setSprite("button_score");
        this.scoreButton.setHandledKeyCodes(new int[] {KeyEvent.KEYCODE_S, KeyEvent.KEYCODE_BUTTON_SELECT});
        this.okButton = new Button();
        this.okButton.setSprite("button_ok");
        this.menuButton = new Button();
        this.menuButton.setSprite("button_menu");
        this.pauseButton = new Button();
        this.pauseButton.setSprite("button_pause");
        this.resumeButton = new Button();
        this.resumeButton.setSprite("button_resume");
        this.shareButton = new Button();
        this.shareButton.setSprite("button_share");
        this.rateButton = new Button();
        this.rateButton.setSprite("button_rate");
        this.scoreNumberRenderer = new ScoreRenderer("number_score");
        this.bgDaySprite = findSpriteByName("bg_day");
        this.bgNightSprite = findSpriteByName("bg_night");
        this.bgForestSprite = findSpriteByName("bg_forest");
        this.landSprite = findSpriteByName("land");
        this.pipeUpSprite = findSpriteByName("pipe_up");
        this.pipeDownSprite = findSpriteByName("pipe_down");
        this.titleSprite = findSpriteByName("title");
        this.copyrightSprite = findSpriteByName("brand_copyright");
        this.pipeSpacing = (288 - ((this.pipeUpSprite.width * 3) / 2)) / 2;
        this.pipe1X = this.pipeSpacing - (this.pipeUpSprite.width >> 1);
        this.pipe1Height = 274;
        this.pipe2X = this.pipe1X + this.pipeSpacing + this.pipeUpSprite.width;
        this.pipe2Height = 274;
        this.pipe3X = this.pipe2X + this.pipeSpacing + this.pipeUpSprite.width;
        this.pipe3Height = 274;
        this.bird = new Bird();
        this.bird.init();
        this.readyScreen = new ReadyScreen();
        this.gameOverScreen = new GameOverScreen();
        this.isInMenu = true;
        scheduleCallback(6, this, 1);
    }

    /* renamed from: e */
    public void resetGameState() {
        if (MathHelper.nextRandom() % 10 > 3) {
            this.backgroundSprite = this.bgDaySprite;
        } else {
            this.backgroundSprite = this.bgNightSprite;
        }
        this.emitterPool.hideAll();
        this.playButton.setPosition((288 - ((this.playButton.width + this.scoreButton.width) + 16)) >> 1, 340);
        this.scoreButton.setPosition(this.playButton.posX + this.playButton.width + 16, 340);
        this.rateButton.setPosition((288 - this.rateButton.width) >> 1, 270);
        this.readyScreen.isActive = false;
        this.gameOverScreen.isActive = false;
        this.scorePanel.isActive = false;
        this.scorePanel.isVisible = false;
        this.okButton.isActive = false;
        this.okButton.isVisible = false;
        this.menuButton.isActive = false;
        this.menuButton.isVisible = false;
        this.pauseButton.isActive = false;
        this.pauseButton.isVisible = false;
        this.resumeButton.isActive = false;
        this.resumeButton.isVisible = false;
        this.shareButton.isActive = false;
        this.shareButton.isVisible = false;
        this.isInMenu = true;
        this.landOffset = 0;
        this.bird.init();
        this.gameSpeed = 2;
        this.pipeCountdown = 1;
        this.gameState = 0;
    }
}