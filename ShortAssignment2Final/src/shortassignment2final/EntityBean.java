/**
 * Program: CST-235 Short Project 2
 * File: EntityBean.java
 * Summary: Entity Java Bean that processes a text file
 * Author: Evan Wilson
 * Date: August 12th, 2018
 * */
package shortassignment2final;

import java.beans.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;

public class EntityBean implements Serializable {

    private static final long serialVersionUID = -3617869989524281359L;

    //declare private variables to store the filepath, file, and various aspects of the file after processing
    private String fileContent;
    private String filePath;
    private File file;
    private int totalWords = 0;
    private int totalChars = 0;
    private int sumOfNumbers = 0;

    //auto generated by NetBeans
    private PropertyChangeSupport propertySupport;

    //auto generated by NetBeans
    public EntityBean() {
        propertySupport = new PropertyChangeSupport(this);
    }

    //auto generated by NetBeans
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.addPropertyChangeListener(listener);
    }

    //auto generated by NetBeans
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.removePropertyChangeListener(listener);
    }

    //public getters and setters for the variables that the JavaFX application can access
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

    public void setFileContent(String fileContent) {
        this.fileContent = fileContent;
    }

    //methods to switch screens (used in week 1 use of the Java Bean)
    public String processedScreen() throws FileNotFoundException, IOException {

        openFile();

        return "processedScreen";
    }

    public String welcome() {
        return "welcome";
    }

    //opens file based on string filepath
    public void openFile() throws FileNotFoundException, IOException {
        //Create a new file object using the local FilePath the user enters
        file = new File(filePath);
        BufferedReader textBuffer = new BufferedReader(new FileReader(file));
        fileContent = "";

        //loops through BufferedReader, reading lines and adding them to the existing FileContent string
        //and adding an XHTML new line tag at the end of each line
        while (textBuffer.ready()) {
            fileContent = fileContent + textBuffer.readLine() + "<br/> ";
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
            } catch (Exception e) { //empty catch statement for when it can't parse a word, doesn't need to notify anyone
            }
        }
    }
}
