import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.MemoryImageSource;
import java.util.Stack;

public class Mandel extends javax.swing.JFrame {

    Graphics g;
    double m=10, k=0.01, zoom=1, izmers=400, x0=0, y0=0;  //robezvertiba
    int w=400, h=400, x1, y1, x2, y2, limenis=0;
    boolean click = true, pirma=true;
    int[] pixels = new int[(h+1) * (w+1)];
    Stack <Double> steks = new Stack <Double>();
    
    public Mandel() {
        initComponents();
        g = jPanel1.getGraphics();
        g.setColor(Color.red);
        jButton2.setEnabled(false);
        
    }

    public void zime(){
        g.clearRect(0, 0, w, h);
        algoritms();
        Image img = createImage(new MemoryImageSource(w, h, pixels, 0 ,w));
        
        g.drawImage(img.getScaledInstance(400, 400, 1), 0, 0, null);
    }
    
    
    public void algoritms(){
        double cr, ci, av, aj, bv, bj, modulis;
        int reizes;
        zoom=(double)izmers/h;
        for(int i=0; i<w; i++){
            for(int j=0; j<h; j++){
                av=cr=(x0+j*zoom-(h/2))*k;
                bv=ci=(y0+i*zoom-(w/2))*k;
                reizes=0;
                modulis = 0;
                while(reizes<100 && modulis<m){
                    aj=av*av - bv*bv + cr;
                    bj=2*av*bv + ci;
                    modulis= aj*aj + bj*bj;
                    av=aj;
                    bv=bj;
                    reizes++;
                }
                if(reizes>=100) pixels[i*w+j]=(1<<24)|(2<<16)|(0<<8)|0;
                else if(reizes>=20) pixels[i*w+j]=(64<<24)|(71<<16)|(27<<8)|0;
                else if(reizes>=10) pixels[i*w+j]=(38<<24)|(96<<16)|(6<<8)|0;
                else if(reizes>=5) pixels[i*w+j]=(137<<24)|(8<<16)|(53<<8)|0;
                else if(reizes>=3) pixels[i*w+j]=(220<<24)|(199<<16)|(252<<8)|0;
                else if(reizes>=1) pixels[i*w+j]=(252<<24)|(0<<16)|(0<<8)|0;
                else pixels[i*w+j]=(256<<24)|(120<<16)|(255<<8)|0;
            }
        }
    }    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 596, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 534, Short.MAX_VALUE)
        );

        jButton1.setText("Zimet");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Atpakaļ");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(29, 29, 29))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(102, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        g.clearRect(0, 0, w, h);
        izmers=400; x0=0; y0=0; zoom=1;
        zime();
        steks.clear();
        steks.push(x0);
        steks.push(y0);
        steks.push(izmers);
        steks.push(zoom);
        pirma=true; limenis=0;
        jButton2.setEnabled(false);
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseClicked
        if(click){
            x1=evt.getX();
            y1=evt.getY();
            click=false;
            g.drawRect(x1, y1, 1, 1);
        }
        
        else{
            x2=evt.getX();
            y2=evt.getY();
            click=true;
            
            steks.push(x0);
            steks.push(y0);
            steks.push(izmers);
            steks.push(zoom);
            
            if(Math.abs(x1-x2)<Math.abs(y1-y2)){
                izmers=Math.abs(x1-x2)*zoom;  
            }
            else{
                izmers=Math.abs(y1-y2)*zoom; 
            }
            if(x1<x2) x0=x0+x1*zoom;
            else x0=x0+x2*zoom;
            if(y1<y2) y0=y0+y1*zoom;
            else y0=y0+y2*zoom;
            zime();
            limenis++;
            jButton2.setEnabled(true);
        }
    }//GEN-LAST:event_jPanel1MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        zoom=steks.pop();
        izmers=steks.pop();
        y0=steks.pop();
        x0=steks.pop();
        zime();
        limenis--;
        if(limenis==0){
            jButton2.setEnabled(false);
            izmers=400; x0=0; y0=0; zoom=1;
            steks.clear();
            steks.push(x0);
            steks.push(y0);
            steks.push(izmers);
            steks.push(zoom);
        }
        pirma=false;
    }//GEN-LAST:event_jButton2ActionPerformed

    public static void main(String args[]) {

        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Mandel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Mandel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Mandel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Mandel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Mandel().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
