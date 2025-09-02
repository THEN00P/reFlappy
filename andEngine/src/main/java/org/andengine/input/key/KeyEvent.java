package org.andengine.input.key;

import org.andengine.util.adt.pool.GenericPool;

public class KeyEvent {
    public static final int ACTION_DOWN = android.view.KeyEvent.ACTION_DOWN;
    public static final int ACTION_UP = android.view.KeyEvent.ACTION_UP;

    private static final KeyEventPool EVENT_POOL = new KeyEventPool();

    protected int mKeyCode;

    protected int mAction;

    protected android.view.KeyEvent mKeyEvent;

    public static KeyEvent obtain(final int pAction, final int pKeyCode, final android.view.KeyEvent pKeyEvent) {
        final KeyEvent touchEvent = EVENT_POOL.obtainPoolItem();
        touchEvent.set(pAction, pKeyCode, pKeyEvent);
        return touchEvent;
    }

    private void set(final int pAction, final int pKeyCode, final android.view.KeyEvent pKeyEvent) {
        this.mAction = pAction;
        this.mKeyCode = pKeyCode;
        this.mKeyEvent = pKeyEvent;
    }

    public void recycle() {
        EVENT_POOL.recyclePoolItem(this);
    }

    public static void recycle(final KeyEvent pKeyEvent) {
        EVENT_POOL.recyclePoolItem(pKeyEvent);
    }

    public int getKeyCode() {
        return this.mKeyCode;
    }

    public int getAction() {
        return this.mAction;
    }

    public boolean isActionDown() {
        return this.mAction == KeyEvent.ACTION_DOWN;
    }

    public boolean isActionUp() {
        return this.mAction == KeyEvent.ACTION_UP;
    }

    public android.view.KeyEvent getKeyEvent() {
        return this.mKeyEvent;
    }

    private static final class KeyEventPool extends GenericPool<KeyEvent> {
        @Override
        protected KeyEvent onAllocatePoolItem() {
            return new KeyEvent();
        }
    }
}
