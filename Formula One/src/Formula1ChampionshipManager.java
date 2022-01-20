import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Formula1ChampionshipManager implements ChampionshipManager,Serializable{
    public static ArrayList<Formula1Driver> driver = new ArrayList<>();//TO STORE DRIVERS
    public static ArrayList<Race> raceArrayList = new ArrayList();//TO STORE RACES OF THE DRIVERS
    HashMap<Integer,Integer> pointsMap = new HashMap<>();//HASH MAP TO ASSIGN POINTS TO EACH POSITION



    final int availableRaceSlots=10;//want 11 slots to check on points and position,variable+1 allocated
    public static void main(String[] args) throws IOException {
        Formula1ChampionshipManager test = new Formula1ChampionshipManager();
        File driverFile =new File("formula.txt");
        File raceFile=new File("race.txt");
//        CREATE NEW FILES
        driverFile.createNewFile();
        raceFile.createNewFile();
        test.load(driverFile,raceFile);
        while (true) {
            System.out.println("'A' to add Driver: " + '\n' +
                    "'D' to delete Driver: " + '\n' +
                    "'C' to show change Driver: " + '\n' +
                    "'I' to show statistics of Driver: " + '\n' +
                    "'T' to show table view of Driver: " + '\n' +
                    "'R' to add a race: " + '\n' +
                    "'S' to save: " + '\n' +
                    "'G' for information in GUI: " + '\n' +
                    "'Q' to quit menu: ");

            Scanner scan = new Scanner(System.in);
            System.out.println("Enter your Choice!");
            String userChoice = scan.next();

            switch (userChoice.toUpperCase()) {
                case "A":
                    test.newDriver();
                    break;
                case "D":
                    test.deleteDriver();
                    break;
                case "C":
                    test.changeDriver();
                    break;
                case "I":
                    test.statisticsDriver();
                    break;
                case "T":
                    test.tableViewDriver();
                    break;
                case "R":
                    test.addRace();
                    break;
                case "S" :
                    test.save(driverFile,raceFile);
                    System.exit(0);
                case "G":
                    Gui gui = new Gui();
                    gui.tableMeathod();
                    break;
                case "Q":
                    System.exit(0);

                default:
                    System.out.println("Choice invalid, Please enter again...");
            }
        }

    }
    public ArrayList<Formula1Driver> getDriver() {
        return driver;
    }//METHOD TO RETURN ARRAYLIST FOR GUI

    @Override
    public void newDriver(){
        if(driver.size()>availableRaceSlots){
            System.out.println("All slots filled!");
            return;
        }
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter Driver Name : ");
        String newDriverName = scan.nextLine();
        System.out.println("Enter Driver location : ");
        String newDriverLocation = scan.nextLine();
        System.out.println("Enter Driver Team : ");
        String newDriverTeam = scan.nextLine();
        System.out.println();//EMPTY PRINT TO MAKE SURE IT SCANS PROPERLY
            for (Formula1Driver drive :driver){
                if(newDriverTeam.equals(drive.driverTeam)){
                    System.out.printf("%s is already has a registered driver \n",newDriverTeam);
                    return;
                }
            }
//            PASSING INPUT INTO A NEW FORMULA DRIVER CONSTRUCTOR
        Formula1Driver Driver = new Formula1Driver(newDriverName,newDriverLocation,newDriverTeam);
//            CONSTRUCTOR ADDED INTO THE ARRAYLIST
        driver.add(Driver);

    }
    @Override
    public void changeDriver(){
        if (driver.isEmpty()) {
            System.out.println("Formula 1 Table is empty,and therefore cannot change any drivers");
            return;
        }
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter Driver Team : ");
        String changeTeam= scan.nextLine();
        boolean teamExists=false;
        for (Formula1Driver drive :driver){
            if(changeTeam.equals(drive.driverTeam)){
                teamExists=true;
                System.out.println("Enter New Driver Name : ");
                String changeName= scan.nextLine();
                System.out.println("Enter New Driver Location : ");
                String changeLocation= scan.nextLine();
//              RESETTING STATISTICS TO 0
                drive.setDriverName(changeName);
                drive.setDriverLocation(changeLocation);
                drive.setPoints(0);
                drive.setFirstPositionCount(0);
                drive.setSecondPositionCount(0);
                drive.setThirdPositionCount(0);
                drive.setParticipatedRacesCount(0);
            }
            if (!teamExists){
                System.out.printf("Team %s does not have a driver so it cannot be changed \n",changeTeam);
            }
        }
    }
    @Override
    public void deleteDriver() {
        if (driver.isEmpty()) {
            System.out.println("Formula 1 Table is empty,and therefore cannot delete any drivers");
            return;
        }
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter Driver Name : ");
        String deleteName = scan.nextLine();
        System.out.println("Enter Driver Team : ");
        String deleteTeam = scan.nextLine();
        System.out.println();
        for (int i=0;i< driver.size();i++) {
            for (Formula1Driver drive : driver) {
                if (drive.getDriverName().equals(deleteName) && drive.getDriverTeam().equals(deleteTeam)) {
                    driver.remove(drive);
                    System.out.println("driver removed" + deleteName);
                    statisticsDriver();
                }
            }
        }
    }
    @Override
    public void statisticsDriver(){
        if (driver.isEmpty()) {
            System.out.println("Formula 1 Table is empty,and therefore cannot show any drivers");
            return;
        }
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter Driver Name : ");
        String statsName = scan.nextLine();
        boolean teamExists=false;
            for (Formula1Driver drive : driver) {
                if(drive.getDriverName().equals(statsName)){
                    teamExists=true;
                    System.out.println("Driver Name : "+drive.getDriverName() +"\n"+"Driver Location : "+drive.getDriverLocation()+"\n"
                            +"Driver Team : "+drive.getDriverTeam()+"\n"+"Driver Points : " +drive.getPoints()+ "\n"+"First Positions : "
                            +drive.getFirstPositionCount()+"\n"+"Second Positions : "+drive.getSecondPositionCount()+"\n"+"Third Positions : "
                            +drive.getThirdPositionCount()+"\n"+"Races Participated in : "
                            +drive.getParticipatedRacesCount());
                    System.out.println();
                }
            }if (!teamExists){
            System.out.printf("Driver %s is not in our system \n",statsName);
        }
    }
    @Override
    public void tableViewDriver() {
        boolean empty=false;
        if (driver.isEmpty()) {
            System.out.println("Formula 1 Table is empty,and therefore cannot show any drivers");
            empty=true;
            return;
        }
        if(!empty){
            Collections.sort(driver,new Compare());
            System.out.println("\n------ Drivers and Their Teams ------");
            System.out.println("-------------------------------------");
            System.out.printf("%10s %10s %10s %10s %10s %10s %10s %10s", "NAME", "LOCATION", "TEAM", "FIRST", "SECOND", "THIRD", "POINTS", "RACES COUNT");
            System.out.println();
            for (int i=0; i<driver.size(); i++){
                System.out.format("%10s %10s %10s %10s %10s %10s %10s %10s", driver.get(i).getDriverName(),driver.get(i).getDriverLocation(),driver.get(i).getDriverTeam(),driver.get(i).getFirstPositionCount(),driver.get(i).getSecondPositionCount(),driver.get(i).getThirdPositionCount(),driver.get(i).getPoints(),driver.get(i).getParticipatedRacesCount());
                System.out.println();
            }
        }
    }

    int count=0;//COUNTER TO VALIDATE HOW MANY RACES TAKEN PLACE YET
    final int racePerSeason=10;//HOW MANY RACES PER SEASON AS ASSUMPTION

//    ASSIGNING POINTS TO EACH POSITION ACHIEVED IN A RACE
    public HashMap<Integer, Integer> getPositions() {
        pointsMap.put(1,25);
        pointsMap.put(2,18);
        pointsMap.put(3,15);
        pointsMap.put(4,12);
        pointsMap.put(5,10);
        pointsMap.put(6,8);
        pointsMap.put(7,6);
        pointsMap.put(8,4);
        pointsMap.put(9,2);
        pointsMap.put(10,1);
        pointsMap.put(11,0);
        return pointsMap;
    }
    @Override
    public void addRace() {
        getPositions();
        boolean empty = false;
        if (driver.isEmpty()) {
            System.out.println("Formula 1 Table is empty,and therefore cannot race without any drivers");
            empty = true;
            return;
        }
//      CHECK IF THERE IS ROOM FOR A RACE BY CHECKING AGAINST RACES LEFT IN A SEASON
        if (count > racePerSeason) {
            System.out.println("all races completed for the season");
            System.out.println();
            return;
        }
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter date (format yyyy-mm-dd): ");
        String raceDate;//DATE INPUT SAVED AS A STRING
        Date date = null;//TO PASS THE INPUT STRING TO A DATE CLASS
        Race race = null;
        while (true) {
            raceDate = scan.nextLine();
            System.out.println(raceDate + " this is string date");
//            VALIDATING THE DATE IF IT PASSES THE FORMAT
            try {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                date = simpleDateFormat.parse(raceDate);
//                UNIQUE DATE INPUTS/TO VALIDATE FOR DUPLICATE
                int j;
                for (j = 0; j < raceArrayList.size(); j++) {
                    if (raceArrayList.get(j).getRaceDate().equals(date)) {
                        System.out.println(raceArrayList);
                        System.out.println(date);
                        System.out.println("please enter a new date");
                        return;

                    }
                }
//                COUNT INCREMENTING ADDED AFTER DATE VALIDATION IS SATISFIED
                count++;
            } catch (ParseException e) {
                System.out.println("Invalid date format,enter date in this format(yyyy-mm-dd): ");//exception not handled
            }
            break;
        }

    try {
                Collections.sort(driver,new Compare());
                for (int  q = 0; q <= driver.size(); q++) {
                    int position;
                    race = new Race(driver.get(q).getDriverName(),driver.get(q).getPosition(), date);
                    raceArrayList.add(race);
                    if (count <= racePerSeason) {
                        System.out.println("Enter team  (" + driver.get(q).getDriverTeam() + ")  position in the race :\n");
                        while (!scan.hasNextInt()) {
                            System.out.println("That is not a number");
                            System.out.println("Enter team  (" + driver.get(q).getDriverTeam() + ")  position in the race :\n");
                            scan.next();
                        }
                        position = scan.nextInt();
                        if (pointsMap.containsKey(position)) {
                            driver.get(q).setPoints(driver.get(q).getPoints() + pointsMap.get(position));
                            driver.get(q).setPosition(position);
                            driver.get(q).setParticipatedRacesCount(driver.get(q).getParticipatedRacesCount() + 1);
                            pointsMap.remove(position);//REMOVING THE ALLOCATED POSITION FROM HASHMAP TO KEEP POSITIONS IN THE RACE UNIQUE
                            race.setPosition(position);//ASSIGNING RACE POSITIONS TO THE SUPER CLASS FORMULA1 DRIVER THROUGH THE CHILD CLASS RACE

//                            CHECKING POSITIONS AGAINST 1ST,2ND AND 3RD POSITIONS TO ASSIGN COUNTS FOR EACH
                            if (position == 1) {
                                driver.get(q).setFirstPositionCount(driver.get(q).getFirstPositionCount() + 1);

                            }
                            if (position == 2) {
                                driver.get(q).setSecondPositionCount(driver.get(q).getSecondPositionCount() + 1);

                            }
                            if (position == 3) {
                                driver.get(q).setThirdPositionCount(driver.get(q).getThirdPositionCount() + 1);

                            }
                            driver.get(q).setPosition(driver.get(q).getPosition());
                        }else{
                            System.out.println("position taken for current race");
                        }
                    }
                }
                System.out.println(raceArrayList.size() + "size of race");

    }catch(Exception e){
        System.out.println("That is all the drivers in the championship");
    }
    }
    public void save(File fileName1,File fileName2) throws IOException {

    FileOutputStream fos1 = new FileOutputStream(fileName1);
    ObjectOutputStream oos1 = new ObjectOutputStream(fos1);

    oos1.writeObject(driver);
    oos1.flush();//flushing things that are buffered by the output stream
    oos1.close();
    fos1.close();

    FileOutputStream fos2 = new FileOutputStream(fileName2);
    ObjectOutputStream oos2 = new ObjectOutputStream(fos2);

    oos2.writeObject(raceArrayList);
    oos2.flush();
    oos2.close();
    fos2.close();

    System.out.println("saved to file");
}
    public void load(File fileName1,File fileName2) throws IOException{
    FileInputStream fis1,fis2 ;
    ObjectInputStream ois1,ois2 ;

        try {
            fis1=new FileInputStream(fileName1);
            ois1= new ObjectInputStream(fis1);

            ArrayList<Formula1Driver> savedDriverData = (ArrayList) ois1.readObject();
            driver = (ArrayList<Formula1Driver>) savedDriverData.clone();

            fis1.close();
            ois1.close();

            fis2=new FileInputStream(fileName2);
            ois2= new ObjectInputStream(fis2);

            ArrayList<Race> savedRaceData = (ArrayList) ois2.readObject();
            raceArrayList = (ArrayList<Race>) savedRaceData.clone();
            fis2.close();
            ois2.close();

        }
        catch (Exception e) {
            System.out.println("the file is empty");
        }
}

    public int driverSearch(String name){

        if (driver.isEmpty()) {
            System.out.println("Formula 1 Table is empty,and therefore cannot show any drivers");
            return -2;

        }else{
            for (Driver driverObject : driver) {
                if (driverObject.getDriverName().equals(name)) {
                    return driver.indexOf(driverObject);
                }
            }

        }
        return  -1;
    }
    }

