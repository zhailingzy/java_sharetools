package shareTools;

import java.awt.Container;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * 
 */
/**
 * @author zhailing
 *
 */
public class MessageBox {
	JFrame mainwin;

	/**
	 * 
	 */
	public MessageBox() {
		// TODO Auto-generated constructor stub
		JFrame.setDefaultLookAndFeelDecorated(true);
		JDialog.setDefaultLookAndFeelDecorated(true);
		mainwin = new JFrame("test joption pane");
		mainwin.setSize(400, 300);
		mainwin.setLocationRelativeTo(null);
		Container cp = mainwin.getContentPane();
		cp.setLayout(null);
		mainwin.setVisible(false);
	}

	public void showMessage(String title, String message) {
		// JOptionPane.showMessageDialog(mainwin, message, title,
		// JOptionPane.PLAIN_MESSAGE, new ImageIcon("icon.jpg"));//full sample
		JOptionPane.showMessageDialog(mainwin, message, title, JOptionPane.INFORMATION_MESSAGE);
	}

	public void showMessage(String title, String message, int type) {
		JOptionPane.showMessageDialog(mainwin, message, title, type);
	}

	public Object showQuestion(String title, String message, int type, Object[] selectionValues,
			Object initialSelectionValue) {
		return JOptionPane.showInputDialog(mainwin, message, title, type, null, selectionValues, initialSelectionValue);
	}

	public String showQuestion(Object message) {
		return JOptionPane.showInputDialog(mainwin, message);
	}
}
