package org.enerdata.lines.repo;

import org.enerdata.lines.domain.Substation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubstationRepo extends JpaRepository<Substation, Integer>{

}
