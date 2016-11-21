package com.galaxy.merchant.calculator;

import java.util.List;

import com.galaxy.merchant.model.RomanNumerals;

public class NumericalCalculator {
	public double calculateCost(List<RomanNumerals> romanNumerals) {
		RomanNumerals lastNumerals = null;
		long cost = 0L;
		boolean isDoubleRepeat = false;
		for (RomanNumerals currentNumerals : romanNumerals) {
			if (lastNumerals == null) {
				lastNumerals = currentNumerals;
			} else {
				if (currentNumerals.getValue() > lastNumerals.getValue()) {
					isDoubleRepeat = false;
					cost = cost + currentNumerals.getValue()
							- lastNumerals.getValue();
					lastNumerals = null;
				} else {
					if (currentNumerals.getValue() == lastNumerals.getValue()) {
						if (isDoubleRepeat) {
							return -1.0d;
						}

						if (!isDoubleRepeat) {
							isDoubleRepeat = true;
						}
					} else {
						isDoubleRepeat = false;
					}

					cost = cost + lastNumerals.getValue();
					lastNumerals = currentNumerals;
				}
			}
		}

		if (lastNumerals != null) {
			cost = cost + lastNumerals.getValue();
		}
		return Double.valueOf(cost);
	}

	public double calculateCost(List<RomanNumerals> romanNumerals, double entityCost) {
		double cost = calculateCost(romanNumerals);
		if (cost == -1) {
			return -1;
		} else {
			return cost * entityCost;
		}
	}

	public double calculateEntity(List<RomanNumerals> romanNumerals, double credits) {
		double cost = calculateCost(romanNumerals);
		if (cost == -1) {
			return -1;
		} else {
			return credits / cost;
		}
	}
}

