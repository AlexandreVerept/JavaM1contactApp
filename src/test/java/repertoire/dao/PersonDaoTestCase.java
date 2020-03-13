package repertoire.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import repertoire.entities.Person;
import repertoire.exceptions.DaoAllParametersAreNullException;
import repertoire.exceptions.DaoException;

/**
 * @authors Gabriel Desmullier, Daniel Gheyssens, Alexandre Verept
 */

public class PersonDaoTestCase {

	/**
	 * This methods is used to insert data before each and every test.
	 * 
	 * @throws SQLException
	 */
	@Before
	public void initDb() throws SQLException {
		Connection connection = DataSourceFactory.getConnection();
		Statement stmt = connection.createStatement();
		// create table if not exists
		stmt.executeUpdate("CREATE TABLE IF NOT EXISTS person (" + "  idperson INT NOT NULL AUTO_INCREMENT,"
				+ "  lastname VARCHAR(45) NOT NULL," + "  firstname VARCHAR(45) NOT NULL,"
				+ "  nickname VARCHAR(45) NOT NULL," + "  phone_number VARCHAR(15) NOT NULL,"
				+ "  adress VARCHAR(200) NOT NULL," + "  emailadress VARCHAR(200) NOT NULL," + "  birth_date Date Null,"
				+ "  PRIMARY KEY (idperson));");

		// clear table
		stmt.executeUpdate("DELETE FROM person");
		// insert values
		stmt.executeUpdate("INSERT INTO person"
				+ "(idperson,lastname,firstname,nickname,phone_number,adress,emailadress,birth_date)"
				+ "VALUES (1,'Verept','Alexandre','Alex','0606060606','1 rue des patates','alex@patate.gouv','1998-03-22 06:00:00.000')");
		stmt.executeUpdate("INSERT INTO person"
				+ "(idperson,lastname,firstname,nickname,phone_number,adress,emailadress,birth_date)"
				+ "VALUES (2,'Gheysens','Daniel','Grani','0606060653','123 rue bidon','alex@patate.gouv','2018-01-01 16:00:00.000')");
		stmt.executeUpdate("INSERT INTO person"
				+ "(idperson,lastname,firstname,nickname,phone_number,adress,emailadress,birth_date)"
				+ "VALUES (3,'Desmullier','Gabriel','Gabi','0','123 rue bidon','gabi@caramail.com','2023-01-01 12:00:00.000')");
		stmt.executeUpdate("INSERT INTO person"
				+ "(idperson,lastname,firstname,nickname,phone_number,adress,emailadress,birth_date)"
				+ "VALUES (4,'Gheysens','Daniel','Alex','0','123 rue bidon','grani@orange.fr','2018-01-01 16:00:00.000')");

		stmt.close();
		connection.close();
	}

	@Test
	public void shouldListPersons() throws SQLException {
		PersonDao personDao = new PersonDao();
		List<Person> personList = personDao.listPersons();
		assertThat(personList).hasSize(4).doesNotContainNull();
	}

	@Test
	public void shouldSearchPersons() throws SQLException, DaoException {
		PersonDao personDao = new PersonDao();

		String nullCaracter = null;

		// we test every parameter one by one
		List<Person> personList = personDao.searchPersons("Gheysens", nullCaracter, nullCaracter, nullCaracter,
				nullCaracter, nullCaracter);
		assertThat(personList).hasSize(2).doesNotContainNull();

		personList = personDao.searchPersons(nullCaracter, "Daniel", nullCaracter, nullCaracter, nullCaracter,
				nullCaracter);
		assertThat(personList).hasSize(2).doesNotContainNull();

		personList = personDao.searchPersons(nullCaracter, nullCaracter, "Alex", nullCaracter, nullCaracter,
				nullCaracter);
		assertThat(personList).hasSize(2).doesNotContainNull();

		personList = personDao.searchPersons(nullCaracter, nullCaracter, nullCaracter, "0", nullCaracter, nullCaracter);
		assertThat(personList).hasSize(2).doesNotContainNull();

		personList = personDao.searchPersons(nullCaracter, nullCaracter, nullCaracter, nullCaracter, "123 rue bidon",
				nullCaracter);
		assertThat(personList).hasSize(3).doesNotContainNull();

		personList = personDao.searchPersons(nullCaracter, nullCaracter, nullCaracter, nullCaracter, nullCaracter,
				"alex@patate.gouv");
		assertThat(personList).hasSize(2).doesNotContainNull();

		// then two parameters that do not follow each other
		personList = personDao.searchPersons("Gheysens", nullCaracter, nullCaracter, nullCaracter, "123 rue bidon",
				nullCaracter);
		assertThat(personList).hasSize(2).doesNotContainNull();

		// the all parameters
		personList = personDao.searchPersons("Verept", "Alexandre", "Alex", "0606060606", "1 rue des patates",
				"alex@patate.gouv");
		assertThat(personList).hasSize(1).doesNotContainNull();

		// test if there is no match
		personList = personDao.searchPersons("George", "Le Rouge Gorge", "Redman", "56537538",
				"3 quartier de la jungle", "mdr@lol.xd");
		assertThat(personList).hasSize(0).doesNotContainNull();
	}

