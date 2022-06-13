import java.awt.Toolkit;

public class HowToDialog extends javax.swing.JDialog {

  public HowToDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        java.awt.Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        java.awt.Dimension framesize = getSize();
        setLocation((screenSize.width - framesize.width)/2, (screenSize.height - framesize.height)/2);
    }
    public void closeHowToDialog() {
        dispose();
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        close = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        close.setText("Close");
        close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeActionPerformed(evt);
            }
        });

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setText("Memulai Proses Pendeteksian :\n- Masukan dokumen asli dan dokumen uji dengan megklik tombol Cari \nuntuk masing-masing dokumen\n- Pilih nilai K-gram yang akan digunakan dalam membandingan\n- Untuk memulai proses penyamaan klik tombol Start\n- Hasil pendeteksian akan tampil di sebelah kanan terdiri dari :\n * Kgram (merupakan nilai K-gram yang digunakan)\n * Jum. Pattern Sama (Jumlah pattern yang sama dari dua dokumen)\n * Persentasi Kemiripan (Nilai persentasi kemiripan dari dua dokumen)\n * Waktu proses (Waktu yang dibutuhkan dalam membandingkan isi \n   dari kedua dokumen)\n- Informasi Dokumen adalah informasi dari dokuemn Asli dan Dokumen \n  Uji yang dimasukan, terdiri dari :\n * Nama dokumen, merupakan nama dokumen yang dimasukan\n * Jum. Kata sebeleum di proses, adalah jumlah kata yang adadi \n   dalam dokumen\n * Jum. Kata setelah di proses, adalah jumlah kata yang ada \n   setelah dilakukan proses stemming, yaitu proses pemotongan \n   sehingga didapat kata-kata dasar dari isi dokumen\n * Jum. Pattern, adalah jumlah pattern dari isi dokumen \n   berdasarkan nilai K-gram yang dipilih\n- Highligh Pattern, merupakan highlight dari isi dokuemn, setiap \n  pattern yang sama akan diberi warna merah\n\nMenyimpan hasil Pendeteksian : Untuk menyimpan hasil pendeteksian, \npilih Menu File kemudian pilih submenu Simpan. Hasil proses \npendeteksian akan disimpan ke dalam file pdf.\");\n\n\n");
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(240, 240, 240)
                .addComponent(close)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 559, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE)
                .addGap(22, 22, 22)
                .addComponent(close))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void closeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeActionPerformed
        closeHowToDialog();
    }//GEN-LAST:event_closeActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton close;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
