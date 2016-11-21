package com.galaxy.merchant.application;

import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.galaxy.merchant.model.RomanNumerals;
import com.galaxy.merchant.resolver.EntityResolver;
import com.galaxy.merchant.resolver.InputResolver;
import com.galaxy.merchant.resolver.NumeralResolver;
import com.galaxy.merchant.resolver.QueryResolver;

public class NumericalManager {
	private final List<RomanNumerals> numerals = new ArrayList<RomanNumerals>();
	private final Map<String, RomanNumerals> mLanguageMap = new HashMap<String, RomanNumerals>();
	private final Map<String, Double> mEntityMap = new HashMap<String, Double>();
	private List<InputResolver> inputResolvers = new ArrayList<InputResolver>();

	NumericalManager(BufferedWriter bufferedWriter) {
		numerals.add(new RomanNumerals("I", 1));
		numerals.add(new RomanNumerals("V", 5));
		numerals.add(new RomanNumerals("X", 10));
		numerals.add(new RomanNumerals("L", 50));
		numerals.add(new RomanNumerals("C", 100));
		numerals.add(new RomanNumerals("D", 500));
		numerals.add(new RomanNumerals("M", 1000));
		inputResolvers.add(new NumeralResolver(numerals, mLanguageMap));
		inputResolvers.add(new EntityResolver(numerals, mLanguageMap,
				mEntityMap));
		inputResolvers
				.add(new QueryResolver(numerals, mLanguageMap, mEntityMap, bufferedWriter));
	}

	public void processInput(String input) {
		for (InputResolver inputResolver : inputResolvers) {
			if (inputResolver.isResolutionApplicable(input)) {
				inputResolver.processInput();
				break;
			}
		}
	}
}
