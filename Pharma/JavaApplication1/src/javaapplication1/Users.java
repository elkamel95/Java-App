/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

/**
 *
 * @author 2
 */
public class Users {
    String Login;
    String Motpasse;
int Role ;
int id ;
  public Users() {
       
    }
    public Users(String Login, String Motpasse, int Role) {
        this.Login = Login;
        this.Motpasse = Motpasse;
        this.Role = Role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return Login;
    }

    public void setLogin(String Login) {
        this.Login = Login;
    }

    public String getMotpasse() {
        return Motpasse;
    }

    public void setMotpasse(String Motpasse) {
        this.Motpasse = Motpasse;
    }

    public int getRole() {
        return Role;
    }

    public void setRole(int Role) {
        this.Role = Role;
    }
   
}
