package awk.terminverwaltung.usecase.impl;

import java.rmi.RemoteException;

import awk.AnwendungskernException;
import awk.terminverwaltung.usecase.ITerminErstellenRemote;

public class TerminErstellenRemote implements ITerminErstellenRemote{
	
	public TerminErstellenRemote() {
		
	}
	
	public boolean terminErstellen(int terminID, int impftag, int impfmonat, int impfjahr, int std, int min, String bemerkung,
			int vakzineID, int patientenID)throws AnwendungskernException, RemoteException{
		TerminErstellen terminErstellen = new TerminErstellen();
		return terminErstellen.terminErstellen(terminID, impftag, impfmonat,
				impfjahr, std, min, bemerkung, vakzineID, patientenID);
	}

}
