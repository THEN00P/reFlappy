package com.dotgears;

import android.util.Log;

/* loaded from: classes.dex */
public class Random {
    public static float A;
    public static float B;
    public static float[] a;
    public static float[] b;
    public static float[] c;
    public static float[] d;
    public static float[] e;
    public static float[] f;
    public static float[] g;
    public static float[] h;
    public static float[] i;
    public static float[] j;
    public static float[] k;
    public static float[] l;
    public static float[] m;
    public static float[] n;
    public static float[] o;
    public static float[] p;
    public static float[] q;
    public static float[] r;
    public static float[] s;
    public static float[] t;
    public static float[] u;
    public static float[] v;
    public static float[] w;
    static Random x;
    public static int y;
    public static int z;
    static float scale = 1.0f;
    static float rotationSpeed = 1.0f;
    static float rotatedX;
    static float rotatedY;
    static float cosValue;
    static float sinValue;
    private int seed;
    private int seedModifier;

    public Random() {
        a = new float[360];
        b = new float[360];
        for (int i2 = 0; i2 < 360; i2++) {
            float f2 = (i2 * 3.1415925f) / 180.0f;
            a[i2] = (float) Math.sin(f2);
            b[i2] = (float) Math.cos(f2);
        }
        c = new float[101];
        d = new float[101];
        e = new float[101];
        f = new float[101];
        g = new float[101];
        h = new float[101];
        i = new float[101];
        j = new float[101];
        k = new float[101];
        l = new float[101];
        m = new float[101];
        n = new float[101];
        o = new float[101];
        p = new float[101];
        q = new float[101];
        r = new float[101];
        s = new float[101];
        t = new float[101];
        u = new float[101];
        v = new float[101];
        w = new float[101];
        for (int i3 = 0; i3 <= 100; i3++) {
            double d2 = i3 / 100.0d;
            c[i3] = (float) a(d2);
            d[i3] = (float) b(d2);
            e[i3] = (float) c(d2);
            f[i3] = (float) d(d2);
            g[i3] = (float) e(d2);
            h[i3] = (float) f(d2);
            i[i3] = (float) g(d2);
            j[i3] = (float) h(d2);
            k[i3] = (float) i(d2);
            l[i3] = (float) j(d2);
            m[i3] = (float) k(d2);
            n[i3] = (float) l(d2);
            o[i3] = (float) m(d2);
            p[i3] = (float) n(d2);
            q[i3] = (float) o(d2);
            r[i3] = (float) p(d2);
            s[i3] = (float) q(d2);
            t[i3] = (float) r(d2);
            u[i3] = (float) s(d2);
            v[i3] = (float) t(d2);
            w[i3] = (float) u(d2);
        }
        this.seed = 0;
        this.seedModifier = 0;
    }

    public static float getFloat(float min, float max) {
        return min + (((float) x.getNextRandom()) * (max - min));
    }

    public static int getInt(int min, int max) {
        return min + ((int) (((float) x.getNextRandom()) * ((float) ((max + 1) - min))));
    }

    public static void a(int x1, int y1, float centerX, float centerY, float angle) {
        float deltaX = ((float) x1) - centerX;
        float deltaY = ((float) y1) - centerY;
        cosValue = (float) Math.cos((((double) angle) * 3.141592653589793d) / 180.0d);
        sinValue = (float) Math.sin((((double) angle) * 3.141592653589793d) / 180.0d);
        rotatedX = (deltaX * cosValue) - (deltaY * sinValue);
        rotatedY = (deltaX * sinValue) + (deltaY * cosValue);
        rotatedX += centerX;
        rotatedY += centerY;
    }

    public static void setSeed(int seed) {
        x = new Random();
        x.seed = seed;
        x.advanceSeed();
    }

    private long getSeed() {
        long currentSeed = this.seed;
        return (currentSeed < 0 ? (-currentSeed) : currentSeed) % 30269;
    }

    private float getNextRandom() {
        advanceSeed();
        double seedValue = (double) getSeed();
        return (float) (seedValue / 30269.0d);
    }

    private void advanceSeed() {
        this.seedModifier++;
        if (this.seedModifier == 10000) {
            this.seedModifier = 0;
        }
        int newSeed = (this.seed * 171) + this.seedModifier;
        this.seed = newSeed - ((newSeed / 30269) * 30269);
    }

    public static float a(float f2) {
        float f3 = f2;
        while (f3 > 360.0f) {
            f3 -= 360.0f;
        }
        while (f3 < 0.0f) {
            f3 += 360.0f;
        }
        return f3;
    }

    public static int a() {
        z = (36969 * (z & 65535)) + (z >> 16);
        y = ((y & 65535) * 18000) + (y >> 16);
        return Math.abs((z << 16) + y);
    }

    public static int a(int i2, int i3) {
        return (a() % (i3 - i2)) + i2;
    }

    public static float b(float f2) {
        return a[(int) f2];
    }

    public static float b(int i2) {
        return i[i2];
    }

    static double c(double d2) {
        return d2 < 0.5d ? 2.0d * d2 * d2 : ((((-2.0d) * d2) * d2) + (4.0d * d2)) - 1.0d;
    }

    public static float c(float f2) {
        return b[(int) f2];
    }

