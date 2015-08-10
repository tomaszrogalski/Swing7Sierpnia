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
}
