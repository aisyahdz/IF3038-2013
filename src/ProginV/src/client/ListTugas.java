/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import java.sql.*;
/**
 *
 * @author Aisyah
 */
public class ListTugas extends JPanel {

    /**
     * Creates new form ListTugas
     */
    public ListTugas() {
        super(new GridLayout(1,0));
        
        JTable table = new JTable(new ListTugas.Tabel());
        table.setPreferredScrollableViewportSize(new Dimension(300,100));
        table.setFillsViewportHeight(true);
        JScrollPane scroll = new JScrollPane(table);
        add (scroll);}
    
    //kelas pembentuk tabel
    
    class Tabel extends AbstractTableModel{
        
        
        
    
    private String[] namakolom = {
    "Nama Tugas", "Tanggal Tugas","Kategori", "Status"};

    private Object [][] data= 
    { {"Makan", 
         "27041993","ok deh", false},
        {"Minum", 
         "17031992", "miscellaneous", true},
        
        };
    //private Object data = new Object [][];
    //ArrayList <Object> data = new ArrayList<>();
        
        @Override
        public int getRowCount() {
            return data.length;
                   }

        @Override
        public int getColumnCount() {
           return namakolom.length;   }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return data[rowIndex][columnIndex];}
        
    @Override
        public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }
 
       
    @Override
        public boolean isCellEditable(int row, int col) {
            if (col ==  3){
                return true;
            } else {
                return false;
            }
        }

    @Override
        public void setValueAt(Object value, int row, int col) {
            if (true) {
                System.out.println("Mengubah " + row + "," + col
                                   + " menjadi " + value
                                 );
            }
 
            data[row][col] = value;
            fireTableCellUpdated(row, col);
 
            if (true) {
                System.out.println("Data baru");
                printDebugData();
            }
        }
 
        private void printDebugData() {
            int numRows = getRowCount();
            int numCols = getColumnCount();
 
            for (int i=0; i < numRows; i++) {
                System.out.print("    baris " + i + ":");
                for (int j=0; j < numCols; j++) {
                    System.out.print("  " + data[i][j]);
                }
                System.out.println();
            }
            System.out.println("--------------------------");
        }
        
        
        
    
    }
    
    
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("List Tugas");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        //Create and set up the content pane.
        ListTugas newContentPane = new ListTugas();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);
 
        //Display the window.
        frame.pack();
        frame.setVisible(true);
        
        
        
        
    }
 
    public static void main(String[] args) {
 
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                
        String url ="jdbc:mysql://localhost:3306/";
        String dbname = "progin";
        String driver="com.mysql.jdbc.Driver";
        String username= "root";
        String password="";
        
        try{
        Class.forName(driver).newInstance();
        Connection conn = DriverManager.getConnection(url+dbname,username,password);
        //akses tabel
        Statement st= conn.createStatement();
        ResultSet res= st.executeQuery("select * from tugas");
        while(res.next()){
        String nama= res.getString("nama_tugas");
        String status = res.getString("status");
        System.out.println(nama);
        System.out.println(status);
        }
        conn.close();
        }
        catch (Exception e)
        {
        e.printStackTrace();
        }
                
                createAndShowGUI();
                
            }
        });
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(255, 204, 204));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
