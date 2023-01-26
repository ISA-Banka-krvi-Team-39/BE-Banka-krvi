package app.wated_material.repository;

import app.center.model.Term;
import app.wated_material.model.WastedMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface IWastedMaterialRepository extends JpaRepository<WastedMaterial, Integer> {

    @Query("select w from WastedMaterial w where w.date between :startDate and :endDate")
    public List<WastedMaterial> getWastedMaterialInBetween(@Param("startDate")LocalDateTime startDate
            , @Param("endDate")LocalDateTime endDate);
}
