/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankersalgorithm;

import javax.swing.JOptionPane;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.Timer;

import java.awt.FlowLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.*;

/**
 *
 * @author User
 */
public class Bankersalgorithm {

    /**
     * @param args the command line arguments
     */
    
    private JFrame mainFrame;
   private JLabel headerLabel, headerLabel2, headerLabel3,  CopyLabel, availMsg, headerLabel4;
   private JLabel statusLabel;
   private JPanel controlPanel;
   private JLabel msglabel;
   static String sequence = "";
   static Bankersalgorithm  banker;
   static int m, n, m2, m3,  ONE_SECOND=400, i2=0, new_i, new_j, i3=0;
   static Timer timer;
   public static int calculate[]=new int[10000];
  
  
   
   static String display =" ";
   static Object ss="Completed calculation";
   
   static UIManager um = new UIManager();
   
   DefaultTableModel model, modelM, modelA, Modelneed;
   JTable table, tableM, tableA, tableNeed;
   String col[] = {"   A   ","   B   ","   C   "};
   JScrollPane pane = new JScrollPane(table);
   JScrollPane pane2 = new JScrollPane(tableM);
   JScrollPane pane3 = new JScrollPane(tableA);
   JScrollPane pane4 = new JScrollPane(tableNeed);
   
   JButton b1=new JButton("Calculate");  
   
   public static int[] available2=new int[3000];
   public static int max2[][]=new int[3000][3000];
   public static int allocation2[][]=new int[3000][3000];
   public static int need2[][]=new int[3000][3000];
   static int row, column, fc=0;
   

   public Bankersalgorithm(){
      prepareGUI();
   }
   
