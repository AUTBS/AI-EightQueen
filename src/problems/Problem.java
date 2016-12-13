package problems;

import java.util.ArrayList;
import java.util.List;

public class Problem {

    String currentState;


    Problem(String initialState){
        currentState = initialState;
    }

    public void changeState(String state){
        currentState = state;
    }

    public int test(String state){
        return 0;
    }

    public String getCurrentState() {
        return currentState;
    }

    public List<String> getPossibleStates(){

        return new ArrayList<>();

    }

    public boolean isGoal(){
        return false;
    }

}
