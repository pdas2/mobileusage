package com.ibm.demo;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.ibm.demo.model.offerService;
import com.ibm.demo.producer.Producer;
import com.ibm.demo.repository.OfferSearchRepository;
import com.ibm.demo.repository.offerMongoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.data.mongodb.core.mapping.Document;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/offer")
public class OfferRestController {
	
	public static final Logger logger = LoggerFactory.getLogger(OfferRestController.class);

	@Autowired
	offerMongoRepository offerRepository;

	@Autowired
	OfferSearchRepository offerSearchRepository;

	@Autowired
	Producer producer;

	
	@RequestMapping(method=RequestMethod.POST, value="/addOffer")
    public String save(@RequestBody offerService offer) {
		offerRepository.save(offer);
		String msg="Order successfully received for User "+offer.getuserName()+"Ph No."+offer.getuserPhone();
		 Gson gson = new Gson();
	     String json = gson.toJson(offer);
		producer.produceMsg(json);
		logger.info(json);
		logger.info("Offer pack will be activated within 24 hr. for User: "+offer.getuserName());
        return "Offer will be activated within 24 hrs for User: "+offer.getuserName();
    }

}
