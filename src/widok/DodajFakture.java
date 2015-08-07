package widok;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import control.DodajFaktureControl;
import inne.Produkt;

public class DodajFakture extends WidokWzorzec {

	class LewaStronaOkienka extends JPanel {

		LewaStronaOkienka() {
			setLayout(new GridLayout(4, 1));

			Label labelNumer = new Label();
			Label labelKlient = new Label();
			Label labelPozycja = new Label();

			labelNumer.setText("Numer");
			labelKlient.setText("Klient");
			labelPozycja.setText("Poycja");

			add(labelNumer);
			add(labelKlient);
			add(labelPozycja);

		}
	}

	class PrawaStronaOkienka extends JPanel {

		PrawaStronaOkienka() {
			setLayout(new GridLayout(4, 1));

			TextField textNumer = new TextField();

			JButton wybierzKlienta = new JButton();
			wybierzKlienta.setText("Wybierz Klienta");

			JButton wybierzPozycje = new JButton();
			wybierzPozycje.setText("Wybierz Pozycje");

			JButton dodajFakture = new JButton();
			dodajFakture.setText("Dodaj");

			add(textNumer);
			add(wybierzKlienta);
			add(wybierzPozycje);
			add(dodajFakture);

			wybierzKlienta.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						new ListaKlientow("ListaKlientow");
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			});

			wybierzPozycje.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						new DodajPozycje("DodajPozycje");
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			});

			dodajFakture.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					try {
						if (DodajFaktureControl.walidacjaCzyNumerJestUnikalny(textNumer)) {
							try {
								System.out.println("1");
								new DodajFaktureControl().StorzInsertFaktura(textNumer);
							} catch (SQLException e2) {

								e2.printStackTrace();
							}

							try {
								new ListaFaktur("ListaFaktur");
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
							DodajFaktureControl.WyczyscDaneUzyteDoDodaniaFaktury();
							dispose();
						}
						else{
							new DodajFaktureOkienko("UWAGA");
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}					
				}
			});
		}
	}

	public DodajFakture(String title) {
		setTitle(title);

		setLayout(new GridLayout(1, 2));
		add(new LewaStronaOkienka());
		add(new PrawaStronaOkienka());
	}
}

class DodajFaktureOkienko extends WidokWzorzec {

	public DodajFaktureOkienko(String title) {

		setTitle(title);
		setSize(200, 100);

		JTextArea Arena = new JTextArea();

		JButton ok = new JButton();
		ok.setText("OK");

		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		Arena.append("Faktura o takim nr juz istnieje");
		
		add(Arena,BorderLayout.CENTER);
		add(ok,BorderLayout.SOUTH);
	}
}