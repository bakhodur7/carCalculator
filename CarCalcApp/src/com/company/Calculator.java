package com.company;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.text.AbstractDocument;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Calculator extends JFrame implements ActionListener {
    Container container = getContentPane();

    JLabel costLabel = new JLabel("Стоимость авто");
    JLabel volumeLabel = new JLabel("Объем авто");
    JLabel ageLabel = new JLabel("Возраст авто");
    JLabel volumeLabelValue = new JLabel();//лэйбл для объема

    //JTextField userTextFieldCost = new JTextField(); //стоимость авто

    String[] agesOfCar = {"до 2-х лет", "от 2-х до 3-х лет", "старше 3-х лет"};
    JComboBox comboBox = new JComboBox(agesOfCar);
    JTextField userTextFieldAge = new JTextField(); //возраст авто

    JLabel nameOfCompanyLabel = new JLabel();

//    ImageIcon imageLabel = new ImageIcon("logo7.png"); //добавление лого в приложение
//    JLabel labelOfIcon = new JLabel();


    JSlider slider = new JSlider(1000,7000, 2000); //объем авто






    JButton calcButton = new JButton("Рассчитать");
    //JButton closeButton = new JButton("Отмена");




    Calculator() {
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();

        slider.setPaintTicks(true);
        slider.setMajorTickSpacing(1000);
        slider.setMinorTickSpacing(100);
        slider.setPaintLabels(true);

        volumeLabelValue.setText("Объем = " + (slider.getValue()/1000 +"." + ((slider.getValue()%1000)/100) + " л" ));
        slider.addChangeListener(this::stateChanged);
        slider.setSnapToTicks(true);

        nameOfCompanyLabel.setText("KazInterCar");
        nameOfCompanyLabel.setFont(new Font("Serif", Font.PLAIN, 30));

        ((AbstractDocument)userTextFieldCost.getDocument()).setDocumentFilter(
                new MyDocumentFilter());

        //labelOfIcon.setIcon(imageLabel);
    }

    public void setLayoutManager() {
        container.setLayout(null);

    }
    public void stateChanged(ChangeEvent e){
        volumeLabelValue.setText("Объем = " + (slider.getValue()/1000 +"." + ((slider.getValue()%1000)/100) + " л" ));
    }

    public void setLocationAndSize() {
        //labelOfIcon.setBounds(80, 10, 260, 133);
        nameOfCompanyLabel.setBounds(130, 70, 260, 50);
        costLabel.setBounds(30, 150, 100, 30);
        volumeLabel.setBounds(30, 220, 100, 30);
        ageLabel.setBounds(30, 350, 100, 30);

        userTextFieldCost.setBounds(125, 150, 150, 30);
        slider.setBounds(120, 220, 275, 50);
        volumeLabelValue.setBounds(120, 290, 200, 30);
        comboBox.setBounds(125, 350, 150, 30);

        calcButton.setBounds(160, 420, 100, 30);
        //closeButton.setBounds(240, 420, 100, 30);




    }

    public void addComponentsToContainer() {
        //container.add(labelOfIcon);
        container.add(nameOfCompanyLabel);
        container.add(costLabel);
        container.add(volumeLabel);
        container.add(ageLabel);
        container.add(userTextFieldAge);
        container.add(userTextFieldCost);
        container.add(comboBox);
       // container.add(showPassword);
        container.add(calcButton);//container.add(closeButton);
        container.add(slider);
        container.add(volumeLabelValue);

    }

    public void addActionEvent() {
        calcButton.addActionListener(this);
        //closeButton.addActionListener(this);

    }
    public JTextField userTextFieldCost = new JTextField(10);


    public void actionPerformed(ActionEvent e) {

        String message;
        String costText = userTextFieldCost.getText().trim();
        //long costOfCar = (Long.parseLong(costText));

        if (costText.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Пожалуйста, заполните все поля",
                    "Ошибка", JOptionPane.ERROR_MESSAGE);
            return;
        }
        long costOfCar = (Long.parseLong(costText));
        if (costText.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Пожалуйста, заполните все поля",
                    "Ошибка", JOptionPane.ERROR_MESSAGE);
            return;
        }else if(costOfCar < 500000){
            JOptionPane.showMessageDialog(null, "Слишком маленькая стоимость, введите стоимость больше 500 тыс тенге",
                    "Ошибка", JOptionPane.ERROR_MESSAGE);
            return;
        }else if(costOfCar > 900000000){
            JOptionPane.showMessageDialog(null, "Слишком большая стоимость, введите стоимость до 900 млн тенге",
                    "Ошибка", JOptionPane.ERROR_MESSAGE);
            return;
        }


        int price_of_car = Integer.parseInt(costText);

        int mrp = 3450;
        double koef_age = 0;
        int resultInitReg = 0;
        Object selectedItem = comboBox.getSelectedItem();
        if (selectedItem.equals("до 2-х лет")) {
            koef_age = 0.25;
        } else if (selectedItem.equals("от 2-х до 3-х лет")) {
            koef_age = 50;
        } else if (selectedItem.equals("старше 3-х лет")) {
            koef_age = 500;
        }


        double resultInitRegDouble = koef_age*mrp;
        resultInitReg = (int)resultInitRegDouble;
        System.out.println("Первичная регистрация: " + resultInitReg);
        int total=0;
        int customs_sbor = 20000;
        int resultCustomsValue = price_of_car + customs_sbor;
        int resultCustomsDuty = ((resultCustomsValue * 15)/100) + resultCustomsValue;
        int resultNds = ((resultCustomsDuty *12)/100) + resultCustomsDuty;
        int resultNdsChisto = resultNds - price_of_car;
        int aktciz = 0;

        int resultUtilInt = 0;
        double koef_util = 0;
        if(slider.getValue()<=1000){
            koef_util = 1.5;

        }else if(slider.getValue()>1000 && slider.getValue()<=2000){
            koef_util = 3.5;

        }else if(slider.getValue()>2000 && slider.getValue()<=3000){
            koef_util = 5;

        }else if(slider.getValue()>3000){
            koef_util = 11.5;
            double aktcizDouble = slider.getValue()*100;
            aktciz = (int)aktcizDouble;
        }
        int base_rate = 50*mrp; //базовая ставка
        double resultUtilDouble = koef_util*base_rate;
        resultUtilInt = (int)resultUtilDouble;
        System.out.println("Утильсбор: " + resultUtilInt);


        total = resultNds + resultInitReg + resultUtilInt + aktciz + 100000;

        message = "Стоимость вашего авто = " + costText + "\n";
        message += "Возраст авто = " + comboBox.getSelectedItem() + "\n";
        message += "Объем авто = " + (slider.getValue()/1000 +"." + ((slider.getValue()%1000)/100) + " л" ) + "\n"+ "\n";

        message +=("Первичная регистрация: " + resultInitReg + " тенге" + "\n");
        message += "Акциз = " +  aktciz + " тенге" + "\n";
        message += "СБКТС = 100000 тенге" + "\n";
        message += "Утильсбор = " +  resultUtilInt + " тенге" + "\n";
        message += "Окончательная цена вместе с растаможкой = " + total + " тенге" + "\n";


        JOptionPane.showMessageDialog(null, message, "Результаты расчета", JOptionPane.PLAIN_MESSAGE);
    }

}
