package com.assignment6.util;

import java.io.File;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class FileSearch {
    /**
     * Using BFS to search for a file in the filesystem.
     *
     * @param startPath The starting directory path where the search begins.
     * @param fileName  The name of the file to search for.
     * @return The path of the file if found, or null if not found.
     *
     * n: Total number of files and directories in the file system
     * w: Maximum number of files and directories at any level.
     *           Time Complexity  Space Complexity
     * Worst Case O(n)              O(w)
     */
    public static String searchFileBFS(String startPath, String fileName) throws Exception {
        // Create a queue for BFS
        Queue<File> queue = new LinkedList<>();

        // Add the starting directory to the queue
        File startDir = new File(startPath);
        // Error handling: Check if the start path exists and is a directory
        if (!startDir.exists()) {
            throw new Exception("Error: The start path does not exist.");
        }
        if (!startDir.isDirectory()) {
            throw new Exception("Error: The start path is not a directory.");
        }
        queue.add(startDir);

        // BFS to search for the file
        while (!queue.isEmpty()) {
            // Remove the current directory/file from the queue
            File current = queue.poll();
            // List all files and directories in the current directory
            File[] filesAndDirs = current.listFiles();
            if (filesAndDirs != null) {
                for (File file : filesAndDirs) {
                    if (file.isDirectory()) {
                        // Add directories to the queue to explore later
                        queue.add(file);

                    } else if (file.isFile() && file.getName().equals(fileName)) {
                        // If the file is found, return the file path
                        return file.getAbsolutePath();
                    }
                }
            }
        }

        // If the file is not found
        return null;
    }
}
