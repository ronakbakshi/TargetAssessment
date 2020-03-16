import com.target.barrenanalysis.BarrenLand;
import util.CoordinateOutOfBoundException;

import static org.junit.jupiter.api.Assertions.*;

class BarrenLandTest {
    private static final int LAND_BOTTOM_X_COORDINATE = 0;
    private static final int LAND_BOTTOM_Y_COORDINATE = 0;
    private static final int LAND_UPPER_X_COORDINATE = 399;
    private static final int LAND_UPPER_Y_COORDINATE = 599;

    @org.junit.jupiter.api.Test
    void getFertileLandAreaTestWhenInputContainsMultipleBarrenLandsTest() throws CoordinateOutOfBoundException {
        String[] input = new String[]{"48 192 351 207", "48 392 351 407", "120 52 135 547", "260 52 275 547"};
        BarrenLand barrenLand = new BarrenLand();
        String actual = barrenLand.getFertileLandArea(input);
        String expected = "22816 192608";
        assertEquals(expected, actual, "Mismatch in the expected and actual");
    }

    @org.junit.jupiter.api.Test
    void getFertileLandAreaTestWhenInputContainsSingleBarrenLandTest() throws CoordinateOutOfBoundException {
        String[] input = new String[]{"0 292 399 307"};
        BarrenLand barrenLand = new BarrenLand();
        String actual = barrenLand.getFertileLandArea(input);
        String expected = "116800 116800";
        assertEquals(expected, actual, "Mismatch in the expected and actual");
    }

    @org.junit.jupiter.api.Test
    void getFertileLandAreaTestWhenInputContainsNullTest() throws CoordinateOutOfBoundException {
        String[] input = new String[]{null};
        BarrenLand barrenLand = new BarrenLand();
        String actual = barrenLand.getFertileLandArea(input);
        String expected = "240000";
        assertEquals(expected, actual, "Mismatch in the expected and actual");
    }

    @org.junit.jupiter.api.Test
    void getFertileLandAreaTestWhenInputContainsIsEmptyTest() throws CoordinateOutOfBoundException {
        String[] input = new String[]{""};
        BarrenLand barrenLand = new BarrenLand();
        String actual = barrenLand.getFertileLandArea(input);
        String expected = "240000";
        assertEquals(expected, actual, "Mismatch in the expected and actual");
    }

    @org.junit.jupiter.api.Test
    void getFertileLandAreaTestWhenInputContainsBadCharactersTest() throws CoordinateOutOfBoundException {
        String[] input = new String[]{"48 192 351 207", "48 392 351 407", "120 52 135 547", "a b c d"};
        BarrenLand barrenLand = new BarrenLand();

        Exception actualMessage = assertThrows(NumberFormatException.class, () -> {
            barrenLand.getFertileLandArea(input);
        });
        String expectedMessage = "Invalid characters present. Please try again with numbers:";
        assertEquals(expectedMessage, actualMessage.getMessage(), "Mismatch in the expected and actual");
    }

    @org.junit.jupiter.api.Test
    void getFertileLandAreaTestWhenInputContainsXCoordinateWhichIsOutOfLandTest() throws CoordinateOutOfBoundException {
        String[] input = new String[]{"1048 192 351 207", "48 392 351 407", "120 52 135 547"};
        BarrenLand barrenLand = new BarrenLand();

        Exception actualMessage = assertThrows(CoordinateOutOfBoundException.class, () -> {
            barrenLand.getFertileLandArea(input);
        });
        String expectedMessage = "Barren land's X coordinate " + 1048 + " should be more than or equal to " + LAND_BOTTOM_X_COORDINATE + " and less than " + LAND_UPPER_X_COORDINATE;
        assertEquals(expectedMessage, actualMessage.getMessage(), "Mismatch in the expected and actual");
    }

    @org.junit.jupiter.api.Test
    void getFertileLandAreaTestWhenInputContainsYCoordinateWhichIsOutOfLandTest() throws CoordinateOutOfBoundException {
        String[] input = new String[]{"10 19002 351 207"};
        BarrenLand barrenLand = new BarrenLand();

        Exception actualMessage = assertThrows(CoordinateOutOfBoundException.class, () -> {
            barrenLand.getFertileLandArea(input);
        });
        String expectedMessage = "Barren land's Y coordinate " + 19002 + " should be more than or equal to " + LAND_BOTTOM_Y_COORDINATE + " and less than " + LAND_UPPER_Y_COORDINATE;
        assertEquals(expectedMessage, actualMessage.getMessage(), "Mismatch in the expected and actual");
    }
}