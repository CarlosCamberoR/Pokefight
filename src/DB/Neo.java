package DB;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.DefaultListModel;

import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Config;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Query;
import org.neo4j.driver.Record;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.SessionConfig;
import org.neo4j.driver.exceptions.Neo4jException;

public class Neo implements AutoCloseable {
	private static final Logger LOGGER = Logger.getLogger(Neo.class.getName());
	private final Driver driver;

	public Neo(String uri, String user, String password, Config config) {
		// The driver is a long living object and should be opened during the start of
		// your application
		driver = GraphDatabase.driver(uri, AuthTokens.basic(user, password), config);
		try {
			Session session = driver.session();
			if (session.isOpen()) {
				System.out.println("Conexión establecida correctamente");
			} else {
				System.out.println("No se pudo establecer la conexión");
			}
			session.close();
		} catch (Exception e) {
			System.out.println("Ocurrió un error al conectarse a la base de datos: " + e.getMessage());
		}
	}

	@Override
	public void close() {
		// The driver object should be closed before the application ends.
		driver.close();

	}

	// TODO
	// Comprueba si existe un entrenador y si no existe lo crea
	public boolean existeEntrenador(final String nombre) {
		boolean existe = true;
		var query = new Query("""
				MATCH (t:Trainer)
				WHERE t.Nombre = $nombre
				RETURN t
				""", Map.of("nombre", nombre));

		try (var session = driver.session(SessionConfig.forDatabase("neo4j"))) {
			Result result = session.run(query);
			if (!result.hasNext()) {

				// Creación de nodo
				existe = false;
			}

		} catch (Neo4jException ex) {
			LOGGER.log(Level.SEVERE, query + " raised an exception", ex);
			throw ex;
		}

		return existe;
	}

	// Devuelve los entrenadores situados a un máximo de 2 saltos del entrenador
	// pasado por parámetros (2 Saltos)
	public List<Record> posiblesRivales(final String entrenador) {
		List<Record> posiblesRivales = new ArrayList<>();
		var query = new Query("""
				MATCH (t:Trainer)-[k:KNOWS*1..2]-(rival:Trainer)
				WHERE t.Nombre=$entrenador
				RETURN DISTINCT rival
				""", Map.of("entrenador", entrenador));

		try (var session = driver.session(SessionConfig.forDatabase("neo4j"))) {
			Result result = session.run(query);
			while (result.hasNext()) {
				posiblesRivales.add(result.next());

			}

			// You should capture any errors along with the query and data for traceability
		} catch (Neo4jException ex) {
			LOGGER.log(Level.SEVERE, query + " raised an exception", ex);
			throw ex;
		}

		return posiblesRivales;
	}

	// En lugar de los ids pueden ser los nodos enteros
	public List<Record> potenciaMovimiento(final int movimientoId) {
		List<Record> potencia = new ArrayList<>();
		// Devuelve el movimiento
		var query = new Query("""
				MATCH (move:Move)
				WHERE move.moveId = $movimientoId
				RETURN move
				""", Map.of("movimientoId", movimientoId));

		try (var session = driver.session(SessionConfig.forDatabase("neo4j"))) {
			Result result = session.run(query);
			while (result.hasNext()) {
				potencia.add(result.next());

			}
			// You should capture any errors along with the query and data for traceability
		} catch (Neo4jException ex) {
			LOGGER.log(Level.SEVERE, query + " raised an exception", ex);
			throw ex;
		}

		return potencia;
	}

	public List<Record> ataquesDe(final int pokeID) {
		List<Record> resultado = new ArrayList<>();
		var query = new Query("""
				MATCH (n:Pokemon)-[r2:PUEDE_USAR]-(ataque:Move)
				WHERE n.pokeID=$pokeID
				RETURN ataque
				         """, Map.of("pokeID", pokeID));
		try (var session = driver.session(SessionConfig.forDatabase("neo4j"))) {
			Result result = session.run(query);
			while (result.hasNext()) {
				resultado.add(result.next());

			}

		} catch (Neo4jException ex) {
			LOGGER.log(Level.SEVERE, query + " raised an exception", ex);
			throw ex;
		}
		return resultado;
	}

