package awk.vakzineverwaltung.entity.internal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;


import awk.vakzineverwaltung.entity.BestandsveraenderungTO;
import awk.vakzineverwaltung.entity.VakzineTO;

public class Vakzine  implements Serializable{
	 private static final long serialVersionUID = -7558883996516598760L;
	private int vakzineId;
	private String name;
	private int lagerbestand;

	private Collection<Bestandsveraenderung> bestandsveraenderungen;
	
	
	public Vakzine(int vakzineId, String name) {
		super();
		this.vakzineId = vakzineId;
		this.name = name;
		this.lagerbestand = 0;
		bestandsveraenderungen = new ArrayList<Bestandsveraenderung>();

	}


	public Vakzine(int vakzineId, String name, int lagerbestand) {
		super();
		this.vakzineId = vakzineId;
		this.name = name;
		this.lagerbestand = lagerbestand;

	}
	public VakzineTO toVakzineTO() {
		VakzineTO vakzineTO = new VakzineTO();
		vakzineTO.setVakzineId(vakzineId);
		vakzineTO.setLagerbestand(lagerbestand);
		vakzineTO.setName(name);
		vakzineTO.setBestandsveraenderungen(new ArrayList<BestandsveraenderungTO>());
		
		for (Bestandsveraenderung bestandsveraenderung:this.getBestandsveraenderungen()) {
			vakzineTO.getBestandsveraenderungen().add(new BestandsveraenderungTO(bestandsveraenderung.getAnzahl(), vakzineTO, bestandsveraenderung.getLiefertag(), bestandsveraenderung.getLiefermonat(), bestandsveraenderung.getLieferjahr()));
		}
		
		
		return vakzineTO;
		
	}

	public int getLagerbestand() {
		return lagerbestand;
	}
	
	public void addBestandsveraenderung(Bestandsveraenderung bestandsveraenderung) {
		this.bestandsveraenderungen.add(bestandsveraenderung);
	}

	public int getVakzineId() {
		return vakzineId;
	}
	public Collection<Bestandsveraenderung> getBestandsveraenderungen() {
		return bestandsveraenderungen;
	}

	public void setLagerbestand(int anzahl) {
		this.lagerbestand = anzahl;
	}


	
}
