# Barren Land Analysis
**Problem**\
You have a farm of 400m by 600m where coordinates of the field are from (0, 0) to (399, 599). A portion of the farm is barren, and all the barren land is in the form of rectangles. Due to these rectangles of barren land, the remaining area of fertile land is in no particular shape. An area of fertile land is defined as the largest area of land that is not covered by any of the rectangles of barren land. 
Read input from STDIN. Print output to STDOUT 
Input 
You are given a set of rectangles that contain the barren land. These rectangles are defined in a string, which consists of four integers separated by single spaces, with no additional spaces in the string. The first two integers are the coordinates of the bottom left corner in the given rectangle, and the last two integers are the coordinates of the top right corner. 
Output 
Output all the fertile land area in square meters, sorted from smallest area to greatest, separated by a space. 

--Sample Input------------------------------------------------------------Sample Output\
{“0 292 399 307”}----------------------------------------------------------116800  116800\
{“48 192 351 207”, “48 392 351 407”, “120 52 135 547”, “260 52 275 547”}---22816 192608 

 **Technology overview:**\
 This is a maven project created and developed in IntelliJ IDE. Unit testing is done using JUnit.
 
 **How to run the project?**
 * Clone this project in your local
 * Open pom.xml(inside root folder) using an IDE, preferably IntelliJ
 * Go to main class under root/src/main/java/Driver.java and you can run the application
 * All the unit tests are under src/test/java/BarrenLandTest.java
 * Logic for the solution is under src/main/java/com.target.barrenanalysis/BarrenLand.java
 * There is a user defined exception class under src/main/java/util/CoordinateOutOfBoundException.java
 * All the default properties are placed in land.properties which is inside src/main/resources
 
 **How to test the project?**\
 Project can either be tested from the Driver class or the BarrenLandTest class
 
 **What were the assumptions made while creating this application?**
 * The input array of Strings contain integer whole number values.
 * All the values in the String are within int range.
