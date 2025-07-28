package com.dotgears;

import androidx.core.splashscreen.SplashScreen;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.dotgears.flappybird.R;
import com.google.android.gms.games.PlayGames;
import com.google.android.gms.games.PlayGamesSdk;

import java.io.IOException;
import org.andengine.audio.sound.Sound;
import org.andengine.audio.sound.SoundFactory;
import org.andengine.engine.Engine;
import org.andengine.engine.LimitedFPSEngine;
import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.WakeLockOptions;
import org.andengine.engine.options.resolutionpolicy.CropResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.opengl.view.RenderSurfaceView;
import org.andengine.ui.activity.BaseGameActivity;
import org.andengine.ui.activity.SimpleBaseGameActivity;

/* loaded from: classes.dex */
public class GameActivity extends SimpleBaseGameActivity {
    /* renamed from: b */
    public Sound soundPoint;

    /* renamed from: c */
    public Sound soundDie;

    /* renamed from: d */
    public Sound soundHit;

    /* renamed from: e */
    public Sound soundSwoosh;

    /* renamed from: f */
    public Sound soundWing;

    /* renamed from: i */
    protected String logTag = "BaseGameActivity";

    /* renamed from: j */
    protected boolean debugLog = false;

    /* renamed from: m */
    private Camera camera;

    /* renamed from: n */
    private TextureRegion textureRegion;

    /* renamed from: o */
    private boolean isSignedIn = false;

    @Override // org.andengine.ui.activity.BaseGameActivity
    /* renamed from: a */
    public Engine onCreateEngine(EngineOptions engineOptions) {
        return new LimitedFPSEngine(engineOptions, 60);
    }

    @Override // org.andengine.ui.a
    /* renamed from: a */
    public EngineOptions onCreateEngineOptions() {
        this.camera = new Camera(0.0f, 0.0f, 288.0f, 512.0f);
        EngineOptions engineOptions = new EngineOptions(true, ScreenOrientation.PORTRAIT_FIXED, new CropResolutionPolicy(288.0f, 512.0f), this.camera);
        engineOptions.getAudioOptions().setNeedsMusic(true).setNeedsSound(true);
        engineOptions.getRenderOptions().setDithering(true);
        engineOptions.getTouchOptions().setNeedsMultiTouch(true);
        engineOptions.setWakeLockOptions(WakeLockOptions.SCREEN_ON);
        return engineOptions;
    }

    @Override // org.andengine.ui.activity.BaseGameActivity
    protected void onSetContentView() {
        RelativeLayout relativeLayout = new RelativeLayout(this);
        ViewGroup.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        this.mRenderSurfaceView = new RenderSurfaceView(this);
		this.mRenderSurfaceView.requestFocus();
        this.mRenderSurfaceView.setRenderer(this.mEngine, this);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams((ViewGroup.MarginLayoutParams) BaseGameActivity.createSurfaceViewLayoutParams());
        layoutParams2.addRule(RelativeLayout.CENTER_IN_PARENT);
        relativeLayout.addView(this.mRenderSurfaceView, layoutParams2);
        FrameLayout frameLayout = new FrameLayout(this);
        relativeLayout.addView(frameLayout);
        setContentView(relativeLayout, layoutParams);
    }

    /* renamed from: e */
    public void playSoundPoint() {
        this.soundPoint.play();
    }

    /* renamed from: f */
    public void playSoundDie() {
        this.soundDie.play();
    }

    /* renamed from: g */
    public void playSoundHit() {
        this.soundHit.play();
    }

    /* renamed from: h */
    public void playSoundSwooshing() {
        this.soundSwoosh.play();
    }

    /* renamed from: i */
    public void playSoundWing() {
        this.soundWing.play();
    }

    @Override // org.andengine.ui.activity.SimpleBaseGameActivity
    /* renamed from: l */
    protected void onCreateResources() {
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
        BitmapTextureAtlas textureAtlas = new BitmapTextureAtlas(getTextureManager(), 1024, 1024, TextureOptions.DEFAULT);
        this.textureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(textureAtlas, this, "atlas.png", 0, 0);
        textureAtlas.load();
        SoundFactory.setAssetBasePath("sounds/");
        try {
            this.soundPoint = SoundFactory.createSoundFromAsset(this.mEngine.getSoundManager(), this, "sfx_point.ogg");
            this.soundDie = SoundFactory.createSoundFromAsset(this.mEngine.getSoundManager(), this, "sfx_die.ogg");
            this.soundHit = SoundFactory.createSoundFromAsset(this.mEngine.getSoundManager(), this, "sfx_hit.ogg");
            this.soundSwoosh = SoundFactory.createSoundFromAsset(this.mEngine.getSoundManager(), this, "sfx_swooshing.ogg");
            this.soundWing = SoundFactory.createSoundFromAsset(this.mEngine.getSoundManager(), this, "sfx_wing.ogg");
        } catch (IOException e) {
        }
    }

    @Override // org.andengine.ui.activity.SimpleBaseGameActivity
    /* renamed from: m */
    protected Scene onCreateScene() {
        return new GameScene(this, this.textureRegion);
    }

    /* renamed from: o */
    public boolean isSignedIn() {
        return isSignedIn;
    }

    @Override // android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
    }

    @Override // org.andengine.ui.activity.BaseGameActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        SplashScreen.installSplashScreen(this);
        super.onCreate(bundle);
		View decorView = getWindow().getDecorView();
		decorView.setSystemUiVisibility(
				View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
						| View.SYSTEM_UI_FLAG_LAYOUT_STABLE
						| View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
						| View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
						| View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
						| View.SYSTEM_UI_FLAG_FULLSCREEN
		);

        PlayGamesSdk.initialize(this);
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
    }

    @Override // android.app.Activity
    protected void onStop() {
        super.onStop();
    }

    /* renamed from: p */
    public void beginUserInitiatedSignIn() {
        PlayGames.getGamesSignInClient(this)
                .signIn()
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful() && task.getResult().isAuthenticated())
                        isSignedIn = true;
                    else
                        new AlertDialog.Builder(this)
                                .setMessage(R.string.sign_in_failed)
                                .setNeutralButton(android.R.string.ok, null)
                                .create()
                                .show();
                });
    }
}