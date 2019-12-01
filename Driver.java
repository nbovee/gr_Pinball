import java.io.*;

public class Driver
{
    static private AirportSystem airport;
    static BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args)
    {
        System.out.println("Initializing Airport");
        System.out.print("Enter Airport name: ");
        String name = stdin.readLine();
        System.out.println(name);
        airport = new AirportSystem(name);
        System.out.print("Enter number of runways: ");
        int numRun = Integer.parseInt(stdin.readLine());
        System.out.println(numRun);
        for(int i = 0; i < numRun; i++)
        {
            System.out.print("Enter name for runway #" + (i+1) + ": ");
            String runName = stdin.readLine();
            System.out.println(runName);
            airport.addRunway(runName);
        }
        System.out.println("Select from the following menu:\n\t0. Exit program.\n\t1. Plane enters the system.\n\t2. Plane takes off.\n\t3. Plane is allowed to re-enter a runway.\n\t4. Runway opens.\n\t5. Runway closes.\n\t6. Display info about planes waiting to take off.\n\t7. Display info about planes waiting to be allowed to re-enter a runway.\n\t8. Display number of planes who have taken off.");
        boolean contin = true;
        int selection;
        while(contin == true)
        {
            System.out.print("Make your selection now: ");
            selection = Integer.parseInt(stdin.readLine());

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
            }

        }
    }

    static public void addPlane()
    {

    }

    static public void takeoff()
    {

    }

    static public void reenter()
    {

    }

    static public void addRunway()
    {

    }

    static public void closeRunway()
    {

    }

    static public void displayTakeoff()
    {

    }

    static public void displayWaiting()
    {

    }

    static public void numTakeoff()
    {

    }
}
