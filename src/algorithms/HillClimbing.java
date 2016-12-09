package algorithms;

import problems.EightQueens;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HillClimbing {


    private static final int SIMPLE_HILL_CLIMBING = 1;
    private static final int RANDOM_HILL_CLIMBING = 2;
    private static final int FIRST_BEST_HILL_CLIMBING = 3;

    ArrayList<String> paths = new ArrayList<>();
    ArrayList<String> views = new ArrayList<>();


    int viewedNodes = 0;
    int openedNodes = 0;

    public HillClimbing() {

        EightQueens e = new EightQueens("11111111");
        climb(e, SIMPLE_HILL_CLIMBING,true);

    }

    private void climb(EightQueens e, int type, boolean randomStart) {


        List<String> states = e.getPossibleStates();
        openedNodes++;
        List<Integer> utilitys = new ArrayList<>();

        for (String state : states)
            utilitys.add(e.test(state));

        System.out.println(states);
        System.out.println(utilitys);


        if (type == SIMPLE_HILL_CLIMBING) {
            int min = 8;
            int selected = 1;
            for (int c = 0; c < 8; c++) {
                viewedNodes++;
                if (utilitys.get(c) <= min ) {
                    min = utilitys.get(c);
                    selected = c;
                }
            }

            System.out.println(states.get(selected));
            e.changeState(states.get(selected));
        } else if (type == RANDOM_HILL_CLIMBING) {
            int min = 8;
            ArrayList<Integer> selected = new ArrayList();
            for (int c = 0; c < 8; c++) {
                viewedNodes++;
                if (utilitys.get(c) < min) {
                    selected = new ArrayList<>();
                    min = utilitys.get(c);
                    selected.add(c);
                } else if (utilitys.get(c) == min)
                    selected.add(c);
            }
            String _selected = states.get(selected.get(randInt(0, selected.size() - 1)));
            System.out.println(_selected);
            e.changeState(_selected);
        } else if (type == FIRST_BEST_HILL_CLIMBING) {
            ArrayList<Integer> viwed = new ArrayList<>();
            boolean flag = true;
            int current = e.test(e.getCurrentState());

            while (viwed.size() < 8 && flag) {
                int random = randInt(0, 7);
                if (!viwed.contains(random)) {
                    viewedNodes++;
                    viwed.add(random);
                    if (utilitys.get(random) <= current) {
                        String _selected = states.get(random);
                        System.out.println(_selected);
                        e.changeState(_selected);
                        flag = false;
                    }
                }
            }
        }

        if (e.isGoal()) {
            if(!paths.contains(e.getCurrentState())) {
                paths.add(e.getCurrentState());
                views.add(" view:" + viewedNodes + " open:" + openedNodes);
            }

            if(randomStart){
                String randomState = Integer.toString(randInt(1,8));
                for(int c = 0 ;c<7;c++)
                    randomState += Integer.toString(randInt(1,8));
                e.changeState(randomState);
            }

        }
        if (viewedNodes < 1000)
            climb(e, type,randomStart);
        else
            for(String path : paths)
            System.out.println("paths:" +path+" "+   views.get(paths.indexOf(path)));
    }


    public static int randInt(int min, int max) {

        // NOTE: This will (intentionally) not run as written so that folks
        // copy-pasting have to think about how to initialize their
        // Random instance.  Initialization of the Random instance is outside
        // the main scope of the question, but some decent options are to have
        // a field that is initialized once and then re-used as needed or to
        // use ThreadLocalRandom (if using at least Java 1.7).
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }

    public static void main(String args[]) {
        HillClimbing hillClimbing = new HillClimbing();
    }
}
