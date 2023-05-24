package com.company;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SimpleGUI extends JFrame{
    private JButton button = new JButton("Рассчитать");
    private JButton button1 = new JButton("ghjfddddddddddddddd");
    private JTextField inputCostOfCar = new JTextField("", 5);

    private JLabel label = new JLabel("Введите стоимость авто:");
//    private JLabel label2 = new JLabel("Введите объем авто:");


    public SimpleGUI(){
        super("Car Calculator");
        this.setSize(600, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        label.setBounds(50, 50, 200, 30);

        inputCostOfCar.setColumns(100);


        this.getContentPane().setBackground(new Color(255, 250, 250)); //change color of background

        Container container = this.getContentPane();
        container.setLayout(new GridLayout(3, 1, 10, 10));
        container.add(label);
//        container.add(label2);
        container.add(inputCostOfCar);

        button.addActionListener(new CalcEventListener());
        container.add(button);
        container.add(button1);
    }
    class CalcEventListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            String message = "";
            message += "Button was pressed\n";
            message += "Стоимость вашего авто= " + inputCostOfCar.getText() + "\n";


            JOptionPane.showMessageDialog(null, message, "Результаты расчета", JOptionPane.PLAIN_MESSAGE);
        }
    }

}
