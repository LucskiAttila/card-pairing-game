package rollingcubes.state;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Class representing the state of the puzzle.
 */
@Data
@Slf4j
public class RollingCubesState implements Cloneable {

    /**
     * Use for holding the picture indexes, that we use for reprezenting the game.
     */
    public int[][] number;

    /** A constructor, use for creating a new game by inicialize number array again, which
     *  use for holding the picture indexes, that we use for reprezenting the game.
     * Generating different start game position by using Random class.
     **/
    public RollingCubesState(int x) {
        Random rand = new Random();
        int[][] number = new int[x / 2][x / 2];
        int[] a = inicialize(x * 2);
        for(int i = 0; i < number.length; i++) {
            for (int j = 0; j < number[0].length; j++) {
                int n = rand.nextInt(x) + 1;
                while(!contain(n, a)) {
                    n = rand.nextInt(x) + 1;
                }
                number[i][j] = n;
            }
        }
        this.number = number;
        log.info("Cards are ready to play!!!");
    }

    /**
     * Use for inicialize an arry with the given range of values.
     * @param a the maximum value - 1 of the array
     * @return the array which filled by values from 1 to {@code a} - 1
     */
    public int[] inicialize(int a) {
        int[] b = new int[a];
        for(int i = 0; i < a; i++) {
            b[i] = i % (a / 2) + 1;
        }
        return b;
    }

    /**
     * Use for checking that the given {@code a} array contains {@code n} integer and modify the first matching element.
     * @param n an integer which will be compare to the {@code a} values
     * @param a an array which values will be compared by {@code n}
     * @return true if {@code a} array contains {@code n}
     */
    public boolean contain(int n, int a[]) {
        for(int i = 0; i < a.length; i ++) {
            if (a[i] == n) {
                a[i] = - 1;
                return true;
            }
        }
        return false;
    }

    /**
     * Check if the card was used in a pair.
     * @param row the row of the card to be display
     * @param col the col of the card to be display
     * @return {@code true} if the card was used, {@code false} otherwise
     */
    public boolean isDisplayed(int row, int col) {
       return number[row][col] % 10 == 0;
    }

    /**
     * Check if the card can be use as a tag of a pair.
     * @return {@code true} if the card be use for pair, {@code false} otherwise
     */
    public boolean canDisplayMore() {
        int n = 0;
        for (int i = 0; i < number.length; i++) {
            for (int j = 0; j < number[0].length; j++) {
                if (number[i][j] < 0) {
                    n++;
                }
            }
        }
        return n < 2;
    }

    /**
     * Change the value of the picked card for displaying it.
    * @param row the row of the card to be display
     * @param col the column of the card to be display
     **/
    public void display(int row, int col) {
        number[row][col] *= -1;
    }

    /**
     * Checks whether the puzzle is solved.
     *
     * @return {@code true} if the puzzle is solved, {@code false} otherwise
     */
    public boolean isSolved() {
        int n = 0;
        for (int i = 0; i < number.length; i++) {
            for (int j = 0; j < number[0].length; j++) {
                if (number[i][j] % 10 == 0) {
                    n++;
                }
            }
        }
        return n == (int) (number.length * number[0].length / 2) * 2;
    }

    /**
     * Checks whether the puzzle has two cards to check up.
     *
     * @return {@code true} if the puzzle should be check, {@code false} otherwise
     */
    public boolean shouldCheck(){
        int n = 0;
        for (int i = 0; i < number.length; i++) {
            for (int j = 0; j < number[0].length; j++) {
                if (number[i][j] < 0) {
                    n++;
                }
            }
        }
        return n == 2;
    }

    /**
     * Checks whether the user found a pair, first found the pairs, than modify the the value of them depending on how the user a matching or not matching pair.
     */
    public void check() {
        int row1, col1, row2, col2;
        row1 = col1 = row2 = col2 = - 1;
        for (int i = 0; i < number.length; i++) {
            for (int j = 0; j < number[0].length; j++) {
                if (number[i][j] < 0) {
                    row2 = i;
                    col2 = j;
                }
            }
        }
        for (int i = number.length - 1; i >= 0; i--) {
            for (int j = number[0].length - 1; j >= 0; j--) {
                if (number[i][j] < 0) {
                    row1 = i;
                    col1 = j;
                }
            }
        }
        if (number[row1][col1] == number[row2][col2]) {
            number[row1][col1] *= - 10;
            number[row2][col2] *= - 10;
            log.info("Cards at ({},{}) is paired with ({},{})", row1, col1, row2, col2 );
        } else {
            number[row1][col1] *= - 1;
            number[row2][col2] *= - 1;
            log.info("MISTAKE!!! Cards at ({},{}) is unpaired with ({},{})", row1, col1, row2, col2 );
        }
    }
}
