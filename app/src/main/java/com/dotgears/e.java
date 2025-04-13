package com.dotgears;

/* loaded from: classes.dex */
public class e extends q {
    public e() {
        a("blink", 10, 10, 4, 4);
        a(0, "blink", new int[]{0, 1, 2, 1, 0}, 5, 10, false);
        this.m = false;
        this.n = false;
        a(0, true);
    }

    @Override // com.dotgears.q, com.dotgears.m
    public void a(float f) {
        if (this.m) {
            super.a(f);
            if (this.j == null || !this.j.a) {
                return;
            }
            this.m = false;
            this.n = false;
        }
    }

    @Override // com.dotgears.q
    public void a(int i, int i2) {
        super.a(i, i2);
        a(0, true);
    }

    @Override // com.dotgears.q, com.dotgears.m
    public void a(g gVar) {
        if (this.n) {
            super.a(gVar);
        }
    }
}