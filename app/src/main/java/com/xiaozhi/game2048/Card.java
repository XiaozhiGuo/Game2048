package com.xiaozhi.game2048;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by sunshine on 2016/3/2.
 */
public class Card extends FrameLayout {
    private int num;
    private TextView lable;
    public void setNum(int num){
        this.num = num;
        if (num<=0){
            lable.setText("");
        }
        else
            lable.setText(this.num+"");
    }
    public int getNum(){
        return num;
    }
    public boolean equals(Card c){
        return getNum()==c.getNum();
    }
    public Card(Context context) {
        super(context);
        lable = new TextView(getContext());
        lable.setTextSize(32);
        lable.setBackgroundColor(0x33ffffff);
        lable.setGravity(Gravity.CENTER);


        LayoutParams lp = new LayoutParams(-1,-1);//-1表示填充满父容器
        lp.setMargins(10, 10, 0, 0);
        addView(lable,lp);

        setNum(0);
    }

    public Card(Context context, AttributeSet attrs) {
        super(context, attrs);

    }
}
