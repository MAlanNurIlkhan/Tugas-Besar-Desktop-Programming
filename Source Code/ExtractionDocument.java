import java.io.IOException;
import java.io.StringReader;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.id.IndonesianAnalyzer;
import org.apache.lucene.analysis.tokenattributes.TermAttribute;
import org.apache.lucene.util.Version;

/**
 *
 * @author Sesha
 */
public final class ExtractionDocument {
    String hasilEkstraksi;
    int jumKata = 0;
    
    private String teksHasilHapusDelimiter;
    
    public ExtractionDocument(String isiDoc) throws IOException {
        //this.dokumenInput = isiDoc;
        teksHasilHapusDelimiter = deleteDelimiter(isiDoc);    
        hasilEkstraksi = Stem(teksHasilHapusDelimiter);
        getJumKata(hasilEkstraksi);        
    }    
   
    private String deleteDelimiter(String isiDoc){
        String [] delimiter = {"0","1","2","3","4","5","6","7","8","9",
            "`","~","!","@","#","$","%","%","^","&","*","(",")","-","_",
            "=","+","{","}","[","]",":",";","'",",","<",".",">","?","/"};
        for(int i=0; i<delimiter.length; i++){
            isiDoc = isiDoc.replace(delimiter[i], "");            
        }
        return isiDoc;
    }
    
    private String Stem(String isiDoc){
        StringBuilder result = new StringBuilder();
        if (isiDoc!=null && isiDoc.trim().length()>0){
            StringReader tReader = new StringReader(isiDoc);            
            IndonesianAnalyzer analyzer = new IndonesianAnalyzer(Version.LUCENE_34);
            TokenStream tStream = analyzer.tokenStream("contents", tReader);
            
            TermAttribute term = tStream.addAttribute(TermAttribute.class);

            try {
                while (tStream.incrementToken()){
                    result.append(term.term());
                    result.append(" ");
                }
            } catch (IOException ioe){
                System.out.println("Error: "+ioe.getMessage());
            }
        }

        // If, for some reason, the stemming did not happen, return the original isiDoc
        if (result.length()==0)
            result.append(isiDoc);
        return result.toString().trim();
    }
    
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