
public class Teste {

	public static void main(String[] args) {
		String op1;
		String op;
		do {
			System.out.println("\n*** Main ***");
			System.out.println("1 - Listar");
			System.out.println("2 - Procurar");
			System.out.println("3 - Inserir");
			System.out.println("4 - Alterar");
			System.out.println("5 - Sair");
			System.out.print("Escolha: ");
			op1=EuropeFootballLeagues.input.nextLine();
			switch (op1) {
				case "1":
					System.out.println("\n1 - Listar Ligas");
					System.out.println("2 - Listar Equipas");
					System.out.println("3 - Listar Partidas");
					System.out.println("4 - Sair");
					System.out.print("Escolha: ");
					op=EuropeFootballLeagues.input.nextLine();
					switch(op) {
						case "1":
							EuropeFootballLeagues.ListarLeagues();
							break;
						case "2":
							EuropeFootballLeagues.ListarTeams();
							break;
						case "3":
							EuropeFootballLeagues.ListarMatches();
							break;
						case "4":
							break;
					}
					break;
				case "2":
					System.out.println("\n1 - Procurar Ligas");
					System.out.println("2 - Procurar Equipas");
					System.out.println("3 - Procurar Partidas");
					System.out.println("4 - Sair");
					System.out.print("Escolha: ");
					op=EuropeFootballLeagues.input.nextLine();
					switch(op) {
						case "1":
							EuropeFootballLeagues.ProcurarLeagues();
							break;
						case "2":
							EuropeFootballLeagues.ProcurarTeams();
							break;
						case "3":
							EuropeFootballLeagues.ProcurarMatches();
							break;
						case "4":
							break;
					}
					break;
				case "3":
					System.out.println("\n1 - Inserir Liga");
					System.out.println("2 - Inserir Equipa");
					System.out.println("3 - Inserir Partidas");
					System.out.println("4 - Sair");
					System.out.print("Escolha: ");
					op=EuropeFootballLeagues.input.nextLine();
					switch(op) {
						case "1":
							EuropeFootballLeagues.InserirLeagues();
							break;
						case "2":
							EuropeFootballLeagues.InserirTeams();
							break;
						case "3":
							EuropeFootballLeagues.InserirMatches();
							break;
						case "4":
							break;
					}
					break;
				case "4":
					System.out.println("\n1 - Alterar Liga");
					System.out.println("2 - Alterar Equipa");
					System.out.println("3 - Sair");
					System.out.print("Escolha: ");
					op=EuropeFootballLeagues.input.nextLine();
					switch(op) {
						case "1":
							EuropeFootballLeagues.AlterarLeagues();
							break;
						case "2":
							EuropeFootballLeagues.AlterarTeams();
							break;
						case "3":
							break;
					}
					break;
			}
		} while (!op1.equalsIgnoreCase("5"));
		System.out.println("Até a proxima!");
		EuropeFootballLeagues.input.close();

	}

	

}
