package app.donated_bags.repository;

import app.donated_bags.model.DonatedBags;
import app.wated_material.model.WastedMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface IDonatedBagsRepository extends JpaRepository<DonatedBags, Integer> {

    @Query("select d from DonatedBags d where d.date > :startDate and d.date < :endDate")
    public List<DonatedBags> getDonatedBagsInBetween(@Param("startDate")LocalDateTime startDate
            , @Param("endDate")LocalDateTime endDate);
}
