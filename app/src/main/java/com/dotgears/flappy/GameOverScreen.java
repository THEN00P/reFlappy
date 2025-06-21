package com.dotgears.flappy;

import com.dotgears.AtlasSprite;
import com.dotgears.GameManager;
import com.dotgears.GameObject;
import com.dotgears.Transition;

/* renamed from: com.dotgears.flappy.e */
/* loaded from: classes.dex */
public class GameOverScreen extends GameObject {

    /* renamed from: c */
    public int positionY;

    /* renamed from: d */
    public float velocity;

    /* renamed from: e */
    public float gravity;

    /* renamed from: f */
    public int state;

    /* renamed from: b */
    public AtlasSprite gameOverText = GameManager.instance.findSpriteByName("text_game_over");

    /* renamed from: a */
    public Transition transition = new Transition();

    /* renamed from: a */
    public void run() {
        this.isActive = true;
        this.isVisible = true;
        this.transition.start(0.0f, 1.0f, 11, 1.0f);
        this.positionY = -1;
        this.velocity = -2.0f;
        this.gravity = 0.25f;
        this.state = 0;
        GameScene.instance.registerCallback(10, 0);
    }

    @Override // com.dotgears.GameObject
    /* renamed from: a */
    public void update(float deltaTime) {
        this.transition.update(deltaTime);
        if (this.positionY < 0) {
            this.positionY = (int) (this.positionY + this.velocity);
            this.velocity += this.gravity;
        } else {
            this.positionY = 0;
        }
        switch (this.state) {
            case com.google.android.gms.e.MapAttrs_mapType /* 0 */:
                if (this.transition.isActive) {
                    this.state = 1;
                    GameManager.instance.scorePanel.show(GameManager.instance.gameState, GameManager.instance.bestScore, 10, 20, 30, 40);
                    GameScene.instance.registerCallback(10, 0);
                    break;
                }
                break;
            case com.google.android.gms.e.MapAttrs_cameraBearing /* 1 */:
                if (GameManager.instance.scorePanel.state == 2) {
                    this.state = 2;
                    break;
                }
                break;
        }
    }

    @Override // com.dotgears.GameObject
    /* renamed from: a */
    public void draw(GameManager gameManager) {
        gameManager.drawSprite(this.gameOverText, (288 - this.gameOverText.width) >> 1, this.positionY + 130, this.transition.value);
    }
}