package com.dotgears.flappy;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import com.dotgears.flappybird.R;

/* loaded from: classes.dex */
public class SplashScreen extends Activity {

    /* renamed from: a */
    private static int delay = 2000;

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new SplashScreenRunnable(this), delay);
    }
}