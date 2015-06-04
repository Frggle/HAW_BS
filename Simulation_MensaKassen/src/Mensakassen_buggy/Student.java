package Mensakassen_buggy;

import java.util.List;
import java.util.concurrent.Semaphore;

public class Student extends Thread
{
	private Mensa mensa;
	private Semaphore myLock;

	public Student(String name, Mensa _mensa)
	{
		super(name);
		mensa = _mensa;
		myLock = new Semaphore(1, true);
	}
	
	private void randomTime()
	{
		try
		{
			Thread.sleep((int) (Math.random() * 100));
		} catch (InterruptedException e)
		{
			//
		}
	}

	@Override
	public void run()
	{
		while (!Thread.interrupted())
		{
			try
			{
				Kasse anstellkasse = null;
				for (Kasse kasse : mensa.getKassen())
				{
//					System.err.println(kasse.getName() + " " + kasse.laengeDerSchlange());
					if (anstellkasse == null || anstellkasse.laengeDerSchlange() > kasse.laengeDerSchlange())
					{
						anstellkasse = kasse;
					}
				}
				anstellkasse.anstellen();
				
				// Anstehzeit
				randomTime();
				
				anstellkasse.bezahlen();

				// Essen
				randomTime();
			} catch (InterruptedException e)
			{
				//
			} finally
			{
				//
			}
		}
	}
}