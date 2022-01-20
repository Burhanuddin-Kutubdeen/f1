import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


class Gui implements ActionListener {
    Formula1ChampionshipManager formula1 = new Formula1ChampionshipManager();
    public int randomDate(int startYear, int endYear){
        return startYear+(int) Math.round(Math.random()*(endYear-startYear));
//        MATH.ROUND USED TO ROUND INTEGER TO NEAREST WHOLE NUMBER
    }




    public  void tableMeathod() throws IOException  {
        JFrame f;
        String [][] data =new String [formula1.driver.size()][7];
        Collections.sort(formula1.driver,new Compare());
        for(int i =0; i<formula1.getDriver().size();i++){
            Formula1Driver driver = formula1.getDriver().get(i);
            data[i][0] = driver.getDriverName();
            data[i][1] = driver.getDriverTeam();
            data[i][2] = String.valueOf(driver.getFirstPositionCount());
            data[i][3] = String.valueOf(driver.getSecondPositionCount());
            data[i][4] = String.valueOf(driver.getThirdPositionCount());
            data[i][5] = String.valueOf(driver.getPoints());
            data[i][6] = String.valueOf(driver.getParticipatedRacesCount());
        }

        f=new JFrame();
        String column[]={"Name","Team","First Positions","Second Positions","Third Positions","Points","Participated"};
        JTable jt=new JTable(data,column);
        jt.setBounds(30,40,200,300);
        JScrollPane sp=new JScrollPane(jt);

        JButton ascendingPoints = new JButton("ascending");
        JButton firstPositions = new JButton("First");
        JButton randomRace=new JButton("randomRace!");
        JButton positionRace=new JButton("positionRace!");
        JButton completedRace=new JButton("completedRace!");
        JButton search=new JButton("search!");
        JTextField textField=new JTextField();
        SearchedDriverStat("lewis");/////////////////////
        ascendingPoints.setBounds(20,500,100,30);
        firstPositions.setBounds(120,500,100,30);
        randomRace.setBounds(200,500,100,30);
        positionRace.setBounds(280,500,100,30);
        completedRace.setBounds(360,500,100,30);
        search.setBounds(20,600,100,30);
        textField.setBounds(120,600,100,30);

        ascendingPoints.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//              CREATING AN INSTANCE OF FORMULA1.DRIVER ARRAYYLIST TO SORT
                ArrayList <Formula1Driver> sort;
                sort=formula1.driver;
                Collections.sort(sort,new Compare().reversed());
                for(int i =0; i<formula1.getDriver().size();i++){
                    Formula1Driver driver = sort.get(i);
                    data[i][0] = driver.getDriverName();
                    data[i][1] = driver.getDriverTeam();
                    data[i][2] = String.valueOf(driver.getFirstPositionCount());
                    data[i][3] = String.valueOf(driver.getSecondPositionCount());
                    data[i][4] = String.valueOf(driver.getThirdPositionCount());
                    data[i][5] = String.valueOf(driver.getPoints());
                    data[i][6] = String.valueOf(driver.getParticipatedRacesCount());
                }
                String column[]={"Name","Team","First Positions","Second Positions","Third Positions","Points","Participated"};
                jt.setBounds(30,40,200,300);

            }
        });
        firstPositions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//              CREATING AN INSTANCE OF FORMULA1.DRIVER ARRAYYLIST TO SORT
                ArrayList <Formula1Driver> positionSort;
                positionSort=formula1.driver;
                Collections.sort(positionSort,new Compare());
                for(int i =0; i<formula1.getDriver().size();i++){
                    Formula1Driver driver = formula1.getDriver().get(i);
                    data[i][0] = driver.getDriverName();
                    data[i][1] = driver.getDriverTeam();
                    data[i][2] = String.valueOf(driver.getFirstPositionCount());
                    data[i][3] = String.valueOf(driver.getSecondPositionCount());
                    data[i][4] = String.valueOf(driver.getThirdPositionCount());
                    data[i][5] = String.valueOf(driver.getPoints());
                    data[i][6] = String.valueOf(driver.getParticipatedRacesCount());

                }
                String column[]={"Name","Team","First Positions","Second Positions","Third Positions","Points","Participated"};
                jt.setBounds(30,40,200,300);
            }
        });
        randomRace.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)   {
                boolean raceSlotAvailable=false;
                Date date = dateGenerate();

                boolean empty=false;
                if (formula1.driver.isEmpty()) {
                    System.out.println("Formula 1 Table is empty,and therefore cannot race without any drivers");
                    empty=true;

                    return;
                }
                if (formula1.count>=formula1.racePerSeason){
                    System.out.println("all races completed for the season");
                    System.out.println();
                    return;
                }

                try{
                    while(true){
                        int j;
                        for (j = 0; j < formula1.raceArrayList.size(); j++) {
                            if (formula1.raceArrayList.get(j).getRaceDate().equals(date)) {
                                date =dateGenerate();//GENERATES NEW DATE IF EXISTING DATE IS IN RACE ARRAYLIST
                                return;
                            }
                        }
                        break;
                    }

                    int i;
                    int  randPosi=0;
                    ArrayList<Integer> forRandaomValidation = new ArrayList<>();
//                      VALIDATE UNIQUE POSITION FOR EACH DRIVER ATTEMPTED ,CREATING ARRAYLIST FOR THE RANDOMLY GENERATED POSITIONS(INTENDED FOR UNIQUE POSITION)
                      for ( i = 0; i <= formula1.driver.size(); i++) {
                          if(randomnumberValidationCheck(forRandaomValidation)){
                              do{
                                  randPosi = randomNumberGen(1,11);
                                  forRandaomValidation.add(randPosi);

                              }while(randomnumberValidationCheck(forRandaomValidation));
                          }else{
                              randPosi = randomNumberGen(1,11);
                              forRandaomValidation.add(randPosi);
                          }


                        Race randomRace = new Race(formula1.driver.get(i).getDriverName(),randPosi,date);


                        formula1.raceArrayList.add(randomRace);
                          System.out.println(randomRace+ " race added");//TESTING RANDOM RACE
                        formula1.count++;
                        if (randPosi == 1) {
                            formula1.driver.get(i).setFirstPositionCount(formula1.driver.get(i).getFirstPositionCount() + 1);
                        }
                        if (randPosi == 2) {
                            formula1.driver.get(i).setSecondPositionCount(formula1.driver.get(i).getSecondPositionCount() + 1);
                        }
                        if (randPosi == 3) {
                            formula1.driver.get(i).setThirdPositionCount(formula1.driver.get(i).getThirdPositionCount() + 1);
                        }
                        formula1.driver.get(i).setParticipatedRacesCount(formula1.driver.get(i).getParticipatedRacesCount() +1);
                        }
                }catch (Exception exception){
                }
            }
        });

        f.add(ascendingPoints);
        f.add(firstPositions);
        f.add(randomRace);
        f.add(positionRace);
        f.add(completedRace);
        f.add(search);
        f.add(textField);
        f.add(sp);
        f.setSize(700,700);
        f.setVisible(true);

    }
