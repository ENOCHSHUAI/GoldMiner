package com.sxt;

import java.awt.*;

public class Rock extends Object{
    Rock(){
        this.x=(int) (Math.random()*1000);
        this.y=(int) (Math.random()*550+350);
        this.width = 80;
        this.height = 80;
        this.flag = false;
        this.m = 100;
        this.count = 5;
        this.img = Toolkit.getDefaultToolkit().getImage("imgs/rock.png");
    }
}
