import java.util.Arrays;

public record Iris(double[] vector, int label) {


    @Override
    public String toString() {
        return "Iris{" +
                "vector=" + Arrays.toString(vector) +
                ", label=" + label +
                '}';
    }
}
