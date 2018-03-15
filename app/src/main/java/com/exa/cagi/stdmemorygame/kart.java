package com.exa.cagi.stdmemorygame;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatDrawableManager;
import android.widget.Button;

/**
 * Created by CAGI on 5.02.2018.
 */

public class kart extends Button {
    boolean acikMi = false;
    boolean cevrilebilir = true;
    int arkaPlanId;
    Drawable arka;
    Drawable on;
    int onPlanId=0;
    public kart(Context context,int id) {
        super(context);
        setId(id);
        arkaPlanId=R.drawable.a;
        if(id%8==1){ onPlanId = R.drawable.c1; }
        if(id%8==2){ onPlanId = R.drawable.c2; }
        if(id%8==3){ onPlanId = R.drawable.c3; }
        if(id%8==4){ onPlanId = R.drawable.c4; }
        if(id%8==5){ onPlanId = R.drawable.c5; }
        if(id%8==6){ onPlanId = R.drawable.c6; }
        if(id%8==7){ onPlanId = R.drawable.c7; }
        if(id%8==0){ onPlanId = R.drawable.c8; }
        arka = AppCompatDrawableManager.get().getDrawable(context,arkaPlanId);
        on = AppCompatDrawableManager.get().getDrawable(context,onPlanId);
        setBackground(arka);
    }
    public void cevir(){
        if(cevrilebilir) {
            if (!acikMi) {
                setBackground(on);
                acikMi = true;
            } else {
                setBackground(arka);
                acikMi = false;
            }
        }
    }
}