	public List<Record> especieDe(final int pokeID) {
		List<Record> resultado = new ArrayList<>();
		var query = new Query("""
				MATCH (p:Pokemon),(especie:PokemonPokedex)
				WHERE p.pokeID=$pokeID AND (p.pokeEspecie=especie.Name)
				RETURN especie
				 """, Map.of("pokeID", pokeID));
		try (var session = driver.session(SessionConfig.forDatabase("neo4j"))) {
			Result result = session.run(query);
			while (result.hasNext()) {
				resultado.add(result.next());

			}

		} catch (Neo4jException ex) {
			LOGGER.log(Level.SEVERE, query + " raised an exception", ex);
			throw ex;
		}
		return resultado;

	}

	// Devuelve los pokemons de un entrenador concreto
	public List<Record> pokemonDe(final String entrenador) {
		List<Record> resultado = new ArrayList<>();
		var query = new Query("""
				MATCH (pokemon:Pokemon)<-[r:ES_ENTRENADOR_DE]-(t:Trainer)
				WHERE t.Nombre= $entrenador
				RETURN pokemon
				""", Map.of("entrenador", entrenador));

		try (var session = driver.session(SessionConfig.forDatabase("neo4j"))) {
			Result result = session.run(query);
			while (result.hasNext()) {
				resultado.add(result.next());

			}

		} catch (Neo4jException ex) {
			LOGGER.log(Level.SEVERE, query + " raised an exception", ex);
			throw ex;
		}
		return resultado;
	}

	// Devuelve los Sprite de pokemons de un entrenador concreto (2 SALTOS)
	public List<String> pokemonDeSprite(final String entrenador) {
		List<String> resultado = new ArrayList<>();
		var query = new Query("""
				MATCH (t:Trainer)-[r:ES_ENTRENADOR_DE]->(pokemon:Pokemon)-[]-(pp:PokemonPokedex)
				WHERE t.Nombre= $entrenador
				RETURN pp.Sprite
				""", Map.of("entrenador", entrenador));

		try (var session = driver.session(SessionConfig.forDatabase("neo4j"))) {
			Result result = session.run(query);
			while (result.hasNext()) {
				resultado.add(result.next().get("pp.Sprite").asString().replace("\"", ""));

			}

		} catch (Neo4jException ex) {
			LOGGER.log(Level.SEVERE, query + " raised an exception", ex);
			throw ex;
		}
		return resultado;
	}

	public String findPokemon(final String pokemonName) {
		var query = new Query("""
				MATCH (n:PokemonPokedex)
				WHERE toLower(n.Name)
				CONTAINS toLower($pokemonName)
				RETURN n
				""", Map.of("pokemonName", pokemonName));

		try (var session = driver.session(SessionConfig.forDatabase("neo4j"))) {
			var record = session.executeRead(tx -> tx.run(query).single());

			for (int i = 0; i < record.values().size(); i++) {
				System.out.println(record.values().get(i).get("Name", "jose"));
			}
			System.out.printf("Found pokemon: %s%n", record.get(0).get("Name").asString());
			return record.get(0).get("Gif").asString();

			// You should capture any errors along with the query and data for traceability
		} catch (Neo4jException ex) {
			LOGGER.log(Level.SEVERE, query + " raised an exception", ex);
			throw ex;
		}

	}

	// Devuelve el tipo de un movimiento
	public List<Record> tipoMovimiento(final int movimientoId) {
		List<Record> tipo = new ArrayList<>();

		var query = new Query("""
				MATCH (move:Move)-[r:TYPE_IS]-(tipo:Type)
				WHERE move.moveId = $movimientoId
				RETURN tipo
				""", Map.of("movimientoId", movimientoId));

		try (var session = driver.session(SessionConfig.forDatabase("neo4j"))) {
			Result result = session.run(query);
			while (result.hasNext()) {
				tipo.add(result.next());

			}
			// You should capture any errors along with the query and data for traceability
		} catch (Neo4jException ex) {
			LOGGER.log(Level.SEVERE, query + " raised an exception", ex);
			throw ex;
		}

		return tipo;
	}

