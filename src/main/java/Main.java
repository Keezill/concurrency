import controller.Controller;

public class Main {

    public static void main(String[] args){
        try {
            Controller.run();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
