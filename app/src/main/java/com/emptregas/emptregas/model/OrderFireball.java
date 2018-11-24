package com.emptregas.emptregas.model;

import com.squareup.moshi.Json;

public class OrderFireball {

    @Json(name = "date_commande")
    private String date_commande;
    @Json(name = "description")
    private String description;
    @Json(name = "fk_soc")
    private String fk_soc;
    @Json(name = "fk_statut")
    private Integer fk_statut;
    @Json(name = "price")
    private String price;
    @Json(name = "qty")
    private Integer qty;
    @Json(name = "rowid")
    private String rowid;
    @Json(name = "subprice")
    private String subprice;
    @Json(name = "tareas")
    private Tareas tareas;
    @Json(name = "total_ht")
    private String total_ht;
    @Json(name = "total_ttc")
    private String total_ttc;
    @Json(name = "met_pay")
    private String met_pay;
    @Json(name = "lug_pay")
    private String lug_pay;

    /**
     * No args constructor for use in serialization
     *
     */
    public OrderFireball() {
    }



    /**
     *
     * @param total_ttc
     * @param total_ht
     * @param price
     * @param date_commande
     * @param description
     * @param fk_statut
     * @param tareas
     * @param qty
     * @param subprice
     * @param fk_soc
     * @param rowid
     */
    public OrderFireball(String date_commande, String description, String fk_soc, Integer fk_statut, String price, Integer qty, String rowid, String subprice, Tareas tareas, String total_ht, String total_ttc,String met_pay,String lug_pay) {
        super();
        this.date_commande = date_commande;
        this.description = description;
        this.fk_soc = fk_soc;
        this.fk_statut = fk_statut;
        this.price = price;
        this.qty = qty;
        this.rowid = rowid;
        this.subprice = subprice;
        this.tareas = tareas;
        this.total_ht = total_ht;
        this.total_ttc = total_ttc;
        this.met_pay = met_pay;

        this.lug_pay = lug_pay;
    }

    public String getDate_commande() {
        return date_commande;
    }

    public void setDate_commande(String date_commande) {
        this.date_commande = date_commande;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFk_soc() {
        return fk_soc;
    }

    public void setFk_soc(String fk_soc) {
        this.fk_soc = fk_soc;
    }

    public Integer getFk_statut() {
        return fk_statut;
    }

    public void setFk_statut(Integer fk_statut) {
        this.fk_statut = fk_statut;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public String getRowid() {
        return rowid;
    }

    public void setRowid(String rowid) {
        this.rowid = rowid;
    }

    public String getSubprice() {
        return subprice;
    }

    public void setSubprice(String subprice) {
        this.subprice = subprice;
    }

    public Tareas getTareas() {
        return tareas;
    }

    public void setTareas(Tareas tareas) {
        this.tareas = tareas;
    }

    public String getTotal_ht() {
        return total_ht;
    }

    public void setTotal_ht(String total_ht) {
        this.total_ht = total_ht;
    }

    public String getTotal_ttc() {
        return total_ttc;
    }

    public void setTotal_ttc(String total_ttc) {
        this.total_ttc = total_ttc;
    }
    public String getMet_pay() {
        return met_pay;
    }

    public String getLug_pay() {
        return lug_pay;
    }

    public void setMet_pay(String met_pay) {
        this.met_pay = met_pay;
    }

    public void setLug_pay(String lug_pay) {
        this.lug_pay = lug_pay;
    }
}