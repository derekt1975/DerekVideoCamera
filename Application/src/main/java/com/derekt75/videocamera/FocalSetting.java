package com.derekt75.videocamera;

import android.app.Activity;

public class FocalSetting extends Activity {
    private Float mFocalFloat;
    private Float mFocalMax;
    private Float mFocalMin;
    private double mSeekBarScale;
    private Float mFocalSeekBarValue;

    public FocalSetting(){
        mFocalMin = Float.valueOf(0.01f);
        mFocalMax = Float.valueOf(10f);
        mFocalFloat = mFocalMax;
        mSeekBarScale = 100.0;
        mFocalSeekBarValue = Float.valueOf(0f);
    }

    public Float getFocalFloat() {
        return mFocalFloat;
    }

    public void setFocalFloat(Float focalFloat) {
        float temp;

        if (focalFloat > mFocalMax) {
            this.mFocalFloat = mFocalMax;
        }
        else if (focalFloat < mFocalMin) {
            this.mFocalFloat = mFocalMin;
        }
        else {
            this.mFocalFloat = focalFloat;
        }
        temp = (mFocalMax - focalFloat)/(mFocalMax + focalFloat);

        mFocalSeekBarValue = temp;
    }

    public Float getFocalMax() {
        return mFocalMax;
    }

    public void setFocalMax(Float focalMax) {
        this.mFocalMax = focalMax;
    }

    public Float getFocalMin() {
        return mFocalMin;
    }

    public void setFocalMin(Float focalMin) {
        this.mFocalMin = focalMin;
    }

    public Integer getFocalSeekBar() {
        return (Integer) (int) Math.round(mFocalSeekBarValue *mSeekBarScale);
    }

    public void setFocalSeekBar(int focalSeekBar) {
        float temp;
        mFocalSeekBarValue = focalSeekBar/(float)mSeekBarScale;

        temp = (1-mFocalSeekBarValue)/(1+mFocalSeekBarValue);
        temp *= mFocalMax;
        mFocalFloat = (float)temp;
    }


}

