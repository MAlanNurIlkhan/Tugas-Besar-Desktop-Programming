import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Calendar;

/**
 *
 * @author Sesha
 */
public final class RabinKarp {
    String DocAsli;
    String DocUji;
    String[] patternSama;
    
    int Kgram;
    int jumPatternDocAsli;
    int jumPatternDocUji;
    int jumPatternSama = 0;      
    
    double basis = 256;    
    double similarity;
    
    long waktuProses;
    
    public RabinKarp(){
    }
    
    public RabinKarp(String Doc1, String Doc2, int Kgram) {
        long start  = Calendar.getInstance().getTimeInMillis();
        this.DocAsli = Doc1.replace(" ", "");
        this.DocUji = Doc2.replace(" ", "");
        this.Kgram = Kgram;
                
        jumPatternDocAsli = this.DocAsli.length()-Kgram+1;
        jumPatternDocUji = this.DocUji.length()-Kgram+1;               
        
        if(jumPatternDocAsli < jumPatternDocUji) {
            patternSama = new String[DocUji.length()];
            parsingKgram(this.DocAsli, this.DocUji, jumPatternDocAsli);
        } else {
            patternSama = new String[DocAsli.length()];
            parsingKgram(this.DocUji, this.DocAsli, jumPatternDocUji);
        }        
        getSimilarity();        
        long end = Calendar.getInstance().getTimeInMillis();
        
        waktuProses = (end-start);
    }
    
    private void parsingKgram(String pattern, String teks, int jumKgram) {
        
        for(int i=0; i<jumKgram; i++) {
            int nextKgram = Kgram+i;            
            //StringBuilder stringBuilder = new StringBuilder();
            //System.out.println(stringBuilder.append(pattern.substring(i, nextKgram)));
            matching(pattern.substring(i, nextKgram), teks);
        }
    }
    
    private int hash(String pattern){
        int h = 0;
        for(int i=0; i<pattern.length(); i++){
            h += pattern.charAt(i)*Math.pow(basis, pattern.length()-i-1);
        }
        return h;
    }
    
    private void matching(String pattern, String teks){
        int panjangPattern = pattern.length();
        int panjangTeks = teks.length();
        int i, j;
        int hashPattern = hash(pattern);
        int hashTeks = hash(teks.substring(0,panjangPattern));
                       
        //System.out.println(pattern +" - "+hashPattern);
        //System.out.println("coba "+teks.substring(1,panjangPattern+1)+" - "+hashTeks);
        
        for(i=0; i<panjangTeks-panjangPattern; i++){
            if(hashPattern==hashTeks){
                for(j=0; j<panjangPattern; j++){
                    if(teks.charAt(i+j) != pattern.charAt(j))
                        break;
                }
                if(j==panjangPattern){                    
                    jumPatternSama++;
                    patternSama[i] = pattern;
                    //System.out.println(pattern +" -> PatternFound at index "+i);
                    break;
                }
            }else{
                hashTeks = hash(teks.substring(i+1, panjangPattern+i+1));
                //System.out.println(teks.substring(i+1, panjangPattern+i+1)+" - "+hashTeks);
            }
        }
    }
    
    private void getSimilarity(){        
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        
        double A = 2*jumPatternSama;
        double B = jumPatternDocAsli+jumPatternDocUji;
        double C = (A/B)*100; 
        //similarity = C;
        
        int decimalPlace = 2;
        BigDecimal bigDecimal = new BigDecimal(C);
        bigDecimal = bigDecimal.setScale(decimalPlace, BigDecimal.ROUND_UP);
        similarity = bigDecimal.doubleValue(); 
    }   
}