package hello;

import static org.junit.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class StuffTests {
	@Before
	public void setup() {
}

	@Test
		public void testDateFormat() {
		Date date = new Date();
		SimpleDateFormat ft = 
	      new SimpleDateFormat ("yyyy.MM.dd hh:mm:ss");
	
	      System.out.println("Current Date: " + ft.format(date));
		//assertEquals(4, solution(invalid_board));
	}
}
