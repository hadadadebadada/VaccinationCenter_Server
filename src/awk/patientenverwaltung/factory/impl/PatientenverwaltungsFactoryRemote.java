package awk.patientenverwaltung.factory.impl;

import awk.patientenverwaltung.factory.IPatientenverwaltungsFactoryRemote;
import awk.patientenverwaltung.usecase.IPatientenErstellenRemote;
import awk.patientenverwaltung.usecase.impl.PatientenErstellenRemote;

public class PatientenverwaltungsFactoryRemote implements IPatientenverwaltungsFactoryRemote{

	public IPatientenErstellenRemote getPatientenErstellenRemote(){
		return new PatientenErstellenRemote();
	}
	
}
