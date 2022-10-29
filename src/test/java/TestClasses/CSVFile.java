package TestClasses;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Represent a csv file have been read from local storage
 * with its data in an organized way
 */
public class CSVFile {

    /**
     * the path of file in local storage
     */
    private String path;
    /**
     * the used splitter to split data
     */
    private String splitter;
    /**
     * store the results of the file
     */
    private HashMap<String,String> results;

    /**
     * Represents a browser driver with its actions
     */
    public CSVFile(String name, String splitter){
        if(!name.contains("\\"))
            this.path = "files\\"+name+".csv";
        else
            this.path = name;

        this.splitter = splitter;
        results = new HashMap<>();
    }

    /**
     * read the file from local storage and sets data
     * with some validation
     */
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

    /**
     * gets the results in the file
     * @return the results of the file
     */
    public HashMap<String,String> getResults(){
        if(results.isEmpty()) readFile();
        return results;
    }

    /**
     * splits the line given using the splitter into arrays
     * @param line the line sent to split
     * @return the array of data in the line
     */
    public List<String> splitLine(String line){
        List<String> result = new ArrayList<String>(List.of(line.split(this.splitter)));
        return result;
    }
}
