package logic;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import model.Input;
import model.ShipmentPrice;

public class ReadFile {
    public static void readInput(String filepath) {
        try {
            Scanner scanner = new Scanner(new File(filepath));

            while (scanner.hasNextLine()) {
                String[] splited = scanner.nextLine().split(" ");
                Input input = new Input("", "", "");
                for (int i = 0; i < splited.length; i++) {
                    switch (i) {
                        case 0:
                            input.setDate(splited[i]);
                            break;
                        case 1:
                            input.setPackageSize(splited[i]);
                            break;
                        case 2:
                            input.setCarrier(splited[i]);
                            break;
                        default:
                            break;
                    }
                }
                CheckFormat.checkFormat(input, readPrices());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<ShipmentPrice> readPrices() {
        ArrayList<ShipmentPrice> shipmentPrices = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File("src\\shpiment.txt"));

            while (scanner.hasNextLine()) {
                String[] splited = scanner.nextLine().split(" ");

                shipmentPrices.add(new ShipmentPrice(splited[0], splited[1], Double.parseDouble(splited[2])));
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return shipmentPrices;
    }
}
