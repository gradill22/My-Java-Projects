import java.util.List;

public class Main {
    static double[][] x = {
            {0, 0},
            {0, 1},
            {1, 0},
            {1, 1}
    };
    static double[][] y = {
            {0}, {1}, {1}, {0}
    };

    public static void main(String[] args) {
        NeuralNetwork nn = new NeuralNetwork(2, 10, 1);
        nn.fit(x, y, 50000);

        List<Double> output;

        double [][] input ={{0,0},{0,1},{1,0},{1,1}};
        for(double[] d:input)
        {
            output = nn.predict(d);
            System.out.println(output.toString());
        }
    }
}