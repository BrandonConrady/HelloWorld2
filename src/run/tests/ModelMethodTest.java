package run.tests;

import mvc.HelloModel;

public class ModelMethodTest {
    public static void main(String[] args) {
        HelloModel model = new HelloModel();

        //first method just prints the message
        System.out.println("Method 1: printMessage()\n");
        model.printMessage();

        //the second method does the same, but uses println instead of print
        System.out.println("\n\nMethod 2: printMessageLine()\n");
        model.printMessageLine();

        //this one prints the message a specified number of times
        System.out.println("\n\nMethod 3: printMessageMulti(3)\n");
        model.printMessageMulti(3);

        //this method prints n lines, each containing n instances of the message where n is the given value
        System.out.println("\n\nMethod 4: printMessageSquared(2)\n");
        model.printMessageSquared(2);
    }
}
