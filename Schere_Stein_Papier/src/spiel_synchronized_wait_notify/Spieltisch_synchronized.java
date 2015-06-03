package spiel_synchronized_wait_notify;

import java.util.HashMap;
import java.util.Map;

public class Spieltisch_synchronized
{
	/* Variablen */
	private Map<Spieler, Hand> gespielteHaende;
	private int spielerAnzahl;
	
	/* Konstruktor */
	public Spieltisch_synchronized(int _spieleranzahl)
	{
		gespielteHaende = new HashMap<Spieler, Hand>();
		spielerAnzahl = _spieleranzahl;
	}
	
	/* Spieler spielt seine Hand */
	public synchronized void spieleHand(Spieler spieler, Hand hand) throws InterruptedException
	{
		// wenn Spieler für die aktuelle Runde bereits eine Hand gespielt hat -> wait()ing
		while(gespielteHaende.containsKey(spieler))
		{
			System.err.println(Thread.currentThread().getName() + " hat bereits eine Hand gespielt und wartet auf den Gegner");
			wait();
		}
		// wenn Spieler für die aktuelle Runde noch KEINE Hand gespielt hat -> füge es in die Spielrundenmap hinzu
		gespielteHaende.put(spieler, hand);
//		System.err.println(Thread.currentThread().getName() + " hat " + hand.name() + " gespielt");
		// informiere alle, das Hand gespielt wurde
		notifyAll();
	}
	
	/* Räumt den Spieltisch auf -> alle gespielten Hände werden gelöscht 
	 * Ist nur möglich, wenn alle Spieler eine Hand in der Runde gespielt haben. Eine Runde aussetzen ist nicht möglich */
	public synchronized boolean leereSpieltisch() throws InterruptedException
	{
		// Wenn noch nicht alle Spieler gespielt haben -> wait()ing
		while(gespielteHaende.size() != spielerAnzahl)
		{
			System.err.println("Es haben noch nicht alle gespielt!");
			wait();
		}
		// lösche alle gespielten Hände
		gespielteHaende.clear();
		System.err.println("Nächste Runde ist möglich");
		// Informiere alle, neue Runde kann beginnen
		notifyAll();
		return gespielteHaende.isEmpty();
	}
	
	/* Gibt die aktuell gespielte Hand für einen Spieler zurück */
	public synchronized Hand gibGespielteHand(Spieler spieler) throws InterruptedException
	{
		// Wenn noch nicht alle Spieler gespielt haben -> wait()ing
		// Damit keine NullPointerException kommt, wird gewartet bis alle gespielt haben 
		while(gespielteHaende.size() != spielerAnzahl)
		{
			System.err.println("Es haben noch nicht alle eine Hand gespielt!");
			wait();
		}
		return gespielteHaende.get(spieler);
	}
}
