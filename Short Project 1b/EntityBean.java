/**
 * Program: CST-235 Short Project 1b
 * File: EntityBean.java
 * Summary: Entity Java Bean that processes a text file
 * Author: Evan Wilson
 * Date: August 12th, 2018
 * */
package ShortProject1b;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.persistence.Entity;
import javax.persistence.Id;

@Named(value = "jSFBean")
@SessionScoped
@Entity
public class EntityBean implements Serializable {

    private String fileContent;
    private String filePath;
    private File file;
    private int totalWords;
    private int totalChars;
    private int sumOfNumbers;
    @Id
    private Long id;

    public EntityBean() {
    }

    public int getTotalWords() {
        return totalWords;
    }

    public void setTotalWords(int totalWords) {
        this.totalWords = totalWords;
    }

    public int getTotalChars() {
        return totalChars;
    }

    public void setTotalChars(int totalChars) {
        this.totalChars = totalChars;
    }

    public int getSumOfNumbers() {
        return sumOfNumbers;
    }

    public void setSumOfNumbers(int sumOfNumbers) {
        this.sumOfNumbers = sumOfNumbers;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getFileContent() {
        return fileContent;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    
    //methods to switch screens
    public String processedScreen() {
        return "processedScreen";
    }

    public String welcome() {
        return "welcome";
    }

    //opens file based on string filepath, then calls processfile() method
    public void openFile() throws FileNotFoundException {
        try {
            file = new File(filePath);
            BufferedReader textBuffer = new BufferedReader(new FileReader(file));
            fileContent = "";

            //loops through BufferedReader, reading lines and adding an XHTML new line tag
            while (textBuffer.ready()) {
                fileContent = fileContent + textBuffer.readLine() + "<br/> ";
            }

            processFile();

            //error message if filePath is incorrect and unable to open the file
        } catch (IOException ex) {
            Logger.getLogger(EntityBean.class.getName()).log(Level.SEVERE, null, ex);

            fileContent = "Incorrect file path or type entered.";
        }
    }

    //method for performing the processing
    public void processFile() {
        //seperates the fileContent into individual words in an array
        String[] words = fileContent.split(" ");

        //gets the length of the array for total words
        totalWords = words.length;

        //go through each word and get the word length to get total chars
        for (String word : words) {
            totalChars += word.length();

            //try to parse every word, if it succeeds, adds the value to the sumOfNumbers
            try {
                int temp = Integer.parseInt(word);
                sumOfNumbers += temp;
            } catch (Exception e) { //empty catch statement for when it can't parse a word
            }
        }
    }
}
