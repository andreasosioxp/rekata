
public class ExactCharRE extends RE {
	char exactChar;
	
	public ExactCharRE(char exactChar) {
		this.exactChar = exactChar;
	}

	@Override
	public boolean matches(String string) {
		return string.equals(""+exactChar);
	}
	
	@Override
	public String toString() 
	{
		return ""+exactChar;
	}
}
