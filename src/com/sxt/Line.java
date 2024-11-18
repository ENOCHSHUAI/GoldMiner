package com.sxt;

import java.awt.*;

public class Line {
    int x = 500;
    int y = 300;
    int endx = 500;
    int endy = 500;
    //线长
    double length = 50;
    double angle = 0;
    //方向
    int dir = 1;
    //状态：0摇摆，1抓取，2收回，3抓取返回
    int state;
    //钩爪
    Image hook = Toolkit.getDefaultToolkit().getImage("imgs/hook.png");
    //所有金子的数量

    GameWin frame;
    Line(GameWin frame){
        this.frame = frame;
    }

    //碰撞检测
    void logic(){
        for (Object obj: this.frame.objectsList) {
            if (endx > obj.x && endx < obj.x + obj.width && endy > obj.y && endy < obj.y + obj.height) {
                state = 3;
                obj.flag = true;
            }
        }
    }
    //绘制
    void lines(Graphics g){
        // 将Graphics对象转换为Graphics2D以使用更多的绘图功能
        Graphics2D g2 = (Graphics2D) g;

        // 设置线条的宽度为3（你可以根据需要调整宽度）
        g2.setStroke(new BasicStroke(3));

        endx = (int) (x+length*Math.cos(angle*Math.PI));
        endy = (int) (y+length*Math.sin(angle*Math.PI));
        g.setColor(Color.red);
        g.drawLine(x,y,endx,endy);
        g.drawImage(hook, endx-10, endy-2,null);
    }


    void paintSelf(Graphics g){
        logic();
        switch (state){
            case 0:
                if (angle < 0.1){
                    dir = 1;
                }
                else if (angle > 0.9){
                    dir = -1;
                }
                angle=angle+0.005*dir;
                lines(g);
                break;
            case 1:
                if(length < 1000){
                    length = length +10;
                    lines(g);
                }else {
                    state = 2;
                }
                break;
            case 2:
                if (length > 50){
                    length = length -10;
                    lines(g);
                }else {
                    state = 0;
                }
            case 3:
                int m = 1;
                if (length > 50){
                    length = length -10;
                    lines(g);
                    for (Object obj:this.frame.objectsList) {
                        if(obj.flag){
                            m = obj.m;
                            obj.x=endx-obj.getWidth()/2;
                            obj.y=endy;
                            if (length <= 50){
                                obj.x = -150;
                                obj.y = -150;
                                obj.flag =false;
                                Bg.count += obj.count;
                                if (obj instanceof Gold) {
                                    this.frame.totalgold-=1;
                                    if (this.frame.totalgold == 0) {
                                        this.frame.showGameOverDialog();
                                    }
                                }
                                state = 0;
                            }
                        }

                    }
                }
                try {
                    Thread.sleep(m);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}
