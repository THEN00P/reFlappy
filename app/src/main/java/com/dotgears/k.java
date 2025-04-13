package com.dotgears;

/* loaded from: classes.dex */
public class k extends m {
    public int a;
    public int b;
    public int c;
    public int d;
    public int e;
    public int f;
    private i[] g = g.D.a("medals");

    @Override // com.dotgears.m
    public void a(float f) {
        if (this.F && this.f > 0) {
            this.f--;
            if (this.f <= 0) {
                this.f = 30;
                g.D.b((this.a - 3) + j.a(0, this.c + 6), (this.b - 3) + j.a(0, this.d + 6));
            }
        }
    }

    void a(int i) {
        this.a = 0;
        this.b = 0;
        this.c = 44;
        this.d = 44;
        this.e = i;
        this.f = 30;
        this.F = true;
        this.G = true;
    }

    @Override // com.dotgears.m
    public void a(g gVar) {
        if (this.G) {
            gVar.a(this.g[this.e].i, this.a, this.b, 1.0f, 1.0f, 1.0f);
        }
    }
}