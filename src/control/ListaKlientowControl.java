package control;

import java.awt.TextField;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import inne.Polaczenia;
import widok.WidokWzorzec;

public class ListaKlientowControl extends ControlWzorzec {

	public DefaultTableModel SelectKlientZwrocModel() throws SQLException {

		String sql = new String();

		sql = "Select swing.klient.id as \"ID\",swing.klient.nip as \"NIP\",swing.klient.kod_pocztowy as \"KOD POCZTOWY\",swing.klient.miejscowosc as \"MIEJSCOWOSC\",swing.klient.nr_domu as \"NR DOMU\",swing.klient.ulica as \"ULICA\",swing.klient.imie as \"IMIE\" from swing.klient";

		return SelectDajSqlZwrocModelTabeli(sql);

	}

	public static boolean walidacjaCzyKlientOPodanymIdIstnieje(TextField textNumer) throws SQLException {

		boolean bool = false;

		for (Integer idNaszegoKlienta : WyliczLiczbeKlientowNaPotrzebyWalidacji()) {
			if (Integer.parseInt(textNumer.getText()) == idNaszegoKlienta) {
				bool = true;
			}
		}

		return bool;
	}

	private static List<Integer> WyliczLiczbeKlientowNaPotrzebyWalidacji() throws SQLException {

		String sql = new String();

		sql = "Select swing.klient.id as klient_id from swing.klient";

		Statement zapytania = null;
		ResultSet rezultatZapytania = null;
		java.sql.Connection lacznosc = Polaczenia.lacznosc;

		try {
			lacznosc.setAutoCommit(false);
			zapytania = lacznosc.createStatement();
			rezultatZapytania = zapytania.executeQuery(sql);
			lacznosc.commit();

			System.err.println("SELECT zrobiony");

		} catch (SQLException e) {

			try {
				lacznosc.rollback();
				System.err.println(e.getMessage());
				System.err.println("BLAD! Wykonuje ROLLBACK'a");

			} catch (SQLException e1) {
				System.err.println("Blad podczas robienia ROLLBACK'a");
			}
		}

		List<Integer> numery = new ArrayList<Integer>();
		while (rezultatZapytania.next()) {
			numery.add(rezultatZapytania.getInt("klient_id"));
		}
		rezultatZapytania.close();
		zapytania.close();
		return numery;

	}
}