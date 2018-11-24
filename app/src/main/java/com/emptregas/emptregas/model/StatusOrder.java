package com.emptregas.emptregas.model;

public class StatusOrder {
    private Integer idStatus;

    public StatusOrder(Integer idStatus) {
        this.idStatus = idStatus;
    }

    public String getNameOrderStatus(){
        String nombre="";
        switch (idStatus){
            case 1:
                nombre= "creado";
                return nombre;
            case 2:
                nombre= "Pendiente";
                break;
            case 3:
                nombre = "Asignado";
                break;
            case 4:
                nombre = "En Curso";
                break;

            case 5:
                nombre = "Completado";
                break;

            case 6:
                nombre= "Cancelado";
                break;

        }
        return nombre;
    }
}
