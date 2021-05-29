package awk.terminverwaltung.persistence;

import java.util.Collection;

import awk.DatenhaltungsException;
import awk.patientenverwaltung.entity.PatientTO;
import awk.terminverwaltung.entity.TerminTO;

public interface ITerminDBZugriff {

	
	public Collection<PatientTO> patientenlisteLaden() throws DatenhaltungsException;
	
	public void termindatenErstellen(TerminTO terminTO) throws DatenhaltungsException;
	
	public void terminValidieren(TerminTO terminTO) throws DatenhaltungsException;
	
	public TerminTO terminSuchenById(int terminID) throws DatenhaltungsException;
}
