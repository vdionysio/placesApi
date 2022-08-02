package com.clickbus.placesApi.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.clickbus.placesApi.model.City;

public class CityDto {

	private String name;

	private String uf;

	public CityDto(City city) {
		this.name = city.getName();
		this.uf = city.getState().getUf();
	}

	public String getName() {
		return name;
	}

	public String getUF() {
		return uf;
	}

	public static List<CityDto> convertFromEntity(List<City> cities) {
		return cities.stream().map(city -> new CityDto(city)).collect(Collectors.toList());
	}
}
