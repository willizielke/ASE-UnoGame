package domain.valueobjects;

public class CardFake extends Card {
	private int points;

	public CardFake(int points) {
		this.points = points;
	}

	@Override
	public int getPoints() {
		return this.points;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
}
