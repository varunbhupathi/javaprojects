import java.io.*;
import java.util.*;

/**
 * Models hurricane information, works with Hurricane class
 * and the user to manipulate an array of hurricane data.
 * 
 * Data came from http://www.aoml.noaa.gov/hrd/tcfaq/E23.html except for 2018.
 * 2018 data came from https://en.wikipedia.org/wiki/2018_Atlantic_hurricane_season.
 *
 * @author Susan King 
 * @version January 17, 2019
 * @version February 10, 2020 Polished code via variable names
 * @author Varun Bhupathi
 * @version January 5, 2023
 */
public class HurricaneOrganizerArray
{
    private Hurricane [] hurricanes;

    /**
     * Makes a HurricaneOrganizerArray object.
     * 
     * @param   the file of the hurricane
     * @throws IOException  if file with the hurricane information cannot be found
     * @return  returns the file length
     */
    public HurricaneOrganizerArray(String filename)throws IOException
    {
        readFile(filename);   
    }

    /**
     * respondes with the length of the parameter file.
     * 
     * @param   the file of the hurricane
     * @throws IOException  if file with the hurricane information cannot be found
     */
    private static int determineFileLength(String filename) throws IOException
    {
        Scanner inFile = new Scanner(new File(filename));
        int counter = 0;

        while(inFile.hasNextLine())
        {
            counter++;
            inFile.nextLine();
        }
        inFile.close();
        return counter;
    }

    /**
     * Sets hurricanes to the length of the file and then adds all the hurricane
     * objects for the file to the huricane array.
     * 
     * @param   the file of the hurricane
     * @throws IOException  if file with the hurricane information cannot be found
     */
    public void readFile(String filename) throws IOException
    {
        hurricanes = new Hurricane [determineFileLength(filename)];
        int hurYear, hurPressure, hurSpeed;
        String hurName, hurMonth;
        Scanner inFile = new Scanner(new File(filename));

        for(int i = 0; i < hurricanes.length; i++)
        {
            hurYear = inFile.nextInt();
            hurMonth = inFile.next();
            hurPressure = inFile.nextInt();
            hurSpeed = inFile.nextInt();
            String tempName = inFile.nextLine();
            hurName = "";
            for(int k = 0; k < tempName.length(); k++)
            {
                char c = tempName.charAt(k);
                if(('a' <= c && c <= 'z') || ('A' <= c && c <='Z'))
                    hurName += c;
            }
            Hurricane h = new Hurricane(hurYear, hurMonth, hurPressure, hurSpeed, hurName);
            hurricanes [i] = h;
        }
        inFile.close();
    }

    /**
     * Responds with the biggest wind speed that 
     * the Hurricanes in array hurricanes contain.
     * 
     * @return   max wind speed
     */
    public int findMaxWindSpeed( )
    {
        Hurricane max = hurricanes[0];
        for (int i = 1; i < hurricanes.length; i++)
        {
            if (max.getSpeed() < hurricanes[i].getSpeed())
                max = hurricanes[i];
        }
        return max.getSpeed();
    }

    /**
     * Responds with the biggest pressure that 
     * the Hurricanes in array hurricanes contain.
     * 
     * @return   max pressure
     */
    public int findMaxPressure( )
    {
        Hurricane max = hurricanes[0];
        for (int i = 1; i < hurricanes.length; i++)
        {
            if (max.getPressure() < hurricanes[i].getPressure())
                max = hurricanes[i];
        }
        return max.getPressure();
    }

    /**
     * Responds with the smallest wind speed that 
     * the Hurricanes in array hurricanes contain.
     * 
     * @return   min wind speed
     */
    public int findMinWindSpeed( )
    {
        Hurricane min = hurricanes[0];
        for (int i = 1; i < hurricanes.length; i++)
        {
            if (min.getSpeed() > hurricanes[i].getSpeed())
                min = hurricanes[i];
        }
        return min.getSpeed();
    }

    /**
     * Responds with the smallest pressure that 
     * the Hurricanes in array hurricanes contain.
     * 
     * @return   min pressure
     */
    public int findMinPressure( )
    {
        Hurricane min = hurricanes[0];
        for (int i = 1; i < hurricanes.length; i++)
        {
            if (min.getPressure() > hurricanes[i].getPressure())
                min = hurricanes[i];
        }
        return min.getPressure();
    }

    /**
     * Calculates the average speed of the hurricanes
     * in the array.
     * 
     * @return   the average wind speed
     */
    public double calculateAverageWindSpeed( )
    {
        double total = hurricanes[0].getSpeed();
        for (int i = 1; i < hurricanes.length; i++)
        {
            total += hurricanes[i].getSpeed();
        }
        return total/hurricanes.length;
    }

    /**
     * Calculates the average pressure of the hurricanes
     * in the array.
     * 
     * @return   the average pressure
     */
    public double calculateAveragePressure( )
    {
        double total = hurricanes[0].getPressure();
        for (int i = 1; i < hurricanes.length; i++)
        {
            total += hurricanes[i].getPressure();
        }
        return total/hurricanes.length;
    }

