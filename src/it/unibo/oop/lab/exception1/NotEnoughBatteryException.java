package it.unibo.oop.lab.exception1;

public class NotEnoughBatteryException extends IllegalStateException {

	private static final long serialVersionUID = 5322262957171100503L;
	
	private final double currentBatteryLevel;
	private final double batteryConsuption;
	
	public NotEnoughBatteryException(final double currentBatteryLevel, final double batteryConsuption) {
		super();
		this.currentBatteryLevel = currentBatteryLevel;
		this.batteryConsuption = batteryConsuption;
	}

	public String toString() {
		return "Can not execute action, current battery level: " + this.currentBatteryLevel
				+ " battery needed for action: " + this.batteryConsuption + ".";
	}

	public String getMessage() {
		return this.toString();
	}
}
