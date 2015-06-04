package ohneVorlage;

public class Spieler extends Thread
{
	/* Variablen */
	private I_Spieltisch tisch;

	
	/* Konstruktor */
	public Spieler(String _name, I_Spieltisch _tisch)
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
//				synchronized (tisch)
//				{
					tisch.spieleHand(this, waehleRandomHand());	
//				}
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