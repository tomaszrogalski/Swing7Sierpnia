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

			Label labelNumer = new Label("NUMER");
			Label labelKlient = new Label("KLIENT");
			Label labelPozycja = new Label("POZYCJA");

			JButton cofnij = new JButton("COFNIJ");
			
			add(labelNumer);
			add(labelKlient);
			add(labelPozycja);
			add(cofnij);

			cofnij.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {		
					try {
						new ListaFaktur("LISTA FAKTUR");
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					DodajFaktureControl.WyczyscDaneUzyteDoDodaniaFaktury();
					dispose();
				}

			});
		}
	}

	class PrawaStronaOkienka extends JPanel {

		PrawaStronaOkienka() {
			setLayout(new GridLayout(4, 1));

			TextField textNumer = new TextField();

			JButton wybierzKlienta = new JButton("WYBIERZ KLIENTA");

			JButton wybierzPozycje = new JButton("WYBIERZ POZYCJE");

			JButton dodajFakture = new JButton("DODAJ");

			add(textNumer);
			add(wybierzKlienta);
			add(wybierzPozycje);
			add(dodajFakture);

			wybierzKlienta.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						new ListaKlientow("LISTA KLIENTOW");
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			});

			wybierzPozycje.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						new DodajPozycje("DODAJ POZYCJE");
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					for (Produkt product : DodajFaktureControl.getListaProduktow()) {
						DodajPozycje.dodajProduktDoTextAreaPozycja(product);
					}
				}
				
			});

			dodajFakture.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					try {
						if (DodajFaktureControl.walidacjaCzyJestDodanyJakisKlient()) {
							if (DodajFaktureControl.walidacjaCzySaDodaneJakiesPozycje()) {
								if (DodajFaktureControl.walidacjaCzyNumerJestUnikalny(textNumer)) {

									try {
										System.out.println("1");
										new DodajFaktureControl().StorzInsertFaktura(textNumer);
									} catch (SQLException e2) {

										e2.printStackTrace();
									}

									try {
										new ListaFaktur("LISTA FAKTUR");
									} catch (SQLException e1) {
										e1.printStackTrace();
									}
									DodajFaktureControl.WyczyscDaneUzyteDoDodaniaFaktury();
									dispose();

								} else {
									new DodajFaktureAlertOkienkoObslugaWalidacjiNumeru("UWAGA");
								}
							} else {
								new DodajFaktureAlertOkienkoObslugaWalidacjiPozycji("UWAGA");
							}
						} else {
							new DodajFaktureAlertOkienkoObslugaWalidacjiKlienta("UWAGA");
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

class DodajFaktureAlertOkienkoObslugaWalidacjiNumeru extends WzorzecAlertOkienko {

	public DodajFaktureAlertOkienkoObslugaWalidacjiNumeru(String title) {
		super(title);
		getArena().append("FAKTURA O TAKIM NUMERU ISTNIEJEJ \n LUB NIE PODALES NUMERU!");

	}
}

class DodajFaktureAlertOkienkoObslugaWalidacjiPozycji extends WzorzecAlertOkienko {

	public DodajFaktureAlertOkienkoObslugaWalidacjiPozycji(String title) {
		super(title);
		getArena().append("MUSI BYC DODANA MINIMUM 1 POZYCJA!");
	}
}

class DodajFaktureAlertOkienkoObslugaWalidacjiKlienta extends WzorzecAlertOkienko {

	public DodajFaktureAlertOkienkoObslugaWalidacjiKlienta(String title) {
		super(title);
		getArena().append("MUSI BYC DODANY KLIENT!");
	}
}