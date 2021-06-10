package Classes;

public class Form {

    private int formId;
    private int clientId;
    private String nume;
    private String prenume;
    private int varsta;
    private String email;
    private String ultimaUnitateInvatamantAbsolvita;
    private String altaOcupatie;

    public Form(int formId, int clientId,  String nume, String prenume, int varsta, String email, String ultimaUnitateInvatamantAbsolvita, String altaOcupatie)
    {
        this.formId = formId;
        this.clientId = clientId;
        this.nume = nume;
        this.prenume = prenume;
        this.varsta = varsta;
        this.email = email;
        this.ultimaUnitateInvatamantAbsolvita = ultimaUnitateInvatamantAbsolvita;
        this.altaOcupatie = altaOcupatie;
    }

    public int getFormId() {
        return formId;
    }

    public void setFormId(int formId) {
        this.formId = formId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public int getVarsta() {
        return varsta;
    }

    public void setVarsta(int varsta) {
        this.varsta = varsta;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUltimaUnitateInvatamantAbsolvita() {
        return ultimaUnitateInvatamantAbsolvita;
    }

    public void setUltimaUnitateInvatamantAbsolvita(String ultimaUnitateInvatamantAbsolvita) {
        this.ultimaUnitateInvatamantAbsolvita = ultimaUnitateInvatamantAbsolvita;
    }

    public String getAltaOcupatie() {
        return altaOcupatie;
    }

    public void setAltaOcupatie(String altaOcupatie) {
        this.altaOcupatie = altaOcupatie;
    }

    @Override
    public String toString()
    {
        return "ID Form : " + formId + "; Last name : " + nume + "; First name : " + prenume +
                "; age : " + varsta + "; email : " + email + " ; Last unit graduated : " +
                ultimaUnitateInvatamantAbsolvita + " ; Works elsewhere : " + altaOcupatie;
    }
}
