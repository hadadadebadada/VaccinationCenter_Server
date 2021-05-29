package awk.terminverwaltung.factory.impl;

import java.rmi.RemoteException;


import awk.terminverwaltung.factory.ITerminverwaltungsFactoryRemote;
import awk.terminverwaltung.usecase.IPatientenlisteLadenRemote;
import awk.terminverwaltung.usecase.ITerminErstellenRemote;
import awk.terminverwaltung.usecase.ITerminValidierenRemote;
import awk.terminverwaltung.usecase.impl.PatientenlisteLadenRemote;
import awk.terminverwaltung.usecase.impl.TerminErstellenRemote;
import awk.terminverwaltung.usecase.impl.TerminValidierenRemote;

public class TerminverwaltungsFactoryRemote	implements ITerminverwaltungsFactoryRemote{
	
	public IPatientenlisteLadenRemote getPatientenlisteLadenRemote() throws RemoteException {
		return new PatientenlisteLadenRemote();
	}

	public ITerminErstellenRemote getTerminErstellenRemote() throws RemoteException{
		return new TerminErstellenRemote();
	}
	
	public ITerminValidierenRemote getTerminValidierenRemote () throws RemoteException{
		return new TerminValidierenRemote();
	}

}
