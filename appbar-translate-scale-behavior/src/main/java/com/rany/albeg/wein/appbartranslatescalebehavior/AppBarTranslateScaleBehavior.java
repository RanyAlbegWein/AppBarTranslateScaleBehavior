package com.rany.albeg.wein.appbartranslatescalebehavior;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;

public class AppBarTranslateScaleBehavior extends CoordinatorLayout.Behavior<View> {
    /**
     * Destination-view resource id.
     */
    private int mDestResId = View.NO_ID;
    /**
     * The visual x position of the source-view, in pixels.
     */
    private float mSrcStartX;
    /**
     * The visual y position of the source-view, in pixels.
     */
    private float mSrcStartY;
    /**
     * The width of the source-view, in pixels.
     */
    private float mSrcStartWidth;
    /**
     * The height of the source-view, in pixels.
     */
    private float mSrcStartHeight;
    /**
     * The ratio between the total dependency travel range and the difference between
     * source-view x position and destination-view x position.
     * (x0-x1) / totalDepTravelRange
     */
    private float mRatioMoveX;
    /**
     * The ratio between the total dependency travel range and the difference between
     * source-view y position and destination-view y position.
     * (x0-x1) / totalDepTravelRange
     */
    private float mRatioMoveY;
    /**
     * The ratio between the total dependency travel range and the difference between
     * source-view width and destination-view width.
     */
    private float mRatioScaleW;
    /**
     * The ratio between the total dependency travel range and the difference between
     * source-view height and destination-view height.
     */
    private float mRatioScaleH;
    /**
     * Initial setup of transformation values is completed.
     */
    private boolean mInit;

    public AppBarTranslateScaleBehavior(Context context, AttributeSet attrs) {

        if (attrs != null) {
            applyAttributes(context, attrs);
        }
    }

    private void applyAttributes(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.AppBarTranslateScaleBehavior);
        mDestResId = typedArray.getResourceId(R.styleable.AppBarTranslateScaleBehavior_behavior_destination_id, View.NO_ID);
        typedArray.recycle();

        if (mDestResId == View.NO_ID) {
            throw new IllegalStateException("Invalid id for destination view " + mDestResId);
        }
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return dependency instanceof AppBarLayout;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {

        if (!mInit) {
            init(parent, child, (AppBarLayout) dependency);
            mInit = true;
        }

        float currAppBarYPos = dependency.getY();

        CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams) child.getLayoutParams();

        /*Scale*/
        lp.width = (int) (mSrcStartWidth + currAppBarYPos * mRatioScaleW);
        lp.height = (int) (mSrcStartHeight + currAppBarYPos * mRatioScaleH);
        child.setLayoutParams(lp);

        /*Translate*/
        child.setX(mSrcStartX + currAppBarYPos * mRatioMoveX);
        child.setY(mSrcStartY + currAppBarYPos * mRatioMoveY);

        return true;
    }

    private void init(CoordinatorLayout parent, View child, AppBarLayout dependency) {

        float totalDepTravelRange = dependency.getTotalScrollRange();

        mSrcStartX = child.getX();
        mSrcStartY = child.getY();
        mSrcStartWidth = child.getWidth();
        mSrcStartHeight = child.getHeight();

        View destView = parent.findViewById(mDestResId);

        float endX = destView.getX();
        float endY = destView.getY();
        float endWidth = destView.getWidth();
        float endHeight = destView.getHeight();

        /*endX and endY should be relative to CoordinatorLayout*/
        while (destView != parent) {
            destView = (View) destView.getParent();
            endX += destView.getX();
            endY += destView.getY();
        }

        mRatioMoveX = (mSrcStartX - endX) / totalDepTravelRange;
        mRatioMoveY = (mSrcStartY - endY) / totalDepTravelRange;
        mRatioScaleW = (mSrcStartWidth - endWidth) / totalDepTravelRange;
        mRatioScaleH = (mSrcStartHeight - endHeight) / totalDepTravelRange;
    }
}
