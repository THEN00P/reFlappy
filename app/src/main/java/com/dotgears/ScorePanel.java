package com.dotgears;

/* renamed from: com.dotgears.p */
/* loaded from: classes.dex */
public class ScorePanel extends GameObject {

    /* renamed from: a */
    public int panelX;

    /* renamed from: b */
    public int panelY;

    /* renamed from: g */
    public int bronzeMedalThreshold;

    /* renamed from: h */
    public int silverMedalThreshold;

    /* renamed from: i */
    public int goldMedalThreshold;

    /* renamed from: j */
    public int platinumMedalThreshold;

    /* renamed from: k */
    public int state;

    /* renamed from: m */
    public boolean isNewHighScore;

    /* renamed from: e */
    public int currentScore = 0;

    /* renamed from: f */
    public int bestScore = 0;

    /* renamed from: l */
    public int delay = 0;

    /* renamed from: r */
    public AtlasSprite scoreSprite = GameManager.instance.findSpriteByName("score_panel");

    /* renamed from: q */
    public AtlasSprite newSprite = GameManager.instance.findSpriteByName("new");

    /* renamed from: s */
    public ScoreRenderer scoreRenderer = GameManager.instance.scoreRenderer;

    /* renamed from: c */
    public int panelWidth = this.scoreSprite.width;

    /* renamed from: d */
    public int panelHeight = this.scoreSprite.height;

    /* renamed from: n */
    public int targetY = (512 - this.panelHeight) >> 1;

    /* renamed from: p */
    public Transition transition = new Transition();

    /* renamed from: o */
    public MedalDisplay medalDisplay = new MedalDisplay();

    @Override // com.dotgears.GameObject
    /* renamed from: a */
    public void update(float deltaTime) {
        if (this.isActive) {
            if (!this.transition.isActive) {
                this.transition.update(deltaTime);
            }
            switch (this.state) {
                case com.google.android.gms.e.MapAttrs_mapType /* 0 */:
                    this.panelY = (int) this.transition.value;
                    if (this.transition.isActive) {
                        if (this.delay <= 0) {
                            this.state = 2;
                            break;
                        } else {
                            this.state = 1;
                            this.transition.start(0.0f, this.delay, 0, 0.5f);
                            break;
                        }
                    }
                    break;
                case com.google.android.gms.e.MapAttrs_cameraBearing /* 1 */:
                    this.currentScore = (int) this.transition.value;
                    if (this.transition.isActive) {
                        this.state = 2;
                        GameManager.instance.addScore(this.currentScore);
                        if (this.currentScore > this.bestScore) {
                            this.bestScore = this.currentScore;
                            this.isNewHighScore = true;
                        }
                        if (this.currentScore >= this.platinumMedalThreshold) {
                            this.medalDisplay.initialize(0);
                        } else if (this.currentScore >= this.goldMedalThreshold) {
                            this.medalDisplay.initialize(1);
                        } else if (this.currentScore >= this.silverMedalThreshold) {
                            this.medalDisplay.initialize(2);
                        } else if (this.currentScore >= this.bronzeMedalThreshold) {
                            this.medalDisplay.initialize(3);
                        }
                        this.medalDisplay.x = this.panelX + 32;
                        this.medalDisplay.y = this.panelY + 44;
                        break;
                    }
                    break;
                case com.google.android.gms.e.MapAttrs_cameraTargetLat /* 2 */:
                    this.medalDisplay.update(deltaTime);
                    break;
            }
        }
    }

    /* renamed from: a */
    public void show(int delay, int bestScore, int bronzeThreshold, int silverThreshold, int goldThreshold, int platinumThreshold) {
        this.delay = delay;
        this.bestScore = bestScore;
        this.currentScore = 0;
        this.bronzeMedalThreshold = bronzeThreshold;
        this.silverMedalThreshold = silverThreshold;
        this.goldMedalThreshold = goldThreshold;
        this.platinumMedalThreshold = platinumThreshold;
        this.isActive = true;
        this.isVisible = true;
        this.isNewHighScore = false;
        this.panelX = (288 - this.panelWidth) >> 1;
        this.panelY = 504;
        this.transition.start(this.panelY, this.targetY, 11, 0.5f);
        this.state = 0;
        this.medalDisplay.isActive = false;
        this.medalDisplay.isVisible = false;
    }

    @Override // com.dotgears.GameObject
    /* renamed from: a */
    public void draw(GameManager gameManager) {
        if (this.isVisible) {
            gameManager.drawRotatedSprite(this.scoreSprite.id, this.panelX, this.panelY, 1.0f, 1.0f, 1.0f);
            this.scoreRenderer.renderScore(gameManager, this.currentScore, this.panelX + 210, this.panelY + 36, false, 10);
            this.scoreRenderer.renderScore(gameManager, this.bestScore, this.panelX + 210, this.panelY + 78, false, 10);
            if (this.isNewHighScore) {
                gameManager.drawRotatedSprite(this.newSprite.id, this.panelX + 142, this.panelY + 60, 1.0f, 1.0f, 1.0f);
            }
            this.medalDisplay.draw(gameManager);
        }
    }
}