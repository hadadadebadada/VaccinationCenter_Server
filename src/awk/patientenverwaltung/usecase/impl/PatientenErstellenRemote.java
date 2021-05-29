package awk.patientenverwaltung.usecase.impl;

import java.rmi.RemoteException;

import awk.AnwendungskernException;
import awk.patientenverwaltung.usecase.IPatientenErstellenRemote;

public class PatientenErstellenRemote implements IPatientenErstellenRemote {

	public PatientenErstellenRemote() {
	}

	public boolean patientenErstellenR(int patientenID, String name, String nachname, int geburtstag, int geburtsmonat,
			int geburtsjahr, String email, int telefonNr) throws AnwendungskernException, RemoteException {
		PatientenErstellen patientenErstellen = new PatientenErstellen();
		return patientenErstellen.patientenErstellen(patientenID, name, nachname, geburtstag, geburtsmonat, geburtsjahr,
				email, telefonNr);

	}

}
