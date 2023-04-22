import Controller.PrisonController;
import View.PrisonView;

import java.awt.EventQueue;

public class App {
    public static void main(String[] args) {
       EventQueue.invokeLater(new Runnable() {
           public void run() {
               PrisonView view = new PrisonView();
               PrisonController controller = new PrisonController(view);
               controller.showPrisonView();
           }
       } );
    }
}
