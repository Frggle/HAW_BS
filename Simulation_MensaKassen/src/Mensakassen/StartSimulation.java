package Mensakassen;

import java.util.ArrayList;
import java.util.List;

import Mensakassen.Student;

public class StartSimulation
{
	/* Konstanten */
	private static final int ANZAHL_KASSEN = 3;
	private static final int ANZAHL_STUDENTEN = 10;
	private static final int DAUER = 5000;
	
	/* Variablen */
	private static List<Kasse> kassenListe;
	private static List<Student> studentenListe;
	private static Mensa mensa;
	
	/* main */
	public static void main(String[] args)
	{
		initKasse();
		initStudenten();
		mensa = new Mensa(kassenListe);
		
		System.err.println("-------- START --------");
		
		// Laufzeit abwarten
		try
		{
			Thread.sleep(DAUER);
		} catch (InterruptedException e)
		{
			//
		}
		
		// Kassen-Threads stoppen
		for(Kasse k : kassenListe)
		{
			k.interrupt();
		}
		
		// Studenten-Threads stoppen
		for(Student s : studentenListe)
		{
			s.interrupt();
		}
		
		System.err.println("-------- ENDE --------");
	}
	
	/* init. Kassen und starten */
	private static void initKasse()
	{
		kassenListe = new ArrayList<Kasse>();
		for(int i = 1; i <= ANZAHL_KASSEN; i++)
		{
			Kasse kasse = new Kasse(i);
			kassenListe.add(kasse);
			kasse.start();
		}
	}
	
	/* init. Studenten und starten */
	private static void initStudenten()
	{
		studentenListe = new ArrayList<Student>();
		for(int i = 1; i <= ANZAHL_STUDENTEN; i++)
		{
			Student student = new Student(i, mensa);
			studentenListe.add(student);
			student.start();
		}
	}
}
