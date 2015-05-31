package Mensakassen;

/*
 * Student.java
 * Version 1.0
 * Autor: Kaepke
 * Zweck: Simuliert das Verhalten eines Studenten in der Mensa
 */
public class Student extends Thread
{
	/* Variablen */
	private int studentID;
	private Mensa mensa;
		
	/* Konstruktor */
	public Student(int id, Mensa _mensa)
	{
		studentID = id;
		mensa = _mensa;
	}
	
	/* wird von der .start() Methode aufgerufen */
	public void run()
	{
		try
		{
			/* gehe immer wieder essen, bis Mensa geschlossen */
			while(!isInterrupted())
			{
				System.err.println("Student " + studentID + " hat sich angestellt");
				
				/* Student betritt die Mensa */
				mensa.enter(this);
				
				/* Student isst und verdaut */
				essenUndVerdauen();
				
				/* Student wartet bis er erneut in die Mensa geht */
				warten();			
				}
		} catch (InterruptedException e)
		{
			System.err.println("Student " + studentID + " wurde unterbrochen!");
		}
	}
	
	/* Student benutzt diese Methode, um das Essen zu verdauen */
	private void essenUndVerdauen() throws InterruptedException
	{
		int verdauungsdauer = (int) (1000* Math.random());
		Thread.sleep(verdauungsdauer);
	}
	
	/* Student wartet, bis er erneut in die Mensa geht */
	private void warten() throws InterruptedException
	{
		int warteZeit = (int) (1000 * Math.random());
		Thread.sleep(warteZeit);
	}
	
	/* Gett für StudentenID */
	public int gibStudentID()
	{
		return studentID;
	}
	
}
