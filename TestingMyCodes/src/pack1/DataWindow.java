package pack1;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class DataWindow extends JFrame {

	private JPanel contentPane;
	private JTable validTable;
	private JTable inValidTable;

	public void addLink(String val, String inVal) {
		DefaultTableModel model = (DefaultTableModel) validTable.getModel();
		DefaultTableModel model2 = (DefaultTableModel) inValidTable.getModel();
		Object[][] x = new Object[1][3];

		String[] splitString = val.split("\n");
		int i = 0, j = 1, count = 1;
		while (i < splitString.length && j < splitString.length) 
		{
			x[0][0] = count;
			x[0][1] = splitString[i];
			x[0][2] = splitString[j];
			model.addRow(x[0]);
			i += 2;
			j += 2;
			count++;
	}
		

		i = j = 0;
		 count = 1;
		splitString = inVal.split("\n");
		while (i < splitString.length && j < splitString.length) 
		{
			x[0][0] = count;
			x[0][1] = splitString[i];
			x[0][2] = splitString[j];
			model2.addRow(x[0]);
			i += 2;
			j += 2;
			count++;
	}
		}

	public DataWindow() {
		setResizable(false);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 796, 658);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Result");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(324, 29, 68, 41);
		contentPane.add(lblNewLabel);

		JLabel validLinks = new JLabel("New label");
		validLinks.setForeground(new Color(0, 153, 51));
		validLinks.setFont(new Font("Tahoma", Font.PLAIN, 13));
		validLinks.setBounds(46, 94, 165, 22);
		contentPane.add(validLinks);
		validLinks.setText("Number of valid links: " + LinkValidator.getValidNum());

		JLabel invalidLinks = new JLabel("New label");
		invalidLinks.setForeground(new Color(255, 0, 51));
		invalidLinks.setFont(new Font("Tahoma", Font.PLAIN, 13));
		invalidLinks.setBounds(294, 94, 165, 22);
		contentPane.add(invalidLinks);
		invalidLinks.setText("Number of invalid links: " + LinkValidator.getInvalidNum());

		JLabel allLinks = new JLabel("New label");
		allLinks.setForeground(new Color(0, 0, 153));
		allLinks.setFont(new Font("Tahoma", Font.PLAIN, 13));
		allLinks.setBounds(565, 94, 165, 22);
		contentPane.add(allLinks);
		allLinks.setText("Number of all links: " + Test1.noOfAllLinks());

		JLabel time = new JLabel("New label");
		time.setForeground(new Color(0, 0, 0));
		time.setFont(new Font("Tahoma", Font.PLAIN, 13));
		time.setBounds(46, 126, 196, 22);
		contentPane.add(time);
		time.setText("Execution time: " + Test1.time() + " sec");

		JLabel threads = new JLabel("New label");
		threads.setForeground(new Color(0, 0, 0));
		threads.setFont(new Font("Tahoma", Font.PLAIN, 13));
		threads.setBounds(294, 126, 165, 22);
		contentPane.add(threads);
		threads.setText("Number of Threads: " + MyThread.getThreadCount());
		
		JLabel maxThreads = new JLabel("Number of Threads: 0");
		maxThreads.setForeground(Color.BLACK);
		maxThreads.setFont(new Font("Tahoma", Font.PLAIN, 13));
		maxThreads.setBounds(565, 126, 165, 22);
		contentPane.add(maxThreads);
		maxThreads.setText("Max number of Threads: " + MyThread.getMaxThreadCount());

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 163, 762, 220);
		contentPane.add(scrollPane);

		validTable = new JTable();
		validTable.setForeground(new Color(0, 102, 51));
		validTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"No.", "VALID links", "text"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, true, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		validTable.getColumnModel().getColumn(0).setPreferredWidth(30);
		validTable.getColumnModel().getColumn(1).setPreferredWidth(515);
		validTable.getColumnModel().getColumn(2).setPreferredWidth(123);
		scrollPane.setViewportView(validTable);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 393, 762, 220);
		contentPane.add(scrollPane_1);
		
		inValidTable = new JTable();
		inValidTable.setForeground(new Color(204, 0, 0));
		inValidTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"No.", "INVALID links", "text"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, true, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		inValidTable.getColumnModel().getColumn(0).setPreferredWidth(30);
		inValidTable.getColumnModel().getColumn(1).setPreferredWidth(515);
		inValidTable.getColumnModel().getColumn(2).setPreferredWidth(123);
		scrollPane_1.setViewportView(inValidTable);
		
		setLocation(new Point(380, 100));
	}
}
