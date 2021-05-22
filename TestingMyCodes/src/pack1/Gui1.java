package pack1;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import java.awt.Point;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import java.lang.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Gui1 extends JFrame {

	private JPanel contentPane;
	private JTextField URLText;
	private JTextField depthText;
	private JTextField threadText;

	private boolean clicked = false;

	public boolean getClick() {
		return clicked;
	}

	public Gui1() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 796, 357);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("URL checker");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setLocation(new Point(200, 2000));
		lblNewLabel.setBounds(318, 22, 114, 55);
		contentPane.add(lblNewLabel);

		URLText = new JTextField();
		URLText.setBounds(51, 99, 680, 26);
		contentPane.add(URLText);
		URLText.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Enter your URL:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(51, 51, 130, 36);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Enter the Depth:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(51, 141, 111, 18);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Enter the Number of Threads:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(51, 163, 200, 36);
		contentPane.add(lblNewLabel_3);

		threadText = new JTextField();
		threadText.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (Character.isDigit(e.getKeyChar()) || e.getKeyChar() == '+'
						|| Character.isISOControl(e.getKeyChar())) {
					threadText.setEditable(true);
				} else {
					threadText.setEditable(false);
				}
			}
		});
		threadText.setBounds(250, 176, 96, 18);
		contentPane.add(threadText);
		threadText.setColumns(10);

		JLabel error = new JLabel("");
		error.setForeground(new Color(220, 20, 60));
		error.setFont(new Font("Tahoma", Font.PLAIN, 13));
		error.setBounds(51, 279, 363, 26);
		contentPane.add(error);
		setLocation(new Point(380, 100));
		
		JLabel message = new JLabel("Max number of threads : " + MyThread.getMaxThreadCount());
		message.setForeground(new Color(0, 128, 128));
		message.setBounds(369, 174, 247, 18);
		contentPane.add(message);
		
		depthText = new JTextField();
		depthText.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (Character.isDigit(e.getKeyChar()) || e.getKeyChar() == '+'
						|| Character.isISOControl(e.getKeyChar())) {
					depthText.setEditable(true);
				} else {
					depthText.setEditable(false);
				}
			}
		});
		depthText.setBounds(172, 140, 96, 19);
		contentPane.add(depthText);
		depthText.setColumns(10);

		JButton btnNewButton = new JButton("check");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int thread = 0;
				if (!threadText.getText().equals("") && Integer.parseInt(threadText.getText()) > 0
						&& Integer.parseInt(threadText.getText()) <= MyThread.getMaxThreadCount())
					thread = Integer.parseInt(threadText.getText());
				error.setText("Invalid NumberOfThread!!");
				
				if (URLText.getText().equals("") || depthText.getText().equals("") || threadText.getText().equals("")) {
					error.setText("Please fill all the data!!");
				} else if (LinkValidator.verifyUrl(URLText.getText(), " ") && thread != 0) {
					Test1.setUrl(URLText.getText());
					Test1.setDepth(Integer.parseInt(depthText.getText()));
					Test1.setNoOfThreads(thread);
					error.setText("");
					clicked = true;
				} else {
					error.setText("Invalid!!");
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBounds(318, 231, 96, 36);
		contentPane.add(btnNewButton);
		
	}
}
