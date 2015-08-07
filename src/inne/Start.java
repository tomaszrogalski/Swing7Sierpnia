package inne;

import java.sql.SQLException;

import widok.ListaFaktur;

public class Start {

	public static void main(String[] args) throws SQLException {

		Polaczenia.ZaladujSterownik();
		
		Polaczenia.OtworzPolaczenie();
		
		new ListaFaktur("ListaFaktur");
	}
}
