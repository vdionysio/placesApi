package com.clickbus.placesApi.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clickbus.placesApi.controller.dto.CityDto;
import com.clickbus.placesApi.controller.form.CityForm;
import com.clickbus.placesApi.model.City;
import com.clickbus.placesApi.repository.CityRepository;
import com.clickbus.placesApi.repository.StateRepository;

@RestController
@RequestMapping("/cities")
public class CityController {

	@Autowired
	private CityRepository cityRepository;

	@Autowired
	private StateRepository stateRepository;

	@GetMapping
	public List<CityDto> list() {
		List<City> cities = cityRepository.findAll();
		return CityDto.convertFromEntity(cities);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getCity(@PathVariable Long id) {
		Optional<City> city = cityRepository.findById(id);
		if (city.isPresent()) {
			return ResponseEntity.ok(new CityDto(city.get()));
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<CityDto> register(@Valid @RequestBody CityForm cityForm) {
		City city = cityForm.convertFromForm(stateRepository);
		cityRepository.save(city);
		return ResponseEntity.created(null).body(new CityDto(city));
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody CityForm cityForm) {
		Optional<City> city = cityRepository.findById(id);
		if (city.isPresent()) {
			cityForm.updateCity(city.get(), stateRepository);
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Optional<City> city = cityRepository.findById(id);
		if (city.isPresent()) {
			cityRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
	}
}
