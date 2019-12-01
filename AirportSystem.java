public class AirportSystem
{
    private String name;
    private ListSLS<Plane> waiting;
    private ListSLS<Runway> runways;
    private boolean allowLanding;
    private int takeoffs;
    private int landings;

    public AirportSystem(String name)
    {
        this(name, false);
    }

    public AirportSystem(String name, boolean enable)
    {
        this.name = name;
        allowLanding = enable;
        takeoffs = 0;
        landings = 0;
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

    public void addRunway(String runName)
    {
        runways.add(new Runway(runName));
    }

    public void removeRunway(String runName)
    {
        //find(runway)
        //deleterunway()
    }

    private Runway nextRunway()
    {
        //pop and store runway
        //check if runway has a plane in the correct queue
        //cycle until that is found (otherwise error)
        //queue runway
        //return runway
    }

    public void addPlaneRunway(Plane airplane, Runway run)
    {
        //find( runway)
        //if enable, check takeoff/landing
        //runway.add(plane)
    }

    public void nextPlane(boolean isTakeoff, boolean allow) throws AirportException
    {
        Plane temp = nextRunway().removePlane(isTakeoff);
        if(temp != null)
        {
            if(allow = false)
            {
                waiting.add(temp);
            }
            else
            {
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
}
