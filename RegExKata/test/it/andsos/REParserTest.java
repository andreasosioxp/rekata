package it.andsos;
import static org.junit.Assert.*;

import org.junit.Test;

import it.andsos.PeriodRE;
import it.andsos.PlusRE;
import it.andsos.RE;
import it.andsos.REParser;
import it.andsos.StarRE;

public class REParserTest {
	REParser parser = new REParser();
	
	String[] someExactStrings = {
			"a",
			"ab",
			"abc",
			"bc",
			"c",
			"def",
			"fed",
			""
	};
	
	@Test
	public void testEmptyPatternMatchesEmptyString() {
		assertTrue(parser.matches("", ""));
	}
	
	@Test
	public void testEmptyPatternDoesntMatchNonEmptyString() {
		assertFalse(parser.matches("", "a"));
	}

	@Test
	public void testExactPatternMatchesIdenticalString() {
		for(int i=0; i<someExactStrings.length; i++)
			for(int j=0; j<someExactStrings.length; j++)
				assertEquals(i==j, parser.matches(someExactStrings[i], someExactStrings[j]));
	}
	
	String replaceCharAt(int index, char c, String s) {
		return s.substring(0, index)+c+s.substring(index+1);
	}

	@Test
	public void testSinglePeriodMatchesOneCharacter() {
		assertWildCharCanBeReplacedEverywhere(PeriodRE.PERIOD_RE_SYMBOL);
	}

	@Test
	public void testSinglePlusMatchesOneCharacter() {
		assertWildCharCanBeReplacedEverywhere(PlusRE.PLUS_RE_SYMBOL);
	}

	@Test
	public void testSingleStarMatchesOneCharacter() {
		assertWildCharCanBeReplacedEverywhere(StarRE.STAR_RE_SYMBOL);
	}

	private void assertWildCharCanBeReplacedEverywhere(char wildChar) {
		for(int i=0; i<someExactStrings.length; i++)
			for(int j=0; j<someExactStrings[i].length(); j++) {
				String regEx = replaceCharAt(j, wildChar, someExactStrings[i]);
				assertTrue(parser.matches(regEx, someExactStrings[i]));
			}
	}

	@Test
	public void testEachPeriodMatchesOneCharacter() {
		assertTrue(parser.matches("a"+PeriodRE.PERIOD_RE_SYMBOL+"c"+PeriodRE.PERIOD_RE_SYMBOL+"e", "abcde"));
	}

	@Test
	public void testPeriodsDontMatchSequences() {
		assertFalse(parser.matches("a"+PeriodRE.PERIOD_RE_SYMBOL, "abc"));
	}

	@Test
	public void testStarMatchesEmptyString() {
		assertTrue(parser.matches(""+StarRE.STAR_RE_SYMBOL, ""));
	}

	@Test
	public void testStarMatchesEmptySubstring() {
		assertTrue(parser.matches("a"+StarRE.STAR_RE_SYMBOL, "a"));
	}

	@Test
	public void testEscapedPeriodMatchesPeriod() {
		assertTrue(parser.matches(""+RE.ESCAPE_SYMBOL+PeriodRE.PERIOD_RE_SYMBOL, ""+PeriodRE.PERIOD_RE_SYMBOL));
	}

	@Test
	public void testEscapedStarMatchesStar() {
		assertTrue(parser.matches(""+RE.ESCAPE_SYMBOL+StarRE.STAR_RE_SYMBOL, StarRE.STAR_RE_SYMBOL+""));
	}

	@Test
	public void testPlusMatchesNonEmptyString() {
		assertTrue(parser.matches(""+PlusRE.PLUS_RE_SYMBOL, "a"));
		assertTrue(parser.matches(""+PlusRE.PLUS_RE_SYMBOL, "ab"));
		assertFalse(parser.matches(""+PlusRE.PLUS_RE_SYMBOL, ""));
	}

	@Test
	public void testWildCharsCanBeCombined() {
		assertTrue(parser.matches("a"+StarRE.STAR_RE_SYMBOL+"b"+PlusRE.PLUS_RE_SYMBOL+"c", "abdc"));
		assertTrue(parser.matches("a"+StarRE.STAR_RE_SYMBOL+
				StarRE.STAR_RE_SYMBOL+
				StarRE.STAR_RE_SYMBOL+
				"b"+
				PeriodRE.PERIOD_RE_SYMBOL+
				PeriodRE.PERIOD_RE_SYMBOL+"c"+StarRE.STAR_RE_SYMBOL, "abdec"));
		assertTrue(parser.matches(""+PeriodRE.PERIOD_RE_SYMBOL+PeriodRE.PERIOD_RE_SYMBOL+PeriodRE.PERIOD_RE_SYMBOL, "abc"));
	}
}
