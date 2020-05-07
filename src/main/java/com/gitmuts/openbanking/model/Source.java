package com.gitmuts.openbanking.model;

import lombok.Data;

@Data
public class Source {

    private String AccountNumber;
    private String Amount;
    private String TransactionCurrency;
    private String Narration;
}
