package com.dotgears;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.AdView;
import com.google.android.gms.games.GamesClient;
import com.google.example.games.basegameutils.GameHelper;
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
import org.andengine.engine.options.resolutionpolicy.BaseResolutionPolicy;
import org.andengine.engine.options.resolutionpolicy.RelativeResolutionPolicy;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.engine.options.resolutionpolicy.FixedResolutionPolicy;
import org.andengine.engine.options.resolutionpolicy.FillResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.opengl.view.RenderSurfaceView;
import org.andengine.ui.activity.BaseGameActivity;
import org.andengine.ui.activity.SimpleBaseGameActivity;

/* loaded from: classes.dex */
public class GameActivity extends SimpleBaseGameActivity implements GameHelper.GameHelperListener {

    /* renamed from: a */
    public AdView adView;

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

    /* renamed from: g */
    protected GameHelper gameHelper;

    /* renamed from: h */
    protected int gameHelperClientCount = 1;

    /* renamed from: i */
    protected String logTag = "BaseGameActivity";

    /* renamed from: j */
    protected boolean debugLog = false;

    /* renamed from: m */
    private Camera camera;

    /* renamed from: n */
    private TextureRegion textureRegion;

    /* renamed from: o */
    private String[] gameHelperScopes;

    @Override // org.andengine.ui.activity.BaseGameActivity
    /* renamed from: a */
    public Engine onCreateEngine(EngineOptions engineOptions) {
        return new LimitedFPSEngine(engineOptions, 60);
    }

    @Override // org.andengine.ui.a
    /* renamed from: a */
    public EngineOptions onCreateEngineOptions() {
//      TODO:  couldn't find a better solution for the upside down stuff so we just flip the camera
        this.camera = new Camera(0.0f, 512.0f, 288.0f, -512.0f);
//        this.camera = new Camera(0.0f, 0.0f, 288.0f, 512.0f);
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
        this.mRenderSurfaceView.setRenderer(this.mEngine, this);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams((ViewGroup.MarginLayoutParams) BaseGameActivity.createSurfaceViewLayoutParams());
        layoutParams2.addRule(13);
        relativeLayout.addView(this.mRenderSurfaceView, layoutParams2);
        FrameLayout frameLayout = new FrameLayout(this);
        this.adView = new AdView(this, AdSize.SMART_BANNER, "a152df006159b75");
        this.adView.refreshDrawableState();
        frameLayout.addView(this.adView, new FrameLayout.LayoutParams(-2, -2, 49));
        relativeLayout.addView(frameLayout);
        this.adView.loadAd(new AdRequest());
        setContentView(relativeLayout, layoutParams);
    }

    @Override
    public void hideAd() {
        runOnUiThread(new GameAdHideRunnable(this));
    }

    /* renamed from: d */
    public void showAd() {
        runOnUiThread(new GameAdShowRunnable(this));
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

    @Override // com.google.example.games.basegameutils.GameHelperListener
    /* renamed from: j */
    public void onSignInFailed() {
    }

    @Override // com.google.example.games.basegameutils.GameHelperListener
    /* renamed from: k */
    public void onSignInSucceeded() {
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

    /* renamed from: n */
    protected GamesClient getGamesClient() {
        return this.gameHelper.getGamesClient();
    }

    /* renamed from: o */
    public boolean isSignedIn() {
        return this.gameHelper.isSignedIn();
    }

    @Override // android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        this.gameHelper.onActivityResult(i, i2, intent);
    }

    @Override // org.andengine.ui.activity.BaseGameActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.gameHelper = new GameHelper(this);
        if (this.debugLog) {
            this.gameHelper.enableDebugLog(this.debugLog, this.logTag);
        }
        this.gameHelper.setup(this, this.gameHelperClientCount, this.gameHelperScopes);
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
        this.gameHelper.onStart(this);
    }

    @Override // android.app.Activity
    protected void onStop() {
        super.onStop();
        this.gameHelper.onStop();
    }

    /* renamed from: p */
    public void beginUserInitiatedSignIn() {
        this.gameHelper.beginUserInitiatedSignIn();
    }
}