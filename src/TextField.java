
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TextField extends JPanel {
	public TextField() {
		setLayout(null);
		JTextField nameField = new JTextField();
		JLabel label = new JLabel("Enter player's name:");
		nameField.setBounds(50, 100, 200, 30);
		label.setBounds(50, 50, 200, 30);
		add(nameField);
		add(label);
		nameField.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(ChickPanel.name == true) {
					ChickPanel.player = nameField.getText();
					ChickPanel.name = false;
					Menu.menu = false;
				}				
			}
		});
	}
	
	@Override
	public Dimension getPreferredSize() {
		// TODO Auto-generated method stub
		return new Dimension(400,400);
	}
}
