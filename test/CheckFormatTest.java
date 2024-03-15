import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import logic.CheckFormat;
import model.Output;
import model.ShipmentPrice;

public class CheckFormatTest {

    @Test
    void correctLine() {
        CheckFormat checkFormat = new CheckFormat();
        ArrayList<ShipmentPrice> shipmentPrices = new ArrayList<>();
        shipmentPrices.add(new ShipmentPrice("LP", "S", 2.00));
        ArrayList<Output> outputs = new ArrayList<>();
        ArrayList<Output> outputsCorrect = new ArrayList<>();
        outputsCorrect.add(new Output("2015-02-01", "S", "LP", 0.0, ""));
        outputs.add(new Output("2015-02-01", "S", "LP", 0.0, ""));
        checkFormat.checkFormat(outputs, shipmentPrices);
        String test = checkFormat.checkFormat(outputs, shipmentPrices).get(0).getDiscount();
        assertEquals(outputsCorrect.get(0).getDiscount(),
                checkFormat.checkFormat(outputs, shipmentPrices).get(0).getDiscount());

    }

    @Test
    void incorectLine() {
        CheckFormat checkFormat = new CheckFormat();
        ArrayList<ShipmentPrice> shipmentPrices = new ArrayList<>();
        shipmentPrices.add(new ShipmentPrice("LP", "S", 2.00));
        ArrayList<Output> outputs = new ArrayList<>();
        ArrayList<Output> outputsCorrect = new ArrayList<>();
        outputs.add(new Output("2015-02-01", "S", "LPT", 0.0, ""));
        outputsCorrect.add(new Output("2015-02-02", "S", "LPT", 0.0, "ignored"));
        assertEquals(outputsCorrect.get(0).getDiscount(),
                checkFormat.checkFormat(outputs, shipmentPrices).get(0).getDiscount());

    }

    @Test
    void checkAprilTen() {
        CheckFormat checkFormat = new CheckFormat();
        assertTrue(checkFormat.checkDate("2023-04-10"));
    }

    @Test
    void checkFebruaryTwentyNine() {
        CheckFormat checkFormat = new CheckFormat();
        assertFalse(checkFormat.checkDate("2013-02-29"));
    }

    @Test
    void checkLargeAtLP() {
        CheckFormat checkFormat = new CheckFormat();
        ArrayList<ShipmentPrice> shipmentPrices = new ArrayList<>();
        shipmentPrices.add(new ShipmentPrice("LP", "L", 6.00));
        shipmentPrices.add(new ShipmentPrice("MR", "L", 8.00));
        shipmentPrices.add(new ShipmentPrice("MR", "S", 2.00));
        assertTrue(checkFormat.checkCarrierandSize(new Output("2015-02-01", "L", "LP", 0.0, ""), shipmentPrices));
    }

    @Test
    void checkSmallAtMRT() {
        CheckFormat checkFormat = new CheckFormat();
        ArrayList<ShipmentPrice> shipmentPrices = new ArrayList<>();
        shipmentPrices.add(new ShipmentPrice("LP", "L", 6.00));
        shipmentPrices.add(new ShipmentPrice("MR", "L", 8.00));
        shipmentPrices.add(new ShipmentPrice("MR", "S", 2.00));
        assertFalse(checkFormat.checkCarrierandSize(new Output("2015-02-01", "S", "MRT", 0.0, ""), shipmentPrices));
    }

    @Test
    void checkXLAtMR() {
        CheckFormat checkFormat = new CheckFormat();
        ArrayList<ShipmentPrice> shipmentPrices = new ArrayList<>();
        shipmentPrices.add(new ShipmentPrice("LP", "L", 6.00));
        shipmentPrices.add(new ShipmentPrice("MR", "L", 8.00));
        shipmentPrices.add(new ShipmentPrice("MR", "S", 2.00));
        assertFalse(checkFormat.checkCarrierandSize(new Output("2015-02-01", "XL", "MR", 0.0, ""), shipmentPrices));
    }

}