    /**
     * Calculates the average category level
     * of the hurricanes in the array.
     * 
     * @return   the average category level
     */
    public double calculateAverageCategory( )
    {
        double total = hurricanes[0].getCategory();
        for (int i = 1; i < hurricanes.length; i++)
        {
            total += hurricanes[i].getCategory();
        }
        return total/hurricanes.length;
    }

    /**
     * Sorts ascending based upon the hurricanes' years,
     * The algorithm is selection sort.
     */
    public void sortYears()
    {
        for(int outer = 0; outer < hurricanes.length-1; outer++)
        {
            int mindex = outer;
            for(int inner = outer + 1; inner < hurricanes.length; inner++)
            {    
                if(hurricanes[inner].compareYearTo(hurricanes[mindex])< 0)
                    mindex = inner;
            }
            Hurricane temp = hurricanes[outer];
            hurricanes[outer] = hurricanes[mindex];
            hurricanes[mindex] = temp;
        }
    }

    /**
     * Lexicographically sorts hurricanes based on the hurricanes' name, 
     * using insertion sort.
     */
    public void sortNames()
    {
        // write this code
        for(int outer = 1; outer < hurricanes.length; outer++)
        {
            Hurricane temp = hurricanes[outer];
            int index = outer-1;
            while(index >= 0 && (hurricanes[index].compareNameTo(temp)>0))
            {
                hurricanes[index +1] = hurricanes[index];
                index--;
            }
            hurricanes[index+1] = temp;
        }
    }

    /**
     * Sorts descending based upon the hurricanes' categories,
     * using selection sort.
     */
    public void sortCategories()
    {
        for(int outer = 0; outer < hurricanes.length-1; outer++)
        {
            int mindex = outer;
            for(int inner = outer + 1; inner < hurricanes.length; inner++)
            {    
                if(hurricanes[inner].compareCategoryTo(hurricanes[mindex]) > 0)
                    mindex = inner;
            }
            Hurricane temp = hurricanes[outer];
            hurricanes[outer] = hurricanes[mindex];
            hurricanes[mindex] = temp;
        }
    }  

    /**
     * Sorts descending based upon pressures using a non-recursive merge sort.
     */
    public void sortPressures()
    {
        // write this code
    }

    /**
     * Sorts descending a portion of array based upon pressure, 
     * using selection sort.
     * 
     * @param   start   the first index to start the sort
     * @param   end     one past the last index to sort; hence, end position
     *                  is excluded in the sort
     */
    private void sortPressuresHelper (int start, int end)
    {
        // write this code
    }

    /**
     * Sorts ascending based upon wind speeds using a recursive merge sort. 
     */
    public void sortWindSpeeds(int low, int high)
    {
        // write this code
    }

    /**
     * Merges two consecutive parts of an array, using wind speed as a criteria
     * and a temporary array.  The merge results in an ascending sort between
     * the two given indices.
     * 
     * @precondition the two parts are sorted ascending based upon wind speed
     * 
     * @param low   the starting index of one part of the array.
     *              This index is included in the first half.
     * @param mid   the starting index of the second part of the array.
     *              This index is included in the second half.
     * @param high  the ending index of the second part of the array.  
     *              This index is included in the merge.
     */
    private void mergeWindSpeedsSortHelper(int low, int mid, int high)
    {
        // write this code
    }

    /**
     * Sequential search for all the hurricanes in a given year.
     * 
     * @param   year
     * @return  an array of objects in Hurricane that occured in
     *          the parameter year
     */
    public Hurricane [] searchYear(int year)
    {
        int counter = 0;
        //Find []h length
        // write this code

        Hurricane[] matches = new Hurricane[counter];
        // write the code
        return matches;
    }     

    /**
     * Binary search for a hurricane name.
     * 
     * @param  name   hurricane name being search
     * @return a Hurricane array of all objects in hurricanes with specified name. 
     *         Returns null if there are no matches
     */
    public Hurricane[ ] searchHurricaneName(String name)
    {
        sortNames();
        return searchHurricaneNameHelper(name, 0, hurricanes.length - 1);
    }

    /**
     * Recursive binary search for a hurricane name.  This is the helper
     * for searchHurricaneName.
     * 
     * @precondition  the array must be presorted by the hurricane names
     * 
     * @param   name  hurricane name to search for
     * @param   low   the smallest index that needs to be checked
     * @param   high  the highest index that needs to be checked
     * @return  a Hurricane array of all Hurricane objects with a specified name. 
     *          Returns null if there are no matches
     */
    private Hurricane[ ] searchHurricaneNameHelper(String name, int low , int high)
    {
        // Test for the base case when a match is not found
        return null;

        // Test for match

        // Determine if the potential match is in the 
        // "first half" of the considered items in the array

        // The potential match must be in the
        // "second half" of the considered items in the array

    }

    /**
     * Supports Binary Search method to get the full range of matches.
     * 
     * @precondition  the array must be presorted by the hurricane names
     * 
     * @param   name hurricane name being search for
     * @param   index  the index where a match was found
     * @return  a Hurricane array with objects from hurricanes with specified name. 
     *          Returns null if there are no matches
     */
    private Hurricane[ ] retrieveMatchedNames (String name, int index)
    {
        // Find the start where the matches start:

        // Find the end of the matches:

        // Copy the objects whose names match:

        return null;  // correct this line
    }

