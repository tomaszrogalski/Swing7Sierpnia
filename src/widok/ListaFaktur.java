package widok;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import control.ListaFakturControl;
import inne.Polaczenia;

public class ListaFaktur extends WidokWzorzec {

	class Tabela extends JPanel {

		Tabela() throws SQLException {

			DefaultTableModel model = new ListaFakturControl().SelectKlientZwrocModel();

			JTable table = new JTable(model);

			this.add(new JScrollPane(table));
		}
	}

	class Dol extends JPanel {

		Dol() {
			setLayout(new GridLayout(1, 1));

			JButton wybierz = new JButton();
			wybierz.setText("DODAJ FAKTURE");

			wybierz.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					new DodajFakture("DodajFakture");
					dispose();
				}
			});
			add(wybierz);
		}
	}

	public ListaFaktur(String title) throws SQLException {
		setTitle(title);

		this.add(new Tabela(), BorderLayout.CENTER);

		this.add(new Dol(), BorderLayout.SOUTH);

		this.pack();

		addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent we) {
				
				Polaczenia.ZamknijPolaczenie();

				System.exit(0);
			}
		});
	}
}
