/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package research;

import java.awt.Button;

/**
 *
 * @author ruhithomas
 */
public class Researchfile {
    
    private String filename;
    private String topic;
    private int  year;
    private String filepath;
    private Button button;
    
    public Researchfile(String filename,String topic,int year,String filepath)
    {
        this.filename=filename;
        this.topic=topic;
        this.year=year;
        this.filepath=filepath;
        this.button=new Button("hi");
    }
    
    public String getfilename()
    {
        return filename;
    }
    
    public String gettopic()
    {
        return topic;
    }
    
    public int getyear()
    {
        return year;
    }
    
    public String getfilepath()
    {
        return filepath;
    }
    
    public Button getbutton()
    {
        return button;
    }
    
    
}
