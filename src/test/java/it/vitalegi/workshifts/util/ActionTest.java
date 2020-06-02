package it.vitalegi.workshifts.util;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import it.vitalegi.workshifts.model.Action;

@SpringBootTest
public class ActionTest {

	@Autowired
	ActionUtil actionUtil;

	@Test
	void testGetValues() {

		List<String> values = actionUtil.getValues(Action.MORNING);
		assertThat(values, containsInAnyOrder("M*", "M"));
		assertThat(values, hasSize(2));

	}
}
