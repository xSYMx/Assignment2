package main;

import controllers.ManufacturerAPI;
import controllers.TechnologyDeviceAPI;

import models.*;
import utils.ScannerInput;
import utils.Utilities;

import java.io.File;
import java.util.ArrayList;

public class Driver {

        private TechnologyDeviceAPI techAPI;
        private ManufacturerAPI manufacturerAPI;


        public static void main(String[] args) throws Exception {
            new Driver().start();
        }

        public void start() throws Exception {

            manufacturerAPI = new ManufacturerAPI(new File("manufacturers.xml"));
            //TODO - construct fields
            techAPI = new TechnologyDeviceAPI(new File("technologies.xml"));

            //TODO - load all data once the serializers are set up

            loadData();

            runMainMenu();
        }

    private void saveData() throws Exception {
        manufacturerAPI.save();
        techAPI.save();
        System.out.println("Data saved successfully.");
    }

    private void loadData() throws Exception {
        manufacturerAPI.load();
        techAPI.load();
        System.out.println("Data loaded successfully.");
    }

    private int mainMenu() {
        System.out.println("""
                         -------Technology Store-------------
                        |  1) Manufacturer CRUD MENU     |
                        |  2) Technology  CRUD MENU      |
                        |  3) Reports MENU               |
                        |--------------------------------|
                        |  4) Search Manufacturers       |
                        |  5) Search Technology Devices  |  
                        |  6) Sort Technology Devices    | 
                        |--------------------------------|
                        |  10) Save all                  |
                        |  11) Load all                  |
                        |--------------------------------|
                        |  0) Exit                       |
                         --------------------------------""");
        return ScannerInput.readNextInt("==>> ");
    }
    //// search todo by different criteria i.e. look at the list methods and give options based on that.
// sort todo (and give a list of options - not a recurring menu thou)
        private void runMainMenu() {
            int option = mainMenu();
            while (option != 0) {
                switch (option) {
                    case 1->  runManufacturerMenu();
                    //TODO - Add options
                    case 2 -> runTechnologyMenu();
                    case 3 -> runReportsMenu();
                    case 4 -> searchManufacturers();
                    case 5 -> searchTechnologyDevices();
                    case 6 -> sortTechnologyDevices();
                    case 10 -> {
                        try {
                            saveData();
                        } catch (Exception e) {
                            System.out.println("Error saving data: " + e.getMessage());
                        }
                    }
                    case 11 -> {
                        try {
                            loadData();
                        } catch (Exception e) {
                            System.out.println("Error loading data: " + e.getMessage());
                        }
                    }
                    default -> System.out.println("Invalid option entered: " + option);
                }
                ScannerInput.readNextLine("\n Press the enter key to continue");
                option = mainMenu();
            }
            exitApp();
        }

        private void exitApp(){
            //TODO - save all the data entered

            System.out.println("Exiting....");
            System.exit(0);
        }

        //----------------------
        //  Manufacturer Menu Items
        //----------------------
        private int manufacturerMenu() {
            System.out.println("""
                --------Manufacturer Menu---------
               |  1) Add a manufacturer           |
               |  2) Delete a manufacturer        |
               |  3) Update manufacturer details  |
               |  4) List all manufacturers       |
               |  5) Find a manufacturer          |
               |  0) Return to main menu          |
                ----------------------------------""");
            return ScannerInput.readNextInt("==>>");
        }

        private void runManufacturerMenu() {
            int option = manufacturerMenu();
            while (option != 0) {
                switch (option) {
                    case 1 -> addManufacturer();
                    case 2 -> deleteManufacturer();
                    case 3 -> updateManufacturer();
                    case 4 -> System.out.println(manufacturerAPI.listManufacturers());
                    case 5-> findManufacturer();
                    case 6-> listByManufacturerName();
                    default->  System.out.println("Invalid option entered" + option);
                }
                ScannerInput.readNextLine("\n Press the enter key to continue");
                option = manufacturerMenu();
            }
        }

