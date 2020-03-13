package repertoire.exceptions;

/**
 * @authors Gabriel Desmullier, Daniel Gheyssens, Alexandre Verept
 */

@SuppressWarnings("serial")
public class DaoException extends Exception {
	public DaoException(String message) {
		super(message);
	}
}