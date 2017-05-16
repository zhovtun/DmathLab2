package ru.time2store.dmath;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Evgen on 08.05.2017.
 */
public class TablePopUp extends JFrame {
    public TablePopUp(String title, int width, int heigth) throws HeadlessException {
        super(title);
        setBounds(200, 200, width, heigth);
        setPreferredSize(new Dimension(width, heigth));
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setResizable(true);
        setDefaultLookAndFeelDecorated(false);
        pack();
    }
}