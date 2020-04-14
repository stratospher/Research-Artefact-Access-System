/**
 *
 * @author ruhithomas
 * DATE:14 April 2020
 * COURSE:CS206 Object Oriented and Design Programming
 * 
 * DESCRIPTION:
 * contains code for the user to view their research
 * 
 * @note change line 59 to connect to database
 */
package research;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class myresearch extends javax.swing.JFrame {

    /**
     * Creates new form myresearch
     */
    public myresearch() {
        initComponents();
        show_file();
    }
    
    /*
    Function to return filename,topic,year,filepath of user's work in ArrayList form
    */
    public ArrayList<Researchfile> researchList(){
//        System.out.print("I'm in researchList");
        ArrayList<Researchfile> researchesList=new ArrayList<>();
        try{
            
        Connection con;
        //connecting to database
        con  = DriverManager.getConnection("jdbc:mysql://localhost:3306/researchartefact", "root", "light123hash");
        Statement stmt = con.createStatement();
        String sql = "SELECT id FROM researchartefact.researcher WHERE username='"+Globals.uname+"'";
        System.out.print("uname is "+Globals.uname);
        System.out.print("query is "+sql);
        ResultSet result = stmt.executeQuery(sql);
        
        if(result.next())
            {
                int id = result.getInt("id");
                System.out.print("ID:"+id);
                String query="SELECT workId FROM researchartefact.researchwork WHERE researcherId="+id;
                Statement st=con.createStatement();
                ResultSet rs = st.executeQuery(query);
//                System.out.print("\n@**"+rs.getString(1)+"**");
//                System.out.print("\n@**"+rs.getString(2)+"**");
                while(rs.next())
                {
//                    System.out.print("**"+rs.getString(i)+"**");
                    String query2="SELECT f.filename,r.topic,r.year,f.filelocation FROM researchartefact.fileinfo f, researchartefact.researchwork r WHERE f.workId=r.workId AND r.researcherId="+id+" AND f.workId="+rs.getString(1);                    
                    Statement st2=con.createStatement();
                    ResultSet rs2 = st2.executeQuery(query2);
//                    String filename,String topic,int year,String filepath
                     Researchfile userfile;
                     while(rs2.next())
                     {
                        userfile=new Researchfile(rs2.getString(1),rs2.getString(2),rs2.getInt(3),rs2.getString(4)); 
                        researchesList.add(userfile);
                     }
                     System.out.print("\nok...i reached end of loop for 11");  
                }
            }
        }
        
        catch(SQLException ex){
            
             Logger.getLogger(superior.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return researchesList; 
    }
    
 /*
   Function to insert details of user's filename,topic,year,filepath into Object data and call table display function
 */
    public void show_file(){
//    System.out.print("I'm in show file");
        ArrayList<Researchfile>list=researchList();
   
        DefaultTableModel model =(DefaultTableModel)jTable1.getModel();
        Object[][] data = new Object[list.size()][4];
        for(int i=0;i<list.size();i++)
        {
            data[i][0]=list.get(i).getfilename();
            data[i][1]=list.get(i).gettopic();
            data[i][2]=list.get(i).getyear();
            data[i][3]=list.get(i).getfilepath();
       }
//        for(int i=0;i<list.size();i++)
//       {
//            System.out.print(" "+data[i][0]);
//            System.out.print(" "+data[i][1]);
//            System.out.print(" "+data[i][2]);
//            System.out.print(" "+data[i][3]+"\n");
//            
//       }
       ButtonClumn bc=new ButtonClumn(data);
//          bc.setVisible(true);
       
}
    
/* Function to set up table */
public class ButtonClumn extends JFrame {
  public ButtonClumn(Object[][] data){
      //COLUMN HEADERS
        String columnHeaders[]={"filename","topic","year","clickme"};
        //CREATE OUR TABLE AND SET HEADER
        JTable table=new JTable(data,columnHeaders);
       
       //SET CUSTOM RENDERER TO clickme COLUMN
        table.getColumnModel().getColumn(3).setCellRenderer(new ButtonRenderer());;

        //SET CUSTOM EDITOR TO clickme COLUMN
        table.getColumnModel().getColumn(3).setCellEditor(new ButtonEditor(new JTextField()));
        //SCROLLPANE,SET SZE,SET CLOSE OPERATION
//       JScrollPane pane=new JScrollPane(table);
        jScrollPane1.getViewport ().add(table);
//        getContentPane().add(pane);
//        setSize(450,100);

//        setDefaultCloseOperation(EXIT_ON_CLOSE);
      
  }
  
/* BUTTON RENDERER CLASS */
class ButtonRenderer extends JButton implements  TableCellRenderer
{

  //CONSTRUCTOR
  public ButtonRenderer() {
    //SET BUTTON PROPERTIES
    setOpaque(true);
  }
  @Override
  public Component getTableCellRendererComponent(JTable table, Object obj,
      boolean selected, boolean focused, int row, int col) {

    //SET PASSED OBJECT AS BUTTON TEXT
//      setText((obj==null) ? "":obj.toString());
  setText("VIEW FILE");
    return this;
  }

}
/* BUTTON EDITOR CLASS */
class ButtonEditor extends DefaultCellEditor
{
  protected JButton btn;
   private String lbl;
   private Boolean clicked;

   public ButtonEditor(JTextField txt) {
    super(txt);

    btn=new JButton();
    btn.setOpaque(true);

    //WHEN BUTTON IS CLICKED
    btn.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {

        fireEditingStopped();
      }
    });
  }
   
   //OVERRIDE A COUPLE OF METHODS
   @Override
  public Component getTableCellEditorComponent(JTable table, Object obj,
      boolean selected, int row, int col) {

      //SET TEXT TO BUTTON,SET CLICKED TO TRUE,THEN RETURN THE BTN OBJECT
     lbl=(obj==null) ? "":obj.toString();
     btn.setText("VIEW FILE");
     clicked=true;
    return btn;
  }

  //IF BUTTON CELL VALUE CHNAGES,IF CLICKED THAT IS
   @Override
  public Object getCellEditorValue() {

     if(clicked)
      {
      //SHOW US SOME MESSAGE
          //
          //
          try{
            
            Desktop.getDesktop().open(new java.io.File(lbl));
        }catch(Exception e)
        {
            e.printStackTrace();
        }
//        JOptionPane.showMessageDialog(btn, lbl+" Clicked");
      }
    //SET IT TO FALSE NOW THAT ITS CLICKED
    clicked=false;
    return new String(lbl);
  }

   @Override
  public boolean stopCellEditing() {

         //SET CLICKED TO FALSE FIRST
      clicked=false;
    return super.stopCellEditing();
  }

   @Override
  protected void fireEditingStopped() {
    // TODO Auto-generated method stub
    super.fireEditingStopped();
  }
  }
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setFont(new java.awt.Font("Futura", 1, 18)); // NOI18N
        jLabel1.setText("My Researches!");

        jButton1.setFont(new java.awt.Font("Futura", 0, 14)); // NOI18N
        jButton1.setText("GO BACK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        jLabel5.setFont(new java.awt.Font("Monaco", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText(" FACILITY SYSTEM");

        jLabel6.setFont(new java.awt.Font("Futura", 0, 36)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("101");

        jLabel7.setFont(new java.awt.Font("Monaco", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("RESEARCH ARTEFACT ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel5)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 8, Short.MAX_VALUE)
                        .addComponent(jLabel7)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addGap(71, 71, 71))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(147, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(120, 120, 120)
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(33, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1)
                        .addGap(15, 15, 15))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        setVisible(false);
        researcherpage mf = new researcherpage();
        mf.setLocationRelativeTo(null);
        mf.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(myresearch.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(myresearch.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(myresearch.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(myresearch.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new myresearch().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
