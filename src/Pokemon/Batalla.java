package Pokemon;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.neo4j.driver.Record;

import Objeto.Ataque;
import Objeto.Entrenador;
import Objeto.Pokemon;
import Objeto.Tipo;

public class Batalla {

	private Entrenador entrenadorRival;
	private Entrenador entrenadorYo;
	private Pokemon luchaYo;
	private Pokemon luchaRival;
	private int indicePokemonRival;

	public Batalla(String entrenadorRival, String entrenadorYo) {
		this.indicePokemonRival = 0;
		this.entrenadorRival = new Entrenador(entrenadorRival);
		this.entrenadorYo = new Entrenador(entrenadorYo);
		cargarPokemons(entrenadorRival, this.entrenadorRival, luchaRival);
		cargarPokemons(entrenadorYo, this.entrenadorYo, luchaYo);
		this.luchaYo = this.entrenadorYo.getPokemons().get(0);
		this.luchaRival = this.entrenadorRival.getPokemons().get(0);
		calculoPotenciaAtaque(this.luchaYo, this.luchaRival);
		calculoPotenciaAtaque(this.luchaRival, this.luchaYo);
	}

	public void cargarPokemons(String entrenador, Entrenador entrenadorObj, Pokemon principal) {
		List<Record> pokemons = Constantes.DBNeo.pokemonDe(entrenador);
		List<Pokemon> misPokemons = new ArrayList<>();
		for (int i = 0; i < pokemons.size(); i++) {
			// Especie
			List<Record> especie = Constantes.DBNeo.especieDe(pokemons.get(i).get("pokemon").get("pokeID").asInt());

			// Tipos
			List<Tipo> tipos = new ArrayList<>();
			List<Record> tipo1Rec = Constantes.DBNeo.tipo1Pokemon(especie.get(0).get("especie").get("Number").asInt());

			Tipo tipo1 = new Tipo(tipo1Rec.get(0).get("tipo").get("name").asString().replace("\"", ""),
					tipo1Rec.get(0).get("tipo").get("Id").asInt());
			tipos.add(tipo1);
			List<Record> tipo2Rec = Constantes.DBNeo.tipo2Pokemon(especie.get(0).get("especie").get("Number").asInt());
			if (tipo2Rec.size() != 0) {
				Tipo tipo2 = new Tipo(tipo2Rec.get(0).get("tipo").get("name").asString().replace("\"", ""),
						tipo2Rec.get(0).get("tipo").get("Id").asInt());
				tipos.add(tipo2);
			}

			// Ataques
			List<Ataque> ataques = new ArrayList<>();
			List<Record> ataquesRec = Constantes.DBNeo.ataquesDe(pokemons.get(i).get("pokemon").get("pokeID").asInt());
			for (int j = 0; j < ataquesRec.size(); j++) {
				List<Record> tipoAtaqueRec = Constantes.DBNeo
						.tipoMovimiento(ataquesRec.get(j).get("ataque").get("moveId").asInt());
				Tipo tipoAtaque = new Tipo(tipoAtaqueRec.get(0).get("tipo").get("name").asString().replace("\"", ""),
						tipoAtaqueRec.get(0).get("tipo").get("Id").asInt());
				Ataque ataque = new Ataque(ataquesRec.get(j).get("ataque").get("moveId").asInt(),
						ataquesRec.get(j).get("ataque").get("moveName").asString().replace("\"", ""),
						ataquesRec.get(j).get("ataque").get("power").asInt(), tipoAtaque);
				ataques.add(ataque);
			}

			Pokemon poke = new Pokemon(pokemons.get(i).get("pokemon").get("mote").asString().replace("\"", ""),
					pokemons.get(i).get("pokemon").get("pokeID").asInt(),
					pokemons.get(i).get("pokemon").get("nivel").asInt(),
					pokemons.get(i).get("pokemon").get("pokeEspecie").asString().replace("\"", ""), tipos, ataques,
					especie.get(0).get("especie").get("Gif").asString().replace("\"", ""),
					especie.get(0).get("especie").get("Espalda").asString().replace("\"", ""),
					especie.get(0).get("especie").get("Sprite").asString().replace("\"", ""));
			calculoDeEstadisticas(poke);
			misPokemons.add(poke);
			//
		}

		entrenadorObj.setPokemons(misPokemons);

	}

