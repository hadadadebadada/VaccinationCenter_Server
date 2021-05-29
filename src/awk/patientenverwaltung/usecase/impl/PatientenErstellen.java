package awk.patientenverwaltung.usecase.impl;

import awk.AnwendungskernException;
import awk.patientenverwaltung.entity.internal.Patient;
import awk.patientenverwaltung.usecase.IPatientenErstellen;

public class PatientenErstellen implements IPatientenErstellen {

	@Override
	public boolean patientenErstellen(int patientenID, String name, String nachname, int geburtstag, int geburtsmonat,
			int geburtsjahr, String email, int telefonNr) throws AnwendungskernException {
		PatientenManager patientenManager = PatientenManager.getPatientenManager();
		System.out.println(name + " - " + nachname);
		patientenManager.patientenHinzufuegen(
				new Patient(patientenID, name, nachname, geburtstag, geburtsmonat, geburtsjahr, email, telefonNr));
		return true;
	}

}
