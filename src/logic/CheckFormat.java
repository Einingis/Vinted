package logic;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import model.Input;
import model.ShipmentPrice;

public class CheckFormat {
    public static void checkFormat(Input input, ArrayList<ShipmentPrice> shipmentPrices) {
        if (checkDate(input.getDate()) == false || checkCarrierandSize(input, shipmentPrices) == false) {
            System.out.println(
                    String.format("%s %s %s ignored", input.getDate(), input.getPackageSize(),
                            input.getCarrier()).replaceAll("\\s{2,}", " "));
        } else {
            System.out.println(
                    String.format("%s %s %s", input.getDate(), input.getPackageSize(),
                            input.getCarrier()).replaceAll("\\s{2,}", " "));
        }
    }

    public static boolean checkDate(String inputDate) {
        try {
            LocalDate.parse(inputDate);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static boolean checkCarrierandSize(Input input, ArrayList<ShipmentPrice> shipmentPrices) {

        for (int i = 0; i < shipmentPrices.size(); i++) {
            ShipmentPrice shipmentPrice = shipmentPrices.get(i);
            if (shipmentPrice.getProvider().equals(input.getCarrier())) {
                if (shipmentPrice.getPackageSize().equals(input.getPackageSize())) {
                    return true;
                }
            }
        }

        return false;
    }
}
