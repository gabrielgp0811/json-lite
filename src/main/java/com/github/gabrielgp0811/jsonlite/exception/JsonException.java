/**
 * 
 */
package com.github.gabrielgp0811.jsonlite.exception;

/**
 * The main exception.
 * 
 * @author gabrielgp0811
 */
public class JsonException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7306491595931729292L;

	/**
	 * 
	 */
	public JsonException() {

	}

	/**
	 * @param message the detail message. The detail message is saved for later
	 *                retrieval by the {@link #getMessage()} method.
	 */
	public JsonException(String message) {
		super(message);
	}

	/**
	 * @param cause cause the cause (which is saved for later retrieval by the
	 *              {@link #getCause()} method). (A <tt>null</tt> value is
	 *              permitted, and indicates that the cause is nonexistent or
	 *              unknown.)
	 */
	public JsonException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message the detail message (which is saved for later retrieval by the
	 *                {@link #getMessage()} method).
	 * @param cause   the cause (which is saved for later retrieval by the
	 *                {@link #getCause()} method). (A <tt>null</tt> value is
	 *                permitted, and indicates that the cause is nonexistent or
	 *                unknown.)
	 */
	public JsonException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message            the detail message.
	 * @param cause              the cause. (A {@code null} value is permitted, and
	 *                           indicates that the cause is nonexistent or
	 *                           unknown.)
	 * @param enableSuppression  whether or not suppression is enabled or disabled
	 * @param writableStackTrace whether or not the stack trace should be writable
	 */
	public JsonException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}