    /**
     * Prints out the heading for the table of hurricanes.
     */
    public void printHeader()
    {
        System.out.println("\n\n");
        System.out.printf("%-4s %-5s %-15s %-5s %-5s %-5s \n", 
            "Year", "Mon.", "Name", "Cat.", "Knots", "Pressure");
    }

    /**
     * Calls another method using the hurricanes array as a parameter,
     * and that method prints out the header and then all the information
     * about each hurricane in the array.
     */
    public void printHurricanes()
    {
        printHurricanes(hurricanes);
    }

    /**
     * Prints out the header and then all the information
     * about each hurricane in the the user passes in.
     * Makes a table for the hurricanes.
     * 
     * @param the array of hurricanes that this method
     *        will make a table for
     */
    public void printHurricanes(Hurricane [] hurs)
    {
        if(hurs.length == 0)
        {
            System.out.println("\nVoid of hurricane data.");
            return;
        }
        printHeader();
        for(Hurricane h: hurs)
        {
            System.out.println(h);
        }
    }

    /**
     * Prints out the select screen that user picks their
     * option from.
     */
    public void printMenu()
    {
        System.out.println("\n\nEnter option: ");
        System.out.println("\t 1 - Print all hurricane data \n" +
            "\t 2 - Print maximum and minimum data \n" +
            "\t 3 - Print averages \n" +
            "\t 4 - Sort hurricanes by year \n" +
            "\t 5 - Sort hurricanes by name \n" +
            "\t 6 - Sort hurricanes by category, descending \n" +
            "\t 7 - Sort hurricanes by pressure, descending \n" +
            "\t 8 - Sort hurricanes by speed \n" + 
            "\t 9 - Search for hurricanes for a given year \n" +
            "\t10 - Search for a given hurricane by name \n" +
            "\t11 - Quit \n");
    }

    /**
     * Uses the various max and min methods and then formats
     * all four data point to be printed out.
     * Prints the max and min data.
     */
    public void printMaxAndMin( )
    {
        System.out.println("Maximum wind speed is " + 
            findMaxWindSpeed( ) +
            " knots and minimum wind speed is " + 
            findMinWindSpeed( ) + " knots.");
        System.out.println("Maximum pressure is " + 
            findMaxPressure( ) +
            " and minimum pressure is " + 
            findMinPressure( ) + ".");
    }

    /**
     * Formats and prints out the average speed, pressure and category
     * from the hurricanes array.
     */
    public void printAverages( )
    {
        System.out.printf("Average wind speed is %5.2f knots. \n" , 
            calculateAverageWindSpeed( ));
        System.out.printf("Average pressure is %5.2f. \n" , 
            calculateAveragePressure( ));
        System.out.printf("Average category is %5.2f. \n" , 
            calculateAverageCategory( ));
    }

    /**
     * Prompts the user with their choices and then exuctes the
     * choice they selection. Will return false if the user wants to
     * keep selcting choices and true when the user wants to quit.
     * 
     * @return false      if the user wants to keep executing other
     *                    code, otherwise:
     *         true       when the user is ready to quit the program
     */
    public boolean interactWithUser( )
    {
        Scanner in = new Scanner(System.in);
        boolean done = false;
        printMenu();
        int choice = in.nextInt();
        // clear the input buffer
        in.nextLine();

        if(choice == 1)
        {
            printHurricanes( ); 
        }
        else if (choice == 2)
        {
            printMaxAndMin( );
        }
        else if (choice == 3)
        {
            printAverages( );
        }
        else if(choice == 4)
        {
            sortYears();
            printHurricanes( );
        }
        else if(choice == 5)
        {
            sortNames();
            printHurricanes( );
        }
        else if(choice == 6)
        {
            sortCategories();
            printHurricanes( );
        }
        else if(choice == 7)
        {
            sortPressures();
            printHurricanes( );
        }
        else if(choice == 8)
        {
            sortWindSpeeds(0, hurricanes.length - 1);
            printHurricanes( );
        }
        else if(choice == 9)
        {
            System.out.print("\n\tWhich year do you want to search for?\n\t");
            int year = in.nextInt();
            printHurricanes(searchYear(year));
        }
        else if(choice == 10)
        {
            System.out.print("\n\tWhich name do you want to search for?\n\t");
            String name = in.next();
            printHurricanes(searchHurricaneName(name));
        }
        else if (choice == 11)
        {
            done = true;
        }  
        return done;
    }

    /**
     * Creates a HurricaneOrganizerArray object.
     * Keeps interacting with the user using that object,
     * until the user decides to quit.
     * 
     * @param args  user's information from the command line
     * 
     * @throws IOException  if file with the hurricane information cannot be found
     */
    public static void main (String [] args) throws IOException
    {
        HurricaneOrganizerArray cane = new HurricaneOrganizerArray("hurricanedata.txt");
        boolean areWeDoneYet = false;
        while ( ! areWeDoneYet)
        {
            areWeDoneYet = cane.interactWithUser( );    
        }
    }
}