package com.dotgears.flappy;

import com.dotgears.g;
import com.dotgears.i;
import com.dotgears.j;
import com.dotgears.m;
import com.dotgears.q;

/* loaded from: classes.dex */
public class a extends q {
    private com.dotgears.e A;
    private int B = 30;
    float q;
    float r;
    float s;
    float t;
    float u;
    boolean v;
    boolean w;
    int x;
    float y;
    int z;

    public a() {
        a("bird", 20, 20, 14, 14);
        int[] iArr = {0, 1, 2, 1, 0, 1, 2, 1, 0, 1, 2, 1};
        a(0, "flap", iArr, 12, 30, false);
        a(1, "auto", iArr, 12, 10, true);
        this.w = true;
        this.z = j.a() % 3;
        this.A = new com.dotgears.e();
    }

    public void a() {
        super.a(80, 246);
        this.q = 0.0f;
        this.t = 0.0f;
        this.u = 1.0f;
        this.s = 0.4f;
        this.v = false;
        this.w = true;
        this.x = 0;
        a(1, true);
        this.z = j.a() % 3;
    }

    @Override // com.dotgears.q, com.dotgears.m
    public void a(float f) {
        super.a(f);
        if (this.w) {
            this.x += 8;
            if (this.x == 360) {
                this.x = 0;
            }
            this.y = j.b(this.x) * 4.0f;
            return;
        }
        this.y = 0.0f;
        this.t += this.u;
        if (this.t > 8.0f) {
            this.t = 8.0f;
        }
        this.c = (int) (this.c + this.t);
        if (this.c > 400 - this.f) {
            this.c = 400 - this.f;
            this.u = 0.0f;
            this.t = 0.0f;
        }
        this.q += this.r;
        this.r += this.s;
        if (this.q < -20.0f) {
            this.q = -20.0f;
        }
        if (this.q > 90.0f) {
            this.q = 90.0f;
        }
        this.B--;
        if (this.B == 0) {
            this.B = 30;
        }
        if (this.A.m) {
            this.A.a(f);
        }
    }

    @Override // com.dotgears.q, com.dotgears.m
    public void a(g gVar) {
        if (this.n) {
            i iVar = this.p[(this.z * 3) + 1];
            if (this.j != null && !this.j.a) {
                iVar = this.p[this.j.j + (this.z * 3)];
            }
            gVar.a(iVar, this.b - this.g, ((int) this.y) + (this.c - this.h), 1.0f, (int) this.q);
        }
    }

    public void b() {
        if (this.w) {
            this.w = false;
        }
        if (this.c >= 0 && !this.v) {
            a(0, true);
            this.t = -5.0f;
            this.u = 0.3f;
            this.r = -10.0f;
            this.s = 0.4f;
            c.D.a(4, (m) null, 5);
        }
    }
}