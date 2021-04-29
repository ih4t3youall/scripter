package ar.com.sourcesistemas.helpers;

import javax.swing.*;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class FileHelper {

    private String basePath;
    private String actualPath;
    private String bashrc;
    private String fileName;

    public FileHelper(){
         this.basePath = System.getProperty("user.home")+"/.scripts/";
         this.actualPath = "";
         this.bashrc = System.getProperty("user.home")+"/.zshrc";
         this.fileName = "";
    }

    private boolean fileExists(String filePath){
        return new File(filePath).exists();
    }

    public boolean createFolderIfNotExists(String folderName){

        this.actualPath = this.basePath+folderName+"/";
        if (this.fileExists(actualPath)){
            return true;
        }else{
            return new File(actualPath).mkdir();
        }
    }

    public void createFileIfNotExists(String fileName) throws IOException {
        String tempPath = this.actualPath+fileName;
        this.fileName = fileName;
        if (fileExists(tempPath)){
            Files.delete(Paths.get(tempPath));
        }
        Files.createFile(Paths.get(tempPath));
    }


    public String getAbsoluteWritePath(String fileName){
        return this.actualPath+"/"+fileName;
    }
    public File[] getFoldersNames(){
        FileFilter fileFilter = pathname -> Files.isDirectory(Paths.get(pathname.getPath()));
        File[] list = new File(basePath).listFiles(fileFilter);
        return list;
    }


    public  void createFolderPrompt(){
        String folderName = "";
        while(true) {
            folderName = JOptionPane.showInputDialog(null, "Folder name:");
            if (folderName.indexOf(" ") == -1) {
                break;
            }
        }

        if (folderName != "" ){
            this.createFolderIfNotExists(folderName);
        }
        else{
            System.out.println(" el folder name esta vacio");
            System.exit(0);
        }
    }

    public String getFilePath(){
        if (fileName != "" && actualPath != "")
        {
            return this.actualPath+fileName;
        }
        return null;
    }

    public List<String> getBashRcFile(){
        List<String> arrayList = new ArrayList<>();
        try (Stream<String> stream = Files.lines(Paths.get(bashrc))) {
            stream.forEach(x->arrayList.add(x));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    public String getBashRc(){
        return this.bashrc;
    }

    public boolean removeBashrc() {
        try {
            Files.deleteIfExists(Paths.get(this.bashrc));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void writeBashrc(List<String> bashrcList) {

        try {
            Files.write(Paths.get(this.bashrc), bashrcList);
        } catch (IOException e) {
            System.out.println("Write fail, here is a back up of how the file should be");
            bashrcList.forEach(System.out::println);
            e.printStackTrace();
        }
    }
}
