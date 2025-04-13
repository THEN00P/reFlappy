package com.dotgears.flappy;

import com.dotgears.g;
import com.dotgears.i;
import com.dotgears.m;
import com.dotgears.r;

/* loaded from: classes.dex */
public class f extends m {
    int d;
    public i b = g.D.b("text_ready");
    public i c = g.D.b("tutorial");
    public r a = new r();

    public void a() {
        this.F = true;
        this.G = true;
        this.a.a(0.0f, 1.0f, 0, 0.5f);
        this.d = 0;
    }

    @Override // com.dotgears.m
    public void a(float f) {
        this.a.a(f);
        switch (this.d) {
            case com.google.android.gms.e.MapAttrs_mapType /* 0 */:
                if (this.a.g) {
                    this.d = 1;
                    break;
                }
                break;
            case com.google.android.gms.e.MapAttrs_cameraTargetLat /* 2 */:
                if (this.a.g) {
                    this.F = true;
                    this.G = false;
                    break;
                }
                break;
        }
    }

    @Override // com.dotgears.m
    public void a(g gVar) {
        gVar.a(this.b, (288 - this.b.b) >> 1, 146, this.a.a);
        gVar.a(this.c, (288 - this.c.b) >> 1, 220, this.a.a);
    }
}