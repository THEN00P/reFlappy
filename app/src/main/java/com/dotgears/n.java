package com.dotgears;

/* loaded from: classes.dex */
// public class n {
public class ObjectPool {
    // public m[] a;
    public GameObject[] objects;
    // public int b;
    public int count;
    // public int c;
    public int currentIndex;

    // public n() {
    public ObjectPool() {
        // this.a = new m[10];
        this.objects = new GameObject[10];
        // this.b = 0;
        this.count = 0;
        // this.c = 0;
        this.currentIndex = 0;
    }

    // public void a(m mVar) {
    public void addObject(GameObject gameObject) {
        if (this.count >= this.objects.length) {
            // m[] mVarArr = new m[this.b + 10];
            GameObject[] newArray = new GameObject[this.count + 10];
            for (int i = 0; i < this.count; i++) {
                // mVarArr[i] = this.a[i];
                newArray[i] = this.objects[i];
            }
            // this.a = mVarArr;
            this.objects = newArray;
        }
        // this.a[this.b] = mVar;
        this.objects[this.count] = gameObject;
        // this.b++;
        this.count++;
    }

    // public void a(float f) {
    public void update(float delta) {
        for (int i = 0; i < this.count; i++) {
            // if (this.a[i].a) {
            if (this.objects[i].isActive) {
                // this.a[i].a(f);
                this.objects[i].a(delta);
            }
        }
    }

    // public void a(g gVar) {
    public void draw(GameManager gameManager) {
        for (int i = 0; i < this.count; i++) {
            // if (this.a[i].a && this.a[i].b) {
            if (this.objects[i].isActive && this.objects[i].isVisible) {
                // this.a[i].b(gVar);
                this.objects[i].b(gameManager);
            }
        }
    }

    // public m b() {
    public GameObject getObject(int index) {
        // return this.a[index];
        return this.objects[index];
    }

    // public m c() {
    public GameObject getFreeObject() {
        int i = 0;
        // while (i < this.b) {
        while (i < this.count) {
            // if (!this.a[i].a) {
            if (!this.objects[i].isActive) {
                // return this.a[i];
                return this.objects[i];
            }
            i++;
        }
        // this.c++;
        this.currentIndex++;
        // if (this.c >= this.b) {
        if (this.currentIndex >= this.count) {
            // this.c = 0;
            this.currentIndex = 0;
        }
        // return this.a[this.c];
        return this.objects[this.currentIndex];
    }
}