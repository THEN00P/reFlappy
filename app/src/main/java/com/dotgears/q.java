package com.dotgears;

/* loaded from: classes.dex */
public class q extends m {
    public int b;
    public int c;
    public int d;
    public int e;
    public int f;
    public int g;
    public int h;
    public boolean k;
    public boolean l;
    public int o;
    public i[] p;
    d[] a = new d[10];
    public float i = 1.0f;
    public d j = null;
    public boolean m = false;
    public boolean n = false;

    @Override // com.dotgears.m
    public void a(float f) {
        if (this.m && this.j != null) {
            this.j.a(f);
        }
    }

    public void a(int i, int i2) {
        this.b = i;
        this.c = i2;
        this.d = 0;
        this.m = true;
        this.n = true;
        this.k = false;
        this.l = false;
        this.i = 1.0f;
    }

    public void a(int i, String str, int[] iArr, int i2, int i3, boolean z) {
        this.a[i] = new d(i, str, iArr, i2, i3, z);
    }

    public void a(int i, boolean z) {
        if (z) {
            this.a[i].a();
        }
        this.a[i].b();
        this.j = this.a[i];
    }

    @Override // com.dotgears.m
    public void a(g gVar) {
        if (this.n) {
            i iVar = this.p[0];
            if (this.j != null) {
                iVar = this.p[this.j.j];
            }
            gVar.a(iVar.i, this.b - this.g, this.c - this.h, 1.0f, 1.0f, this.i);
        }
    }

    public void a(String str, int i, int i2, int i3, int i4) {
        this.p = g.D.a(str);
        this.o = this.p.length;
        if (i == 0 || i2 == 0) {
            this.e = this.p[0].b;
            this.f = this.p[0].c;
        } else {
            this.e = i;
            this.f = i2;
        }
        if (i3 == 0 || i4 == 0) {
            this.g = (this.p[0].b - this.e) >> 1;
            this.h = (this.p[0].c - this.f) >> 1;
        } else {
            this.g = i3;
            this.h = i4;
        }
    }
}