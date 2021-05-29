package awk.terminverwaltung.usecase.impl;

import java.util.ArrayList;
import java.util.Collection;

import awk.AnwendungskernException;
import awk.DatenhaltungsException;
import awk.patientenverwaltung.entity.PatientTO;
import awk.patientenverwaltung.entity.internal.Patient;
import awk.terminverwaltung.entity.TerminTO;
import awk.terminverwaltung.entity.internal.Termin;
import awk.terminverwaltung.persistence.ITerminDBZugriff;
import awk.terminverwaltung.persistence.impl.TerminDBZugriff;

public class TerminManager {

	private ITerminDBZugriff datenZugriff = new TerminDBZugriff();

	private static TerminManager terminManager;

	private Collection<Patient> patienten; //

	public static TerminManager getTerminManager() throws AnwendungskernException {
		if (terminManager == null) {
			terminManager = new TerminManager();
		}
		return terminManager;
	}

	private TerminManager() throws AnwendungskernException {
		patienten = new ArrayList<Patient>();
		this.patientenLaden();

	}

	public Collection<Patient> patientenCollectionLaden() {
		return this.patienten;
	}

	public void patientenLaden() throws AnwendungskernException { // fuer patientenliste
		Collection<PatientTO> patientenlisteTO;

		try {

			patientenlisteTO = datenZugriff.patientenlisteLaden();
			System.out.println("PatientenlisteTO im Manager: " + patientenlisteTO.size());
		} catch (DatenhaltungsException e) {
			throw new AnwendungskernException();
		}

		for (PatientTO patientTO : patientenlisteTO) {
			this.patienten.add(patientTO.toPatient());
		}

	}

	public void terminHinzufuegen(Termin termin) throws AnwendungskernException {
		// this.patienten.add(patient);

		try {
			this.datenZugriff.termindatenErstellen(termin.toTerminTO());
		} catch (DatenhaltungsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new AnwendungskernException();

		}

	}

	// laed einen termin je nach ID
	public Termin terminSuchenByID(int terminID) throws AnwendungskernException {
		TerminTO terminTO;
		try {
			terminTO = this.datenZugriff.terminSuchenById(terminID);
		} catch (DatenhaltungsException e) {
			e.printStackTrace();
			throw new AnwendungskernException();
		}
		return terminTO.toTerminTO();

	}

//	public void terminEntfernen(Termin termin) throws AnwendungskernException{
//		try {
//			this.datenZugriff.termindatenLoeschen(termin.toTerminTO());
//		}catch(DatenhaltungsException e) {
//			e.printStackTrace();
//			throw new AnwendungskernException();
//		}
//		
//	}
//	

	
	//alter termin wird entfernt sobald der patient geimpft wurde 
	// neuer termin enthaelt nun zusaetzlich eine vakzineID und eine bemerkung
	public void terminValidieren(Termin termin) throws AnwendungskernException {
		try {
			this.datenZugriff.terminValidieren(termin.toTerminTO());
		} catch (DatenhaltungsException e) {
			e.printStackTrace();
			throw new AnwendungskernException();
		}

	}

}
