package org.kam.controllers;

import org.kam.dtos.lead.LeadDto;
import org.kam.dtos.lead.RestaurantLeadDto;
import org.kam.req.RestaurantReqBody;
import org.kam.res.CommonResponse;
import org.kam.services.LeadManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class LeadManagerController {

    private final LeadManagerService leadManagerService;

    @Autowired
    public LeadManagerController(LeadManagerService leadManagerService) {
        this.leadManagerService = leadManagerService;
    }

    @RequestMapping(value = "/restaurant_lead", method = RequestMethod.POST)
    public ResponseEntity<CommonResponse> postRestaurantLeads(@RequestBody RestaurantReqBody restaurantReqBody) {
        CommonResponse commonResponse = leadManagerService.postRestaurantLead(restaurantReqBody);
        return commonResponse.getSuccessfulEvent() ? new ResponseEntity<>(commonResponse, HttpStatus.OK) :
                new ResponseEntity<>(commonResponse, HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/lead_status", method = RequestMethod.GET)
    public ResponseEntity<CommonResponse<RestaurantLeadDto>> getLeadStatus(@RequestParam("lead_id") String leadId) {
        CommonResponse<RestaurantLeadDto> response = leadManagerService.getLeadStatus(leadId);
        return response.getSuccessfulEvent() ? new ResponseEntity<>(response, HttpStatus.OK) :
                new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
