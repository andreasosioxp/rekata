package it.andsos;

public class PlusRE extends RE {
	public final static char PLUS_RE_SYMBOL='+';
	
	@Override
	public boolean matches(String string) {
		return string.length()>0;
	}
	
	@Override
	public String toString() {
		return ""+PLUS_RE_SYMBOL;
	}
}