	public void calculoDeEstadisticas(Pokemon poke) {

		List<Record> especiePoke1 = Constantes.DBNeo.especieDe(poke.getPokeId());

		// Calculo de stats por nivel

		// Nivel Pokes

		// Vida Poke1
		int psPoke1 = especiePoke1.get(0).get("especie").get("HP").asInt();
		int psTotalPoke1 = (((2 * psPoke1) * poke.getNivel()) / 100) + poke.getNivel() + 10;
		poke.setVidaTotal(psTotalPoke1);
		poke.setVidaRestante(psTotalPoke1);
		// System.out.println(psPoke1+" "+psTotalPoke1+" "+nivelPoke1);

		// Ataque,ataque especial y media Poke1
		int ataquePoke1 = especiePoke1.get(0).get("especie").get("Attack").asInt();
		int ataqueEspecialPoke1 = especiePoke1.get(0).get("especie").get("SpAtk").asInt();
		int ataqueCalc = (((2 * ataquePoke1) * poke.getNivel()) / 100) + poke.getNivel() + 10;
		int ataqueEspCalc = (((2 * ataqueEspecialPoke1) * poke.getNivel()) / 100) + poke.getNivel() + 10;
		int ataqueMedio = (ataqueCalc + ataqueEspCalc) / 2;
		poke.setAtaque(ataqueCalc);
		poke.setAtaqueEspecial(ataqueEspCalc);
		poke.setAtaqueTotal(ataqueMedio);
		// System.out.println(ataquePoke1+" "+ataqueEspecialPoke1+" "+ataqueCalc+"
		// "+ataqueEspCalc+" "+ataqueMedio);

		// Defensa, defensa especial y media poke1
		int defensaPoke1 = especiePoke1.get(0).get("especie").get("Defense").asInt();
		int defensaEspecialPoke1 = especiePoke1.get(0).get("especie").get("SpDef").asInt();
		int defensaCalc = (((2 * defensaPoke1) * poke.getNivel()) / 100) + poke.getNivel() + 10;
		int defensaEspCalc = (((2 * defensaEspecialPoke1) * poke.getNivel()) / 100) + poke.getNivel() + 10;
		int defensaMedia = (defensaCalc + defensaEspCalc) / 2;
		poke.setDefensa(defensaCalc);
		poke.setDefensaEspecial(defensaEspCalc);
		poke.setDefensaTotal(defensaMedia);
		// System.out.println(defensaPoke1+" "+defensaEspecialPoke1+" "+defensaCalc+"
		// "+defensaEspCalc+" "+defensaMedia);

		// Velocidad de los pokes
		int velPoke1 = especiePoke1.get(0).get("especie").get("Speed").asInt();
		int velCalc1 = (((2 * velPoke1) * poke.getNivel()) / 100) + poke.getNivel() + 10;
		poke.setVelocidad(velCalc1);
		// System.out.println(velPoke1+" "+velPoke2+" "+velCalc1+" "+velCalc2);

	}

	public void calculoPotenciaAtaque(Pokemon pokemonYo, Pokemon pokemonRival) {
		List<Ataque> ataquesYo = pokemonYo.getAtaques();
		List<Tipo> tiposRival = pokemonRival.getTipos();

		List<Double> potenciaConBonos = new ArrayList<>();

		for (int i = 0; i < ataquesYo.size(); i++) {
			double multiplicador = 1;
			double potencia = ataquesYo.get(i).getPotencia();

			if (pokemonYo.getTipos().get(0).getNombre().equals(ataquesYo.get(i).getTipo().getNombre())) {
				potencia = potencia * 1.5;
			
			} else {
				if (pokemonYo.getTipos().size() > 1) {
					if (pokemonYo.getTipos().get(1).getNombre().equals(ataquesYo.get(i).getTipo().getNombre())) {
						potencia = potencia * 1.5;
					
					}
				}
			}

			if (pokemonRival.getTipos().size() == 1) {
				List<Record> bonoConTipo1 = Constantes.DBNeo.bonoDeDano(ataquesYo.get(i).getTipo().getNombre(),
						pokemonRival.getTipos().get(0).getNombre());
				if (bonoConTipo1.size() > 0) {
					multiplicador *= bonoConTipo1.get(0).get("r.bonus").asDouble();
				}
			} else {
				if (pokemonRival.getTipos().size() > 1) {
					List<Record> bonoConTipo1 = Constantes.DBNeo.bonoDeDano(ataquesYo.get(i).getTipo().getNombre(),
							pokemonRival.getTipos().get(0).getNombre());
					List<Record> bonoConTipo2 = Constantes.DBNeo.bonoDeDano(ataquesYo.get(i).getTipo().getNombre(),
							pokemonRival.getTipos().get(1).getNombre());
					if (bonoConTipo1.size() > 0) {
						multiplicador *= bonoConTipo1.get(0).get("r.bonus").asDouble();

					}
					if (bonoConTipo2.size() > 0) {
						multiplicador *= bonoConTipo2.get(0).get("r.bonus").asDouble();

					}

				}
			}
			potencia = potencia * multiplicador;
			pokemonYo.getAtaques().get(i).setPotenciaTotal(potencia);
		}
		

	}

