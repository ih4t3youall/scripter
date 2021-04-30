package ar.com.sourcesistemas;

import ar.com.sourcesistemas.actions.Alias;
import ar.com.sourcesistemas.actions.Script;
import ar.com.sourcesistemas.helpers.FileHelper;
import ar.com.sourcesistemas.services.Terminal;
import ar.com.sourcesistemas.views.ScriptOrAliasView;
import ar.com.sourcesistemas.views.SelectFolderView;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.List;


public class Main {

    private FileHelper fileHelper ;
    private boolean isGoodName = true;
    private boolean isScript;
    public static void main(String [] args) throws Exception {
        new Main();
    }
    public Main() throws InterruptedException, IOException {
        fileHelper = new FileHelper();
        Terminal terminal =  new Terminal();
        Alias alias = new Alias(fileHelper);
        Script script = new Script(terminal,fileHelper);

        List<String> config = fileHelper.readCreateConfigFile();
        if (config.size() == 0)
        {
            String newBash = JOptionPane.showInputDialog(null, "with type of bash file?");
            config.add(newBash);
            fileHelper.writeCreateConfigFile(config);
            fileHelper.setBashrc(newBash);
        }else {
            fileHelper.setBashrc(config.get(0));
        }

        ScriptOrAliasView scriptOrAlias = new ScriptOrAliasView();

        while(true){
            if(scriptOrAlias.isFinish() != ""){
                isScript = scriptOrAlias.isFinish().equals("Script");
                break;
            }
            Thread.sleep(300);
        }

        List<String> bashrc = fileHelper.getBashRcFile();

        if (isScript) {
            int i = JOptionPane.showConfirmDialog(null, "new folder?");

            if (i == 0) {
                fileHelper.createFolderPrompt();
            } else {
                System.out.println("no");
            }

            File[] foldersNames = fileHelper.getFoldersNames();
            SelectFolderView selectFolderView = new SelectFolderView(foldersNames);

            String folderName = "";
            while (true)
            {
                if (selectFolderView.isSelected()!= "")
                    folderName = selectFolderView.isSelected();
                    break;

            }
            fileHelper.setFolder(folderName);

            String fileName = JOptionPane.showInputDialog(null, "fileName ?");
            try {
                script.createScript(bashrc, fileName);
            } catch (IOException e) {
                System.exit(5);
                e.printStackTrace();
            }
        }else {
            String fileName = JOptionPane.showInputDialog(null, "aliasName?");
            String command = JOptionPane.showInputDialog(null, "write alias");
            alias.createAlias(bashrc, command, fileName);
        }
        fileHelper.writeBashrc(bashrc);
        System.out.println("finish");
        System.exit(0);


    }






}
