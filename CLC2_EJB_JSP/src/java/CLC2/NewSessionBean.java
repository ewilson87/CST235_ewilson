/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLC2;

import java.io.Serializable;
import javax.ejb.Stateless;
import javax.inject.Named;

/**
 *
 * @author ewwil
 */
@Stateless
@Named
public class NewSessionBean implements Serializable {

    private String name = "";
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    private static final long serialVersionUID = -3253502106650968439L;

    public String getName() {
        return "Hello " + name + "!";
    }

    public void setName(String name) {
        this.name = name;
    }

}
