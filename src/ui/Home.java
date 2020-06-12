package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

import demineur.demin.Demineur;
import net.miginfocom.swing.MigLayout;
import paint.DrawFrame;
import shoot.duckhunt.GUI.MainFrame;

public class Home extends JFrame  {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Home() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(250, 150, 805, 513);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(255, 255, 255));
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(null);
		setContentPane(contentPane);
		setTitle("The 90's Generation");
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(135, 206, 250));
		panel.setLayout(new MigLayout("", "[][351px][][][][][][][]", "[50px][][][][][]"));
		
		JLabel lblNewLabel_1 = new JLabel("The 90's Generation");
		lblNewLabel_1.setFont(new Font("Comic Sans MS", Font.BOLD, 35));
		panel.add(lblNewLabel_1, "cell 2 1,alignx center,aligny center");
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel_1, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 793, Short.MAX_VALUE)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 793, Short.MAX_VALUE))
					.addGap(0))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 347, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(84, Short.MAX_VALUE))
		);
		
		JButton button = new JButton("Paint");
		button.setFocusable(false);
		button.setIcon(new ImageIcon(Home.class.getResource("/ui/images/paint.jpg")));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DrawFrame frame = new DrawFrame();
				frame.setTitle("Draw");
				frame.setBackground( new Color(39,174,96));
				frame.pack();

				// put the frame in the middle of the display
				Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
				frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);
				frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
				frame.setDefaultCloseOperation(HIDE_ON_CLOSE);
				frame.setVisible(true);
			}
		});
		button.setVerticalAlignment(SwingConstants.CENTER);
		button.setHorizontalAlignment(SwingConstants.LEADING);
		button.setForeground(Color.BLACK);
		button.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		button.setBackground(Color.WHITE);
		
		JButton button_1 = new JButton("Hunt");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainFrame frame = new MainFrame();
			       frame.setVisible(true);
			}
		});
		button_1.setIcon(new ImageIcon(Home.class.getResource("/ui/images/duckhunt.jpg")));
		button_1.setFocusable(false);
		button_1.setHorizontalAlignment(SwingConstants.LEADING);
		button_1.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		button_1.setBackground(Color.WHITE);
		
		JButton button_2 = new JButton("Minesweeper");
		button_2.setFocusable(false);
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				    new Demineur(16,30,99,3);//hop, on lance le jeux en expert
				  
			}
		});
		button_2.setIcon(new ImageIcon(Home.class.getResource("/ui/images/mine.png")));
		button_2.setHorizontalAlignment(SwingConstants.LEADING);
		button_2.setVerticalAlignment(SwingConstants.CENTER);
		button_2.setEnabled(true);
		button_2.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		button_2.setBackground(Color.WHITE);
		
		JButton button_3 = new JButton("About !");
		button_3.setFocusable(false);
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				About frame = new About();
				frame.setVisible(true);
			}
		});
		button_3.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		button_3.setIcon(new ImageIcon(Home.class.getResource("/ui/images/about.png")));
		button_3.setBackground(Color.WHITE);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Home.class.getResource("/ui/images/90genbl.jpg")));
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(22)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(button, GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)
						.addComponent(button_2, GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE))
					.addGap(63)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(button_3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(button_1, GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE))
					.addGap(64)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
					.addGap(9))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(21)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(button, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE)
								.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
								.addComponent(button_3, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE)
								.addComponent(button_2, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(113)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(8, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		contentPane.setLayout(gl_contentPane);
		
	}


}
