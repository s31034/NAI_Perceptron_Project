import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        List<double[]> data = new ArrayList<>();
        List<Integer> decisions = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("src\\iris.data"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length < 5) continue;

                double[] features = new double[4];
                for (int i = 0; i < 4; i++) {
                    features[i] = Double.parseDouble(parts[i]);
                }

                int label;
                if (parts[4].equals("Iris-setosa")) {
                    label = 1;
                } else {
                    label = 0;
                }

                data.add(features);
                decisions.add(label);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Perceptron p = new Perceptron(4, 0.5);
        p.train(data, decisions, 50);
    }
}
