package com.dotgears.flappy;

import com.dotgears.g;
import com.dotgears.i;
import com.dotgears.m;
import com.dotgears.r;

/* loaded from: classes.dex */
// public class f extends m {
public class ReadyScreen extends m {
    int state;
    public i readyText = g.D.b("text_ready");
    public i tutorialText = g.D.b("tutorial");
    public r transition = new r();

    public void a() {
        this.F = true;
        this.G = true;
        this.transition.a(0.0f, 1.0f, 0, 0.5f);
        this.state = 0;
    }

    @Override // com.dotgears.m
    public void a(float f) {
        this.transition.a(f);
        switch (this.state) {
            case com.google.android.gms.e.MapAttrs_mapType /* 0 */:
                if (this.transition.g) {
                    this.state = 1;
                    break;
                }
                break;
            case com.google.android.gms.e.MapAttrs_cameraTargetLat /* 2 */:
                if (this.transition.g) {
                    this.F = true;
                    this.G = false;
                    break;
                }
                break;
        }
    }

    @Override // com.dotgears.m
    public void a(g gVar) {
        gVar.a(this.readyText, (288 - this.readyText.b) >> 1, 146, this.transition.a);
        gVar.a(this.tutorialText, (288 - this.tutorialText.b) >> 1, 220, this.transition.a);
    }
}