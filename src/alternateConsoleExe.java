import main.CLI.console;
import main.CLI.eventsImpl.ConsoleEventHandler;
import main.CLI.eventsImpl.ConsoleEventListener_Imp;

/*
main und test-Klasse
 */
public class alternateConsoleExe
{
    public static void main(String[] args)
    {
        console con = new console();
        ConsoleEventHandler handler = new ConsoleEventHandler();
        ConsoleEventListener_Imp listener = new ConsoleEventListener_Imp("listener");
        handler.add(listener);
        con.setConsoleHandler(handler);
        con.start();
    }
}
