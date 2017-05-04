package it.andsos;
public class REParser {
	public boolean matches(String regex, String string) {
		return new REFactory().getRegExp(regex).matches(string);
	}
}
