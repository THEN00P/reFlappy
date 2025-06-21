package com.dotgears;

/* renamed from: com.dotgears.b */
/* loaded from: classes.dex */
class GameAdShowRunnable implements Runnable {

    /* renamed from: a */
    final /* synthetic */ GameActivity gameActivity;

    GameAdShowRunnable(GameActivity gameActivity) {
        this.gameActivity = gameActivity;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.gameActivity.adView.setVisibility(0);
    }
}