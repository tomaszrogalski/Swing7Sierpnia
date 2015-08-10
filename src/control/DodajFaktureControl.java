package control;

import java.awt.TextField;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import inne.Polaczenia;
import inne.Produkt;
import inne.Start;
import widok.WidokWzorzec;

public class DodajFaktureControl extends ControlWzorzec {

	static Integer klientId = null;
	static List<ArrayList<Produkt>> listaPozycji = new ArrayList<ArrayList<Produkt>>();
	static ArrayList<Produkt> listaProduktow = new ArrayList<Produkt>();

	public int StworzSelectIdPozycja() throws SQLException {
		String sql = new String();
		sql = " SELECT swing.pozycja.id FROM swing.pozycja ORDER BY swing.pozycja.id DESC LIMIT 1;";
		return ObslugaSelectaKolumnaIdPoyzcja(sql);
	}

	public int StworzSelectIdFaktura(TextField textNumer) throws SQLException {
		String sql = new String();
		sql = "SELECT swing.faktura.id FROM swing.faktura ORDER BY swing.faktura.id DESC LIMIT 1;";
		return ObslugaSelectaKolumnaIdFaktura(sql);
	}

	public Integer ObslugaSelectaKolumnaIdPoyzcja(String sql) throws SQLException {

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

		Integer id = null;
		while (rezultatZapytania.next()) {
			id = rezultatZapytania.getInt(1);
		}
		rezultatZapytania.close();
		zapytania.close();
		return id;
	}

	public Integer ObslugaSelectaKolumnaIdFaktura(String sql) throws SQLException {

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
		// Strefa podwyzszonego ryzyka
		Integer id = null;
		while (rezultatZapytania.next()) {
			id = rezultatZapytania.getInt(1);
		}
		rezultatZapytania.close();
		zapytania.close();
		return id + 1;
		//
	}

	public void StorzInsertFaktura(TextField textNumer) throws SQLException {

		Integer idFakturaInt = StworzSelectIdFaktura(textNumer);

		String idFaktura = idFakturaInt.toString();

		listaPozycji.size();

		String sql1 = new String();
		String sql2 = new String();
		String sql3 = new String();

		Statement zapytania = null;

		java.sql.Connection lacznosc = Polaczenia.lacznosc;

		try {
			lacznosc.setAutoCommit(false);
			zapytania = lacznosc.createStatement();

			sql1 = "Insert into swing.faktura(numer,klient_id) values('" + textNumer.getText() + "'," + klientId + ");";
			zapytania.execute(sql1);

			for (ArrayList<Produkt> listaProduktow : listaPozycji) {
				sql2 = "insert into swing.pozycja(faktura_id) values('" + idFaktura + "');";
				zapytania.execute(sql2);

				Integer idPozycjaInt = StworzSelectIdPozycja();
				String idPozycja = idPozycjaInt.toString();

				for (Produkt produkt : listaProduktow) {

					sql3 = "insert into swing.produkt(nazwa,vat,pozycja_id) values('" + produkt.getNazwa() + "','"
							+ produkt.getVat() + "','" + idPozycja + "');";
					zapytania.execute(sql3);
				}
			}

			lacznosc.commit();

			System.err.println("INSERT zrobiony");

		} catch (SQLException e) {

			try {
				lacznosc.rollback();
				System.err.println(e.getMessage());
				System.err.println("BLAD! Wykonuje ROLLBACK'a");

			} catch (SQLException e1) {
				System.err.println("Blad podczas robienia ROLLBACK'a");
			}
		}
		zapytania.close();
	}

	public static void wybierzKlienta(Integer klientID) {
		klientId = klientID;
	}

	public static void dodajPozycje() {

		listaPozycji.add(listaProduktow);
	}

	public static void DodajProduktDoListyProduktow(Produkt produkt) {
		listaProduktow.add(produkt);
	}

	public static void WyczyscDaneUzyteDoDodaniaFaktury() {
		klientId = null;
		listaPozycji = new ArrayList<ArrayList<Produkt>>();
		listaProduktow = new ArrayList<Produkt>();
	}

	public static boolean walidacjaCzyNumerJestUnikalny(TextField textNumer) throws SQLException {

		boolean bool = true;
		for (String numer : SelectNumeryTabelaFaktury()) {
			if (numer.equals(textNumer.getText())) {
				bool = false;
			}
		}
		if (textNumer.getText().equals("")) {
			bool = false;
		}
		return bool;
	}

	public static boolean walidacjaCzySaDodaneJakiesPozycje() {

		boolean bool = true;
		if (listaPozycji.size() == 0) {
			bool = false;
		}
		return bool;
	}

	public static boolean walidacjaCzyJestDodanyJakisKlient() {
		System.out.println(klientId);
		boolean bool = true;
		if (klientId == null) {
			bool = false;
		}
		return bool;
	}

	public static List<String> SelectNumeryTabelaFaktury() throws SQLException {

		Statement zapytania = null;
		ResultSet rezultatZapytania = null;
		java.sql.Connection lacznosc = Polaczenia.lacznosc;

		try {
			lacznosc.setAutoCommit(false);
			zapytania = lacznosc.createStatement();
			rezultatZapytania = zapytania.executeQuery("select swing.faktura.numer from swing.faktura");
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

		List<String> numery = new ArrayList<String>();
		while (rezultatZapytania.next()) {
			numery.add(rezultatZapytania.getString(1));
		}
		rezultatZapytania.close();
		zapytania.close();
		return numery;
	}
}
