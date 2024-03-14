package logic;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import model.Output;
import model.ShipmentPrice;

public class Discounts {
    static int largeCounter = 0;
    static int currentMonth = 0;
    static Boolean discountUsed = false;

    public static ArrayList<Output> giveDiscounts(ArrayList<Output> outputs, ArrayList<ShipmentPrice> shipmentPrices) {
        ShipmentPrice shipmentPrice = getSmallCheapest(shipmentPrices);

        Double monthsDiscount = 0.0;
        for (int i = 0; i < outputs.size(); i++) {
            Output output = outputs.get(i);

            if (output.getDiscount().equals("")) {
                int month = getMonth(output.getDate());
                if (currentMonth != month) { // maybe new function here
                    largeCounter = 1;
                    currentMonth = month;
                    discountUsed = false;
                    monthsDiscount = 0.0;
                }
                if (output.getPackageSize().equals("S")) {
                    output = smallPrice(output, shipmentPrice, shipmentPrices);
                } else if (output.getPackageSize().equals("L") && output.getCarrier().equals("LP")) {
                    output = largeDiscount(output, shipmentPrices);
                } else {
                    output.setDiscount("-");
                }

                try { // move this to new function and maybe break it apart
                    double discount = Double.parseDouble(output.getDiscount());
                    monthsDiscount += discount;
                    if (monthsDiscount > 10) {
                        double newDiscount = Double.parseDouble(output.getDiscount()) - (monthsDiscount - 10);
                        if (newDiscount < 0.0) {
                            output.setDiscount("-");
                        } else {
                            output.setDiscount(Double.toString(round(newDiscount, 2)));
                        }

                    }
                } catch (NumberFormatException e) {
                }
            }

            output = addPrice(output, shipmentPrices);
        }
        return outputs;
    }

    private static int getMonth(String date) {
        LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ISO_DATE);
        return localDate.getMonthValue();
    }

    private static Output smallPrice(Output output, ShipmentPrice shipmentPrice,
            ArrayList<ShipmentPrice> shipmentPrices) {
        if (output.getCarrier().equals(shipmentPrice.getProvider())) {
            output.setDiscount("-");
            return output;
        } else {
            Double providersPrise = getprovidersPriceBySize(output.getCarrier(), output.getPackageSize(),
                    shipmentPrices);
            output.setDiscount(Double.toString(providersPrise - shipmentPrice.getPrice()));
            return output;
        }
    }

    private static Double getprovidersPriceBySize(String provider, String packageSize,
            ArrayList<ShipmentPrice> shipmentPrices) {
        for (int i = 0; i < shipmentPrices.size(); i++) {
            if (shipmentPrices.get(i).getProvider().equals(provider)
                    && shipmentPrices.get(i).getPackageSize().equals(packageSize)) {
                return shipmentPrices.get(i).getPrice();
            }

        }
        return 0.0;
    }

    private static ShipmentPrice getSmallCheapest(ArrayList<ShipmentPrice> shipmentPrices) {
        ShipmentPrice shipmentPrice = new ShipmentPrice(null, null, 0.0);
        for (int i = 0; i < shipmentPrices.size(); i++) {
            if (shipmentPrices.get(i).getPackageSize().equals("S")) {
                if (shipmentPrice.getPrice() == 0.0
                        || shipmentPrice.getPrice() > shipmentPrices.get(i).getPrice()) {
                    shipmentPrice = shipmentPrices.get(i);
                }
            }
        }
        return shipmentPrice;
    }

    private static Output largeDiscount(Output output, ArrayList<ShipmentPrice> shipmentPrices) {
        int month = getMonth(output.getDate());
        if (currentMonth == month && discountUsed == false) {
            largeCounter++;
        }

        if (largeCounter >= 3 && discountUsed == false) {
            discountUsed = true;
            Double providersPrise = getprovidersPriceBySize(output.getCarrier(), output.getPackageSize(),
                    shipmentPrices);
            output.setDiscount(Double.toString(providersPrise));
        }

        if (output.getDiscount().equals("")) {
            output.setDiscount("-");
        }
        return output;
    }

    public static Output addPrice(Output output, ArrayList<ShipmentPrice> shipmentPrices) {
        Double providersPrise = getprovidersPriceBySize(output.getCarrier(), output.getPackageSize(),
                shipmentPrices);

        try {
            double discount = Double.parseDouble(output.getDiscount());
            output.setPrice(providersPrise - discount);
        } catch (NumberFormatException e) {
            output.setPrice(providersPrise);
        }
        return output;

    }

    public static Double round(Double value, int places) {
        if (places < 0)
            throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
