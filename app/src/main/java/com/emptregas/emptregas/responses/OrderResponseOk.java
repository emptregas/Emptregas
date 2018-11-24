package com.emptregas.emptregas.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderResponseOk {

    @SerializedName("fk_soc")
    @Expose
    private Integer fk_soc;
    @SerializedName("fk_statut")
    @Expose
    private Integer fk_statut;
    @SerializedName("total_ht")
    @Expose
    private Object total_ht;
    @SerializedName("date_commande")
    @Expose
    private String date_commande;
    @SerializedName("tms")
    @Expose
    private String tms;
    @SerializedName("date_creation")
    @Expose
    private String date_creation;
    @SerializedName("rowid")
    @Expose
    private Integer rowid;
    @SerializedName("ref")
    @Expose
    private String ref;

    public Integer getFk_soc() {
        return fk_soc;
    }

    public void setFk_soc(Integer fk_soc) {
        this.fk_soc = fk_soc;
    }

    public Integer getFk_statut() {
        return fk_statut;
    }

    public void setFk_statut(Integer fk_statut) {
        this.fk_statut = fk_statut;
    }

    public Object getTotal_ht() {
        return total_ht;
    }

    public void setTotal_ht(Object total_ht) {
        this.total_ht = total_ht;
    }

    public String getDate_commande() {
        return date_commande;
    }

    public void setDate_commande(String date_commande) {
        this.date_commande = date_commande;
    }

    public String getTms() {
        return tms;
    }

    public void setTms(String tms) {
        this.tms = tms;
    }

    public String getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(String date_creation) {
        this.date_creation = date_creation;
    }

    public Integer getRowid() {
        return rowid;
    }

    public void setRowid(Integer rowid) {
        this.rowid = rowid;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

}
