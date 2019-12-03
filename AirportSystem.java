/*
 * Purpose: Data Structure and Algorithms Project: AirportSystem Class
 * Status: Complete and thoroughly tested
 * Last update: 12/03/19
 * Submitted:  12/03/19
 * Comment: test suite and sample run attached
 * @author: Nicholas Bovee
 * @version: 2019.12.03
 */
public class AirportSystem
{
    private String name;
    private ListRAB<String> activeFlights;
    private ListRAB<Plane> waiting;
    private ListRAB<Runway> runways;
    private boolean allowLanding;
    private int takeoffs;
    private int landings;
    private int nextRunway;

    /**
     * Initializes the AirportSystem.
     * @param name name of the airport, used for checking if a flight will be landing.
     * @param enable boolean to see if landings will be enabled. Currently unused.
     */
    public AirportSystem(String name, boolean enable)
    {
        this.name = name;
        allowLanding = enable;
        activeFlights = new ListRAB();
        waiting = new ListRAB();
        runways = new ListRAB();
        takeoffs = 0;
        landings = 0;
        nextRunway = 0;
    }

    /**
     * Checks if the named runway exists in the AirportSystem.
     * @param runName name of the runway to find.
     * @return i integer of -1 to runways.size(), depending on result.
     */
    private int checkRunway(String runName)
    {
        int result = -1;
        for(int i = 0; i < runways.size() && result < 0; i++)
        {
            if(runways.get(i).getName().compareTo(runName) == 0)
            {
                result = i;
            }
        }
        return result;
    }

    /**
     * Checks if the named Runway exists in the AirportSystem.
     * @param runName name of the Runway to find.
     * @return boolean of if the Runway exists.
     */
    public boolean runwayValid(String runName)
    {
        return (checkRunway(runName) < 0) ? false : true;
    }

    /**
     * Removes the next Departure from a Runway of runName, and also removes it from the log of activeFlight numbers.
     * @param runName name of the Runway to find.
     * @return p the next plane in the departure Queue.
     */
    public Plane removeRunwayDeparture(String runName) throws AirportException
    {
        int check = checkRunway(runName);
        if(check >= 0)
        {
            if(runways.get(check).peekDepartures() != null)
            {
                Plane temp = runways.get(check).removeDeparture();
                activeFlights.remove(checkFlights(temp.getFlightNumber()));
                return temp;
            }
            else
            {
                return null;
            }
        }
        else
            return null;
    }

    /**
     * Removes the next Arrival from a Runway of runName, and also removes it from the log of activeFlight numbers.
     * @param runName name of the Runway to find.
     * @return p the next plane in the arrival Queue.
     */
    public Plane removeRunwayArrival(String runName) throws AirportException
    {
        int check = checkRunway(runName);
        if(check >= 0)
        {
            Plane temp = runways.get(check).removeArrival();
            activeFlights.remove(checkFlights(temp.getFlightNumber()));
            return temp;
        }
        else
            return null;
    }

//    /**
//     * Returns the next plane in the waitlist, nondestructively.
//     * @param runName name of the Runway to find.
//     * @return p the next plane in the waiting list.
//     */
//    public Plane peekWaitingPlane(String runName) throws AirportException
//    {
//        int check = checkWaiting(runName);
//        if(check >= 0)
//        {
//            Plane temp = waiting.get(check);
//            return temp;
//        }
//        else
//            return null;
//    }

    /**
     * Returns if the waiting List is empty.
     * @return b the state of the waiting List.
     */
    public boolean waitIsEmpty()
    {
        return waiting.isEmpty();
    }

    /**
     * Returns the waitlist for direct modification (required by Driver.removeRunway().)
     * @return l the List of all currently waiting Planes.
     */
    public ListRAB<Plane> getWaiting()
    {
        return waiting;
    }
    
    /**
     * Returns the index of the flightNumber in the tracking List, or -1.
     * @param flightName a String of the flightNumber being searched for.
     * @return i the index of the flightNumber, or -1.
     */
    private int checkFlights(String flightName)
    {
        int result = -1;
        for(int i = 0; i < activeFlights.size() && result < 0; i++)
        {
            if(activeFlights.get(i).compareTo(flightName) == 0)
            {
                result = i;
            }
        }
        return result;
    }
    
