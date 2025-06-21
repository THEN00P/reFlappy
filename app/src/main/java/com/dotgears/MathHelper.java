package com.dotgears;

import android.util.Log;

/* renamed from: com.dotgears.j */
/* loaded from: classes.dex */
public class MathHelper {

    /* renamed from: A */
    public static float rotatedX;

    /* renamed from: B */
    public static float rotatedY;

    /* renamed from: a */
    public static float[] sinTable;

    /* renamed from: b */
    public static float[] cosTable;

    /* renamed from: c */
    public static float[] quadraticEaseIn;

    /* renamed from: d */
    public static float[] quadraticEaseOut;

    /* renamed from: e */
    public static float[] quadraticEaseInOut;

    /* renamed from: f */
    public static float[] cubicEaseIn;

    /* renamed from: g */
    public static float[] cubicEaseOut;

    /* renamed from: h */
    public static float[] cubicEaseInOut;

    /* renamed from: i */
    public static float[] quartEaseIn;

    /* renamed from: j */
    public static float[] quartEaseOut;

    /* renamed from: k */
    public static float[] quartEaseInOut;

    /* renamed from: l */
    public static float[] quintEaseIn;

    /* renamed from: m */
    public static float[] quintEaseOut;

    /* renamed from: n */
    public static float[] quintEaseInOut;

    /* renamed from: o */
    public static float[] sineEaseIn;

    /* renamed from: p */
    public static float[] sineEaseOut;

    /* renamed from: q */
    public static float[] sineEaseInOut;

    /* renamed from: r */
    public static float[] bounceEaseIn;

    /* renamed from: s */
    public static float[] bounceEaseOut;

    /* renamed from: t */
    public static float[] bounceEaseInOut;

    /* renamed from: u */
    public static float[] elasticEaseIn;

    /* renamed from: v */
    public static float[] elasticEaseOut;

    /* renamed from: w */
    public static float[] elasticEaseInOut;

    /* renamed from: x */
    public static MathHelper instance;

    /* renamed from: y */
    public static int randomSeed1;

    /* renamed from: z */
    public static int randomSeed2;

    MathHelper() {
        sinTable = new float[360];
        cosTable = new float[360];
        for (int i = 0; i < 360; i++) {
            float f = (i * 3.1415925f) / 180.0f;
            sinTable[i] = (float) Math.sin(f);
            cosTable[i] = (float) Math.cos(f);
        }
        quadraticEaseIn = new float[101];
        quadraticEaseOut = new float[101];
        quadraticEaseInOut = new float[101];
        cubicEaseIn = new float[101];
        cubicEaseOut = new float[101];
        cubicEaseInOut = new float[101];
        quartEaseIn = new float[101];
        quartEaseOut = new float[101];
        quartEaseInOut = new float[101];
        quintEaseIn = new float[101];
        quintEaseOut = new float[101];
        quintEaseInOut = new float[101];
        sineEaseIn = new float[101];
        sineEaseOut = new float[101];
        sineEaseInOut = new float[101];
        bounceEaseIn = new float[101];
        bounceEaseOut = new float[101];
        bounceEaseInOut = new float[101];
        elasticEaseIn = new float[101];
        elasticEaseOut = new float[101];
        elasticEaseInOut = new float[101];
        for (int i2 = 0; i2 <= 100; i2++) {
            double d = i2 / 100.0d;
            quadraticEaseIn[i2] = (float) calculateQuadraticEaseIn(d);
            quadraticEaseOut[i2] = (float) calculateQuadraticEaseOut(d);
            quadraticEaseInOut[i2] = (float) calculateQuadraticEaseInOut(d);
            cubicEaseIn[i2] = (float) calculateCubicEaseIn(d);
            cubicEaseOut[i2] = (float) calculateCubicEaseOut(d);
            cubicEaseInOut[i2] = (float) calculateCubicEaseInOut(d);
            quartEaseIn[i2] = (float) calculateQuartEaseIn(d);
            quartEaseOut[i2] = (float) calculateQuartEaseOut(d);
            quartEaseInOut[i2] = (float) calculateQuartEaseInOut(d);
            quintEaseIn[i2] = (float) calculateQuintEaseIn(d);
            quintEaseOut[i2] = (float) calculateQuintEaseOut(d);
            quintEaseInOut[i2] = (float) calculateQuintEaseInOut(d);
            sineEaseIn[i2] = (float) calculateSineEaseIn(d);
            sineEaseOut[i2] = (float) calculateSineEaseOut(d);
            sineEaseInOut[i2] = (float) calculateSineEaseInOut(d);
            bounceEaseIn[i2] = (float) calculateBounceEaseIn(d);
            bounceEaseOut[i2] = (float) calculateBounceEaseOut(d);
            bounceEaseInOut[i2] = (float) calculateBounceEaseInOut(d);
            elasticEaseIn[i2] = (float) calculateElasticEaseIn(d);
            elasticEaseOut[i2] = (float) calculateElasticEaseOut(d);
            elasticEaseInOut[i2] = (float) calculateElasticEaseInOut(d);
        }
    }

