package nextTry;

/*
 * nextTry ist die aktuelle Version, mit einer Warteschlange. 
 */

/* Eröffnet die Mensa */
public class Start
{
	private static final int DEFAULT_ANZAHL_KASSEN = 2; // 4
	private static final int DEFAULT_ANZAHL_STUDENTEN = 10;
	private static final int DEFAULT_DAUER_MILLIS = 1000;

	public static void main(String[] args)
	{
			new Mensa(DEFAULT_ANZAHL_KASSEN, DEFAULT_ANZAHL_STUDENTEN, DEFAULT_DAUER_MILLIS);
	}
}