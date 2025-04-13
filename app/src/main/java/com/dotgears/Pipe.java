package com.dotgears;

/* loaded from: classes.dex */
// public class o extends m {
public class Pipe extends GameObject {
    // public boolean c;
    public boolean isTop;
    // public int d;
    public int lifespan;
    // public int e;
    public int score;
    // public int f;
    public int digits;
    // public int g;
    public int velocityX;
    // public int h;
    public int acceleration;
    // public int i;
    public int frameCounter;
    // public int j;
    public int x;
    // public int k;
    public int y;
    // public int l;
    public int scoreOffsetX;
    // public int a = 12;
    public int digitWidth = 12;
    // public int b = 14;
    public int digitHeight = 14;
    // private i[] m = g.D.a("number_context");
    private Sprite[] numberSprites = GameManager.instance.getSpritesByPrefix("number_context");

    @Override // com.dotgears.m
    // public void a(float f) {
    public void a(float deltaTime) {
        // if (this.F && this.d > 0) {
        if (this.isActive && this.lifespan > 0) {
            // this.d--;
            this.lifespan--;
            // if (this.g < 2) {
            if (this.velocityX < 2) {
                // this.k += this.g;
                this.y += this.velocityX;
                // this.i++;
                this.frameCounter++;
                // if (this.i == 4) {
                if (this.frameCounter == 4) {
                    // this.i = 0;
                    this.frameCounter = 0;
                    // this.g += this.h;
                    this.velocityX += this.acceleration;
                }
            }
            // if (this.d <= 0) {
            if (this.lifespan <= 0) {
                // this.F = false;
                this.isActive = false;
                // this.G = false;
                this.isVisible = false;
            }
        }
    }

    @Override // com.dotgears.m
    // public void a(g gVar) {
    public void draw(GameManager gameManager) {
        // if (this.G) {
        if (this.isVisible) {
            // if (this.c) {
            if (this.isTop) {
                // gVar.a(this.m[10].i, this.j, this.k, 1.0f, 1.0f, 1.0f);
                gameManager.drawRotatedSprite(this.numberSprites[10].id, this.x, this.y, 1.0f, 1.0f, 1.0f);
            }
            // a(gVar, this.e, this.j + this.l, this.k, false, this.f);
            renderScore(gameManager, this.score, this.x + this.scoreOffsetX, this.y, false, this.digits);
        }
    }

    // public void a(g gVar, int i, int i2, int i3, boolean z, int i4) {
    public void renderScore(GameManager gameManager, int score, int x, int y, boolean center, int maxDigits) {
        // int i5;
        int nextDigit;
        // int i6 = i2 - this.a;
        int digitX = x - this.digitWidth;
        // boolean z2 = true;
        boolean showZero = true;
        // int i7 = i;
        int remainingScore = score;
        // while (i4 > 0) {
        while (maxDigits > 0) {
            // if (i7 > 0 || z2) {
            if (remainingScore > 0 || showZero) {
                // int i8 = i7 % 10;
                int digit = remainingScore % 10;
                // gVar.a(this.m[i8].i, i6, i3, 1.0f, 1.0f, 1.0f);
                gameManager.drawRotatedSprite(this.numberSprites[digit].id, digitX, y, 1.0f, 1.0f, 1.0f);
                // i5 = i7 / 10;
                nextDigit = remainingScore / 10;
                // i6 = i8 != 1 ? i6 - (this.a - 2) : i6 - 4;
                digitX = digit != 1 ? digitX - (this.digitWidth - 2) : digitX - 4;
                // z2 = z;
                showZero = center;
            } else {
                // i5 = i7;
                nextDigit = remainingScore;
            }
            // i4--;
            maxDigits--;
            // i7 = i5;
            remainingScore = nextDigit;
        }
    }
}