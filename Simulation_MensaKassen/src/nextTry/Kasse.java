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
		semaphore = new Semaphore(1, true); // einer zurzeit in kritischen Abschnitt, faire Verteilung (Warteschlange)
		waitingQueue = 0;
	}
	
	/* Verbraucher ruft diese Methode auf
	 * Er betritt die Mensa -> geht anschließend zu den Kassen -> stellt sich an oder wenn leer bezahlt direkt */
	public void enter() throws InterruptedException
	{
		System.err.println(Thread.currentThread().getName() + " betritt die Mensa");
		System.err.println(Thread.currentThread().getName() + " geht zu den Kassen");

		// Kasse ist leer, kritischer Abschnitt wird blockiert
		if(semaphore.tryAcquire())
		{
			System.err.println("       " + Thread.currentThread().getName() + " ist an der " + this.getName());
			waitingQueue++;
			
			// Verbraucher bezahlt sein Essen
			try
			{
				bezahlen();
				System.err.println("                " + Thread.currentThread().getName() + " hat bezahlt an " + this.getName());
			} finally
			{
				// nach dem Bezahlen wird der kritische Abschnitt wieder freigegeben
				semaphore.release();
			}	
		}
		// Kasse ist nicht leer, Verbraucher stellt sich in die Warteschlange
		else
		{
			System.err.println("Keine Kasse ist leer. " + Thread.currentThread().getName() + " stellt sich bei " + this.getName() + " an");
//			System.err.println(this.getName() + " ist nicht leer. " + Thread.currentThread().getName() + " stellt sich an");
		}
	}
	
	/* Verbraucher bezahlt */
	private void bezahlen() throws InterruptedException
	{
		int sleepTime = (int) (50 * Math.random());
		Thread.sleep(sleepTime);
		waitingQueue--;
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
