/*
 * Purpose: Data Structure and Algorithms Project: Runway for planes to take off of or land on.
 * Status: Complete and thoroughly tested
 * Last update: 12/3/19
 * Submitted: 12/03/19
 * Comment: test suite and sample run attached
 * @author: Devyn Melendez
 * @version: 2019.12.03
 */

public class Runway
{
    private String name;
    private QueueCSLS<Plane> departures;
    private QueueCSLS<Plane> arrivals;

    /**
     * Creates a new runway for planes and sets up the queues for
     * arriving planes and departing planes.
     * @param n The runway's name.
     */
    public Runway(String n)
    {
        name = n;
        departures = new QueueCSLS<Plane>();
        arrivals = new QueueCSLS<Plane>();
    }

    /**
     * Enqueues a new plane that will depart from this runway.
     * @param p The departing plane.
     */
    public void addDeparture(Plane p)
    {
        departures.enqueue(p);
    }

    /**
     * Dequeues the oldest departing plane from this runway.
     * @return The removed plane.
     */
    public Plane removeDeparture()
    {
        return departures.dequeue();
    }

    /**
     * Enqueues a new plane that will arrive on this runway.
     * @param p The arriving plane.
     */
    public void addArrival(Plane p)
    {
        arrivals.enqueue(p);
    }

    /**
     * Dequeues the oldest arriving plane from this runway.
     * @return The removed plane.
     */
    public Plane removeArrival()
    {
        return arrivals.dequeue();
    }

    /**
     * Returns the oldest plane set to depart from this runway.
     * @return The oldest plane.
     */
    public Plane peekDepartures()
    {
        return departures.peek();
    }

    /**
     * Returns the oldest plane set to arrive on this runway.
     * @return The oldest plane.
     */
    public Plane peekArrivals()
    {
        return arrivals.peek();
    }

    /**
     * Returns true if there are no planes set to depart from
     * this runway; returns false otherwise.
     * @return Boolean stating whether there or not there are departing planes.
     */
    public boolean noDepartures()
    {
        return departures.isEmpty();
    }

    /**
     * Returns true if there are no planes set to arrive on
     * this runway; returns true otherwise.
     * @return Boolean stating whether or not there are arriving planes.
     */
    public boolean noArrivals()
    {
        return arrivals.isEmpty();
    }

    /**
     * Returns the runway's name.
     * @return The name.
     */
    public String getName()
    {
        return name;
    }

    /**
     * Sets a new name for the runway.
     * @param n The new name.
     */
    public void setName(String n)
    {
        name = n;
    }

    /**
     * Returns the queue of departing planes.
     * @return The departures.
     */
    public QueueCSLS<Plane> getDepartures()
    {
        return departures;
    }

    /**
     * Sets a new queue for departing planes.
     * @param d The new queue.
     */
    public void setDepartures(QueueCSLS<Plane> d)
    {
        departures = d;
    }

    /**
     * Returns the queue of arriving planes.
     * @return The arrivals.
     */
    public QueueCSLS<Plane> getArrivals()
    {
        return arrivals;
    }

    /**
     * Sets a new queue for arriving planes.
     * @param a The new queue.
     */
    public void setArrivals(QueueCSLS<Plane> a)
    {
        arrivals = a;
    }

    /**
     * Returns a listing of the planes waiting for takeoff on this runway.
     * @return The listing.
     */
    public String listDepartures()
    {
        StringBuilder str = new StringBuilder();
	if(departures.isEmpty())
	{
		str.append("No ");
	}
	else
	{
		str.append("these ");
	}
        str.append("planes are waiting for takeoff on runway " + name + ":");
        str.append("\n" + arrivals.toString());
        return str.toString();
    }

    /**
     * Returns a listing of the planes waiting to land on this runway.
     * @return The listing.
     */
    public String listArrivals()
    {
        StringBuilder str = new StringBuilder();
	if(arrivals.isEmpty())
	{
		str.append("No ");
	}
	else
	{
		str.append("these ");
	}
        str.append("planes are waiting to land on runway " + name + ":");
        str.append("\n" + arrivals.toString());
        return str.toString();
    }

    /**
     * Returns a complete listing of planes waiting to takeoff and land on this runway.
     * @return The listing.
     */
    public String toString()
    {
        StringBuilder str = new StringBuilder();

        str.append(listDepartures());
        str.append("\n");
        str.append(listArrivals());

        return str.toString();
    }
}
