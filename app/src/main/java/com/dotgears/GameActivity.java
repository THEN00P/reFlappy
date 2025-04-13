package com.dotgears;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import com.google.ads.AdView;
import java.io.IOException;
import org.andengine.opengl.view.RenderSurfaceView;
import org.andengine.ui.activity.BaseGameActivity;
import org.andengine.ui.activity.SimpleBaseGameActivity;

/* loaded from: classes.dex */
public class GameActivity extends SimpleBaseGameActivity implements com.google.example.games.basegameutils.b {
    public AdView adView;
    public org.andengine.a.c.a soundPoint;
    public org.andengine.a.c.a soundDie;
    public org.andengine.a.c.a soundHit;
    public org.andengine.a.c.a soundSwooshing;
    public org.andengine.a.c.a soundWing;
    protected com.google.example.games.basegameutils.a gameHelper;
    protected int requestCode = 1;
    protected String logTag = "BaseGameActivity";
    protected boolean debugLog = false;
    private org.andengine.b.a.a camera;
    private org.andengine.opengl.c.c.c textureRegion;
    private String[] scopeArray;

    @Override // org.andengine.ui.activity.BaseGameActivity
    public org.andengine.b.a a(org.andengine.b.c.b bVar) {
        return new org.andengine.b.e(bVar, 60);
    }

    @Override // org.andengine.ui.a
    public org.andengine.b.c.b a() {
        this.camera = new org.andengine.b.a.a(0.0f, 0.0f, 288.0f, 512.0f);
        org.andengine.b.c.b engineOptions = new org.andengine.b.c.b(true, org.andengine.b.c.e.PORTRAIT_FIXED, new org.andengine.b.c.a.b(288.0f, 512.0f), this.camera);
        engineOptions.d().b(true).a(true);
        engineOptions.e().a(true);
        engineOptions.c().a(true);
        engineOptions.a(org.andengine.b.c.h.SCREEN_ON);
        return engineOptions;
    }

    @Override // org.andengine.ui.activity.BaseGameActivity
    protected void b() {
        RelativeLayout relativeLayout = new RelativeLayout(this);
        ViewGroup.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        this.l = new RenderSurfaceView(this);
        this.l.a(this.k, this);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams((ViewGroup.MarginLayoutParams) BaseGameActivity.B());
        layoutParams2.addRule(13);
        relativeLayout.addView(this.l, layoutParams2);
        FrameLayout frameLayout = new FrameLayout(this);
        this.adView = new AdView(this, com.google.ads.g.a, "a152df006159b75");
        this.adView.refreshDrawableState();
        frameLayout.addView(this.adView, new FrameLayout.LayoutParams(-2, -2, 49));
        relativeLayout.addView(frameLayout);
        this.adView.a(new com.google.ads.d());
        setContentView(relativeLayout, layoutParams);
    }

    public void hideAd() {
        runOnUiThread(new GameAdHideRunnable(this));
    }

    public void showAd() {
        runOnUiThread(new GameAdShowRunnable(this));
    }

    public void playSoundPoint() {
        this.soundPoint.d();
    }

    public void playSoundDie() {
        this.soundDie.d();
    }

    public void playSoundHit() {
        this.soundHit.d();
    }

    public void playSoundSwooshing() {
        this.soundSwooshing.d();
    }

    public void playSoundWing() {
        this.soundWing.d();
    }

    @Override // com.google.example.games.basegameutils.b
    public void j() {
    }

    @Override // com.google.example.games.basegameutils.b
    public void k() {
    }

    @Override // org.andengine.ui.activity.SimpleBaseGameActivity
    protected void l() {
        org.andengine.opengl.c.a.a.b.a("gfx/");
        org.andengine.opengl.c.a.a.a aVar = new org.andengine.opengl.c.a.a.a(y(), 1024, 1024, org.andengine.opengl.c.f.i);
        this.textureRegion = org.andengine.opengl.c.a.a.b.a(aVar, this, "atlas.png", 0, 0);
        aVar.i();
        org.andengine.a.c.b.a("sounds/");
        try {
            this.soundPoint = org.andengine.a.c.b.a(this.k.i(), this, "sfx_point.ogg");
            this.soundDie = org.andengine.a.c.b.a(this.k.i(), this, "sfx_die.ogg");
            this.soundHit = org.andengine.a.c.b.a(this.k.i(), this, "sfx_hit.ogg");
            this.soundSwooshing = org.andengine.a.c.b.a(this.k.i(), this, "sfx_swooshing.ogg");
            this.soundWing = org.andengine.a.c.b.a(this.k.i(), this, "sfx_wing.ogg");
        } catch (IOException e) {
        }
    }

    @Override // org.andengine.ui.activity.SimpleBaseGameActivity
    protected org.andengine.c.b.e m() {
        return new GameScene(this, this.textureRegion);
    }

    protected com.google.android.gms.games.c getGamesClient() {
        return this.gameHelper.b();
    }

    public boolean isSignedIn() {
        return this.gameHelper.c();
    }

    @Override // android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        this.gameHelper.a(i, i2, intent);
    }

    @Override // org.andengine.ui.activity.BaseGameActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.gameHelper = new com.google.example.games.basegameutils.a(this);
        if (this.debugLog) {
            this.gameHelper.a(this.debugLog, this.logTag);
        }
        this.gameHelper.a(this, this.requestCode, this.scopeArray);
    }

    @Override // org.andengine.ui.activity.BaseGameActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        System.exit(0);
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return false;
        }
        Log.i("FlappyBird", "Back key pressed.");
        return false;
    }

    @Override // android.app.Activity
    protected void onStart() {
        super.onStart();
        this.gameHelper.a(this);
    }

    @Override // android.app.Activity
    protected void onStop() {
        super.onStop();
        this.gameHelper.d();
    }

    public void beginUserInitiatedSignIn() {
        this.gameHelper.f();
    }
}