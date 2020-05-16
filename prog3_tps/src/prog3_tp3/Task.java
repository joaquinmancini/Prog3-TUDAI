package prog3_tp3;

public class Task<T> {
	private Integer color;
	private Integer d;
	private Integer f;
	private Integer duration;

	public Task(Integer duration) {
		this.color = 0;
		this.duration = duration;
	}

	public Integer getColor() {
		return color;
	}

	public void setColor(Integer color) {
		this.color = color;
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
