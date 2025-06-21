package com.dotgears.flappy;

import android.content.Intent;
import com.dotgears.GameActivity;

/* renamed from: com.dotgears.flappy.d */
/* loaded from: classes.dex */
class SplashScreenRunnable implements Runnable {

    /* renamed from: a */
    final /* synthetic */ SplashScreen splashScreen;

    SplashScreenRunnable(SplashScreen splashScreen) {
        this.splashScreen = splashScreen;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.splashScreen.startActivity(new Intent(this.splashScreen, (Class<?>) GameActivity.class));
        this.splashScreen.finish();
    }
}