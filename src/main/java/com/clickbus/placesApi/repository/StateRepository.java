package com.clickbus.placesApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clickbus.placesApi.model.State;

public interface StateRepository extends JpaRepository<State, Long> {

	public State findByUf(String uf);
}