    /**
     * Returns the index of the flightNumber in the waiting List, or -1.
     * @param flightName a String of the flightNumber being searched for.
     * @return i the index of the flightNumber, or -1.
     */
    private int checkWaiting(String flightName)
    {
        int result = -1;
        for(int i = 0; i < waiting.size() && result < 0; i++)
        {
            if(waiting.get(i).getFlightNumber().compareTo(flightName) == 0)
            {
                result = i;
            }
        }
        return result;
    }
    
    /**
     * Returns whether the flightNumber is in the waiting List.
     * @param flightName a String of the flightNumber being searched for.
     * @return b a boolean of whether the flightName is present in the waiting List.
     */
    public boolean waitValid(String flightName)
    {
        return (checkWaiting(flightName) < 0) ? false : true;
    }

    /**
     * Adds a Runway of name runName.
     * @param runName the name of the new Runway to make.
     */
    public void addRunway(String runName)
    {
        int found = checkRunway(runName);
        if(found < 0)
        {
            runways.add(runways.size(),new Runway(runName));
        }
        else
        {
            throw new AirportException("Runway already exists.");
        }
    }

    /**
     * Removes a Runway of name runName.
     * @param runName the name of the Runway to remove.
     */
    public void removeRunway(String runName) throws AirportException
    {
        int found = checkRunway(runName);
        if(found >= 0)
        {
            runways.remove(found);
        }
        else
        {
            throw new AirportException("Runway not found.");
        }
    }

    /**
     * Returns the next Runway in the List, arranged circularly.
     * @return r the next Runway allowed to process a plane.
     */
    private Runway nextRunway()
    {
        Runway result = runways.get(nextRunway);
        nextRunway = ++nextRunway%runways.size();
        return result;
    }
    
    /**
     * Returns the specified Runway for direct modification (required by Driver.removeRunway().)
     * @param runName the name of the runway to find.
     * @return the Runway identified.
     */
    public Runway getRunway(String runName)
    {
        int found = checkRunway(runName);
        if(found >=0)
        {
            return runways.get(found);
        }
        else
        {
            throw new AirportException("Runway not found.");
        }
    }

    /**
     * Returns the next Runway allowed to operate, without incrementing the counter.
     * @param isTakeoff boolean selecting if we are searching for a takeoff or landing operation.
     * @return the Runway identified.
     */
    private Runway peekNextActionableRunway(boolean isTakeoff)
    {
        Runway result = null;
        boolean empty = true;
        int count = nextRunway;
        while(empty == true && count < runways.size()+nextRunway)
        {
            result = runways.get(count);
            if(isTakeoff == true)
            {
                empty = result.noDepartures();
            }
            else
            {
                empty = result.noArrivals();
            }
            count++;
        }
        if(empty == false)
        {
            return result;
        }
        else
        {
            throw new AirportException("No available planes on any runway.");
        }
    }

    /**
     * Returns the next Runway allowed to operate, and increments the counter.
     * @param isTakeoff boolean selecting if we are searching for a takeoff or landing operation.
     * @return the Runway identified.
     */
    private Runway nextActionableRunway(boolean isTakeoff) throws AirportException
    {
        Runway result = null;
        boolean empty = true;
        int count = 0;
        while(empty == true && count < runways.size())
        {
            result = nextRunway();
            if(isTakeoff == true)
            {
                empty = result.noDepartures();
            }
            else
            {
                empty = result.noArrivals();
            }
            count++;
        }
        if(empty == false)
        {
            return result;
        }
        else
        {
            throw new AirportException("No available planes on any runway.");
        }
    }

    /**
     * Returns the next Plane that will be processed, without incrementing the counter.
     * @param isTakeoff boolean selecting if we are searching for a takeoff or landing operation.
     * @return the Plane identified.
     * @throws AirportException
     * @throws Exception 
     */
    public Plane peekNextPlane(boolean isTakeoff) throws AirportException, Exception
    {
        Plane temp = null;
        try {
            if(isTakeoff == true)
            {

                temp =  peekNextActionableRunway(isTakeoff).peekDepartures();
            }
            else
            {
                temp =  peekNextActionableRunway(isTakeoff).peekArrivals();
            }
        }
        catch(Exception e)
        {
        }
        if(temp != null)
        {
            return temp;
        }
        else
        {
            throw new AirportException("No plane on any runway.");
        }
    }

    /**
     * Returns the next Plane that will be processed, and increments the counter.
     * @param isTakeoff boolean selecting if we are searching for a takeoff or landing operation.
     * @return the Plane identified.
     * @throws AirportException
     * @throws Exception 
     */
    public Plane getNextPlane(boolean isTakeoff) throws AirportException
    {
        Runway result = nextActionableRunway(isTakeoff);
        //get next runway that has a plane in the appropriate queue
        if(isTakeoff == true)
        {
            return result.removeDeparture();
        }
        else
        {
            return result.removeArrival();
        }
    }

