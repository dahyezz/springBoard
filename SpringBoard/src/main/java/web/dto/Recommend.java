package web.dto;

public class Recommend {
	private String id;
	private int board_no;
	
	@Override
	public String toString() {
		return "Recommend [id=" + id + ", board_no=" + board_no + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getBoard_no() {
		return board_no;
	}

	public void setBoard_no(int board_no) {
		this.board_no = board_no;
	}
	
	

}