	// Devuelve el bono de daño entre dos tipos
	public List<Record> bonoDeDano(final String tipoAtaque, final String tipoRival) {
		List<Record> bonoDeDano = new ArrayList<>();

		var query = new Query("""
				MATCH (tipo:Type)-[r]->(tipo2:Type)
				WHERE tipo.name=$tipoAtaque AND tipo2.name=$tipoRival
				RETURN r.bonus
				""", Map.of("tipoAtaque", tipoAtaque, "tipoRival", tipoRival));

		try (var session = driver.session(SessionConfig.forDatabase("neo4j"))) {
			Result result = session.run(query);
			while (result.hasNext()) {
				bonoDeDano.add(result.next());

			}
			// You should capture any errors along with the query and data for traceability
		} catch (Neo4jException ex) {
			LOGGER.log(Level.SEVERE, query + " raised an exception", ex);
			throw ex;
		}

		return bonoDeDano;
	}

	// Devuelve un pokemon dado su pokeID
	public List<Record> pokemonPorId(final int pokeId) {
		List<Record> poke = new ArrayList<>();

		var query = new Query("""
				MATCH (pokemon:Pokemon)
				WHERE pokemon.pokeID = $pokeId
				RETURN pokemon
				""", Map.of("pokeId", pokeId));

		try (var session = driver.session(SessionConfig.forDatabase("neo4j"))) {
			Result result = session.run(query);
			while (result.hasNext()) {
				poke.add(result.next());

			}
			// You should capture any errors along with the query and data for traceability
		} catch (Neo4jException ex) {
			LOGGER.log(Level.SEVERE, query + " raised an exception", ex);
			throw ex;
		}

		return poke;
	}

	// Devuelve el tipo1 de un pokemon
	public List<Record> tipo1Pokemon(final int pokeNumber) {
		List<Record> tiposPokemon = new ArrayList<>();

		var query = new Query("""
				MATCH (PokePo:PokemonPokedex)-[r:TYPE1_IS]-(tipo:Type)
				WHERE PokePo.Number= $pokeNumber
				RETURN tipo
				""", Map.of("pokeNumber", pokeNumber));

		try (var session = driver.session(SessionConfig.forDatabase("neo4j"))) {
			Result result = session.run(query);
			while (result.hasNext()) {
				tiposPokemon.add(result.next());

			}
			// You should capture any errors along with the query and data for traceability
		} catch (Neo4jException ex) {
			LOGGER.log(Level.SEVERE, query + " raised an exception", ex);
			throw ex;
		}

		return tiposPokemon;
	}

	// Devuelve el tipo2 de un pokemon
	public List<Record> tipo2Pokemon(final int pokeNumber) {
		List<Record> tiposPokemon = new ArrayList<>();

		var query = new Query("""
				MATCH (PokePo:PokemonPokedex)-[r:TYPE2_IS]-(tipo:Type)
				WHERE PokePo.Number= $pokeNumber
				RETURN tipo
				""", Map.of("pokeNumber", pokeNumber));

		try (var session = driver.session(SessionConfig.forDatabase("neo4j"))) {
			Result result = session.run(query);
			while (result.hasNext()) {
				tiposPokemon.add(result.next());

			}
			// You should capture any errors along with the query and data for traceability
		} catch (Neo4jException ex) {
			LOGGER.log(Level.SEVERE, query + " raised an exception", ex);
			throw ex;
		}

		return tiposPokemon;
	}

	// Devuelve los entrenadores amigos del entrenador pasado por parámetros
	public DefaultListModel<String> entrenadoresAmigos(final String entrenador) {
		DefaultListModel<String> amigos = new DefaultListModel<>();
		var query = new Query("""
				MATCH (t:Trainer)-[k:KNOWS]-(rival:Trainer)
				WHERE t.Nombre=$entrenador
				RETURN DISTINCT rival.Nombre
				""", Map.of("entrenador", entrenador));

		try (var session = driver.session(SessionConfig.forDatabase("neo4j"))) {
			Result result = session.run(query);
			while (result.hasNext()) {
				amigos.addElement(result.next().get("rival.Nombre").asString().replace("\"", ""));

			}

			// You should capture any errors along with the query and data for traceability
		} catch (Neo4jException ex) {
			LOGGER.log(Level.SEVERE, query + " raised an exception", ex);
			throw ex;
		}

		return amigos;
	}

