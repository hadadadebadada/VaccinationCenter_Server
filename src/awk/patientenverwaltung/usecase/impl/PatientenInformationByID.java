package awk.patientenverwaltung.usecase.impl;

import awk.AnwendungskernException;
import awk.patientenverwaltung.entity.PatientTO;
import awk.patientenverwaltung.entity.internal.Patient;
import awk.patientenverwaltung.usecase.IPatientenInformationByID;

public class PatientenInformationByID implements IPatientenInformationByID {

	public PatientTO suchePatientByID(int patientenID) throws AnwendungskernException {
		PatientenManager patientenManager = PatientenManager.getPatientenManager();
		Patient patient = patientenManager.patientenSuchenById(patientenID);
		return patient.toPatientTO();
	}

}
