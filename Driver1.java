/*
 * Purpose: Data Structure and Algorithms Project: Driver Class
 * Status: Complete and thoroughly tested
 * Last update: 12/03/19
 * Submitted:  12/03/19
 * Comment: test suite and sample run attached
 * @author: Nicholas Bovee
 * @version: 2019.12.03
 */
import java.io.*;

public class Driver1
{
    static private AirportSystem airport;
    static BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
	
    /**
     * Operates the AirportSystem Class.
     */
    public static void main(String[] args)
    {
        try {
            System.out.println("Initializing Airport");
            System.out.print("Enter Airport name: ");
            String name = stdin.readLine();
            System.out.println(name);
            airport = new AirportSystem(name,false);
            System.out.print("Enter number of runways: ");
            int numRun = Integer.parseInt(stdin.readLine());
            System.out.println(numRun);
            for(int i = 0; i < numRun; i++)
            {
                try {
                    System.out.print("Enter name for runway #" + (i+1) + ": ");
                    String runName = stdin.readLine();
                    System.out.println(runName);
                    airport.addRunway(runName);
                }
                catch(Exception e)
                {
                    System.out.println(e.getMessage());
                }
            }
            System.out.println("Select from the following menu:\n\t0. Exit program.\n\t1. Plane enters the system.\n\t2. Plane attempts takes off.\n\t3. Plane is allowed to re-enter a runway.\n\t4. Runway opens.\n\t5. Runway closes.\n\t6. Display info about planes waiting to take off.\n\t7. Display info about planes waiting to be allowed to re-enter a runway.\n\t8. Display number of planes who have taken off.\n\t9. Plane attemps landing.\n\t10. Display info about planes waiting to land.\n\t11. Display number of planes who have landed.");
            boolean contin = true;
            int selection;
            while(contin == true)
            {
                try {
                    System.out.print("Make your selection now: ");
                    selection = Integer.parseInt(stdin.readLine());
                    System.out.println(selection);
                    switch (selection)
                    {
                    case 0:
                        contin = false;
                        break;
                    case 1:
                        addPlane();
                        break;
                    case 2:
                        takeoff();
                        break;
                    case 3:
                        reenter();
                        break;
                    case 4:
                        addRunway();
                        break;
                    case 5:
                        closeRunway();
                        break;
                    case 6:
                        displayTakeoff();
                        break;
                    case 7:
                        displayWaiting();
                        break;
                    case 8:
                        numTakeoff();
                        break;
                    case 9:
                        landing();
                        break;
                    case 10:
                        displayLanding();
                        break;
                    case 11:
                        numLanding();
                        break;
                    }
                    System.out.println();
                }
                catch(Exception e)
                {
                    System.out.println(e.getMessage());
                }
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

	/**
     * Adds a plane dynamically to the AirportSystem.
     */
    static public void addPlane() throws AirportException, Exception
    {
        System.out.print("Enter flight number: ");
        String fn = stdin.readLine();
        System.out.println(fn);
        System.out.print("Enter destination: ");
        String d = stdin.readLine();
        System.out.println(d);
        String r;
        boolean val = true;
        do
        {
            System.out.print("Enter runway: ");
            r = stdin.readLine();
            System.out.println(r);
            val = airport.runwayValid(r);
            if(val != true)
            {
                System.out.println("No such runway.");
            }
        } while(val != true);
        airport.addPlane(new Plane(fn,d,r));
        System.out.println(airport.getName() + " " + airport.getName().compareTo(d));
        System.out.println("Flight " + fn + " is now waiting for clearance on runway " + r + ".");

    }

    /**
     * processes a plane for takeoff in the AirportSystem.
     */
    static public void takeoff() throws AirportException, QueueException, Exception
    {
        Plane temp = airport.peekNextPlane(true);
        System.out.print("Is " + temp + " clear for takeoff(Y/N): ");
        String input = stdin.readLine().toUpperCase();
        System.out.println(input);
        boolean allow = (input.compareTo("Y") == 0 ) ? true : false;
        airport.processPlane(true,allow);
        if(allow ==true)
        {
            System.out.println(temp.getFlightNumber() + " has taken off from runway " + temp.getRunway() + ".");
        }
        else
        {
            System.out.println(temp.getFlightNumber() + " has been denied clearance.");
        }
    }

    /**
     * processes a plane for landing in the AirportSystem.
     */
    static public void landing() throws AirportException, Exception
    {
        Plane temp = airport.peekNextPlane(true);
        System.out.print("Is " + temp + " clear for landing(Y/N): ");
        String input = stdin.readLine().toUpperCase();
        System.out.println(input);
        boolean allow = (input.compareTo("Y") == 0 ) ? true : false;
        airport.processPlane(false,allow);
        if(allow ==true)
        {
            System.out.println(temp.getFlightNumber() + " has landed on runway " + temp.getRunway() + ".");
        }
        else
        {
            System.out.println(temp.getFlightNumber() + " has been denied clearance.");
        }
    }

    /**
     * re-adds a plane to it's Runway and departure/arrival Queue in AirportSystem.
     */
    static public void reenter() throws AirportException, Exception
    {
        if(airport.waitIsEmpty() != true)
        {
            String toAdd;
            boolean val;
            do
            {
                System.out.print("Enter the the flight number: ");
                toAdd = stdin.readLine();
                System.out.println(toAdd);
                val = airport.waitValid(toAdd);
                if(val == false)
                {
                    System.out.println(toAdd + " is not waiting for clearance.");
                }
            }
            while(val == false);
            airport.reenter(toAdd);
        }
        else
        {
            throw new AirportException("No planes in wait list.");
        }

    }

    /**
     * Adds a Runway to the AirportSystem.
     */
    static public void addRunway() throws AirportException, Exception
    {
        String toAdd;
        boolean val = true;
        do
        {
            System.out.print("Enter the name of the runway to open: ");
            toAdd = stdin.readLine();
            System.out.println(toAdd);
            val = airport.runwayValid(toAdd);
            if(val == true)
            {
                System.out.println("Runway already exists.");
            }
        } while(val == true);
        airport.addRunway(toAdd);
        System.out.println(toAdd + " was added.");

    }

    /**
     * Closes a Runway in the AirportSystem, moving assigned Planes to other Runways.
     */
    static public void closeRunway() throws AirportException, Exception
    {
        String toRem;
        boolean val = true;
        do
        {
            System.out.print("Enter the name of the runway to close: ");
            toRem = stdin.readLine();
            System.out.println(toRem);
            val = airport.runwayValid(toRem);
            if(val == false)
            {
                System.out.println("Runway does not exist.");
            }
        }
        while(val == false);
        System.out.println(toRem);

        Runway run = airport.getRunway(toRem);
        Plane temp = null;
        while(run.noDepartures() == false)
        {
            temp = airport.removeRunwayDeparture(toRem);
            String newRun = null;
            int comp = 0;
            boolean exists = false;
            do
            {
                System.out.print("Enter new runway for " + temp.getFlightNumber() + ": ");
                newRun = stdin.readLine();
                System.out.println(newRun);
                exists = airport.runwayValid(newRun);
                comp = newRun.compareTo(toRem);
                if(comp == 0)
                {
                    System.out.println("This is the runway that will close.");
                }
                if(exists == false)
                {
                    System.out.println("No such runway.");
                }
            } while(comp == 0 || exists == false);
            temp.setRunway(newRun);
            airport.addPlane(temp);
            System.out.println(temp.getFlightNumber() + " is now assigned to runway " + newRun + ".");
        }
        while(run.noArrivals() == false)
        {
            temp = airport.removeRunwayDeparture(toRem);
            String newRun = null;
            int comp = 0;
            boolean exists = false;
            do
            {
                System.out.print("Enter new runway for " + temp.getFlightNumber() + ": ");
                newRun = stdin.readLine();
                System.out.println(newRun);
                exists = airport.runwayValid(newRun);
                comp = newRun.compareTo(toRem);
                if(comp == 0)
                {
                    System.out.println("This is the runway that will close.");
                }
                if(exists == false)
                {
                    System.out.println("No such runway.");
                }
            } while(comp == 0 || exists == false);
            temp.setRunway(newRun);
            airport.addPlane(temp);
            System.out.println(temp.getFlightNumber() + " is now assigned to runway " + newRun + ".");
        }

        ListRAB<Plane> wait = airport.getWaiting();
        for(int i = 0; i<wait.size(); i++)
        {
            temp = wait.get(i);
            if(temp.getRunway().compareTo(toRem) == 0)
            {
                String newRun = null;
                int comp = 0;
                boolean exists = false;
                do
                {
                    System.out.print("Enter new runway for " + temp.getFlightNumber() + ": ");
                    newRun = stdin.readLine();
                    System.out.println(newRun);
                    exists = airport.runwayValid(newRun);
                    comp = newRun.compareTo(toRem);
                    if(comp == 0)
                    {
                        System.out.println("This is the runway that will close.");
                    }
                    if(exists == false)
                    {
                        System.out.println("No such runway.");
                    }
                } while(comp == 0 || exists == false);
                temp.setRunway(newRun);
                System.out.println(temp.getFlightNumber() + " is now assigned to runway " + newRun);
            }
        }

        airport.removeRunway(toRem);
        System.out.println(toRem + " has been closed.");

    }

    /**
     * Prints info about the Planes trying to takeoff.
     */
    static public void displayTakeoff()
    {
        System.out.println(airport.displayTakeoff());
    }

    /**
     * Prints info about the Planes trying to land.
     */
    static public void displayLanding()
    {
        System.out.println(airport.displayLanding());
    }

    /**
     * Prints info about the planes currently waiting to be added to a Runway.
     */
    static public void displayWaiting()
    {
        System.out.println(airport.displayWaiting());
    }

    /**
     * Prints the number of takeoffs.
     */
    static public void numTakeoff()
    {
        System.out.println(airport.getTakeoffs() + " takeoffs have occurred.");
    }
    
    /**
     * Prints the number of landings.
     */
    static public void numLanding()
    {
        System.out.println(airport.getLandings() + " landings have occurred.");
    }
}
