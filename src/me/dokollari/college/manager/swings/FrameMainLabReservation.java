package me.dokollari.college.manager.swings;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSeparator;

import me.dokollari.college.manager.mvc.Controller;

/**
 * @author Rizart Dokollari
 * @since November, 2012
 */

public class FrameMainLabReservation extends JFrame
{
	private static final long serialVersionUID = -1510868791910636585L;

	private Controller controller;
	private JPanel jPanel2 = new JPanel();
	private JProgressBar jPB_db = new JProgressBar();

	public FrameMainLabReservation() {
		try {
			jPB_db.setStringPainted(true);
			jbInit();

			controller = new Controller();
			controller.retrieveData(jPB_db);
		} catch (Exception e) {
			e.printStackTrace();
			jPB_db.setString(Integer.toString(jPB_db.getValue())
					+ "% Critical Error. Hover for details.");
			jPB_db.setToolTipText(e.getMessage());
		}
	}

	private void jbInit() throws Exception {
		JMenuBar jMB_menuBar = new JMenuBar();
		JLabel jL_imageDeree = new JLabel();
		JMenuItem jMI_menuFileExit = new JMenuItem();
		JMenu jM_menuFile = new JMenu();
		JMenuItem jMI_menuHelpAbout = new JMenuItem();
		JMenu jM_menuHelp = new JMenu();
		JButton jB_roomsLabData = new JButton();
		JButton jB_editCollegeData = new JButton();
		JButton jB_editLabsReservation = new JButton();
		JButton jB_quit = new JButton();
		JSeparator jSeparator1 = new JSeparator();

		this.setJMenuBar(jMB_menuBar);
		this.setResizable(false);
		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(781, 440));
		this.setTitle("Deree - American College of Greece - Lab Reservation Data");

		BufferedImage bi = ImageIO.read(this.getClass().getResource(
				"/img/deree.jpg"));
		// URL url =
		// (this.getClass().getClassLoader().getResource("/img/deree.jpg"));
		ImageIcon ii = new ImageIcon(bi);
		jL_imageDeree.setIcon(ii);

		jM_menuFile.add(jMI_menuFileExit);
		jMB_menuBar.add(jM_menuFile);
		jM_menuHelp.add(jMI_menuHelpAbout);
		jMB_menuBar.add(jM_menuHelp);
		jM_menuFile.setText("File");
		jMI_menuFileExit.setText("Exit");
		jMI_menuFileExit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae) {
				try {
					fileExit_ActionPerformed(ae);
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			}
		});
		jM_menuHelp.setText("Help");
		jMI_menuHelpAbout.setText("About");
		jMI_menuHelpAbout.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae) {
				helpAbout_ActionPerformed(ae);
			}
		});
		jB_roomsLabData.setText("Room Labs Data");
		jB_roomsLabData.setBounds(new Rectangle(15, 150, 190, 35));
		jB_roomsLabData.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				jButton1_actionPerformed(e);
			}
		});
		jB_editCollegeData.setText("Edit College Data");
		jB_editCollegeData.setBounds(new Rectangle(590, 95, 140, 35));
		jB_editCollegeData.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				jButton2_actionPerformed(e);
			}
		});
		jB_editLabsReservation.setText("Edit Lab's Reservation");
		jB_editLabsReservation.setBounds(new Rectangle(15, 95, 190, 35));
		jB_editLabsReservation.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				jButton4_actionPerformed(e);
			}
		});
		jB_quit.setText("Quit");
		jB_quit.setBounds(new Rectangle(305, 260, 170, 35));
		jB_quit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				jButton9_actionPerformed(e);
			}
		});
		jSeparator1.setBounds(new Rectangle(115, 215, 530, 25));
		jPanel2.setBounds(new Rectangle(235, 25, 315, 160));

		jPB_db.setSize(new Dimension(155, 14));
		jPB_db.setBounds(new Rectangle(490, 325, 240, 35));
		jPanel2.add(jL_imageDeree, null);
		this.getContentPane().add(jPanel2, null);
		this.getContentPane().add(jSeparator1, null);
		this.getContentPane().add(jB_quit, null);
		this.getContentPane().add(jB_editLabsReservation, null);
		this.getContentPane().add(jB_editCollegeData, null);
		this.getContentPane().add(jB_roomsLabData, null);
		this.getContentPane().add(jPB_db, null);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setBackground(new Color(110, 237, 228));
		this.setForeground(new Color(0, 214, 214));
	}

	private void jButton2_actionPerformed(ActionEvent e) {
		new me.dokollari.college.manager.swings.DialogFacultyMain(null,
				"Reservation Data", true, controller);
	}

	void fileExit_ActionPerformed(ActionEvent e) throws SQLException {
		// controller.writeData();
		System.exit(0);
	}

	void helpAbout_ActionPerformed(ActionEvent e) {
		JOptionPane.showMessageDialog(this, new MainMenu_AboutBoxPanel(),
				"About", JOptionPane.PLAIN_MESSAGE);
	}

	private void jButton1_actionPerformed(ActionEvent e) {
		new me.dokollari.college.manager.swings.DialogRoomData(null,
				"Edit Room Labs", true, controller);
	}

	private void jButton4_actionPerformed(ActionEvent e) { // button to reserve
																				// lab
		new me.dokollari.college.manager.swings.DialogRoomReservationsData(null,
				"Reserve Lab", true, controller);
	}

	private void jButton9_actionPerformed(ActionEvent e) {
		System.exit(0);
	}
}
