
public class Leagues {
	private int id;
	private String league;
	public Leagues() {
		this.id = 0;
		this.league = "";
	}
	public Leagues(int id, String league) {
		this.id = id;
		this.league = league;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLeague() {
		return league;
	}
	public void setLeague(String league) {
		this.league = league;
	}
	
}
