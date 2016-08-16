package models;

public class InvalidInputNameException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public InvalidInputNameException(String str){
		super(str);
	}
	public InvalidInputNameException(){
		super("Invalid Input Name");
	}
}
