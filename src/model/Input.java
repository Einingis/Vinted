package model;

public class Input {
    private String date;
    private String packageSize;
    private String carrier;

    public Input(String date, String packageSize, String carrier) {
        this.date = date;
        this.packageSize = packageSize;
        this.carrier = carrier;
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

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setPackageSize(String packageSize) {
        this.packageSize = packageSize;
    }
}
