package mitVorlage;

public class Spieltisch extends Thread
{
	private Player spieler1;
	private Player spieler2;
	@SuppressWarnings("rawtypes")
	private BoundedBuffer buffer;
	private int gewonneneSpiele1;
	private int gewonneneSpiele2;
	private int remis;

	@SuppressWarnings(
	{ "rawtypes" })
	public Spieltisch(BoundedBuffer _buffer, Player _spieler1, Player _spieler2)
	{
		buffer = _buffer;
		spieler1 = _spieler1;
		spieler2 = _spieler2;
		gewonneneSpiele1 = 0;
		gewonneneSpiele2 = 0;
		remis = 0;
	}

	public void run()
	{
		try
		{
			while (!Thread.interrupted())
			{
				Hand hand1 = Hand.STEIN;
				Hand hand2 = Hand.STEIN;
				try
				{
					hand1 = (Hand) spieler1.getBuffer().remove();
					hand2 = (Hand) spieler2.getBuffer().remove();
				} catch (InterruptedException e)
				{
					//
				}
				if(hand1.schlaegt(hand2))
				{
					gewonneneSpiele1++;
					System.err.println(spieler1.getName() + "(" + hand1 + ") vs. " + spieler2.getName() + "(" + hand2 + ")");
				}
				else if(hand2.schlaegt(hand1))
				{
					gewonneneSpiele2++;
					System.err.println(spieler2.getName() + "(" + hand2 + ") vs. " + spieler1.getName() + "(" + hand1 + ")");
				}
				else
				{
					remis++;
					System.err.println("REMIS");
				}
			}
		} finally
		{
			//
		}
	}
	
	public int gibGewonneneSpiel1()
	{
		return gewonneneSpiele1;
	}
	
	public int gibGewonneneSpiel2()
	{
		return gewonneneSpiele2;
	}
	
	public int gibRemis()
	{
		return remis;
	}
}
