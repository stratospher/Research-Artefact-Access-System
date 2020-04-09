import java.awt.*;  
import java.awt.event.*;  
public class ScreenTwo_1 
{  
        ScreenTwo_1(){  
        Frame f= new Frame();  

        Label l = new Label("Researcher Name:");          
        l.setAlignment(Label.CENTER);  
        l.setSize(400,75); 

        final Label label = new Label();          
        label.setAlignment(Label.CENTER);  
        label.setSize(400,175);  
        Button b=new Button("Select Research Work");  
        b.setBounds(200,200,170,20); 
        Button goback=new Button("Go Back");  
        goback.setBounds(200,250,170,20);  
        final Choice c=new Choice();  
        c.setBounds(100,100,175,75);  
        c.add("Work 1");  
        c.add("Work 2");  
        c.add("Work 3");  
        c.add("Work 4");  
        c.add("Work 5");  
        f.add(l);
        f.add(c);f.add(label);f.add(b);f.add(goback);  
        f.setSize(400,400);  
        f.setLayout(null);  
        f.setVisible(true);  
        b.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {       
         String data = "Work Selected: "+ c.getItem(c.getSelectedIndex());  
         label.setText(data);  
        }  
        });           
        }  
public static void main(String args[])  
{  
   new ScreenTwo_1();  
}  
}  