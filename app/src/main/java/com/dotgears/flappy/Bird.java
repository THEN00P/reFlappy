package com.dotgears.flappy;

import com.dotgears.AnimatedSprite;
import com.dotgears.AtlasSprite;
import com.dotgears.GameManager;
import com.dotgears.GameObject;
import com.dotgears.MathHelper;
import com.dotgears.ParticleEmitter;

/* renamed from: com.dotgears.flappy.a */
/* loaded from: classes.dex */
public class Bird extends AnimatedSprite {

    /* renamed from: A */
    private ParticleEmitter particleEmitter;

    /* renamed from: B */
    private int particleTimer = 30;

    /* renamed from: q */
    float angle;

    /* renamed from: r */
    float angleVelocity;

    /* renamed from: s */
    float angleAcceleration;

    /* renamed from: t */
    float verticalVelocity;

    /* renamed from: u */
    float gravity;

    /* renamed from: v */
    boolean isDead;

    /* renamed from: w */
    boolean isReady;

    /* renamed from: x */
    int flapAngle;

    /* renamed from: y */
    float flapOffset;

    /* renamed from: z */
    int birdColor;

    public Bird() {
        loadFrames("bird", 20, 20, 14, 14);
        int[] frameSequence = {0, 1, 2, 1, 0, 1, 2, 1, 0, 1, 2, 1};
        defineAnimation(0, "flap", frameSequence, 12, 30, false);
        defineAnimation(1, "auto", frameSequence, 12, 10, true);
        this.isReady = true;
        this.birdColor = MathHelper.nextRandom() % 3;
        this.particleEmitter = new ParticleEmitter();
    }

    /* renamed from: a */
    public void init() {
        super.initialize(80, 246);
        this.angle = 0.0f;
        this.verticalVelocity = 0.0f;
        this.gravity = 1.0f;
        this.angleAcceleration = 0.4f;
        this.isDead = false;
        this.isReady = true;
        this.flapAngle = 0;
        playAnimation(1, true);
        this.birdColor = MathHelper.nextRandom() % 3;
    }

    @Override // com.dotgears.AnimatedSprite, com.dotgears.GameObject
    /* renamed from: a */
    public void update(float f) {
        super.update(f);
        if (this.isReady) {
            this.flapAngle += 8;
            if (this.flapAngle == 360) {
                this.flapAngle = 0;
            }
            this.flapOffset = MathHelper.getSin(this.flapAngle) * 4.0f;
            return;
        }
        this.flapOffset = 0.0f;
        this.verticalVelocity += this.gravity;
        if (this.verticalVelocity > 8.0f) {
            this.verticalVelocity = 8.0f;
        }
        this.y = (int) (this.y + this.verticalVelocity);
        if (this.y > 400 - this.height) {
            this.y = 400 - this.height;
            this.gravity = 0.0f;
            this.verticalVelocity = 0.0f;
        }
        this.angle += this.angleVelocity;
        this.angleVelocity += this.angleAcceleration;
        if (this.angle < -20.0f) {
            this.angle = -20.0f;
        }
        if (this.angle > 90.0f) {
            this.angle = 90.0f;
        }
        this.particleTimer--;
        if (this.particleTimer == 0) {
            this.particleTimer = 30;
        }
        if (this.particleEmitter.isActive) {
            this.particleEmitter.update(f);
        }
    }

    @Override // com.dotgears.AnimatedSprite, com.dotgears.GameObject
    /* renamed from: a */
    public void draw(GameManager gameManager) {
        if (this.isVisible) {
            AtlasSprite atlasSprite = this.frames[(this.birdColor * 3) + 1];
            if (this.currentAnimation != null && !this.currentAnimation.isComplete) {
                atlasSprite = this.frames[this.currentAnimation.currentFrame + (this.birdColor * 3)];
            }
            gameManager.drawRotatedSprite(atlasSprite, this.x - this.offsetX, ((int) this.flapOffset) + (this.y - this.offsetY), 1.0f, (int) this.angle);
        }
    }

    public void b() {
        if (this.isReady) {
            this.isReady = false;
        }
        if (this.y >= 0 && !this.isDead) {
            playAnimation(0, true);
            this.verticalVelocity = -5.0f;
            this.gravity = 0.3f;
            this.angleVelocity = -10.0f;
            this.angleAcceleration = 0.4f;
            GameScene.instance.scheduleCallback(4, (GameObject) null, 5);
        }
    }
}