    public static void main(String[] args) {
        // TODO code application logic here
        
     UIManager.put("TextField.font", new Font("Verdana", Font.BOLD, 20) );
     UIManager.put("OptionPane.messageFont", new Font("Verdana", Font.BOLD, 20));
     
     JFrame frame_1 = new JFrame("Banker #1");
       try{
        n = Integer.parseInt(JOptionPane.showInputDialog(frame_1,"Number Of Process:","Input: Number Of Process (P)",-1));
        m = Integer.parseInt(JOptionPane.showInputDialog(frame_1,"Resource Type Number:","Input: Resource Number (R)",-1));
       }catch(NumberFormatException e){
           JFrame frame = new JFrame("JOptionPane showMessageDialog example");
           um.put("OptionPane.messageForeground", Color.red);
            JOptionPane.showMessageDialog(frame,
              "Unable to process non-numeric !",
              "Error FOUND!",
              JOptionPane.ERROR_MESSAGE);
               System.exit(0);
       }
        
        m2=0; 
        m3=0;        
 
        int available[] = new int[m];
        int max[][] = new int[n][m];
        int allocation[][] = new int[n][m];
        int need[][] = new int[n][m];
       
        
     banker = new Bankersalgorithm(); 
     banker.showJPanelDemo();

      
         

    }
//------------------------------------------------------------------------------------------    
    private void prepareGUI(){
      int i1, j1; 
      mainFrame = new JFrame("Banker's Algorithm");
      mainFrame.setSize(800,750);
       mainFrame.setLayout(new GridLayout(0, 1));// /setting grid layout of y rows and x columns  
       //mainFrame.setLayout(new BoxLayout(mainFrame, BoxLayout.Y_AXIS));
       mainFrame.setLocationRelativeTo(null);  
      /*
      mainFrame.addWindowListener(new WindowAdapter() {
          @Override
         public void windowClosing(WindowEvent windowEvent){
            System.exit(0);
         }        
      });    
      */
      //String col_name="R";
      modelA = new DefaultTableModel(1,m); //table row and column setting   
      
      tableA=new JTable(modelA){
          
               public Component prepareRenderer(TableCellRenderer renderer, int row1, int column1)
               {
                Component c = super.prepareRenderer(renderer, row1, column1);

                //  Color row based on a cell value

                if (isRowSelected(row1) && isColumnSelected(column1)){ //When A row is selected
                                   c.setBackground(new Color(214,214,190));//Set Background
                                   c.setForeground(Color.BLACK);
                                   fc=3;
                }else{
                     c.setBackground(tableA.getBackground());
                     c.setForeground(tableA.getForeground());
                }

                return c;
               }
          
               @Override
		public boolean isCellEditable(int arg0, int arg1) {
                    
                        ss=tableA.getValueAt(arg0,arg1);
                        //available2[arg1]=(int) ss;
                        //available2[arg1]=Integer.parseInt(ss.toString());
                        row=arg0;
                        column=arg1;
			return true;
		}};
     tableA.setCellSelectionEnabled(true); 
     
     for( j1 = 0; j1 < m; j1++)
        {
         tableA.getColumnModel().getColumn(j1).setHeaderValue("R"+j1);   
         //tableA.setValueAt(available2[j1],0,j1); 
         tableA.setFont(new Font("Verdana", Font.PLAIN, 18));
        }
     tableA.setRowHeight(20);
      //table.setValueAt(n,0,0);            
      
      pane3 = new JScrollPane(tableA, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,  JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
      pane3.setPreferredSize(new Dimension(mainFrame.getWidth(), 100));
      pane3.revalidate();
      pane3.repaint();
     
      
      model = new DefaultTableModel(n,m); //table row and column setting	      
      table=new JTable(model){
          
           public Component prepareRenderer(TableCellRenderer renderer, int row1, int column1)
               {
                Component c = super.prepareRenderer(renderer, row1, column1);

                //  Color row based on a cell value

                if (isRowSelected(row1) && isColumnSelected(column1)){ //When A row is selected
                                   c.setBackground(new Color(255, 255, 179));//Set Background
                                   c.setForeground(Color.BLACK);
                                   fc=1;
                }else{
                     c.setBackground(table.getBackground());
                     c.setForeground(table.getForeground());
                }

                return c;
               }
           
               @Override
		public boolean isCellEditable(int arg0, int arg1) {
		        
                        ss=table.getValueAt(arg0,arg1); 
                        //allocation2[arg0][arg1]=(int) ss;
                        //allocation2[arg0][arg1]=Integer.parseInt(ss.toString());
                        row=arg0;
                        column=arg1;
                        
			return true;
		}};
     table.setCellSelectionEnabled(true);
     /*
     if (table.isCellSelected(row, column)){
        table.setBackground(Color.RED);
     }
     */
     for( i1 = 0; i1 < n; i1++)
     {
        for( j1 = 0; j1 < m; j1++)
        {
         table.getColumnModel().getColumn(j1).setHeaderValue("R"+j1);      
         //table.setValueAt(allocation2[i1][j1],i1,j1); 
         table.setFont(new Font("Verdana", Font.PLAIN, 18));
        }
     }
      //table.setValueAt(n,0,0);   
     table.setRowHeight(20);
      
      pane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,  JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
      pane.setPreferredSize(new Dimension(mainFrame.getWidth(), 250));
      pane.revalidate();
      pane.repaint();
      
      modelM = new DefaultTableModel(n,m); //table row and column setting	
      tableM=new JTable(modelM){
          
              public Component prepareRenderer(TableCellRenderer renderer, int row1, int column1)
               {
                Component c = super.prepareRenderer(renderer, row1, column1);

                //  Color row based on a cell value

                if (isRowSelected(row1) && isColumnSelected(column1)){ //When A row is selected
                                   c.setBackground(new Color(255, 204, 179));//Set Background
                                   c.setForeground(Color.BLACK);
                                   fc=2;
                }else{
                     c.setBackground(tableM.getBackground());
                     c.setForeground(tableM.getForeground());
                }

                return c;
               }
          
               @Override
		public boolean isCellEditable(int arg0, int arg1) {
		
                        ss=tableM.getValueAt(arg0,arg1); 
                        //max2[arg0][arg1]=(int) ss;
                        //max2[arg0][arg1]=Integer.parseInt(ss.toString());
                        row=arg0;
                        column=arg1;
                        
			return true;
		}};
      tableM.setCellSelectionEnabled(true);
      
      for( i1 = 0; i1 < n; i1++)
        {
        for( j1 = 0; j1 < m; j1++)
          {
            tableM.getColumnModel().getColumn(j1).setHeaderValue("R"+j1);     
            //tableM.setValueAt(max2[i1][j1],i1,j1); 
            tableM.setFont(new Font("Verdana", Font.PLAIN, 18));
          }
        }
      //tableM.setValueAt(m,0,0);
      tableM.setRowHeight(20);
      
      pane2 = new JScrollPane(tableM, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,  JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
      pane2.setPreferredSize(new Dimension(mainFrame.getWidth(), 250));
      pane2.revalidate();
      pane2.repaint();
      
       //String col_name="R";
      Modelneed = new DefaultTableModel(n,m); //table row and column setting   
      
      tableNeed=new JTable(Modelneed){
               @Override
		public boolean isCellEditable(int arg0, int arg1) {		        
                                                
			return false;
		}};
     //tableA.setCellSelectionEnabled(true); 
     
     for( i1 = 0; i1 < n; i1++)
        {
        for( j1 = 0; j1 < m; j1++)
          {
            tableNeed.getColumnModel().getColumn(j1).setHeaderValue("R"+j1);  
            tableNeed.setFont(new Font("Verdana", Font.PLAIN, 18));
            //tableNeed.setValueAt(need2[i1][j1],i1,j1); 
            
          }
        }
     
      //table.setValueAt(n,0,0);     
      tableNeed.setRowHeight(20);
      
      pane4 = new JScrollPane(tableNeed, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,  JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
      pane4.setPreferredSize(new Dimension(mainFrame.getWidth(), 250));
      pane4.revalidate();
      pane4.repaint();
      
      headerLabel = new JLabel("Banker's Algorithm Result: Allocation of P ="+n+", R ="+m, JLabel.CENTER);
      headerLabel.setFont(new Font("Verdana", Font.BOLD, 20));
      CopyLabel=new JLabel(" ©2017 Copyright LAI CXEN VOEN. \n\n",JLabel.CENTER);      
      availMsg=new JLabel("Number Of Available Resource (R).", JLabel.CENTER);
      availMsg.setFont(new Font("Verdana", Font.PLAIN, 20));
      headerLabel2 = new JLabel("Allocation of "+n+" Process (P) for "+m+" Resources (R)", JLabel.CENTER);
      headerLabel2.setFont(new Font("Verdana", Font.PLAIN, 20));
      //label1 = new JLabel("Allocation of P ="+n+", R type ="+m, JLabel.CENTER);
      //label1.setFont(new Font("Verdana", Font.PLAIN, 20));
      headerLabel3 = new JLabel("Maximum allocated "+n+" Process (P) for "+m+" Resources (R)", JLabel.CENTER);
      headerLabel3.setFont(new Font("Verdana", Font.PLAIN, 20));
      headerLabel4 = new JLabel("NEEDING TABLE", JLabel.CENTER);
      headerLabel4.setFont(new Font("Verdana", Font.PLAIN, 20));
      statusLabel = new JLabel("",JLabel.CENTER);    
      statusLabel.setSize(350,10); 
      b1.setBounds(50,100,95,30);
      b1.setFont(new Font("Serif", Font.BOLD, 16));
      //headerLabel.setSize(350,100); 
           
      //msglabel = new JLabel("SAFE And Sequence is:"+sequence, JLabel.CENTER);
      msglabel = new JLabel(" ", JLabel.CENTER);      
      controlPanel = new JPanel();
      controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.PAGE_AXIS));
      //msglabel.setForeground(Color.WHITE);  
      controlPanel.add(Box.createRigidArea(new Dimension(5,10)));// creating a gap between component
      controlPanel.add(headerLabel);      
      controlPanel.add(Box.createRigidArea(new Dimension(5,10)));// creating a gap between component
      //controlPanel.add(label1);
      //controlPanel.add(Box.createRigidArea(new Dimension(5,20)));// creating a gap between component
      controlPanel.add(availMsg);
      controlPanel.add(pane3);
      controlPanel.add(Box.createRigidArea(new Dimension(5,10)));// creating a gap between component
      controlPanel.add(headerLabel2);
      controlPanel.add(pane);
      controlPanel.add(Box.createRigidArea(new Dimension(5,10)));// creating a gap between component
      controlPanel.add(statusLabel);
      controlPanel.add(headerLabel3);
      controlPanel.add(pane2);
      controlPanel.add(Box.createRigidArea(new Dimension(5,15)));// creating a gap between component
      controlPanel.add(headerLabel4);
      controlPanel.add(pane4);
      controlPanel.add(Box.createRigidArea(new Dimension(5,15)));// creating a gap between component      
      controlPanel.add(b1);
      
      tableA.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
      tableM.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
               
      table.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);      
      
      b1.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent e)
           {
            // display/center the jdialog when the button is pressed
                
              try{ 
                for( new_j = 0; new_j < m; new_j++)
                   { 
                    available2[new_j]=Integer.parseInt(tableA.getValueAt(0,new_j).toString()); 
                   }      
                
                
                 
               for( new_i = 0; new_i < n; new_i++)
                   {
                   for( new_j = 0; new_j < m; new_j++)
                   {                
                     allocation2[new_i][new_j]=Integer.parseInt(table.getValueAt(new_i,new_j).toString()); //display the needing table result
            
                    }
                 } 
                
               for( new_i = 0; new_i < n; new_i++)
                   {
                   for( new_j = 0; new_j < m; new_j++)
                   {                
                     max2[new_i][new_j]=Integer.parseInt(tableM.getValueAt(new_i,new_j).toString()); //display the needing table result
            
                    }
                 }
               
               for( new_i = 0; new_i < n; new_i++)
                   {
                   for( new_j = 0; new_j < m; new_j++)
                   {                
                     //max2[new_i][new_j]=Integer.parseInt(table.getValueAt(new_i,new_j).toString()); //display the needing table result
                     need2[new_i][new_j] = max2[new_i][new_j] - allocation2[new_i][new_j];
                    }
                   
                 }
               
               for( new_i = 0; new_i < n; new_i++)
               {
                  for( new_j = 0; new_j < m; new_j++)
                   {                
                     tableNeed.setValueAt(need2[new_i][new_j],new_i,new_j); //display the needing table result
            
                    }
                 }  
               i3=0;
              }catch(NumberFormatException e1 ){
                  JFrame frame = new JFrame("JOptionPane showMessageDialog example");
                  um.put("OptionPane.messageForeground", Color.red);
                 JOptionPane.showMessageDialog(frame,
                   "Unable to process non-numeric !",
                  "Error FOUND!",
                   JOptionPane.ERROR_MESSAGE);
                   msglabel.setText("");
                   ONE_SECOND=400;
                   i3=1; 
              }catch(NullPointerException e2){
                  JFrame frame = new JFrame("JOptionPane showMessageDialog example");
                  um.put("OptionPane.messageForeground", Color.red);
                 JOptionPane.showMessageDialog(frame,
                   "Null entry found. Please key in integer in missing cell !",
                  "Error FOUND!",
                   JOptionPane.ERROR_MESSAGE);
                  msglabel.setText("");
                  ONE_SECOND=400;
                  i3=1;                
              }
//-----------------------------------------------------------------------------------------               
               int work[] = available2; 
               boolean finish[] = new boolean[n];
 
               for(int i = 0; i < n; i++)
                {
                finish[i] = false;
                }
 
               boolean check = true;
                   while(check)
                   {
                     check = false;
                       for(int i = 0; i < n; i++)
                        {
                         if(!finish[i])
                          {
                           int j;
                           for(j = 0; j < m; j++)
                           {
                              if(need2[i][j] > work[j])
                             {
                                      break;
                                         }
                                       }
 
                         if(j == m)
                              {
                                for(j=0; j < m; j++)
                                {
                                 work[j] = work[j] + allocation2[i][j];
                                 }
                            finish[i] = true;
                            check = true;
                            //sequence += i + ", ";             
                            calculate[m2]=i;// store specific value             
                            m2+=1;
                               }
                          }
                         }
                      }
 
               int i;
                for(i = 0; i < n; i++)
                {
                if(!finish[i])
                break;
                } 
                
                if(i==n)
                    {
                     i2=0;
                   }else {
                     i2=1;
               }
                
                
//---------------------------------------------------------------------------------------               
             
             timer = new Timer(ONE_SECOND, new ActionListener() {
                  public void actionPerformed(ActionEvent evt) { 
                  b1.setEnabled(false);
                  if (m3<m2 && i2!=1 && i3!=1){          
                    if(m3+1!=m2){  
                       display+=calculate[m3]+", ";
                    }else{
                       display+=calculate[m3]+"  ";
                    }
                    msglabel.setBackground(new Color(0,153,51));
                    msglabel.setForeground(Color.WHITE);
                    msglabel.setText(" SAFE And Sequence is:"+display); //displaying the result in animated sequence, 1 second per number               
                    
                  }
                m3+=1;
                               
                if (m3==m2 || m3>m2 ) {                    
                    timer.stop();
                    display=" ";
                    ONE_SECOND=30;                    
                    /*
                    JFrame frame = new JFrame("table Value");
                    if(fc==1 && i3==0){
                        um.put("OptionPane.messageForeground", Color.white);
                        um.put("Panel.background", Color.red);
                    }else if (fc==2 && i3==0){
                        um.put("OptionPane.messageForeground", Color.white);
                        um.put("Panel.background", Color.black);
                    }else if (fc==3 && i3==0){
                        um.put("OptionPane.messageForeground", Color.white);
                        um.put("Panel.background", Color.blue);
                    }
                    fc=0;
                    JOptionPane.showMessageDialog(frame,
                       " Data= "+ss,
                       "data FOUND!",
                        JOptionPane.INFORMATION_MESSAGE);
                    */
                    b1.setEnabled(true);
                    }
                if(i2==1 && i3==0){// IF DEADLOCK IS FOUND
                     msglabel.setBackground(Color.RED);
                     msglabel.setForeground(Color.WHITE);
                     msglabel.setText(" Result: DEAD LOCK FOUND!! "); //displaying the error result  
                  }
                }
               });
               timer.start(); 
               
                 
           }
       });
      controlPanel.add(Box.createRigidArea(new Dimension(5,10)));
      controlPanel.add(statusLabel);
      //mainFrame.add(headerLabel);
      //mainFrame.add(label1);
      //mainFrame.add(headerLabel2);
      
      //mainFrame.add(pane);

      mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    
      // mainFrame.add(statusLabel);
      // mainFrame.add(headerLabel3);
      // mainFrame.add(pane2);
       
      // mainFrame.add(b1);
      mainFrame.add(controlPanel);
      
      //mainFrame.add(statusLabel);
      mainFrame.setVisible(true);  
      //mainFrame.pack();
      //Create a timer.          
        
             
   }
   private void showJPanelDemo(){
       
      JPanel panel = new JPanel();
      
      panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
      panel.setSize(new Dimension(mainFrame.getWidth(),800));
      msglabel.setFont(new Font("Serif", Font.BOLD, 24));
      msglabel.setOpaque(true);
      
     // msglabel.setBackground(Color.BLACK);
      //panel.setBackground(Color.black);            
      panel.add(msglabel);            
      panel.add(Box.createRigidArea(new Dimension(5,10)));
      panel.add(CopyLabel);
      panel.add(Box.createRigidArea(new Dimension(5,10)));
      //panel.setLayout(new FlowLayout());
      
      controlPanel.add(panel);        
      mainFrame.setVisible(true); 
      
   }   
}