        private void addManufacturer() {
            String manufacturerName = ScannerInput.readNextLine("Please enter the manufacturer name: ");
            int manufacturerNumEmployees = ScannerInput.readNextInt("Please enter the number of employees: ");

            if (manufacturerAPI.addManufacturer(new Manufacturer(manufacturerName, manufacturerNumEmployees))){
                System.out.println("Add successful");
            }
            else{
                System.out.println("Add not successful");
            }
        }

        private void deleteManufacturer() {
            String manufacturerName = ScannerInput.readNextLine("Please enter the manufacturer name: ");
            if (manufacturerAPI.removeManufacturerByName(manufacturerName) != null){
                System.out.println("Delete successful");
            }
            else{
                System.out.println("Delete not successful");
            }
        }

        private void updateManufacturer(){
            Manufacturer manufacturer = getManufacturerByName();
            if (manufacturer != null){
                int numEmployees= ScannerInput.readNextInt("Please enter number of Employees: ");
                if (manufacturerAPI.updateManufacturer(manufacturer.getManufacturerName(), numEmployees))
                    System.out.println("Number of Employees Updated");
                else
                    System.out.println("Number of Employees NOT Updated");
            }
            else
                System.out.println("Manufacturer name is NOT valid");
        }

        private void findManufacturer(){
            Manufacturer developer = getManufacturerByName();
            if (developer == null){
                System.out.println("No such manufacturer exists");
            }
            else{
                System.out.println(developer);
            }
        }

        private void listByManufacturerName(){
            String manufacturer = ScannerInput.readNextLine("Enter the manufacturer's name:  ");

            System.out.println(manufacturerAPI.listAllByManufacturerName(manufacturer));
        }


        //---------------------
        //  Tech Store Menu
        //---------------------

        private int techAPIMenu() {
            System.out.println(""" 
                -----Technology Store Menu----- 
               | 1) Add a Tech Device           |
               | 2) Delete a Tech Device        |
               | 3) List all Tech Devices       |
               | 4) Update Tech Device          |
               | 0) Return to main menu         |
                ----------------------------""");
            return ScannerInput.readNextInt("==>>");
        }

    private void runTechnologyMenu() {
        int option = techAPIMenu();
        while (option != 0) {
            switch (option) {
                case 1 -> addTechnologyDevice();
                case 2 -> deleteTechnologyDevice();
                case 3 -> System.out.println(techAPI.listAllTechnologyDevices());
                case 4 -> updateTechnologyDevice();
                default -> System.out.println("Invalid option entered: " + option);
            }
            ScannerInput.readNextLine("\n Press the enter key to continue");
            option = techAPIMenu();
        }
    }

    private void addTechnologyDevice() {
        String modelName = ScannerInput.readNextLine("Enter Model Name: ");
        double price = ScannerInput.readNextDouble("Enter Price: ");
        String manufacturerName = ScannerInput.readNextLine("Enter Manufacturer Name: ");
        String id = ScannerInput.readNextLine("Enter ID: ");
        String type = ScannerInput.readNextLine("Enter device type (tablet/smartwatch/smartband): ").toLowerCase();

        Manufacturer manufacturer = manufacturerAPI.getManufacturerByName(manufacturerName);
        if (manufacturer == null) {
            System.out.println("Manufacturer does not exist.");
            return;
        }

        switch (type) {
            case "tablet" -> {
                String processor = ScannerInput.readNextLine("Enter Processor: ");
                int storage = ScannerInput.readNextInt("Enter Storage (GB): ");
                String operatingSystem = ScannerInput.readNextLine("Enter Operating System: ");
                techAPI.addTechnologyDevice(new Tablet(modelName, price, manufacturer, id, processor, storage, operatingSystem));
            }
            case "smartwatch" -> {
                String material = ScannerInput.readNextLine("Enter Material: ");
                String size = ScannerInput.readNextLine("Enter Size: ");
                String displayType = ScannerInput.readNextLine("Enter Display Type: ");
                techAPI.addTechnologyDevice(new SmartWatch(modelName, price, manufacturer, id, material, size, displayType));
            }
            case "smartband" -> {
                char ifHeartRateSensor = ScannerInput.readNextChar("Does it have heart rate monitor? (Y/N): ");
                String material = ScannerInput.readNextLine("Enter new Material: ");
                String size = ScannerInput.readNextLine("Enter new Size: ");
                boolean heartRateMonitor = Utilities.YNtoBoolean(ifHeartRateSensor);
                techAPI.addTechnologyDevice(new SmartBand(modelName, price, manufacturer, id, material, size, heartRateMonitor));
            }
            default -> System.out.println("Invalid device type.");
        }
    }

