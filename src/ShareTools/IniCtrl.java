package ShareTools;





import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import javax.swing.JOptionPane;

public class IniCtrl {
	private static Properties p;
	private String inifile;
	private MessageBox msgBox;
	private String s_errTitle = "INI Control Error";
	private boolean showError;

	public IniCtrl(String inipath, Properties defaultpro,boolean showError) throws IOException {
		msgBox = new MessageBox();
		this.showError = showError;
		if (!Files.isReadable(Paths.get(inipath))) {
			try {
				Files.createFile(Paths.get(inipath));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				if (showError)
					msgBox.showMessage(s_errTitle , e.getMessage(), JOptionPane.ERROR_MESSAGE);
				throw e;
			}
			p = new Properties(defaultpro);
			p.putAll(defaultpro);
		} else {
			p = new Properties(defaultpro);
		}
		try {
			p.load(new FileInputStream(inipath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if (showError)
				msgBox.showMessage(s_errTitle , e.getMessage(), JOptionPane.ERROR_MESSAGE);
			throw e;
		}
		inifile = inipath;
	}

	public void save() throws IOException {
			try {
				p.store(new FileOutputStream(inifile), null);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				if (showError)
					msgBox.showMessage(s_errTitle , e.getMessage(), JOptionPane.ERROR_MESSAGE);
				throw e;
			}

	}

	@Override
	protected void finalize() throws Throwable  {
		// TODO Auto-generated method stub
		save();
		try {
			super.finalize();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if (showError)
				msgBox.showMessage(s_errTitle , e.getMessage(), JOptionPane.ERROR_MESSAGE);
			throw e;
		}
		
	}

	public void defaultIniSetting(String[] key, String[] value) throws IOException {
		for (int i = 0; i < key.length; i++) {
			p.setProperty(key[i], value[i]);
		}
		save();
	}

	public String getIniSetting(String key) {
		return p.getProperty(key);
	}

	public void setIniSetting(String key, String value) throws IOException {
		p.setProperty(key, value);
		save();

	}
}
