package model;

public class ShipmentPrice {
    private String provider;
    private String packageSize;
    private Double price;

    public ShipmentPrice(String provider, String packageSize, Double price) {
        this.provider = provider;
        this.packageSize = packageSize;
        this.price = price;
    }

    public String getPackageSize() {
        return packageSize;
    }

    public Double getPrice() {
        return price;
    }

    public String getProvider() {
        return provider;
    }

    public void setPackageSize(String packageSize) {
        this.packageSize = packageSize;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }
}
