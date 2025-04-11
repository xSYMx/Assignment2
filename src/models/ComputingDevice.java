package models;

public abstract class ComputingDevice extends Technology{
    private int storage;
    private String processor;
    public ComputingDevice(String modelName, double price, Manufacturer manufacturer, String id, String processor, int storage) {
        super(modelName, price, manufacturer, id);
        this.processor = processor;
        this.storage = storage;
    }

    public int getStorage() {
        return storage;
    }

    public void setStorage(int storage) {
        this.storage = storage;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public abstract double getInsurancePremium();
    public abstract String connectToInternet();

}
