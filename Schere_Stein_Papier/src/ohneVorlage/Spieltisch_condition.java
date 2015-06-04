package ohneVorlage;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Spieltisch_condition implements I_Spieltisch
{
	/* Variablen */
	private Map<Spieler, Hand> gespielteHaende;
	private int spielerAnzahl;
	private Lock lock;
	private Condition notFull;
	private Condition notEmpty;
	
	/* Konstruktor */
	public Spieltisch_condition(int _spieleranzahl)
	{
		gespielteHaende = new HashMap<Spieler, Hand>();
		spielerAnzahl = _spieleranzahl;
		lock = new ReentrantLock(true);
		notFull = lock.newCondition(); 	// Spiel ist noch am Laufen 
		notEmpty = lock.newCondition(); // Spiel ist durch und Spieltisch darf geleert werden
		/* Condition:
		 * await() => wait()
		 * signal => notify()
		 * 
		 * ReentrantLock:
		 * lock() => synchronized
		 * unlock() => synchronized
		 */
	}
	
	/* Spieler spielt seine Hand */
	/* ENTER Funktion - aufgerufen vom Erzeuger (Spieler) */
	@Override
	public void spieleHand(Spieler spieler, Hand hand) throws InterruptedException
	{
		lock.lockInterruptibly();
		try
		{
			// wenn Spieler für die aktuelle Runde bereits eine Hand gespielt hat -> wait()ing
			while(gespielteHaende.containsKey(spieler))
			{
				System.err.println(Thread.currentThread().getName() + " wartet auf seinen Gegner");
				notFull.await();
			}
			// wenn Spieler für die aktuelle Runde noch KEINE Hand gespielt hat -> füge es in die Spielrundenmap hinzu
			gespielteHaende.put(spieler, hand);
//			System.err.println(Thread.currentThread().getName() + " hat " + hand.name() + " gespielt");
			// informiere, das Hand gespielt wurde
			notEmpty.signal();
		} finally
		{
			lock.unlock();
		}
	}
	
	/* Räumt den Spieltisch auf -> alle gespielten Hände werden gelöscht 
	 * Ist nur möglich, wenn alle Spieler eine Hand in der Runde gespielt haben. Eine Runde aussetzen ist nicht möglich */
	/* REMOVE Funktion - aufgerufen vom Verbraucher (Schiedsrichter) */
	@Override
	public boolean leereSpieltisch() throws InterruptedException
	{
		lock.lockInterruptibly();
		try
		{
			// Wenn noch nicht alle Spieler gespielt haben -> wait()ing
			while(gespielteHaende.size() != spielerAnzahl)
			{
				System.err.println("Es haben noch nicht alle gespielt!");
				notEmpty.await();
			}
			// lösche alle gespielten Hände
			gespielteHaende.clear();
			System.err.println("Nächste Runde ist möglich");
			// Informiere, neue Runde kann beginnen
			notFull.signal();
		} finally
		{
			lock.unlock();
		}
		return gespielteHaende.isEmpty();
	}
	
	/* Gibt die aktuell gespielte Hand für einen Spieler zurück */
	@Override
	public Hand gibGespielteHand(Spieler spieler) throws InterruptedException
	{
		lock.lockInterruptibly();
		try
		{
			// Wenn noch nicht alle Spieler gespielt haben -> wait()ing
			// Damit keine NullPointerException kommt, wird gewartet bis alle gespielt haben 
			while(gespielteHaende.size() != spielerAnzahl)
			{
				System.err.println("Es haben noch nicht alle eine Hand gespielt!");
				notFull.await();
			}	
			notEmpty.signal();
		} finally
		{
			lock.unlock();
		}
		return gespielteHaende.get(spieler);
	}
}
