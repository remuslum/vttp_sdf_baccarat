package src.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    private final String filePath;
    private final String username;
    private final String fileExtension;
    private final String file;

    public FileManager(String username){
        this.filePath = "/Users/remuslum/Downloads/vttp_sdf/vttp_sdf_bacarrat/data";
        this.username = username;
        this.fileExtension = ".db";
        this.file = this.filePath + File.separator + this.username + this.fileExtension;
    }

    public List<String> readFile(String amount) throws IOException {
        List<String> fileContent = new ArrayList<>();

        File file = new File(this.file);
        if(!file.exists()) {
            file.createNewFile();
        }

        FileReader fileReader = new FileReader(file);
        BufferedReader br = new BufferedReader(fileReader);
        String line = "";
        while((line = br.readLine()) != null) {
            fileContent.add(line);
        }
        br.close();
        fileReader.close();
        if(fileContent.isEmpty()){
            fileContent.add(amount);
        } else {
            int currentAmount = Integer.parseInt(fileContent.get(0));
            fileContent.set(0, String.valueOf(currentAmount + Integer.parseInt(amount)));
        }
        return fileContent;     
    }

    public void writeToFile(List<String> fileContent) throws IOException{
        File file = new File(this.file);
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fileWriter);

        for(String content:fileContent) {
            bw.write(content + "\n");
            bw.flush();
        }
        bw.close();
        fileWriter.close();
    }
}
