package logic;

import java.util.ArrayList;

import model.Output;

public class Print {
    public static void printOutputs(ArrayList<Output> outputs) {
        for (int i = 0; i < outputs.size(); i++) {
            Output output = outputs.get(i);
            System.out.println(
                    String.format("%s %s %s %s ", output.getDate(), output.getPackageSize(),
                            output.getCarrier(), output.getDiscount()).replaceAll("\\s{2,}", " "));
        }
    }
}
