package com.assignment6.main;


import static com.assignment6.util.FileSearch.searchFileBFS;

public class FileSearchMain {

    public static void main(String[] args) throws Exception {
        String startPath = "C:\\Manish\\";
        String fileName = "Test2.txt";

        String result = searchFileBFS(startPath, fileName);
        if (result != null) {
            System.out.println("File found at: " + result);
        } else {
            System.out.println("File not found.");
        }
    }
}
