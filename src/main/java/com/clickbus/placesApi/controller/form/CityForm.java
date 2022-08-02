package com.clickbus.placesApi.controller.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.clickbus.placesApi.config.validation.CityUfValidation;
import com.clickbus.placesApi.model.City;
import com.clickbus.placesApi.model.State;
import com.clickbus.placesApi.repository.StateRepository;

public class CityForm {

	@NotNull
	private String name;

	@NotNull
	@Pattern(regexp = "[A-Za-z]{2}", message = "UF REGEX Problem")
	@CityUfValidation
	private String uf;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public City convertFromForm(StateRepository stateRepository) {
		City city = new City();
		State state = stateRepository.findByUf(uf);
		city.setName(name);
		city.setState(state);
		return city;
	}
	
	public void updateCity(City city, StateRepository stateRepository) {
		city.setName(name);
		State state = stateRepository.findByUf(uf);
		city.setState(state);
	}
}