    /**
     * Moves the designated flightNum from the waiting List to its Runway.
     * @param flightNum the name of the flight to add.
     * @throws AirportException
     * @throws Exception 
     */
    public void reenter(String flightNum) throws AirportException, Exception
    {
        Plane temp = null;
        int num = waiting.size();
        int index = -1;
        for(int i = 0; i< num && index < 0; i++)
        {
            if(waiting.get(i).getFlightNumber().compareTo(flightNum) == 0)
            {
                index = i;
                temp = waiting.get(i);
            }
        }
        if(index >= 0)
        {
            waiting.remove(index);
            activeFlights.remove(checkFlights(flightNum));
            addPlane(temp);
        }
        else
        {
            throw new AirportException("Plane specified not found in wait list.");
        }
    }

    /**
     * Adds the designated Plane to its Runway.
     * @param airplane the Plane to add.
     * @throws AirportException
     * @throws Exception 
     */
    public void addPlane(Plane airplane) throws AirportException, Exception
    {
        String fn = airplane.getFlightNumber();
        int check = checkFlights(fn);
        if(check < 0)
        {
            int run = checkRunway(airplane.getRunway());
            if(run >= 0)
            {
                activeFlights.add(activeFlights.size(),fn);
                Runway temp = runways.get(run);
                if(airplane.getDestination().compareTo(this.name) == 0)
                {
                    temp.addArrival(airplane);
                }
                else
                {
                    temp.addDeparture(airplane);
                }
            }
            else
            {
                throw new AirportException("Runway not found.");
            }
        }
        else
        {
            throw new AirportException("Plane with identical flight number already in system");
        }
    }

    /**
     * Removes the designated Plane from its Runway, either to delete or to add to the waitlist.
     * @param isTakeoff is this a departure or arrival.
     * @param allow will it complete the action or move to the waitlist.
     * @throws AirportException
     * @throws Exception 
     */
    public void processPlane(boolean isTakeoff, boolean allow) throws AirportException
    {
        Plane temp = getNextPlane(isTakeoff);
        if(temp != null)
        {
            if(allow == false)
            {
                waiting.add(waiting.size(),temp);
            }
            else
            {
                activeFlights.remove(checkFlights(temp.getFlightNumber()));
                if(isTakeoff == true)
                {
                    takeoffs++;
                }
                else
                {
                    landings++;
                }
            }
        }
        else
        {
            throw new AirportException("No planes on any runways able to proceed.");
        }
    }

    /**
     * Returns the airport name.
     * @return the String of the airport name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Returns the number of takeoffs.
     * @return the int of total takeoffs
     */
    public int getTakeoffs()
    {
        return takeoffs;
    }

    /**
     * Returns the number of landings.
     * @return the int of total landings
     */
    public int getLandings()
    {
        return landings;
    }

    /**
     * Returns info about the Planes trying to take off.
     * @return the String of each Plane trying to take off, by Runway
     */
    public String displayTakeoff()
    {
        StringBuilder sb = new StringBuilder();
        int num = runways.size();
        for(int i = 0; i<num; i++)
        {
            sb.append(runways.get(i).listDepartures() + "\n\n");
        }
        sb.delete(sb.length()-2,sb.length());
        return sb.toString();
    }

    /**
     * Returns info about the Planes trying to land.
     * @return the String of each Plane trying to land, by Runway
     */
    public String displayLanding()
    {
        StringBuilder sb = new StringBuilder();
        int num = runways.size();
        for(int i = 0; i<num; i++)
        {
            sb.append(runways.get(i).listArrivals() + "\n\n");
        }
        sb.delete(sb.length()-2,sb.length());
        return sb.toString();
    }

    /**
     * Returns info about the Planes waiting to be sent back to a Runway.
     * @return the String of each Plane in the waiting List
     */
    public String displayWaiting()
    {
        StringBuilder sb = new StringBuilder();
        int num = waiting.size();
        if(num == 0)
        {
            return "No flights are waiting for clearance.";
        }
        else
        {
            sb.append("These flights are waiting for clearance:\n");
            for(int i = 0; i<num; i++)
            {
                sb.append(waiting.get(i) + "\n");
            }
            sb.delete(sb.length()-1,sb.length());
            return sb.toString();
        }
    }
}
