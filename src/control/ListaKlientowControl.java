package control;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import widok.WidokWzorzec;

public class ListaKlientowControl extends ControlWzorzec {

	public DefaultTableModel SelectKlientZwrocModel() throws SQLException {

		String sql = new String();

		sql = "Select swing.klient.id as \"ID\",swing.klient.nip as \"NIP\",swing.klient.kod_pocztowy as \"KOD POCZTOWY\",swing.klient.miejscowosc as \"MIEJSCOWOSC\",swing.klient.nr_domu as \"NR DOMU\",swing.klient.ulica as \"ULICA\",swing.klient.imie as \"IMIE\" from swing.klient";

		return ZwrocModelZOtrzymanegoSelecta(sql);

	}

}
