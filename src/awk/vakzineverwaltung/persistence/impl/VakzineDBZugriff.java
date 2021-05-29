package awk.vakzineverwaltung.persistence.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import awk.DatenhaltungsException;
import awk.persistence.Persistence;
import awk.vakzineverwaltung.entity.BestandsveraenderungTO;
import awk.vakzineverwaltung.entity.VakzineTO;
import awk.vakzineverwaltung.persistence.IVakzineDBZugriff;

public class VakzineDBZugriff implements IVakzineDBZugriff {

	@Override
	public void vakzinedatenErstellen(VakzineTO vakzineTO) throws DatenhaltungsException {
		Connection aConnection = Persistence.getConnection();
		try {
			Persistence.executeUpdateStatement(
					aConnection, 
					"INSERT INTO HA1_VAKZINEVERWALTUNG VALUES ( " +
					"'"+ vakzineTO.getVakzineId()+"'," +
					"'"+ vakzineTO.getName()+ "'," +
					"'"+ vakzineTO.getLagerbestand()+"')" );

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DatenhaltungsException();
		} finally {
			Persistence.closeConnection(aConnection);
		}

	}
	


	
	public void patientGeimpft(int vakzineId) throws DatenhaltungsException{
		
		Connection aConnection = Persistence.getConnection();
		
		try {
			Persistence.executeUpdateStatement(aConnection, "UPDATE HA1_bestandsveraenderung SET anzahl=anzahl-1 WHERE anzahl > 0 AND ROWNUM = 1 AND vakzineid = " + vakzineId);			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DatenhaltungsException();
		}finally {
		Persistence.closeConnection(aConnection);}
	}
	
	
	

// neben dem laden aller chargen eines bestimmten impfstoffes wird der gesamtbestand berechnet und der DB hinzugefuegt
	
	
	public VakzineTO vakzinedatenLadenbyId(int vakzineId) throws DatenhaltungsException { 

		Connection aConnection = Persistence.getConnection();
		ResultSet resultSet;
		VakzineTO vakzineTO = null;
		try {

			resultSet = Persistence.executeQueryStatement(aConnection,
					"SELECT vakzineid, name " + "FROM HA1_vakzineverwaltung " + "WHERE vakzineid = " + vakzineId);
			while (resultSet.next()) {
				System.out.println("suche in db nach vakzineId: " + vakzineId);
				vakzineTO = new VakzineTO();
				vakzineTO.setVakzineId(resultSet.getInt("vakzineid"));
				vakzineTO.setName(resultSet.getString("name"));
			}
			;
			if (vakzineTO != null) {
				resultSet = Persistence.executeQueryStatement(aConnection,
						"SELECT anzahl, liefertag, liefermonat, lieferjahr " + "FROM HA1_bestandsveraenderung "
								+ "WHERE vakzineid = " + vakzineTO.getVakzineId());
				int gesamt = 0;
				while (resultSet.next()) {
					System.out.println(resultSet.getInt("anzahl"));
					gesamt = gesamt + resultSet.getInt("anzahl");

					BestandsveraenderungTO bestandsveraenderungTO = new BestandsveraenderungTO(
							resultSet.getInt("anzahl"), vakzineTO, resultSet.getInt("liefertag"),
							resultSet.getInt("liefermonat"), resultSet.getInt("lieferjahr"));
					vakzineTO.getBestandsveraenderungen().add(bestandsveraenderungTO);

				}
				vakzineTO.setLagerbestand(gesamt);
				
				Persistence.executeUpdateStatement(aConnection, 
						"UPDATE HA1_vakzineverwaltung SET lagerbestand = "+ gesamt+
						" WHERE vakzineid = " + vakzineTO.getVakzineId()
						);
				
				System.out.println("gesamt: " + gesamt);

			} else {
				System.out.println("Vakzine Objekt ist null");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DatenhaltungsException();
		} finally {
			Persistence.closeConnection(aConnection);
		}

		return vakzineTO;

	}


// eine neue charge eines impfstoffes wird der DB hinzugefuegt
	public void chargeErfassen(int vakzineId, BestandsveraenderungTO bestandsveraenderungTO)
			throws DatenhaltungsException {
		Connection aConnection = Persistence.getConnection();
	System.out.println(	"lieferjahr in DB: "+ bestandsveraenderungTO.getLieferjahr());
		try {
			Persistence.executeUpdateStatement(aConnection,
					"INSERT INTO HA1_bestandsveraenderung VALUES (" + "'" + vakzineId + "',"
							+ bestandsveraenderungTO.getAnzahl() + "," + bestandsveraenderungTO.getLiefertag() + ","
							+ bestandsveraenderungTO.getLiefermonat() + "," + bestandsveraenderungTO.getLieferjahr()
							+ ")"

			);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DatenhaltungsException();
		}
	}

	@Override
	public void valzinedatenListeErstellen(Collection<VakzineTO> vakzinedatenListe) throws DatenhaltungsException {

		Connection aConnection = Persistence.getConnection();

		try {
			Persistence.executeUpdateStatement(aConnection, "DELETE FROM HA1_bestandsveraenderung");
			Persistence.executeUpdateStatement(aConnection, "DELETE FROM HA1_vakzineverwaltung");
			for (VakzineTO vakzineTO : vakzinedatenListe) {
				Persistence.executeUpdateStatement(aConnection,
						"INSERT INTO HA1_vakzineverwaltung VALUES ( " + vakzineTO.getVakzineId() + ","
								+ vakzineTO.getName() + "," + vakzineTO.getLagerbestand() + ")");
				for (BestandsveraenderungTO bestandsveraenderungTO : vakzineTO.getBestandsveraenderungen()) {
					Persistence.executeUpdateStatement(aConnection,
							"INSERT INTO HA1_bestandsveraenderung VALUES ( " + "'" + vakzineTO.getVakzineId() + ","
									+ bestandsveraenderungTO.getAnzahl() + "," + bestandsveraenderungTO.getLiefertag()
									+ "," + bestandsveraenderungTO.getLiefermonat() + ","
									+ bestandsveraenderungTO.getLieferjahr() + ")");
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DatenhaltungsException();

		} finally {
			Persistence.closeConnection(aConnection);
		}

	}



}
