public Driver
{
    private String name;
    private List runways;


    public static void main(String[] args)
    {
        System.out.println("Initializing Airport");
        System.out.print("Enter Airport name: ");
        name = stdin.readLine();
        System.out.println(name);
        System.out.print("Enter number of runways: ");
        int numRun = Integer.parseInt(stdin.readLine());
        System.out.println(numRun);
        for(int i = 0; i < numRun; i++)
        {
            System.out.print("Enter name for runway #" + (i+1) + ": ");
            String runName = stdin.readLine();
            System.out.println(runName);
            runways.add(new Runway(runName, i));
        }
        System.out.println("Select from the following menu:\n\t0. Exit program.\n\t1. Plane enters the system.\n\t2. Plane takes off.\n\t3. Plane is allowed to re-enter a runway.\n\t4. Runway opens.\n\t5. Runway closes.\n\t6. Display info about planes waiting to take off.\n\t7. Display info about planes waiting to be allowed to re-enter a runway.\n\t8. Display number of planes who have taken off.");
        boolean contin = true;
        while(contin == true)
        {
            int selection;
            System.out.print("Make your selection now: ");
            selection = stdin.readLine();

            switch (selection)
            {
            case 0:
                ;
                break;
            case 1:
                ;
                break;
            case 2:
                ;
                break;
            case 3:
                ;
                break;
            case 4:
                ;
                break;
            case 5:
                ;
                break;
            case 6:
                ;
                break;
            case 7:
                ;
                break;
            case 8:
                ;
                break;
            }

        }
    }
}