    public static float c(int i2) {
        return d[i2];
    }

    static double d(double d2) {
        return d2 * d2 * d2;
    }

    public static float d(int i2) {
        return e[i2];
    }

    static double e(double d2) {
        double d3 = d2 - 1.0d;
        return (d3 * d3 * d3) + 1.0d;
    }

    public static float e(int i2) {
        return f[i2];
    }

    static double f(double d2) {
        if (d2 < 0.5d) {
            return 4.0d * d2 * d2 * d2;
        }
        double d3 = (2.0d * d2) - 2.0d;
        return (d3 * 0.5d * d3 * d3) + 1.0d;
    }

    public static float f(int i2) {
        return g[i2];
    }

    static double g(double d2) {
        return d2 * d2 * d2 * d2;
    }

    public static float g(int i2) {
        return h[i2];
    }

    static double h(double d2) {
        double d3 = d2 - 1.0d;
        return (d3 * d3 * d3 * (1.0d - d2)) + 1.0d;
    }

    public static float h(int i2) {
        return i[i2];
    }

    static double i(double d2) {
        if (d2 < 0.5d) {
            return 8.0d * d2 * d2 * d2 * d2;
        }
        double d3 = d2 - 1.0d;
        return (d3 * (-8.0d) * d3 * d3 * d3) + 1.0d;
    }

    public static float i(int i2) {
        return j[i2];
    }

    static double j(double d2) {
        return d2 * d2 * d2 * d2 * d2;
    }

    public static float j(int i2) {
        return k[i2];
    }

    static double k(double d2) {
        double d3 = d2 - 1.0d;
        return (d3 * d3 * d3 * d3 * d3) + 1.0d;
    }

    public static float k(int i2) {
        return l[i2];
    }

    static double l(double d2) {
        if (d2 < 0.5d) {
            return 16.0d * d2 * d2 * d2 * d2 * d2;
        }
        double d3 = (2.0d * d2) - 2.0d;
        return (d3 * 0.5d * d3 * d3 * d3 * d3) + 1.0d;
    }

    public static float l(int i2) {
        return m[i2];
    }

    public static float m(int i2) {
        return n[i2];
    }

    public static float n(int i2) {
        return o[i2];
    }

    public static float o(int i2) {
        return p[i2];
    }

    public static float p(int i2) {
        return q[i2];
    }

    public static float q(int i2) {
        return o[i2];
    }

    public static float r(int i2) {
        return p[i2];
    }

    public static float s(int i2) {
        return q[i2];
    }

    public static float t(int i2) {
        return u[i2];
    }

    public static float u(int i2) {
        return v[i2];
    }

    public static float v(int i2) {
        return w[i2];
    }

    double a(double d2) {
        return d2 * d2;
    }

    double b(double d2) {
        return -((d2 - 2.0d) * d2);
    }

    double m(double d2) {
        return Math.sin(20.420352248333657d * d2) * Math.pow(2.0d, 10.0d * (d2 - 1.0d));
    }

    double n(double d2) {
        return (Math.sin((-20.420352248333657d) * (d2 + 1.0d)) * Math.pow(2.0d, (-10.0d) * d2)) + 1.0d;
    }

    double o(double d2) {
        return d2 < 0.5d ? Math.sin(20.420352248333657d * 2.0d * d2) * 0.5d * Math.pow(2.0d, 10.0d * ((2.0d * d2) - 1.0d)) : ((Math.sin((-20.420352248333657d) * (((2.0d * d2) - 1.0d) + 1.0d)) * Math.pow(2.0d, (-10.0d) * ((2.0d * d2) - 1.0d))) + 2.0d) * 0.5d;
    }

    double p(double d2) {
        return ((d2 * d2) * d2) - (Math.sin(3.141592653589793d * d2) * d2);
    }

    double q(double d2) {
        double d3 = 1.0d - d2;
        return 1.0d - (((d3 * d3) * d3) - (d3 * Math.sin(3.141592653589793d * d3)));
    }

    double r(double d2) {
        if (d2 < 0.5d) {
            double d3 = 2.0d * d2;
            return (((d3 * d3) * d3) - (d3 * Math.sin(3.141592653589793d * d3))) * 0.5d;
        }
        double d4 = 1.0d - ((2.0d * d2) - 1.0d);
        return ((1.0d - (((d4 * d4) * d4) - (d4 * Math.sin(3.141592653589793d * d4)))) * 0.5d) + 0.5d;
    }

    double s(double d2) {
        return 1.0d - t(1.0d - d2);
    }

    double t(double d2) {
        return d2 < 0.36363636363636365d ? ((121.0d * d2) * d2) / 16.0d : d2 < 0.7272727272727273d ? (((9.075d * d2) * d2) - (9.9d * d2)) + 3.4d : d2 < 0.9d ? (((12.066481994459833d * d2) * d2) - (19.63545706371191d * d2)) + 8.898060941828255d : (((10.8d * d2) * d2) - (20.52d * d2)) + 10.72d;
    }

    double u(double d2) {
        return d2 < 0.5d ? s(d2 * 2.0d) * 0.5d : (t((d2 * 2.0d) - 1.0d) * 0.5d) + 0.5d;
    }
}