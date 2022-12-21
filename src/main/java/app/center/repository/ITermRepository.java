package app.center.repository;

import app.center.model.Center;
import app.center.model.Term;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ITermRepository extends JpaRepository<Term, Integer> {
    public Term findOneByTermId(Integer termId);

}
