package com.dotgears;

/* renamed from: com.dotgears.l */
/* loaded from: classes.dex */
public class ScoreRenderer {

    /* renamed from: a */
    public int width;

    /* renamed from: b */
    public int height;

    /* renamed from: c */
    private AtlasSprite[] frames;

    public ScoreRenderer(String str) {
        this.frames = GameManager.instance.getSpritesByPrefix(str);
        this.width = this.frames[0].width;
        this.height = this.frames[0].height;
    }

    /* renamed from: a */
    public void renderScore(GameManager gameManager, int score, int x, int y, boolean pFalse, int p10) {
        int i;
        int offsetX = x - this.width;
        boolean z = true;
        int i2 = score;
        while (p10 > 0) {
            if (i2 > 0 || z) {
                gameManager.drawRotatedSprite(this.frames[i2 % 10].id, offsetX, y, 1.0f, 1.0f, 1.0f);
                offsetX -= this.width;
                i = i2 / 10;
                z = pFalse;
            } else {
                i = i2;
            }
            p10--;
            i2 = i;
        }
    }
}