package dam.isi.frsf.utn.edu.ar.Modelo;

public class Cliente {
    private String mail;
    private String cuil;

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getCuil() {
        return cuil;
    }

    public void setCuil(String cuil) {
        this.cuil = cuil;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "mail='" + mail + '\'' +
                ", cuil='" + cuil + '\'' +
                '}';
    }
}
