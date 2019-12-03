public class AirportSystem
{
    private String name;
    private ListRAB<String> activeFlights;
    private ListRAB<Plane> waiting;
    private ListRAB<Runway> runways;
    private boolean allowLanding;
    private int takeoffs;
    private int landings;
    private int nextRunway; //THIS COULD BE BEST SERVED WITH AN INTERATOR IF POSSIBLE?

    public AirportSystem(String name)
    {
        this(name, false);
    }

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

    public boolean runwayValid(String runName)
    {
        return (checkRunway(runName) < 0) ? false : true;
    }

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

    public boolean waitIsEmpty()
    {
        return waiting.isEmpty();
    }

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

    private Runway nextRunway()
    {
        Runway result = runways.get(nextRunway);
        nextRunway = ++nextRunway%runways.size();
        return result;
    }

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

    private Runway peekRunway()
    {
        return runways.get(nextRunway);
    }

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

    public Plane peekNextPlane(boolean isTakeoff) throws AirportException, Exception
    {
        Plane temp = null;
        try {
            if(isTakeoff == true)
            {

                temp =  peekRunway().peekDepartures();
            }
            else
            {
                temp =  peekRunway().peekArrivals();
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

    public String getName()
    {
        return name;
    }

    public int getTakeoffs()
    {
        return takeoffs;
    }

    public int getLandings()
    {
        return landings;
    }

    public String displayTakeoff()
    {
        StringBuilder sb = new StringBuilder();
        int num = runways.size();
        for(int i = 0; i<num; i++)
        {
            sb.append(runways.get(i).listDepartures() + "\n\n");
        }
        return sb.toString();
    }

    public String displayLanding()
    {
        StringBuilder sb = new StringBuilder();
        int num = runways.size();
        for(int i = 0; i<num; i++)
        {
            sb.append(runways.get(i).listArrivals() + "\n\n");
        }
        return sb.toString();
    }

    public String displayWaiting()
    {
        StringBuilder sb = new StringBuilder();
        int num = waiting.size();
        sb.append("Waiting List: ");
        for(int i = 0; i<num; i++)
        {
            sb.append(waiting.get(i) + "\n\n");
        }
        return sb.toString();
    }
}
