package com.fia.mia.expandlistviewdemo.viewer;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.LinearLayout;

import com.fia.mia.expandlistviewdemo.MyApplication;
import com.fia.mia.expandlistviewdemo.R;

/**
 * Created by milla.wang on 15/10/2.
 */
public class ExpandAnimation extends Animation {
    private View mAnimatedView;
    private View mAnimatedView2;
    private int tagetHeight;
    private boolean isExpend;

    /**
     * Initialize the animation
     *
     * @param view
     *            The layout we want to animate
     * @param duration
     *            The duration of the animation, in ms
     */
    public ExpandAnimation(View view, View view2, int duration, boolean wasEndedAlready) {
        mAnimatedView = view;
        mAnimatedView2 = view2;
        if (mAnimatedView.getVisibility() == View.VISIBLE) {
            tagetHeight = mAnimatedView.getMeasuredHeight();
            isExpend = false;
        } else {
            //這裡算出來的只有單行，要再想一下怎麼measure textview內容長度有換行的高度。
            mAnimatedView.measure(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            tagetHeight = mAnimatedView.getMeasuredHeight();
            mAnimatedView.getLayoutParams().height = 0;
            mAnimatedView.setVisibility(View.VISIBLE);
            isExpend = true;
        }
        Log.d("","tagetHeight: "+tagetHeight);
        setDuration(duration);
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);
        if (isExpend) {
            mAnimatedView.getLayoutParams().height = interpolatedTime == 1 ? LinearLayout.LayoutParams.WRAP_CONTENT : (int) (tagetHeight * interpolatedTime);
            mAnimatedView.requestLayout();
            if (interpolatedTime == 1) {
                mAnimatedView2.setBackgroundResource(R.drawable.arrow_listup);
            }
        } else {
            if (interpolatedTime == 1) {
                mAnimatedView2.setBackgroundResource(R.drawable.arrow_listdn);
                mAnimatedView.setVisibility(View.GONE);
            } else {
                mAnimatedView.getLayoutParams().height = tagetHeight - (int) (tagetHeight * interpolatedTime);
                mAnimatedView.requestLayout();
            }
        }
    }

    @Override
    public boolean willChangeBounds() {
        return true;
    }
}
