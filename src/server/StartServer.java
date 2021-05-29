package server;

import java.rmi.AccessException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.RemoteServer;
import java.rmi.server.UnicastRemoteObject;

import awk.patientenverwaltung.factory.IPatientenverwaltungsFactoryRemote;
import awk.patientenverwaltung.factory.impl.PatientenverwaltungsFactoryRemote;
import awk.patientenverwaltung.usecase.IPatientenErstellenRemote;
import awk.terminverwaltung.factory.ITerminverwaltungsFactoryRemote;
import awk.terminverwaltung.factory.impl.TerminverwaltungsFactoryRemote;
import awk.terminverwaltung.usecase.IPatientenlisteLadenRemote;
import awk.terminverwaltung.usecase.ITerminErstellenRemote;
import awk.terminverwaltung.usecase.ITerminValidierenRemote;
import awk.vakzineverwaltung.factory.IVakzineverwaltungsFactoryRemote;
import awk.vakzineverwaltung.factory.impl.VakzineverwaltungsFactoryRemote;
import awk.vakzineverwaltung.usecase.IBestandsveraenderungErfassenRemote;
import awk.vakzineverwaltung.usecase.IVakzineErstellenRemote;

public class StartServer {
	
	public static void main (String args[]) throws AccessException, RemoteException {
		// Implementierung wird aus der Factory bezogen

		IPatientenverwaltungsFactoryRemote patientenverwaltungsFactoryRemote = new PatientenverwaltungsFactoryRemote();
		IVakzineverwaltungsFactoryRemote vakzineverwaltungsFactoryRemote = new VakzineverwaltungsFactoryRemote();
		ITerminverwaltungsFactoryRemote terminverwaltungsFactoryRemote = new TerminverwaltungsFactoryRemote();
	
		
		

		// implementierung wird vom usecase bezogen
		 IPatientenErstellenRemote patientenErstellenRemote = patientenverwaltungsFactoryRemote.getPatientenErstellenRemote();
		 IVakzineErstellenRemote vakzineErstellenRemote = vakzineverwaltungsFactoryRemote.getVakzineErstellenRemote();
		 IBestandsveraenderungErfassenRemote bestandsveraenderungErfassenRemote = vakzineverwaltungsFactoryRemote.getBestandsveraenderungErfassenRemote();
		 IPatientenlisteLadenRemote patientenlisteLadenRemote = terminverwaltungsFactoryRemote.getPatientenlisteLadenRemote();
		 ITerminErstellenRemote terminerstellenRemote = terminverwaltungsFactoryRemote.getTerminErstellenRemote();
		ITerminValidierenRemote terminValidierenRemote = terminverwaltungsFactoryRemote.getTerminValidierenRemote();

	
		
		 IPatientenErstellenRemote stubPatientenErstellenRemote = (IPatientenErstellenRemote) UnicastRemoteObject.exportObject(patientenErstellenRemote,0);
		 IVakzineErstellenRemote stubVakzineErstellenRemote = (IVakzineErstellenRemote) UnicastRemoteObject.exportObject(vakzineErstellenRemote,0);
		 IBestandsveraenderungErfassenRemote stubBestandsveraenderungErfassenRemote = (IBestandsveraenderungErfassenRemote) UnicastRemoteObject.exportObject(bestandsveraenderungErfassenRemote,0);
		 IPatientenlisteLadenRemote stubpatientenlisteLadenRemote= (IPatientenlisteLadenRemote) UnicastRemoteObject.exportObject(patientenlisteLadenRemote,0);
		 ITerminErstellenRemote stubTerminErstellenRemote = (ITerminErstellenRemote) UnicastRemoteObject.exportObject(terminerstellenRemote,0);
		 ITerminValidierenRemote stubTerminValidierenRemote = (ITerminValidierenRemote) UnicastRemoteObject.exportObject(terminValidierenRemote,0);
		 
		 
		 //unicast remote objekte werden veroeffentlicht
		RemoteServer.setLog(System.out);
		LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
		Registry registry = LocateRegistry.getRegistry("127.0.0.1");
	
		registry.rebind("patientenErstellen", stubPatientenErstellenRemote);
		registry.rebind("vakzineErstellen", stubVakzineErstellenRemote);
		registry.rebind("bestandsveraenderungErfassen", stubBestandsveraenderungErfassenRemote);
		registry.rebind("patientenlisteLaden", stubpatientenlisteLadenRemote);
		registry.rebind("terminErstellen", stubTerminErstellenRemote);
		registry.rebind("terminLadenByID", stubTerminValidierenRemote);

}
}