package models;

public class SmartWatch extends WearableDevice {
    private String displayType;

    public SmartWatch(String modelName, double price, Manufacturer manufacturer, String id, String material, String size, String displayType) {
        super(modelName, price, manufacturer, id, material, size);
        this.displayType = displayType;
    }

    public String getDisplayType() {
        return displayType;
    }

    public void setDisplayType(String displayType) {
        this.displayType = displayType;
    }

    @Override
    public double getInsurancePremium() {
        return getPrice() * 0.06;
    }

    @Override
    public String connectToInternet() {
        return "Connects to the internet via Bluetooth";
    }

    @Override
    public String toString() {
        return super.toString() + ", Display Type: " + displayType;
    }
}
