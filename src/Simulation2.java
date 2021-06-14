import main.GL.*;
import main.Simulation.addThread;
import main.Simulation.inspectionThread;
import main.Simulation.removeThread;

public class Simulation2
{
    public static void main(String[] args) throws InterruptedException
    {
        Automat auto = new Automat(5);
        addThread at = new addThread(auto, 2);
        inspectionThread it = new inspectionThread(auto, 2);
        removeThread rt = new removeThread(auto, 2);
        at.start();
        rt.start();
        it.start();
        it.join();
        at.join();
        rt.join();



    }
}
