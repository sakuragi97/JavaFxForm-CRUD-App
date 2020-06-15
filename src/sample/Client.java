package sample;

import java.sql.Date;

public class Client {
    private int idClient;
    private String nomPrenom;
    private String email;
    private String password;
    private String phone;
    private Date dateCreation;

    public Client(){
    }

    public Client(String nomPrenom, String email, String password, String phone) {
        this.nomPrenom = nomPrenom;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }

    public Client(int idClient, String nomPrenom, String email, String password, String phone, Date dateCreation) {
        this.idClient = idClient;
        this.nomPrenom = nomPrenom;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.dateCreation = dateCreation;
    }

    public int getIdClient() {
        return idClient;
    }

    public String getNomPrenom() {
        return nomPrenom;
    }

    public void setNomPrenom(String nomPrenom) {
        this.nomPrenom = nomPrenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    @Override
    public String toString() {
        return "Client{" +
                "idClient=" + idClient +
                ", nomPrenom='" + nomPrenom + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", dateCreation=" + dateCreation +
                '}';
    }
}
