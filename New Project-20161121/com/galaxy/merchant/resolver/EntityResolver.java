package com.galaxy.merchant.resolver;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.galaxy.merchant.calculator.NumericalCalculator;
import com.galaxy.merchant.model.RomanNumerals;

public class EntityResolver extends NumeralResolver {
	final Map<String, Double> mEntityMap;
	private String tokens[];

	public EntityResolver(List<RomanNumerals> numerals,
			Map<String, RomanNumerals> languageMap,
			Map<String, Double> entityMap) {
		super(numerals, languageMap);
		mEntityMap = entityMap;
	}

	@Override
	public void processInput() {
		final List<RomanNumerals> romanNumerals = new ArrayList<RomanNumerals>();
		String entity = null;
		for (int cnt = 0; cnt < tokens.length - 3; cnt++) {
			RomanNumerals romanNumeral = mLanguageMap.get(tokens[cnt]);
			if (romanNumeral == null) {
				entity = tokens[cnt];
			} else {
				romanNumerals.add(romanNumeral);
			}
		}
		final double credits = Double.valueOf(tokens[tokens.length - 2]);
		final double value = new NumericalCalculator().calculateEntity(
				romanNumerals, credits);

		if (value > 0 && entity != null) {
			mEntityMap.put(entity, value);
		} else {
			System.out.println("I have no idea what you are talking about");
		}

	}

	@Override
	public boolean isResolutionApplicable(String input) {
		tokens = input.split(" ");
		return tokens.length > 3 && tokens[tokens.length - 3].equals("is")
				&& tokens[tokens.length - 1].equals("Credits");
	}
}
