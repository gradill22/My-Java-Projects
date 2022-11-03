import java.util.List;

public class NeuralNetwork {
    Matrix weights_ih; // weights matrix for the input and hidden layer
    Matrix weights_ho; // weights matrix for the hidden and output layer
    Matrix bias_h; // bias matrix for the hidden layer
    Matrix bias_o; // bias matrix for the output layer
    double l_rate = 0.01; // learning rate: a hyperparameter used to control the learning steps during optimization of weights

    public NeuralNetwork(int inner, int hidden, int outer) {
        weights_ih = new Matrix(hidden, inner);
        weights_ho = new Matrix(outer, hidden);
        bias_h = new Matrix(hidden, 1);
        bias_o = new Matrix(outer, 1);
    }
    /*
    Forward Propagation is a computational step in Neural Networks where the input (Matrix) is multiplied with the
    weights (Matrix) of the hidden layer, then bias (column Matrix) is added to the dot product of the previous step.
    Finally, the result is then processed element-wise by the activation function. This is preformed on each layer with
    the result of preceding layer as the input for the next layer.
    This step is also called as Forward Pass and is used for generating prediction from the network.

    The function accepts a double array of inputs and then converts them to a column matrix by our helper function the
    forward pass is then calculated on both the layers and the output is then flattened into a list by another helper
    function.
     */
    public List<Double> predict(double[] x) {
        Matrix input = Matrix.fromArray(x);
        Matrix hidden = Matrix.multiply(weights_ih, input);
        hidden.add(bias_h);
        hidden.sigmoid();

        Matrix output = Matrix.multiply(weights_ho, hidden);
        output.add(bias_o);
        output.sigmoid();

        return output.toArray();
    }
    /*
    In the train() function, we get double arrays x and y, convert them to matrices, and subtract the output, which
    came from the forward pass, from the target, which is y in matrix form. The result of the subtraction is the error
    of the current sample passed. This error is used to calculate gradients for the backpropagation. The derivative of
    the sigmoid function is applied element-wise on the output matrix, which returns the gradient matrix then it is
    multiplied by the output errors and the learning rate which decides the learning step.

    Backpropagation is the reverse of the forward pass. We take the transpose of the weight matrices and multiply them
    with the gradient calculated from the errors, returning the deltas that are used to adjust the weights in the
    current layer. The biases are updated using the gradients.
     */
    public void train(double[] x, double[] y) {
        Matrix input = Matrix.fromArray(x);
        Matrix hidden = Matrix.multiply(weights_ih, input);
        hidden.add(bias_h);
        hidden.sigmoid();

        Matrix output = Matrix.multiply(weights_ho, hidden);
        output.add(bias_o);
        output.sigmoid();

        Matrix target = Matrix.fromArray(y);
        Matrix error = Matrix.subtract(target, output);
        Matrix gradient = output.dsigmoid();
        gradient.multiply(error);
        gradient.multiply(l_rate);

        Matrix hidden_T = Matrix.transpose(hidden);
        Matrix who_delta = Matrix.multiply(gradient, hidden_T); // who = weight matrix of hidden and output layer
        weights_ho.add(who_delta);
        bias_o.add(gradient);

        Matrix who_T = Matrix.transpose(weights_ho);
        Matrix hidden_errors = Matrix.multiply(who_T, error);
        Matrix h_gradient = hidden.dsigmoid();
        h_gradient.multiply(hidden_errors);
        h_gradient.multiply(l_rate);

        Matrix i_T = Matrix.transpose(input);
        Matrix wih_delta = Matrix.multiply(h_gradient, i_T);
        weights_ih.add(wih_delta);
        bias_h.add(h_gradient);
    }
    /*
    The fit function takes in 2 2D arrays, x and y, and the number of epochs (i.e. how many times do we have to
    iterate over the dataset?) represented as an integer. Here, we repeatedly call the train function with random data
    points from the dataset to generalize the network over the dataset.
     */
    public void fit(double[][] x, double[][] y, int epochs) {
        for(int i = 0; i < epochs; i++) {
            int sampleN = (int) (Math.random() * x.length);
            this.train(x[sampleN], y[sampleN]);
        }
    }
}