/*
 * CSC-239 Project 3: Word Indexer
 * Student: Patrick Gould
 * Date: 12/10/2022
 * Description: This program takes in the name of a text file as
 * a command line argument. It then reads in that file and creates
 * an index of each word containing the number of times it occurs
 * as well as the location of each occurrence in the text file.
 *
 * Example command : "java WordIndexer Gettysburg.txt"
 *
 */

import java.io.*;
import java.util.Scanner;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.Map;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class WordIndexer {
	public static void main(String[] args) {
		
		// Save first argument from command line as the name of the file
		final String FILE_NAME = args[0];

		 // Create a tree map to store the words and associated word info
    	TreeMap<String, WordInfo> wordCounts = new TreeMap<String, WordInfo>();

    	// Set file name
		java.io.File file = new java.io.File(FILE_NAME);

        // Create a Scanner for the file and attempt to read it
        try {
        	Scanner input = new Scanner(file);

        	// Notify user of the file that is being indexed
			System.out.printf("\nIndex for file \"%s\"\n\n", FILE_NAME);

			// Initialize line number
        	int lineNum = 1;

        	// Read text from file
        	while (input.hasNext()) {
        		
        		// Save current line
            	String line = input.nextLine();
            	
            	// Split current line into individual words
            	String[] words = line.split(" ");
            	
            	// Initialize word number
            	int wordNum = 1;
            	
            	// Iterate over array of words
            	for (String word : words) {
          			
            		// Convert word to lowercase and remove punctuation
          			word = word.toLowerCase().replaceAll("[^a-z]", "");
          			if (word.isEmpty()) continue;

          			// If the word is not in the treemap add it to the map
          			if (!wordCounts.containsKey(word)) {
          				WordInfo newWordInfo = new WordInfo(1, lineNum, wordNum);
            			wordCounts.put(word, newWordInfo);
          			} 
          			else {
          				// If the word is in the map increment it's occurence count
          				// and add an occurrence object to the occurrence list
          				WordInfo temp = wordCounts.get(word);
          				temp.incrementOccurenceCount();
          				temp.addOccurrence(lineNum, wordNum);
            			wordCounts.put(word, temp);
          			}
          			// Increment the word number
          			wordNum += 1;
        		}
        	    // Increment the line number
            	lineNum++;
        	}
         	// Close the file
        	input.close();
        }
        // If the file can't be found let the user know
        catch(FileNotFoundException e) {
        	System.out.printf("Error could not find file named \"%s\"\n", FILE_NAME);
        }
        
        // Iterate over the tree map and print out the occurrence count and locations for each word
        for (Map.Entry<String, WordInfo> entry : wordCounts.entrySet()) {
        	WordInfo temp = entry.getValue();
      		System.out.print(entry.getKey() + "(" + temp.toString() + "): [");
      		ArrayList<Occurrence> occurrenceList = temp.getOccurrenceList();
      	
      		int counter = 1;
      		for (Occurrence occurrence : occurrenceList) {
      			if (counter == occurrenceList.size()) {
      				System.out.print(occurrence.toString() + "]");
      			}
      			else {
      				System.out.print(occurrence.toString() + ", ");
      			}
      			
      			counter++;
      		}
      		System.out.print("\n");
    	}

       
      
	}
}