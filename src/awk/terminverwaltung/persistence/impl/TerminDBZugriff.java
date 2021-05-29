package awk.terminverwaltung.persistence.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import awk.DatenhaltungsException;
import awk.patientenverwaltung.entity.PatientTO;
import awk.persistence.Persistence;
import awk.terminverwaltung.entity.TerminTO;
import awk.terminverwaltung.persistence.ITerminDBZugriff;

public class TerminDBZugriff implements ITerminDBZugriff {

	// patientenliste wird geladen um einen termin fuer den jeweiligen patieten zu
	// erstellen ( sollte eigentlich in der patietenverwaltung stehen)
	public Collection<PatientTO> patientenlisteLaden() throws DatenhaltungsException {

		Connection aConnection = Persistence.getConnection();
		ResultSet resultSet;

		PatientTO patientTO = new PatientTO();

		Collection<PatientTO> patientenlisteTO = new ArrayList<PatientTO>();

		try {
			resultSet = Persistence.executeQueryStatement(aConnection, "SELECT * FROM HA1_patientenverwaltung");
			while (resultSet.next()) {
				patientTO = new PatientTO(resultSet.getInt("patientenid"), resultSet.getString("name"),
						resultSet.getString("nachname"), resultSet.getInt("geburtstag"),
						resultSet.getInt("geburtsmonat"), resultSet.getInt("geburtstjahr"),
						resultSet.getString("email"), resultSet.getInt("telefonnr"));
				patientenlisteTO.add(patientTO);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DatenhaltungsException();
		} finally {
			Persistence.closeConnection(aConnection);
		}

		System.out.println("anzhal geladener patienten: " + patientenlisteTO.size());
		return patientenlisteTO;

	}

	public TerminTO terminSuchenById(int terminID) throws DatenhaltungsException {
		Connection aConnection = Persistence.getConnection();

		ResultSet resultSet;
		try {
			resultSet = Persistence.executeQueryStatement(aConnection,
					"SELECT * FROM HA1_terminverwaltung WHERE terminid = " + terminID

			);
			if (resultSet.next()) {
				TerminTO terminTO = new TerminTO();
				terminTO.setTerminID(resultSet.getInt("terminid"));
				terminTO.setImpftag(resultSet.getInt("impftag"));
				terminTO.setImpfmonat(resultSet.getInt("impfmonat"));
				terminTO.setImpfjahr(resultSet.getInt("impfjahr"));
				terminTO.setStd(resultSet.getInt("std"));
				terminTO.setMin(resultSet.getInt("min"));
				terminTO.setBemerkung(resultSet.getString("bemerkung"));
				terminTO.setVakzineID(resultSet.getInt("vakzineid"));
				terminTO.setTerminID(resultSet.getInt("patientenid"));

				System.out.println("terminid ist: " + terminTO.getTerminID());
				return terminTO;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DatenhaltungsException();

		}

		return null;

	}

	public void termindatenErstellen(TerminTO terminTO) throws DatenhaltungsException {
		System.out.println("db zugriff!");
		Connection aConnection = Persistence.getConnection();
		try {
			Persistence.executeUpdateStatement(aConnection,
					"INSERT INTO HA1_TERMINVERWALTUNG VALUES ( " + "'" + terminTO.getTerminID() + "'," + "'"
							+ terminTO.getImpftag() + "'," + "'" + terminTO.getImpfmonat() + "'," + "'"
							+ terminTO.getImpfjahr() + "'," + "'" + terminTO.getStd() + "'," + "'" + terminTO.getMin()
							+ "'," + "'" + terminTO.getBemerkung() + "'," + "'" + terminTO.getVakzineID() + "'," + "'"
							+ terminTO.getPatientenID() + "')");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DatenhaltungsException();
		} finally {
			Persistence.closeConnection(aConnection);
		}

	}

	public void termindatenLoeschen(TerminTO terminTO) throws DatenhaltungsException {

		Connection aConnection = Persistence.getConnection();
		try {
			Persistence.executeUpdateStatement(aConnection,
					"DELETE FROM HA1_terminverwaltung WHERE terminid = " + terminTO.getTerminID());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DatenhaltungsException();
		}
	}

	public void terminValidieren(TerminTO terminTO) throws DatenhaltungsException {

		termindatenLoeschen(terminTO);
		termindatenErstellen(terminTO);
	}

}
