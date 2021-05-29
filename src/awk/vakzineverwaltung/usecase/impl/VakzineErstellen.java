package awk.vakzineverwaltung.usecase.impl;

import awk.AnwendungskernException;
import awk.vakzineverwaltung.entity.internal.Vakzine;
import awk.vakzineverwaltung.usecase.IVakzineErstellen;

public class VakzineErstellen implements IVakzineErstellen {

	public boolean vakzineErstellen(int vakzineId, String name) throws AnwendungskernException {

		VakzineManager vakzineManager = VakzineManager.getVakzineManager();
		System.out.println(vakzineId + "----" + name);
		vakzineManager.vakzineHinzufuegen(new Vakzine(vakzineId, name));

		return true;

	}

}
