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
					Integer row = table.rowAtPoint(evt.getPoint()) + 1;
					getTextId().setText(row.toString());
				}

			});

			this.add(new JScrollPane(table));
		}
	}

	class Klawisze extends JPanel {

		Klawisze() {
			setLayout(new GridLayout(1, 2));

			textId = new TextField();
			textId.setText("ID");

			JButton wybierz = new JButton();
			wybierz.setText("Wybierz produkt");
			wybierz.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						DodajFaktureControl.DodajProduktDoListyProduktow(new ListaProduktowControl()
								.StworzSelectProduktIDodajGoDoKlasyProdukt(textId.getText()));
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					DodajPozycje.dodajProduktDoTextAreaPozycja(textId.getText());
				}
			});

			add(textId);
			add(wybierz);

		}
	}

	public ListaProduktow() throws SQLException {

		this.add(new Tabela(), BorderLayout.CENTER);

		setSize(300, 300);

		this.add(new Klawisze(), BorderLayout.SOUTH);
	}
}