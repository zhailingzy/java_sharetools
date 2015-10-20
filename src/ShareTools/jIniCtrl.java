package ShareTools;





import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class jIniCtrl {
	private static Properties p;
	private String inifile;

	public jIniCtrl(String inipath, Properties defaultpro) throws FileNotFoundException, IOException {

		if (!Files.isReadable(Paths.get(inipath))) {
			Files.createFile(Paths.get(inipath));
			p = new Properties(defaultpro);
			p.putAll(defaultpro);
		} else {
			p = new Properties(defaultpro);
		}
		p.load(new FileInputStream(inipath));
		inifile = inipath;
	}

	public void save() {
		try {
			p.store(new FileOutputStream(inifile), null);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
		save();
	}

	public void defaultIniSetting(String[] key, String[] value) {
		for (int i = 0; i < key.length; i++) {
			p.setProperty(key[i], value[i]);
		}
		save();
	}

	public String getIniSetting(String key) throws FileNotFoundException, IOException {
		return p.getProperty(key);
	}

	public void setIniSetting(String key, String value) {
		p.setProperty(key, value);
		save();

	}
}
