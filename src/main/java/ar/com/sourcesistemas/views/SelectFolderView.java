package ar.com.sourcesistemas.views;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class SelectFolderView extends JFrame {

    private JButton[] buttons;
    private String selected = null;
    public SelectFolderView(File[] folders){
        super("select folder");

        setLayout(new FlowLayout());
        setSize(200,200);
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
