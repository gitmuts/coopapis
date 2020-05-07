package com.gitmuts.openbanking.model;

import lombok.Data;

import java.util.List;

@Data
public class PesalinkRequest {

    private String MessageReference;
    private String CallBackUrl;
    private Source Source;
    private List<Destinations> Destinations;


}
