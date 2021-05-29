package awk.patientenverwaltung.usecase.impl;

import awk.AnwendungskernException;
import awk.DatenhaltungsException;
import awk.patientenverwaltung.entity.PatientTO;
import awk.patientenverwaltung.entity.internal.Patient;
import awk.patientenverwaltung.persistence.IPatientenDBZugriff;
import awk.patientenverwaltung.persistence.impl.PatientenDBZugriff;

public class PatientenManager {

	// 1. Datenzugriff
	private IPatientenDBZugriff datenZugriffVerwalter = new PatientenDBZugriff();

	private static PatientenManager patientenManager;

	public static PatientenManager getPatientenManager() throws AnwendungskernException {
		if (patientenManager == null) {
			patientenManager = new PatientenManager();
		}
		return patientenManager;
	}

	private PatientenManager() throws AnwendungskernException {

	}

	public Patient patientenSuchenById(int patientenID) throws AnwendungskernException {
		PatientTO patientTO;
		try {
			patientTO = datenZugriffVerwalter.patientendatenSuchenById(patientenID);

		} catch (DatenhaltungsException e) {
			e.printStackTrace();
			throw new AnwendungskernException();
		}

		return patientTO.toPatient();
	}

	public void patientenHinzufuegen(Patient patient) throws AnwendungskernException {

		try {
			this.datenZugriffVerwalter.patientendatenErstellen(patient.toPatientTO());
		} catch (DatenhaltungsException e) {

			e.printStackTrace();
			throw new AnwendungskernException();

		}

	}

}
