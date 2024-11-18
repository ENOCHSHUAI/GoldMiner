package com.sxt;

import java.awt.*;

public class Object {
    //坐标
    int x;
    int y;
    //宽高
    int width;
    int height;

    Image img;
    //标记是否可以移动
    boolean flag = false;
    //质量
    int m;
    //积分
    int count;

    void paintself(Graphics g){
        g.drawImage(img, x, y, null);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Rectangle getRec(){
        return new Rectangle(x,y,width,height);
    }
}
