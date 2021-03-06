/**
 * Copyright 2014 eBusiness Information, Groupe Excilys (www.ebusinessinformation.fr)
 */
package com.excilys.liferay.gatling.validator;



import java.util.ArrayList;
import java.util.List;

import com.excilys.liferay.gatling.model.Scenario;
import com.excilys.liferay.gatling.service.ScenarioLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.Validator;

public class ScenarioValidator {

	/**
	 * Validate a {@link Scenario}
	 * @param {@link Scenario} scenario 
	 * @return {@link List} errors
	 * @throws SystemException
	 */
	public static List<String> validateScenario(Scenario scenario) throws SystemException {
		List<String> errors = new ArrayList<String>();

		if (Validator.isNull(scenario.getName())) {
			errors.add("scenario-name-required");
		}

		if (!ScenarioLocalServiceUtil.isNameUnique(scenario.getName(), scenario.getSimulation_id())) {
			errors.add("scenario-name-already-used");
		}
		
		if( !Validator.isNumber(Long.toString(scenario.getDuration()))) {
			errors.add("scenario-duration-required");
		}

		if( !Validator.isNumber(Long.toString(scenario.getNumberOfUsers()))) {
			errors.add("scenario-users_per_seconds-required");
		}

		if(Validator.isNull(scenario.getGroup_id())) {
			errors.add("scenario-groupid-missing");
		}
		
		if(Validator.isNull(scenario.getSimulation_id())) {
			errors.add("scenario-simulationid-missing");
		}

		return errors;
	}

}