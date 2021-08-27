package com.bkc.pathfinder;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AssertTest {
	
	@Test
	public void assertThis() {
		assertEquals(12,12);
	}

}
