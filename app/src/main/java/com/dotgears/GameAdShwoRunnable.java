package com.dotgears;

/* loaded from: classes.dex */
// public class b implements Runnable {
public class GameAdShowRunnable implements Runnable {
    // public GameActivity a;
    public GameActivity gameActivity;

    // public b(GameActivity gameActivity) {
    public GameAdShowRunnable(GameActivity gameActivity) {
        // this.a = gameActivity;
        this.gameActivity = gameActivity;
    }

    @Override // java.lang.Runnable
    public void run() {
        // this.a.a.setVisibility(0);
        this.gameActivity.adView.setVisibility(0);
    }
}