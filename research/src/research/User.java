/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package research;

/**
 *
 * @author ruhithomas
 */
class User {
    
    private String username;
    private int  id;
    
    public User(String username,int  id)
    {
        this.id=id;
        this.username=username; 
    }
    
    public int getid()
    {
        return id;
    }
    
    public String getusername()
    {
        return username;
    }
}
