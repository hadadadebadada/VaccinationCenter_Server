package awk.patientenverwaltung.usecase;

import awk.AnwendungskernException;
import awk.patientenverwaltung.entity.PatientTO;

public interface IPatientenInformationByID {

	public PatientTO suchePatientByID(int patientenID) throws AnwendungskernException;
	
}
