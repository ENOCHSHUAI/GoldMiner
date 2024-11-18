package com.sxt;

import sun.awt.image.ToolkitImage;

import java.awt.*;

public class Gold extends Object{
     Gold(){
         this.x=(int) (Math.random()*1000);
         this.y=(int) (Math.random()*550+350);
         this.width = 25;
         this.height = 25;
         this.flag = false;
         this.m = 50;
         this.count = 10;
         this.img = Toolkit.getDefaultToolkit().getImage("imgs/gold2.png");
     }
}
class GoldMini extends Gold{
    GoldMini(){
        this.width=10;
        this.height=10;
        this.m = 20;
        this.count = 2;
        this.img = Toolkit.getDefaultToolkit().getImage("imgs/gold3.png");
    }
}
class GoldPlus extends Gold{
    GoldPlus(){
        this.width=50;
        this.height=50;
        this.m = 90;
        this.count = 15;
        this.img = Toolkit.getDefaultToolkit().getImage("imgs/gold1.png");
    }
}