package com.sxt;

import java.awt.*;

public class Bg {
    static int count = 0;
    Image bg2 = Toolkit.getDefaultToolkit().getImage("imgs/bg2.png");
    Image bg1 = Toolkit.getDefaultToolkit().getImage("imgs/bg1.png");
    Image miner = Toolkit.getDefaultToolkit().getImage("imgs/miner.png");
    void paintSelf(Graphics g){
        g.drawImage(bg1,0,0,null);
        g.drawImage(bg2,0,300,null);
        g.drawImage(miner,400,170,null);

        drawWord(g,30,Color.black, "Point: "+count, 30, 150);
    }
    //打印方法
    public static void drawWord(Graphics g, int size, Color color, String str, int x, int y){
        g.setColor(color);
        g.setFont(new Font("Arial", Font.BOLD, size));
        g.drawString(str, x,y);
    }
}
