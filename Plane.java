/*
 * Purpose: Data Structure and Algorithms Project: Plane that takes off of / lands on runways.
 * Status: Complete and thoroughly tested
 * Last update: 12/03/19
 * Submitted: 12/03/19
 * Comment: test suite and sample run attached
 * @author: Devyn Melendez
 * @version: 2019.12.03
 */

public class Plane
{
    private String flightNumber;
    private String destination;
    private String runway;

    /**
     * Constructs a Plane object with its flight number, destination, and the runway it should use.
     * @param fn The flight number.
     * @param d The destination.
     * @param r The runway.
     */
    public Plane(String fn, String d, String r)
    {
        flightNumber = fn;
        destination = d;
        runway = r;
    }

    /**
     * Returns the flight number of the plane.
     * @return The plane's flight number.
     */
    public String getFlightNumber()
    {
        return flightNumber;
    }

    /**
     * Sets a new flight number for the plane.
     * @param fn The new flight number.
     */
    public void setFlightNumber(String fn)
    {
        flightNumber = fn;
    }

    /**
     * Returns the plane's destination.
     * @return The plane's destination.
     */
    public String getDestination()
    {
        return destination;
    }

    /**
     * Sets a new destination for the plane.
     * @param d The new destination.
     */
    public void setDestination(String d)
    {
        destination = d;
    }

    /**
     * Returns the plane's runway to be used.
     * @return The plane's runway.
     */
    public String getRunway()
    {
        return runway;
    }

    /**
     * Sets a new runway for the plane.
     * @param r The new runway.
     */
    public void setRunway(String r)
    {
        runway = r;
    }

    /**
     * Returns an overall description of the plane as a String.
     * @return The plane description.
     */
    public String toString()
    {
        return "Flight" + flightNumber + " to " + destination + ".";
    }
}
