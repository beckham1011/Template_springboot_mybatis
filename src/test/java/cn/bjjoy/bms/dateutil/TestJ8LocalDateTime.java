package cn.bjjoy.bms.dateutil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class TestJ8LocalDateTime {

	@Test
	public void test() {
		
		String fURL = "d:/ZhijieXu-En.doc";
		try {
			System.out.println(Files.getLastModifiedTime(Paths.get(fURL)).to(TimeUnit.DAYS));
			System.out.println(LocalDate.now().toEpochDay());
			System.out.println(LocalDate.now().toEpochDay() - Files.getLastModifiedTime(Paths.get(fURL)).to(							TimeUnit.DAYS));

		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println(7 * 24 * 3600);

		System.out.println(ChronoUnit.WEEKS.getDuration().getSeconds() * 1000L);
	}

}
