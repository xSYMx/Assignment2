package controllers;

import models.*;

import utils.ISerializer;
import utils.OperatingSystemUtility;

import utils.Utilities;

import java.io.*;
import java.util.*;

//TODO - ensure that this class implements iSerializer
public class TechnologyDeviceAPI implements ISerializer {

    //TODO - create 2 fields

    private ArrayList<Technology> technologyList;
    private File file;

    //TODO - create constructor

    public TechnologyDeviceAPI(File file){
        this.technologyList = new ArrayList<>();
        this.file = file;
    }

    //TODO - CRUD Methods

    public boolean addTechnologyDevice(Technology technology){
        return technologyList.add(technology);
    }

    public boolean updateTablet(String id, models.Tablet updatedDetails) {
        for (int i = 0; i < technologyList.size(); i++) {
            if (technologyList.get(i).getId().equals(id) && technologyList.get(i) instanceof models.Tablet) {
                technologyList.set(i, updatedDetails);
                return true;
            }
        }
        return false;
    }

    public boolean updateSmartWatch(String id, models.SmartWatch updatedDetails) {
        for (int i = 0; i < technologyList.size(); i++) {
            if (technologyList.get(i).getId().equals(id) && technologyList.get(i) instanceof models.SmartWatch) {
                technologyList.set(i, updatedDetails);
                return true;
            }
        }
        return false;
    }

    public boolean updateSmartBand(String id, models.SmartBand updatedDetails) {
        for (int i = 0; i < technologyList.size(); i++) {
            if (technologyList.get(i).getId().equals(id) && technologyList.get(i) instanceof models.SmartBand) {
                technologyList.set(i, updatedDetails);
                return true;
            }
        }
        return false;
    }



    //TODO - Number methods

    public int numberOfTechnologyDevices() {
        return technologyList.size();
    }

    public int numberOfTablets() {
        int count = 0;
        for (Technology tech : technologyList) {
            if (tech instanceof models.Tablet) {
                count++;
            }
        }
        return count;
    }

    public int numberOfSmartBands() {
        int count = 0;
        for (Technology tech : technologyList) {
            if (tech instanceof models.SmartBand) {
                count++;
            }
        }
        return count;
    }

    public int numberOfSmartWatches() {
        int count = 0;
        for (Technology tech : technologyList) {
            if (tech instanceof models.SmartWatch) {
                count++;
            }
        }
        return count;
    }

    public int numberOfTechnologyByChosenManufacturer(String manufacturerName) {
        int count = 0;
        for (Technology tech : technologyList) {
            if (tech.getManufacturer().getManufacturerName().equals(manufacturerName)) {
                count++;
            }
        }
        return count;
    }



    // TODO Read/list methods

    public String listAllTechnologyDevices() {
        if (technologyList.isEmpty()) {
            return "No Technology Devices";
        }
        String listed = "";
        for (int i = 0; i < technologyList.size(); i++) {
            listed += i + ": " + technologyList.get(i).toString() + "\n";
        }
        return listed;
    }

    public String listAllTechnologyAbovePrice(double price) {
        boolean iffound = false;
        String listed = "";
        for (Technology tech : technologyList) {
            if (tech.getPrice() >= price) {
                listed += technologyList.indexOf(tech) + tech.toString() + "\n";
                iffound = true;
            }
        }
        if (!iffound) {
            listed = "No technology more expensive than " + price;
        }
        return listed;
    }

    public String listAllTechnologyBelowPrice(double price) {
        boolean iffound = false;
        String listed = "";
        for (Technology tech : technologyList) {
            if (tech.getPrice() <= price) {
                listed += technologyList.indexOf(tech) + tech.toString() + "\n";
                iffound = true;
            }
        }
        if (!iffound) {
            listed = "No technology cheaper than " + price;
        }
        return listed;
    }

    public String listAllTechDevicesByChosenManufacturer(String manufacturerName) {
        String listed = "";
        boolean iffound = false;
        for (Technology tech : technologyList) {
            if (tech.getManufacturer().getManufacturerName().equals(manufacturerName)) {
                listed = technologyList.indexOf(tech) + tech.toString() + "\n";
                iffound = true;
            }
        }
        if (!iffound) {
            listed = "No technology manufactured by " + manufacturerName;
        }
        return listed;
    }


    //the following is isValidId can be updated to suit your code
        public boolean isValidId(String id) {
        for (Technology techDev : technologyList) {
            if (techDev.getId().equals(id))
                return false;
        }
            return true;
        }

    //TODO get Technology methods

    public Technology getTechnologyByIndex(int index) {
        if (index >= 0 && index < technologyList.size()) {
            return technologyList.get(index);
        }
        return null;
    }

    public Technology getTechnologyDeviceById(String id) {
        for (Technology tech : technologyList) {
            if (isValidId(id)){
                if (tech.getId().equals(id)) {
                    return tech;
                }
            }
        }
        return null;
    }

    //TODO - delete methods

    public Technology deleteTechnologyDeviceByIndex(int index){
        if (index >= 0 && index < technologyList.size()){
            return technologyList.remove(index);
        }
        else {
            return null;
        }
    }

    public Technology deleteTechnologyById(String id) {
        for (int i = 0; i < technologyList.size(); i++) {
            if (isValidId(id)){
                if (technologyList.get(i).getId().equals(id)) {
                    return technologyList.remove(i);
                }
            }
        }
        return null;
    }



    //TODO - sort methods

    //TODO Top 5 methods





    // TODO Persistence methods

    @Override
    public void save() throws Exception {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(technologyList);
        }
    }

    @Override
    public void load() throws Exception {
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                technologyList = (ArrayList<Technology>) ois.readObject();
            }
        }
    }

    @Override
    public String fileName() {
        return file.getName();
    }

}
