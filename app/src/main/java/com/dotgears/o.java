package com.dotgears;

/* loaded from: classes.dex */
public class o extends m {
    public boolean c;
    public int d;
    public int e;
    public int f;
    public int g;
    public int h;
    public int i;
    public int j;
    public int k;
    public int l;
    public int a = 12;
    public int b = 14;
    private i[] m = g.D.a("number_context");

    @Override // com.dotgears.m
    public void a(float f) {
        if (this.F && this.d > 0) {
            this.d--;
            if (this.g < 2) {
                this.k += this.g;
                this.i++;
                if (this.i == 4) {
                    this.i = 0;
                    this.g += this.h;
                }
            }
            if (this.d <= 0) {
                this.F = false;
                this.G = false;
            }
        }
    }

    @Override // com.dotgears.m
    public void a(g gVar) {
        if (this.G) {
            if (this.c) {
                gVar.a(this.m[10].i, this.j, this.k, 1.0f, 1.0f, 1.0f);
            }
            a(gVar, this.e, this.j + this.l, this.k, false, this.f);
        }
    }

    public void a(g gVar, int i, int i2, int i3, boolean z, int i4) {
        int i5;
        int i6 = i2 - this.a;
        boolean z2 = true;
        int i7 = i;
        while (i4 > 0) {
            if (i7 > 0 || z2) {
                int i8 = i7 % 10;
                gVar.a(this.m[i8].i, i6, i3, 1.0f, 1.0f, 1.0f);
                i5 = i7 / 10;
                i6 = i8 != 1 ? i6 - (this.a - 2) : i6 - 4;
                z2 = z;
            } else {
                i5 = i7;
            }
            i4--;
            i7 = i5;
        }
    }
}