    /* renamed from: a */
    public static float normalizeAngle(float angle) {
        float normalizedAngle = angle;
        while (normalizedAngle > 360.0f) {
            normalizedAngle -= 360.0f;
        }
        while (normalizedAngle < 0.0f) {
            normalizedAngle += 360.0f;
        }
        return normalizedAngle;
    }

    /* renamed from: a */
    public static int nextRandom() {
        randomSeed2 = (36969 * (randomSeed2 & 65535)) + (randomSeed2 >> 16);
        randomSeed1 = ((randomSeed1 & 65535) * 18000) + (randomSeed1 >> 16);
        return Math.abs((randomSeed2 << 16) + randomSeed1);
    }

    /* renamed from: a */
    public static int randomRange(int min, int max) {
        return (nextRandom() % (max - min)) + min;
    }

    /* renamed from: a */
    public static void rotate(float x, float y, float pivotX, float pivotY, float angle) {
        float dx = x - pivotX;
        float dy = y - pivotY;
        float normalizeAngle = normalizeAngle(angle);
        rotatedX = ((getCos(normalizeAngle) * dx) - (getSin(normalizeAngle) * dy)) + pivotX;
        rotatedY = (dx * getSin(normalizeAngle)) + (dy * getCos(normalizeAngle)) + pivotY;
    }

    /* renamed from: a */
    public static void setSeed(int seed) {
        Log.i("FlappyBird", "Engine: Randomize " + seed);
        randomSeed1 = seed % 32000;
        randomSeed2 = seed % 65535;
    }

    /* renamed from: b */
    public static float getSin(float angle) {
        return sinTable[(int) angle];
    }

    /* renamed from: b */
    public static float getQuadraticEaseOut(int percent) {
        return quartEaseIn[percent];
    }

    /* renamed from: c */
    static double calculateQuadraticEaseInOut(double d) {
        return d < 0.5d ? 2.0d * d * d : ((((-2.0d) * d) * d) + (4.0d * d)) - 1.0d;
    }

    /* renamed from: c */
    public static float getCos(float percent) {
        return cosTable[(int) percent];
    }

    /* renamed from: c */
    public static float getQuadraticEaseOut2(int percent) {
        return quadraticEaseOut[percent];
    }

    /* renamed from: d */
    static double calculateCubicEaseIn(double d) {
        return d * d * d;
    }

    /* renamed from: d */
    public static float getQuadraticEaseInOut(int percent) {
        return quadraticEaseInOut[percent];
    }

    /* renamed from: e */
    static double calculateCubicEaseOut(double d) {
        double d2 = d - 1.0d;
        return (d2 * d2 * d2) + 1.0d;
    }

    /* renamed from: e */
    public static float getCubicEaseIn(int percent) {
        return cubicEaseIn[percent];
    }

    /* renamed from: f */
    static double calculateCubicEaseInOut(double d) {
        if (d < 0.5d) {
            return 4.0d * d * d * d;
        }
        double d2 = (2.0d * d) - 2.0d;
        return (d2 * 0.5d * d2 * d2) + 1.0d;
    }

    /* renamed from: f */
    public static float getCubicEaseOut(int percent) {
        return cubicEaseOut[percent];
    }

    /* renamed from: g */
    static double calculateQuartEaseIn(double d) {
        return d * d * d * d;
    }

    /* renamed from: g */
    public static float getCubicEaseInOut(int percent) {
        return cubicEaseInOut[percent];
    }

    /* renamed from: h */
    static double calculateQuartEaseOut(double d) {
        double d2 = d - 1.0d;
        return (d2 * d2 * d2 * (1.0d - d)) + 1.0d;
    }

    /* renamed from: h */
    public static float getQuartEaseIn(int percent) {
        return quartEaseIn[percent];
    }

    /* renamed from: i */
    static double calculateQuartEaseInOut(double d) {
        if (d < 0.5d) {
            return 8.0d * d * d * d * d;
        }
        double d2 = d - 1.0d;
        return (d2 * (-8.0d) * d2 * d2 * d2) + 1.0d;
    }

    /* renamed from: i */
    public static float getQuartEaseOut(int percent) {
        return quartEaseOut[percent];
    }

    /* renamed from: j */
    static double calculateQuintEaseIn(double d) {
        return d * d * d * d * d;
    }

    /* renamed from: j */
    public static float getQuartEaseInOut(int percent) {
        return quartEaseInOut[percent];
    }

    /* renamed from: k */
    static double calculateQuintEaseOut(double d) {
        double d2 = d - 1.0d;
        return (d2 * d2 * d2 * d2 * d2) + 1.0d;
    }

