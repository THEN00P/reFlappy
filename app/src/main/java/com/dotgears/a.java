package com.dotgears;

/* loaded from: classes.dex */
// public class a implements Runnable {
public class GameAdHideRunnable implements Runnable {
    // public GameActivity a;
    public GameActivity gameActivity;

    // public a(GameActivity gameActivity) {
    public GameAdHideRunnable(GameActivity gameActivity) {
        // this.a = gameActivity;
        this.gameActivity = gameActivity;
    }

    @Override // java.lang.Runnable
    public void run() {
        // this.a.a.setVisibility(8);
        this.gameActivity.adView.setVisibility(8);
    }
}