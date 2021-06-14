import main.GL.Automat;
import main.Simulation.addThread;
import main.Simulation.inspectionThread;
import main.Simulation.removeThread;

public class Simulation3
{
    public static void main(String[] args) throws InterruptedException
    {
        Automat auto = new Automat(5);
        addThread at = new addThread(auto, 3);
        inspectionThread it = new inspectionThread(auto, 3);
        removeThread rt = new removeThread(auto, 3);
        at.start();
        rt.start();
        it.start();
        it.join();
        at.join();
        rt.join();



    }
}
