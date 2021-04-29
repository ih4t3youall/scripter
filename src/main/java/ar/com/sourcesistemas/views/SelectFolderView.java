package ar.com.sourcesistemas.views;

import ar.com.sourcesistemas.utils.ScreenPositionUtil;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class SelectFolderView extends JFrame {

    private JButton[] buttons;
    private String selected = null;
    public SelectFolderView(File[] folders){
        super("select folder");

        Point point = ScreenPositionUtil.getCenter();
        setLocation(point);

        setLocationRelativeTo(null);
        setLayout(new FlowLayout());
        buttons = new JButton[folders.length];

        for (int i =0 ; i < folders.length;i++ ) {
            buttons[i] = new JButton(folders[i].getName());
            buttons[i].addActionListener(x-> {
                selected = ((JButton)x.getSource()).getText();
                shutdown();

            });
            add(buttons[i]);
        }
        setVisible(true);
        setResizable(true);

    }

    public String isSelected(){
        return selected;
    }

    public void shutdown(){
        setVisible(false);
    }


}
