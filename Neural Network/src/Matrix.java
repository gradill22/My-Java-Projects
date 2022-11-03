import java.util.ArrayList;
import java.util.List;

public class Matrix {
    double[][] data;
    int rows, cols;

    public Matrix(int rows, int cols) {
        data = new double[rows][cols];
        this.rows = rows;
        this.cols = cols;
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                data[i][j] = Math.random() * 2 - 1;
            }
        }
    }

    public void add(double scaler) {
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                this.data[i][j] += scaler;
            }
        }
    }

    public void add(Matrix m) {
        if(cols != m.cols || rows != m.rows) {
            System.out.println("Incorrect shape");
            return;
        }
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                this.data[i][j] += m.data[i][j];
            }
        }
    }

    public static Matrix subtract(Matrix a, Matrix b) {
        Matrix m = new Matrix(a.rows, a.cols);
        for(int i = 0; i < a.rows; i++) {
            for(int j = 0; j < a.cols; j++) {
                m.data[i][j] = a.data[i][j] + b.data[i][j];
            }
        }
        return m;
    }

    public static Matrix transpose(Matrix a) {
        Matrix m = new Matrix(a.cols, a.rows);
        for(int i = 0; i < a.rows; i++) {
            for(int j = 0; j < a.cols; j++) {
                m.data[j][i] = a.data[i][j];
            }
        }
        return m;
    }
    /*
    This multiply function takes in two Matrix objects and returns a new Matrix object with the product of the matrices.
     */
    public static Matrix multiply(Matrix a, Matrix b) {
        Matrix m = new Matrix(a.rows, b.cols);
        for(int i = 0; i < m.rows; i++) {
            for(int j = 0; j < m.cols; j++) {
                double sum = 0;
                for(int k = 0; k < a.cols; k++) {  // loop does dot product
                    sum += a.data[i][k] * b.data[k][j];
                }
                m.data[i][j] = sum;
            }
        }
        return m;
    }
    /*
    This multiply function takes in one Matrix object and modifies this Matrix object's data variable to the product
    of the two Matrix objects. It does not do dot products, but instead multiplies each point to its corresponding
    point.
     */
    public void multiply(Matrix a) {
        for(int i = 0; i < a.rows; i++) {
            for(int j = 0; j < a.cols; j++) {
                this.data[i][j] *= a.data[i][j];
            }
        }
    }
    /*
    This multiply function scales each number/point in the data variable by the scale parameter
     */
    public void multiply(double scale) {
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                this.data[i][j] *= scale;
            }
        }
    }

    public void sigmoid() {
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                this.data[i][j] = 1 / (1 + Math.pow(Math.E, -this.data[i][j]));
            }
        }
    }

    public Matrix dsigmoid() {
        Matrix m = new Matrix(rows, cols);
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                double d = this.data[i][j];
                m.data[i][j] = d * (1 - d);
            }
        }
        return m;
    }

    public static Matrix fromArray(double[] x) {
        int length = x.length;
        Matrix m = new Matrix(length, 1);
        for(int i = 0; i < length; i++) {
            m.data[i][0] = x[i];
        }
        return m;
    }

    public List<Double> toArray() {
        List<Double> temp = new ArrayList<>();
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                temp.add(data[i][j]);
            }
        }
        return temp;
    }
}