	// Devuelve los entrenadores que no son amigos del entrenador pasado por
	// parámetros
	public List<String> entrenadorNoAmigos(final String nombre) {
		List<String> noAmigos = new ArrayList<>();
		var query = new Query("""
				MATCH (t:Trainer),(noAmigo:Trainer)
				WHERE NOT (t)-[:KNOWS]-(noAmigo) AND t.Nombre=$nombre AND t<>noAmigo
				RETURN noAmigo.Nombre
				""", Map.of("nombre", nombre));

		try (var session = driver.session(SessionConfig.forDatabase("neo4j"))) {
			Result result = session.run(query);
			while (result.hasNext()) {
				noAmigos.add(result.next().get("noAmigo.Nombre").asString().replace("\"", ""));

			}
			// You should capture any errors along with the query and data for traceability
		} catch (Neo4jException ex) {
			LOGGER.log(Level.SEVERE, query + " raised an exception", ex);
			throw ex;
		}

		return noAmigos;
	}

	// Devuelve la lista de especies de pokemon que tienen algún tipo con ventaja
	// sobre la especie pasada por parámetro (3 SALTOS)
	public DefaultListModel<String> pokemonConVentaja(final String nombreEspecie) {
		DefaultListModel<String> pokemonConVentaja = new DefaultListModel<String>();
		var query = new Query(
				"""
						MATCH (pp1:PokemonPokedex)-[k]-(t1:Type)<-[t:VERY_EFECTIVE_AGAINST]-(t2:Type)-[l]-(pokemonVentaja:PokemonPokedex)
						WHERE toLower(pp1.Name)=toLower($nombreEspecie)
						RETURN pokemonVentaja.Name
						""",
				Map.of("nombreEspecie", nombreEspecie));

		try (var session = driver.session(SessionConfig.forDatabase("neo4j"))) {
			Result result = session.run(query);
			while (result.hasNext()) {
				pokemonConVentaja.addElement(result.next().get("pokemonVentaja.Name").asString().replace("\"", ""));

			}
			// You should capture any errors along with the query and data for traceability
		} catch (Neo4jException ex) {
			LOGGER.log(Level.SEVERE, query + " raised an exception", ex);
			throw ex;
		}

		return pokemonConVentaja;
	}

	// Devuleve el id más alto de los entrenadores
	public List<Record> trainerMaxId() {
		List<Record> trainerMaxId = new ArrayList<>();
		int maxId = 0;
		var query = new Query("""
				match (t:Trainer)
				return max(t.TrainerID)
				""", Map.of("maxId", maxId));

		try (var session = driver.session(SessionConfig.forDatabase("neo4j"))) {
			Result result = session.run(query);
			while (result.hasNext()) {
				trainerMaxId.add(result.next());

			}
			// You should capture any errors along with the query and data for traceability
		} catch (Neo4jException ex) {
			LOGGER.log(Level.SEVERE, query + " raised an exception", ex);
			throw ex;
		}

		return trainerMaxId;
	}

	// Crea un nuevo entrenador
	public List<Record> nuevoEntrenador(final String Nombre, final int trainerID) {
		List<Record> newTrainer = new ArrayList<>();
		var query = new Query("""
				CREATE (t:Trainer {Nombre:$Nombre, TrainerID:$trainerID})
				RETURN t
				""", Map.of("Nombre", Nombre, "trainerID", trainerID));

		try (var session = driver.session(SessionConfig.forDatabase("neo4j"))) {
			Result result = session.run(query);
			while (result.hasNext()) {
				newTrainer.add(result.next());

			}
			// You should capture any errors along with the query and data for traceability
		} catch (Neo4jException ex) {
			LOGGER.log(Level.SEVERE, query + " raised an exception", ex);
			throw ex;
		}

		return newTrainer;
	}

	// Elimina un entrenador
	public String eliminaEntrenador(final String Nombre) {
		var query = new Query("""
				MATCH (t:Trainer)
				WHERE t.Nombre=$Nombre
				DELETE t
				""", Map.of("Nombre", Nombre));

		try (var session = driver.session(SessionConfig.forDatabase("neo4j"))) {
			Result result = session.run(query);

		} catch (Neo4jException ex) {
			LOGGER.log(Level.SEVERE, query + " raised an exception", ex);
			throw ex;
		}

		String salida = "El entrenador " + Nombre + " se ha eliminado correctamente.";

		return salida;
	}

