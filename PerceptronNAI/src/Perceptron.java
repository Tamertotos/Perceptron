import java.util.ArrayList;
import java.util.Random;

public class Perceptron {
    Random r = new Random();
    public double learningRate = 0.01;
    public double bias = r.nextDouble();
    public double[] weight;

    //weight array's size is correlated with Iris object's first attribute's size (which is double[] vector array)
    public Perceptron( int size) {
        weight = new double[size];
        for (int i = 0; i < weight.length; i++){
            this.weight[i] = r.nextDouble();
        }
    }


    public  int net(double[] input){
        double dotProduct = 0.0;
        for (int i = 0; i < input.length ; i++){
            dotProduct += input[i] * this.weight[i];
        }

        if (dotProduct - getBias() >= 0){
            return 1;
        } else {
            return 0;
        }
    }

    //Since we started with random bias, and random weights by using delta rule formulae we move our hyperplane accordingly.
    //So, it means, we train our perceptron
    public void deltaRule(ArrayList<Iris> trainSet){
        int epoch = r.nextInt(50,100);
        int count = 0;
        while (count < epoch){
            for (int i = 0; i < trainSet.size(); i++){
                Iris iris = trainSet.get(i);
                double[] input = iris.vector();
                double error =  iris.label() - net(iris.vector());
                for (int j = 0; j < weight.length ; j++){
                    weight[j] = weight[j] + learningRate * error * input[j];
                }

                bias = bias - learningRate * error;
            }
            count++;
        }
    }

    //Since we trained our perceptron (Arranged and modified our weight and bias) now we can test any data and calculate accuracy.
    public double evaluate(ArrayList<Iris> testSet){
        double countAccurate = 0.0;
        for (int i = 0 ; i < testSet.size() ; i++){
            if (net(testSet.get(i).vector()) == testSet.get(i).label()){
                 countAccurate++;
            }
        }
        return countAccurate / testSet.size() * 100;
    }

    public int userInputtedVectors(ArrayList<Double> arrList){
        double[] arr = new double[arrList.size()];
        for (int i = 0; i < arrList.size(); i++){
            arr[i] = arrList.get(i);
        }

        return(net(arr));

    }

    public double getBias() {
        return bias;
    }
}
