package com.sxt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class GameWin extends JFrame implements KeyListener {
    //存储金块，石头
    List<Object> objectsList = new ArrayList<>();
    Bg bg = new Bg();
    Line line = new Line(this);
    int totalgold = 0;
    {
        //是否可以放置
        boolean isPlace = true;
        for (int i = 0; i < 7; i++) {
            double random = Math.random();
            Gold gold;//存放当前生成的金块

            if(random<0.3){
                gold = new GoldMini();
            }else if(random < 0.7){
                gold = new Gold();
            }else{
                gold = new GoldPlus();
            }
            for (Object obj : objectsList){
                if (gold.getRec().intersects(obj.getRec())){
                    //不可放置，重新生成
                    isPlace = false;
                }
            }
            if (isPlace){
                objectsList.add(gold);
                totalgold +=1;
            }else {
                isPlace=true;
                i--;
            }
        }
        for (int i = 0; i < 4; i++) {
            Rock rock = new Rock();
            for (Object obj:objectsList) {
                if(rock.getRec().intersects(obj.getRec())){
                    isPlace = false;
                }
            }
            if(isPlace){
                objectsList.add(rock);
            }
            else {
                isPlace=true;
                i--;
            }
        }
    }

    Image offScreenImage;

    void launch(){
        this.setVisible(true);
        this.setSize(1000,1000);
        this.setLocationRelativeTo(null);
        this.setTitle("Gold Miner");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        addKeyListener(this);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getButton() == 1){
                    line.state =1;
                }
            }
        });

        while (true){
            repaint();

            try{
                Thread.sleep(10);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    @Override
    public void keyPressed(KeyEvent e) {
        // 当按下键盘时，检测是否是空格键
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            line.state = 1;
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {

    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    public void paint(Graphics g){
        offScreenImage = this.createImage(1000,1000);
        Graphics gImage = offScreenImage.getGraphics();


        bg.paintSelf(gImage);
        line.paintSelf(gImage);

        for (Object obj : objectsList){
            obj.paintself(gImage);
        }
        g.drawImage(offScreenImage,0,0,null);
    }

    public static void main(String[] args) {
        GameWin gameWin = new GameWin();
        gameWin.launch();

    }

    public void showGameOverDialog() {
        int choice = JOptionPane.showOptionDialog(this, "Game Over！What u want do next:", "GameOver", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
                new String[]{"Restart", "Exit"}, "Restart");

        if (choice == JOptionPane.YES_OPTION) {
            // 用户选择重新开始，添加重新开始的逻辑
            dispose();
            GameWin gameWin1 = new GameWin();
            gameWin1.launch();
        } else {
            // 用户选择退出，可以调用 System.exit(0) 退出程序
            System.exit(0);
        }
    }
}
