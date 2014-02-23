package me.dokollari.college.manager.swings;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;


 /** @author Rizart Dokollari
 * @since November, 2012
 */

public class MainMenu_AboutBoxPanel extends JPanel {
    JLabel labelTitle = new JLabel();
    JLabel labelAuthor = new JLabel();
    JLabel labelCopyright = new JLabel();
    GridBagLayout layoutMain = new GridBagLayout();
    Border border = BorderFactory.createEtchedBorder();

    public MainMenu_AboutBoxPanel() {
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        this.setLayout( layoutMain );
        this.setBorder( border );
        labelTitle.setText( "Title: College App" );
        labelAuthor.setText( "Author: Rizart Dokollari" );
        labelCopyright.setText( "Copyright: Open Source" );
        this.add( labelTitle, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 15, 0, 15), 0, 0) );
        this.add( labelAuthor, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 15, 0, 15), 0, 0) );
        this.add( labelCopyright, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 15, 0, 15), 0, 0) );
    }
}
