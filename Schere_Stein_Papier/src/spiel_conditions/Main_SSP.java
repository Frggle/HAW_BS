package spiel_conditions;

import java.text.NumberFormat;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import spiel_synchronized_wait_notify.Schiedsrichter;
import spiel_synchronized_wait_notify.Spieler;
import spiel_synchronized_wait_notify.Spieltisch_synchronized;

public class Main_SSP extends Thread
{
	/* Variablen */
	private Spieler spieler1;
	private Spieler spieler2;
	private Schiedsrichter richter;
	private Spieltisch_synchronized tisch;
	private Lock myLock;
	
	/* Konstante */
	private final int SPIELZEITinMS = 500; 
	private final int ANZAHL_SPIELER = 2;

	/* Konstruktor */
	public Main_SSP()
	{
		tisch = new Spieltisch_synchronized(ANZAHL_SPIELER);
		spieler1 = new Spieler("Spieler 1", tisch);
		spieler2 = new Spieler("Spieler 2", tisch);
		richter = new Schiedsrichter(tisch, spieler1, spieler2);
		myLock = new ReentrantLock();
	}

	/* main Methode */
	public static void main(String[] args)
	{
		new Main_SSP().start();
	}
	
	/* wird von .start() aufgerufen */
	public void run()
	{
		System.err.println("---------- Das Spiel wurde gestartet ----------");
		
		// Spieler-Threads starten
		spieler1.start();
		spieler2.start();
		// Schiedsrichter-Thread starten
		richter.start();
		// Spielzeit abwarten
		try
		{
			Thread.sleep(SPIELZEITinMS);
		} catch (InterruptedException e)
		{
			//
		}
		// Threads interrupten
		spieler1.interrupt();
		spieler2.interrupt();
		richter.interrupt();
		
		// Ergebnisausgabe
		System.err.println("---------- Das Spiel wurde beendet " + richter.gibRundenanzahl() + " Runden" + " ----------");
		System.err.println(spieler1.getName() + " hat " + richter.gewinneSpieler1() + "x gewonnen => " + prozentual1());
		System.err.println(spieler2.getName() + " hat " + richter.gewinneSpieler2() + "x gewonnen => " + prozentual2());
		System.err.println("Remis: " + richter.gibRemis());
	}
	
	private String prozentual1()
	{
		NumberFormat percent = NumberFormat.getPercentInstance();
		percent.setMaximumFractionDigits(2);
		return percent.format((float)richter.gewinneSpieler1() / richter.gibRundenanzahl());
	}
	
	private String prozentual2()
	{
		NumberFormat percent = NumberFormat.getPercentInstance();
		percent.setMaximumFractionDigits(2);
		return percent.format((float)richter.gewinneSpieler2() / richter.gibRundenanzahl());
	}
}
