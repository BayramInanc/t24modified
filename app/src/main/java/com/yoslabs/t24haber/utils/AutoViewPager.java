package com.yoslabs.t24haber.utils;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

public class AutoViewPager extends ViewPager {

    private boolean isCounterReady = true;

    public AutoViewPager(Context context) {
        super(context);
    }

    public AutoViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
//        postDelayed(mSwither, 3000);
        addOnPageChangeListener(new OnPageChangeListener() {

            private static final float thresholdOffset = 0.5f;
            private boolean scrollStarted, checkDirection;
            private int positionNow = 0;

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (checkDirection) {
                    if (thresholdOffset > positionOffset) {

                        System.out.println("scrolled left " + positionNow + " - " + getCurrentItem());
//                        Log.i("a", "going left");
//                        if(positionNow == 0) {
//                            System.out.println("sona gönder direk");
//                        }
//                        if(positionNow ==  (getAdapter().getCount() - 1)) {
//                            System.out.println("başa gönder direk");
//                        }

                    } else {
                        System.out.println("scrolled right" + positionNow + " - " + getCurrentItem());
                    }
                    checkDirection = false;
                }
            }

            @Override
            public void onPageSelected(int position) {
                positionNow = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (!scrollStarted && state == ViewPager.SCROLL_STATE_DRAGGING) {
                    scrollStarted = true;
                    checkDirection = true;
                } else {
                    scrollStarted = false;
                }
            }

        });
    }

//    private Runnable  mSwither = new Runnable() {
//        @Override
//        public void run() {
//            if(getAdapter() != null) {
//                int count = getCurrentItem();
//                if(count ==  (getAdapter().getCount() - 1)) count = 0;
//                else count++;
//                setCurrentItem(count, true);
//            }
//            postDelayed(this, 3000);
//        }
//    };

//    @Override
//    public boolean onTouchEvent(MotionEvent motionEvent) {
//        switch (motionEvent.getAction()) {
//            case MotionEvent.ACTION_CANCEL:
//            case MotionEvent.ACTION_UP :
//                postDelayed(mSwither, 3000);
//                break;
//            default:
//                removeCallbacks(mSwither);
//                break;
//        }
//        return super.onTouchEvent(motionEvent);
//    }

//    public void stopCounter() {
//        removeCallbacks(mSwither);
//        isCounterReady = false;
//    }
//
//    public void startCounter() {
//        if(!isCounterReady) postDelayed(mSwither, 3000);
//        isCounterReady = true;
//    }

}
