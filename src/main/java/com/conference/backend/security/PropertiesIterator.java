package com.conference.backend.security;

import com.conference.frontend.main.MainView;

import java.io.*;
import java.util.*;

/**
 * Iterates through a list of String prompts
 * @author Lindsey Shorser
 * @author Jonathan Calver
 */
public class PropertiesIterator implements Iterator<String> {
    private List<String> properties = new ArrayList<>();
    private int current = 0;
    private MainView mainView  = new MainView();

    /**
     * The prompt Strings are read from a file,
     * and added to the list of properties.
     *
     * @param filePath the filepath
     */
    public PropertiesIterator(String filePath) {

        //open file and read from it...
        BufferedReader br = null;
        try {
            Scanner myReader = new Scanner(new File(filePath));
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                properties.add(data + ": ");
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            mainView.displayFileIsMissing(filePath);
            e.printStackTrace();
        }
    }

    /**
     * Checks for subsequent prompts.
     * @return true if there is prompt that has not yet been returned.
     */
    @Override
    public boolean hasNext() {
        return current < properties.size();
    }

    /**
     * Returns the next prompt to be printed.
     * @return the next prompt.
     */
    @Override
    public String next() {
        String res;

        // List.get(i) throws an IndexOutBoundsException if
        // we call it with i >= properties.size().
        // But Iterator's next() needs to throw a
        // NoSuchElementException if there are no more elements.
        try {
            res = properties.get(current);
        } catch (IndexOutOfBoundsException e) {
            throw new NoSuchElementException();
        }
        current += 1;
        return res;
    }

    /**
     * Removes the prompt just returned. Unsupported.
     */
    @Override
    public void remove() {
        throw new UnsupportedOperationException("Not supported.");
    }


}
