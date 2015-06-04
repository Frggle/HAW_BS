package nextTry;

import java.util.List;
import java.util.concurrent.Semaphore;

public class Student extends Thread
{
	private List<Kasse> kassenListe;
	private Semaphore semaphore;
	
	public Student(String name, List<Kasse> _kassen)
	{
		super(name);
		kassenListe = _kassen;
		semaphore = new Semaphore(1, true);
	}
	
	public void run()
	{
		try
		{
			while(!isInterrupted())
			{
				semaphore.acquire();
				Kasse anstellKasse = null;
				for(Kasse k : kassenListe)
				{
					if(anstellKasse == null || anstellKasse.getQueueLength() > k.getQueueLength())
					{
						anstellKasse = k;
					}
				}
				semaphore.release();
				System.err.println(this.getName() + " stellt sich bei " + anstellKasse.getName() + " an");

				anstellKasse.enter();
				
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
	
	private void essen() throws InterruptedException
	{
		int sleepTime = (int) (100 * Math.random());
		Thread.sleep(sleepTime);
	}
	
	private void warten() throws InterruptedException
	{
		int sleepTime = (int) (75 * Math.random());
		Thread.sleep(sleepTime);
	}

}
