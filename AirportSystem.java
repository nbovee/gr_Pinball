public class AirportSystem
{
    private String name;
    private ListSLS<Plane> waiting;
    private ListSLS<Runway> runways;
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
        takeoffs = 0;
        landings = 0;
	nextRunway = 0;
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
	boolean found = runways(findKey(runName));
	if(found != true)
	{
        runways.add(new Runway(runName));
	}
	else
	{
	throw new AirportException("Runway already exists.");	
	}
    }

    public void removeRunway(String runName)
    {
	boolean found = runways(findKey(runName));
	if(found == true)
	{
        runways.removeKey(runName);
	}
	else
	{
	throw new AirportException("Runway not found.");	
	}
    }

    private Runway nextRunway()
    {
        //pop and store runway
        //check if runway has a plane in the correct queue
        //cycle until that is found (otherwise error)
        //queue runway
        //return runway
    }

    public Plane getNextPlane(boolean isTakeoff)
    {
	Plane result;
	//get next runway that has a plane in the appropriate queue	

	return result;
    }

    public void reenter(String flightNum)
    {
	//match flight num in plane object
	//add to runway in Plane object
    }

    public void addPlane(Plane airplane)
    {
	
        //find( runway)
        //if enable, check takeoff/landing
        //runway.add(plane)
    }

    public void processPlane(boolean isTakeoff, boolean allow) throws AirportException
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
