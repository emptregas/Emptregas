package com.emptregas.emptregas.model;

import com.squareup.moshi.Json;

public class Tareas {

    @Json(name = "ciu_ent")
    private Integer ciu_ent;
    @Json(name = "ciu_rec")
    private Integer ciu_rec;
    @Json(name = "direc_ent")
    private String direc_ent;
    @Json(name = "direc_rec")
    private String direc_rec;
    @Json(name = "ent_status")
    private Integer ent_status;
    @Json(name = "nom_ent")
    private String nom_ent;
    @Json(name = "nom_rec")
    private String nom_rec;
    @Json(name = "rec_status")
    private Integer rec_status;
    @Json(name = "tel_ent")
    private String tel_ent;
    @Json(name = "tel_rec")
    private String tel_rec;

    /**
     * No args constructor for use in serialization
     *
     */
    public Tareas() {
    }

    /**
     *
     * @param tel_ent
     * @param nom_ent
     * @param direc_ent
     * @param ciu_ent
     * @param rec_status
     * @param nom_rec
     * @param ent_status
     * @param ciu_rec
     * @param tel_rec
     * @param direc_rec
     */
    public Tareas(Integer ciu_ent, Integer ciu_rec, String direc_ent, String direc_rec, Integer ent_status, String nom_ent, String nom_rec, Integer rec_status, String tel_ent, String tel_rec) {
        super();
        this.ciu_ent = ciu_ent;
        this.ciu_rec = ciu_rec;
        this.direc_ent = direc_ent;
        this.direc_rec = direc_rec;
        this.ent_status = ent_status;
        this.nom_ent = nom_ent;
        this.nom_rec = nom_rec;
        this.rec_status = rec_status;
        this.tel_ent = tel_ent;
        this.tel_rec = tel_rec;
    }

    public Integer getCiu_ent() {
        return ciu_ent;
    }

    public void setCiu_ent(Integer ciu_ent) {
        this.ciu_ent = ciu_ent;
    }

    public Integer getCiu_rec() {
        return ciu_rec;
    }

    public void setCiu_rec(Integer ciu_rec) {
        this.ciu_rec = ciu_rec;
    }

    public String getDirec_ent() {
        return direc_ent;
    }

    public void setDirec_ent(String direc_ent) {
        this.direc_ent = direc_ent;
    }

    public String getDirec_rec() {
        return direc_rec;
    }

    public void setDirec_rec(String direc_rec) {
        this.direc_rec = direc_rec;
    }

    public Integer getEnt_status() {
        return ent_status;
    }

    public void setEnt_status(Integer ent_status) {
        this.ent_status = ent_status;
    }

    public String getNom_ent() {
        return nom_ent;
    }

    public void setNom_ent(String nom_ent) {
        this.nom_ent = nom_ent;
    }

    public String getNom_rec() {
        return nom_rec;
    }

    public void setNom_rec(String nom_rec) {
        this.nom_rec = nom_rec;
    }

    public Integer getRec_status() {
        return rec_status;
    }

    public void setRec_status(Integer rec_status) {
        this.rec_status = rec_status;
    }

    public String getTel_ent() {
        return tel_ent;
    }

    public void setTel_ent(String tel_ent) {
        this.tel_ent = tel_ent;
    }

    public String getTel_rec() {
        return tel_rec;
    }

    public void setTel_rec(String tel_rec) {
        this.tel_rec = tel_rec;
    }

}
