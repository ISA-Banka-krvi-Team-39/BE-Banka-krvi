package app.center.repository;

import app.center.model.Term;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITermRepository extends JpaRepository<Term, Integer> {
}
