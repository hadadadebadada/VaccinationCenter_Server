package awk.vakzineverwaltung.usecase;

import awk.AnwendungskernException;

public interface IVakzineErstellen {
	
	public boolean vakzineErstellen(int vakzineId, String name) throws AnwendungskernException;

}
