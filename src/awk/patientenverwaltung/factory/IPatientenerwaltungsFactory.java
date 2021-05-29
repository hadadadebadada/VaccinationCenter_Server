package awk.patientenverwaltung.factory;

import awk.patientenverwaltung.usecase.IPatientenErstellen;
import awk.patientenverwaltung.usecase.IPatientenInformationByID;

public interface IPatientenerwaltungsFactory {
	
	IPatientenErstellen getPatientenErstellen(); 
	
	public IPatientenInformationByID getPatientenInformationByID();


}
