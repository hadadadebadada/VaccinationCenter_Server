package awk.vakzineverwaltung.factory.impl;

import awk.vakzineverwaltung.factory.IVakzineverwaltungsFactory;
import awk.vakzineverwaltung.usecase.IBestandsveraenderungErfassen;
import awk.vakzineverwaltung.usecase.IVakzineErstellen;
import awk.vakzineverwaltung.usecase.impl.BestandsveraenderungErfassen;
import awk.vakzineverwaltung.usecase.impl.VakzineErstellen;

public class VakzineverwaltungsFactory implements IVakzineverwaltungsFactory{
	
	
	public IVakzineErstellen getVakzineErstellen() {
		return new VakzineErstellen();
	}
	
	public IBestandsveraenderungErfassen getBestandsveraenderungErfassen() {
		return new BestandsveraenderungErfassen();
	}
	
	

}
