package com.dotgears;

/* loaded from: classes.dex */
// public class l {
public class ScoreRenderer {
    // i[] a;
    Sprite[] numberSprites;
    // String b;
    String spritePrefix;

    // public l(String str) {
    public ScoreRenderer(String prefix) {
        // this.b = str;
        this.spritePrefix = prefix;
        // this.a = new i[11];
        this.numberSprites = new Sprite[11];
    }

    // public void a(g gVar, int i, int i2, int i3, boolean z, int i4) {
    public void renderScore(GameManager gameManager, int score, int x, int y, boolean center, int spacing) {
        // this.a = gVar.a(this.b);
        this.numberSprites = gameManager.getSpritesByPrefix(this.spritePrefix);
        
        // Calculate digits
        int length = 0;
        int scoreTemp = score;
        // if (scoreTemp == 0) {
        if (scoreTemp == 0) {
            length = 1;
        }
        // while (scoreTemp > 0) {
        while (scoreTemp > 0) {
            // scoreTemp /= 10;
            scoreTemp /= 10;
            // length++;
            length++;
        }
        
        // Calculate width if centering
        int totalWidth = 0;
        // if (z) {
        if (center) {
            // totalWidth = ((length - 1) * i4);
            totalWidth = ((length - 1) * spacing);
            // int scoreTemp2 = i;
            int scoreTemp2 = score;
            // do {
            do {
                // int num = scoreTemp2 % 10;
                int num = scoreTemp2 % 10;
                // scoreTemp2 /= 10;
                scoreTemp2 /= 10;
                
                // totalWidth += this.a[num].b;
                totalWidth += this.numberSprites[num].width;
            // } while (scoreTemp2 > 0);
            } while (scoreTemp2 > 0);
            
            // Start position if centered
            // i2 -= totalWidth / 2;
            x -= totalWidth / 2;
        }
        
        // Render digits from right to left
        // int digitPos = i2 + totalWidth;
        int digitPos = x + totalWidth;
        // int scoreTemp3 = i;
        int scoreTemp3 = score;
        
        // if (scoreTemp3 == 0) {
        if (scoreTemp3 == 0) {
            // digitPos -= this.a[0].b;
            digitPos -= this.numberSprites[0].width;
            // gVar.a(this.a[0], digitPos, i3, 1.0f);
            gameManager.drawSprite(this.numberSprites[0], digitPos, y, 1.0f);
        } else {
            // while (scoreTemp3 > 0) {
            while (scoreTemp3 > 0) {
                // int num = scoreTemp3 % 10;
                int num = scoreTemp3 % 10;
                // scoreTemp3 /= 10;
                scoreTemp3 /= 10;
                
                // digitPos -= this.a[num].b;
                digitPos -= this.numberSprites[num].width;
                // gVar.a(this.a[num], digitPos, i3, 1.0f);
                gameManager.drawSprite(this.numberSprites[num], digitPos, y, 1.0f);
                // digitPos -= i4;
                digitPos -= spacing;
            }
        }
    }
}