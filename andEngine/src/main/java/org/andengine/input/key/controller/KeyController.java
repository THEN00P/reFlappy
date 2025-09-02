package org.andengine.input.key.controller;

import org.andengine.input.key.KeyEvent;
import org.andengine.util.adt.pool.RunnablePoolItem;
import org.andengine.util.adt.pool.RunnablePoolUpdateHandler;

public class KeyController implements IKeyController {
    private IKeyEventCallback mKeyEventCallback;

    private final RunnablePoolUpdateHandler<KeyEventRunnablePoolItem> mKeyEventRunnablePoolUpdateHandler = new RunnablePoolUpdateHandler<KeyEventRunnablePoolItem>() {
        @Override
        protected KeyEventRunnablePoolItem onAllocatePoolItem() {
            return new KeyEventRunnablePoolItem();
        }
    };

    // ===========================================================
    // Constructors
    // ===========================================================

    public KeyController() {

    }

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    @Override
    public void onHandleKeyEvent(final android.view.KeyEvent pKeyEvent) {
        this.fireKeyEvent(pKeyEvent.getAction(), pKeyEvent.getKeyCode(), pKeyEvent);
    }

    @Override
    public void setKeyEventCallback(final IKeyEventCallback pKeyEventCallback) {
        this.mKeyEventCallback = pKeyEventCallback;
    }

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    @Override
    public void reset() {
        this.mKeyEventRunnablePoolUpdateHandler.reset();
    }

    @Override
    public void onUpdate(final float pSecondsElapsed) {
        this.mKeyEventRunnablePoolUpdateHandler.onUpdate(pSecondsElapsed);
    }

    protected void fireKeyEvent(final int pAction, final int pKeyCode, final android.view.KeyEvent pKeyEvent) {
        final KeyEvent keyEvent = KeyEvent.obtain(pAction, pKeyCode, pKeyEvent);

        final KeyEventRunnablePoolItem keyEventRunnablePoolItem = this.mKeyEventRunnablePoolUpdateHandler.obtainPoolItem();
        keyEventRunnablePoolItem.set(keyEvent);
        this.mKeyEventRunnablePoolUpdateHandler.postPoolItem(keyEventRunnablePoolItem);
    }

    // ===========================================================
    // Methods
    // ===========================================================

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

    class KeyEventRunnablePoolItem extends RunnablePoolItem {
        // ===========================================================
        // Fields
        // ===========================================================

        private KeyEvent mKeyEvent;

        // ===========================================================
        // Getter & Setter
        // ===========================================================

        public void set(final KeyEvent pKeyEvent) {
            this.mKeyEvent = pKeyEvent;
        }

        // ===========================================================
        // Methods for/from SuperClass/Interfaces
        // ===========================================================

        @Override
        public void run() {
            KeyController.this.mKeyEventCallback.onKeyEvent(this.mKeyEvent);
        }

        @Override
        protected void onRecycle() {
            super.onRecycle();
            final KeyEvent keyEvent = this.mKeyEvent;
            keyEvent.recycle();
        }
    }
}
