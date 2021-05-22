package pack1;


import java.awt.Point;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;

public class LoadingScreen extends JFrame {
	private JPanel contentPane;

	public LoadingScreen() {
		
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 603, 487);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel spinHolder = new JLabel("");
		ImageIcon icon1 = new ImageIcon(this.getClass().getResource("/tenor.gif"));
		spinHolder.setIcon(icon1);

		spinHolder.setBounds(169, 87, 255, 286);
		contentPane.add(spinHolder);
		setLocation(new Point(450, 150));
	}
}
