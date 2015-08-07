package control;

import java.awt.TextField;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import widok.WidokWzorzec;

public class DodajKlientaControl extends ControlWzorzec{
	
	public void StworzInsertDoTabeliKlient(TextField textNip, TextField textKod, TextField textMiejscowosc,
			TextField textNrDomu, TextField textUlica, TextField textImie, TextField textNazwisko) throws SQLException {

		String sql = new String();

		sql = "INSERT INTO swing.Klient(nip,kod_pocztowy,miejscowosc,nr_domu,ulica,imie,nazwisko)" + "VALUES('"
				+ textNip.getText() + "','" + textKod.getText() + "','" + textMiejscowosc.getText() + "','"
				+ textNrDomu.getText() + "','" + textUlica.getText() + "','" + textImie.getText() + "','"
				+ textNazwisko.getText() + "');";

		ObslugaInserta(sql);

	}

	// public void InsertDoTabeliKlient(TextField textNip, TextField textKod,
	// TextField textMiejscowosc,
	// TextField textNrDomu, TextField textUlica, TextField textImie, TextField
	// textNazwisko) throws SQLException {
	//
	// Statement zapytania = null;
	// java.sql.Connection lacznosc = Wzorzec.lacznosc;
	//
	// try {
	// lacznosc.setAutoCommit(false);
	// zapytania = lacznosc.createStatement();
	// zapytania.execute("INSERT INTO
	// swing.Klient(nip,kod_pocztowy,miejscowosc,nr_domu,ulica,imie,nazwisko)"
	// + "VALUES('" + textNip.getText() + "','" + textKod.getText() + "','" +
	// textMiejscowosc.getText()
	// + "','" + textNrDomu.getText() + "','" + textUlica.getText() + "','" +
	// textImie.getText() + "','"
	// + textNazwisko.getText() + "');");
	// lacznosc.commit();
	// System.err.println("INSERT zrobiony");
	//
	// } catch (SQLException e) {
	//
	// try {
	// lacznosc.rollback();
	// System.err.println(e.getMessage());
	// System.err.println("BLAD! Wykonuje ROLLBACK'a");
	//
	// } catch (SQLException e1) {
	// System.err.println("Blad podczas robienia ROLLBACK'a");
	// }
	// }
	// zapytania.close();
	// }

}
