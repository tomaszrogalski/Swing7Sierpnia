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
import control.ListaKlientowControl;

public class ListaKlientow extends WidokWzorzec {

	TextField textId;

	public TextField getTextId() {
		return textId;
	}

	class Tabela extends JPanel {

		Tabela() throws SQLException {

			DefaultTableModel model = new ListaKlientowControl().SelectKlientZwrocModel();

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
						DodajFaktureControl.wybierzKlienta(row);
						dispose();
					}
				}
			});
			this.add(new JScrollPane(table));
		}
	}

	class Dol extends JPanel {

		Dol() {
			setLayout(new GridLayout(1, 2));


			JButton cofnij = new JButton("COFNIJ");

			JButton dodaj = new JButton("DODAJ NOWEGO KLIENTA");
	
			dodaj.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					new DodajKlienta("DODAJ KLIENTA");
					dispose();
				}
			});
			cofnij.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					new DodajFakture("DODAJ FAKTURE");

					dispose();
				}
			});
			add(cofnij);
			add(dodaj);
		}
	}
	public ListaKlientow(String title) throws SQLException {
		setTitle(title);

		this.add(new Tabela(), BorderLayout.CENTER);

		this.add(new Dol(), BorderLayout.SOUTH);

		this.pack();
	}
}

