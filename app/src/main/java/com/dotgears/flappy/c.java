package com.dotgears.flappy;

import com.dotgears.g;
import com.dotgears.h;
import com.dotgears.i;
import com.dotgears.j;
import com.dotgears.l;
import com.dotgears.m;
import java.io.InputStream;

/* loaded from: classes.dex */
public class c extends g {
    boolean H;
    boolean I;
    a J;
    com.dotgears.f K;
    com.dotgears.f L;
    com.dotgears.f M;
    com.dotgears.f N;
    com.dotgears.f O;
    com.dotgears.f P;
    com.dotgears.f Q;
    com.dotgears.f R;
    l S;
    i T;
    i U;
    i V;
    i W;
    i X;
    i Y;
    i Z;
    i aa;
    i ab;
    int ac;
    int ad;
    int ae;
    int af;
    int ag;
    int ah;
    int ai;
    int aj;
    int ak;
    f al;
    e am;
    h an;
    int ao;
    private boolean ap;

    public c(int i, int i2, InputStream inputStream) {
        super(i, i2, inputStream);
        this.ap = true;
    }

    public static boolean a(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        return i + i3 >= i5 && i <= i5 + i7 && i2 + i4 >= i6 && i2 <= i6 + i8;
    }

    @Override // com.dotgears.g
    public void a(int i, int i2) {
        if (this.H) {
            return;
        }
        if (i < this.M.b - 20 || i > this.M.b + this.M.d + 20 || i2 < this.M.c - 20 || i2 > this.M.c + this.M.e + 20) {
            if (!this.J.w) {
                if (this.ao > 0) {
                    this.J.b();
                }
            } else if (this.al.F && this.al.d == 1) {
                this.al.d = 2;
                this.al.a.a(1.0f, 0.0f, 0, 0.5f);
                this.J.w = false;
                this.J.b();
            }
        }
    }

    @Override // com.dotgears.g
    public void a(int i, int i2, int i3, int i4) {
        if (!this.H || i4 < 482 - this.ab.c || i4 > 482) {
            return;
        }
        a(7, (m) null, 5);
    }

    @Override // com.dotgears.g
    public void a(int i, m mVar) {
        switch (i) {
            case com.google.android.gms.e.MapAttrs_cameraTargetLat /* 2 */:
                this.I = true;
                c(0, this.y);
                this.am.a();
                c(8, 0);
                break;
            case com.google.android.gms.e.MapAttrs_cameraTargetLng /* 3 */:
                c(11, 0);
                break;
            case com.google.android.gms.e.MapAttrs_cameraTilt /* 4 */:
                c(13, 0);
                break;
            case com.google.android.gms.e.MapAttrs_cameraZoom /* 5 */:
                e();
                this.K.F = false;
                this.P.F = false;
                this.R.F = false;
                this.H = false;
                a(false, 0, 0.5f);
                this.ac = 0;
                this.J.a();
                this.ao = 2;
                this.ak = 1;
                this.y = 0;
                this.al.a();
                this.A++;
                c(7, 0);
                break;
            case com.google.android.gms.e.MapAttrs_uiCompass /* 6 */:
                e();
                a(false, 0, 0.5f);
                c(6, 0);
                break;
            case com.google.android.gms.e.MapAttrs_uiRotateGestures /* 7 */:
                c(3, 0);
                break;
        }
    }

