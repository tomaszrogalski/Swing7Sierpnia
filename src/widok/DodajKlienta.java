package widok;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import control.DodajKlientaControl;

public class DodajKlienta extends WidokWzorzec {

	class LewaStronaOkienka extends JPanel {

		LewaStronaOkienka() {
			setLayout(new GridLayout(8, 1));

			JLabel labelNip = new JLabel("NIP");
			JLabel labelKod = new JLabel("KOD POCZTOWY");
			JLabel labelMiejscowosc = new JLabel("MIEJSCOWOSC");
			JLabel labelNrDomu = new JLabel("NR DOMU");
			JLabel labelUlica = new JLabel("ULICA");
			JLabel labelImie = new JLabel("IMIE");
			JLabel labelNazwisko = new JLabel("NAZWISKO");

			JButton cofnij = new JButton("COFNIJ");
		
			add(labelNip);
			add(labelImie);
			add(labelNazwisko);
			add(labelKod);
			add(labelMiejscowosc);
			add(labelNrDomu);
			add(labelUlica);
			add(cofnij);

			cofnij.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					try {
						new ListaKlientow("LISTA KLIENTOW");
					} catch (SQLException e1) {

						e1.printStackTrace();
					}
					dispose();
				}
			});
		}
	}

	class PrawaStronaOkienka extends JPanel {

		PrawaStronaOkienka() {
			setLayout(new GridLayout(8, 1));

			TextField textNip = new TextField();
			TextField textKod = new TextField();
			TextField textMiejscowosc = new TextField();
			TextField textNrDomu = new TextField();
			TextField textUlica = new TextField();
			TextField textImie = new TextField();
			TextField textNazwisko = new TextField();

			JButton dodajKlienta = new JButton("DODAJ");

			add(textNip);
			add(textImie);
			add(textNazwisko);
			add(textKod);
			add(textMiejscowosc);
			add(textNrDomu);
			add(textUlica);
			add(dodajKlienta);

			dodajKlienta.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					try {
						new DodajKlientaControl().StworzInsertDoTabeliKlient(textNip, textKod, textMiejscowosc,
								textNrDomu, textUlica, textImie, textNazwisko);
					} catch (SQLException e1) {

						e1.printStackTrace();
					}

					try {
						new ListaKlientow("LISTA KLIENTOW");
					} catch (SQLException e1) {

						e1.printStackTrace();
					}
					dispose();
				}
			});
		}
	}

	public DodajKlienta(String title) {
		setTitle(title);

		setLayout(new GridLayout(1, 2));

		add(new LewaStronaOkienka());
		add(new PrawaStronaOkienka());
	}
}