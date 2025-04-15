public class Arena {

    private int idBatalha;
    private Fila listaDeParticipantes;
    private Pilha rankingFinal;
    private int turnoAtual;
    private String estadoBatalha;
    private Personagem[] referencia;

    public Arena(int idBatalha, int tamanhoMaximo) {
        this.idBatalha = idBatalha;
        this.listaDeParticipantes = new Fila(tamanhoMaximo);
        this.rankingFinal = new Pilha(tamanhoMaximo);
        this.turnoAtual = 0;
        this.estadoBatalha = "Preparando";
    }

    public void iniciarBatalha(Personagem[] participantes) {
        this.referencia = participantes;
        for (Personagem personagem : participantes) {
            if (personagem != null && personagem.estaVivo()) {
                listaDeParticipantes.add(personagem.getIdPersonagem());
                System.out.println(personagem.nome + " entrou na batalha.");
            }
        }
        this.estadoBatalha = "Em andamento";
        System.out.println("Batalha iniciada!");
    }
    
    private Personagem buscarPersonagemPorId(int id) {
        for (Personagem p : referencia) {
            if (p != null && p.getIdPersonagem() == id) {
                return p;
            }
        }
        return null;
    }
    

    public void executarTurno() {
        if (listaDeParticipantes.head == null) {
            System.out.println("Não há ninguém vivo na batalha");
            return;
        }
    
        int idPersonagemAtual = listaDeParticipantes.head.data;
        listaDeParticipantes.remove();
    
        Personagem personagem = buscarPersonagemPorId(idPersonagemAtual);
        if (personagem == null) {
            System.out.println(idPersonagemAtual + " não encontrado");
            return;
        }
    
        System.out.println("Turno do personagem: " + personagem.nome);
        System.out.println(personagem.nome + " realizou sua ação");
    
        if (personagem.estaVivo()) {
            listaDeParticipantes.add(personagem.getIdPersonagem());
            System.out.println(personagem.nome + " sobreviveu");
        } else {
            rankingFinal.push(personagem.getIdPersonagem());
            System.out.println(personagem.nome + " foi derrotado");
        }
    
        turnoAtual++;
    }
    

    public void verificarVencedor() {
        if (listaDeParticipantes.head != null && listaDeParticipantes.head.next == null) {
            int idVencedor = listaDeParticipantes.head.data;
            rankingFinal.push(idVencedor);
            System.out.println("O vencedor é o personagem com ID: " + idVencedor);
            estadoBatalha = "Finalizada";
        } else if (listaDeParticipantes.head == null) {
            System.out.println("Todos os participantes foram derrotados. Não há vencedor.");
            estadoBatalha = "Finalizada";
        }
    }

    public void exibirRankingFinal() {
        System.out.println("*** Ranking Final ***");
        Pilha copiaRanking = new Pilha(rankingFinal.size);
        
        while (rankingFinal.head != null) {
            int idPersonagem = rankingFinal.head.data;
            Personagem personagem = buscarPersonagemPorId(idPersonagem);
    
            if (personagem != null) {
                System.out.println(personagem.nome);
            } else {
                System.out.println(idPersonagem + " não encontrado");
            }
    
            rankingFinal.pop();
            copiaRanking.push(idPersonagem);
        }
    
        rankingFinal = copiaRanking;
    }
    

    public int getIdBatalha() {
        return idBatalha;
    }

    public int getTurnoAtual() {
        return turnoAtual;
    }

    public String getEstadoBatalha() {
        return estadoBatalha;
    }

}