package com.dotgears;

import android.util.Log;

/* renamed from: com.dotgears.h */
/* loaded from: classes.dex */
public class FontRenderer {

    /* renamed from: l */
    public static String DIGITS = "0123456789";

    /* renamed from: c */
    protected int maxCharHeight;

    /* renamed from: d */
    protected int letterSpacing;

    /* renamed from: e */
    protected int alignmentFlags;

    /* renamed from: f */
    protected int posX;

    /* renamed from: g */
    protected int posY;

    /* renamed from: h */
    protected float alpha;

    /* renamed from: a */
    public AtlasSprite[] fontSprites = new AtlasSprite[256];

    /* renamed from: b */
    protected int[] charWidths = new int[256];

    /* renamed from: i */
    public char[] textBuffer = new char[256];

    /* renamed from: j */
    public char[] reverseBuffer = new char[256];

    /* renamed from: k */
    public int textLength = 0;

    public FontRenderer(String spriteSheetName, int spacing) {
        AtlasSprite[] sprites = GameManager.instance.getSpritesByPrefix(spriteSheetName);
        for (int i = 0; i < sprites.length; i++) {
            int charCode = Integer.parseInt(sprites[i].name.split("_")[1]);
            this.fontSprites[charCode] = sprites[i];
            this.charWidths[charCode] = sprites[i].width;
            this.maxCharHeight = this.maxCharHeight > sprites[i].height ? this.maxCharHeight : sprites[i].height;
            Log.i("FlappyBird", "Number: " + sprites[i].name + " " + charCode);
        }
        this.charWidths[32] = this.charWidths[48];
        this.letterSpacing = spacing;
    }

    /* renamed from: a */
    public void setNumber(int number, int minDigits) {
        this.textLength = 0;
        int remainingNumber = number;
        while (minDigits > 0) {
            if (remainingNumber > 0) {
                int digit = remainingNumber % 10;
                remainingNumber /= 10;
                this.reverseBuffer[this.textLength] = DIGITS.charAt(digit);
                this.textLength++;
            }
            minDigits--;
        }
        for (int i = 0; i < this.textLength; i++) {
            this.textBuffer[i] = this.reverseBuffer[(this.textLength - i) - 1];
        }
        if (this.textLength == 0) {
            this.textBuffer[0] = '0';
            this.textLength = 1;
        }
    }

    /* renamed from: a */
    public void setPosition(int x, int y, int alignment, float alpha) {
        this.posX = x;
        this.posY = y;
        this.alignmentFlags = alignment;
        this.alpha = alpha;
    }

    /* renamed from: a */
    public void render(GameManager gameManager) {
        int length = this.textLength;
        int totalWidth = 0;
        for (int i = 0; i < length; i++) {
            totalWidth = (totalWidth + this.charWidths[this.textBuffer[i]]) - this.letterSpacing;
        }
        int finalWidth = totalWidth + 2;
        int height = this.maxCharHeight;
        if ((this.alignmentFlags & 2) != 0) {
            this.posX -= finalWidth / 2;
        } else if ((this.alignmentFlags & 1) != 0) {
            this.posX -= finalWidth;
        }
        if ((this.alignmentFlags & 4) != 0) {
            this.posY -= height / 2;
        } else if ((this.alignmentFlags & 8) != 0) {
            this.posY -= height;
        }
        int currentX = this.posX;
        int currentY = this.posY;
        for (int i2 = 0; i2 < length; i2++) {
            if (this.fontSprites[this.textBuffer[i2]] != null) {
                gameManager.drawSprite(this.fontSprites[this.textBuffer[i2]], currentX, currentY, this.alpha);
            }
            currentX = (currentX + this.charWidths[this.textBuffer[i2]]) - this.letterSpacing;
        }
    }
}