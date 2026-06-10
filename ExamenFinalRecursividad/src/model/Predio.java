package model;

public class Predio {

    private String npn;
    private String municipio;
    private String direccion;
    private String ficha;

    public Predio(String npn, String municipio,
                  String direccion, String ficha) {

        this.npn = npn;
        this.municipio = municipio;
        this.direccion = direccion;
        this.ficha = ficha;
    }

    public String getNpn() {
        return npn;
    }

    public String getMunicipio() {
        return municipio;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getFicha() {
        return ficha;
    }

    @Override
    public String toString() {
        return npn + " | " +
               municipio + " | " +
               direccion + " | " +
               ficha;
    }
}