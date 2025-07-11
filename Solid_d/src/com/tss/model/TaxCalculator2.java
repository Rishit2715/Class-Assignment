package com.tss.model;

public class TaxCalculator2 {
	private ILogger logger;

	public TaxCalculator2(ILogger logger) {
		this.logger = logger;
	}

	public ILogger getLogger() {
		return logger;
	}

	public void setLogger(ILogger logger) {
		this.logger = logger;
	}

	public int calculateTax(int amount, int rate) {
		int tax = 0;
		try {
			tax = amount / rate;
		} catch (Exception e) {
			logger.log("Divide by zero");
		}
		return tax;
	}
}