package it.vitalegi.workshifts.util;

import java.text.SimpleDateFormat;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Service
public class JsonUtil {

	protected static JsonUtil _instance;

	@PostConstruct
	void init() {
		_instance = this;
	}

	public static JsonUtil getInstance() {
		return _instance;
	}

	public <E> String serialize(E value) {
		try {
			return getMapper().writeValueAsString(value);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}

	public <E> E deserialize(String json, Class<E> clazz) {
		try {
			return getMapper().readValue(json, clazz);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}

	public <E> E deserialize(String json, TypeReference<E> typeReference) {
		try {
			return getMapper().readValue(json, typeReference);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}

	protected ObjectMapper getMapper() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ"));
		mapper.registerModule(new JavaTimeModule());
		mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		return mapper;
	}
}
