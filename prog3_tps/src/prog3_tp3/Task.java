package prog3_tp3;

public class Task<T> {
	private Integer state;
	private Integer duration;

	public Task(Integer duration) {
		this.state = 0;
		this.duration = duration;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getDuration() {
		return duration;
	}

}
