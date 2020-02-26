package repertoire.exceptions;

/**
 * @authors Gabriel Desmullier, Daniel Gheyssens, Alexandre Verept
 */

public class DaoAllParametersAreNullException extends DaoException {

	public DaoAllParametersAreNullException() {
		super("At least one parameter should not be null !");
	}

}
