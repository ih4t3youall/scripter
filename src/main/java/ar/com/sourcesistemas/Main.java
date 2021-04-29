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

    private FileHelper fileHelper = new FileHelper();
    private boolean isGoodName = true;
    private boolean isScript;
    public static void main(String [] args) throws InterruptedException {
        new Main();
    }
    public Main() throws InterruptedException {

        ScriptOrAliasView scriptOrAlias = new ScriptOrAliasView();
        Terminal terminal =  new Terminal();
        Script script = new Script(terminal,fileHelper);
        Alias alias = new Alias(fileHelper);
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
