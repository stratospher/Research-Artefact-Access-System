import java.awt.*;
import java.awt.event.*;
class ScreenOne extends Frame
{
    TextField name,pass;
    Button b1,b2;
    ScreenOne()
    {
        setLayout(new FlowLayout());
        this.setLayout(null);
        Label n=new Label("Name:",Label.CENTER);
        Label p=new Label("Password:",Label.CENTER);
        name=new TextField(20);
        pass=new TextField(20);
        pass.setEchoChar('#');
        b1=new Button("submit");
        b2=new Button("cancel");
        this.add(n);
        this.add(name);
        this.add(p);
        this.add(pass);
        this.add(b1);
        this.add(b2);
        n.setBounds(70,90,90,60);
        p.setBounds(70,130,90,60);
        name.setBounds(200,110,90,20);
        pass.setBounds(200,150,90,20);
        b1.setBounds(100,260,70,40);
        b2.setBounds(180,260,70,40);
 
    }
    public static void main(String args[])
    {
        ScreenOne ml=new ScreenOne();
        ml.setVisible(true);
        ml.setSize(400,400);
        ml.setTitle("Login");
 
    }
}
