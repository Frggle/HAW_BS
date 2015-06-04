package nextTry;

import java.util.concurrent.Semaphore;

public class Kasse 
{
	private String name;
	private Semaphore semaphore;
	
	public Kasse(String _name)
	{
		name = _name;
		semaphore = new Semaphore(1, true);
	}
	
	public void enter() throws InterruptedException
	{
//		warten();
//		semaphore.acquire();
		if(semaphore.tryAcquire())
		{
			try
			{
				bezahlen();
				System.err.println("             " + Thread.currentThread().getName() + " hat bezahlt an " + this.getName());
			} finally
			{
				semaphore.release();
			}	
		}
		else
		{
			System.err.println(this.getName() + " ist nicht leer. " + Thread.currentThread().getName() + " stellt sich an");
		}
	}
	
	private void bezahlen() throws InterruptedException
	{
		int sleepTime = (int) (50 * Math.random());
		Thread.sleep(sleepTime);
	}
	
	private void warten() throws InterruptedException
	{
		int sleepTime = (int) (50 * Math.random());
		Thread.sleep(sleepTime);
	}

	
	public synchronized int getQueueLength()
	{
		return semaphore.getQueueLength();
	}
	
	public String getName()
	{
		return name;
	}
}
