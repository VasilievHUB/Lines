package org.enerdata.lines.controller;

import java.util.List;

import org.enerdata.lines.domain.Line;
import org.enerdata.lines.repo.LineRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("lines")
public class LinesController {

	private final LineRepo lineRepo;
	
	@Autowired
	public LinesController(LineRepo lineRepo) {
		this.lineRepo = lineRepo;
	}

	@GetMapping
	public List<Line> listLines(){
		return lineRepo.findAll();
	}
	
	@GetMapping("{id}")
	public Line getLineByID(@PathVariable("id") Line line) {
		return line;
	}
	
	@PostMapping
	public Line createLine(@RequestBody Line line) {
		return lineRepo.save(line);
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Line line) {
		lineRepo.delete(line);
	}
	@PutMapping
	public Line update(@PathVariable("id") Line lineToSave, @RequestBody Line line) {
		BeanUtils.copyProperties(line, lineToSave, "id");
		return lineRepo.save(lineToSave);
	}
}
