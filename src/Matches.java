
public class Matches {
	private int id;
	private int HomeTeam_id;
	private int AwayTeam_id;
	private int HomeScore;
	private int AwayScore;
	private String Date;
	private String team;
	public Matches() {
		this.id = 0;
		this.HomeTeam_id = 0;
		this.AwayTeam_id = 0;
		this.HomeScore = 0;
		this.AwayScore = 0;
		this.Date = "";
		this.team = "";
	}
	public Matches(int id, int homeTeam_id, int awayTeam_id, int homeScore, int awayScore, String date, String team) {
		this.id = id;
		HomeTeam_id = homeTeam_id;
		AwayTeam_id = awayTeam_id;
		HomeScore = homeScore;
		AwayScore = awayScore;
		Date = date;
		this.team = team;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getHomeTeam_id() {
		return HomeTeam_id;
	}
	public void setHomeTeam_id(int homeTeam_id) {
		HomeTeam_id = homeTeam_id;
	}
	public int getAwayTeam_id() {
		return AwayTeam_id;
	}
	public void setAwayTeam_id(int awayTeam_id) {
		AwayTeam_id = awayTeam_id;
	}
	public int getHomeScore() {
		return HomeScore;
	}
	public void setHomeScore(int homeScore) {
		HomeScore = homeScore;
	}
	public int getAwayScore() {
		return AwayScore;
	}
	public void setAwayScore(int awayScore) {
		AwayScore = awayScore;
	}
	public String getDate() {
		return Date;
	}
	public void setDate(String date) {
		Date = date;
	}
	public String getTeam() {
		return team;
	}
	public void setTeam(String team) {
		this.team = team;
	}
	
	
	
	
}
