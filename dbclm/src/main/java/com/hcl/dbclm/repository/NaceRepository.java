package com.hcl.dbclm.repository;

import org.springframework.data.repository.CrudRepository;

import com.hcl.dbclm.entity.Nace;

public interface NaceRepository extends CrudRepository<Nace, Long>{

    Nace findByNaceCode(String naceCode);

}
