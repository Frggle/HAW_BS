import java.util.Random;

public class Accident extends Thread
{
	int randomTimeInMiliSec;
	Thread _simrace;
	
	public Accident(int numOfRounds, Thread simRace)
	{
		_simrace = simRace;
		Random random = new Random();
		int max = numOfRounds * 99;
		int min = 99; // eine Runde mit maximaler Länge/Rundenzeit				
		randomTimeInMiliSec = random.nextInt((max - min) +1) + min;
	}
	
	public void run()
	{
		try
		{
			Thread.sleep(randomTimeInMiliSec);
			_simrace.interrupt();
		} catch (InterruptedException e)
		{
			System.err.println("Kein Unfall!");
		}
	}
}
