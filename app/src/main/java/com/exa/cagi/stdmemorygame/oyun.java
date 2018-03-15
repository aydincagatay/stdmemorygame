package com.exa.cagi.stdmemorygame;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;


public class oyun extends AppCompatActivity {
    int sonKart=0;
    int skor=0;
    int hata=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oyun);
        final Intent i = getIntent();
        final String s = i.getStringExtra("mesaj");
        TextView tv= (TextView) findViewById(R.id.textView);
        tv.setText("Welcome "+s+".") ;
        GridLayout gl = (GridLayout) findViewById(R.id.kartlar);
        kart cards[]= new kart[16];
        for(int j=1;j<=16;j++){
            cards[j-1]=new kart(this,j);
            cards[j-1].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   final kart k=(kart)v;
                    k.cevir();
                        if(sonKart>0){
                            final kart k2=(kart)findViewById(sonKart);
                            if(k2.onPlanId==k.onPlanId && k2.getId()!=k.getId()){
                                //eşleştiler
                                k2.cevrilebilir=false;
                                k.cevrilebilir=false;
                                skor++;
                                TextView tv= (TextView)findViewById(R.id.textView3);
                                tv.setText("Your score = "+skor);
                                if(skor==8){
                                    //oyun bitti skor ekranına git
                                    Intent i = new  Intent(oyun.this,skor.class);
                                    i.putExtra("puan",hata);
                                    i.putExtra("isim",s);
                                    startActivity(i);
                                }
                                sonKart=0;
                            }
                            else{
                                //eşleşmediler geri çevir
                                Handler h = new Handler();
                                h.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        k.cevir();
                                        k2.cevir();
                                    }
                                },600);
                                hata++;
                                TextView tv= (TextView)findViewById(R.id.textView4);
                                tv.setText("Fails = "+hata);
                                sonKart=0;
                            }
                        }
                        else{
                            sonKart=k.getId();
                        }
                }
            });

        }
        for(int j=0;j<16;j++){
            int rg = (int) (Math.random()*16) ;
            kart k= cards[rg];
            cards[rg]=cards[j];
            cards[j]=k;
        }
        for(int j=0;j<16;j++){
            gl.addView(cards[j]);
        }
    }

}
