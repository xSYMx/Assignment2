package models;

public abstract class Technology {
    private double price = 0;
    private String id = "unknown";
    private String modelName = "";
    private Manufacturer manufacturer;

    public Technology(String modelName, double price, Manufacturer manufacturer, String id) {
        setModelName(modelName);
        setPrice(price);
        setManufacturer(manufacturer);
        setId(id);
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public abstract double getInsurancePremium();

    public abstract String connectToInternet();

    @Override
    public String toString() {
        return "Model:" + modelName + "," +
                "Price:" + price + "," +
                manufacturer + "," +
                "ID:" + id ;
    }
}
