package com.dotgears.flappy;

import android.content.Intent;
import com.dotgears.GameActivity;

/* loaded from: classes.dex */
class d implements Runnable {
    final /* synthetic */ SplashScreen a;

    d(SplashScreen splashScreen) {
        this.a = splashScreen;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.a.startActivity(new Intent(this.a, (Class<?>) GameActivity.class));
        this.a.finish();
    }
}