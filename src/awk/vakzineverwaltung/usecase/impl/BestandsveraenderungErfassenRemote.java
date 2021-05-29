package awk.vakzineverwaltung.usecase.impl;

import awk.AnwendungskernException;
import awk.vakzineverwaltung.entity.VakzineTO;
import awk.vakzineverwaltung.usecase.IBestandsveraenderungErfassenRemote;

public class BestandsveraenderungErfassenRemote implements IBestandsveraenderungErfassenRemote{

	public VakzineTO vakzineZuordnenByIdR(int vakzineId) throws AnwendungskernException {
		BestandsveraenderungErfassen bestandsveraenderungErfassen = new BestandsveraenderungErfassen();
		return bestandsveraenderungErfassen.vakzineZuordnenById(vakzineId);
	}
	
	public boolean bestandErhoehenR(int vakzineId, int anzahl, int liefertag, int liefermonat, int lieferjahr) throws AnwendungskernException{
		BestandsveraenderungErfassen bestandsveraenderungErfassen = new BestandsveraenderungErfassen();
		return bestandsveraenderungErfassen.bestandErhoehen(vakzineId, anzahl,liefertag, liefermonat, lieferjahr);
	}
	

	public boolean lagerbestandSenkenR(int vakzineId) throws AnwendungskernException{
		BestandsveraenderungErfassen bestandsveraenderungErfassen = new BestandsveraenderungErfassen();
		return bestandsveraenderungErfassen.lagerbestandSenken(vakzineId);
	}

	
	
}
