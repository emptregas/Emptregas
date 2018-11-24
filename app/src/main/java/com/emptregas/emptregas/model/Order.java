        package com.emptregas.emptregas.model;

        import com.squareup.moshi.Json;

        public class Order {

            @Json(name = "rowid")
            private Integer rowid;
            @Json(name = "ref")
            private String ref;
            @Json(name = "entity")
            private Integer entity;
            @Json(name = "ref_ext")
            private Object refExt;
            @Json(name = "ref_int")
            private Object refInt;
            @Json(name = "ref_client")
            private Object refClient;
            @Json(name = "fk_soc")
            private Integer fkSoc;
            @Json(name = "fk_projet")
            private Object fkProjet;
            @Json(name = "tms")
            private String tms;
            @Json(name = "date_creation")
            private String dateCreation;
            @Json(name = "date_valid")
            private Object dateValid;
            @Json(name = "date_cloture")
            private Object dateCloture;
            @Json(name = "date_commande")
            private String dateCommande;
            @Json(name = "fk_user_author")
            private Integer fkUserAuthor;
            @Json(name = "fk_user_modif")
            private Object fkUserModif;
            @Json(name = "fk_user_valid")
            private Object fkUserValid;
            @Json(name = "fk_user_cloture")
            private Object fkUserCloture;
            @Json(name = "source")
            private Object source;
            @Json(name = "fk_statut")
            private Integer fkStatut;
            @Json(name = "amount_ht")
            private Integer amountHt;
            @Json(name = "remise_percent")
            private Integer remisePercent;
            @Json(name = "remise_absolue")
            private Integer remiseAbsolue;
            @Json(name = "remise")
            private Integer remise;
            @Json(name = "tva")
            private Integer tva;
            @Json(name = "localtax1")
            private Integer localtax1;
            @Json(name = "localtax2")
            private Integer localtax2;
            @Json(name = "total_ht")
            private Integer totalHt;
            @Json(name = "total_ttc")
            private Integer totalTtc;
            @Json(name = "note_private")
            private Object notePrivate;
            @Json(name = "note_public")
            private Object notePublic;
            @Json(name = "model_pdf")
            private Object modelPdf;
            @Json(name = "facture")
            private Integer facture;
            @Json(name = "fk_account")
            private Object fkAccount;
            @Json(name = "fk_currency")
            private Object fkCurrency;
            @Json(name = "fk_cond_reglement")
            private Object fkCondReglement;
            @Json(name = "fk_mode_reglement")
            private Object fkModeReglement;
            @Json(name = "date_livraison")
            private Object dateLivraison;
            @Json(name = "fk_shipping_method")
            private Object fkShippingMethod;
            @Json(name = "fk_warehouse")
            private Object fkWarehouse;
            @Json(name = "fk_availability")
            private Object fkAvailability;
            @Json(name = "fk_input_reason")
            private Object fkInputReason;
            @Json(name = "fk_delivery_address")
            private Object fkDeliveryAddress;
            @Json(name = "import_key")
            private Object importKey;
            @Json(name = "extraparams")
            private Object extraparams;
            @Json(name = "fk_incoterms")
            private Object fkIncoterms;
            @Json(name = "location_incoterms")
            private Object locationIncoterms;
            @Json(name = "fk_multicurrency")
            private Object fkMulticurrency;
            @Json(name = "multicurrency_code")
            private Object multicurrencyCode;
            @Json(name = "multicurrency_tx")
            private Integer multicurrencyTx;
            @Json(name = "multicurrency_total_ht")
            private Integer multicurrencyTotalHt;
            @Json(name = "multicurrency_total_tva")
            private Integer multicurrencyTotalTva;
            @Json(name = "multicurrency_total_ttc")
            private Integer multicurrencyTotalTtc;

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

            public Integer getEntity() {
                return entity;
            }

            public void setEntity(Integer entity) {
                this.entity = entity;
            }

            public Object getRefExt() {
                return refExt;
            }

            public void setRefExt(Object refExt) {
                this.refExt = refExt;
            }

            public Object getRefInt() {
                return refInt;
            }

            public void setRefInt(Object refInt) {
                this.refInt = refInt;
            }

            public Object getRefClient() {
                return refClient;
            }

            public void setRefClient(Object refClient) {
                this.refClient = refClient;
            }

            public Integer getFkSoc() {
                return fkSoc;
            }

            public void setFkSoc(Integer fkSoc) {
                this.fkSoc = fkSoc;
            }

            public Object getFkProjet() {
                return fkProjet;
            }

            public void setFkProjet(Object fkProjet) {
                this.fkProjet = fkProjet;
            }

            public String getTms() {
                return tms;
            }

            public void setTms(String tms) {
                this.tms = tms;
            }

            public String getDateCreation() {
                return dateCreation;
            }

            public void setDateCreation(String dateCreation) {
                this.dateCreation = dateCreation;
            }

            public Object getDateValid() {
                return dateValid;
            }

            public void setDateValid(Object dateValid) {
                this.dateValid = dateValid;
            }

            public Object getDateCloture() {
                return dateCloture;
            }

            public void setDateCloture(Object dateCloture) {
                this.dateCloture = dateCloture;
            }

            public String getDateCommande() {
                return dateCommande;
            }

            public void setDateCommande(String dateCommande) {
                this.dateCommande = dateCommande;
            }

            public Integer getFkUserAuthor() {
                return fkUserAuthor;
            }

            public void setFkUserAuthor(Integer fkUserAuthor) {
                this.fkUserAuthor = fkUserAuthor;
            }

            public Object getFkUserModif() {
                return fkUserModif;
            }

            public void setFkUserModif(Object fkUserModif) {
                this.fkUserModif = fkUserModif;
            }

            public Object getFkUserValid() {
                return fkUserValid;
            }

            public void setFkUserValid(Object fkUserValid) {
                this.fkUserValid = fkUserValid;
            }

            public Object getFkUserCloture() {
                return fkUserCloture;
            }

            public void setFkUserCloture(Object fkUserCloture) {
                this.fkUserCloture = fkUserCloture;
            }

            public Object getSource() {
                return source;
            }

            public void setSource(Object source) {
                this.source = source;
            }

            public Integer getFkStatut() {
                return fkStatut;
            }

            public void setFkStatut(Integer fkStatut) {
                this.fkStatut = fkStatut;
            }

            public Integer getAmountHt() {
                return amountHt;
            }

            public void setAmountHt(Integer amountHt) {
                this.amountHt = amountHt;
            }

            public Integer getRemisePercent() {
                return remisePercent;
            }

            public void setRemisePercent(Integer remisePercent) {
                this.remisePercent = remisePercent;
            }

            public Integer getRemiseAbsolue() {
                return remiseAbsolue;
            }

            public void setRemiseAbsolue(Integer remiseAbsolue) {
                this.remiseAbsolue = remiseAbsolue;
            }

            public Integer getRemise() {
                return remise;
            }

            public void setRemise(Integer remise) {
                this.remise = remise;
            }

            public Integer getTva() {
                return tva;
            }

            public void setTva(Integer tva) {
                this.tva = tva;
            }

            public Integer getLocaltax1() {
                return localtax1;
            }

            public void setLocaltax1(Integer localtax1) {
                this.localtax1 = localtax1;
            }

            public Integer getLocaltax2() {
                return localtax2;
            }

            public void setLocaltax2(Integer localtax2) {
                this.localtax2 = localtax2;
            }

            public Integer getTotalHt() {
                return totalHt;
            }

            public void setTotalHt(Integer totalHt) {
                this.totalHt = totalHt;
            }

            public Integer getTotalTtc() {
                return totalTtc;
            }

            public void setTotalTtc(Integer totalTtc) {
                this.totalTtc = totalTtc;
            }

            public Object getNotePrivate() {
                return notePrivate;
            }

            public void setNotePrivate(Object notePrivate) {
                this.notePrivate = notePrivate;
            }

            public Object getNotePublic() {
                return notePublic;
            }

            public void setNotePublic(Object notePublic) {
                this.notePublic = notePublic;
            }

            public Object getModelPdf() {
                return modelPdf;
            }

            public void setModelPdf(Object modelPdf) {
                this.modelPdf = modelPdf;
            }

            public Integer getFacture() {
                return facture;
            }

            public void setFacture(Integer facture) {
                this.facture = facture;
            }

            public Object getFkAccount() {
                return fkAccount;
            }

            public void setFkAccount(Object fkAccount) {
                this.fkAccount = fkAccount;
            }

            public Object getFkCurrency() {
                return fkCurrency;
            }

            public void setFkCurrency(Object fkCurrency) {
                this.fkCurrency = fkCurrency;
            }

            public Object getFkCondReglement() {
                return fkCondReglement;
            }

            public void setFkCondReglement(Object fkCondReglement) {
                this.fkCondReglement = fkCondReglement;
            }

            public Object getFkModeReglement() {
                return fkModeReglement;
            }

            public void setFkModeReglement(Object fkModeReglement) {
                this.fkModeReglement = fkModeReglement;
            }

            public Object getDateLivraison() {
                return dateLivraison;
            }

            public void setDateLivraison(Object dateLivraison) {
                this.dateLivraison = dateLivraison;
            }

            public Object getFkShippingMethod() {
                return fkShippingMethod;
            }

            public void setFkShippingMethod(Object fkShippingMethod) {
                this.fkShippingMethod = fkShippingMethod;
            }

            public Object getFkWarehouse() {
                return fkWarehouse;
            }

            public void setFkWarehouse(Object fkWarehouse) {
                this.fkWarehouse = fkWarehouse;
            }

            public Object getFkAvailability() {
                return fkAvailability;
            }

            public void setFkAvailability(Object fkAvailability) {
                this.fkAvailability = fkAvailability;
            }

            public Object getFkInputReason() {
                return fkInputReason;
            }

            public void setFkInputReason(Object fkInputReason) {
                this.fkInputReason = fkInputReason;
            }

            public Object getFkDeliveryAddress() {
                return fkDeliveryAddress;
            }

            public void setFkDeliveryAddress(Object fkDeliveryAddress) {
                this.fkDeliveryAddress = fkDeliveryAddress;
            }

            public Object getImportKey() {
                return importKey;
            }

            public void setImportKey(Object importKey) {
                this.importKey = importKey;
            }

            public Object getExtraparams() {
                return extraparams;
            }

            public void setExtraparams(Object extraparams) {
                this.extraparams = extraparams;
            }

            public Object getFkIncoterms() {
                return fkIncoterms;
            }

            public void setFkIncoterms(Object fkIncoterms) {
                this.fkIncoterms = fkIncoterms;
            }

            public Object getLocationIncoterms() {
                return locationIncoterms;
            }

            public void setLocationIncoterms(Object locationIncoterms) {
                this.locationIncoterms = locationIncoterms;
            }

            public Object getFkMulticurrency() {
                return fkMulticurrency;
            }

            public void setFkMulticurrency(Object fkMulticurrency) {
                this.fkMulticurrency = fkMulticurrency;
            }

            public Object getMulticurrencyCode() {
                return multicurrencyCode;
            }

            public void setMulticurrencyCode(Object multicurrencyCode) {
                this.multicurrencyCode = multicurrencyCode;
            }

            public Integer getMulticurrencyTx() {
                return multicurrencyTx;
            }

            public void setMulticurrencyTx(Integer multicurrencyTx) {
                this.multicurrencyTx = multicurrencyTx;
            }

            public Integer getMulticurrencyTotalHt() {
                return multicurrencyTotalHt;
            }

            public void setMulticurrencyTotalHt(Integer multicurrencyTotalHt) {
                this.multicurrencyTotalHt = multicurrencyTotalHt;
            }

            public Integer getMulticurrencyTotalTva() {
                return multicurrencyTotalTva;
            }

            public void setMulticurrencyTotalTva(Integer multicurrencyTotalTva) {
                this.multicurrencyTotalTva = multicurrencyTotalTva;
            }

            public Integer getMulticurrencyTotalTtc() {
                return multicurrencyTotalTtc;
            }

            public void setMulticurrencyTotalTtc(Integer multicurrencyTotalTtc) {
                this.multicurrencyTotalTtc = multicurrencyTotalTtc;
            }

        }