    private void deleteTechnologyDevice() {
        int index = ScannerInput.readNextInt("Enter Index to Delete: ");
        Technology device = techAPI.deleteTechnologyDeviceByIndex(index);
        if (device != null) {
            System.out.println("Device deleted: " + device.toString());
        } else {
            System.out.println("Invalid index.");
        }
    }

    private void updateTechnologyDevice() {
        String id = ScannerInput.readNextLine("Enter ID of the device to update: ");
        Technology device = techAPI.getTechnologyDeviceById(id);
        if (device == null) {
            System.out.println("Device not found.");
            return;
        }

        if (device instanceof Tablet) {
            String processor = ScannerInput.readNextLine("Enter new Processor: ");
            int storage = ScannerInput.readNextInt("Enter new Storage (GB): ");
            String operatingSystem = ScannerInput.readNextLine("Enter new Operating System: ");
            techAPI.updateTablet(id, new Tablet(device.getModelName(), device.getPrice(), device.getManufacturer(), id, processor, storage, operatingSystem));
        } else if (device instanceof SmartWatch) {
            String material = ScannerInput.readNextLine("Enter new Material: ");
            String size = ScannerInput.readNextLine("Enter new Size: ");
            String displayType = ScannerInput.readNextLine("Enter new Display Type: ");
            techAPI.updateSmartWatch(id, new SmartWatch(device.getModelName(), device.getPrice(), device.getManufacturer(), id, material, size, displayType));
        } else if (device instanceof SmartBand) {
            char ifHeartRateSensor = ScannerInput.readNextChar("Does it have heart rate monitor? (Y/N): ");
            String material = ScannerInput.readNextLine("Enter new Material: ");
            String size = ScannerInput.readNextLine("Enter new Size: ");
            boolean heartRateMonitor = Utilities.YNtoBoolean(ifHeartRateSensor);
            techAPI.updateSmartBand(id, new SmartBand(device.getModelName(), device.getPrice(), device.getManufacturer(), id, material, size, heartRateMonitor));
        } else {
            System.out.println("Unknown device type.");
        }
    }

    private int reportsMenu() {
        System.out.println(""" 
                --------Reports Menu ---------
               | 1) Manufacturers Overview    | 
               | 2) Technology Overview         |
               | 0) Return to main menu       | 
                 -----------------------------  """);
        return ScannerInput.readNextInt("==>>");
    }

    public void runReportsMenu(){
        int option = reportsMenu();
        while (option != 0) {
            switch (option) {
                case 1-> runManufacturerReports();
                case 2-> runTechnologyReportsMenu();
                default->  System.out.println("Invalid option entered" + option);
            }
            ScannerInput.readNextLine("\n Press the enter key to continue");
            option = reportsMenu();
        }
    }

    private int technologyReportsMenu() {
        System.out.println("""
                --------Technology Reports Menu---------
               |  1) List all technology             |
               |  2) List all SmartBands             |
               |  3) List all Smart Watches          |
               |  4) List all Tablets                |
               |  5) List all devices above a price  |
               |  6) List all devices below a price  |
               |  7) List all tablets by operating system |
               |  8) List the top five most expensive smart watches |
               |  0) Return to main menu             | 
                 ------------------------------------""");
        return ScannerInput.readNextInt("==>>");
    }

