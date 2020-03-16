package com.target.barrenanalysis;

import util.CoordinateOutOfBoundException;
import util.PropertyUtil;

import java.util.*;

public class BarrenLand {
    private PropertyUtil propertyUtil = new PropertyUtil();
    private Queue<Integer[]> allBarrenRectangleNodes;
    private Queue<Integer[]> toBeVisitedFertileNodes;
    private Map<Integer, Integer> fertileLandAreas;
    private int[][] barrenOrFertileLandTracker;
    private int threshold_x = Integer.parseInt(this.propertyUtil.getProperty("threshold.x"));
    private int threshold_y = Integer.parseInt(this.propertyUtil.getProperty("threshold.y"));
    private int landBottomXCoordinate = Integer.parseInt(this.propertyUtil.getProperty("land.bottom.x.coordinate"));
    private int landBottomYCoordinate = Integer.parseInt(this.propertyUtil.getProperty("land.bottom.y.coordinate"));
    private int landUpperXCoordinate = Integer.parseInt(this.propertyUtil.getProperty("land.upper.x.coordinate"));
    private int landUpperYCoordinate = Integer.parseInt(this.propertyUtil.getProperty("land.upper.y.coordinate"));

    public BarrenLand() {
        allBarrenRectangleNodes = new LinkedList<Integer[]>();
        toBeVisitedFertileNodes = new LinkedList<Integer[]>();
        fertileLandAreas = new HashMap<Integer, Integer>();
        barrenOrFertileLandTracker = new int[threshold_x][threshold_y];
    }

    /**
     * Calculate fertile land area
     *
     * @param input array of barren rectangles
     * @return sorted areas of fertile land converted to string
     * @throws CoordinateOutOfBoundException
     */
    public String getFertileLandArea(String input[]) throws CoordinateOutOfBoundException {
        readInputAndAddBarrenRectangles(input);
        markAllBarrenRectangleNodesTo1();
        int tile = 1;
        int i = landBottomXCoordinate;
        int j = landBottomYCoordinate;
        while (i < threshold_x && j < threshold_y) {
            if (toBeVisitedFertileNodes.isEmpty()) {
                Integer node[] = {i, j};
                //if the current node is a fertile one
                if (barrenOrFertileLandTracker[i][j] == 0) {
                    tile++;
                    fertileLandAreas.put(tile, 0);
                    toBeVisitedFertileNodes.add(node);
                }
                if (i == threshold_x - 1) {
                    i = 0;
                    j++;
                } else {
                    i++;
                }
            }
            if (!toBeVisitedFertileNodes.isEmpty()) {
                Integer node[] = toBeVisitedFertileNodes.poll();
                int x = node[0];
                int y = node[1];
                if (barrenOrFertileLandTracker[x][y] == 0) {
                    nextDirectionsFromCurrentNode(x, y);
                    barrenOrFertileLandTracker[x][y] = tile;
                    fertileLandAreas.put(tile, fertileLandAreas.get(tile) + 1);
                }
            }
        }
        return formatOutput();
    }

