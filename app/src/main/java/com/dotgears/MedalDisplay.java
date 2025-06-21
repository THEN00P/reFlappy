package com.dotgears;

/* renamed from: com.dotgears.k */
/* loaded from: classes.dex */
public class MedalDisplay extends GameObject {

    /* renamed from: a */
    public int x;

    /* renamed from: b */
    public int y;

    /* renamed from: c */
    public int width;

    /* renamed from: d */
    public int height;

    /* renamed from: e */
    public int id;

    /* renamed from: f */
    public int maxParticleCount;

    /* renamed from: g */
    private AtlasSprite[] medalSprites = GameManager.instance.getSpritesByPrefix("medals");

    @Override // com.dotgears.GameObject
    /* renamed from: a */
    public void update(float f) {
        if (this.isActive && this.maxParticleCount > 0) {
            this.maxParticleCount--;
            if (this.maxParticleCount <= 0) {
                this.maxParticleCount = 30;
                GameManager.instance.createParticleEmitter((this.x - 3) + MathHelper.randomRange(0, this.width + 6), (this.y - 3) + MathHelper.randomRange(0, this.height + 6));
            }
        }
    }

    /* renamed from: a */
    void initialize(int medalId) {
        this.x = 0;
        this.y = 0;
        this.width = 44;
        this.height = 44;
        this.id = medalId;
        this.maxParticleCount = 30;
        this.isActive = true;
        this.isVisible = true;
    }

    @Override // com.dotgears.GameObject
    /* renamed from: a */
    public void draw(GameManager gameManager) {
        if (this.isVisible) {
            gameManager.drawRotatedSprite(this.medalSprites[this.id].id, this.x, this.y, 1.0f, 1.0f, 1.0f);
        }
    }
}