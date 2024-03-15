import java.util.ArrayList;

import logic.CheckFormat;
import logic.Discounts;

import logic.Print;
import logic.ReadFile;
import model.Output;
import model.ShipmentPrice;

public class App {
    public static void main(String[] args) throws Exception {

        ArrayList<Output> outputs = ReadFile.readInput(args[0]);
        ArrayList<ShipmentPrice> shipmentPrices = ReadFile.readPrices();
        outputs = CheckFormat.checkFormat(outputs, shipmentPrices);
        outputs = Discounts.giveDiscounts(outputs, shipmentPrices);
        Print.printOutputs(outputs);
    }
}
