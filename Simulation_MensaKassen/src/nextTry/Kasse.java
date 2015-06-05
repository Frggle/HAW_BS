package nextTry;

import java.util.concurrent.Semaphore;

public class Kasse
{
	/* Variablen */
	private String name;
	private Semaphore semaphore;
	private int waitingQueue;

	/* Konstruktor */
	public Kasse(String _name)
	{
		name = _name;
		semaphore = new Semaphore(1, true); // einer zurzeit in kritischen
											// Abschnitt, faire Verteilung
											// (Warteschlange)
		waitingQueue = 0;
	}

	/*
	 * Verbraucher ruft diese Methode auf Er betritt die Mensa -> geht
	 * anschließend zu den Kassen -> stellt sich an oder wenn leer bezahlt
	 * direkt
	 */
	public void enter() throws InterruptedException
	{
//		warten();
		if (!semaphore.tryAcquire())
		{
			warten();
			System.err.println(Thread.currentThread().getName() + " stellt sich bei " + this.getName() + " an ++");
			semaphore.acquire();
		}
		try
		{
			bezahlen();
		} finally
		{
			semaphore.release();
		}
	}

	public void incrementQueue()
	{
		waitingQueue++;
	}

	public void decrementQueue()
	{
		waitingQueue--;
	}

	/* Verbraucher bezahlt */
	private void bezahlen() throws InterruptedException
	{
		int sleepTime = (int) (50 * Math.random());
		// Thread.sleep(sleepTime);
		System.err.println("                " + Thread.currentThread().getName() + " hat bezahlt an " + this.getName()
				+ " --");
		Thread.sleep(sleepTime);
	}

	private void warten() throws InterruptedException
	{
		Thread.sleep(50);
	}

	/* Gibt die aktuelle Länge der Warteschlange */
	public int getQueueLength()
	{
		return waitingQueue;
	}

	/* Gibt den Kassennamen */
	public String getName()
	{
		return name;
	}
}
