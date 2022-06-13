import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author Sesha
 */
public class OpenFile {
    String namaDoc = "";
    String sumberDoc = "";
    
    public OpenFile() {
        JFileChooser fileChooser = new JFileChooser();
        FileFilter filter = new ExtensionFileFilter("doc dan docx", new String[] {"doc", "docx"});
        
        fileChooser.setFileFilter(filter);
        
        int status = fileChooser.showOpenDialog(null);
        
        if(status == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            namaDoc = selectedFile.getName();
            sumberDoc = selectedFile.getAbsolutePath();
            
            if(sumberDoc.endsWith("doc")){
                //System.out.println("File Doc");
            }
            else if(sumberDoc.endsWith("docx")) {
                //System.out.println("File Docx");
            }
            else {
                JOptionPane.showMessageDialog(null, "Dokumen harus berupa dokumen Microsoft Office Word (.doc/.docx)","Peringatan",JOptionPane.WARNING_MESSAGE);
                namaDoc = "";
            }
        }      
    }
}