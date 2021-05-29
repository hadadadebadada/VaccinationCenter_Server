package awk.terminverwaltung.usecase.impl;

import awk.AnwendungskernException;
import awk.terminverwaltung.entity.TerminTO;
import awk.terminverwaltung.usecase.ITerminValidierenRemote;

public class TerminValidierenRemote implements ITerminValidierenRemote {

	public TerminTO terminSuchenByIDR(int terminID) throws AnwendungskernException {

		TerminValidieren terminValidieren = new TerminValidieren();
		return terminValidieren.terminSuchenbyID(terminID);

	}

	public boolean terminValidierenR(int terminID, int impftag, int impfmonat, int impfjahr, int std, int min,
			String bemerkung, int vakzineID, int patientenID) throws AnwendungskernException {

		TerminValidieren terminValidieren = new TerminValidieren();
		return terminValidieren.terminValidieren(terminID, impftag, impfmonat, impfjahr, std, min, bemerkung, vakzineID,
				patientenID);

	}

}
