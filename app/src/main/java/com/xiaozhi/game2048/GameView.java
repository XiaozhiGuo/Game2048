package com.xiaozhi.game2048;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Point;
import android.os.Build;
import android.provider.Settings;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridLayout;
import android.widget.Toast;
import com.xiaozhi.game2048.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunshine on 2016/3/2.
 */
public class GameView extends GridLayout {

    private Card[][]  cards = new Card[4][4];//保存卡片
    private List<Point> emptyPoints = new ArrayList<Point>();

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initGameView(context);
    }

    public GameView(Context context) {
        super(context);
        initGameView(context);
    }

    public GameView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initGameView(context);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        int cardWidth = (Math.min(w,h)-10)/4;
        addCard(cardWidth, cardWidth);
        startGame();
    }
    private void addCard(int cardWidth,int cardHeight){
        Card c;
        //添加卡片
        for (int i = 0;i<4;i++){
            for (int j = 0;j<4;j++){
                c = new Card(getContext());
                c.setNum(100);
                addView(c,cardWidth,cardHeight);
                cards[i][j] = c;
            }
        }

    }
    //开始游戏，向
    private void startGame(){
        for(int i = 0;i<4;i++){//将卡片全部清空
            for (int j = 0;j<4;j++){
                cards[i][j].setNum(0);
            }
        }
        addRandomNum();
        addRandomNum();
    }
    private void addRandomNum(){
        emptyPoints.clear();//先清空再添加空白卡片
        for(int i = 0;i<4;i++){
            for (int j = 0;j<4;j++){
                if (cards[i][j].getNum() <= 0){
                    emptyPoints.add(new Point(i,j));
                }
            }
        }
        Point p = emptyPoints.remove((int)(Math.random()*emptyPoints.size()));//随机选择一个卡片
        cards[p.x][p.y].setNum(Math.random() > 0.3 ? 2 : 4);//对卡片生成一个随机数
    }
    //向左滑动
    private void swipeLeft(){
        boolean merge = false;//判断是否进行过合并，否则不添加新的卡片
        for (int i=0;i<4;i++){
            for (int j = 0 ;j<4;j++){
                for (int j1 = j+1; j1 < 4; j1++) {
                    if (cards[i][j1].getNum()>0) {
                        if (cards[i][j].getNum()<=0) {
                            cards[i][j].setNum(cards[i][j1].getNum());
                            cards[i][j1].setNum(0);
                            j--;
                            merge = true;
                            break;
                        }else if (cards[i][j].equals(cards[i][j1])) {
                            cards[i][j].setNum(cards[i][j].getNum()*2);
                            cards[i][j1].setNum(0);
                            MainActivity.getMainActivity().addScore(cards[i][j].getNum());
                            merge = true;
                            break;
                        }
                    }
                }
            }
        }
        if (merge) {
            addRandomNum();
            gameOver();
        }
    }
    //向右滑动
    private void swipeRight(){
        boolean merge = false;//判断是否进行过合并，否则不添加新的卡片
        for (int i=0;i<4;i++){
            for (int j = 3 ;j>=0;j--){
                for (int j1 = j-1; j1 >=0 ; j1--) {
                    if (cards[i][j1].getNum()>0) {
                        if (cards[i][j].getNum()<=0) {
                            cards[i][j].setNum(cards[i][j1].getNum());
                            cards[i][j1].setNum(0);
                            j++;
                            merge = true;
                            break;
                        }else if (cards[i][j].equals(cards[i][j1])) {
                            cards[i][j].setNum(cards[i][j].getNum()*2);
                            cards[i][j1].setNum(0);
                            MainActivity.getMainActivity().addScore(cards[i][j].getNum());
                            merge = true;
                            break;
                        }
                    }
                }
            }
        }
        if (merge) {
            addRandomNum();
            gameOver();
        }
    }
    //向上滑动
    private void swipeUp(){
        boolean merge = false;//判断是否进行过合并，否则不添加新的卡片
        for (int j=0;j<4;j++){
            for (int i = 0 ;i<4;i++){
                for (int i1 = i+1; i1 < 4; i1++) {
                    if (cards[i1][j].getNum()>0) {
                        if (cards[i][j].getNum()<=0) {
                            cards[i][j].setNum(cards[i1][j].getNum());
                            cards[i1][j].setNum(0);
                            i--;
                            merge = true;
                            break;
                        }else if (cards[i][j].equals(cards[i1][j])) {
                            cards[i][j].setNum(cards[i][j].getNum()*2);
                            cards[i1][j].setNum(0);
                            MainActivity.getMainActivity().addScore(cards[i][j].getNum());
                            merge = true;
                            break;
                        }
                    }
                }
            }
        }
        if (merge) {
            addRandomNum();
            gameOver();
        }
    }
    //向下滑动
    private void swipeDown(){
        boolean merge = false;//判断是否进行过合并，否则不添加新的卡片
        for (int j=0;j<4;j++){
            for (int i = 3 ;i>=0;i--){
                for (int i1 = i-1; i1 >= 0; i1--) {
                    if (cards[i1][j].getNum()>0) {
                        if (cards[i][j].getNum()<=0) {
                            cards[i][j].setNum(cards[i1][j].getNum());
                            cards[i1][j].setNum(0);
                            i++;
                            merge = true;
                            break;
                        }else if (cards[i][j].equals(cards[i1][j])) {
                            cards[i][j].setNum(cards[i][j].getNum()*2);
                            cards[i1][j].setNum(0);
                            MainActivity.getMainActivity().addScore(cards[i][j].getNum());
                            merge = true;
                            break;
                        }
                    }
                }
            }
        }
        if (merge) {
            addRandomNum();
            gameOver();
        }
    }
    private void initGameView(final Context context){

        setColumnCount(4);
        //实现屏幕滑动事件的响应
        setOnTouchListener(new View.OnTouchListener(){
            private float startX,startY,offsetX,offsetY;
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        startX = event.getX();
                        startY = event.getY();
                        break;
                    case MotionEvent.ACTION_UP:
                        offsetX = event.getX()-startX;
                        offsetY = event.getY()-startY;
                        if(Math.abs(offsetX)>Math.abs(offsetY)){//水平方向
                            if(offsetX<-5){//向左
                                System.out.println("left");
                                //Toast.makeText(context,"left",Toast.LENGTH_SHORT).show();
                                swipeLeft();
                            }
                            else if (offsetX>5){//向右
                                System.out.println("right");
                                //Toast.makeText(context,"right",Toast.LENGTH_SHORT).show();
                                swipeRight();
                            }
                        }
                        else{//竖直方向
                            if(offsetY<-5){//向下
                                System.out.println("up");
                                //Toast.makeText(context,"up",Toast.LENGTH_SHORT).show();
                                swipeUp();
                            }
                            else if (offsetY>5){//向上
                                System.out.println("down");
                                //Toast.makeText(context,"down",Toast.LENGTH_SHORT).show();
                                swipeDown();
                            }
                        }
                        break;
                }
                return true;
            }
        });
    }
    //检查游戏是否结束
    private void gameOver(){
        boolean gameover = true;
        ALL:
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                if (cards[x][y].getNum()==0||
                        (x>0&&cards[x][y].equals(cards[x-1][y]))||
                        (x<3&&cards[x][y].equals(cards[x+1][y]))||
                        (y>0&&cards[x][y].equals(cards[x][y-1]))||
                        (y<3&&cards[x][y].equals(cards[x][y+1]))) {
                    gameover = false;
                    break ALL;
                }
            }
        }
        if(gameover){
           new AlertDialog.Builder(getContext()).setTitle("Good").setMessage("游戏结束").setPositiveButton("再来一次", new DialogInterface.OnClickListener() {
               @Override
               public void onClick(DialogInterface dialog, int which) {
                   startGame();
               }
           }).show();
        }
    }
}