    /**
     * Process the input and then add all the barren rectangles to the barren queue
     *
     * @param input array of strings with barren rectangle coordinates
     * @throws CoordinateOutOfBoundException
     */
    public void readInputAndAddBarrenRectangles(String input[]) throws CoordinateOutOfBoundException {
        for (int i = 0; i < input.length; i++) {
            //check for null or empty String
            if (input[i] == null || input[i].length() == 0) {
                continue;
            }
            String[] StringCoordinate = input[i].split(" ");
            int bottomLeftXCoordinate;
            int bottomLeftYCoordinate;
            int upperRightXCoordinate;
            int upperRightYCoordinate;

            //in case the input does not contain numbers
            try {
                bottomLeftXCoordinate = Integer.parseInt(StringCoordinate[0]);
                bottomLeftYCoordinate = Integer.parseInt(StringCoordinate[1]);
                upperRightXCoordinate = Integer.parseInt(StringCoordinate[2]);
                upperRightYCoordinate = Integer.parseInt(StringCoordinate[3]);
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
                throw new NumberFormatException("Invalid characters present. Please try again with numbers:");
            }
            //verify that the barren land is within total land coordinates
            if (bottomLeftXCoordinate < landBottomXCoordinate || bottomLeftXCoordinate >= landUpperXCoordinate) {
                throw new CoordinateOutOfBoundException("Barren land's X coordinate " + bottomLeftXCoordinate + " should be more than or equal to " + landBottomXCoordinate +
                        " and less than " + landUpperXCoordinate);
            }
            if (bottomLeftYCoordinate < landBottomYCoordinate || bottomLeftYCoordinate >= landUpperYCoordinate) {
                throw new CoordinateOutOfBoundException("Barren land's Y coordinate " + bottomLeftYCoordinate + " should be more than or equal to " + landBottomYCoordinate +
                        " and less than " + landUpperYCoordinate);
            }
            if (upperRightXCoordinate > landUpperXCoordinate || upperRightXCoordinate <= landBottomXCoordinate) {
                throw new CoordinateOutOfBoundException("Barren land's X coordinate " + upperRightXCoordinate + " should be more than " + landBottomXCoordinate +
                        " and less than equal to " + landUpperXCoordinate);
            }
            if (upperRightYCoordinate > landUpperYCoordinate || upperRightYCoordinate <= landBottomYCoordinate) {
                throw new CoordinateOutOfBoundException("Barren land's Y coordinate " + upperRightYCoordinate + " should be more than " + landBottomYCoordinate +
                        " and less than equal to " + landUpperYCoordinate);
            }

            //add the coordinates to the Barren collection of rectangles
            Integer[] IntegerCoordinate = {bottomLeftXCoordinate, bottomLeftYCoordinate, upperRightXCoordinate, upperRightYCoordinate};
            allBarrenRectangleNodes.add(IntegerCoordinate);
        }
    }

    /**
     * Mark all barren field nodes to 1 in the tracker
     */
    public void markAllBarrenRectangleNodesTo1() {
        for (Integer[] rectangle : allBarrenRectangleNodes) {
            for (int i = rectangle[0]; i <= rectangle[2]; i++) {
                for (int j = rectangle[1]; j <= rectangle[3]; j++) {
                    barrenOrFertileLandTracker[i][j] = 1;
                }
            }
        }
    }

    /**
     * Check the current positions and based on that decide the next nodes to add in the queue. Movement happens horizontally and vertically
     *
     * @param x coordinate
     * @param y coordinate
     */
    public void nextDirectionsFromCurrentNode(int x, int y) {
        if (x > 0) {
            addNextToVisitCoordinatesInTheToBeVisitedQueue(x - 1, y);
        }
        if (x < threshold_x - 1) {
            addNextToVisitCoordinatesInTheToBeVisitedQueue(x + 1, y);
        }
        if (y > 0) {
            addNextToVisitCoordinatesInTheToBeVisitedQueue(x, y - 1);
        }
        if (y < threshold_y - 1) {
            addNextToVisitCoordinatesInTheToBeVisitedQueue(x, y + 1);
        }
    }

    /**
     * Adds next to visit coordinates in the toBeVisitedQueue
     *
     * @param i
     * @param j
     */
    public void addNextToVisitCoordinatesInTheToBeVisitedQueue(int i, int j) {
        if (barrenOrFertileLandTracker[i][j] == 0) {
            toBeVisitedFertileNodes.add(new Integer[]{i, j});
        }
    }

    /**
     * Returns sorted string with all the fertile areas from areasMap values
     *
     * @return String
     */
    public String formatOutput() {
        int[] result = new int[fertileLandAreas.values().size()];
        int index = 0;
        for (Map.Entry<Integer, Integer> entry : fertileLandAreas.entrySet()) {
            result[index] = entry.getValue();
            index++;
        }
        Arrays.sort(result);
        StringBuilder resultantString = new StringBuilder();
        for (int i = 0; i < result.length; i++) {
            resultantString.append(result[i] + " ");
        }
        return resultantString.toString().trim();
    }
}