package Mensakassen;

import java.util.ArrayList;
/*
 * Kasse.java
 * Version 1.0
 Autor: Kaepke
 Zweck: ...
 */

public class Kasse
{
	private String kassenNummer;
	private ArrayList<?> warteschlange;
	
	public Kasse(String nummer)
	{
		kassenNummer = nummer;
		warteschlange = new ArrayList<>();
	}
	
	/* Student ruft die Methode HOLE_ESSEN auf */
	public void holeEssen() throws InterruptedException
	{
		int sleepTime = (int) (Math.random() * 1000);
		Thread.sleep(sleepTime);
	}
	
	public String gibName()
	{
		return kassenNummer;
	}
}
