package logic;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import model.Output;
import model.ShipmentPrice;

public class ReadFile {
    public static ArrayList<Output> readInput(String filepath) {
        ArrayList<Output> outputs = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File(filepath));

            while (scanner.hasNextLine()) {
                String[] splited = scanner.nextLine().split(" ");
                Output output = new Output("", "", "", 0.0, "");
                for (int i = 0; i < splited.length; i++) {
                    switch (i) {
                        case 0:
                            output.setDate(splited[i]);
                            break;
                        case 1:
                            output.setPackageSize(splited[i]);
                            break;
                        case 2:
                            output.setCarrier(splited[i]);
                            break;
                        default:
                            output.setCarrier(String.format("%s %s", output.getCarrier(), splited[i]));
                            break;
                    }
                }
                outputs.add(output);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return outputs;
    }

    public static ArrayList<ShipmentPrice> readPrices() {
        ArrayList<ShipmentPrice> shipmentPrices = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File("shpiment.txt"));

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
