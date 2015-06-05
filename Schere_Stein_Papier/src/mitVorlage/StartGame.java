package mitVorlage;

public class StartGame extends Thread
{	
	private static final int SPIELDAUER = 500;
	
	@SuppressWarnings("rawtypes")
	private static BoundedBuffer bufferMonitor;
	@SuppressWarnings("rawtypes")
	private static BoundedBuffer bufferCond;
	private static Player spieler1;
	private static Player spieler2;
	private static Spieltisch tisch;
	
	@SuppressWarnings("rawtypes")
	public static void main(String[] args)
	{
		bufferCond = new BoundedBufferSyncCondQueues(1);
		bufferMonitor = new BoundedBufferSyncMonitor(1);
		new StartGame().start();
	}
	
	@SuppressWarnings("unchecked")
	public void run()
	{
		System.err.println("Spiel wurde gestartet!");
		
		// Jeder Spieler erhält einen eigenen (aktuell unterschiedlichen) Buffer
		spieler1 = new Player("Player1", bufferCond);
		spieler2 = new Player("Player2", bufferMonitor);
		tisch = new Spieltisch(spieler1, spieler2);
		tisch.start();
		spieler1.start();
		spieler2.start();
				
		try
		{
			sleep(SPIELDAUER);
		} catch (InterruptedException e)
		{
			//
		}
		
		tisch.interrupt();		
		spieler1.interrupt();
		spieler2.interrupt();
		
		// Ergebnisausgabe
		System.err.println(spieler1.getName() + " hat " + tisch.gibGewonneneSpiel1() + "x gewonnen");
		System.err.println(spieler2.getName() + " hat " + tisch.gibGewonneneSpiel2() + "x gewonnen");
		System.err.println("Remis: " + tisch.gibRemis());
		
		System.err.println("Spiel wurde beendet!");
	}
}
