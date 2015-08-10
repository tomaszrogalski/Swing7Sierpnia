package control;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import inne.Polaczenia;
import widok.WidokWzorzec;

public class ControlWzorzec {

	public void ObslugaInserta(String sql) throws SQLException {

		Statement zapytania = null;
		java.sql.Connection lacznosc = Polaczenia.lacznosc;

		try {
			lacznosc.setAutoCommit(false);
			zapytania = lacznosc.createStatement();
			zapytania.execute(sql);
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

	public DefaultTableModel SelectDajSqlZwrocModelTabeli(String sql) throws SQLException {

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

		ResultSetMetaData metaData = rezultatZapytania.getMetaData();
		int numberOfColumns = metaData.getColumnCount();
		Vector columnNames = new Vector();

		for (int column = 0; column < numberOfColumns; column++) {
			columnNames.addElement(metaData.getColumnLabel(column + 1));
		}

		Vector rows = new Vector();

		while (rezultatZapytania.next()) {
			Vector newRow = new Vector();

			for (int i = 1; i <= numberOfColumns; i++) {
				newRow.addElement(rezultatZapytania.getObject(i));
			}

			rows.addElement(newRow);
		}
		rezultatZapytania.close();
		zapytania.close();
		return new DefaultTableModel(rows, columnNames);
	}

}