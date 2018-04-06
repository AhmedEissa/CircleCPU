package protocol;

import java.io.Serializable;
import java.util.ArrayList;

public class TextProtocol implements Serializable{

    private String header;
    private int data;
    private ArrayList<String> stringArray;

    public TextProtocol(String header) {
        this.header = header;
    }

    public TextProtocol(String header, int data) {
        this.header = header;
        this.data = data;
    }

    public TextProtocol(String header, ArrayList<String> stringArray) {
        this.header = header;
        this.stringArray = stringArray;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public ArrayList<String> getStringArray() {
        return stringArray;
    }

    public void setStringArray(ArrayList<String> stringArray) {
        this.stringArray = stringArray;
    }



}