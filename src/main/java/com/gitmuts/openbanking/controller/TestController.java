package com.gitmuts.openbanking.controller;

import com.gitmuts.openbanking.model.Destinations;
import com.gitmuts.openbanking.model.GetTokenResponse;
import com.gitmuts.openbanking.model.PesalinkRequest;
import com.gitmuts.openbanking.model.Source;
import com.gitmuts.openbanking.service.GetToken;
import com.gitmuts.openbanking.service.PesalinkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/test")
public class TestController {

    @Autowired
    GetToken getTokenService;

    @Autowired
    PesalinkService pesalinkService;


    @GetMapping("/pesalink")
    public ResponseEntity<?> getTokenTest(){
        try{
            GetTokenResponse getTokenResponse = getTokenService.getToken();

            String response = "";
            if(getTokenResponse != null){

                response = pesalinkService.sendRequest(getSampleRequest(), getTokenResponse.getAccess_token());

            } else {
                response ="Error getting token";
            }
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            log.error("Error occurred while calling {}", "getTokenTest", e);
            return new ResponseEntity<>("Error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private PesalinkRequest getSampleRequest() {
        PesalinkRequest request = new PesalinkRequest();

        request.setMessageReference("40ca18c6765086089a1200");
        request.setCallBackUrl("http://localhost:8000");
        Source source = new Source();
        source.setAccountNumber("36001873000");
        source.setAmount("100");
        source.setTransactionCurrency("KES");
        source.setNarration("Supplier Payment");

        request.setSource(source);

        Destinations destinations = new Destinations();
        destinations.setReferenceNumber("40ca18c6765086089a712");
        destinations.setAccountNumber("54321987654321");
        destinations.setBankCode("11");
        destinations.setBranchCode("00011001");
        destinations.setAmount("100");
        destinations.setTransactionCurrency("KES");
        destinations.setNarration("Electricity Payment");

        List<Destinations> destinationsList = new ArrayList<>();
        destinationsList.add(destinations);
        request.setDestinations(destinationsList);
        return  request;
    }

}
