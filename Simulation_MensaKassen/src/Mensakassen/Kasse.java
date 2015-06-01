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
	/* Variablen */
	private int kassenID;
	
	/* Pro Kasse genau ein Student warten */
	private Semaphore semaphore = new Semaphore(1);
	
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
	
	public void anstellen(Student student)
	{
		//
	}
	
	/* Getter für Kassen ID */
	public int gibKassenID()
	{
		return kassenID;
	}
}
