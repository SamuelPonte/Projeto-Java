import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class EuropeFootballLeagues {
	public static Scanner input=new Scanner(System.in);
	public static final String url="jdbc:mysql://localhost:3306/EuropeFootballLeagues?serverTomezone=WET";
	public static final String user="samu";
	public static final String pwd="*Cet2022*";
	public static void ListarLeagues() {
		System.out.println("\n***Lista das Ligas***");
		String sql="select id,league FROM Leagues";
		try(Connection conn=DriverManager.getConnection(url,user,pwd);Statement stat=conn.createStatement()){
			ResultSet rs=stat.executeQuery(sql);
			while(rs.next()) {
				System.out.println("ID: "+rs.getInt("id"));
				System.out.println("League: "+rs.getString("league")+"\n");
			}
		}catch(SQLException e) {
			System.out.println("Erro: "+e.getMessage());
		}
	}
	public static void ListarTeams() {
		System.out.println("\n***Lista das Equipas***");
		String sql="select Teams.id,team, League.league as league FROM Teams JOIN Leagues as League ON Teams.league_id = League.id ORDER BY Teams.id ASC";
		try(Connection conn=DriverManager.getConnection(url,user,pwd);Statement stat=conn.createStatement()){
			ResultSet rs=stat.executeQuery(sql);
			while(rs.next()) {
				System.out.println("ID: "+rs.getInt("id"));
				System.out.println("Team: "+rs.getString("team"));
				System.out.println("League: "+rs.getString("league")+"\n");
			}
		}catch(SQLException e) {
			System.out.println("Erro: "+e.getMessage());
		}
	}
	public static void ListarMatches() {
		System.out.println("\n***Lista das Partidas***");
		String sql="select Matches.id, HomeTeam.team as HomeTeam, AwayTeam.team as AwayTeam, HomeScore, AwayScore, Date FROM Matches JOIN Teams as HomeTeam ON Matches.HomeTeam_id = HomeTeam.id JOIN Teams as AwayTeam ON Matches.AwayTeam_id = AwayTeam.id ORDER BY Matches.id ASC";
		try(Connection conn=DriverManager.getConnection(url,user,pwd);Statement stat=conn.createStatement()){
			ResultSet rs=stat.executeQuery(sql);
			while(rs.next()) {
				System.out.println("ID: "+rs.getInt("id"));
				System.out.println("HomeTeam: "+rs.getString("HomeTeam"));
				System.out.println("AwayTeam: "+rs.getString("AwayTeam"));
				System.out.println("HomeScore: "+rs.getInt("HomeScore"));
				System.out.println("AwayScore: "+rs.getInt("AwayScore"));
				System.out.println("Date: "+rs.getString("Date")+"\n");
			}
		}catch(SQLException e) {
			System.out.println("Erro: "+e.getMessage());
		}
	}
	public static void ProcurarLeagues() {
	    System.out.println("\n***Procurar Ligas***");
	    System.out.print("Qual o nome da liga? ");
	    String name = input.nextLine();
	    name = "%" + name + "%";
	    String sql = "select Teams.team, Teams.league_id, Leagues.league AS league FROM Teams JOIN Leagues ON Teams.league_id = Leagues.id WHERE Leagues.league LIKE ? GROUP BY Teams.id";
	    boolean existe = false;
	    try (Connection conn = DriverManager.getConnection(url, user, pwd);
	         PreparedStatement pstat = conn.prepareStatement(sql)) {
	        pstat.setString(1, name);
	        ResultSet rs = pstat.executeQuery();
	        while (rs.next()) {
	        	existe = true;
	            System.out.println("Team: " + rs.getString("team"));
	            System.out.println("League_id: " + rs.getInt("league_id"));
	            System.out.println("League: " + rs.getString("league")+ "\n");
	        }
	        if(!existe) {
	        	System.out.println("Erro: A liga " + name + " não existe ou não tem equipa associada");
	        }
	    } catch (SQLException e) {
	        System.out.println("Erro: " + e.getMessage());
	    }
	}
	public static void ProcurarTeams() {
		System.out.println("\n***Procurar Equipas***");
		System.out.print("Qual o nome da equipa? ");
		String name=input.nextLine();
		name="%"+name+"%";
		String sql="select Teams.id, team, league_id, league FROM Teams JOIN Leagues ON Teams.league_id = Leagues.id WHERE team LIKE ?";
		boolean existe = false;
		try(Connection conn=DriverManager.getConnection(url,user,pwd);PreparedStatement pstat=conn.prepareStatement(sql)){
			pstat.setString(1, name);
			ResultSet rs=pstat.executeQuery();
			while(rs.next()) {
				existe = true;
				System.out.println("ID: "+rs.getInt("id"));
				System.out.println("Team: "+rs.getString("team"));
				System.out.println("League_id: "+rs.getInt("league_id"));
				System.out.println("League: "+rs.getString("league")+"\n");
			}
			if(!existe) {
				System.out.println("Erro: A equipa " + name + " não existe");
			}
		}catch(SQLException e) {
			System.out.println("Erro: "+e.getMessage());
		}		
	}
	public static void ProcurarMatches() {
		System.out.println("\n***Procurar Partidas***");
		System.out.print("Qual o nome da equipa? ");
		String name=input.nextLine();
		name="%"+name+"%";
		String sql="select Matches.id, HomeTeam.team as HomeTeam, AwayTeam.team as AwayTeam, HomeScore, AwayScore, Date FROM Matches JOIN Teams as HomeTeam ON Matches.HomeTeam_id = HomeTeam.id JOIN Teams as AwayTeam ON Matches.AwayTeam_id = AwayTeam.id WHERE HomeTeam.team LIKE ?";
		boolean existe = false;
		try(Connection conn=DriverManager.getConnection(url,user,pwd);PreparedStatement pstat=conn.prepareStatement(sql)){
			pstat.setString(1, name);
			ResultSet rs=pstat.executeQuery();
			while(rs.next()) {
				existe = true;
				System.out.println("ID: "+rs.getInt("id"));
				System.out.println("HomeTeam: "+rs.getString("HomeTeam"));
				System.out.println("AwayTeam: "+rs.getString("AwayTeam"));
				System.out.println("HomeScore: "+rs.getInt("HomeScore"));
				System.out.println("AwayScore: "+rs.getInt("AwayScore"));
				System.out.println("Date: "+rs.getString("Date")+"\n");
			}
			if(!existe) {
				System.out.println("Erro: A equipa " + name + " não existe ou não tem partidas");
			}
		}catch(SQLException e) {
			System.out.println("Erro: "+e.getMessage());
		}		
	}
	public static void InserirLeagues() {
		System.out.println("\n***Inserir Ligas***");
		Leagues cont=new Leagues();
		System.out.print("Nome da liga:");cont.setLeague(input.nextLine());
		String sql = "select league FROM Leagues WHERE league = ?";
		try(Connection conn=DriverManager.getConnection(url,user,pwd);PreparedStatement pstat=conn.prepareStatement(sql)){
			pstat.setString(1, cont.getLeague());
		    ResultSet rs = pstat.executeQuery();
		    if (rs.next()) {
		        System.out.println("Erro: A liga " + cont.getLeague() + " já existe.");
		        return;
		    }
		    sql="insert into Leagues(league) values(?)";
		    try(PreparedStatement pstat2=conn.prepareStatement(sql)){
		    	pstat2.setString(1, cont.getLeague());
				int RecordCount=pstat2.executeUpdate();
				System.out.println(RecordCount+" registo(s) inserido(s)");
		    }
			
		}
		catch(SQLException e) {
			System.out.println("Erro: "+e.getMessage());
		}
		
	}
	public static void InserirTeams() {
		System.out.println("\n***Inserir Equipas***");
		System.out.print("Que liga a equipa vai pertencer: ");
		String leagueName= input.nextLine();
		String leaguesql = "select id,league FROM Leagues WHERE league=? ";
		try(Connection conn=DriverManager.getConnection(url,user,pwd);PreparedStatement pstat=conn.prepareStatement(leaguesql)){
			pstat.setString(1, leagueName);
		    ResultSet rs = pstat.executeQuery();
		    if (!rs.next()) {
		        System.out.println("Erro: A liga " + leagueName + " não existe.");
		        return;
		    }
		    int leagueId = rs.getInt("id");   
			Teams cont=new Teams();
			System.out.print("Nome da equipa: ");cont.setTeam(input.nextLine());
			cont.setLeague_id(leagueId);
			String sql = "select team FROM Teams WHERE team=?";
			try(PreparedStatement pstat2=conn.prepareStatement(sql)){
				pstat2.setString(1, cont.getTeam());
				ResultSet rsl = pstat2.executeQuery();
				if(rsl.next()) {
					System.out.println("Erro: A equipa " + cont.getTeam() + " já existe.");
			        return;
				}
				sql="insert into Teams(team, league_id) values(?,?)";
				try(PreparedStatement pstat3=conn.prepareStatement(sql)){
					pstat3.setString(1, cont.getTeam());
					pstat3.setInt(2, cont.getLeague_id());
					int RecordCount=pstat3.executeUpdate();
					System.out.println(RecordCount+" registo(s) inserido(s)");
				}
			}
		}catch(SQLException e) {
			System.out.println("Erro: "+e.getMessage());
		}
		
	}
	public static void InserirMatches() {
		System.out.println("\n***Inserir Partidas***");
		System.out.print("Qual é o nome da equipa: ");
		String TeamName= input.nextLine();
		String Teamsql = "select id,team FROM Teams WHERE team=? ";
		try(Connection conn=DriverManager.getConnection(url,user,pwd);PreparedStatement pstat=conn.prepareStatement(Teamsql)){
			pstat.setString(1, TeamName);
		    ResultSet rs = pstat.executeQuery();
		    if (!rs.next()) {
		        System.out.println("Erro: A equipa " + TeamName + " não existe.");
		        return;
		    }
		    int homeTeamId = rs.getInt("id");
			Matches cont=new Matches();
			System.out.print("Equipa adversária: ");
			String AwayTeam = input.nextLine();
			String Awaysql = "select id,team FROM Teams WHERE team=?";
			try(PreparedStatement pstat2=conn.prepareStatement(Awaysql)){
				pstat2.setString(1, AwayTeam);
				ResultSet r = pstat2.executeQuery();
				if(!r.next()) {
					System.out.println("Erro: A equipa " + AwayTeam + " não existe.");
			        return;
				}
				int awayTeamId = r.getInt("id");
				System.out.print("HomeScore:"); cont.setHomeScore(input.nextInt());
				input.nextLine(); 
				System.out.print("AwayScore:"); cont.setAwayScore(input.nextInt());
				input.nextLine(); 
				System.out.print("Date:"); cont.setDate(input.nextLine());
				cont.setHomeTeam_id(homeTeamId);
				cont.setAwayTeam_id(awayTeamId);
				String sql="insert into Matches(HomeTeam_id,AwayTeam_id,HomeScore,AwayScore,Date) values(?,?,?,?,?)";
				try(PreparedStatement pstat3=conn.prepareStatement(sql)){
					pstat3.setInt(1, cont.getHomeTeam_id());
					pstat3.setInt(2, cont.getAwayTeam_id());
					pstat3.setInt(3, cont.getHomeScore());
					pstat3.setInt(4, cont.getAwayScore());
					pstat3.setString(5, cont.getDate());
					int RecordCount=pstat3.executeUpdate();
					System.out.println(RecordCount+" registo(s) inserido(s)");
				}
			}
			
		}catch(SQLException e) {
			System.out.println("Erro: "+e.getMessage());
		}
		
	}
	public static void AlterarLeagues() {
		System.out.println("\n***Alterar Liga***");
		System.out.print("Qual é o nome da liga: ");
		String name= input.nextLine();
		String sql="select id,league FROM Leagues WHERE league=?";
		boolean existe = false;
		try(Connection conn=DriverManager.getConnection(url,user,pwd);PreparedStatement pstat=conn.prepareStatement(sql)){
			pstat.setString(1, name);
			ResultSet rs=pstat.executeQuery();
			
			Leagues cont=new Leagues();
			while(rs.next()) {
				existe = true;
				cont.setId(rs.getInt("id"));
				cont.setLeague(rs.getString("league"));
				System.out.println("\nID: "+rs.getInt("id"));
				System.out.println("League: "+rs.getString("league"));
				System.out.print("Quer alterar esta Liga (S/N)?");
				String op=input.nextLine();
				if(op.equalsIgnoreCase("S")) {
					System.out.print("Novo nome: ");cont.setLeague(input.nextLine());
					sql="select league FROM Leagues WHERE league=?";
					try(PreparedStatement pstat2=conn.prepareStatement(sql);){
						pstat2.setString(1, cont.getLeague());
						ResultSet rsl = pstat2.executeQuery();
						if (rsl.next()) {
					        System.out.println("Erro: A liga " + cont.getLeague() + " já existe.");
					        return;
					    }
						sql="update Leagues set league=? where id=?";
						try(PreparedStatement pstat3=conn.prepareStatement(sql)){
							int i=1;
							pstat3.setString(i++,cont.getLeague());
							pstat3.setInt(i++,cont.getId());
							int RecordCount=pstat3.executeUpdate();
							System.out.println(RecordCount+" registo alterado");
							pstat3.close();
						}
					}
				}
			}
			if(!existe) {
				System.out.println("Erro: A liga " + name + " não existe");
			}
	
		}catch(SQLException e) {
			System.out.println("Erro: "+e.getMessage());
		}		
	}
	public static void AlterarTeams() {
		System.out.println("\n***Alterar Equipa***");
		System.out.print("Qual é o nome da equipa: ");
		String name = input.nextLine();
		String sql="select Teams.id, Teams.team, Leagues.league FROM Teams JOIN Leagues ON Teams.league_id = Leagues.id WHERE Teams.team = ?";
		boolean existe = false;
		try(Connection conn=DriverManager.getConnection(url,user,pwd);PreparedStatement pstat=conn.prepareStatement(sql)){
			pstat.setString(1, name);
			ResultSet rs=pstat.executeQuery();
			
			Teams cont=new Teams();
			while(rs.next()) {
				existe = true;
				cont.setId(rs.getInt("id"));
				cont.setTeam(rs.getString("team"));
				System.out.println("\nID: "+rs.getInt("id"));
				System.out.println("Team: "+rs.getString("team"));
				System.out.println("League: "+rs.getString("league"));
				System.out.print("Quer alterar esta equipa (S/N)? ");
				String op=input.nextLine();
				if(op.equalsIgnoreCase("S")) {
					System.out.print("Novo nome: ");cont.setTeam(input.nextLine());
					sql = "select team FROM Teams WHERE team=?";
					try(PreparedStatement pstat2=conn.prepareStatement(sql);){
						pstat2.setString(1, cont.getTeam());
						ResultSet rsl = pstat2.executeQuery();
						if (rsl.next()) {
					        System.out.println("Erro: A equipa " + cont.getTeam() + " já existe.");
					        return;
					    }
						sql="update Teams set team=? where id=?";
						PreparedStatement pstat3=conn.prepareStatement(sql);
						int i=1;
						pstat3.setString(i++,cont.getTeam());
						pstat3.setInt(i++,cont.getId());
						int RecordCount=pstat3.executeUpdate();
						System.out.println(RecordCount+" registo alterado");
						System.out.print("Quer alterar a Liga desta equipa (S/N)? ");
						String ops = input.nextLine();
						if(ops.equalsIgnoreCase("S")) {
							Leagues lg =new Leagues();
							System.out.print("Nova Liga: ");lg.setLeague(input.nextLine());
							sql = "select id from Leagues where league = ?";
							PreparedStatement pstat4 = conn.prepareStatement(sql);
			                pstat4.setString(1, lg.getLeague());
			                ResultSet rs3=pstat4.executeQuery();
							if(rs3.next()) {
								int leagueId = rs3.getInt("id");
			                    sql = "UPDATE Teams SET league_id = ? WHERE id = ?";
			                    PreparedStatement pstat5 = conn.prepareStatement(sql);
			                    pstat5.setInt(1, leagueId);
			                    pstat5.setInt(2, cont.getId());
			                    int RecordCount1 = pstat5.executeUpdate();
			                    System.out.println(RecordCount1 + " registo alterado");
							}
							else {
								 sql = "INSERT INTO Leagues (league) VALUES (?)";
				                 PreparedStatement pstat5 = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				                 pstat5.setString(1, lg.getLeague());
				                 int RecordCount1 = pstat5.executeUpdate();
				                 System.out.println(RecordCount1 + " registo inserido");
				                 ResultSet generatedKeys = pstat5.getGeneratedKeys();
				                 if (generatedKeys.next()) {
				                	 int leagueId = generatedKeys.getInt(1);
				                     sql = "UPDATE Teams SET league_id = ? WHERE id = ?";
				                     PreparedStatement pstat6 = conn.prepareStatement(sql);
				                     pstat6.setInt(1, leagueId);
				                     pstat6.setInt(2, cont.getId());
				                     int RecordCount2 = pstat6.executeUpdate();
				                     System.out.println(RecordCount2 + " registo alterado");
				                 }
							}
		                    
						}
						pstat3.close();
					}
					
				}
			}
			if(!existe) {
				System.out.println("Erro: A equipa " + name + " não existe");
			}
	
		}catch(SQLException e) {
			System.out.println("Erro: "+e.getMessage());
		}		
	}
}
