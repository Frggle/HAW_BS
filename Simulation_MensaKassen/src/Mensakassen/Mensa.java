package Mensakassen;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.Semaphore;

/*
 * Mensa.java
 * Version 1.0
 * Autor: Kaepke
 * Zweck: Hält alle Kassen -> entspricht dem Server aus dem Beispiel "Shop"
 */
public class Mensa
{	
	/* Variablen */
	private List<Kasse> kassenListe;
	private List<Student> studentenListe;
	private List<Integer> warteschlangen;
	
	/* EINE Kasse kann nur ein Student zur Zeit bedienen/bezahlen lassen */
	private Semaphore semaphore = new Semaphore(1);
	
	/* Konstruktor */
	public Mensa(List<Kasse> kassen)
	{
		kassenListe = kassen;
	}
	
	public void kassenZuweisung(Student student)
	{	
		try
		{
			// Versuche eine leere Kasse zu bekommen, ansonsten warte
			semaphore.acquire();
					
			// die Kasse mit der kürzesten Warteschlange wird ermittelt und dem Student zugewiesen
			Kasse k = gibOptimaleKasse();
			k.anstellen(student);
		} catch (InterruptedException e)
		{
			//
		} finally
		{
			// Verlässt den kritischen Abschnitt
			semaphore.release();
		}
	}
	
	/* gibt die Kasse zurück, wo die Warteschlange am kürzesten ist */
	private Kasse gibOptimaleKasse()
	{
		Semaphore sema = new Sema
		Kasse optKasse = null;
		warteschlangen = new ArrayList<Integer>();
		int min = Integer.MAX_VALUE;
		for(Kasse kasse : kassenListe)
		{
			if(kasse.)
			{
				optKasse = kasse;
			}
		}
		return optKasse;
	}
}
