import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class LeerPrograma {

	
	/** 
	 * Metodo que devuelve un string de lo leido
	 * @return
	 */
	public static String txtRead() {
		
		File archivo = null;
		
		try {
			archivo = new File(getPath());
		}catch(Exception ec) {
			return "";
		}
		
		
		FileReader fr;
		BufferedReader br;
		
		String leido = "";
		try {
			
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);
			
			String linea = "";
			
			while((linea = br.readLine()) != null) {
				leido = leido + linea;
			}
			
			
			
			br.close();
			fr.close();
			
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Ha sucedido un error leyendo el archivo " + e);
		}
		
		return leido;
	}
	

	/**
	 * Funcion para buscar el path de el archivo del codigo
	 * @return
	 */
	private static String getPath()  {
	
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filtroImagen =new FileNameExtensionFilter("*.TXT", "txt");
		chooser.setFileFilter(filtroImagen);
		File f = null;
 	
		try {
			f = new File(new File(".").getCanonicalPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		String path = "";
	
		try {
			chooser.setCurrentDirectory(f);
			chooser.setCurrentDirectory(null);
			chooser.showOpenDialog(null);
    
			path = chooser.getSelectedFile().toString();
		}catch(Exception e) {}
		
    return path;
	}
}
