package model;

public class Output {
    private String date;
    private String packageSize;
    private String carrier;
    private Double price;
    private String discount;

    public Output(String date, String packageSize, String carrier, Double price, String discount) {
        this.date = date;
        this.packageSize = packageSize;
        this.carrier = carrier;
        this.price = price;
        this.discount = discount;
    }

    public String getCarrier() {
        return carrier;
    }

    public String getDate() {
        return date;
    }

    public String getPackageSize() {
        return packageSize;
    }

    public String getDiscount() {
        return discount;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setPackageSize(String packageSize) {
        this.packageSize = packageSize;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
