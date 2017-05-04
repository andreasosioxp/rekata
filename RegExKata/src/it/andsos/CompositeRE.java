package it.andsos;
import java.util.List;

public class CompositeRE extends RE {
	List<RE> regexps;
	
	public CompositeRE(List<RE> regexps) {
		this.regexps = regexps;
	}
	
	public boolean matches(String s) {
		if(regexps.size()==0)
			return s.length()==0;
		for(int indexTo = 0; indexTo<=s.length(); indexTo++) 
			if(regexps.get(0).matches(s.substring(0, indexTo)) &&
					new CompositeRE(regexps.subList(1,regexps.size())).matches(s.substring(indexTo)))
						return true;
		
		return false;			
	}
	
	public String toString() {
		String result = "";
		for(RE regExp: regexps)
			result += regExp;
		return result;
	}
}
