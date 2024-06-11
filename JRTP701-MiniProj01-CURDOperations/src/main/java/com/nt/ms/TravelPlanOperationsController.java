package com.nt.ms;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nt.entity.TravelPlan;
import com.nt.service.ITravelPlanMgmtService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/travelplan/api") //Global path(optional)
public class TravelPlanOperationsController {

	private ITravelPlanMgmtService planService;
	
	@GetMapping("/categories")
	public ResponseEntity<?>showTravelPlanCategories(){
		//Invoke service class methods
		try {
			Map<Integer,String>mapCategories=planService.getTravelPlanCategories();
			return new ResponseEntity<Map<Integer,String>>(mapCategories,HttpStatus.OK);	
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}
	
	@PostMapping("/register")
	public ResponseEntity<String> saveTourPlan(@RequestBody TravelPlan plan){
		//use sevice
		try {
			String msg=planService.registerTrvelPlan(plan);
			return new ResponseEntity<String>(msg,HttpStatus.CREATED);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}
	
	@GetMapping("/all")
	public ResponseEntity<?>getAllTravelPlans(){
		//use service
		try {
			List<TravelPlan> list =planService.showAllTravelPlan();
			return new ResponseEntity<List<TravelPlan>>(list,HttpStatus.OK);	
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}
	
	@GetMapping("/find/{planId}")
	public ResponseEntity<?>getTravelPlanById(@PathVariable Integer planId){
		//Invoke service class methods
		try {
			TravelPlan plan=planService.showTravelPlanById(planId);
			return new ResponseEntity<TravelPlan>(plan,HttpStatus.OK);	
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}
	
	@PutMapping("/update")
	public ResponseEntity<String> updateTravelPlan(@RequestBody TravelPlan plan){
		//use service
		try {
			String msg=planService.updateTravelPlan(plan);
			return new ResponseEntity<String>(msg,HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}
	
	
	@DeleteMapping("/delite/{planId}")
	public ResponseEntity<?>removeTravelPlanByPlanId(@PathVariable Integer planId){
		//use service
		try {
			String msg=planService.deleteTravelPlan(planId);
			return new ResponseEntity<String>(msg,HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/status-change/{planId}/{status}")
	public ResponseEntity<String>removeTravelPlanByPlanId(@PathVariable Integer planId, @PathVariable String status ){
		//use service
		try {
			String msg=planService.changeTravelPlanStatus(planId,status);
			return new ResponseEntity<String>(msg,HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}
	
}