	// Al pulsar un ataque
	public int calculoDano(Pokemon pokemonYo, Pokemon pokemonRival, Ataque ataque) {
		int danoTotal = 0;// Cantidad de daño total que proporciona el ataque(numero de ps)

		// Numero aleatorio entre 85 y 100
		Random numAleatorio = new Random();
		int variacion = numAleatorio.nextInt(100 - 85 + 1) + 85;

		// Formula del daño:Daño (PS) = [{([{(Nv. * 2 / 5) + 2} * Poder * Ataque / 50] /
		// Defensa) + 2} * Rnd / 100] * STAB * Efec;(el random entre 85 y 100)

		// POTENCIA DEL MOVIMIENTO
		double potenciaTotal = ataque.getPotenciaTotal();

		danoTotal = (int) ((((((pokemonYo.getNivel() * 2 / 5) + 2) * potenciaTotal * pokemonYo.getAtaqueTotal() / 50)
				/ pokemonRival.getDefensaTotal()) + 2) * variacion / 100);

		return danoTotal;
	}

	// permite cambiar el pokemon del rival una vez muerto de forma secuencial
	public void cambiarPokemonRival() {
		indicePokemonRival++;
		luchaRival = entrenadorRival.getPokemons().get(indicePokemonRival);
	}

	// Determina si todos los pokemons del equipo de un entrenador dado no tienen
	// vida.
	public boolean equipoEliminado(Entrenador entrenador) {
		boolean muerto = true;
		int i = 0;
		while (i < entrenador.getPokemons().size() && muerto) {
			if (entrenador.getPokemons().get(i).getVidaRestante() > 0) {
				muerto = false;

			}
			i++;
		}
		return muerto;
	}

	// Realiza el combate y en el caso de acabar con la vida del rival y todo su
	// equipo devuelve true.
	public boolean combate(Ataque ataqueYo) {
		boolean victoria = false;
		Random r = new Random();
		int numRand = r.nextInt(4);
		// System.out.println("aleatorio: "+numRand);
		if (this.luchaRival.getVelocidad() > this.luchaYo.getVelocidad()) {

			luchaYo.setVidaRestante(
					luchaYo.getVidaRestante() - calculoDano(luchaRival, luchaYo, luchaRival.getAtaques().get(numRand)));
			if (luchaYo.getVidaRestante() > 0) {
				luchaRival.setVidaRestante(luchaRival.getVidaRestante() - calculoDano(luchaYo, luchaRival, ataqueYo));
			}
		} else {
			luchaRival.setVidaRestante(luchaRival.getVidaRestante() - calculoDano(luchaYo, luchaRival, ataqueYo));
			if (luchaRival.getVidaRestante() > 0) {
				luchaYo.setVidaRestante(luchaYo.getVidaRestante()
						- calculoDano(luchaRival, luchaYo, luchaRival.getAtaques().get(numRand)));
			}
		}

		if (luchaRival.getVidaRestante() <= 0) {
			// cambiar pokemon rival
			if (!equipoEliminado(entrenadorRival)) {
				cambiarPokemonRival();
			} else {
				victoria = true;
			}
		}
		return victoria;
	}

	public Entrenador getEntrenadorRival() {
		return entrenadorRival;
	}

	public void setEntrenadorRival(Entrenador entrenadorRival) {
		this.entrenadorRival = entrenadorRival;
	}

	public Entrenador getEntrenadorYo() {
		return entrenadorYo;
	}

	public void setEntrenadorYo(Entrenador entrenadorYo) {
		this.entrenadorYo = entrenadorYo;
	}

	public Pokemon getLuchaYo() {
		return luchaYo;
	}

	public void setLuchaYo(Pokemon luchaYo) {
		this.luchaYo = luchaYo;
	}

	public Pokemon getLuchaRival() {
		return luchaRival;
	}

	public void setLuchaRival(Pokemon luchaRival) {
		this.luchaRival = luchaRival;
	}

}
