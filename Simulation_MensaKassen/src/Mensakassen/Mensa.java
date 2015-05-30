package Mensakassen;

import java.util.ArrayList;
import java.util.List;
/*
 * Mensa.java
 * Version 1.0
 * Autor: Kaepke
 * Zweck: Hält alle Kassen -> entspricht dem Server aus dem Beispiel "Shop"
 */

public class Mensa
{
	private final int ANZAHL_KASSEN = 3;
	private final int ANZAHL_STUDENTEN = 10;
	private final int DAUER = 5000;
	
	public static void main(String[] args)
	{
		/* Starte Simulation */
		new Mensa().startMensa();
	}
		
	private void startMensa()
	{
		System.err.println("--------------- MENSA ERÖFFNUNG -----------");
		
		/* Erzeuge Kassen */
		List<Kasse> kassenArray = new ArrayList<Kasse>();
		for(int i = 1; i <= ANZAHL_KASSEN; i++)
		{
			kassenArray.add(new Kasse("Kasse " + i));
		}
				
		/* Studenten-Threads erzeugen und starten */
		List<Student> hungrigen = new ArrayList<Student>();
		for(int i = 1; i <= ANZAHL_STUDENTEN; i++)
		{
			Student aktueller = new Student("Student " + i, kassenArray);
			hungrigen.add(aktueller);
			aktueller.start();
		}
		
		/* Warten bis Mensa schließt */
		try
		{
			Thread.sleep(DAUER);
		} catch (InterruptedException ie)
		{
		}
		
		/* Studenten holen kein Essen mehr */
		for(Student s : hungrigen)
		{
			s.interrupt();
		}
		
		System.err.println("--------------- MENSA SCHLIEßUNG -----------");

	}
}
