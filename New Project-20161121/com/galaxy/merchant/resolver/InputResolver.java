package com.galaxy.merchant.resolver;

public interface InputResolver {
	public boolean isResolutionApplicable(String input);
	public void processInput();
}
