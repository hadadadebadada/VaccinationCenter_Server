package awk.vakzineverwaltung.usecase.impl;

import awk.AnwendungskernException;
import awk.DatenhaltungsException;
import awk.vakzineverwaltung.entity.VakzineTO;
import awk.vakzineverwaltung.entity.internal.Bestandsveraenderung;
import awk.vakzineverwaltung.entity.internal.Vakzine;
import awk.vakzineverwaltung.usecase.IBestandsveraenderungErfassen;

public class BestandsveraenderungErfassen implements IBestandsveraenderungErfassen {

	public BestandsveraenderungErfassen() {

	}

	@Override
	public VakzineTO vakzineZuordnenById(int vakzineId) throws AnwendungskernException {
		VakzineManager vakzineManager = VakzineManager.getVakzineManager();

		Vakzine vakzine = vakzineManager.sucheVakzineId(vakzineId); // was passiert hier ?
		VakzineTO vakzineTO;
		if (vakzine != null) {
			vakzineTO = vakzine.toVakzineTO();
		} else {
			vakzineTO = null;
		}

		return vakzineTO;

	}

	public boolean bestandErhoehen(int vakzineId, int anzahl, int liefertag, int liefermonat, int lieferjahr)
			throws AnwendungskernException {

		VakzineManager vakzineManager = VakzineManager.getVakzineManager();
		Vakzine vakzine = vakzineManager.sucheVakzineId(vakzineId);

		if (vakzine == null) {
			return false;
		} else {

			Bestandsveraenderung bestandsveraenderung = new Bestandsveraenderung(anzahl, vakzine, liefertag,
					liefermonat, lieferjahr);
			vakzine.addBestandsveraenderung(bestandsveraenderung);
			vakzine.setLagerbestand(vakzine.getLagerbestand() + anzahl);
			System.out.println("aktueller lagerbestand" + vakzine.getLagerbestand());
			try {
				vakzineManager.getDatenZugriffVerwalter().chargeErfassen(vakzine.getVakzineId(),
						bestandsveraenderung.toBestandsveraenderungTO());
				System.out.println("charge wurde erfassst");
				// System.out.println("neuer lagerbestand in DB abgelegt");
			} catch (DatenhaltungsException e) {
				e.printStackTrace();
				throw new AnwendungskernException();
			}

			return true;
		}

	}

	// bestand wird nach der impfung eines patienten von einer charge abgezogen
	public boolean lagerbestandSenken(int vakzineId) throws AnwendungskernException {

		VakzineManager vakzineManager = VakzineManager.getVakzineManager();

		Vakzine vakzine = vakzineManager.sucheVakzineId(vakzineId);

		if (vakzine == null) {
			return false;
		} else {

			try {
				vakzineManager.getDatenZugriffVerwalter().patientGeimpft(vakzineId);
			} catch (DatenhaltungsException e) {
				e.printStackTrace();
			}

			return true;
		}

	}

}
