package widok;

import java.awt.GridLayout;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JPanel;

import control.DodajKlientaControl;

public class DodajKlienta extends WidokWzorzec {

	class LewaStronaOkienka extends JPanel {

		LewaStronaOkienka() {
			setLayout(new GridLayout(8, 1));

			Label labelNip = new Label();
			Label labelKod = new Label();
			Label labelMiejscowosc = new Label();
			Label labelNrDomu = new Label();
			Label labelUlica = new Label();
			Label labelImie = new Label();
			Label labelNazwisko = new Label();

			labelNip.setText("Nip");
			labelKod.setText("Kod");
			labelMiejscowosc.setText("Miejscowosc");
			labelNrDomu.setText("NrDomu");
			labelUlica.setText("Ulica");
			labelImie.setText("Imie");
			labelNazwisko.setText("Nazwisko");

			JButton cofnij = new JButton();
			cofnij.setText("Cofnij");

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
						new ListaKlientow("ListaKlientow");
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

			JButton dodajKlienta = new JButton();
			dodajKlienta.setText("Dodaj");

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
						new ListaKlientow("ListaKlientow");
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