package com.nt.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.nt.entity.PlanCategory;
import com.nt.entity.TravelPlan;
import com.nt.repository.IPlanCategoryRepository;
import com.nt.repository.ITravelPlanRepository;

public class TravelPlanMgmtServiceImpl implements ITravelPlanMgmtService {

	@Autowired
	private ITravelPlanRepository travelPlanRepo;

	@Autowired
	private IPlanCategoryRepository planCategoryRepo;
	
	@Override
	public String registerTrvelPlan(TravelPlan plan) {
		// Save object
		
		TravelPlan saved=travelPlanRepo.save(plan);
/*		
		if(saved.getPlanId()!=null){
			return "Travel plan is saved with the id value:"+saved.getPlanId();
		}else{
			return "problem in saving the Id value";
		}
*/
		return saved.getPlanId()!=null?"Travel plan is saved with the id value:"+saved.getPlanId():"problem in saving the Id value";
	}

	@Override
	public Map<Integer, String> getTravelPlanCategories() {
		// get All TravelPlan Categories
		
		List<PlanCategory> list=planCategoryRepo.findAll();
		Map<Integer,String> categoriesMap=new HashMap<Integer,String>();
		list.forEach(category->{
			categoriesMap.put(category.getCategoryId(), category.getCategoryName());
		});
		return categoriesMap;
	}

	@Override
	public List<TravelPlan> showAllTravelPlan() {
		// 
		return travelPlanRepo.findAll();
	}
	
	
	@Override
	public TravelPlan showTravelPlanById(Integer planId) {
		
		//return travelPlanRepo.findAllById(planId).orElseThrow(()->new IllegalArgumentException("TravelPlan is not found"));
	Optional<TravelPlan> opt = travelPlanRepo.findById(planId);
	if(opt.isPresent()) {
		return opt.get();
	}else {
		throw new IllegalArgumentException("planId is not found");
	}
		
	}

	@Override
	public String updateTravelPlan(TravelPlan plan) {
		// return null;
		
		Optional<TravelPlan> opt=travelPlanRepo.findById(plan.getPlanId());
		if (opt.isPresent()) {
			//update the obj 
			travelPlanRepo.save(plan);
			return plan.getPlanId()+"Travel plan is updated";
		}else{
			return plan.getPlanId()+"Travel plan is not found";
		}
	}
	
	

	@Override
	public String deleteTravelPlan(Integer planId) {
		// return null;
		Optional<TravelPlan> opt=travelPlanRepo.findById(planId);
		//Delete The obj
		if(opt.isPresent()) {
			travelPlanRepo.deleteById(planId);
			return planId+"Travel Plan is Deleted";
		}
		return planId+"Travel plan is not found";
		
	}

	@Override
	public String changeTravelPlanStatus(Integer PlanId, String status) {
		// return null;
		
		Optional<TravelPlan> opt=travelPlanRepo.findById(PlanId);
		if(opt.isPresent()) {
			TravelPlan plan=opt.get();
			plan.setActiveSW(status);
			travelPlanRepo.save(plan);
			return PlanId+"Travel Plan Status is Changed";
		}
		else {
			return PlanId+"Travel Plan not Found";
		}
		
		
	}

}
