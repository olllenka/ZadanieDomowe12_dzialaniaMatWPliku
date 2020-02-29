import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String inputFileName = "MathOperations.txt";
        String outputFileName = "MathOperationsWithResults.txt";

        try {
            ArrayList<String> operationsArray = readOperationsFromFile(inputFileName);
            ArrayList<String> operationsWithResultsArray = prepareOperationsWithResults(operationsArray);
            writeOperationsWithResultsToFile(operationsWithResultsArray, outputFileName);
        } catch (FileNotFoundException e) {
            System.out.println("Nie znaleziono pliku do odczytu " + inputFileName);;
        } catch (IOException e) {
            System.out.println("Nie udało się zapisać wyników do pliku " + outputFileName);;
        }
    }

    private static void writeOperationsWithResultsToFile(ArrayList<String> operationsWithResultsArray, String outputFileName) throws IOException {
        FileWriter fileWriter = new FileWriter(outputFileName);
        BufferedWriter writer = new BufferedWriter(fileWriter);

        for (int i=0; i<operationsWithResultsArray.size(); i++) {
            writer.write(operationsWithResultsArray.get(i));
            writer.newLine();
        }
        writer.close();
    }

    private static ArrayList<String> readOperationsFromFile(String inputFileName) throws FileNotFoundException {
        File inputFile = new File(inputFileName);
        Scanner sc = new Scanner(inputFile);

        ArrayList<String> operationsArray = new ArrayList<>();

        while (sc.hasNextLine()) {
            String operation = sc.nextLine();
            operationsArray.add(operation);
        }
        sc.close();
        return operationsArray;
    }

    private static ArrayList<String> prepareOperationsWithResults(ArrayList<String> operationsArray){
        ArrayList<String> operationsWithResultsArray = new ArrayList<>();

        for (int i = 0; i < operationsArray.size(); i++) {
            String operationWithResult = operationWithResultToString(operationsArray.get(i));
            operationsWithResultsArray.add(operationWithResult);
        }

        return operationsWithResultsArray;
    }

    private static String operationWithResultToString(String operation) {
        String[] operationArray = operation.split(" ");
        String operator = operationArray[1];
        Double number1D = Double.valueOf(operationArray[0]);
        Double number2D = Double.valueOf(operationArray[2]);
        Double result = countResult(operator, number1D, number2D);
        return number1D + " " + operator + " " + number2D + " = " + result;
    }

    private static Double countResult(String operator, Double number1D, Double number2D) {
        if (operator.equals("+")) {
            return number1D + number2D;
        } else if (operator.equals("-")) {
            return number1D - number2D;
        } else if (operator.equals("*")) {
            return number1D * number2D;
        } else if (operator.equals("/")) {
            return number1D / number2D;
        }
        else return null;
    }
}
