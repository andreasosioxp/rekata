
public class StarRE extends RE {
	public static final char STAR_RE_SYMBOL = '*';
	
	@Override
	public boolean matches(String string) {
		return true;
	}
	
	public String toString() 
	{
		return ""+STAR_RE_SYMBOL;
	}

}
