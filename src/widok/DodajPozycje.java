package widok;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import control.DodajFaktureControl;

public class DodajPozycje extends WidokWzorzec {

	static JTextArea Arena = null;

	class Arena extends JPanel {
		public Arena() {
			Arena = new JTextArea();
			add(Arena);
		}
	}

	class KlawiszDodaj extends JPanel {

		KlawiszDodaj() {
			setLayout(new GridLayout(1, 2));

			JButton dodaj = new JButton();
			dodaj.setText("Dodaj");

			JButton cofnij = new JButton();
			cofnij.setText("Cofnij");

			dodaj.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					DodajFaktureControl.dodajPozycje();
					dispose();
				}
			});
			cofnij.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			
			add(cofnij);
			add(dodaj);
		}
	}

	class GoraOkna extends JPanel {

		public GoraOkna() throws SQLException {

			setLayout(new GridLayout(1, 2));

			this.add(new Arena());
			this.add(new ListaProduktow());
		}
	}

	public DodajPozycje(String title) throws SQLException {
		setTitle(title);

		this.add(new GoraOkna(), BorderLayout.CENTER);

		setSize(1000, 600);

		this.add(new KlawiszDodaj(), BorderLayout.SOUTH);
	}

	public static void dodajProduktDoTextAreaPozycja(Integer row) {
		Arena.append(row + "\n");
	}
}