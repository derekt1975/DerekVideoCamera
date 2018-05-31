package com.derekt75.videocamera;

import android.app.Activity;

public class IsoSetting extends Activity {
    private Integer mIsoInteger;
    private Integer mIsoMax;
    private Integer mIsoMin;
    private double mSeekBarScale;
    private Float mIsoSeekBarValue;

    public IsoSetting(){
        mIsoMin = Integer.valueOf(50);
        mIsoMax = Integer.valueOf(3200);
        mIsoInteger = mIsoMin;
        mSeekBarScale = 100.0;
        mIsoSeekBarValue = Float.valueOf(0);
    }

    public Integer getIsoInteger() {
        return mIsoInteger;
    }

    public void setIsoInteger(Integer isoInteger) {
        double temp;

        if (isoInteger > mIsoMax) {
            this.mIsoInteger = mIsoMax;
        }
        else if (isoInteger < mIsoMin) {
            this.mIsoInteger = mIsoMin;
        }
        else {
            this.mIsoInteger = isoInteger;
        }
        temp = mIsoInteger / mIsoMin;
        temp = Math.log(temp);
        temp = temp / Math.log(mIsoMax/mIsoMin);
        if (temp > 1) {
            temp = 1;
        }
        mIsoSeekBarValue = (Float) (float)temp;
    }

    public Integer getIsoMax() {
        return mIsoMax;
    }

    public void setIsoMax(Integer isoMax) {
        this.mIsoMax = isoMax;
    }

    public Integer getIsoMin() {
        return mIsoMin;
    }

    public void setIsoMin(Integer isoMin) {
        this.mIsoMin = isoMin;
    }

    public Integer getIsoSeekBar() {
        return (Integer) (int) Math.round(mIsoSeekBarValue*mSeekBarScale);
    }

    public void setIsoSeekBar(int isoSeekBar) {
        double temp;
        this.mIsoSeekBarValue = isoSeekBar/(float)mSeekBarScale;

        temp = Math.pow((double)mIsoMax/mIsoMin,this.mIsoSeekBarValue);
        temp *= mIsoMin;
        mIsoInteger = (int)Math.round(temp);
    }


}

