package it.andsos;

public class PeriodRE extends RE {
	public static final char PERIOD_RE_SYMBOL = '.';

	@Override
	public boolean matches(String string) {
		return string.length()==1;
	}
	
	@Override
	public String toString() 
	{
		return ""+PERIOD_RE_SYMBOL;
	}
}
