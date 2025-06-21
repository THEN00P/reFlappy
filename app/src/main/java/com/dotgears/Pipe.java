package com.dotgears;

/* renamed from: com.dotgears.o */
/* loaded from: classes.dex */
public class Pipe extends GameObject {

    /* renamed from: c */
    public boolean isTop;

    /* renamed from: d */
    public int lifespan;

    /* renamed from: e */
    public int score;

    /* renamed from: f */
    public int digits;

    /* renamed from: g */
    public int velocityX;

    /* renamed from: h */
    public int acceleration;

    /* renamed from: i */
    public int frameCounter;

    /* renamed from: j */
    public int x;

    /* renamed from: k */
    public int y;

    /* renamed from: l */
    public int scoreOffsetX;

    /* renamed from: a */
    public int digitWidth = 12;

    /* renamed from: b */
    public int digitHeight = 14;

    /* renamed from: m */
    private AtlasSprite[] numberSprites = GameManager.instance.getSpritesByPrefix("number_context");

    @Override // com.dotgears.GameObject
    /* renamed from: a */
    public void update(float f) {
        if (this.isActive && this.lifespan > 0) {
            this.lifespan--;
            if (this.velocityX < 2) {
                this.y += this.velocityX;
                this.frameCounter++;
                if (this.frameCounter == 4) {
                    this.frameCounter = 0;
                    this.velocityX += this.acceleration;
                }
            }
            if (this.lifespan <= 0) {
                this.isActive = false;
                this.isVisible = false;
            }
        }
    }

    @Override // com.dotgears.GameObject
    /* renamed from: a */
    public void draw(GameManager gameManager) {
        if (this.isVisible) {
            if (this.isTop) {
                gameManager.drawRotatedSprite(this.numberSprites[10].id, this.x, this.y, 1.0f, 1.0f, 1.0f);
            }
            renderScore(gameManager, this.score, this.x + this.scoreOffsetX, this.y, false, this.digits);
        }
    }

    /* renamed from: a */
    public void renderScore(GameManager gameManager, int score, int x, int y, boolean center, int maxDigits) {
        int nextDigit;
        int digitX = x - this.digitWidth;
        boolean showZero = true;
        int remainingScore = score;
        while (maxDigits > 0) {
            if (remainingScore > 0 || showZero) {
                int i = remainingScore % 10;
                gameManager.drawRotatedSprite(this.numberSprites[i].id, digitX, y, 1.0f, 1.0f, 1.0f);
                nextDigit = remainingScore / 10;
                digitX = i != 1 ? digitX - (this.digitWidth - 2) : digitX - 4;
                showZero = center;
            } else {
                nextDigit = remainingScore;
            }
            maxDigits--;
            remainingScore = nextDigit;
        }
    }
}