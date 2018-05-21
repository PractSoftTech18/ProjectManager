import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import project.*;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import java.awt.Color;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import java.awt.GridLayout;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 * @author Lydia Grillenberger, Julia Hofer and Lukas Schiefermueller
 * @version 1.0
 */
public class ProjectManager {

	private JFrame frame;
	private JTable table;

	private ArrayList<Project> projects;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProjectManager window = new ProjectManager();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ProjectManager() {
		projects = new ArrayList<>(10);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		tabbedPane.setBounds(0, 0, frame.getWidth(), frame.getHeight());
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("New tab", null, panel, null);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0};
		gbl_panel.rowHeights = new int[]{0, 0};
		gbl_panel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		//JLabel lblOurLabel = new JLabel("l");
		//scrollPane.add(lblOurLabel);
		
		
		JScrollPane scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 0;
		gbc_scrollPane_1.gridy = 0;
		panel.add(scrollPane_1, gbc_scrollPane_1);
		
		System.out.println(projects.size());
		for (int i = 0; i < 10; i++) {
			JTextPane p = new JTextPane();
			p.setText("Test" + Integer.toString(i));
			scrollPane_1.add(p);
		}
	}

}
