package it.unibo.oop.lab.exception2;

public class WrongAccountHolderException extends IllegalArgumentException {
	
	private static final long serialVersionUID = 1L;
	private final int usrID;
	
	/**
	 * @param usrID user ID
	 */
	public WrongAccountHolderException(final int usrID) {
		super();
		this.usrID = usrID;
	}
	
	@Override
	public String toString() {
		return "Can not execute operation, wrong user id. Given id: " + this.usrID + ".";
	}
	
	@Override
	public String getMessage() {
		return this.toString();
	}
}
