package mitVorlage;

import mitVorlage.Hand;

public class Player extends Thread
{
	private Hand item;
	@SuppressWarnings("rawtypes")
	private BoundedBuffer currentBuffer;

	public <E> Player(String name, BoundedBuffer<E> buffer)
	{
		super(name);
		currentBuffer = buffer;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void run()
	{
		try
		{
			while (!Thread.interrupted())
			{
				// Wählt eine Hand
				item = waehleRandomHand();
				
				// Buffer-Zugriffsmethode aufrufen --> Synchronisation über den Puffer!
				currentBuffer.enter(item);
				
				// Für unbestimmte Zeit anhalten
//				Thread.sleep((int) (100 * Math.random()));
			}
		} catch (InterruptedException e)
		{
			System.err.println(Thread.currentThread().getName() + " wurde erfolgreich interrupted!");
		}
	}

	/* Über ein ENUM wird eine zufällige Hand gewählt */
	public Hand waehleRandomHand()
	{
		Hand[] temp = Hand.values();
		return temp[(int) (Math.random() * temp.length)];
	}

	@SuppressWarnings("rawtypes")
	public BoundedBuffer getBuffer()
	{
		return currentBuffer;
	}
}
