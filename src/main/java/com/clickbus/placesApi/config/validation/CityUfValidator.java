package com.clickbus.placesApi.config.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.clickbus.placesApi.model.State;
import com.clickbus.placesApi.repository.StateRepository;

public class CityUfValidator implements ConstraintValidator<CityUfValidation, String> {
	@Autowired
	StateRepository stateRepository;

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		State state = stateRepository.findByUf(value);
		if (state == null) {
			return false;
		}
		return true;
	}
}
