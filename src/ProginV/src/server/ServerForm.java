/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author M Reza MP
 */
public class ServerForm extends javax.swing.JFrame implements Runnable
{

    /**
     * Creates new form CobaServer
     */
    ServerSocket server = null;
    Socket client = null;
    boolean shutdown = false;
    
    ArrayList<Socket> clients = new ArrayList<>();
    
    public ServerForm() {
        initComponents();
        setVisible(true);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        logScrollPane = new javax.swing.JScrollPane();
        logTextArea = new javax.swing.JTextArea();
        shutdownButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        logTextArea.setColumns(20);
        logTextArea.setRows(5);
        logScrollPane.setViewportView(logTextArea);

        shutdownButton.setText("Turn Off");
        shutdownButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shutdownButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(logScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(shutdownButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(logScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(shutdownButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void shutdownButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shutdownButtonActionPerformed
        // TODO add your handling code here:
        shutdown = !shutdown;
        shutdownButton.setText((shutdown) ? "Turn On" : "Turn Off");
        logTextArea.append((shutdown) ? "Turn On" : "Turn Off");
    }//GEN-LAST:event_shutdownButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
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
            java.util.logging.Logger.getLogger(ServerForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ServerForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ServerForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ServerForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Thread t = new Thread(new ServerForm());
                t.start();
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane logScrollPane;
    private javax.swing.JTextArea logTextArea;
    private javax.swing.JButton shutdownButton;
    // End of variables declaration//GEN-END:variables

    public javax.swing.JTextArea getLogTextArea()
    {
        return logTextArea;
    }
    
    @SuppressWarnings("deprecation")
	@Override
    public void run() 
    {
        try {
            // TODO add your handling code here:
            //Thread t = null;
            while (true)
            {
            	if (!shutdown)
            	{
            		System.out.println("on");
            		if (server == null)
            		{
            			server = new ServerSocket(2000, 5);
            		}
            		
            		client = server.accept();
                        if ((!clients.contains(client)) || clients.isEmpty())
                        {
                            clients.add(client);
                        }
                        
                        for(Socket c : clients)
                        {
                            Thread t = new Thread(new HandleClient(this, c));
                            t.start();
                        }
            	}
            	else
            	{
            		System.out.println("off");
                        /*
            		if (t.isAlive())
            		{
            			t = null;
            		}
                        */
            		if (server != null)
            		{
                            server = null;
                            clients.clear();
            		}
            	}
            }
            //JOptionPane.showMessageDialog(null, "Client Request Accepted.");
        } catch (IOException ex) {
            Logger.getLogger(ServerForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}
