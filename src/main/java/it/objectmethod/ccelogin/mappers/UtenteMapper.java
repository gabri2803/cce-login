package it.objectmethod.ccelogin.mappers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import it.objectmethod.ccelogin.domain.UtenteEntity;
import it.objectmethod.ccelogin.dto.UtenteDTO;

@Component
public class UtenteMapper implements EntityMapper<UtenteEntity, UtenteDTO> {

	@Override
	public UtenteEntity toEntity(UtenteDTO dto) {
		UtenteEntity entity = new UtenteEntity();
		entity.setIdUser(dto.getIdUser());
		entity.setName(dto.getName());
		entity.setRole(dto.getRole());
		entity.setPassword(dto.getPassword());
		entity.setEmail(dto.getEmail());
		return entity;
	}

	@Override
	public UtenteDTO toDto(UtenteEntity entity) {
		UtenteDTO dto = new UtenteDTO();
		dto.setIdUser(entity.getIdUser());
		dto.setName(entity.getName());
		dto.setRole(entity.getRole());
		dto.setPassword(entity.getPassword());
		dto.setEmail(entity.getEmail());
		return dto;
	}

	@Override
	public List<UtenteEntity> toEntity(List<UtenteDTO> dtoList) {
		List<UtenteEntity> entityList = new ArrayList<>();
		for (UtenteDTO dto : dtoList) {
			UtenteEntity entity = toEntity(dto);
			entityList.add(entity);
		}
		return entityList;
	}

	@Override
	public List<UtenteDTO> toDto(List<UtenteEntity> entityList) {
		List<UtenteDTO> dtoList = new ArrayList<>();
		for (UtenteEntity entity : entityList) {
			UtenteDTO dto = toDto(entity);
			dtoList.add(dto);
		}
		return dtoList;
	}

}
