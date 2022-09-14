package INF102.Mandatory1.util;

import java.util.List;
import java.util.Random;

public class MyRandomSelector implements IRandomSelector {

	Random random = new Random();

	@Override
	public <T> T removeRandom(List<T> list) {
		throw new UnsupportedOperationException();

	}

	@Override
	public <T> List<T> removeRandom(List<T> list, int k) {
		throw new UnsupportedOperationException();

	}
	
}
