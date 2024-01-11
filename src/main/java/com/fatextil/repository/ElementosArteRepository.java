package com.fatextil.repository;

import com.fatextil.model.ElementosArteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ElementosArteRepository extends JpaRepository<ElementosArteModel, Long>{

    @Query("SELECT CASE WHEN COUNT(e) > 0 THEN true ELSE false END FROM ElementosArteModel e WHERE e.fileName = :fileName")
    Byte existsByFileName(@Param("fileName") String fileName);

}
