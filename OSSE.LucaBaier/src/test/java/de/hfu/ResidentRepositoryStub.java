package de.hfu;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import de.hfu.residents.domain.Resident;
import de.hfu.residents.repository.ResidentRepository;

public class ResidentRepositoryStub implements ResidentRepository {

	@Override
	public List<Resident> getResidents() {
		SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
		
		
		List<Resident> result = new ArrayList<Resident>();
		Resident max = null;
		Resident erika = null;
		Resident hans = null;
		Resident petra = null;
		Resident manuela = null;
		
		try {
			max = new Resident("Max", "Mustermann", "Hauptstraße", "Berlin", format.parse("01.05.1990"));
			erika = new Resident("Erika", "Schmid", "Goethestraße", "Koeln", format.parse("25.08.1975"));
			hans = new Resident("Hans", "Mustermann", "Badsraße", "Freiburg", format.parse("25.08.1975"));
			petra = new Resident("Petra", "Mueller", "Opernplatz", "Koeln", format.parse("17.04.2000"));
			manuela = new Resident("Manuela", "Mayer", "Schlossallee", "Berlin", format.parse("08.08.1962"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		result.add(max);
		result.add(erika);
		result.add(hans);
		result.add(petra);
		result.add(manuela);
		
		return result;
	}

}
