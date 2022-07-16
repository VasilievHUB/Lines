package org.enerdata.lines.controller;

import java.util.List;

import org.enerdata.lines.domain.Substation;
import org.enerdata.lines.repo.SubstationRepo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@CrossOrigin(origins = "http://localhost:8888/subs")
@RequestMapping("subs")
public class SubstationController {

	private final SubstationRepo substationRepo;

	public SubstationController(SubstationRepo substationRepo) {
		super();
		this.substationRepo = substationRepo;
	}
	@GetMapping
	public List<Substation> listSubstation(){
		return substationRepo.findAll();
	}
	@PostMapping
	public Substation createSubstation(@RequestBody Substation substation) {
		return substationRepo.save(substation);
	}
	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Substation substation) {
		substationRepo.delete(substation);
	}
}
