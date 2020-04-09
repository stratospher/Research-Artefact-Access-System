import java.awt.*;  
import java.awt.event.*;  
public class ScreenThree 
{  
        ScreenThree(){  
        Frame f= new Frame();  

        Label txt = new Label("You have permission to view other Research Work");           
        txt.setBounds(60,20,400,20);

        Label l = new Label("Researcher Name:");          
        l.setBounds(60,50,150,20); 

        Label topic = new Label("Search by Topic:");          
        topic.setBounds(40,100,150,20); 

        Label year = new Label("Search by Year:");          
        year.setBounds(40,155,150,20);

        final Label label = new Label();          
        label.setBounds(60,170,200,20); ;  

        Button b=new Button("Update");  
        b.setBounds(180,300,200,20); 
        Button goback=new Button("Logout");  
        goback.setBounds(180,350,170,20);  
        final Choice c=new Choice();  
        c.setBounds(200,100,175,20);  
        c.add("Topic 1");  
        c.add("Topic 2");  
        c.add("Topic 3");  
        c.add("Topic 4");  
        c.add("Topic 5");  

        final Choice c1=new Choice();  
        c1.setBounds(200,130,175,75);  
        c1.add("Year 1");  
        c1.add("Year 2");  
        c1.add("Year 3");  
        c1.add("Year 4");  
        c1.add("Year 5");  

        f.add(txt);f.add(l);
        f.add(c);f.add(b);f.add(goback); 
        f.add(c1); 
        f.add(topic);f.add(year);f.add(label);
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
   new ScreenThree();  
}  
}  