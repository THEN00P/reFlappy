package com.dotgears;

/* loaded from: classes.dex */
public class d {
    public boolean a;
    public String b;
    public int c;
    public int[] d;
    public float e;
    public boolean f;
    public int g;
    public int h;
    public int i;
    public int j;

    public d(int i, String str, int[] iArr, int i2, int i3, boolean z) {
        this.b = str;
        this.c = i2;
        this.d = new int[this.c];
        System.arraycopy(iArr, 0, this.d, 0, this.c);
        this.e = 1000 / i3;
        this.f = z;
        this.g = i;
    }

    public void a() {
        this.h = 0;
        this.i = 0;
        this.j = this.d[0];
    }

    public void a(float f) {
        if (this.a) {
            return;
        }
        this.h += 15;
        if (this.h >= this.e) {
            this.h = 0;
            this.i++;
            if (this.i >= this.c) {
                if (this.f) {
                    this.i = 0;
                } else {
                    this.a = true;
                }
                this.i = 0;
            }
            this.j = this.d[this.i];
        }
    }

    void b() {
        if (!this.f && this.a) {
            a();
        }
        this.a = false;
    }
}