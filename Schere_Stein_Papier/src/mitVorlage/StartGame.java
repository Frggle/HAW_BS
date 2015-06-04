package mitVorlage;

public class StartGame extends Thread
{	
	private static final int SPIELDAUER = 1000;
	
	@SuppressWarnings("rawtypes")
	private static BoundedBuffer buffer;
	private static Player spieler1;
	private static Player spieler2;
	private static Spieltisch tisch;
	
	@SuppressWarnings("rawtypes")
	public static void main(String[] args)
	{
		buffer = new BoundedBufferSyncCondQueues(1);
//		buffer = new BoundedBufferSyncMonitor(1);
		new StartGame().start();
	}
	
	@SuppressWarnings("unchecked")
	public void run()
	{
		System.err.println("Spiel wurde gestartet!");
		spieler1 = new Player("Player1", buffer);
		spieler2 = new Player("Player2", buffer);
		tisch = new Spieltisch(buffer, spieler1, spieler2);
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
