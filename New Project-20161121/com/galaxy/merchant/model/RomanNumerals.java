package com.galaxy.merchant.model;

public class RomanNumerals implements Comparable<RomanNumerals> {
	private final String mSymbol;
	private final long mValue;

	public RomanNumerals(String symbol, long value) {
		mSymbol = symbol;
		mValue = value;
	}

	public String getSymbol() {
		return mSymbol;
	}

	public long getValue() {
		return mValue;
	}

	public int compareTo(RomanNumerals romanNumeral) {
		return Long.valueOf(mValue).compareTo(
				Long.valueOf(romanNumeral.getValue()));
	}

	public String toString() {
		return "{ " + mSymbol + " : " + mValue + " } ";
	}
}
