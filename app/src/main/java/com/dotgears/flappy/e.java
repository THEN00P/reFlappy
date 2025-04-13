package com.dotgears.flappy;

import com.dotgears.g;
import com.dotgears.i;
import com.dotgears.m;
import com.dotgears.r;

/* loaded from: classes.dex */
public class e extends m {
    public int c;
    public float d;
    public float e;
    public int f;
    public i b = g.D.b("text_game_over");
    public r a = new r();

    public void a() {
        this.F = true;
        this.G = true;
        this.a.a(0.0f, 1.0f, 11, 1.0f);
        this.c = -1;
        this.d = -2.0f;
        this.e = 0.25f;
        this.f = 0;
        c.D.c(10, 0);
    }

    @Override // com.dotgears.m
    public void a(float f) {
        this.a.a(f);
        if (this.c < 0) {
            this.c = (int) (this.c + this.d);
            this.d += this.e;
        } else {
            this.c = 0;
        }
        switch (this.f) {
            case com.google.android.gms.e.MapAttrs_mapType /* 0 */:
                if (this.a.g) {
                    this.f = 1;
                    g.D.C.a(g.D.y, g.D.z, 10, 20, 30, 40);
                    c.D.c(10, 0);
                    break;
                }
                break;
            case com.google.android.gms.e.MapAttrs_cameraBearing /* 1 */:
                if (g.D.C.k == 2) {
                    this.f = 2;
                    break;
                }
                break;
        }
    }

    @Override // com.dotgears.m
    public void a(g gVar) {
        gVar.a(this.b, (288 - this.b.b) >> 1, this.c + 130, this.a.a);
    }
}