//    GENERATING RANDOM NUMBERS IN A RANGE METHOD
    public int randomNumberGen(int startNumber, int endNumber){
        return (int) Math.round(Math.random()*(endNumber-startNumber));
    }
//    RANDOM POSITION VALIDATION
    public boolean randomnumberValidationCheck(ArrayList<Integer> list){
        HashSet<Integer> set = new HashSet<>();
         for (int i =0; i < set.size();i++){
            if (set.contains(list.get(i)))
            {
                return true;
            }
         }
        return false;
    }
    public void SearchedDriverStat(String name){
        int x ;
        if (formula1.driverSearch(name) == -1){
            System.out.println("driver is not found");
        }else if(formula1.driverSearch(name) == -2){
            System.out.println("Driver List is empty");
        }
        else{
            ArrayList<Formula1Driver> searchList = new ArrayList<>();
            searchList.add(formula1.driver.get(formula1.driverSearch(name)));
            String[][] data = new String[1][15];
            String[] headers = {"Name", "Location", "Team"};


            x = 0;
            data[x][0]=searchList.get(x).getDriverName();
            data[x][1]=searchList.get(x).getDriverLocation();
            data[x][2]=searchList.get(x).getDriverTeam();
            System.out.println(searchList);////////////


            JTable jt1=new JTable(data,headers);
            JScrollPane pane = new JScrollPane(jt1);
            pane.setBounds(15,525,955,135);
            searchList.clear();

        }


    }

//METHOD TO GENERATE RANDOM DATE AS A DATE OBJECT
    public Date dateGenerate(){
        int day=randomDate(1,28);
        int month=randomDate(1,12);
        int year = randomDate(2020,2021);
        String dateS=year+"-"+month+"-"+day;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        try {
            date=simpleDateFormat.parse(dateS);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
