package it.vitalegi.workshifts.service;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import it.vitalegi.workshifts.model.WorkContext;
import it.vitalegi.workshifts.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class ExcelExportServiceTest {

	@Autowired
	ExcelExportServiceFactory factory;

	@Test
	void testExport() throws IOException {
		InputStream is = getClass().getClassLoader().getResourceAsStream("workshift1.json");
		String text = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8)).lines()
				.collect(Collectors.joining("\n"));
		WorkContext context = JsonUtil.getInstance().deserialize(text, WorkContext.class);

		try (FileOutputStream fos = new FileOutputStream("./workshift1.xlsx")) {
			factory.getService(context).export(fos);
		}
	}

}
