import java.awt.*;  
import java.awt.event.*;  
public class ScreenTwo  
{  
        ScreenTwo(){  
        Frame f= new Frame();  
        final Label label = new Label();          
        label.setAlignment(Label.CENTER);  
        label.setSize(400,100);  
        Button b=new Button("View Research Work");  
        b.setBounds(200,200,170,20);  
        final Choice c=new Choice();  
        c.setBounds(100,100,175,75);  
        c.add("Researcher 1");  
        c.add("Researcher 2");  
        c.add("Researcher 3");  
        c.add("Researcher 4");  
        c.add("Researcher 5");  
        f.add(c);f.add(label); f.add(b);  
        f.setSize(400,400);  
        f.setLayout(null);  
        f.setVisible(true);  
        b.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {       
         String data = "Researcher Selected: "+ c.getItem(c.getSelectedIndex());  
         label.setText(data);  
        }  
        });           
        }  
public static void main(String args[])  
{  
   new ScreenTwo();  
}  
}  