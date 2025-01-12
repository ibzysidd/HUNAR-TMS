package com.hunar.api.email;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.hunar.api.entity.CustomerEntity;
import com.hunar.api.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class EmailController {
	
	private Long count =1l;
	@Autowired
	private EmailService service;

	@Autowired
	CustomerRepository repo;
	
	@PostMapping("/email")
	public EmailResponse sendEmail(@RequestBody EmailRequest request) {
//		CustomerEntity cEntity = repo.findById(1).get();
		Map<String, Object> model = new HashMap<>();
//		model.put("name", cEntity.getCustomerName());
//		model.put("to",cEntity.getCustomerEmail());
//		model.put("date", new Date().toString());
//		model.put("bookingID", String.valueOf(cEntity.getCustomerId()));
//		model.put("sdate", cEntity.getCreatedBy().toString());
//		model.put("edate", cEntity.getLastModifiedDate().toString());
//		model.put("rooms", cEntity.getMobileNo());
//		model.put("halls", cEntity.getCreatedBy());
		model.put("name", "IBRAHIM SIDDIQUI");
		model.put("to","ibzysidd@gmail.com");
		model.put("date", new Date().toString());
		model.put("bookingID", "ORD-121213");
		model.put("sdate", LocalDate.now().toString());
		model.put("edate", LocalDate.now().toString());
		model.put("rooms", "15");
		model.put("halls", "10");
		System.out.println("Your APIs request count : "+count);
		count++;
		return service.sendEmail(request, model);
	}
}