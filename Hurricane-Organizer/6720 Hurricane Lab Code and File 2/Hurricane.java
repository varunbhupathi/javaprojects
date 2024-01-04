import java.io.*;

/**
 * Models hurricane information, including categories.  
 * Works with HurricaneOrganizer, provides object and comparison skeletons.
 * 
 * @author Susan King
 * @version January 17, 2019
 * @author Varun Bhupathi
 * @version January 5, 2023
 */
public class Hurricane
{
    //Instance variables
    private int year, pressure, speed, category; 
    private String month, name;
    
    /**
     * Initializes a Hurricane object with no information.
     */
    public Hurricane( )
    {

    }

    /**
     * Initializes a Hurricane object with historical information.
     * 
     * @param year      year the hurricane took place
     * @param month     month in String format
     * @param pressure  hurricane's pressure
     * @param speed     hurricane's speed in knots
     * @param name      hurricane's name
     */
    public Hurricane(int year, String month, 
    int pressure, int speed, String name)
    {
        this.year = year;
        this.month = month;
        this.pressure = pressure;
        this.speed = speed;
        this.name = name;
        this.category = determineCategory(speed);
    }

    /**
     * Based upon Saffir/Simpson Hurricane Scale, figures out
     * the category using wind speed in knots.
     * 
     * Use https://en.wikipedia.org/wiki/Saffir%E2%80%93Simpson_scale.
     * 
     * @param knots     wind speed in knots
     * @return Saffir/Simpson Hurricane Scale category
     */
    public int determineCategory(int knots)
    {
        // replace the following line with code
        // that determines the category from speed
        if (knots < 64)
            return 0;
        if (knots < 83)
            return 1;
        if (knots < 96)
            return 2;
        if (knots < 113)
            return 3;
        if (knots < 137)
            return 4;
        return 5;
    }

    //Getters

    /**
     * Responds with name.
     * 
     * @return name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Responds with month.
     * 
     * @return month
     */
    public String getMonth()
    {
        return month;
    }

    /**
     * Responds with pressure.
     * 
     * @return pressure
     */
    public int getPressure()
    {
        return pressure;
    }

    /**
     * Responds with speed.
     * 
     * @return speed
     */
    public int getSpeed()
    {
        return speed;
    }

    /**
     * Responds with year.
     * 
     * @return year
     */
    public int getYear()
    {
        return year;
    }

    /**
     * Responds with category.
     * 
     * @return category
     */
    public int getCategory()
    {
        return category;
    }

    /**
     * Prints out the toString for this Hurricane.
     */
    public void print()
    {
        System.out.println(toString( ));
    }

    /**
     * Formats hurricnae information to be in a single line.
     * 
     * @reutnr formatted information about the hurricane
     */
    public String toString()
    {
        return String.format("%-4d %-5s %-15s %-5d %5d %5d ", 
               year, month, name, category, speed, pressure);
    }

    /**
     * Compares the years of two hurricanes.
     * 
     * @param    the second hurricane to compare
     * 
     * @return  the difference between this huricane's
     *          year and hurricane h's year
     */
    public int compareYearTo(Hurricane h)
    {
        return year - h.getYear();
    }

    /**
     * Compares the names of two hurricanes.
     * 
     * @param    the second hurricane to compare
     * 
     * @return  the String compareTo between this 
     *          huricane's name and hurricane h's name
     */
    public int compareNameTo(Hurricane h)
    {
        return name.toLowerCase().compareTo(h.getName().toLowerCase());
    }

    /**
     * Compares the pressures of two hurricanes.
     * 
     * @param    the second hurricane to compare
     * 
     * @return  the difference between this huricane's
     *          pressure and hurricane h's pressure
     */
    public int comparePressureTo(Hurricane h)
    {
        // replace the following line
        return pressure - h.getPressure();
    }

    /**
     * Compares the speed of two hurricanes.
     * 
     * @param    the second hurricane to compare
     * 
     * @return  the difference between this huricane's
     *          speed and hurricane h's speed
     */
    public int compareSpeedTo(Hurricane h)
    {
        // replace the following line
        return speed - h.getSpeed();
    }

    /**
     * Compares the years of two hurricanes.
     * 
     * @param    the second hurricane to compare
     * 
     * @return  the difference between this huricane's
     *          category and hurricane h's category
     */
    public int compareCategoryTo(Hurricane h)
    {
        // replace the following line
        return category - h.getCategory();
    }
}
