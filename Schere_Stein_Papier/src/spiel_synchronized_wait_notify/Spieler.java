package spiel_synchronized_wait_notify;

public class Spieler extends Thread
{
	/* Variablen */
	private Spieltisch tisch;

	
	/* Konstruktor */
	public Spieler(String _name, Spieltisch _tisch)
	{
		super(_name);
		tisch = _tisch;
	}

	/* wird von start() Methode aufgerufen */
	public void run()
	{
		try
		{
			// Spiele solange eine zufällige Hand, bis Spiel unterbrochen wird
			while (!Thread.interrupted())
			{
				tisch.spieleHand(this, waehleRandomHand());
			}
		} catch (InterruptedException e)
		{
			//
		}
	}
	
	/* Über ein ENUM wird eine zufällige Hand gewählt */
	private Hand waehleRandomHand()
	{
		Hand[] temp = Hand.values();
		return temp[(int) (Math.random() * temp.length)];
	}
}