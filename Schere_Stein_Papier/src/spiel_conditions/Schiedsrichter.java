package spiel_conditions;

import spiel_synchronized_wait_notify.Hand;
import spiel_synchronized_wait_notify.Spieler;
import spiel_synchronized_wait_notify.Spieltisch_synchronized;

public class Schiedsrichter extends Thread
{
	/* Variablen */
	private Spieltisch_synchronized tisch;
	private Spieler spieler1;
	private Spieler spieler2;
	private int gewonneneRundenSpieler1;
	private int gewonneneRundenSpieler2;
	private int remis;
	private int rundenZaehler;

	/* Konstruktor */
	public Schiedsrichter(Spieltisch_synchronized _tisch, Spieler _spieler1, Spieler _spieler2)
	{
		super("Schiedsrichter");
		tisch = _tisch;
		spieler1 = _spieler1;
		spieler2 = _spieler2;
		
		gewonneneRundenSpieler1 = 0;
		gewonneneRundenSpieler2 = 0;
		remis = 0;
		rundenZaehler = 0;
	}

	/* wird von start() Methode aufgerufen */
	public void run()
	{
		try
		{
			while (!Thread.interrupted())
			{
				Hand hand1 = tisch.gibGespielteHand(spieler1);
				Hand hand2 = tisch.gibGespielteHand(spieler2);
				if(hand1.schlaegt(hand2))
				{
					gewonneneRundenSpieler1++;
					System.err.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> " + spieler1.getName() + "(" + hand1.name() + ")" + " vs. " + spieler2.getName() + "(" + hand2.name() + ")");
				}
				else if(hand2.schlaegt(hand1))
				{
					gewonneneRundenSpieler2++;
					System.err.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> " + spieler2.getName() + "(" + hand2.name() + ")" + " vs. " + spieler1.getName() + "(" + hand1.name() + ")");
				}
				else
				{
					remis++;
				}
				rundenZaehler++;
				tisch.leereSpieltisch();
			}
		} catch (InterruptedException e)
		{
			// 
		}
	}
	
	public int gewinneSpieler1()
	{
		return gewonneneRundenSpieler1;
	}
	
	public int gewinneSpieler2()
	{
		return gewonneneRundenSpieler2;
	}
	
	public int gibRundenanzahl()
	{
		return rundenZaehler;
	}
	
	public int gibRemis()
	{
		return remis;
	}
}
