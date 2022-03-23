package lk.iit.oop.manage;

import lk.iit.oop.Main;
import lk.iit.oop.driver.CarTeam;
import lk.iit.oop.driver.Formula1Driver;

import java.io.Serializable;

public class Formula1ChampionshipManager implements ChampionshipManager, Serializable {
    //Array store Formula1Driver objects....
    private static Formula1Driver[] formula1Drivers;
    private static int []positionPoints = {25, 18, 15, 12, 10, 8, 6, 4, 2, 1 };/*{1st->25, 2nd->18, 3rd->15, 4th->12, 5th->10, 6th->8, 7th->6,
    8th->4, 9th->2, 10th->1}*/
    private static String [][] driverStatistic; // {(id, 50, 18, 30),(id,30,12,20)}

    public static String[][] getDriverStatistic() {
        return driverStatistic;
    }

    public static void setDriverStatistic(String[][] driverStatistic) {
        if(driverStatistic == null) {
            driverStatistic = new String[1][11];
        }
        Formula1ChampionshipManager.driverStatistic = driverStatistic;
    }

    public static int[] getPositionPoints() {
        return positionPoints;
    }

    public static void setPositionPoints(int[] positionPoints) {
        Formula1ChampionshipManager.positionPoints = positionPoints;
    }

    public static Formula1Driver[] getFormula1Drivers() {
        return formula1Drivers;
    }

    public static void setFormula1Drivers(Formula1Driver[] formula1Drivers) {
        Formula1ChampionshipManager.formula1Drivers = formula1Drivers;
    }

    //add driver
    @Override
    public void addDriver(Formula1Driver formula1Driver) {
        if(formula1Drivers == null) {
            formula1Drivers = new Formula1Driver[1];
            formula1Drivers[0] = formula1Driver;
        } else {

            Formula1Driver [] temp = new Formula1Driver[formula1Drivers.length + 1];
            for (int i = 0; i < formula1Drivers.length; i++) {
                temp[i] = formula1Drivers[i];
            }
            temp[temp.length - 1] = formula1Driver;
            formula1Drivers = temp;
        }
    }
    //delete Driver
    @Override
    public void deleteDriver(String driverId) {
        System.out.println("before deleting: " + formula1Drivers.length);
        Formula1Driver[] afterDeleted = new Formula1Driver[formula1Drivers.length - 1];
        String[][] afterDeletedDriverStatistic = new String[100][11];

        for (int i=0; i< formula1Drivers.length; i++) {
            if(driverId.equals(formula1Drivers[i].getDriverId())){
                formula1Drivers[i]=null;


                //to make not null status
                int count = 0;
                for (int j = 0; j < formula1Drivers.length; j++) {

                    if(formula1Drivers[j] == null) {
                        count++;
                        continue;
                    }
                    afterDeleted[j-count] = formula1Drivers[j];
                }
                formula1Drivers = afterDeleted;

                //to delete driver statistic from the selected driver
                for (int r = 0; r < driverStatistic.length; r++) {
                    if(driverId.equals(driverStatistic[r][0])) {
                        driverStatistic[r] = null;

                        int statisticElementCount = 0;
                        for (int j = 0; j < driverStatistic.length; j++) {

                            if(driverStatistic[j] == null) {
                                statisticElementCount++;
                                continue;
                            }
                            afterDeletedDriverStatistic[j-statisticElementCount] = driverStatistic[j];
                        }
                        driverStatistic = afterDeletedDriverStatistic;
                    }
                }

                return;
            }

        }
        System.out.println("Cannot find driver..");
    }
}
