package ar.com.sourcesistemas.actions;

import ar.com.sourcesistemas.helpers.FileHelper;
import ar.com.sourcesistemas.services.Terminal;
import ar.com.sourcesistemas.views.SelectFolderView;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class Script {
    private FileHelper fileHelper;
    private boolean isGoodName = true;


    public Script(Terminal terminal){
        fileHelper = new FileHelper();
        String command = ".";
        int i = JOptionPane.showConfirmDialog(null, "new folder?");

        if (i == 0) {
            fileHelper.createFolderPrompt();
        } else {
            System.out.println("no");
        }
        File[] foldersNames = fileHelper.getFoldersNames();

        SelectFolderView selectFolderView = new SelectFolderView(foldersNames);

        while (true) {
            if (selectFolderView.isSelected() != null) {
                break;
            }
            Thread.sleep(300);
        }
        String folderName = selectFolderView.isSelected();
        String fileName = "";
        while (isGoodName) {
            fileName = JOptionPane.showInputDialog(null, "File name");
            if (fileName.indexOf(" ") == -1) {
                isGoodName = false;
            }
        }

        System.out.println("script will be created in:");
        System.out.println("folder: " + folderName);
        System.out.println("file: " + fileName);
        try {
            fileHelper.createFolderIfNotExists(folderName);
            fileHelper.createFileIfNotExists(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String absoluteWritePath = fileHelper.getAbsoluteWritePath(fileName);
        terminal.executeInTerminal(absoluteWritePath);


        String filepath = fileHelper.getFilePath();
        addAlias(bashrc, filepath, fileName, command);
    }
}
