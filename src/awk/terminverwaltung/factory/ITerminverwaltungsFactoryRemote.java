package awk.terminverwaltung.factory;

import java.rmi.Remote;
import java.rmi.RemoteException;

import awk.terminverwaltung.usecase.IPatientenlisteLadenRemote;
import awk.terminverwaltung.usecase.ITerminErstellenRemote;
import awk.terminverwaltung.usecase.ITerminValidierenRemote;

public interface ITerminverwaltungsFactoryRemote extends Remote{

	public IPatientenlisteLadenRemote getPatientenlisteLadenRemote() throws RemoteException;
	public ITerminErstellenRemote getTerminErstellenRemote() throws RemoteException;
	public ITerminValidierenRemote getTerminValidierenRemote () throws RemoteException;

}