	// Elimina un amigo
	public void eliminaAmigo(final String NombreUser, final String NombreAmigo) {
		var query = new Query("""
				MATCH (t:Trainer)-[r:KNOWS]-(t1:Trainer)
				WHERE t.Nombre=$NombreUser and t1.Nombre=$NombreAmigo
				DELETE r
				""", Map.of("NombreUser", NombreUser, "NombreAmigo", NombreAmigo));

		try (var session = driver.session(SessionConfig.forDatabase("neo4j"))) {
			Result result = session.run(query);
		} catch (Neo4jException ex) {
			LOGGER.log(Level.SEVERE, query + " raised an exception", ex);
			throw ex;
		}

	}

	// Crea un nuevo amigo
	public void nuevoAmigo(final String Nombre, final String NombreAmigo) {
		var query = new Query("""
				MATCH (t:Trainer),(t1:Trainer)
				WHERE t.Nombre=$Nombre and t1.Nombre=$NombreAmigo
				CREATE (t)-[r:KNOWS]->(t1)
				RETURN t1
				""", Map.of("Nombre", Nombre, "NombreAmigo", NombreAmigo));

		try (var session = driver.session(SessionConfig.forDatabase("neo4j"))) {
			session.run(query);

		} catch (Neo4jException ex) {
			LOGGER.log(Level.SEVERE, query + " raised an exception", ex);
			throw ex;
		}

	}

	// Busqueda de noAmigo por substring
	public List<String> busquedaNoAmigos(final String entrenador, final String nombre) {
		List<String> noAmigosPorNombre = new ArrayList<>();
		var query = new Query("""
				MATCH (t:Trainer),(noAmigo:Trainer)
				      WHERE NOT (t)-[:KNOWS]-(noAmigo) AND t.Nombre=$entrenador AND t<>noAmigo
				      AND toLower(noAmigo.Nombre) CONTAINS toLower($nombre)
				      RETURN noAmigo.Nombre
				      """, Map.of("entrenador", entrenador, "nombre", nombre));

		try (var session = driver.session(SessionConfig.forDatabase("neo4j"))) {
			Result result = session.run(query);
			while (result.hasNext()) {
				noAmigosPorNombre.add(result.next().get("noAmigo.Nombre").asString().replace("\"", ""));
			}
			// You should capture any errors along with the query and data for traceability
		} catch (Neo4jException ex) {
			LOGGER.log(Level.SEVERE, query + " raised an exception", ex);
			throw ex;
		}

		return noAmigosPorNombre;
	}

	// Cuenta el numero de pokemon de un entrenador pasando como parametro el nombre
	// del entrenador
	public int numeroPokemons(final String nombreEntrenador) {
		int numeroPokemons = 0;
		var query = new Query("""
				MATCH (t:Trainer)-[r:ES_ENTRENADOR_DE]-(p:Pokemon)
				WHERE t.Nombre=$nombreEntrenador
				RETURN COUNT(p)
				""", Map.of("nombreEntrenador", nombreEntrenador));

		try (var session = driver.session(SessionConfig.forDatabase("neo4j"))) {
			Result result = session.run(query);
			while (result.hasNext()) {
				numeroPokemons = result.next().get("COUNT(p)").asInt();
			}
			// You should capture any errors along with the query and data for traceability
		} catch (Neo4jException ex) {
			LOGGER.log(Level.SEVERE, query + " raised an exception", ex);
			throw ex;
		}

		return numeroPokemons;
	}

	// Devuleve el id más alto de los pokemon
	public int pokemonMaxId() {
		int pokemonMaxId = 0;
		int maxId = 0;
		var query = new Query("""
				match (p:Pokemon)
				return max(p.pokeID)
				""", Map.of("maxId", maxId));

		try (var session = driver.session(SessionConfig.forDatabase("neo4j"))) {
			Result result = session.run(query);
			while (result.hasNext()) {
				pokemonMaxId = result.next().get("max(p.pokeID)").asInt();

			}
			// You should capture any errors along with the query and data for traceability
		} catch (Neo4jException ex) {
			LOGGER.log(Level.SEVERE, query + " raised an exception", ex);
			throw ex;
		}

		return pokemonMaxId;
	}

