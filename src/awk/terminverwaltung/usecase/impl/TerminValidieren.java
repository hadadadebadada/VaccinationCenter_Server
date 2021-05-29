package awk.terminverwaltung.usecase.impl;

import awk.AnwendungskernException;
import awk.patientenverwaltung.entity.PatientTO;
import awk.patientenverwaltung.factory.IPatientenerwaltungsFactory;
import awk.patientenverwaltung.factory.impl.PatientenVerwaltungsFactory;
import awk.patientenverwaltung.usecase.IPatientenInformationByID;
import awk.terminverwaltung.entity.TerminTO;
import awk.terminverwaltung.entity.internal.Termin;
import awk.terminverwaltung.usecase.ITerminValidieren;

public class TerminValidieren implements ITerminValidieren {

	public boolean terminValidieren(int terminID, int impftag, int impfmonat, int impfjahr, int std, int min,
			String bemerkung, int vakzineID, int patientenID) throws AnwendungskernException {
		TerminManager terminManager = TerminManager.getTerminManager();
		System.out.println("TerminID: " + terminID);
		terminManager.terminValidieren(
				new Termin(terminID, impftag, impfmonat, impfjahr, std, min, bemerkung, vakzineID, patientenID));
		return true;

	}

	public TerminTO terminSuchenbyID(int terminID) throws AnwendungskernException {
		TerminManager terminManager = TerminManager.getTerminManager();
		Termin termin = terminManager.terminSuchenByID(terminID);
		TerminTO terminTO = null;

		terminTO = termin.toTerminTO();
		IPatientenerwaltungsFactory pvf = new PatientenVerwaltungsFactory();
		IPatientenInformationByID info = pvf.getPatientenInformationByID();
		PatientTO patientTO = info.suchePatientByID(termin.getTerminID()); // verknüpfung von patient und termin
		terminTO.setPatientenID(patientTO.getPatientenID());

		return terminTO;

	}

}