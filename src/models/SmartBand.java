package models;

public class SmartBand extends WearableDevice{
    private boolean heartRateMonitor;
    public SmartBand(String modelName, double price, Manufacturer manufacturer, String id, String material, String size, boolean heartRateMonitor) {
        super(modelName, price, manufacturer, id, material, size);
        this.heartRateMonitor = heartRateMonitor;
    }

    public boolean isHeartRateMonitor() {
        return heartRateMonitor;
    }

    public void setHeartRateMonitor(boolean heartRateMonitor) {
        this.heartRateMonitor = heartRateMonitor;
    }

    @Override
    public double getInsurancePremium() {
        return getPrice() * 0.07;
    }

    @Override
    public String connectToInternet() {
        return "Connects to the internet via Companion App";
    }

    @Override
    public String toString() {
        return super.toString() + ", Heart Rate Monitor: " + (heartRateMonitor ? "Yes" : "No");
    }

}
