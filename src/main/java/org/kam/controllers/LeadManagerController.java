package org.kam.controllers;

import org.kam.req.RestaurantReqBody;
import org.kam.res.CommonResponse;
import org.kam.services.LeadManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LeadManagerController {

    private final LeadManagerService leadManagerService;

    @Autowired
    public LeadManagerController(LeadManagerService leadManagerService) {
        this.leadManagerService = leadManagerService;
    }

    @PostMapping("/restaurant_lead")
    public ResponseEntity<CommonResponse> postRestaurantLeads(@RequestBody RestaurantReqBody restaurantReqBody) {
        CommonResponse commonResponse = leadManagerService.postRestaurantLead(restaurantReqBody);
        return commonResponse.getSuccessfulEvent() ? new ResponseEntity<>(commonResponse, HttpStatus.OK) :
                new ResponseEntity<>(commonResponse, HttpStatus.BAD_REQUEST);
    }
}
