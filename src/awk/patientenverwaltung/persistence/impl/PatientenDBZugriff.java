package awk.patientenverwaltung.persistence.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;


import awk.DatenhaltungsException;
import awk.patientenverwaltung.entity.PatientTO;
import awk.patientenverwaltung.persistence.IPatientenDBZugriff;
import awk.persistence.Persistence;

public class PatientenDBZugriff implements IPatientenDBZugriff {

	public void patientendatenErstellen(PatientTO patientTO) throws DatenhaltungsException {
		System.out.println("db zugriff!");
		Connection aConnection = Persistence.getConnection();
		try {
			Persistence.executeUpdateStatement(aConnection,
					"INSERT INTO HA1_PATIENTENVERWALTUNG VALUES ( " + "'" + patientTO.getPatientenID() + "'," + "'"
							+ patientTO.getName() + "'," + "'" + patientTO.getNachname() + "'," + "'"
							+ patientTO.getEmail() + "'," + "'" + patientTO.getTelefonNr() + "'," + "'"
							+ patientTO.getGeburtstag() + "'," + "'" + patientTO.getGeburtsmonat() + "'," + "'"
							+ patientTO.getGeburtsjahr() + "')");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DatenhaltungsException();
		} finally {
			Persistence.closeConnection(aConnection);
		}

	}

	public PatientTO patientendatenSuchenById(int patientenID) throws DatenhaltungsException {
		Connection aConnection = Persistence.getConnection();
		ResultSet resultSet;
		PatientTO patientTO = null;

		try {
			resultSet = Persistence.executeQueryStatement(aConnection,
					"SELECT * FROM HA1_patientenverwaltung WHERE patientenid = " + patientenID);
			while (resultSet.next()) {
				patientTO = new PatientTO(resultSet.getInt("patientenid"), resultSet.getString("name"),
						resultSet.getString("nachname"), resultSet.getInt("geburtstag"),
						resultSet.getInt("geburtsmonat"), resultSet.getInt("geburtstjahr"),
						resultSet.getString("email"), resultSet.getInt("telefonnr"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DatenhaltungsException();
		} finally {
			Persistence.closeConnection(aConnection);
		}
		return patientTO;
	}
}
