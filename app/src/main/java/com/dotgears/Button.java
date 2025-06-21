package com.dotgears;

/* renamed from: com.dotgears.f */
/* loaded from: classes.dex */
public class Button extends GameObject {

    /* renamed from: a */
    public AtlasSprite sprite;

    /* renamed from: b */
    public int posX;

    /* renamed from: c */
    public int posY;

    /* renamed from: d */
    public int width;

    /* renamed from: e */
    public int height;

    /* renamed from: f */
    public boolean isPressed;

    /* renamed from: g */
    public boolean isJustPressed;

    /* renamed from: h */
    public boolean isJustReleased;

    /* renamed from: i */
    public boolean isTouched;

    @Override // com.dotgears.GameObject
    /* renamed from: a */
    public void update(float f) {
        int touchCount = GameManager.instance.maxTouches;
        int[] touchX = GameManager.instance.touchX;
        int[] touchY = GameManager.instance.touchY;
        this.isTouched = false;
        int i = 0;
        while (true) {
            if (i >= touchCount) {
                break;
            }
            if (touchX[i] > this.posX && touchX[i] < this.posX + this.width && touchY[i] > this.posY && touchY[i] < this.posY + this.height) {
                this.isTouched = true;
                break;
            }
            i++;
        }
        this.isJustPressed = false;
        this.isJustReleased = false;
        if (this.isTouched != this.isPressed) {
            if (this.isPressed) {
                this.isJustReleased = true;
                this.isPressed = false;
            } else {
                this.isJustPressed = true;
                this.isPressed = true;
            }
        }
    }

    /* renamed from: a */
    public void setPosition(int i, int i2) {
        this.posX = i;
        this.posY = i2;
        this.isActive = true;
        this.isVisible = true;
        this.isTouched = false;
        this.isJustPressed = false;
        this.isJustReleased = false;
        this.isPressed = false;
    }

    @Override // com.dotgears.GameObject
    /* renamed from: a */
    public void draw(GameManager gameManager) {
        if (this.isPressed) {
            gameManager.drawRotatedSprite(this.sprite.id, this.posX, this.posY + 2, 1.0f, 1.0f, 1.0f);
        } else {
            gameManager.drawRotatedSprite(this.sprite.id, this.posX, this.posY, 1.0f, 1.0f, 1.0f);
        }
    }

    /* renamed from: a */
    public void setSprite(String str) {
        this.sprite = GameManager.instance.findSpriteByName(str);
        this.width = this.sprite.width;
        this.height = this.sprite.height;
    }
}