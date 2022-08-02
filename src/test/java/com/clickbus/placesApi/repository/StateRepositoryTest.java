package com.clickbus.placesApi.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.clickbus.placesApi.model.State;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
class StateRepositoryTest {

	@Autowired
	private TestEntityManager em;
	
	@Autowired
	private StateRepository stateRepository;

	@Test
	void test() {
		String stateUf = "SP";

		State state = stateRepository.findByUf(stateUf);
		
		Assertions.assertNotNull(state);
		Assertions.assertEquals(stateUf, state.getUf());
	}

}
