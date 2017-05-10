package ru.time2store.dmath;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Frame {
    JTextArea subsetC;
    JTextField subsetA;
    JTextField subsetB;
    Relatives subset = new Relatives();

    // Контсруктор окна GUI
    Frame() {

        JFrame jfrm = new JFrame("Лабораторная работа №1 по дискретной математике");

        jfrm.setPreferredSize(new Dimension(600, 300));
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jfrm.setLayout(new FlowLayout(FlowLayout.LEFT));

        JLabel lb1 = new JLabel("Введите множество А (вводится латинскими буквами):");
        JLabel lb2 = new JLabel("Введите пары (a, b) \u2208 A (латинские букваы или цифры):");
        JButton bt1 = new JButton("Выполнить операции над множествами");

        ActionListener actionListener = new TestActionListener();
        bt1.addActionListener(actionListener);

        subsetA = new JTextField(50);
        subsetB = new JTextField(50);
        subsetC = new JTextArea(7, 50);

        jfrm.add(lb1);
        jfrm.add(subsetA);
        jfrm.add(lb2);
        jfrm.add(subsetB);
        jfrm.add(bt1);
        jfrm.add(subsetC);

        jfrm.pack();
        jfrm.setVisible(true);

    }

    public class TestActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            subset.setArray(subsetA.getText(), subsetB.getText());
            subsetC.setText(subset.showSubset() + '\n' + subset.showRArray());

            TablePopUp fr1 = new TablePopUp("Результат вычислений" , 400, 200);
            int i = 0;

            JTable table = new JTable(subset.getBinMatrix(), subset.getHeader());
            table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            while (i<subset.getRArryLenght()) {
                table.getColumnModel().getColumn(i).setMaxWidth(3);
                i++;
            }

            /*JButton button = new JButton("Пересчитать");
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                }
            });*/
            JTextArea textArea = new JTextArea(10, 20);
            fr1.add(table);
            fr1.add(textArea);
            fr1.setVisible(true);
        }
    }
}