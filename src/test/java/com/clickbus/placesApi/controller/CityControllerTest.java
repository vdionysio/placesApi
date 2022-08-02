package com.clickbus.placesApi.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.clickbus.placesApi.model.City;
import com.clickbus.placesApi.model.State;
import com.clickbus.placesApi.repository.CityRepository;

@SpringBootTest
@AutoConfigureMockMvc
class CityControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CityRepository cityRepository;

	private List<City> citiesList;

	@BeforeEach
	void setUp() {
		this.citiesList = new ArrayList<City>();
		City genericCity = new City();
		State genericState = new State();
		genericState.setName("São Paulo");
		genericState.setUf("SP");
		genericCity.setName("Botucatu");
		genericCity.setState(genericState);
		this.citiesList.add(genericCity);
	}

	@Test
	void shouldReturnAListOfCities() throws Exception {
		BDDMockito.given(cityRepository.findAll()).willReturn(citiesList);

		mockMvc.perform(get("/cities")).andExpect(status().isOk()).andExpect(jsonPath("$.size()", is(1)));
	}

	@Test
	void shouldReturn404WhenFindUserByIdWithInvalidId() throws Exception {
		BDDMockito.given(cityRepository.findById(1L)).willReturn(null);

		mockMvc.perform(get("/cities/4")).andExpect(status().isNotFound());
	}
}
