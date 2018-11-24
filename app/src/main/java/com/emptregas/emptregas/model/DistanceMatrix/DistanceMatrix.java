package com.emptregas.emptregas.model.DistanceMatrix;


import com.squareup.moshi.Json;

import java.io.Serializable;
import java.util.List;

public class DistanceMatrix implements Serializable
{

    @Json(name = "destination_addresses")
    public List<String> destinationAddresses = null;
    @Json(name = "origin_addresses")
    public List<String> originAddresses = null;
    @Json(name = "rows")
    public List<Row> rows = null;
    @Json(name = "status")
    public String status;
    private final static long serialVersionUID = 2744328108780913893L;

    public DistanceMatrix withDestinationAddresses(List<String> destinationAddresses) {
        this.destinationAddresses = destinationAddresses;
        return this;
    }

    public DistanceMatrix withOriginAddresses(List<String> originAddresses) {
        this.originAddresses = originAddresses;
        return this;
    }

    public DistanceMatrix withRows(List<Row> rows) {
        this.rows = rows;
        return this;
    }

    public DistanceMatrix withStatus(String status) {
        this.status = status;
        return this;
    }

}