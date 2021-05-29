package awk.terminverwaltung.usecase;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;

import awk.AnwendungskernException;
import awk.patientenverwaltung.entity.PatientTO;

public interface IPatientenlisteLadenRemote extends Remote {

	public Collection<PatientTO> patientenListeAusgebenR() throws AnwendungskernException, RemoteException;

}
