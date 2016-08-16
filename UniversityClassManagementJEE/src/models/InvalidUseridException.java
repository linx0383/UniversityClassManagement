package models;

public class InvalidUseridException  extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidUseridException() {
		// TODO Auto-generated constructor stub
		super("The userid is not valid");
	}
}
