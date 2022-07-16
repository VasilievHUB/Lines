package org.enerdata.lines.repo;

import org.enerdata.lines.domain.Line;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LineRepo extends JpaRepository<Line, Integer>{

}
