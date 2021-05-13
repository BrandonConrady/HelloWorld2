package mvc;

public class HelloModel implements HelloWorld {
    private final String message;
    private int value;

    public HelloModel() {
        this.message = HELLO_WORLD;
        this.value = 1;
    }

    ///getters and setters///

    //message
    public String getMessage() {
        return message;
    }

    //value
    public int getValue() {
        return value;
    }

    public void incrementValue(){
        value++;
    }

    public void decrementValue(){
        value--;
    }

    public void setValueMax(){
        value = COUNT_MAX;
    }

    public void setValueMin(){
        value = COUNT_MIN;
    }

    public void setValue(int val){
        this.value = val;
    }

    ///message printers///

    //prints message once
    public void printMessage(){
        System.out.print(getMessage() + " ");
    }

    //prints message once but using println() instead of print()
    public void printMessageLine(){
        System.out.println(getMessage());
    }

    //prints message multiple times based on input value
    public void printMessageMulti(int value){
        if(value > 0){
            for(int i = 0; i < value; i++){
                printMessageLine();
            }
        }
    }

    //prints a n x n square of the message based on input value
    public void printMessageSquared(int value){
        if(value > 0){
            for(int i = 0; i < value; i++){
                for(int j = 0; j < value; j++){
                    printMessage();
                }
                System.out.println();
            }
        }
    }
}
