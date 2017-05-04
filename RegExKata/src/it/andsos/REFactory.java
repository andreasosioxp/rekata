package it.andsos;
import java.util.ArrayList;
import java.util.List;

public class REFactory {
	private RE getSubRegEgetNextRegExp(String regexpAsString) {
		switch(regexpAsString.charAt(0)) {
			case PeriodRE.PERIOD_RE_SYMBOL:
				return new PeriodRE();
			case StarRE.STAR_RE_SYMBOL:
				return new StarRE();
			case PlusRE.PLUS_RE_SYMBOL:
				return new PlusRE();
			case RE.ESCAPE_SYMBOL:
				return new ExactCharRE(regexpAsString.charAt(1));
			default:
				return new ExactCharRE(regexpAsString.charAt(0));
		}
	}
	
	private String removeNextRegExpSymbol(String regexpAsString) {
		if(regexpAsString.charAt(0)==RE.ESCAPE_SYMBOL)
			return regexpAsString.substring(2);
		else
			return regexpAsString.substring(1);
	}

	public RE getRegExp(String regExpAsString) {
		List<RE> allREs = new ArrayList<RE>();
		
		while(regExpAsString.length()>0) { 
			allREs.add(getSubRegEgetNextRegExp(regExpAsString));
			regExpAsString = removeNextRegExpSymbol(regExpAsString);
		}
		
		return new CompositeRE(allREs);
	}
}
