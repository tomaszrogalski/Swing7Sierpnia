package widok;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

import control.DodajFaktureControl;
import control.ListaProduktowControl;

public class ListaProduktow extends JPanel {

	TextField textId;

	public TextField getTextId() {
		return textId;
	}

	class Tabela extends JPanel {

		Tabela() throws SQLException {

			DefaultTableModel model = new ListaProduktowControl().StworzSelectProduktyIZwrocModel();

			JTable table = new JTable(model) {

				public boolean isCellEditable(int row, int col) {
					return false;
				}
			};

			table.addMouseListener(new java.awt.event.MouseAdapter() {
				@Override
				public void mouseClicked(java.awt.event.MouseEvent evt) {
					if (evt.getClickCount() == 2) {
						Integer row = table.rowAtPoint(evt.getPoint()) + 1;
						try {
							DodajFaktureControl.DodajProduktDoListyProduktow(
									new ListaProduktowControl().StworzSelectProduktIDodajGoDoKlasyProdukt(row));
						} catch (SQLException e) {
							e.printStackTrace();
						}
						DodajPozycje.dodajProduktDoTextAreaPozycja(row);
					}
				}
			});
			this.add(new JScrollPane(table));
		}
	}

	public ListaProduktow() throws SQLException {

		this.add(new Tabela());
	}
}