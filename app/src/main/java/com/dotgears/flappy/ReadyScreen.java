package com.dotgears.flappy;

import com.dotgears.AtlasSprite;
import com.dotgears.GameManager;
import com.dotgears.GameObject;
import com.dotgears.Transition;

/* renamed from: com.dotgears.flappy.f */
/* loaded from: classes.dex */
public class ReadyScreen extends GameObject {

    /* renamed from: d */
    int state;

    /* renamed from: b */
    public AtlasSprite readyText = GameManager.instance.findSpriteByName("text_ready");

    /* renamed from: c */
    public AtlasSprite tutorialText = GameManager.instance.findSpriteByName("tutorial");

    /* renamed from: a */
    public Transition transition = new Transition();

    /* renamed from: a */
    public void run() {
        this.isActive = true;
        this.isVisible = true;
        this.transition.start(0.0f, 1.0f, 0, 0.5f);
        this.state = 0;
    }

    @Override // com.dotgears.GameObject
    /* renamed from: a */
    public void update(float f) {
        this.transition.update(f);
        switch (this.state) {
            case com.google.android.gms.e.MapAttrs_mapType /* 0 */:
                if (this.transition.isActive) {
                    this.state = 1;
                    break;
                }
                break;
            case com.google.android.gms.e.MapAttrs_cameraTargetLat /* 2 */:
                if (this.transition.isActive) {
                    this.isActive = true;
                    this.isVisible = false;
                    break;
                }
                break;
        }
    }

    @Override // com.dotgears.GameObject
    /* renamed from: a */
    public void draw(GameManager gameManager) {
        gameManager.drawSprite(this.readyText, (288 - this.readyText.width) >> 1, 146, this.transition.value);
        gameManager.drawSprite(this.tutorialText, (288 - this.tutorialText.width) >> 1, 220, this.transition.value);
    }
}