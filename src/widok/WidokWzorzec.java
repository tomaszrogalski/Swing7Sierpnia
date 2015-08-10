package widok;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

import inne.Polaczenia;

public class WidokWzorzec extends JFrame {

	public WidokWzorzec() {

		setSize(400, 400);
		setLocation(100, 100);
		setVisible(true);
		setResizable(false);
	}
}

class WzorzecAlertOkienko extends WidokWzorzec {
	JTextArea Arena = new JTextArea();

	public JTextArea getArena() {
		return Arena;
	}

	public WzorzecAlertOkienko(String title) {

		setTitle(title);
		setSize(200, 100);

		Arena.setEditable(false);
		JButton ok = new JButton();
		ok.setText("OK");

		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		add(Arena, BorderLayout.CENTER);
		add(ok, BorderLayout.SOUTH);
	}
}