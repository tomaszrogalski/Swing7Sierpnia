package control;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import widok.WidokWzorzec;

public class ListaFakturControl extends ControlWzorzec {

	public DefaultTableModel SelectKlientZwrocModel() throws SQLException {

		String sql = new String();

		sql = "select swing.faktura.numer as \"NUMER FAKTURY\",swing.produkt.nazwa as \"PRODUKT\"	from swing.faktura left join (swing.pozycja left join swing.produkt on swing.pozycja.id=swing.produkt.pozycja_id) on swing.faktura.id=swing.pozycja.faktura_id Order by swing.faktura.numer ;";

		return SelectDajSqlZwrocModelTabeli(sql);

	}

}
