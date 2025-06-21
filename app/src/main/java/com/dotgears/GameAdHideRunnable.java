package com.dotgears;

/* renamed from: com.dotgears.a */
/* loaded from: classes.dex */
class GameAdHideRunnable implements Runnable {

    /* renamed from: a */
    final /* synthetic */ GameActivity gameActivity;

    GameAdHideRunnable(GameActivity gameActivity) {
        this.gameActivity = gameActivity;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.gameActivity.adView.setVisibility(4);
    }
}