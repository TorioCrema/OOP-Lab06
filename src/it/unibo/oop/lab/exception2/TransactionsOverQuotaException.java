package it.unibo.oop.lab.exception2;

public class TransactionsOverQuotaException extends IllegalStateException {
	
	private static final long serialVersionUID = 1L;
	private final int maximumAllowedATMTransactions;
	
	public TransactionsOverQuotaException(final int maximumAllowedATMTransactions) {
		super();
		this.maximumAllowedATMTransactions = maximumAllowedATMTransactions;
	}
	
	@Override
	public String toString() {
		return "Can not execute operations, maximum number of allowed ATM transactions reached:"
				+ this.maximumAllowedATMTransactions + ".";
	}
	
	@Override
	public String getMessage() {
		return this.toString();
	}
}
