package control;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.table.DefaultTableModel;

import inne.Polaczenia;
import inne.Produkt;
import inne.Start;

public class ListaProduktowControl extends ControlWzorzec{

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

}
