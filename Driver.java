import java.io.*;

public class Driver
{
    static private AirportSystem airport;
    static BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args)
    {
        try {
            System.out.println("Initializing Airport");
            //System.out.print("Enter Airport name: ");
            // String name = stdin.readLine();
            // System.out.println(name);
            airport = new AirportSystem("airport");
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
        airport.addPlane(new Plane(fn,d,r)); //throw error if FN already exists, or runway doesnt
        System.out.println("Flight " + fn + " is now waiting for takeoff on runway " + r + ".");

    }

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

    static public void landing() throws AirportException, Exception
    {
        Plane temp = airport.getNextPlane(false);
        System.out.print("Is flight " + temp + " clear for landing(Y/N): ");
        String input = stdin.readLine().toUpperCase();
        System.out.println(input);
        boolean allow = (input.compareTo("Y") == 0 ) ? true : false;
        airport.processPlane(false,allow);
    }

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

    static public void closeRunway() throws AirportException, Exception
    {
        System.out.print("Enter the name of the runway to close: ");
        String toRem = stdin.readLine();
        System.out.println(toRem);
        airport.removeRunway(toRem);
        System.out.println(toRem + " was removed.");

    }

    static public void numTakeoff()
    {
        System.out.println(airport.getTakeoffs() + " takeoffs have occurred.");
    }

    static public void displayTakeoff()
    {
        System.out.println(airport.displayTakeoff());
    }

    static public void displayLanding()
    {
        System.out.println(airport.displayLanding());
    }

    static public void displayWaiting()
    {
        System.out.println(airport.displayWaiting());
    }

    static public void numLanding()
    {
        System.out.println(airport.getLandings() + " landings have occurred.");
    }
}
