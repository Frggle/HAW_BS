package Mensakassen;

import java.util.List;

/*
 * Student.java
 * Version 1.0
 * Autor: Kaepke
 * Zweck: Simuliert das Verhalten eines Studenten in der Mensa
 */
public class Student extends Thread
{
	private String student_nummer;
	private List<Kasse> kassenListe;
	
	public Student(String nummer, List<Kasse> _kassenListe)
	{
		student_nummer = nummer;
		kassenListe = _kassenListe;
	}
	
	public String gibName()
	{
		return student_nummer;
	}
}
