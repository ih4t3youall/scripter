package ar.com.sourcesistemas.views;

import ar.com.sourcesistemas.utils.ScreenPositionUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

public class SelectFolderView extends JFrame {

    private JButton[] buttons;
    private String selected = null;
    public SelectFolderView(File[] folders){
        super("select folder");
        addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                System.out.println("Closed");
                e.getWindow().dispose();
                System.exit(10);
            }
        });

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
        setSize(200,200);
        setResizable(true);

    }

    public String isSelected(){
        return selected;
    }

    public void shutdown(){
        setVisible(false);
    }


}
