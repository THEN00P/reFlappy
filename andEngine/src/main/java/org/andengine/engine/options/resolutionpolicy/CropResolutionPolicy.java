package org.andengine.engine.options.resolutionpolicy;

import android.view.View;
import org.andengine.opengl.view.RenderSurfaceView;

/**
 * Based on CroppedResolutionPolicy by jgibbs, added methods to get corners coordinates
 * https://web.archive.org/web/20140415202340/http://www.andengine.org/forums/gles2/targeting-multiple-display-resolutions-t6794.html
 *
 * @author jgibbs, Martin Varga
 *
 */
public class CropResolutionPolicy extends BaseResolutionPolicy {

    private final float desiredWidth;
    private final float desiredHeight;

    private float userWidth;
    private float userHeight;

    private float left;
    private float right;
    private float top;
    private float bottom;

    public CropResolutionPolicy(float pWidth, float pHeight) {
        desiredWidth = pWidth;
        desiredHeight = pHeight;
    }

    @Override // org.andengine.engine.options.resolutionpolicy.c
    /* renamed from: a */
    public void onMeasure(RenderSurfaceView pRenderSurfaceView, int pWidthMeasureSpec, int pHeightMeasureSpec) {
        float resultHeight;
        float resultWidth;
        float scaleRatio;
        BaseResolutionPolicy.throwOnNotMeasureSpecEXACTLY(pWidthMeasureSpec, pHeightMeasureSpec);
        int measuredWidth = View.MeasureSpec.getSize(pWidthMeasureSpec);
        int measuredHeight = View.MeasureSpec.getSize(pHeightMeasureSpec);
        float desiredRatio = this.desiredWidth / this.desiredHeight;
        if (measuredWidth / measuredHeight < desiredRatio) {
            resultWidth = measuredHeight * desiredRatio;
            resultHeight = measuredHeight;
            scaleRatio = this.desiredHeight / resultHeight;
        } else {
            resultHeight = measuredWidth / desiredRatio;
            resultWidth = measuredWidth;
            scaleRatio = this.desiredWidth / resultWidth;
        }
        this.userWidth = measuredWidth * scaleRatio;
        this.userHeight = scaleRatio * measuredHeight;
        this.left = (this.desiredWidth - this.userWidth) / 2.0f;
        this.right = this.userWidth + this.left;
        this.bottom = (this.desiredHeight - this.userHeight) / 2.0f;
        this.top = this.userHeight + this.bottom;
        pRenderSurfaceView.setMeasuredDimensionProxy((int) resultWidth, (int) resultHeight);
    }

    public float getUserWidth() {
        return userWidth;
    }

    public float getUserHeight() {
        return userHeight;
    }


    public float getLeft() {
        return left;
    }


    public float getRight() {
        return right;
    }


    public float getTop() {
        return top;
    }


    public float getBottom() {
        return bottom;
    }
}