	@Test(expected = DaoAllParametersAreNullException.class)
	public void shouldSendDaoAllParametersAreNullExceptionSearchPersons()
			throws SQLException, DaoAllParametersAreNullException {
		PersonDao personDao = new PersonDao();

		String nullCaracter = null;

		personDao.searchPersons(nullCaracter, nullCaracter, nullCaracter, nullCaracter, nullCaracter, nullCaracter);
	}

	@Test
	public void shouldAddPerson() throws Exception {
		PersonDao personDao = new PersonDao();
		Person personToCreate = new Person(Integer.valueOf(10), "Ginette", "Six Tonnes", "Gigi", "911", "graveyard",
				"gigi@lol.com", LocalDate.of(2000, 12, 4));

		Person newPerson = personDao.addPerson(personToCreate);

		// put gigi in db and check if everything is okay

		Connection connection = DataSourceFactory.getConnection();
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("SELECT * FROM person WHERE lastname='Ginette'");
		assertThat(resultSet.next()).isTrue();
		assertThat(resultSet.getInt("idperson")).isNotNull();
		assertThat(resultSet.getString("lastname")).isEqualTo("Ginette");
		assertThat(resultSet.getString("firstname")).isEqualTo("Six Tonnes");
		assertThat(resultSet.getString("nickname")).isEqualTo("Gigi");
		assertThat(resultSet.getString("phone_number")).isEqualTo("911");
		assertThat(resultSet.getString("adress")).isEqualTo("graveyard");
		assertThat(resultSet.getString("emailadress")).isEqualTo("gigi@lol.com");
		assertThat(resultSet.getDate("birth_date").toLocalDate()).isEqualTo(LocalDate.of(2000, 12, 4));

		// check that there is only one person in the db
		assertThat(resultSet.next()).isFalse();
		resultSet.close();
		statement.close();
		connection.close();

		// Now check the Person that we got back
		assertThat(newPerson).isNotNull();
		assertThat(newPerson.getIdPerson()).isNotNull();

		// check if the person that we got back is the same that the one we stored
		assertThat(newPerson.getLastName()).isEqualTo(personToCreate.getLastName());
		assertThat(newPerson.getFirstName()).isEqualTo(personToCreate.getFirstName());
		assertThat(newPerson.getNickName()).isEqualTo(personToCreate.getNickName());
		assertThat(newPerson.getPhoneNumber()).isEqualTo(personToCreate.getPhoneNumber());
		assertThat(newPerson.getAddress()).isEqualTo(personToCreate.getAddress());
		assertThat(newPerson.geteMailAddress()).isEqualTo(personToCreate.geteMailAddress());
		assertThat(newPerson.getBirthDate()).isEqualTo(personToCreate.getBirthDate());
	}

	@Test
	public void shouldDeleteByID() throws SQLException {
		PersonDao personDao = new PersonDao();

		// delete ID 2
		Boolean answer = personDao.deletePersonbyID(2);
		assertThat(answer == true);

		// fail to delete ID 123
		answer = personDao.deletePersonbyID(123);
		assertThat(answer == false);

		// check in the db if the id 2 is still there
		Connection connection = DataSourceFactory.getConnection();
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("SELECT * FROM person WHERE idperson=2");

		assertThat(resultSet.next()).isFalse();

		resultSet.close();
		statement.close();
		connection.close();

	}

	@Test
	public void shouldUpdate() throws SQLException {
		PersonDao personDao = new PersonDao();

		Person personToUpdate = new Person(Integer.valueOf(1), "Ginette", "Six Tonnes", "Gigi", "911", "graveyard",
				"gigi@lol.com", LocalDate.of(2000, 12, 4));

		Boolean answer = personDao.updatePerson(personToUpdate);
		assertThat(answer == true);

		// check in the db if the id one is updated
		Connection connection = DataSourceFactory.getConnection();
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(
				"select * from person where (idperson = 1 and lastname='Ginette' and firstname='Six Tonnes' and nickname='Gigi' and phone_number='911' and adress='graveyard' and emailadress='gigi@lol.com')");

		assertThat(resultSet.next()).isTrue();

		resultSet.close();
		statement.close();
		connection.close();

	}

}
