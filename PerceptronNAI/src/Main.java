import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException{
        //Reading .txt files' data to a collection of Iris record.
        ArrayList<Iris> trainData = loadData("perceptron.data");
        ArrayList<Iris> testData = loadData("perceptron.test.data");

        Perceptron p1 = new Perceptron(trainData.get(0).vector().length);

        //weight vectors before delta rule
        System.out.println(Arrays.toString(p1.weight));
        p1.deltaRule(trainData);
        //weight vectors after delta rule
        System.out.println(Arrays.toString(p1.weight));
        System.out.println("Accuracy: " + p1.evaluate(testData) + "%");



        //Very-simple UI to manually input and test vector with the same size of data vectors.
        Scanner scan = new Scanner(System.in);
        System.out.println(String.format("Enter %d dimension vector to be tested ", testData.get(0).vector().length));
        ArrayList<Double> userEntered = new ArrayList<>();
        for (int i = 0; i < testData.get(0).vector().length; i++) {
            double value = Double.parseDouble(scan.nextLine());
            userEntered.add(value);
        }
        int resultOfUserInput = p1.userInputtedVectors(userEntered);
        if (resultOfUserInput == 1){
            System.out.println("Iris-virginica");
        } else {
            System.out.println("Iris-versicolor");
        }
    }

    public static ArrayList<Iris> loadData(String path) throws FileNotFoundException{
        //While scanner object reading .txt files' lines one-by-one, it first put everything into a String array,
        //then parsing all elements of this String array into double array except for last element, which is species.
        //But to use species in Perceptron it must be represented by a number, so by using ternary operator we can represent it
        //As an integer. In the end Iris object is instantiated as much line as we have in .txt file, and create an ArrayList<Iris>
        File file = new File(path);
        Scanner scanner = new Scanner(file);
        ArrayList<Iris> data = new ArrayList<>();
        while (scanner.hasNextLine()){
            String[] parts = scanner.nextLine().split(",");
            double[] vectors = new double[parts.length-1];
            for (int i = 0; i < parts.length -1; i++){
                vectors[i] = Double.parseDouble(parts[i]);
            }
            int label = parts[parts.length - 1].equals("Iris-versicolor") ? 0 : 1;
            data.add(new Iris(vectors,label));
        }
        return data;
    }
}
