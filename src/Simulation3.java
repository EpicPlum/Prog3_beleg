import main.GL.Automat;
import main.Simulation.addThread;
import main.Simulation.inspectionThread;
import main.Simulation.removeThread;

public class Simulation3
{
    public static void main(String[] args) throws InterruptedException
    {
        Automat auto = new Automat(5);
        addThread at1 = new addThread(auto, 2);
        addThread at2 = new addThread(auto, 2);
        removeThread rt1 = new removeThread(auto, 3);
        removeThread rt2 = new removeThread(auto, 3);
        inspectionThread it = new inspectionThread(auto, 3);
        at1.start();
        at2.start();
        rt1.start();
        rt2.start();
        it.start();
        it.join();
        at1.join();
        at2.join();
        rt1.join();
        rt2.join();



    }
}
