package rollingcubes.state;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RollingCubesStateTest {
    /**
     * private void assertEmptySpace(int expectedEmptyRow, int expectedEmptyCol, RollingCubesState state) {
     * assertAll(
     * () -> assertEquals(expectedEmptyRow, state.getEmptyRow()),
     * () -> assertEquals(expectedEmptyCol, state.getEmptyCol())
     * );
     * }
     *
     * @Test void testOneArgConstructor_InvalidArg() {
     * assertThrows(IllegalArgumentException.class, () -> new RollingCubesState(null));
     * assertThrows(IllegalArgumentException.class, () -> new RollingCubesState(new int[][] {
     * {1, 1},
     * {1, 0}})
     * );
     * assertThrows(IllegalArgumentException.class, () -> new RollingCubesState(new int[][] {
     * {0},
     * {1, 2},
     * {3, 4, 5}})
     * );
     * assertThrows(IllegalArgumentException.class, () -> new RollingCubesState(new int[][] {
     * {1, 2, 3},
     * {4, 5, 6},
     * {7, 8, 9}})
     * );
     * assertThrows(IllegalArgumentException.class, () -> new RollingCubesState(new int[][] {
     * {1, 1, 1},
     * {1, 1, 1},
     * {1, 1, 1}})
     * );
     * assertThrows(IllegalArgumentException.class, () -> new RollingCubesState(new int[][] {
     * {0, 1, 1},
     * {1, 1, 1},
     * {1, 1, 0}})
     * );
     * }
     * @Test void testOneArgConstructor_ValidArg() {
     * int[][] a = new int[][] {
     * {1, 1, 1},
     * {1, 0, 1},
     * {1, 1, 1}
     * };
     * RollingCubesState state = new RollingCubesState(a);
     * assertArrayEquals(new Cube[][] {
     * {Cube.CUBE1, Cube.CUBE1, Cube.CUBE1},
     * {Cube.CUBE1, Cube.EMPTY, Cube.CUBE1},
     * {Cube.CUBE1, Cube.CUBE1, Cube.CUBE1}
     * }, state.getTray());
     * assertEmptySpace(1, 1, state);
     * }
    **/
    @Test
    void testRandom() {
        RollingCubesState rollingCubesState = new RollingCubesState(8);
        RollingCubesState rollingCubesState1 = new RollingCubesState(8);
        assertNotEquals(rollingCubesState, rollingCubesState1);
    }

    @Test
    void testinicialize() {
        RollingCubesState rollingCubesState = new RollingCubesState(0);
        int[] a = {1, 2, 1, 2};
        assertEquals(a[0], rollingCubesState.inicialize(4)[0]);
        assertEquals(a[1], rollingCubesState.inicialize(4)[1]);
        assertEquals(a[2], rollingCubesState.inicialize(4)[2]);
        assertEquals(a[3], rollingCubesState.inicialize(4)[3]);
    }

    @Test
    void testcontain() {
        RollingCubesState rollingCubesState = new RollingCubesState(0);
        int[] a = {1, 2, 1, 2};
        assertTrue(rollingCubesState.contain(1, a));
        assertTrue(rollingCubesState.contain(1, a));
        assertFalse(rollingCubesState.contain(1, a));
        assertTrue(rollingCubesState.contain(2, a));
        assertEquals(- 1, a[0]);
        assertEquals(- 1, a[2]);
        assertEquals(- 1, a[1]);
        assertEquals(2, a[3]);
    }

    @Test
    void testIsDisplayed() {
        RollingCubesState rollingCubesState = new RollingCubesState(0);
        rollingCubesState.number = new int[][]{
                {10, 3, 2},
                {3, 5, 2},
                {6, 10, 5}
        };
        assertFalse(rollingCubesState.isDisplayed(2, 2));
        assertTrue(rollingCubesState.isDisplayed(0, 0));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> rollingCubesState.isDisplayed(3, 3));
    }

    @Test
    void testcanDisplayMore() {
        RollingCubesState rollingCubesState = new RollingCubesState(0);
        rollingCubesState.number = new int[][]{
                {10, 3, 2},
                {3, 5, 2},
                {6, 10, 5}
        };
        assertTrue(rollingCubesState.canDisplayMore());
        rollingCubesState.number = new int[][]{
                {10, 3, 2},
                {3, 5, 2},
                {6, 10, - 5}
        };
        assertTrue(rollingCubesState.canDisplayMore());
        rollingCubesState.number = new int[][]{
                {10, 3, 2},
                {3, - 5, 2},
                {6, 10, -5}
        };
        assertFalse(rollingCubesState.canDisplayMore());
    }

    @Test
    void testdisplay() {
        RollingCubesState rollingCubesState = new RollingCubesState(0);
        rollingCubesState.number = new int[][]{
                {10, 3, 2},
                {3, 5, 2},
                {6, 10, 5}
        };
        rollingCubesState.display(0, 1);
        assertEquals(rollingCubesState.number[0][1], -3);
        rollingCubesState.display(0, 1);
        assertNotEquals(rollingCubesState.number[0][1], -3);
        assertEquals(rollingCubesState.number[0][1], 3);
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> rollingCubesState.display(3, 3));
    }


    @Test
    void testisSolved() {
        RollingCubesState rollingCubesState = new RollingCubesState(0);
        rollingCubesState.number = new int[][]{
                {1, 3, 2},
                {3, 5, 2},
                {6, 1, 5}
        };
        assertFalse(rollingCubesState.isSolved());
        rollingCubesState.number = new int[][]{
                {-1, 3, 2},
                {3, 5, 2},
                {6, - 1, 5}
        };
        assertFalse(rollingCubesState.isSolved());
        rollingCubesState.number = new int[][]{
                {- 10, -3, 2},
                {3, 5, 2},
                {6, 10, 5}
        };
        assertFalse(rollingCubesState.isSolved());
        rollingCubesState.number = new int[][]{
                {1, -3, 2},
                {- 3, 5, 2},
                {6, 1, 5}
        };
        assertFalse(rollingCubesState.isSolved());
        rollingCubesState.number = new int[][]{
                {10, 30, 2},
                {30, 5, 2},
                {6, 10, 5}
        };
        assertFalse(rollingCubesState.isSolved());
        rollingCubesState.number = new int[][]{
                {10, 30, 20},
                {30, 5, 20},
                {6, 10, 5}
        };
        assertFalse(rollingCubesState.isSolved());
        rollingCubesState.number = new int[][]{
                {10, 30, 20},
                {30, 50, 20},
                {6, 10, 50}
        };
        assertTrue(rollingCubesState.isSolved());
        rollingCubesState.number = new int[][]{
                {10, 30, 20},
                {30, 50, 20},
                {- 6, 10, 50}
        };
        assertTrue(rollingCubesState.isSolved());
    }

    @Test
    void testshouldCheck(){
        RollingCubesState rollingCubesState = new RollingCubesState(0);
        rollingCubesState.number = new int[][]{
                {1, 3, 2},
                {3, 5, 2},
                {6, 1, 5}
        };
        assertFalse(rollingCubesState.shouldCheck());
        rollingCubesState.number = new int[][]{
                {- 1, 3, 2},
                {3, 5, 2},
                {6, 1, 5}
        };
        assertFalse(rollingCubesState.shouldCheck());
        rollingCubesState.number = new int[][]{
                {- 1, 3, 2},
                {3, 5, 2},
                {6, - 1, 5}
        };
        assertTrue(rollingCubesState.shouldCheck());
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> rollingCubesState.display(3, 3));
    }

    @Test
    void testcheck(){
        RollingCubesState rollingCubesState = new RollingCubesState(0);
        rollingCubesState.number = new int[][]{
                {- 1, - 3, 2},
                {3, 5, 2},
                {6, 1, 5}
        };
        rollingCubesState.check();
        assertEquals(rollingCubesState.number[0][0], 1);
        assertEquals(rollingCubesState.number[0][1], 3);
        rollingCubesState.number = new int[][]{
                {- 1, 3, 2},
                {3, 5, 2},
                {6, - 1, 5}
        };
        rollingCubesState.check();
        assertEquals(rollingCubesState.number[0][0], 10);
        assertEquals(rollingCubesState.number[2][1], 10);
    }
}
