package widok;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import control.DodajFaktureControl;
import inne.Produkt;

public class DodajPozycje extends WidokWzorzec {

	static JTextArea Arena = null;

	class Arena extends JPanel {
		public Arena() {
			Arena = new JTextArea();
			Arena.setEditable(false);
			Arena.setForeground(null);
			add(Arena);
		}
	}

	class KlawiszDodaj extends JPanel {

		KlawiszDodaj() {
			setLayout(new GridLayout(1, 2));

			JButton dodaj = new JButton("DODAJ");

			JButton cofnij = new JButton("COFNIJ");

			dodaj.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					if (DodajFaktureControl.walidacjaCzyJestDodanyJakisProdukt()) {

						DodajFaktureControl.dodajPozycje();
						dispose();
					} else {
						new DodajPozycjeAlertOkienkoObslugaWalidacjiCzyDodanoProdukt("UWAGA");
					}
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

			setLayout(new BorderLayout());

			this.add(new Arena(),BorderLayout.WEST);
			this.add(new ListaProduktow(),BorderLayout.EAST);
		}
	}

	public DodajPozycje(String title) throws SQLException {
		setTitle(title);

		this.add(new GoraOkna(), BorderLayout.CENTER);

		this.add(new KlawiszDodaj(), BorderLayout.SOUTH);
		
		setSize(600, 500);

	}

	public static void dodajProduktDoTextAreaPozycja(Produkt produkt) {
		Arena.append(produkt.getNazwa() + "\n");
	}
}

class DodajPozycjeAlertOkienkoObslugaWalidacjiCzyDodanoProdukt extends WzorzecAlertOkienko {

	public DodajPozycjeAlertOkienkoObslugaWalidacjiCzyDodanoProdukt(String title) {
		super(title);
		getArena().append("MUSI BYC DODANY \n MINIMUM 1 PRODUKT!");
	}
}