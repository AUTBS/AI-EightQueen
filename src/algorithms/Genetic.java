package algorithms;

import java.rmi.MarshalException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class Genetic {

    int societySize = 20;
    ArrayList<Individual> society = new ArrayList<>();

    Genetic() {
        society = makeSociety();
        start();
    }

    private void start() {

        for (int c = 0; c < 1000000; c++) {
            calculateUtility();
            society = sort(society);
            combineSociety();
            calculateUtility();
            society = sort(society);
//            System.out.println(society.size());

        }
        System.out.println(society.get(0));

    }


    void calculateUtility() {
        for (Individual parent : society) {
            double utility = Math.sin(parent.value) - Math.pow(parent.value, 2.0) + parent.value;
            parent.utility = Math.abs(utility);
        }
    }

    void combineSociety() {
        for (int c = 0; c < societySize; c++) {
            if (c == societySize - 1)
                break;
            double child = (society.get(c).value + society.get(c + 1).value) / 2.0;
            child = adaptation(child);
            society.set(c + societySize, new Individual(child, null));
        }
        double child = (society.get(societySize - 1).value + society.get(0).value) / 2.0;
        child = adaptation(child);
        society.set(2 * societySize - 1, new Individual(child, null));
    }

    ArrayList<Individual> makeSociety() {

        ArrayList<Individual> society = new ArrayList<>();

        for (int c = 0; c < 40; c++) {
            double rand = (double) randInt(1, 315) / 100.0;
            society.add(new Individual(rand, null));
        }

        return society;
    }

    ArrayList<Individual> sort(ArrayList<Individual> society) {

        Collections.sort(society, new Comparator<Individual>() {
            @Override
            public int compare(Individual D2, Individual D1) {
                return D2.utility.compareTo(D1.utility);
            }
        });

        return society;
    }

    private double adaptation(double value) {
        value += 0 + nextGaussian() * 0.01;
        return value;
    }

    private static int randInt(int min, int max) {

        // NOTE: This will (intentionally) not run as written so that folks
        // copy-pasting have to think about how to initialize their
        // Random instance.  Initialization of the Random instance is outside
        // the main scope of the question, but some decent options are to have
        // a field that is initialized once and then re-used as needed or to
        // use ThreadLocalRandom (if using at least Java 1.7).
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive

        return rand.nextInt((max - min) + 1) + min;
    }

    public static void main(String args[]) {
        Genetic genetic = new Genetic();
    }

    class Individual {
        public Individual(Double value, Double utility) {
            this.value = value;
            this.utility = utility;
        }

        Double value;
        Double utility;

        @Override
        public String toString() {
            return "Individual{" +
                    "value=" + value +
                    ", utility=" + utility +
                    '}';
        }
    }


    public double nextGaussian() {

        Random rand = new Random();
        double v1, v2, s;
        do {
            v1 = 2 * rand.nextDouble() - 1;   // between -1.0 and 1.0
            v2 = 2 * rand.nextDouble() - 1;   // between -1.0 and 1.0
            s = v1 * v1 + v2 * v2;
        } while (s >= 1 || s == 0);
        double multiplier = StrictMath.sqrt(-2 * StrictMath.log(s) / s);
//            return v1 * multiplier;
        return rand.nextGaussian();
    }


}





