
public class Teams {
	private int id;
	private String team;
	private int league_id;
	private String league;
	public Teams() {
		this.id = 0;
		this.team = "";
		this.league_id = 0;
		this.league = "";
	}
	public Teams(int id, String team, int league_id, String league) {
		this.id = id;
		this.team = team;
		this.league_id = league_id;
		this.league = league;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTeam() {
		return team;
	}
	public void setTeam(String team) {
		this.team = team;
	}
	public int getLeague_id() {
		return league_id;
	}
	public void setLeague_id(int league_id) {
		this.league_id = league_id;
	}
	public String getLeague() {
		return league;
	}
	public void setLeague(String league) {
		this.league = league;
	}
	
}
