package Mensakassen;

import java.util.concurrent.Semaphore;

/*
 * Kasse.java
 * Version 1.0
 * Autor: Kaepke
 * Zweck: ...
 */

public class Kasse extends Thread
{
	/* Konstanten */
	private final int WARTESCHLANGELAENGE = 3;
	
	/* Variablen */
	private int kassenID;
	
	/* Pro Kasse X Studenten warten */
	private Semaphore semaphore = new Semaphore(WARTESCHLANGELAENGE);
	
	/* Konstruktor */
	public Kasse(int id)
	{
		kassenID = id;
	}
	
	/* wird von .start() Methode aufgerufen */
	public void run()
	{
		//
	}
	
	/* Getter für die Anzahl der wartenden Studenten-Threads */
	public int gibWarteschlangenLaenge()
	{
		return semaphore.getQueueLength();
	}
	
	/* Getter für Kassen ID */
	public int gibKassenID()
	{
		return kassenID;
	}
}
