package com.target.redsky.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.stereotype.Service;

import com.target.redsky.entity.RedSkyEntity;
import com.target.redsky.exception.NoContentException;

import com.target.redsky.model.RedSky;
import com.target.redsky.repository.RedSkyRepository;

@Service
public class RedSkyService {

	@Autowired
	private RedSkyRepository redSkyRepository;

	public RedSky saveIntoRedSkyTable(RedSky redSky) {
		RedSkyEntity entity = redSkyRepository.save(mapObjectToEntity(redSky));
		return mapEntityToObject(entity);
	}

	public RedSkyEntity mapObjectToEntity(RedSky redSky) {

		RedSkyEntity entity = new RedSkyEntity();
		entity.setProductId(redSky.getProductId());
		entity.setName(redSky.getName());

		return entity;
	}

	public RedSky mapEntityToObject(RedSkyEntity entity) {
		RedSky table = new RedSky();

		table.setProductId(entity.getProductId());
		table.setName(entity.getName());
		table.setProductId(entity.getProductId());
		return table;
	}

	public RedSky fetchRecordFromRedSkyTable(Integer id) throws NoContentException {

		Optional<RedSkyEntity> entity = redSkyRepository.findById(id);
		if (!entity.isPresent()) {
			throw new NoContentException(HttpStatus.NO_CONTENT);
		}
		return mapEntityToObject(entity.get());

	}

	public RedSky updateRecordIntoRedSkyTable(RedSky redSky) {
		RedSkyEntity entity = redSkyRepository.save(mapObjectToEntity(redSky));
		return mapEntityToObject(entity);

	}

}