package com.nt.service;

import java.util.List;
import java.util.Map;

import com.nt.entity.TravelPlan;

public interface ITravelPlanMgmtService {

	public String registerTrvelPlan(TravelPlan plan); //save operation
	public Map<Integer, String> getTravelPlanCategories(); //for select operation
	public List<TravelPlan> showAllTravelPlan(); //
	public TravelPlan showTravelPlanById(Integer planId); //
	public String updateTravelPlan(TravelPlan plan); //
	public String deleteTravelPlan(Integer planId); //
	public String changeTravelPlanStatus(Integer PlanId, String status); //

}
