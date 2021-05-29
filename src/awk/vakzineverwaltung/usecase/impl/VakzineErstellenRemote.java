package awk.vakzineverwaltung.usecase.impl;

import java.rmi.RemoteException;

import awk.AnwendungskernException;
import awk.vakzineverwaltung.usecase.IVakzineErstellenRemote;

public class VakzineErstellenRemote implements IVakzineErstellenRemote{


	public boolean vakzineErstellenR(int vakzineId, String name) throws AnwendungskernException, RemoteException {
		VakzineErstellen vakzineErstellen = new VakzineErstellen();
		return vakzineErstellen.vakzineErstellen(vakzineId, name);
	}

}
