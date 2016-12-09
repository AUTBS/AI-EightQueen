package problems;

import java.util.ArrayList;
import java.util.List;

public class EightQueens extends Problem {


    private int selectedQueen = 1;

    public EightQueens(String initialState) {
        super(initialState);
    }

    @Override
    public int test(String state) {

        int confilict = 0;
        int _state = Integer.parseInt(state);
        int[] queens = new int[9];

        queens[1] = _state % 10;
        queens[2] = (_state % 100) / 10;
        queens[3] = (_state % 1000) / 100;
        queens[4] = (_state % 10000) / 1000;
        queens[5] = (_state % 100000) / 10000;
        queens[6] = (_state % 1000000) / 100000;
        queens[7] = (_state % 10000000) / 1000000;
        queens[8] = (_state % 100000000) / 10000000;

        for (int q = 1; q < 9; q++)
            if (queens[q] == queens[selectedQueen] && q != selectedQueen)
                confilict++;
            else {
                int diff = selectedQueen - q;
                if (diff != 0)
                    if (queens[q] - queens[selectedQueen] == -diff || queens[q] - queens[selectedQueen] == diff)
                        confilict++;
            }

        return confilict;
    }

    @Override
    public String getCurrentState() {
        return super.getCurrentState();
    }

    @Override
    public List<String> getPossibleStates() {
        List<String> states = new ArrayList<>();
        for (int c = 1; c < 9; c++) {
            String state = "";
            if (selectedQueen == 1)
                state = currentState.substring(0, 7) + c;
            else if (selectedQueen == 8)
                state = c + currentState.substring(1, 8);
            else
                state = currentState.substring(0, 8 - selectedQueen ) + c + currentState.substring(9-selectedQueen, 8);
            states.add(state);
        }
        return states;
    }


    @Override
    public void changeState(String state) {
        super.changeState(state);
        selectedQueen = ((selectedQueen) % 8) + 1;
    }

    @Override
    public boolean isGoal() {
        int goal = 0;
        int temp = selectedQueen;
        for(int c = 1 ; c < 9 ; c++){
            selectedQueen = c;
            goal += test(getCurrentState());
        }
        selectedQueen = temp;
        return goal == 0;
    }
}
