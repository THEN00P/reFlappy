package org.andengine.input.key.controller;

import org.andengine.engine.handler.IUpdateHandler;

public interface IKeyController extends IUpdateHandler {
    public void setKeyEventCallback(final IKeyEventCallback pKeyEventCallback);

    public void onHandleKeyEvent(final android.view.KeyEvent pKeyEvent);
}
