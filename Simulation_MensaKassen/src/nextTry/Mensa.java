package nextTry;

import java.util.ArrayList;
import java.util.List;

/* Pendant zum ShopServer */
public class Mensa
{
	/* Variablen */
	private int kassenAnzahl;
	private int studentenAnzahl;
	private int laufzeit;
	
	private List<Kasse> kassenListe;
	private List<Student> studentenListe;
	
	/* Konstrutor */
	public Mensa(int _kassen, int _studenten, int _dauer)
	{
		kassenAnzahl = _kassen;
		studentenAnzahl = _studenten;
		laufzeit = _dauer;
		
		kassenListe = new ArrayList<Kasse>();
		studentenListe = new ArrayList<Student>();
		
		openDoors();
	}
	
	/* öffnet die Mensatüren */
	private void openDoors()
	{
		System.err.println("---------- START ---------");
		for(int i = 1; i <= kassenAnzahl; i++)
		{
			kassenListe.add(new Kasse("Kasse" + i));
		}
		// Starte Studenten
		for(int i = 1; i <= studentenAnzahl; i++)
		{
			Student s = new Student("Student" + i, kassenListe);
			studentenListe.add(s);
			s.start();
			warten();
		}
		
		// Laufzeit abwarten
		try
		{
			Thread.sleep(laufzeit);
		} catch (InterruptedException e)
		{
			//
		}
		
		// Interrupt Studenten
		for(Student s : studentenListe)
		{
			s.interrupt();
		}
				
		System.err.println("---------- ENDE ---------");
	}
	
	private void warten()
	{
		int sleepTime = (int) (10 * Math.random());
		try
		{
			Thread.sleep(sleepTime);
		} catch (InterruptedException e)
		{
			//
		}
	}
}
