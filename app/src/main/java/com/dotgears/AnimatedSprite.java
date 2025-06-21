package com.dotgears;

/* renamed from: com.dotgears.q */
/* loaded from: classes.dex */
public class AnimatedSprite extends GameObject {

    /* renamed from: b */
    public int x;

    /* renamed from: c */
    public int y;

    /* renamed from: d */
    public int frameIndex;

    /* renamed from: e */
    public int width;

    /* renamed from: f */
    public int height;

    /* renamed from: g */
    public int offsetX;

    /* renamed from: h */
    public int offsetY;

    /* renamed from: k */
    public boolean isFlipped;

    /* renamed from: l */
    public boolean isRotated;

    /* renamed from: o */
    public int frameCount;

    /* renamed from: p */
    public AtlasSprite[] frames;

    /* renamed from: a */
    Animation[] animations = new Animation[10];

    /* renamed from: i */
    public float alpha = 1.0f;

    /* renamed from: j */
    public Animation currentAnimation = null;

    /* renamed from: m */
    public boolean isActive = false;

    /* renamed from: n */
    public boolean isVisible = false;

    @Override // com.dotgears.GameObject
    /* renamed from: a */
    public void update(float deltaTime) {
        if (this.isActive && this.currentAnimation != null) {
            this.currentAnimation.update(deltaTime);
        }
    }

    /* renamed from: a */
    public void initialize(int x, int y) {
        this.x = x;
        this.y = y;
        this.frameIndex = 0;
        this.isActive = true;
        this.isVisible = true;
        this.isFlipped = false;
        this.isRotated = false;
        this.alpha = 1.0f;
    }

    /* renamed from: a */
    public void defineAnimation(int id, String name, int[] frameSequence, int frameCount, int fps, boolean loop) {
        this.animations[id] = new Animation(id, name, frameSequence, frameCount, fps, loop);
    }

    /* renamed from: a */
    public void playAnimation(int animationId, boolean restart) {
        if (restart) {
            this.animations[animationId].reset();
        }
        this.animations[animationId].start();
        this.currentAnimation = this.animations[animationId];
    }

    @Override // com.dotgears.GameObject
    /* renamed from: a */
    public void draw(GameManager gameManager) {
        if (this.isVisible) {
            AtlasSprite atlasSprite = this.frames[0];
            if (this.currentAnimation != null) {
                atlasSprite = this.frames[this.currentAnimation.currentFrame];
            }
            gameManager.drawRotatedSprite(atlasSprite.id, this.x - this.offsetX, this.y - this.offsetY, 1.0f, 1.0f, this.alpha);
        }
    }

    /* renamed from: a */
    public void loadFrames(String framePrefix, int width, int height, int offsetX, int offsetY) {
        this.frames = GameManager.instance.getSpritesByPrefix(framePrefix);
        this.frameCount = this.frames.length;
        if (width == 0 || height == 0) {
            this.width = this.frames[0].width;
            this.height = this.frames[0].height;
        } else {
            this.width = width;
            this.height = height;
        }
        if (offsetX == 0 || offsetY == 0) {
            this.offsetX = (this.frames[0].width - this.width) >> 1;
            this.offsetY = (this.frames[0].height - this.height) >> 1;
        } else {
            this.offsetX = offsetX;
            this.offsetY = offsetY;
        }
    }
}