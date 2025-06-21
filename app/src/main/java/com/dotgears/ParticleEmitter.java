package com.dotgears;

/* renamed from: com.dotgears.e */
/* loaded from: classes.dex */
public class ParticleEmitter extends AnimatedSprite {
    public ParticleEmitter() {
        loadFrames("blink", 10, 10, 4, 4);
        defineAnimation(0, "blink", new int[]{0, 1, 2, 1, 0}, 5, 10, false);
        this.isActive = false;
        this.isVisible = false;
        playAnimation(0, true);
    }

    @Override // com.dotgears.AnimatedSprite, com.dotgears.GameObject
    /* renamed from: a */
    public void update(float f) {
        if (this.isActive) {
            super.update(f);
            if (this.currentAnimation == null || !this.currentAnimation.isComplete) {
                return;
            }
            this.isActive = false;
            this.isVisible = false;
        }
    }

    @Override // com.dotgears.AnimatedSprite
    /* renamed from: a */
    public void initialize(int i, int i2) {
        super.initialize(i, i2);
        playAnimation(0, true);
    }

    @Override // com.dotgears.AnimatedSprite, com.dotgears.GameObject
    /* renamed from: a */
    public void draw(GameManager gameManager) {
        if (this.isVisible) {
            super.draw(gameManager);
        }
    }
}