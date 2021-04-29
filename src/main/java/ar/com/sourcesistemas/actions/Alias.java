package ar.com.sourcesistemas.actions;

import ar.com.sourcesistemas.helpers.FileHelper;

import javax.swing.*;
import java.util.List;

public class Alias {

    private FileHelper fileHelper;

    public Alias(FileHelper fileHelper){
        this.fileHelper = fileHelper;
    }

    public void createAlias(List<String> bashrc,String command, String fileName){
        if (command == "")
            System.exit(5);
        this.fileHelper.addAlias(bashrc,"",fileName,command);

    }
}
