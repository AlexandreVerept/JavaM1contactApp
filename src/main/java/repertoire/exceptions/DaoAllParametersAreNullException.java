package repertoire.exceptions;

public class DaoAllParametersAreNullException extends DaoException {

	public DaoAllParametersAreNullException() {
		super("At least one parameter should not be null !");
	}

}
