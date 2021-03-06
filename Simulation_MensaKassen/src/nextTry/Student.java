package nextTry;

import java.util.List;
import java.util.concurrent.Semaphore;

public class Student extends Thread
{
	/* Variablen */
	private List<Kasse> kassenListe;
	private static final Semaphore semaphore = new Semaphore(1, true);
	
	/* Konstruktor */
	public Student(String name, List<Kasse> _kassen)
	{
		super(name);
		kassenListe = _kassen;
	}
	
	/* wird von start() aufgerufen */
	public void run()
	{
		try
		{
			// Wiederhole, bis Mensa schließt
			while(!isInterrupted())
			{
				semaphore.acquire();
				// Suche die Kasse mit der kürzesten Warteschlange
				Kasse anstellKasse = null;
				for(Kasse k : kassenListe)
				{
					if(anstellKasse == null || anstellKasse.getQueueLength() > k.getQueueLength())
					{
						anstellKasse = k;
					}
				}
				
				// erhöhe Warteschlange
				anstellKasse.incrementQueue();

				semaphore.release();
				
				// Gehe in die Mensa und weiter zu den Kassen, um anschließend zu zahlen
				anstellKasse.enter();
				
				semaphore.acquire();
				
				// fertig mit bezahlen, verringere Schlange
				anstellKasse.decrementQueue();
				
				// verlasse kritischen Abschnitt
				semaphore.release();
				
				// Warten
				essen();
			}
		} catch (InterruptedException e)
		{
			System.err.println(this.getName() + " was interrupted!");
		} finally
		{
			semaphore.release();
		}
	}
	
	/* Wartezeit, bis fertig gegessen */
	private void essen() throws InterruptedException
	{
		int sleepTime = (int) (100 * Math.random());
		Thread.sleep(sleepTime);
	}
}