    public void runTechnologyReportsMenu() {
        int option = technologyReportsMenu();
        while (option != 0) {
            switch (option) {
                case 1 -> System.out.println(techAPI.listAllTechnologyDevices());
                case 2 -> System.out.println(techAPI.listAllSmartBands());
                case 3 -> System.out.println(techAPI.listAllSmartWatches());
                case 4 -> System.out.println(techAPI.listAllTablets());
                case 5 -> {
                    double price = ScannerInput.readNextDouble("Enter price: ");
                    System.out.println(techAPI.listAllTechnologyAbovePrice(price));
                }
                case 6 -> {
                    double price = ScannerInput.readNextDouble("Enter price: ");
                    System.out.println(techAPI.listAllTechnologyBelowPrice(price));
                }
                case 7 -> {
                    String os = ScannerInput.readNextLine("Enter operating system: ");
                    System.out.println(techAPI.listAllTabletsByOperatingSystem(os));
                }
                case 8 -> {
                    ArrayList<Technology> topSmartWatches = techAPI.topFiveMostExpensiveSmartWatch();
                    for (Technology tech : topSmartWatches) {
                        System.out.println(tech.toString());
                    }
                }
                default -> System.out.println("Invalid option entered: " + option);
            }
            ScannerInput.readNextLine("\n Press the enter key to continue");
            option = technologyReportsMenu();
        }
    }


    private int manufacturerReportsMenu() {
        System.out.println(""" 
                ---------- Manufacturers Reports Menu  -------------
               | 1) List Manufacturers                              | 
               | 2) List Manufacturers from a given manufacturer    |
               | 3) List Manufacturers by a given name              |
               | 0) Return to main menu                             | 
                 ---------------------------------------------------  """);
        return ScannerInput.readNextInt("==>>");
    }
    public void runManufacturerReports() {
        int option = manufacturerReportsMenu();
        while (option != 0) {
            switch (option) {
                case 1-> System.out.println(manufacturerAPI.listManufacturers());
                case 2-> System.out.println("todo - Case 2");
                case 3-> System.out.println("todo - Case 3");
                default->  System.out.println("Invalid option entered" + option);
            }
            ScannerInput.readNextLine("\n Press the enter key to continue");
            option =  manufacturerReportsMenu();
        }
    }

//todo update methods counting methods




        //---------------------
        //  General Menu Items
        //---------------------

//TODO - write all the methods that are called from your menu


    private void searchManufacturers() {
        String name = ScannerInput.readNextLine("Enter manufacturer name to search: ");
        System.out.println(manufacturerAPI.listAllByManufacturerName(name));
    }

    private void searchTechnologyDevices() {
        double price = ScannerInput.readNextDouble("Enter price to search: ");
        System.out.println(techAPI.listAllTechnologyAbovePrice(price));
    }

    private void sortTechnologyDevices() {
        int option = ScannerInput.readNextInt("Sort by price: 1) Ascending  2) Descending: ");
        if (option == 1) {
            techAPI.sortByPriceAscending();
        } else if (option == 2) {
            techAPI.sortByPriceDescending();
        } else {
            System.out.println("Invalid option.");
        }
        System.out.println(techAPI.listAllTechnologyDevices());
    }

        //---------------------
        //  Helper Methods
        //---------------------

//TODO- write any helper methods that are required


        private Manufacturer getManufacturerByName(){
            String manufacturerName = ScannerInput.readNextLine("Please enter the manufacturer's name: ");
            if (manufacturerAPI.isValidManufacturer(manufacturerName)){
                return manufacturerAPI.getManufacturerByName(manufacturerName);
            }
            else{
                return null;
            }
        }



    }

