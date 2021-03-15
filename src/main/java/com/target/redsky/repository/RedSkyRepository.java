package com.target.redsky.repository;

import java.util.Optional;


import org.springframework.data.cassandra.repository.CassandraRepository;
import com.target.redsky.entity.RedSkyEntity;



public interface RedSkyRepository extends CassandraRepository<RedSkyEntity,Integer> {
	 Optional<RedSkyEntity> findById(Integer id);
	
}
