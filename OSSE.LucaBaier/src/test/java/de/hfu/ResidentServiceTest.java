package de.hfu;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import de.hfu.residents.domain.Resident;
import de.hfu.residents.repository.ResidentRepository;
import de.hfu.residents.service.BaseResidentService;
import de.hfu.residents.service.ResidentServiceException;

public class ResidentServiceTest {

	@Test
	public void getFilteredResidentsTest() {
		SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
		Resident pattern1 = null;
		Resident pattern2 = null;
		Resident pattern3 = null;
		
		BaseResidentService testService = new BaseResidentService();
		testService.setResidentRepository(new ResidentRepositoryStub());
		
		try {
			pattern1 = new Resident();
			pattern1.setGivenName("Ma*");
			pattern1.setCity("Berlin");
			pattern2 = new Resident();
			pattern2.setDateOfBirth(format.parse("25.08.1975"));
			pattern3 = new Resident();
			pattern3.setFamilyName("Lu*");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<Resident> list1 = testService.getFilteredResidentsList(pattern1);
		List<Resident> list2 = testService.getFilteredResidentsList(pattern2);
		List<Resident> list3 = testService.getFilteredResidentsList(pattern3);
		
		assertEquals(2, list1.size());
		assertEquals(2, list2.size());
		assertEquals(0, list3.size());
	}
	
	@Test
	public void getUniqueResidentTest() {
		BaseResidentService testService = new BaseResidentService();
		testService.setResidentRepository(new ResidentRepositoryStub());
		
		SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
		Resident resident1 = null;
		Resident resident2 = null;
		Resident resident3 = null;
		
		try {
			resident1 = new Resident("Max", null, null, null, null);
			resident2 = new Resident("Erika", "Schmid", "Goethestraße", "Koeln", format.parse("25.08.1975"));
			resident3 = new Resident(null, null, null, null, format.parse("25.08.1975"));
		} catch (ParseException e) {
			
		}
		
		Resident r = null;
		
		try {
			r = testService.getUniqueResident(resident1);
			
			assertEquals("Max", r.getGivenName());
			assertEquals("Mustermann", r.getFamilyName());
			assertEquals("Hauptstraße", r.getStreet());
			assertEquals("Berlin", r.getCity());
			assertEquals(format.parse("01.05.1990"), r.getDateOfBirth());
			
			testService.getUniqueResident(resident2);
			testService.getUniqueResident(resident3);
			fail();
		} catch (ResidentServiceException | ParseException e) {
			// TODO: handle exception
		}
	}
	
	@Test
	public void easyMockTest() {
		SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
		
		List<Resident> allResidents = new ArrayList<Resident>();
		List<Resident> filteredResidents = new ArrayList<Resident>();
		Resident filter = null;
		
		try {
			allResidents.add(new Resident("Max", "Mustermann", "Hauptstraße", "Berlin", format.parse("01.05.1990")));
			allResidents.add(new Resident("Erika", "Schmid", "Goethestraße", "Koeln", format.parse("25.08.1975")));
			allResidents.add(new Resident("Hans", "Mustermann", "Badsraße", "Freiburg", format.parse("25.08.1975")));
			allResidents.add(new Resident("Petra", "Mueller", "Opernplatz", "Koeln", format.parse("17.04.2000")));
			allResidents.add(new Resident("Manuela", "Mayer", "Schlossallee", "Berlin", format.parse("08.08.1962")));
			
			filteredResidents.add(new Resident("Max", "Mustermann", "Hauptstraße", "Berlin", format.parse("01.05.1990")));
			filteredResidents.add(new Resident("Manuela", "Mayer", "Schlossallee", "Berlin", format.parse("08.08.1962")));
			
			filter = new Resident();
			filter.setGivenName("Ma*");
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		ResidentRepository mock = createMock(ResidentRepository.class);
		expect(mock.getResidents()).andReturn(allResidents);
		replay(mock);
		BaseResidentService service = new BaseResidentService();
		service.setResidentRepository(mock);
		List<Resident> result = service.getFilteredResidentsList(filter);
		
		assertThat(result, equalTo(filteredResidents));
	}

}
