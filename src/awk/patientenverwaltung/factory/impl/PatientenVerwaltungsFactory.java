package awk.patientenverwaltung.factory.impl;

import awk.patientenverwaltung.factory.IPatientenerwaltungsFactory;
import awk.patientenverwaltung.usecase.IPatientenErstellen;
import awk.patientenverwaltung.usecase.IPatientenInformationByID;
import awk.patientenverwaltung.usecase.impl.PatientenErstellen;
import awk.patientenverwaltung.usecase.impl.PatientenInformationByID;

public class PatientenVerwaltungsFactory implements IPatientenerwaltungsFactory{
	
	public IPatientenErstellen getPatientenErstellen(){
		return new PatientenErstellen();
		
	}
	
	public IPatientenInformationByID getPatientenInformationByID() {
		return new PatientenInformationByID();
	}

}
