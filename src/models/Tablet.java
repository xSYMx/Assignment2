package models;

public class Tablet extends ComputingDevice {
    private String operatingSystem;

    public Tablet(String modelName, double price, Manufacturer manufacturer, String id, String processor, int storage, String operatingSystem) {
        super(modelName, price, manufacturer, id, processor, storage);
        this.operatingSystem = operatingSystem;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    @Override
    public double getInsurancePremium() {
        return getPrice() * 0.01;
    }

    @Override
    public String connectToInternet() {
        return "Connects to the internet via Wi-Fi";
    }

    @Override
    public String toString() {
        return super.toString() + ", OS: " + operatingSystem;
    }
}

