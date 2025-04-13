package com.dotgears.flappy;

import com.dotgears.g;
import com.dotgears.i;
import com.dotgears.m;
import com.dotgears.r;

/* loaded from: classes.dex */
// public class e extends m {
public class GameOverScreen extends m {
    // public int c;
    public int positionY;
    // public float d;
    public float velocity;
    // public float e;
    public float gravity;
    // public int f;
    public int state;
    // public i b = g.D.b("text_game_over");
    public i gameOverText = g.D.b("text_game_over");
    // public r a = new r();
    public r transition = new r();

    public void a() {
        this.F = true;
        this.G = true;
        this.transition.a(0.0f, 1.0f, 11, 1.0f);
        this.positionY = -1;
        this.velocity = -2.0f;
        this.gravity = 0.25f;
        this.state = 0;
        GameScene.instance.c(10, 0);
    }

    @Override // com.dotgears.m
    public void a(float f) {
        this.transition.a(f);
        if (this.positionY < 0) {
            this.positionY = (int) (this.positionY + this.velocity);
            this.velocity += this.gravity;
        } else {
            this.positionY = 0;
        }
        switch (this.state) {
            case com.google.android.gms.e.MapAttrs_mapType /* 0 */:
                if (this.transition.g) {
                    this.state = 1;
                    g.D.C.a(g.D.y, g.D.z, 10, 20, 30, 40);
                    GameScene.instance.c(10, 0);
                    break;
                }
                break;
            case com.google.android.gms.e.MapAttrs_cameraBearing /* 1 */:
                if (g.D.C.k == 2) {
                    this.state = 2;
                    break;
                }
                break;
        }
    }

    @Override // com.dotgears.m
    public void a(g gVar) {
        gVar.a(this.gameOverText, (288 - this.gameOverText.b) >> 1, this.positionY + 130, this.transition.a);
    }
}