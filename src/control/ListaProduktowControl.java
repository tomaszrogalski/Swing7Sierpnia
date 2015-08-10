package control;

import java.awt.TextField;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import inne.Polaczenia;
import inne.Produkt;
import inne.Start;

public class ListaProduktowControl extends ControlWzorzec {

	public DefaultTableModel StworzSelectProduktyIZwrocModel() throws SQLException {

		String sql = new String();

		sql = "select swing.produkt.id as \"ID\",swing.produkt.nazwa as \"NAZWA\", swing.produkt.vat as \"VAT\" from swing.produkt where swing.produkt.pozycja_id is NULL";

		return ZwrocModelZOtrzymanegoSelecta(sql);
	}

	public Produkt StworzSelectProduktIDodajGoDoKlasyProdukt(String id) throws SQLException {

		Statement zapytania = null;
		ResultSet rezultatZapytania = null;
		java.sql.Connection lacznosc = Polaczenia.lacznosc;

		String sql = new String();

		sql = "Select swing.produkt.nazwa, swing.produkt.vat from swing.produkt where id='" + id + "'";

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
		Produkt produkt = null;
		while (rezultatZapytania.next()) {
			String nazwa = rezultatZapytania.getString(1);
			String vat = rezultatZapytania.getString(2);
			produkt = new Produkt(nazwa, vat);
		}
		return produkt;
	}

	public static boolean walidacjaCzyWybranoOdpowiedniIndexProduktu(TextField textNumer)
			throws NumberFormatException, SQLException {
		boolean bool = false;

		for (Integer idNaszegoKlienta : WyliczIdProduktowNaPotrzebyWalidacji()) {
			if (Integer.parseInt(textNumer.getText()) == idNaszegoKlienta) {
				bool = true;
			}
		}

		return bool;
	}

	private static List<Integer> WyliczIdProduktowNaPotrzebyWalidacji() throws SQLException {

		String sql = new String();

		sql = "Select swing.produkt.id as produkt_id from swing.produkt where swing.produkt.pozycja_id is NULL";

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
			numery.add(rezultatZapytania.getInt("produkt_id"));
		}
		System.out.println(numery);
		return numery;

	}
}
