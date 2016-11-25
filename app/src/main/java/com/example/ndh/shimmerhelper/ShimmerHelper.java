package com.example.ndh.shimmerhelper;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ndh on 16/11/25.
 */

public class ShimmerHelper {
    Paint mPaint;
    private LinearGradient mLinearGradient;
    private Matrix mGradientMatrix;
    private int mViewWidth = 0;
    private int mTranslate = 0;
    private boolean mAnimating = true;
    long mDuration = 50;
    View mView;
    int mRepeatCount = -1;

    int[] mColors = new int[]{0x33ffffff, 0xffffffff, 0x33ffffff};
    Shader.TileMode mMode = Shader.TileMode.CLAMP;

    public ShimmerHelper(View v, Paint paint) {
        mView = v;
        mPaint = paint;
    }

    public void setDuration(long duration) {
        //每次只移动10%
        mDuration = duration/10;
    }

    public void setColors(int starColor, int endColor) {
        mColors[0] = starColor;
        mColors[1] = endColor;
        mColors[2] = starColor;
    }

    public void setMode(Shader.TileMode mode) {
        mMode = mode;
    }

    public void start() {
        mView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                init(mView);
                ObjectAnimator animator = ObjectAnimator.ofFloat(mView, "n", 0f, 0.5f, 1f);
                animator.setDuration(mDuration);
                animator.setRepeatCount(mRepeatCount);
                animator.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        start();
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {

                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {
                        start();
                    }

                    private void start() {
                        if (mAnimating && mGradientMatrix != null) {
                            mTranslate += mViewWidth / 10;
                            if (mTranslate > 2 * mViewWidth) {
                                mTranslate = -mViewWidth; // 移动了整个View的距离后 回到原位
                            }
                            mGradientMatrix.setTranslate(mTranslate, 0);
                            mLinearGradient.setLocalMatrix(mGradientMatrix);

                        }
                        mView.postInvalidate();
                    }
                });
                animator.start();
            }
        });


    }

    private void init(View v) {
        if (mViewWidth == 0) {
            mViewWidth = v.getMeasuredWidth();
            if (mViewWidth > 0) {
                mLinearGradient = new LinearGradient(-mViewWidth, 0, 0, 0,
                        mColors,
                        new float[]{0, 0.5f, 1}, mMode);
                mPaint.setShader(mLinearGradient);
                mGradientMatrix = new Matrix();
            }
        }
    }
}
