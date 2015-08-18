/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package demo;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Dave Syer
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = FleetLocationServiceApplication.class)
@WebAppConfiguration
public class FleetLocationTests {

	@Autowired
	ObjectMapper mapper;

	@Autowired
	LocationRepository repository;

	@Test
	public void saveAndFindAll() throws Exception {
		List<Location> value = this.mapper.readValue(new ClassPathResource("fleet.json").getInputStream(), new TypeReference<List<Location>>() {
		});
		assertEquals(4, value.size());
		this.repository.save(value);
		Iterable<Location> vehicles = this.repository.findAll();
		// TODO: findByVin("1FUJGBDV20LBZ2345", new PageRequest(0, 20));
		assertEquals(4, getList(vehicles).size());
	}

	private List<Location> getList(Iterable<Location> vehicles) {
		ArrayList<Location> list = new ArrayList<Location>();
		for (Location location : vehicles) {
			list.add(location);
		}
		return list;
	}

}
