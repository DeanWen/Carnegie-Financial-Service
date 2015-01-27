/*
 * Name: Dean Wen
 * Date: 1/16/2015
 */
package model;
public class MyDAOException extends Exception {
	private static final long serialVersionUID = 1L;

	public MyDAOException(Exception e) {
		super(e);
	}

	public MyDAOException(String s) {
		super(s);
	}
}