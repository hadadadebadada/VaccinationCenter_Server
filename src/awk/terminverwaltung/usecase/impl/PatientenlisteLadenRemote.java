package awk.terminverwaltung.usecase.impl;

import java.rmi.RemoteException;
import java.util.Collection;

import awk.AnwendungskernException;
import awk.patientenverwaltung.entity.PatientTO;
import awk.terminverwaltung.usecase.IPatientenlisteLadenRemote;

public class PatientenlisteLadenRemote implements IPatientenlisteLadenRemote{
	
	public Collection<PatientTO> patientenListeAusgebenR() throws AnwendungskernException, RemoteException{
		System.out.println("erstelle patientenlisteTO remote");
		PatientenlisteLaden patientenlisteLaden = new PatientenlisteLaden();
		return patientenlisteLaden.patientenListeAusgeben();		
	}	

}
