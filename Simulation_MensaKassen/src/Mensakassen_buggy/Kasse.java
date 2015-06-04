package Mensakassen_buggy;

import java.util.concurrent.Semaphore;

public class Kasse
{
	private String myName;
	private Semaphore myLock;

	public Kasse(String name)
	{
		myName = name;
		myLock = new Semaphore(1, true);
	}

	public String getName()
	{
		return myName;
	}

	public void anstellen() throws InterruptedException
	{
		System.err.println(Thread.currentThread().getName() + " hat sich an " + myName + " angestellt!");
		myLock.acquire();		
//		System.err.println(Thread.currentThread().getName() + " darf an " + myName + " bezahlen!");
	}

	public int laengeDerSchlange()// throws InterruptedException
	{
		return myLock.getQueueLength();
	}

	public void bezahlen()// throws InterruptedException
	{
		System.err.println(Thread.currentThread().getName() + " hat an " + myName + " bezahlt!");
		myLock.release();
	}
}