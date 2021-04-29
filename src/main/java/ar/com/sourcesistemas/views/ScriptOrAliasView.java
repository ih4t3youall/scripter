package ar.com.sourcesistemas.views;

import ar.com.sourcesistemas.utils.ScreenPositionUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScriptOrAliasView extends JFrame {

    private JButton script,alias;
    private String selected = "";

    public ScriptOrAliasView()
    {

        super("create a script or an alias");
        setLayout(new FlowLayout());
        Point point = ScreenPositionUtil.getCenter();
        setSize(200,200);

        script = new JButton("Script");
        alias = new JButton("alias");

        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selected = ((JButton)e.getSource()).getText();
                shutdown();
            }
        };
        add(script);
        add(alias);
        script.addActionListener(listener);
        alias.addActionListener(listener);
        setVisible(true);
        System.out.println("script or alias");
        setLocation(200,200);
    }


    public String isFinish(){
        return this.selected;
    }
    public void shutdown(){
       setVisible(false);
    }


}
