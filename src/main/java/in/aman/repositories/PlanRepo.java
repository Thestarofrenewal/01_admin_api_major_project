package in.aman.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import in.aman.entities.PlanEntity;

public interface PlanRepo extends JpaRepository<PlanEntity, Integer> {

}
