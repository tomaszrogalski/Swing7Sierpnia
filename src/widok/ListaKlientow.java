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
import control.ListaKlientowControl;

public class ListaKlientow extends WidokWzorzec {

	class Tabela extends JPanel {

		Tabela() throws SQLException {

			DefaultTableModel model = new ListaKlientowControl().SelectKlientZwrocModel();

			JTable table = new JTable(model);

			this.add(new JScrollPane(table));
		}
	}

	class Dol extends JPanel {

		Dol() {
			setLayout(new GridLayout(1, 4));

			TextField textId = new TextField();
			textId.setText("ID");

			JButton cofnij = new JButton();
			cofnij.setText("Cofnij");

			JButton wybierz = new JButton();
			wybierz.setText("Wybierz klienta");

			JButton dodaj = new JButton();
			dodaj.setText("Dodaj nowego klienta");

			wybierz.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					DodajFaktureControl.wybierzKlienta(textId.getText());
					dispose();
				}
			});
			dodaj.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					new DodajKlienta("DodajKlienta");
					dispose();
				}
			});
			cofnij.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					new DodajFakture("DodajFakture");

					dispose();
				}
			});

			add(textId);
			add(wybierz);
			add(dodaj);
			add(cofnij);
		}
	}

	public ListaKlientow(String title) throws SQLException {
		setTitle(title);

		this.add(new Tabela(), BorderLayout.CENTER);

		this.add(new Dol(), BorderLayout.SOUTH);

		this.pack();
	}
}
