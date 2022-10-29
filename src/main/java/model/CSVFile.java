package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class CSVFile {

    private String path;
    private String splitter;
    private HashMap<String,String> results;

    public CSVFile(String name, String splitter){
        if(!name.contains("\\"))
            this.path = "files\\"+name+".csv";
        else
            this.path = name;

        this.splitter = splitter;
        results = new HashMap<>();
    }

    public void readFile() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line = "";
            while ((line = br.readLine()) != null){
                List<String> lineSplited = this.splitLine(line);
                if(lineSplited.size() < 2) continue;
                results.put(lineSplited.get(0).replaceAll("\\s","").toLowerCase(),lineSplited.get(1));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public HashMap<String,String> getResults(){
        if(results.isEmpty()) readFile();
        return results;
    }

    public List<String> splitLine(String line){
        List<String> result = new ArrayList<String>(List.of(line.split(this.splitter)));
        return result;
    }
}