	// Crea un nuevo pokemon teniendo la especie y el id (hay que sumarle +1 a la
	// salida del metodo anterior)
	public List<Record> nuevoPokemon(final String Especie, final int pokeID) {
		List<Record> newPokemon = new ArrayList<>();
		var query = new Query(
				"""
						CREATE (p:Pokemon {pokeEspecie:$Especie, pokeID:$pokeID, mote:$Especie,nivel:toInteger(rand() * 100) + 1})
						RETURN p
						""",
				Map.of("Especie", Especie, "pokeID", pokeID));

		try (var session = driver.session(SessionConfig.forDatabase("neo4j"))) {
			Result result = session.run(query);
			while (result.hasNext()) {
				newPokemon.add(result.next());

			}
			// You should capture any errors along with the query and data for traceability
		} catch (Neo4jException ex) {
			LOGGER.log(Level.SEVERE, query + " raised an exception", ex);
			throw ex;
		}

		return newPokemon;
	}

	// Asignar pokemon con especie y entrenador
	public void enlacePokemonEntrenadorEspecie(final int pokeID, final String nombreEntrenador) {
		var query = new Query("""
				MATCH (p:Pokemon), (pp:PokemonPokedex), (t:Trainer)
				WHERE p.pokeID = $pokeID AND t.Nombre = $nombreEntrenador AND pp.Name = p.pokeEspecie
				CREATE (p)-[:PERTENECE_A]->(pp), (t)-[:ES_ENTRENADOR_DE]->(p)
				""", Map.of("pokeID", pokeID, "nombreEntrenador", nombreEntrenador));

		try (var session = driver.session(SessionConfig.forDatabase("neo4j"))) {
			Result result = session.run(query);
			/*
			 * while (result.hasNext()) { //newPokemon.add(result.next());
			 * 
			 * }
			 */
			// You should capture any errors along with the query and data for traceability
		} catch (Neo4jException ex) {
			LOGGER.log(Level.SEVERE, query + " raised an exception", ex);
			throw ex;
		}

	}

	// Asignar pokemon con ataques
	public void asignarAtaquesPokemon(final int pokeID, final int moveId1, final int moveId2, final int moveId3,
			final int moveId4) {
		var query = new Query(
				"""
						MATCH (p:Pokemon), (m1:Move), (m2:Move), (m3:Move), (m4:Move)
						WHERE p.pokeID = $pokeID AND m1.moveId = $moveId1 AND m2.moveId = $moveId2 AND m3.moveId = $moveId3 AND m4.moveId = $moveId4
						CREATE (p)-[:PUEDE_USAR]->(m1), (p)-[:PUEDE_USAR]->(m2), (p)-[:PUEDE_USAR]->(m3), (p)-[:PUEDE_USAR]->(m4)
						""",
				Map.of("pokeID", pokeID, "moveId1", moveId1, "moveId2", moveId2, "moveId3", moveId3, "moveId4",
						moveId4));

		try (var session = driver.session(SessionConfig.forDatabase("neo4j"))) {
			Result result = session.run(query);
			/*
			 * while (result.hasNext()) { //newPokemon.add(result.next());
			 * 
			 * }
			 */
			// You should capture any errors along with the query and data for traceability
		} catch (Neo4jException ex) {
			LOGGER.log(Level.SEVERE, query + " raised an exception", ex);
			throw ex;
		}

	}

	// Eliminar un pokemon y todos sus enlaces
	public void eliminarPokemon(final int pokeID) {
		var query = new Query("""
				match (t:Trainer)-[r]-(p:Pokemon)-[j]-(pp:PokemonPokedex),(p)-[j2]-(m:Move)
				where p.pokeID=$pokeID delete r,j,j2,p
				""", Map.of("pokeID", pokeID));

		try (var session = driver.session(SessionConfig.forDatabase("neo4j"))) {
			Result result = session.run(query);

			// You should capture any errors along with the query and data for traceability
		} catch (Neo4jException ex) {
			LOGGER.log(Level.SEVERE, query + " raised an exception", ex);
			throw ex;
		}

	}

}
