package com.galaxy.merchant.resolver;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.galaxy.merchant.model.RomanNumerals;

public class NumeralResolver implements InputResolver {
	final List<RomanNumerals> mNumerals;
	final Map<String, RomanNumerals> mLanguageMap;
	private String tokens[];

	public NumeralResolver(List<RomanNumerals> numerals,
			Map<String, RomanNumerals> languageMap) {
		mNumerals = numerals;
		mLanguageMap = languageMap;
	}

	public void processInput() {
		List<RomanNumerals> numerals = new ArrayList<RomanNumerals>(mNumerals);
		numerals.removeAll(mLanguageMap.values());
		for (RomanNumerals numeral : numerals) {
			if (tokens[2].equals(numeral.getSymbol())) {
				mLanguageMap.put(tokens[0], numeral);
			}
		}
	}

	public boolean isResolutionApplicable(String input) {
		tokens = input.split(" ");
		return tokens.length == 3 && tokens[1].equals("is")
				&& !mLanguageMap.containsKey(tokens[2]);
	}
}
