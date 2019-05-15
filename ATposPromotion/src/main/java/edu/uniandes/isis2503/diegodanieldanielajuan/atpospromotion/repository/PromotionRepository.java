package edu.uniandes.isis2503.diegodanieldanielajuan.atpospromotion.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.uniandes.isis2503.diegodanieldanielajuan.atpospromotion.entity.Promotion;

@Repository
public interface PromotionRepository extends CrudRepository<Promotion, String> {
}
