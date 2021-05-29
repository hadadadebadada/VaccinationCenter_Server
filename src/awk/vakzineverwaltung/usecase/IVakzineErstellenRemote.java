package awk.vakzineverwaltung.usecase;

import java.rmi.Remote;
import java.rmi.RemoteException;

import awk.AnwendungskernException;

public interface IVakzineErstellenRemote extends Remote{
	
	public boolean vakzineErstellenR(int vakzineId, String name) throws AnwendungskernException, RemoteException;

}
