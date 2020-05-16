package prog3_tp3;

public class Task<T> {
	private Integer state;
	private Integer d;
	private Integer f;
	private Integer duration;

	public Task(Integer duration) {
		this.state = 0;
		this.d = 0;
		this.f = 0;
		this.duration = duration;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getD() {
		return d;
	}

	public void setD(Integer d) {
		this.d = d;
	}

	public Integer getF() {
		return f;
	}

	public void setF(Integer f) {
		this.f = f;
	}

	public Integer getDuration() {
		return duration;
	}

}