    @Override // com.dotgears.g
    public void b(float f) {
        a(this.T, 0, 0, 1.0f);
        this.ac -= this.ao;
        if (this.ac <= -24) {
            this.ac = 0;
        }
        if (!this.J.w) {
            this.ag -= this.ao;
            this.ah -= this.ao;
            this.ai -= this.ao;
            if (this.ao > 0 && this.ak <= 0 && (this.ag == this.J.b || this.ag == this.J.b - 1)) {
                this.y++;
                c(9, 0);
            }
            if (this.ag < (-this.Y.b)) {
                this.ag = this.ah;
                this.ad = this.ae;
                this.ah = this.ai;
                this.ae = this.af;
                this.ai = this.ah + this.aj + this.Y.b;
                this.af = j.a(180, 360);
                if (this.ak > 0) {
                    this.ak--;
                    if (this.ak == 0) {
                        this.ah = -this.Y.b;
                        this.ag = -this.Y.b;
                    }
                }
            }
        }
        this.J.a(f);
        if (this.H) {
            a(this.aa, (288 - this.aa.b) >> 1, 150, 1.0f);
            this.J.b = (288 - this.J.e) >> 1;
            this.J.c = this.aa.c + 150 + 20;
            this.J.a(this);
            a(this.X, this.ac, 512 - this.X.c, 1.0f);
        } else {
            if (this.J.c >= 400 - this.J.f && this.ao > 0) {
                c(1.0f);
                a(4, 0.5f);
                this.ao = 0;
                c(12, 0);
                a(2, this.C, 1000);
            }
            if (!this.J.v && this.ak <= 0 && this.ao > 0) {
                if (a(this.J.b, this.J.c, this.J.e, this.J.f, this.ag, (this.ad - this.Z.c) - 96, this.Z.b, this.Z.c)) {
                    c(1.0f);
                    a(4, 0.5f);
                    this.ao = 0;
                    c(12, 0);
                    a(3, (m) null, 500);
                    a(2, this.C, 1000);
                } else if (a(this.J.b, this.J.c, this.J.e, this.J.f, this.ag, this.ad, this.Y.b, this.Y.c)) {
                    c(1.0f);
                    a(4, 0.5f);
                    this.J.v = true;
                    this.ao = 0;
                    c(12, 0);
                    a(3, (m) null, 500);
                    a(2, this.C, 1000);
                }
                if (a(this.J.b, this.J.c, this.J.e, this.J.f, this.ah, (this.ae - this.Z.c) - 96, this.Z.b, this.Z.c)) {
                    c(1.0f);
                    a(4, 0.5f);
                    this.J.v = true;
                    this.ao = 0;
                    c(12, 0);
                    a(3, (m) null, 500);
                    a(2, this.C, 1000);
                } else if (a(this.J.b, this.J.c, this.J.e, this.J.f, this.ah, this.ae, this.Y.b, this.Y.c)) {
                    c(1.0f);
                    a(4, 0.5f);
                    this.J.v = true;
                    this.ao = 0;
                    c(12, 0);
                    a(3, (m) null, 500);
                    a(2, this.C, 1000);
                }
            }
            if (this.ak <= 0) {
                a(this.Y, this.ag, this.ad, 1.0f);
                a(this.Z, this.ag, (this.ad - this.Z.c) - 96, 1.0f);
                a(this.Y, this.ah, this.ae, 1.0f);
                a(this.Z, this.ah, (this.ae - this.Z.c) - 96, 1.0f);
                if (this.ai < 288) {
                    a(this.Y, this.ai, this.af, 1.0f);
                    a(this.Z, this.ai, (this.af - this.Z.c) - 96, 1.0f);
                }
            }
            if (this.C.F && this.C.k == 2 && !this.K.F) {
                this.K.a((288 - ((this.K.d + this.P.d) + 16)) >> 1, 340);
                this.P.a(this.K.b + this.K.d + 16, 340);
            }
            if (this.am.F) {
                this.am.a(f);
                this.am.a(this);
            } else {
                this.an.a(144, 100, 6, 1.0f);
                this.an.a(this.y, 20);
                this.an.a(this);
            }
            this.J.a(this);
            a(this.X, this.ac, 512 - this.X.c, 1.0f);
        }
        if (this.al.F) {
            this.al.a(f);
            this.al.a(this);
        }
        if (this.H) {
            a(this.ab, (288 - this.ab.b) >> 1, 432 - this.ab.c, 1.0f);
        }
        if (this.K.F) {
            this.K.a(f);
            this.K.a(this);
            this.P.a(f);
            this.P.a(this);
            if (this.K.h) {
                a(true, 5, 0.5f);
                c(10, 0);
            }
            if (this.P.h) {
                c(1, 0);
                c(10, 0);
            }
            if (this.R.F) {
                this.R.a(f);
                this.R.a(this);
                if (this.R.h) {
                    c(2, 0);
                }
            }
        }
    }

    @Override // com.dotgears.g
    public void c() {
        this.an = new b();
        this.K = new com.dotgears.f();
        this.K.a("button_play");
        this.P = new com.dotgears.f();
        this.P.a("button_score");
        this.L = new com.dotgears.f();
        this.L.a("button_ok");
        this.O = new com.dotgears.f();
        this.O.a("button_menu");
        this.M = new com.dotgears.f();
        this.M.a("button_pause");
        this.N = new com.dotgears.f();
        this.N.a("button_resume");
        this.Q = new com.dotgears.f();
        this.Q.a("button_share");
        this.R = new com.dotgears.f();
        this.R.a("button_rate");
        this.S = new l("number_score");
        this.U = b("bg_day");
        this.V = b("bg_night");
        this.W = b("bg_forest");
        this.X = b("land");
        this.Y = b("pipe_up");
        this.Z = b("pipe_down");
        this.aa = b("title");
        this.ab = b("brand_copyright");
        this.aj = (288 - ((this.Y.b * 3) / 2)) / 2;
        this.ag = this.aj - (this.Y.b >> 1);
        this.ad = 274;
        this.ah = this.ag + this.aj + this.Y.b;
        this.ae = 274;
        this.ai = this.ah + this.aj + this.Y.b;
        this.af = 274;
        this.J = new a();
        this.J.a();
        this.al = new f();
        this.am = new e();
        this.H = true;
        a(6, this, 1);
    }

    public void e() {
        if (j.a() % 10 > 3) {
            this.T = this.U;
        } else {
            this.T = this.V;
        }
        this.n.b();
        this.K.a((288 - ((this.K.d + this.P.d) + 16)) >> 1, 340);
        this.P.a(this.K.b + this.K.d + 16, 340);
        this.R.a((288 - this.R.d) >> 1, 270);
        this.al.F = false;
        this.am.F = false;
        this.C.F = false;
        this.C.G = false;
        this.L.F = false;
        this.L.G = false;
        this.O.F = false;
        this.O.G = false;
        this.M.F = false;
        this.M.G = false;
        this.N.F = false;
        this.N.G = false;
        this.Q.F = false;
        this.Q.G = false;
        this.H = true;
        this.ac = 0;
        this.J.a();
        this.ao = 2;
        this.ak = 1;
        this.y = 0;
    }
}