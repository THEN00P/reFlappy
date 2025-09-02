package org.andengine.engine.options.resolutionpolicy;

import org.andengine.opengl.view.RenderSurfaceView;

import android.view.View.MeasureSpec;

public class MaxCropResolutionPolicy extends BaseResolutionPolicy {
    private final float mCameraWidth;
    private final float mCameraHeight;

    public MaxCropResolutionPolicy(float pCameraWidth, float pCameraHeight) {
        this.mCameraWidth = pCameraWidth;
        this.mCameraHeight = pCameraHeight;
    }

    @Override
    public void onMeasure(final RenderSurfaceView pRenderSurfaceView, final int pWidthMeasureSpec, final int pHeightMeasureSpec) {
        final int specWidth = MeasureSpec.getSize(pWidthMeasureSpec);
        final int specHeight = MeasureSpec.getSize(pHeightMeasureSpec);

        if (specWidth > specHeight) {
            // Landscape screen -> Ratio policy
            final float cameraAspectRatio = this.mCameraWidth / this.mCameraHeight;
            final float ratioWidth = specHeight * cameraAspectRatio;
            if (ratioWidth > specWidth) {
                 pRenderSurfaceView.setMeasuredDimensionProxy(specWidth, (int) (specWidth / cameraAspectRatio));
            } else {
                 pRenderSurfaceView.setMeasuredDimensionProxy((int) ratioWidth, specHeight);
            }
        } else {
            // Portrait screen
            final float surfaceAspectRatio = (float) specWidth / specHeight;
            final float maxPortraitAspectRatio = 9.0f / 21.0f;

            if (surfaceAspectRatio < maxPortraitAspectRatio) {
                // Screen is "thinner" than 21:9. Lock to 21:9 aspect ratio.
                final int resolvedHeight = specHeight;
                final int resolvedWidth = (int) (resolvedHeight * maxPortraitAspectRatio);
                pRenderSurfaceView.setMeasuredDimensionProxy(resolvedWidth, resolvedHeight);
            } else {
                // Screen is between camera AR and 21:9. Use cropping.
                final float cameraAspectRatio = this.mCameraWidth / this.mCameraHeight;
                final float cropWidth = specHeight * cameraAspectRatio;
                if (cropWidth > specWidth) {
                    pRenderSurfaceView.setMeasuredDimensionProxy((int) cropWidth, specHeight);
                } else {
                    pRenderSurfaceView.setMeasuredDimensionProxy(specWidth, (int) (specWidth / cameraAspectRatio));
                }
            }
        }
    }
}
