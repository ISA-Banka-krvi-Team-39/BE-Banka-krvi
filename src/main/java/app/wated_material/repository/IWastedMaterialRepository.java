package app.wated_material.repository;

import app.wated_material.model.WastedMaterial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IWastedMaterialRepository extends JpaRepository<WastedMaterial, Integer> {

}
