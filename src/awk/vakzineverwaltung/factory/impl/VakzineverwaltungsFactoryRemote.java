package awk.vakzineverwaltung.factory.impl;

import awk.vakzineverwaltung.factory.IVakzineverwaltungsFactoryRemote;
import awk.vakzineverwaltung.usecase.IBestandsveraenderungErfassenRemote;
import awk.vakzineverwaltung.usecase.IVakzineErstellenRemote;
import awk.vakzineverwaltung.usecase.impl.BestandsveraenderungErfassenRemote;
import awk.vakzineverwaltung.usecase.impl.VakzineErstellenRemote;

public class VakzineverwaltungsFactoryRemote implements IVakzineverwaltungsFactoryRemote{

	public IVakzineErstellenRemote getVakzineErstellenRemote() {
		return new VakzineErstellenRemote();
	}
	
	public IBestandsveraenderungErfassenRemote getBestandsveraenderungErfassenRemote() {
		return new BestandsveraenderungErfassenRemote();
	}
	
}