    /* renamed from: k */
    public static float getQuintEaseIn(int percent) {
        return quintEaseIn[percent];
    }

    /* renamed from: l */
    static double calculateQuintEaseInOut(double d) {
        if (d < 0.5d) {
            return 16.0d * d * d * d * d * d;
        }
        double d2 = (2.0d * d) - 2.0d;
        return (d2 * 0.5d * d2 * d2 * d2 * d2) + 1.0d;
    }

    /* renamed from: l */
    public static float getQuintEaseOut(int percent) {
        return quintEaseOut[percent];
    }

    /* renamed from: m */
    public static float getQuintEaseInOut(int percent) {
        return quintEaseInOut[percent];
    }

    /* renamed from: n */
    public static float getSineEaseIn(int percent) {
        return sineEaseIn[percent];
    }

    /* renamed from: o */
    public static float getSineEaseOut(int percent) {
        return sineEaseOut[percent];
    }

    /* renamed from: p */
    public static float getSineEaseInOut(int percent) {
        return sineEaseInOut[percent];
    }

    /* renamed from: q */
    public static float getSineEaseIn2(int percent) {
        return sineEaseIn[percent];
    }

    /* renamed from: r */
    public static float getSineEaseOut2(int percent) {
        return sineEaseOut[percent];
    }

    /* renamed from: s */
    public static float getSineEaseInOut2(int percent) {
        return sineEaseInOut[percent];
    }

    /* renamed from: t */
    public static float getElasticEaseIn(int percent) {
        return elasticEaseIn[percent];
    }

    /* renamed from: u */
    public static float getElasticEaseOut(int percent) {
        return elasticEaseOut[percent];
    }

    /* renamed from: v */
    public static float getElasticEaseInOut(int percent) {
        return elasticEaseInOut[percent];
    }

    /* renamed from: a */
    double calculateQuadraticEaseIn(double d) {
        return d * d;
    }

    /* renamed from: b */
    double calculateQuadraticEaseOut(double d) {
        return -((d - 2.0d) * d);
    }

    /* renamed from: m */
    double calculateSineEaseIn(double d) {
        return Math.sin(20.420352248333657d * d) * Math.pow(2.0d, 10.0d * (d - 1.0d));
    }

    /* renamed from: n */
    double calculateSineEaseOut(double d) {
        return (Math.sin((-20.420352248333657d) * (d + 1.0d)) * Math.pow(2.0d, (-10.0d) * d)) + 1.0d;
    }

    /* renamed from: o */
    double calculateSineEaseInOut(double d) {
        return d < 0.5d ? Math.sin(20.420352248333657d * 2.0d * d) * 0.5d * Math.pow(2.0d, 10.0d * ((2.0d * d) - 1.0d)) : ((Math.sin((-20.420352248333657d) * (((2.0d * d) - 1.0d) + 1.0d)) * Math.pow(2.0d, (-10.0d) * ((2.0d * d) - 1.0d))) + 2.0d) * 0.5d;
    }

    /* renamed from: p */
    double calculateBounceEaseIn(double d) {
        return ((d * d) * d) - (Math.sin(3.141592653589793d * d) * d);
    }

    /* renamed from: q */
    double calculateBounceEaseOut(double d) {
        double d2 = 1.0d - d;
        return 1.0d - (((d2 * d2) * d2) - (d2 * Math.sin(3.141592653589793d * d2)));
    }

    /* renamed from: r */
    double calculateBounceEaseInOut(double d) {
        if (d < 0.5d) {
            double d2 = 2.0d * d;
            return (((d2 * d2) * d2) - (d2 * Math.sin(3.141592653589793d * d2))) * 0.5d;
        }
        double d3 = 1.0d - ((2.0d * d) - 1.0d);
        return ((1.0d - (((d3 * d3) * d3) - (d3 * Math.sin(3.141592653589793d * d3)))) * 0.5d) + 0.5d;
    }

    /* renamed from: s */
    double calculateElasticEaseIn(double d) {
        return 1.0d - calculateElasticEaseOut(1.0d - d);
    }

    /* renamed from: t */
    double calculateElasticEaseOut(double d) {
        return d < 0.36363636363636365d ? ((121.0d * d) * d) / 16.0d : d < 0.7272727272727273d ? (((9.075d * d) * d) - (9.9d * d)) + 3.4d : d < 0.9d ? (((12.066481994459833d * d) * d) - (19.63545706371191d * d)) + 8.898060941828255d : (((10.8d * d) * d) - (20.52d * d)) + 10.72d;
    }

    /* renamed from: u */
    double calculateElasticEaseInOut(double d) {
        return d < 0.5d ? calculateElasticEaseIn(d * 2.0d) * 0.5d : (calculateElasticEaseOut((d * 2.0d) - 1.0d) * 0.5d) + 0.5d;
    }
}