package Classes;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Review {
    private int idRecenzie;
    private String continut;
    private int idProdus;
    private int idClient;
    private LocalDate data;

    public Review(int idRecenzie, String continut, int idProdus, int idClient, LocalDate data)
    {
        this.idRecenzie = idRecenzie;
        this.continut = continut;
        this.idProdus = idProdus;
        this.idClient = idClient;
        this.data = data;
    }


    public int getIdRecenzie() {
        return idRecenzie;
    }

    public void setIdRecenzie(int idRecenzie) {
        this.idRecenzie = idRecenzie;
    }

    public String getContinut() {
        return continut;
    }

    public void setContinut(String continut) {
        this.continut = continut;
    }

    public int getIdProdus() {
        return idProdus;
    }

    public void setIdProdus(int idProdus) {
        this.idProdus = idProdus;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    @Override
    public String toString()
    {
        return continut + "  ; idProdus : " + idProdus + ", idClient : " + idClient + " , " + data;
    }
}
