package lk.iit.oop;

import lk.iit.oop.driver.Formula1Driver;
import lk.iit.oop.manage.Formula1ChampionshipManager;
import sun.text.resources.cldr.lag.FormatData_lag;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterGraphics;
import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.function.DoubleToIntFunction;

public class Main {
    private static final int totalPoints = 0;
    private static int count = 0;
    private static int total = 0;
    private static int statTot = 0;
    private static final Scanner scanner = new Scanner(System.in);
    private static int driverCount = 0;
    private static String[][] temp = new String[100][11];
    private static Formula1Driver[] tempDriver;
    private static Formula1Driver[] tempDriver1;
    static int[] driversPointTotal;
    static JTable jt;
    static JTable jt1;
    static JScrollPane sp;

    //Create gui
    public static void printDriverDetailOnTable() {
        JFrame f = new JFrame();
        f.setLayout(null);
        JButton btn1 = new JButton("Click");
        JTextField txt1 = new JTextField("To genarate random race");

        tempDriver = Formula1ChampionshipManager.getFormula1Drivers();
        String actualDriver[][] = new String[tempDriver.length][15];

       // tempDriver1 = Formula1ChampionshipManager.getFormula1Drivers();
        String randomRace [][] = new String[tempDriver.length][100];


        for (int i = 0; i < actualDriver.length; i++) {
            if (tempDriver[i] == null) {
                continue;
            }
            actualDriver[i][0] = tempDriver[i].getDriverId();
            actualDriver[i][1] = tempDriver[i].getDriverName();
            actualDriver[i][2] = tempDriver[i].getLocation();
            actualDriver[i][3] = tempDriver[i].getTeam();
            actualDriver[i][4] = String.valueOf(Integer.parseInt(Formula1ChampionshipManager.getDriverStatistic()[i][1]) / 25);
            actualDriver[i][5] = String.valueOf(Integer.parseInt(Formula1ChampionshipManager.getDriverStatistic()[i][2]) / 18);
            actualDriver[i][6] = String.valueOf(Integer.parseInt(Formula1ChampionshipManager.getDriverStatistic()[i][3]) / 15);
            actualDriver[i][7] = String.valueOf(Integer.parseInt(Formula1ChampionshipManager.getDriverStatistic()[i][4]) / 12);
            actualDriver[i][8] = String.valueOf(Integer.parseInt(Formula1ChampionshipManager.getDriverStatistic()[i][5]) / 10);
            actualDriver[i][9] = String.valueOf(Integer.parseInt(Formula1ChampionshipManager.getDriverStatistic()[i][6]) / 8);
            actualDriver[i][10] = String.valueOf(Integer.parseInt(Formula1ChampionshipManager.getDriverStatistic()[i][7]) / 6);
            actualDriver[i][11] = String.valueOf(Integer.parseInt(Formula1ChampionshipManager.getDriverStatistic()[i][8]) / 4);
            actualDriver[i][12] = String.valueOf(Integer.parseInt(Formula1ChampionshipManager.getDriverStatistic()[i][9]) / 2);
            actualDriver[i][13] = String.valueOf(Integer.parseInt(Formula1ChampionshipManager.getDriverStatistic()[i][10]));
            actualDriver[i][14] = String.valueOf(driversPointTotal[i]);

        }

        String[] column = {"Id", "Name", "Location", "Team Name", "1st", "2nd", "3rd", "4th", "5th", "6th", "7th"
                , "8th", "9th", "10th", "Total Point"};

        //create header label
        JLabel hLabel = new JLabel("Formula Driver Management");
        hLabel.setFont(new Font("Serif", Font.PLAIN, 45));
        hLabel.setForeground(Color.BLUE);

        //Create a combobox
        String select[] = {"By points drivers descending", "By points drivers ascending", "By first position"};
        JComboBox cb = new JComboBox(select);
        cb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedItem = (String) cb.getSelectedItem();
                String id[] = new String[tempDriver.length];
                if (selectedItem.equals("By first position")) {
                    //to sort by first position count
                    for (int j = actualDriver.length - 1; j > 0; j--) {
                        for (int i = 0; i < j; i++) {
                            if (Integer.parseInt(actualDriver[i][4]) < Integer.parseInt(actualDriver[i + 1][4])) {
                                String[] t = actualDriver[i];
                                actualDriver[i] = actualDriver[i + 1];
                                actualDriver[i + 1] = t;
                            }
                        }
                    }
                    jt = new JTable(actualDriver, column);
                    sp = new JScrollPane(jt);
                    sp.setBounds(45, 175, 1000, 200);

                    f.add(sp);
                } else if (selectedItem.equals("By points drivers descending")) {
                    //to sort by total point by desc
                    for (int j = actualDriver.length - 1; j > 0; j--) {
                        for (int i = 0; i < j; i++) {
                            if (Integer.parseInt(actualDriver[i][14]) < Integer.parseInt(actualDriver[i + 1][14])) {
                                String[] t = actualDriver[i];
                                actualDriver[i] = actualDriver[i + 1];
                                actualDriver[i + 1] = t;
                            }
                        }
                    }

                    jt = new JTable(actualDriver, column);
                    sp = new JScrollPane(jt);
                    sp.setBounds(45, 175, 1000, 200);
                    f.add(sp);
                } else if (selectedItem.equals("By points drivers ascending")) {
                    //to sort by total point by asc
                    for (int j = actualDriver.length - 1; j > 0; j--) {
                        for (int i = 0; i < j; i++) {
                            if (Integer.parseInt(actualDriver[i][14]) > Integer.parseInt(actualDriver[i + 1][14])) {
                                String[] t = actualDriver[i];
                                actualDriver[i] = actualDriver[i + 1];
                                actualDriver[i + 1] = t;
                            }
                        }
                    }
                    jt = new JTable(actualDriver, column);
                    sp = new JScrollPane(jt);
                    sp.setBounds(45, 175, 1000, 200);

                    f.add(sp);
                }
            }
        });

        jt = new JTable(actualDriver, column);
        jt.setAutoCreateRowSorter(true);

        sp = new JScrollPane(jt);

        sp.setBounds(45, 175, 1260, 300);
        cb.setBounds(45, 130, 200, 30);
        hLabel.setBounds(370, 5, 800, 100);
        //Center table data
        DefaultTableCellRenderer dTableCellRenderer = new DefaultTableCellRenderer();
        dTableCellRenderer.setHorizontalAlignment(JLabel.CENTER);

        for (int i = 0; i < 15; i++) {
            jt.getColumnModel().getColumn(i).setCellRenderer(dTableCellRenderer);
        }

        //set action listner to the button
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int driverPosition = 0;
                int oldPosition = 0;
                for (int i = 0; i < Formula1ChampionshipManager.getFormula1Drivers().length; i++) {
              L2:     for (int j = 0; j < 9; j++) {
                        driverPosition = new Random().nextInt(10);
                          if(driverPosition == oldPosition) {
                              continue L2;
                          }
                         oldPosition = driverPosition;

                        Formula1ChampionshipManager.getDriverStatistic()[i][j + 1] = String.valueOf(Integer.parseInt(Formula1ChampionshipManager.getDriverStatistic()[i][j + 1]) +
                                Formula1ChampionshipManager.getPositionPoints()[driverPosition]);
                    }

                }
                f.dispose();
                printDriverDetailOnTable();

            }
        });
        f.add(cb);
        f.add(sp);
        f.add(hLabel);
        txt1.setBounds(260, 130, 210, 30);
        txt1.setBackground(Color.GREEN);
        btn1.setBounds(485, 130, 100,30);
        btn1.setBackground( Color.BLUE);
        btn1.setBorder(new EmptyBorder(5,15, 5, 15));
        txt1.setBorder(new EmptyBorder(5,15, 5, 15));
        btn1.setVerticalTextPosition(JButton.CENTER);
        btn1.setFont(new Font("Courier",Font.BOLD, 14));
        txt1.setFont(new Font("Courier",Font.BOLD, 14));
        txt1.setForeground(Color.DARK_GRAY);
        btn1.setForeground(Color.WHITE);
        f.add(btn1);
        f.add(txt1);

        //to increase table row
        jt.setRowHeight(jt.getRowHeight() + 10);

        f.setSize(1370, 700);
        f.setTitle("Formula Management");
        f.setVisible(true);
    }

    //To save all the driver details--> file handling part
    public static void saveToFile() {
        File file = new File("driverDetails.txt");
        File file2 = new File("driverStatistic.txt");
        File file3 = new File("driverPointTotal.txt");

        try {
            //for the driver details like id, name, team name
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream obj = new ObjectOutputStream(fileOutputStream);

            //for the driver statistic
            FileOutputStream fileOutputStream2 = new FileOutputStream(file2);
            ObjectOutputStream obj2 = new ObjectOutputStream(fileOutputStream2);

            //for the driver point Total
            FileOutputStream fileOutputStream3 = new FileOutputStream(file3);
            ObjectOutputStream obj3 = new ObjectOutputStream(fileOutputStream3);

            //for the driver details like id, name, team name
            for (int i = 0; i < Formula1ChampionshipManager.getFormula1Drivers().length; i++) {
                if (Formula1ChampionshipManager.getFormula1Drivers()[i] == null) {
                    continue;
                }
                obj.writeObject(Formula1ChampionshipManager.getFormula1Drivers()[i]);
            }

            //for the driver statistic
            obj2.writeObject(Formula1ChampionshipManager.getDriverStatistic());

            //for the driver point Total
            System.out.println("save driver point " + driversPointTotal.length);
            obj3.writeObject(driversPointTotal);

            obj3.close();
            obj2.close();
            obj.close();

//           homeScreen();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //get driver details from objects
    public static void getDetailsFromObject() {
        File file = new File("driverDetails.txt");//Driver id, name and team name
        File file2 = new File("driverStatistic.txt");//driver statistic
        File file3 = new File("driverPointTotal.txt");//archived position marks

        try {
            //for the driver details like id, name, team name
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            //for the driver statistic
            FileInputStream fileInputStream2 = new FileInputStream(file2);
            ObjectInputStream objectInputStream2 = new ObjectInputStream(fileInputStream2);

            //for the driver point Total
            FileInputStream fileInputStream3 = new FileInputStream(file3);
            ObjectInputStream objectInputStream3 = new ObjectInputStream(fileInputStream3);

            //for the driver details like id, name, team name
            try {
                Formula1Driver obj;
                while ((obj = (Formula1Driver) objectInputStream.readObject()) != null) {
                    Formula1ChampionshipManager formula1ChampionshipManager = new Formula1ChampionshipManager();
                    formula1ChampionshipManager.addDriver(obj);
                }
            } catch (EOFException e) {

            }

            //for the driver statistic
            String[][] o = (String[][]) objectInputStream2.readObject();
            Formula1ChampionshipManager.setDriverStatistic(o);

            //for the driver point Total
            int[] pointTotal = (int[]) objectInputStream3.readObject();
            driversPointTotal = pointTotal;
            System.out.println("pint total length" + pointTotal.length);

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    //to display driver details
    public static void displayDriverDetailsInTable() {
        int valueCount = 0;
        if (Formula1ChampionshipManager.getDriverStatistic() != null) {
            if (driversPointTotal == null) {
                for (int i = 0; i < Formula1ChampionshipManager.getDriverStatistic().length; i++) {
                    if (null != Formula1ChampionshipManager.getDriverStatistic()[i][0]) {
                        valueCount++;
                    }
                }
                driversPointTotal = new int[valueCount];
            }


            L1:
            for (int i = 0; i < valueCount; i++) {
                for (int j = 1; j < Formula1ChampionshipManager.getDriverStatistic()[i].length; j++) {
                    statTot += Integer.parseInt(Formula1ChampionshipManager.getDriverStatistic()[i][j]);
                }
                driversPointTotal[i] = statTot;
                statTot = 0;
            }
        }

        tempDriver = Formula1ChampionshipManager.getFormula1Drivers();
        System.out.println("line 178 " + tempDriver.length);

        for (int j = 0; j < driversPointTotal.length; j++) {
            for (int i = 0; i < driversPointTotal.length - 1; i++) {
                if (driversPointTotal[i] < driversPointTotal[i + 1]) {
                    if (Formula1ChampionshipManager.getFormula1Drivers()[0] == null) {
                        continue;
                    }
                    int temp = driversPointTotal[i];
                    System.out.println(Formula1ChampionshipManager.getFormula1Drivers()[0].getDriverName());
                    Formula1Driver tempDriverObject = tempDriver[i];

                    driversPointTotal[i] = driversPointTotal[i + 1];
                    tempDriver[i] = tempDriver[i + 1];

                    driversPointTotal[i + 1] = temp;
                    tempDriver[i + 1] = tempDriverObject;
                }
            }
        }
        //Draw a table and put data into table...
        String leftAlignFormat = "| %-15s | %-15s | %-15s | %-15s| %-15s|%n";

        System.out.format("+-----------------+-----------------+-----------------+----------------+----------------+%n");
        System.out.format("| Driver ID       | Driver Name     | Location        | Team           | statistic      |%n");
        System.out.format("+-----------------+-----------------+-----------------+----------------+----------------+%n");

        //to print all sorted Drivers
        for (int i = 0; i < tempDriver.length; i++) {
            if (tempDriver[i] == null) {
                continue;
            }
                System.out.format(leftAlignFormat, tempDriver[i].getDriverId(), tempDriver[i].getDriverName(), tempDriver[i].getLocation(),
                        tempDriver[i].getTeam(), driversPointTotal[i]);

            System.out.format("+-----------------+-----------------+-----------------+----------------+----------------+%n");
        }
        System.out.print("Dou you want to go HomeScreen or exit (h/e): ");
        char answer = scanner.next().charAt(0);

        if (answer == 'E' || answer == 'e') {
            return;
        } else if (answer == 'H' || answer == 'h') {
            homeScreen();
        } else
            System.out.println("INVALID INPUT...!");


    }

    //To print driver stat.....
    public static void displayDriverStatistic() {
        System.out.print("Input driver id to display statistic: ");
        String driverId = scanner.next();   //D001

        for (int i = 0; i < Formula1ChampionshipManager.getDriverStatistic().length; i++) {
            if (driverId.equals(Formula1ChampionshipManager.getDriverStatistic()[i][0])) {
                for (int j = 0; j < Formula1ChampionshipManager.getDriverStatistic()[i].length; j++) {
                    System.out.println((j + 1) + " Places Points: " + Formula1ChampionshipManager.getDriverStatistic()[i][j]);
                }
            }
        }
        System.out.print("Do you want print another driver various stat..?(Y/N): ");
        //check 1st character in the line
        char answer = scanner.next().charAt(0);
        if (answer == 'Y' || answer == 'y') {
            displayDriverStatistic();
        } else if (answer == 'N' || answer == 'n') {
            homeScreen();
        } else
            System.out.println("INVALID INPUT...!");
    }

    //to update diver team name
    public static void updateConstructor() {
        System.out.print("Input driver id: ");
        String dId = scanner.next();
        if (Formula1ChampionshipManager.getFormula1Drivers() != null) {
            for (int i = 0; i < Formula1ChampionshipManager.getFormula1Drivers().length; i++) {
                if (dId.equals(Formula1ChampionshipManager.getFormula1Drivers()[i].getDriverId())) {
                    System.out.print("Input new constructor team name: ");
                    String dNewTeam = scanner.next();
                    Formula1ChampionshipManager.getFormula1Drivers()[i].setTeam(dNewTeam);
                    System.out.println("Updated successfully..!");
                    System.out.print("Do you want to update more drivers..?(Y/N): ");
                    //user input y--> go to again updateConstructor and user can update another driver team again.
                    //User input n--> it will display the (menu) homeScreen and user and continue the program.
                    char yOrN = scanner.next().charAt(0);
                    if (yOrN == 'Y' || yOrN == 'y') {
                        deleteDriver();
                    } else if (yOrN == 'N' || yOrN == 'n') {
                        homeScreen();
                    } else
                        System.out.println("INVALID INPUT...!");
                }
            }

        }
    }

    //create method to count points
   /* public static int countPoints() {
        //count statistic for driver


        for (int i = 1; i <= 10; i++) {
            //total=total+count;
            System.out.print("Position" + i + ": ");
            int statistic = scanner.nextInt();
            if (i == 1) {
                count = statistic * 25;
                total = count;
                count = 0;
                System.out.println("c1: " + count);
                System.out.println("t1: " + total);

            } else if (i == 2) {
                count = statistic * 18;
                total = count + total;
                count = 0;
                System.out.println("c2: " + count);
                System.out.println("t2: " + total);


            } else if (i == 3) {
                count = statistic * 15;
                total = count + total;
                count = 0;
                System.out.println("c3: " + count);
                System.out.println("t3: " + total);
            } else if (i == 4) {
                count = statistic * 12;
                total = count + total;
                count = 0;
                System.out.println("c4: " + count);
                System.out.println("t4: " + total);
            } else if (i == 5) {
                count = statistic * 10;
                total = count + total;
                count = 0;
                System.out.println("c5: " + count);
                System.out.println("t5: " + total);
            } else if (i == 6) {
                count = statistic * 8;
                total = count + total;
                count = 0;
                System.out.println("c6: " + count);
                System.out.println("t6: " + total);
            } else if (i == 7) {
                count = statistic * 6;
                total = count + total;
                count = 0;
                System.out.println("c7: " + count);
                System.out.println("t7: " + total);
            } else if (i == 8) {
                count = statistic * 4;
                total = count + total;
                count = 0;
                System.out.println("c8: " + count);
                System.out.println("t8: " + total);
            } else if (i == 9) {
                count = statistic * 2;
                total = count + total;
                count = 0;
                System.out.println("c9: " + count);
                System.out.println("t9: " + total);
            } else if (i == 10) {
                count = statistic * 1;
                total = count + total;
                count = 0;
                System.out.println("c10: " + count);
                System.out.println("t10: " + total);
            }

        }
        System.out.println("kk:" + total);

        int statistic = total;
        return statistic;


    }*/

    //To delete driver
    public static void deleteDriver() {
        System.out.print("Input driver id: ");
        String id = scanner.next();

        Formula1ChampionshipManager formula1ChampionshipManager = new Formula1ChampionshipManager();
        formula1ChampionshipManager.deleteDriver(id);
        System.out.print("Do you want to delete more drivers..?(Y/N): ");
        //user input y--> go to again deletedriver and user can delete another driver again.
        //User input n--> it will display the (menu) homeScreen and user and continue the program.
        char yOrN = scanner.next().charAt(0);
        if (yOrN == 'Y' || yOrN == 'y') {
            deleteDriver();
        } else if (yOrN == 'N' || yOrN == 'n') {
            homeScreen();
        } else
            System.out.println("INVALID INPUT...!");
    }

    public static void addDriver() {
        //I took driver id as a driver input details because it avoids repeating same driver details again.
        System.out.print("Input driver id: ");
        String id = scanner.next();
        if (Formula1ChampionshipManager.getFormula1Drivers() != null) {
            for (int i = 0; i < Formula1ChampionshipManager.getFormula1Drivers().length; i++) {
                if (Formula1ChampionshipManager.getFormula1Drivers()[i] == null) {
                    continue;
                }
                if (id.equals(Formula1ChampionshipManager.getFormula1Drivers()[i].getDriverId())) {
                    System.out.println("Invalid id");
                    addDriver();
                }
            }
        }

        //Input driver's details like-->(name, location, team name, and position marks)...

        System.out.print("Input driver name: ");
        String name = scanner.next();
        System.out.print("Input driver location: ");
        String location = scanner.next();
        System.out.print("Input team name: ");
        String team = scanner.next();
        System.out.println("----Input statistic( Archived positions)----");
        // int statistic = countPoints();

        //Input driver's positions...

        System.out.print("First position count: ");
        int fCount = scanner.nextInt(); //5

        System.out.print("Second position count: ");
        int sCount = scanner.nextInt(); //2

        System.out.print("Third position count: ");
        int tCount = scanner.nextInt();

        System.out.print("Fourth  position count: ");
        int fourthCount = scanner.nextInt();

        System.out.print("Fifth position count: ");
        int fifthCount = scanner.nextInt();

        System.out.print("Sixth position count: ");
        int sixthCount = scanner.nextInt();

        System.out.print("Seventh position count: ");
        int seventhCount = scanner.nextInt();

        System.out.print("Eighth  position count: ");
        int eighthCount = scanner.nextInt();

        System.out.print("Ninth position count: ");
        int ninthCount = scanner.nextInt();

        System.out.print("Tenth position count: ");
        int tenthCount = scanner.nextInt();

        //To calculate driver's position marks 1-10

        int firstPointTot = fCount * Formula1ChampionshipManager.getPositionPoints()[0];
        int secondPointTot = sCount * Formula1ChampionshipManager.getPositionPoints()[1];
        int thirdPointTot = tCount * Formula1ChampionshipManager.getPositionPoints()[2];
        int fourthPointTot = fourthCount * Formula1ChampionshipManager.getPositionPoints()[3];
        int fifthPointTot = fifthCount * Formula1ChampionshipManager.getPositionPoints()[4];
        int sixthPointTot = sixthCount * Formula1ChampionshipManager.getPositionPoints()[5];
        int seventhPointTot = seventhCount * Formula1ChampionshipManager.getPositionPoints()[6];
        int eighthPointTot = eighthCount * Formula1ChampionshipManager.getPositionPoints()[7];
        int ninthPointTot = ninthCount * Formula1ChampionshipManager.getPositionPoints()[8];
        int tenthPointTot = tenthCount * Formula1ChampionshipManager.getPositionPoints()[9];

        //create object from Formula1Driver class to pass input values of the users'

        Formula1Driver formula1Driver = new Formula1Driver();
        //Store Drivers'(users') inputs
        formula1Driver.setDriverId(id);
        formula1Driver.setDriverName(name);
        formula1Driver.setLocation(location);
        formula1Driver.setTeam(team);

        //formula1Driver.setStatistics(statistic);
        Formula1ChampionshipManager formula1ChampionshipManager = new Formula1ChampionshipManager();
        formula1ChampionshipManager.addDriver(formula1Driver);

        //driverCount

        temp[driverCount][0] = id;
        temp[driverCount][1] = String.valueOf(firstPointTot);
        temp[driverCount][2] = String.valueOf(secondPointTot);
        temp[driverCount][3] = String.valueOf(thirdPointTot);
        temp[driverCount][4] = String.valueOf(fourthPointTot);
        temp[driverCount][5] = String.valueOf(fifthPointTot);
        temp[driverCount][6] = String.valueOf(sixthPointTot);
        temp[driverCount][7] = String.valueOf(seventhPointTot);
        temp[driverCount][8] = String.valueOf(eighthPointTot);
        temp[driverCount][9] = String.valueOf(ninthPointTot);
        temp[driverCount][10] = String.valueOf(tenthPointTot);

        Formula1ChampionshipManager.setDriverStatistic(temp);
        driverCount++;

        System.out.print("Do you want to add more drivers..?(Y/N): ");
        char yOrN = scanner.next().charAt(0);
        if (yOrN == 'Y' || yOrN == 'y') {
            addDriver();
        } else if (yOrN == 'N' || yOrN == 'n') {
            homeScreen();
        } else
            System.out.println("INVALID INPUT...!");
    }

    public static void homeScreen() {

        System.out.println("\t\t----------Manage Formula Driver----------\n");
        System.out.println("1. Add Driver\t\t\t\t2. Delete Driver\n3. Change Driver\t\t\t4. Display Driver Statistics" +
                "\n5. Display All Drivers\t\t6. Add Completed Races\n7. Save All Details\t\t\t8. Display Table GUI");
        System.out.print("Select an option: ");
        int option = scanner.nextInt();


        switch (option) {
            case 1:
                addDriver();
                break;
            case 2:
                deleteDriver();
                break;
            case 3:
                updateConstructor();
                break;
            case 4:
                displayDriverStatistic();
                break;
            case 5:
                displayDriverDetailsInTable();
                break;
           /* case 6:
                tableUpdate();
                break;*/
            case 7:
                saveToFile();
                break;
            case 8:
                printDriverDetailOnTable();
                break;
            default:
                System.out.println("INVALID OPTION...!");
        }

    }

    public static void main(String[] args) {
        //for get the objects from the file and set them to array
        if (new File("driverDetails.txt").exists() && new File("driverStatistic.txt").exists() && new File("driverPointTotal.txt").exists()) {
            getDetailsFromObject();
        }

        //home screen is a static method therefore its no need to create objects
        homeScreen();
        //

    }
}
