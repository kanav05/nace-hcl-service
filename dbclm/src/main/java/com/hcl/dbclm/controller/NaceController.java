package com.hcl.dbclm.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.dbclm.entity.Nace;
import com.hcl.dbclm.service.NaceService;

@RestController
@RequestMapping("/api")
public class NaceController {

	@Autowired
	private final NaceService naceService;

	public NaceController(NaceService naceService) {
		this.naceService = naceService;
	}

	@PostMapping("/nace/createNace")
	public ResponseEntity<Nace> saveNace(@Valid @RequestBody final Nace nace) {
		try {
			System.out.println("Hi");
			return new ResponseEntity<Nace>(naceService.create(nace), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/nace")
	public ResponseEntity<List<Nace>> getAllNace() {
		try {
			List<Nace> list = naceService.getAllNace();

			if (list.isEmpty() || list.size() == 0) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(list, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/nace/{naceCode}")
	public ResponseEntity<Nace> getNaceDetails(@PathVariable String naceCode) {
		return ResponseEntity.ok(naceService.findByNaceCode(naceCode));
	}
}
