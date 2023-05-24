package com.company;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        Calculator frameCalc = new Calculator();
        frameCalc.setTitle("Car calculator");
        frameCalc.setVisible(true);
        frameCalc.setBounds(10, 10, 430, 600);
        frameCalc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameCalc.setResizable(false);


        ImageIcon imageNew = new ImageIcon("logo1.png"); //устанавливаем логотип
        frameCalc.setIconImage(imageNew.getImage());
        frameCalc.getContentPane().setBackground(new Color(32, 178, 170)); //меняем цвет фона
    }
}
