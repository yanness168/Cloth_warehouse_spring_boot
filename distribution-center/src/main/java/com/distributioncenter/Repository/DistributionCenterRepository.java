package com.distributioncenter.Repository;

import com.distributioncenter.Models.DistributionCenter;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DistributionCenterRepository extends CrudRepository<DistributionCenter, Long> {
    @Override
    List<DistributionCenter> findAll();

}
