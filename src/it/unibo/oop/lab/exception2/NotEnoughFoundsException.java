package it.unibo.oop.lab.exception2;

public class NotEnoughFoundsException extends IllegalStateException {
	
	private static final long serialVersionUID = 1L;
	private final double balance;
	private final double amount;
	
	/**
	 * @param balance current account balance
	 * @param amount operation amount
	 */
	public NotEnoughFoundsException(final double balance, final double amount) {
		super();
		this.amount = amount;
		this.balance = balance;
	}
	
	@Override
	public String toString() {
		return "Can not execute operation, not enough founds. Current balance: " + this.balance
				+ " operation amount: " + this.amount + ".";
	}
	
	@Override
	public String getMessage() {
		return this.toString();
	}
}
