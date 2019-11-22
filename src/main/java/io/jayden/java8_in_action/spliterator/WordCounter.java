package io.jayden.java8_in_action.spliterator;

public class WordCounter {

	private final int counter;
	private final boolean lastSpace;

	public WordCounter(final int counter, final boolean lastSpace) {
		this.counter = counter;
		this.lastSpace = lastSpace;
	}

	public WordCounter accumulate(final Character c) {
		if (Character.isWhitespace(c)) {
			return lastSpace ? this : new WordCounter(counter, true);
		} else {
			return lastSpace ? new WordCounter(counter + 1, false) : this;
		}
	}

	public WordCounter combine(final WordCounter wordCounter) {
		return new WordCounter(counter + wordCounter.counter, wordCounter.lastSpace);
	}

	public int getCounter() {
		return this.counter;
	}

	@Override
	public String toString() {
		return "WordCounter{" +
				"counter=" + counter +
				", lastSpace=" + lastSpace +
				'}';
	}

	// ---------------------------------------------------------------------------------------------------------------------------------------------------------
	// -- private
	// ---------------------------------------------------------------------------------------------------------------------------------------------------------
}
