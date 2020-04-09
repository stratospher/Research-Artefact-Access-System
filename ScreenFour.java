import java.awt.*;  
import java.awt.event.*;  
public class ScreenFour extends Frame{  
Label l1,l2,l3;  
TextArea area;  
TextField tf;
ScreenFour(){  
    l1=new Label("Welcome Researcher!");  
    l1.setBounds(60,50,200,30);  
    l2=new Label("+Add Research Details     TITLE:");    
    l2.setBounds(20,100,210,30);  
    tf=new TextField();
    tf.setBounds(250,100,70,30); 
    area=new TextArea();  
    area.setBounds(20,150,300,300);   
    add(l1);add(l2);add(area);  add(tf);
    setSize(500,600);  
    setLayout(null);  
    setVisible(true);  
    l2=new Label("+Add Research Details");  
    l2.setBounds(20,100,200,30);  
    Button b=new Button("Update");  
    b.setBounds(250,470,200,20); 
    add(b);
    l3=new Label("+View existing Research");  
    l3.setBounds(20,500,200,30);  
    add(l3);
}  

public static void main(String[] args) {  
    new ScreenFour();  
}  
}  