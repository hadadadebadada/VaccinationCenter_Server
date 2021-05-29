package awk.terminverwaltung.usecase.impl;

import awk.AnwendungskernException;
import awk.terminverwaltung.entity.internal.Termin;
import awk.terminverwaltung.usecase.ITerminErstellen;

public class TerminErstellen implements ITerminErstellen{
	
	public boolean terminErstellen(int terminID, int impftag, int impfmonat, int impfjahr, int std, int min, String bemerkung,
			int vakzineID, int patientenID)throws AnwendungskernException{
		TerminManager terminManager = TerminManager.getTerminManager();
		System.out.println("TerminID: "+ terminID );
		
		terminManager.terminHinzufuegen(new Termin(terminID, impftag, impfmonat,
				impfjahr, std, min, bemerkung, vakzineID, patientenID));
		return true;
	}
	

}
