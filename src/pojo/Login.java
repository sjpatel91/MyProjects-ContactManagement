/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;

import java.util.ArrayList;


public class Login {
    private long id;
    private String uname;
    private String password;
    private ArrayList<Login> arrayList = new ArrayList<Login>();
    
    public ArrayList<Login> getArrayList() {
		return arrayList;
	}

	public void setArrayList(ArrayList<Login> arrayList) {
		this.arrayList = arrayList;
	}

    public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
}
