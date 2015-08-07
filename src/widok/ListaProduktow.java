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

	class Tabela extends JPanel {

		Tabela() throws SQLException {

			DefaultTableModel model = new ListaProduktowControl().StworzSelectProduktyIZwrocModel();

			JTable table = new JTable(model);

			this.add(new JScrollPane(table));
		}
	}

	class Klawisze extends JPanel {

		Klawisze() {
			setLayout(new GridLayout(1, 2));

			TextField textId = new TextField();
			textId.setText("ID");

			JButton wybierz = new JButton();
			wybierz.setText("Wybierz produkt");
			wybierz.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						DodajFaktureControl.DodajProduktDoListyProduktow(new ListaProduktowControl().StworzSelectProduktIDodajGoDoKlasyProdukt(textId.getText()));
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