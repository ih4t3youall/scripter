package ar.com.sourcesistemas.actions;

import ar.com.sourcesistemas.helpers.FileHelper;
import ar.com.sourcesistemas.services.Terminal;

import java.io.IOException;
import java.util.List;

public class Script {
    private FileHelper fileHelper;
    private boolean isGoodName = true;
    private Terminal terminal;

    public Script(Terminal terminal, FileHelper fileHelper){
        this.fileHelper = fileHelper;
        this.terminal = terminal;
    }


    public void createScript(List<String> bashrc, String fileName) throws InterruptedException, IOException {
        String command = ".";
        if (fileName.trim() == ""){
            System.out.println("filename empty, script");
            System.exit(5);
        }

        String absoluteWritePath = fileHelper.getAbsoluteWritePath(fileName);
        fileHelper.createFileIfNotExists(fileName);
        terminal.executeInTerminal(absoluteWritePath);


        String filepath = fileHelper.getFilePath();
        fileHelper.addAlias(bashrc, filepath, fileName, command);
    }
}
