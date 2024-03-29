import java.util.ArrayList;

import logic.CheckFormat;
import logic.Discounts;

import logic.Print;
import logic.ReadFile;
import model.Output;
import model.ShipmentPrice;

public class Vinted {
    public static void main(String[] args) throws Exception {

        ArrayList<Output> outputs = ReadFile.readInput("input.txt");
        ArrayList<ShipmentPrice> shipmentPrices = ReadFile.readPrices();
        outputs = CheckFormat.checkFormat(outputs, shipmentPrices);
        outputs = Discounts.giveDiscounts(outputs, shipmentPrices);
        Print.printOutputs(outputs);
    }
}
