package awk.terminverwaltung.usecase.impl;

import java.util.ArrayList;
import java.util.Collection;

import awk.AnwendungskernException;

import awk.patientenverwaltung.entity.PatientTO;
import awk.patientenverwaltung.entity.internal.Patient;
import awk.terminverwaltung.usecase.IPatientenlisteLaden;

public class PatientenlisteLaden implements IPatientenlisteLaden{
	
	public Collection<PatientTO> patientenListeAusgeben() throws AnwendungskernException{
		TerminManager terminManager = TerminManager.getTerminManager();
		
		Collection<PatientTO> patientenlisteTO = new ArrayList<PatientTO>();
		Collection<Patient> patientenliste = terminManager.patientenCollectionLaden();
		
		System.out.println(patientenliste.size());
		for (Patient patient:patientenliste) {
			patientenlisteTO.add(patient.toPatientTO());
		}
		
		
		return patientenlisteTO;
	}
	
}
