package com.derekt75.videocamera;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.EditText;
import android.widget.SeekBar;

public class ShutterSetting extends Activity {
    private Long mShutterLong;
    private Long mShutterMax;
    private Long mShutterMin;
    private double mSeekBarScale;
    private Float mShutterSeekBarValue;
    private Long mShutterInverted;

    private static final float TIME_SCALE = 1.0e9f;

    public ShutterSetting (){
        mShutterMin = Long.valueOf(100000000);;
        mShutterMax = Long.valueOf(1000000000);
        mShutterLong = mShutterMin;
        mSeekBarScale = 100.0;
        mShutterSeekBarValue = Float.valueOf(0);
        mShutterInverted = (long)(TIME_SCALE/mShutterLong);
    }

//    public void setShutterEditText(EditText mShutterEditText) {
//        this.mShutterEditText = mShutterEditText;
//    }
//
//    public void setShutterSeekBar(SeekBar mShutterSeekBar) {
//        this.mShutterSeekBar = mShutterSeekBar;
//    }

    public Long getShutterLong() {
        return mShutterLong;
    }

    public Long getShutterInverted() {
        return mShutterInverted;
    }

    public void setShutterInverted(Long shutterInverted) {
        Float nonInvertedShutter;
        double temp;
        nonInvertedShutter = TIME_SCALE/shutterInverted;

        if (nonInvertedShutter > mShutterMax) {
            this.mShutterLong = mShutterMax;
        }
        else if (nonInvertedShutter < mShutterMin) {
            this.mShutterLong = mShutterMin;
        }
        else {
            this.mShutterLong = (long)Math.round(nonInvertedShutter);
        }
        mShutterInverted = (long)Math.round(TIME_SCALE/this.mShutterLong);
        temp = mShutterLong / mShutterMin;
        temp = Math.log(temp);
        temp = temp / Math.log(mShutterMax/mShutterMin);
        if (temp > 1) {
            temp = 1;
        }
        mShutterSeekBarValue = (Float) (float)temp;
    }

    public Long getShutterMax() {
        return mShutterMax;
    }

    public void setShutterMax(Long shutterMax) {
        this.mShutterMax = shutterMax;
    }

    public Long getShutterMin() {
        return mShutterMin;
    }

    public void setShutterMin(Long shutterMin) {
        this.mShutterMin = shutterMin;
    }

    public Integer getShutterSeekBar() {
        return ((Integer) (int) Math.round(mShutterSeekBarValue*mSeekBarScale));
    }

    public void setShutterSeekBar(int shutterSeekBar) {
        double temp;
        this.mShutterSeekBarValue = shutterSeekBar/(float)mSeekBarScale;

        temp = Math.pow((double)mShutterMax/mShutterMin,this.mShutterSeekBarValue);
        temp *= mShutterMin;
        mShutterLong = (long)Math.round(temp);
        mShutterInverted = (long) Math.round(TIME_SCALE/mShutterLong);
    }


}


