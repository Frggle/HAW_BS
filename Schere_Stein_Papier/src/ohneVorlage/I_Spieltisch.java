package ohneVorlage;

/* Interface für Spieltisch mit ConditionQueue und synchronized/wait/notify */
public interface I_Spieltisch
{
	public void spieleHand(Spieler spieler, Hand hand) throws InterruptedException;
	
	public boolean leereSpieltisch() throws InterruptedException;

	public Hand gibGespielteHand(Spieler spieler) throws InterruptedException;
}
