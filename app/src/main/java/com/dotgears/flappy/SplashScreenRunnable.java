package com.dotgears.flappy;

import android.content.Intent;
import com.dotgears.GameActivity;

/* loaded from: classes.dex */
// class d implements Runnable {
class SplashScreenRunnable implements Runnable {
    // final /* synthetic */ SplashScreen a;
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