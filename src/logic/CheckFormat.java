package logic;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import model.Output;
import model.ShipmentPrice;

public class CheckFormat {
    public static ArrayList<Output> checkFormat(ArrayList<Output> outputs, ArrayList<ShipmentPrice> shipmentPrices) {
        for (int i = 0; i < outputs.size(); i++) {
            Output output = outputs.get(i);
            if (checkDate(output.getDate()) == false || checkCarrierandSize(output, shipmentPrices) == false) {
                output.setDiscount("ignored");
                outputs.set(i, output);
            }
        }
        return outputs;
    }

    public static boolean checkDate(String outputDate) {
        try {
            LocalDate.parse(outputDate);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static boolean checkCarrierandSize(Output output, ArrayList<ShipmentPrice> shipmentPrices) {
        for (int i = 0; i < shipmentPrices.size(); i++) {
            ShipmentPrice shipmentPrice = shipmentPrices.get(i);
            if (shipmentPrice.getProvider().equals(output.getCarrier())) {
                if (shipmentPrice.getPackageSize().equals(output.getPackageSize())) {
                    return true;
                }
            }
        }
        return false;
    }
}
