import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import logic.Discounts;
import model.ShipmentPrice;

public class DiscountsTest {
    @Test
    void getNumberOfApril() {
        Discounts discounts = new Discounts();
        assertEquals(4, discounts.getMonth("2013-04-25"));
    }

    @Test
    void getNumberOfSeptember() {
        Discounts discounts = new Discounts();
        assertEquals(9, discounts.getMonth("2013-09-01"));
    }

    @Test
    void getLOfLPPrice() {
        Discounts discounts = new Discounts();
        ArrayList<ShipmentPrice> shipmentPrices = new ArrayList<>();
        shipmentPrices.add(new ShipmentPrice("LP", "L", 6.2));
        shipmentPrices.add(new ShipmentPrice("MR", "L", 8.00));
        shipmentPrices.add(new ShipmentPrice("MR", "S", 2.00));

        assertEquals(6.2, discounts.getprovidersPriceBySize("LP", "L", shipmentPrices), 0);
    }

    @Test
    void getLOfLPTPrice() {
        Discounts discounts = new Discounts();
        ArrayList<ShipmentPrice> shipmentPrices = new ArrayList<>();
        shipmentPrices.add(new ShipmentPrice("LP", "L", 6.00));
        shipmentPrices.add(new ShipmentPrice("MR", "L", 8.00));
        shipmentPrices.add(new ShipmentPrice("MR", "S", 2.00));

        assertEquals(0.0, discounts.getprovidersPriceBySize("LPT", "L", shipmentPrices), 0);
    }

}
