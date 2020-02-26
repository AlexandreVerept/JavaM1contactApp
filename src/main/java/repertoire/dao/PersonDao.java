package repertoire.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import repertoire.entities.Person;
import repertoire.exceptions.DaoAllParametersAreNullException;



/**
 * @authors Gabriel Desmullier, Daniel Gheyssens, Alexandre Verept
 */
public class PersonDao {

	/**
	 * Method Used to get the list of all person in the DB (in lastname order)
	 * 
	 * @return the list of all Person in the database
	 */
	public List<Person> listPersons() {
		List<Person> listOfPerson = new ArrayList<>();
		try (Connection connection = DataSourceFactory.getConnection()) {
			try (Statement stmt = connection.createStatement()) {
				try (ResultSet results = stmt.executeQuery("select * from person order by lastname")) {
					while (results.next()) {

						Person person = new Person(results.getInt("idperson"), results.getString("lastname"),
								results.getString("firstname"), results.getString("nickname"),
								results.getString("phone_number"), results.getString("adress"),
								results.getString("emailadress"), results.getDate("birth_date").toLocalDate());
						listOfPerson.add(person);
					}
					return listOfPerson;
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException("Error: listPersons", e);
		}
	}

	/**
	 * Method Used to search a person by firstname, lastname or nickname
	 * 
	 * @return the list of all Person in the database that match the research
	 * @throws DaoException
	 */
	public List<Person> searchPersons(String lastname, String firstname, String nickname, String phone_number,
			String adress, String emailadress) throws DaoAllParametersAreNullException {
		List<Person> listOfPerson = new ArrayList<>();

		String sqlQuery = "select * from person where ";
		int count = 0;
		ArrayList<String> myParametersList = new ArrayList<String>();

		String nullCaracter = null;
		String sepCar = " and ";

		if (firstname != nullCaracter) {
			sqlQuery += "firstname=?";
			myParametersList.add(firstname);
			count += 1;
		}
		if (lastname != nullCaracter) {
			if (count > 0) {
				sqlQuery += sepCar;
			}
			sqlQuery += "lastname=?";
			myParametersList.add(lastname);
			count += 1;
		}
		if (nickname != nullCaracter) {
			if (count > 0) {
				sqlQuery += sepCar;
			}
			sqlQuery += "nickname=?";
			myParametersList.add(nickname);
			count += 1;
		}
		if (phone_number != nullCaracter) {
			if (count > 0) {
				sqlQuery += sepCar;
			}
			sqlQuery += "phone_number=?";
			myParametersList.add(phone_number);
			count += 1;
		}
		if (adress != nullCaracter) {
			if (count > 0) {
				sqlQuery += sepCar;
			}
			sqlQuery += "adress=?";
			myParametersList.add(adress);
			count += 1;
		}
		if (emailadress != nullCaracter) {
			if (count > 0) {
				sqlQuery += sepCar;
			}
			sqlQuery += "emailadress=?";
			myParametersList.add(emailadress);
			count += 1;
		}

		if (count == 0) {
			throw new DaoAllParametersAreNullException();
		}

		sqlQuery += " order by lastname";

		try (Connection connection = DataSourceFactory.getConnection()) {
			try (PreparedStatement stmt = connection.prepareStatement(sqlQuery)) {
				for (int i = 0; i < myParametersList.size(); i++) {
					stmt.setString(i + 1, myParametersList.get(i));
				}
				try (ResultSet results = stmt.executeQuery()) {
					while (results.next()) {

						Person person = new Person(results.getInt("idperson"), results.getString("lastname"),
								results.getString("firstname"), results.getString("nickname"),
								results.getString("phone_number"), results.getString("adress"),
								results.getString("emailadress"), results.getDate("birth_date").toLocalDate());
						listOfPerson.add(person);
					}
					return listOfPerson;
				}
			}
		} catch (SQLException e1) {
			throw new RuntimeException("Error: listPersons", e1);
		}
	}

	/**
	 * Method Used to save a person in the DB
	 * 
	 * @param Person
	 * @return the Person that was persisted, with it's ID
	 * 
	 */
	public Person addPerson(Person person) {
		try (Connection cnx = DataSourceFactory.getConnection()) {
			try (PreparedStatement stmt = cnx.prepareStatement(
					"INSERT INTO person(lastname,firstname,nickname,phone_number,adress,emailadress,birth_date) VALUES(?,?,?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS)) {
				stmt.setString(1, person.getLastName());
				stmt.setString(2, person.getFirstName());
				stmt.setString(3, person.getNickName());
				stmt.setString(4, person.getPhoneNumber());
				stmt.setString(5, person.getAddress());
				stmt.setString(6, person.geteMailAddress());
				// we need to do add one day as MySQL database create an offset of -1 day when
				// we use "LocalDate" for unknown reason
				stmt.setDate(7, java.sql.Date.valueOf(person.getBirthDate().plusDays(1)));

				stmt.executeUpdate();
				try (ResultSet keys = stmt.getGeneratedKeys()) {
					keys.next();
					person.setIdPerson(keys.getInt(1));
					return person;
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException("Error: addPerson", e);
		}
	}

	/**
	 * Method Used to delete a person in the DB
	 * 
	 * @param Person
	 * @return boolean indicating the success or not
	 * @throws SQLException 
	 * 
	 */
	public Boolean deletePersonbyID(int id) throws SQLException {

		try (Connection cnx = DataSourceFactory.getConnection()) {
			try (PreparedStatement stmt = cnx.prepareStatement("DELETE FROM person WHERE idPerson=?")) {
				stmt.setInt(1, id);
				// we need to do add one day as MySQL database create an offset of -1 day when
				// we use "LocalDate" for unknown reason

				stmt.executeUpdate();

			} catch (SQLException e) {
				throw new RuntimeException("Error: addPerson", e);
			}
		}
		return true;
	}
}
