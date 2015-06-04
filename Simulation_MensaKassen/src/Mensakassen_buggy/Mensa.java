package Mensakassen_buggy;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Mensa
{
	private List<Kasse> alleKassen;
	private List<Student> alleStudenten;
	private int mensaDauerMilisec;
	
	public Mensa(int kassen, int studenten, int dauerMillis)
	{
		alleKassen = new LinkedList<>();
		for (int i = 1; i <= kassen; i++)
		{
			alleKassen.add(new Kasse("Kasse" + (i)));
		}
		alleStudenten = new LinkedList<>();
		for (int i = 1; i <= studenten; i++)
		{
			alleStudenten.add(new Student("Student" + (i), this));
		}
		mensaDauerMilisec = dauerMillis;
		
		startSimulation();
	}

	public List<Kasse> getKassen()
	{
		return Collections.unmodifiableList(alleKassen);
	}

	public void startSimulation()
	{
		System.err.println("----------Mensa hat eröffnet--------------------");
		for (Student student : alleStudenten)
		{
			student.start();
		}

		try
		{
			Thread.sleep(mensaDauerMilisec);
		} catch (InterruptedException e)
		{
			//
		}
		for (Student student : alleStudenten)
		{
			while(!student.isInterrupted())
			{
				student.interrupt();	
			}	
		}
		System.err.println("----------Mensa hat geschlossen--------------------");
	}
}