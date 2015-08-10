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
					Integer row = table.rowAtPoint(evt.getPoint()) + 1;
					// Bo od zera numeruje a w bazie od 1
					getTextId().setText(row.toString());
				}

			});

			this.add(new JScrollPane(table));
		}
	}

	class Dol extends JPanel {

		Dol() {
			setLayout(new GridLayout(1, 4));

			textId = new TextField();
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
					try {
						if (ListaKlientowControl.walidacjaCzyKlientOPodanymIdIstnieje(textId)) {
							DodajFaktureControl.wybierzKlienta(textId.getText());
							dispose();
						} else {
							new ListaKlientowAlertOkienko("UWAGA");
						}
					} catch (SQLException e1) {

						e1.printStackTrace();
					}
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
			add(cofnij);
			add(textId);
			add(wybierz);
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
class ListaKlientowAlertOkienko extends WzorzecAlertOkienko {

	public ListaKlientowAlertOkienko(String title) {
		super(title);
		getArena().append("Nie ma klienta o takim id \n sprobuj jeszcze raz");
	}
}