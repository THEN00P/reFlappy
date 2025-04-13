package com.dotgears;

import android.util.Log;

/* loaded from: classes.dex */
public class h {
    public static String l = "0123456789";
    protected int c;
    protected int d;
    protected int e;
    protected int f;
    protected int g;
    protected float h;
    public i[] a = new i[256];
    protected int[] b = new int[256];
    public char[] i = new char[256];
    public char[] j = new char[256];
    public int k = 0;

    public h(String str, int i) {
        i[] a = g.D.a(str);
        for (int i2 = 0; i2 < a.length; i2++) {
            int parseInt = Integer.parseInt(a[i2].a.split("_")[1]);
            this.a[parseInt] = a[i2];
            this.b[parseInt] = a[i2].b;
            this.c = this.c > a[i2].c ? this.c : a[i2].c;
            Log.i("FlappyBird", "Number: " + a[i2].a + " " + parseInt);
        }
        this.b[32] = this.b[48];
        this.d = i;
    }

    public void a(int i, int i2) {
        this.k = 0;
        int i3 = i;
        while (i2 > 0) {
            if (i3 > 0) {
                int i4 = i3 % 10;
                i3 /= 10;
                this.j[this.k] = l.charAt(i4);
                this.k++;
            }
            i2--;
        }
        for (int i5 = 0; i5 < this.k; i5++) {
            this.i[i5] = this.j[(this.k - i5) - 1];
        }
        if (this.k == 0) {
            this.i[0] = '0';
            this.k = 1;
        }
    }

    public void a(int i, int i2, int i3, float f) {
        this.f = i;
        this.g = i2;
        this.e = i3;
        this.h = f;
    }

    public void a(g gVar) {
        int i = this.k;
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            i2 = (i2 + this.b[this.i[i3]]) - this.d;
        }
        int i4 = i2 + 2;
        int i5 = this.c;
        if ((this.e & 2) != 0) {
            this.f -= i4 / 2;
        } else if ((this.e & 1) != 0) {
            this.f -= i4;
        }
        if ((this.e & 4) != 0) {
            this.g -= i5 / 2;
        } else if ((this.e & 8) != 0) {
            this.g -= i5;
        }
        int i6 = this.f;
        int i7 = this.g;
        for (int i8 = 0; i8 < i; i8++) {
            if (this.a[this.i[i8]] != null) {
                gVar.a(this.a[this.i[i8]], i6, i7, this.h);
            }
            i6 = (i6 + this.b[this.i[i8]]) - this.d;
        }
    }
}