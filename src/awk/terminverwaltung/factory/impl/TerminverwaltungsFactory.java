package awk.terminverwaltung.factory.impl;

import awk.terminverwaltung.factory.ITerminverwaltungsFactory;
import awk.terminverwaltung.usecase.IPatientenlisteLaden;
import awk.terminverwaltung.usecase.ITerminErstellen;
import awk.terminverwaltung.usecase.ITerminValidieren;
import awk.terminverwaltung.usecase.impl.PatientenlisteLaden;
import awk.terminverwaltung.usecase.impl.TerminErstellen;
import awk.terminverwaltung.usecase.impl.TerminValidieren;

public class TerminverwaltungsFactory implements ITerminverwaltungsFactory{
	
	public IPatientenlisteLaden getPatientenlisteLaden() {
		return new PatientenlisteLaden();
	}
	public ITerminErstellen getTerminErstellen() {
		return new TerminErstellen();
	}
	public ITerminValidieren getTerminValidieren() {
		return new TerminValidieren();
	}

}
