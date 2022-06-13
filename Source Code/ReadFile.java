import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import org.xml.sax.SAXException;

/**
 *
 * @author Sesha
 */
public class ReadFile {
    String sumberDoc;
    String isiDoc = "";
    
    int jumKata = 0;

    public ReadFile(String sumberDoc) throws IOException, ParserConfigurationException, SAXException {
        this.sumberDoc = sumberDoc;
        if (sumberDoc.endsWith("doc")) {
            readDoc(sumberDoc);
        } else {
            readDocx(sumberDoc);
        }

        getJumKata(isiDoc);
    }

    //Fungsi untuk membaca file .doc
    private void readDoc(String sumberDoc) {
        this.sumberDoc = sumberDoc;

        POIFSFileSystem fs = null;
        try {
            fs = new POIFSFileSystem(new FileInputStream(sumberDoc));
            HWPFDocument doc = new HWPFDocument(fs);
            WordExtractor we = new WordExtractor(doc);
            String[] paraghraps = we.getParagraphText();

            //System.out.println("Word Document has "+ paraghraps.length +" paragraphs");

            for (int i = 0; i < paraghraps.length; i++) {
                paraghraps[i] = paraghraps[i].replaceAll("\\cM?\r?", "");
                for (int j = 0; j < paraghraps[i].length(); j++) {
                    //System.out.print(paraghraps[i].charAt(j));      
                    isiDoc += paraghraps[i].charAt(j);
                }
            }
        } catch (Exception e) {
        }
    }

    //fungsi untuk membaca file .docx
    private void readDocx(String sumberDoc) throws IOException, ParserConfigurationException, SAXException {
        this.sumberDoc = sumberDoc;

        FileInputStream file = new FileInputStream(sumberDoc);

        ZipInputStream docxFile = new ZipInputStream(file);
        ZipEntry zipEntry;
        OutputStream out;
        String xml = "";

        while ((zipEntry = docxFile.getNextEntry()) != null) {
            if (zipEntry.toString().equals("word/document.xml")) {
                byte[] buffer = new byte[1024 * 4];
                long count = 0;
                int n = 0;
                long size = zipEntry.getSize();
                out = System.out;

                while (-1 != (n = docxFile.read(buffer)) && count < size) {
                    xml += new String(buffer, 0, n);
                    count += n;
                }
            }
        }

        InputStream is = new ByteArrayInputStream(xml.getBytes("UTF-8"));
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder parser = factory.newDocumentBuilder();

        Document document = parser.parse(is);

        NodeList sections = document.getElementsByTagName("w:t");

        for (int i = 0; i < sections.getLength(); i++) {
            isiDoc += sections.item(i).getFirstChild().getNodeValue();
        }
    }

    //Fungsi untuk menghitung jumlah kata
    private void getJumKata(String isiDoc) {
        //System.out.println(isiDoc);
        int index = 0;
        boolean prevWhiteSpace = true;
        
        while(index < isiDoc.length()) {
            char c = isiDoc.charAt(index++);
            boolean currWhiteSpace = Character.isWhitespace(c);
            if(prevWhiteSpace && !currWhiteSpace) {
                jumKata++;
            }
            prevWhiteSpace = currWhiteSpace;
        }        
    }
}