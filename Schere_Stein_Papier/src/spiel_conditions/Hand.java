package spiel_conditions;

public enum Hand
{
	SCHERE, STEIN, PAPIER;
	
	public boolean schlaegt(Hand andereHand)
	{
		if(this == SCHERE && andereHand == PAPIER)
		{
			// Schere schneidet Papier
			return true;	
		}
		else if(this == STEIN && andereHand == SCHERE)
		{
			// Stein zerstört Schere
			return true;
		}
		else if(this == PAPIER && andereHand == STEIN)
		{
			// Papier umwickelt Stein
			return true;
		}
		else
		{
			// alle anderen Kombinationen verlieren
			return false;
		}
	}
}

