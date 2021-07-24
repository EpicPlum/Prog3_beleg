import main.CLI.console;
import main.CLI.eventsImpl.ConsoleEventHandler;
import main.CLI.eventsImpl.ConsoleEventListener_Imp;
import main.CLI.observerPatternImpl.AllergenObserver;
import main.CLI.observerPatternImpl.CapacityObserver;

/*
main und test-Klasse
 */
public class consoleExe
{
    public static void main(String[] args)
    {
        console con = new console();
        ConsoleEventHandler handler = new ConsoleEventHandler();
        ConsoleEventListener_Imp listener = new ConsoleEventListener_Imp("listener");
        handler.add(listener);
        con.setConsoleHandler(handler);
        CapacityObserver capacity = new CapacityObserver(con, "capacity");
        AllergenObserver allergene = new AllergenObserver(con, "allergene");
        con.start();

        //System.out.println(con.commaPreisInput("1,55"));
    }
}
