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

        JFrame jfrm = new JFrame("Лабораторная работа №2 по дискретной математике");

        jfrm.setPreferredSize(new Dimension(580, 330));
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jfrm.setLayout(new FlowLayout(FlowLayout.LEFT));

        JLabel lb1 = new JLabel("Введите множество А (вводится латинскими буквами):");
        JLabel lb2 = new JLabel("Введите пары (a, b) \u2208 A (латинские букваы или цифры):");
        JButton bt1 = new JButton("Выполнить операции над множествами");

        ActionListener actionListener = new TestActionListener();
        bt1.addActionListener(actionListener);

        subsetA = new JTextField(50);
        subsetB = new JTextField(50);
        subsetC = new JTextArea(10, 50);

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
            subsetC.setText(subset.showSubset() + '\n' + subset.showRArray() + '\n' + subset.matrixForCheck());

            JTable table = new JTable(subset.getBinMatrix(), subset.getHeader());
            TablePopUp fr1 = new TablePopUp("Бинарная матрица" , subset.getTableDimension()*19, subset.getTableDimension()*25);
            table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

            int i = 0;
            while (i<subset.getHeaderLenght()+1) {
                table.getColumnModel().getColumn(i).setMaxWidth(3);
                i++;
            }

            fr1.add(table);
            fr1.setVisible(true);
        }
    }
}