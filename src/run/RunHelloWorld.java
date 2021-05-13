package run;

import mvc.HelloModel;
import mvc.HelloViewController;

import java.io.File;

public class RunHelloWorld {
    public static void main(String[] args) {
        HelloModel model = new HelloModel();
        File save = new File("save.txt");

        new HelloViewController(model, save);
    }
}
