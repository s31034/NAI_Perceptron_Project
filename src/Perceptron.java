import java.util.List;

public class Perceptron {
    double []  weights;
    double alpha;
    double activateLevel;
    int counter = 1;

    Perceptron(int size, double alpha) {
        this.weights = new double[size];
        this.alpha = alpha;
        this.activateLevel = 0.0;
        for (int i = 0; i < weights.length; i++) {
            weights[i] = (Math.random() * 2) - 1;
        }
    }
    public int compute(double[] data) {
        double sum = 0.0;
        for (int i = 0; i < weights.length; i++) {
            sum += weights[i] * data[i];        }
        if (sum >= activateLevel) {
            return 1;
        }else
            return 0;
    }

    public void update(double [] data, int decision) {
        int y = compute(data);
        if( decision != y){
            activateLevel += (decision - y) * alpha;
            for (int i = 0; i < weights.length; i++) {
                weights[i] += (decision - y) * alpha * data[i];
            }
        }
    }


    public void train(List<double[]> data, List<Integer> decisions, int iterations) {
        for (int iter = 0; iter < iterations; iter++) {
            for (int i = 0; i < data.size(); i++) {
                update(data.get(i), decisions.get(i));
            }
            stats(data, decisions);
        }
    }

    public void stats(List<double[]> data, List<Integer> decisions) {
        int correctSetosa = 0;
        int falsePositives = 0;
        int missedSetosa = 0;
        for (int i = 0; i < data.size(); i++) {
            int prediction = compute(data.get(i));
            if (prediction == 1 && decisions.get(i) == 1) correctSetosa++;
            else if (prediction == 1 && decisions.get(i) == 0) falsePositives++;
            else if (prediction == 0 && decisions.get(i) == 1) missedSetosa++;
        }
        System.out.println("Number of try: " + counter++);
        System.out.println("Correctly recognized Setosa: " + correctSetosa);
        System.out.println("False positives: " + falsePositives);
        System.out.println("Missed Setosa: " + missedSetosa);
        System.out.println();
        System.out.println("=================================================");
    }
}