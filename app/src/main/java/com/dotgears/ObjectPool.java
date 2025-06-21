package com.dotgears;

/* renamed from: com.dotgears.n */
/* loaded from: classes.dex */
public class ObjectPool extends GameObject {

    /* renamed from: a */
    public GameObject[] objects = new GameObject[30];

    /* renamed from: b */
    int count;

    /* renamed from: c */
    int currentIndex;

    /* renamed from: a */
    public int getCurrentIndex() {
        return this.currentIndex;
    }

    @Override // com.dotgears.GameObject
    /* renamed from: a */
    public void update(float f) {
        for (int i = 0; i < getCurrentIndex(); i++) {
            this.objects[i].update(f);
        }
    }

    @Override // com.dotgears.GameObject
    /* renamed from: a */
    public void draw(GameManager gameManager) {
        for (int i = 0; i < getCurrentIndex(); i++) {
            this.objects[i].draw(gameManager);
        }
    }

    /* renamed from: a */
    public void addObject(GameObject gameObject) {
        this.objects[this.currentIndex] = gameObject;
        this.count = 0;
        this.currentIndex++;
    }

    /* renamed from: b */
    public void hideAll() {
        for (int i = 0; i < getCurrentIndex(); i++) {
            this.objects[i].isActive = false;
            this.objects[i].isVisible = false;
        }
        this.count = 0;
    }

    /* renamed from: c */
    GameObject getCurrentObject() {
        GameObject gameObject = this.objects[this.count];
        this.count++;
        if (this.count == getCurrentIndex()) {
            this.count = 0;
        }
        return gameObject;
    }
}