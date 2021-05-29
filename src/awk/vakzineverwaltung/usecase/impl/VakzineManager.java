package awk.vakzineverwaltung.usecase.impl;


import java.util.Collection;

import awk.AnwendungskernException;
import awk.DatenhaltungsException;
import awk.vakzineverwaltung.entity.VakzineTO;
import awk.vakzineverwaltung.entity.internal.Vakzine;
import awk.vakzineverwaltung.persistence.IVakzineDBZugriff;
import awk.vakzineverwaltung.persistence.impl.VakzineDBZugriff;

public class VakzineManager {

	// 1. Datenzugriff

	private IVakzineDBZugriff datenZugriffVerwalter = new VakzineDBZugriff();

	public static VakzineManager vakzineManager;

	Collection<Vakzine> vakzine;

	public static VakzineManager getVakzineManager() throws AnwendungskernException {

		if (vakzineManager == null) {
			vakzineManager = new VakzineManager();
		}
		return vakzineManager;

	}

	public VakzineManager() throws AnwendungskernException {

	}

	public void vakzineHinzufuegen(Vakzine vakzine) throws AnwendungskernException { // charge
		try {
			this.datenZugriffVerwalter.vakzinedatenErstellen(vakzine.toVakzineTO());
		} catch (DatenhaltungsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new AnwendungskernException();
		}
	}

	public Vakzine sucheVakzineId(int vakzineId) throws AnwendungskernException {
		VakzineTO vakzineTO;
		try {
			vakzineTO = this.datenZugriffVerwalter.vakzinedatenLadenbyId(vakzineId);
		} catch (DatenhaltungsException e) {
			e.printStackTrace();
			throw new AnwendungskernException();

		}
		if (vakzineTO == null) {
			return null;
		} else {
			return vakzineTO.toVakzine();
		}
	}


	public IVakzineDBZugriff getDatenZugriffVerwalter() {
		return datenZugriffVerwalter;
	}

}
