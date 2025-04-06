package Objeto;

import java.util.HashMap;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.PutItemRequest;

/**
 * 
 * Clase que permite crear elementos para que se puedan introducir en la tabla
 * "log" de DynamoDB
 *
 */
public class Log {
	private PutItemRequest item;

	public Log(String usuarioLog, String tipoLog, String especiePokemonBusqueda, String tipo1PokemonBusqueda,
			String tipo2PokemonBusqueda, String especiePokemonSeleccionado, String tipo1PokemonSeleccionado,
			String tipo2PokemonSeleccionado) {
		AttributeValue usuarioLogAttr = new AttributeValue();
		usuarioLogAttr.setS(usuarioLog);
		AttributeValue tipoLogAttr = new AttributeValue();
		tipoLogAttr.setN(tipoLog);
		AttributeValue especiePokemonBusquedaAttr = new AttributeValue();
		especiePokemonBusquedaAttr.setS(especiePokemonBusqueda);
		AttributeValue tipo1PokemonBusquedaAttr = new AttributeValue();
		tipo1PokemonBusquedaAttr.setS(tipo1PokemonBusqueda);
		AttributeValue tipo2PokemonBusquedaAttr = new AttributeValue();
		tipo2PokemonBusquedaAttr.setS(tipo2PokemonBusqueda);
		AttributeValue especiePokemonSeleccionadoAttr = new AttributeValue();
		especiePokemonSeleccionadoAttr.setS(especiePokemonSeleccionado);
		AttributeValue tipo1PokemonSeleccionadoAttr = new AttributeValue();
		tipo1PokemonSeleccionadoAttr.setS(tipo1PokemonSeleccionado);
		AttributeValue tipo2PokemonSeleccionadoAttr = new AttributeValue();
		tipo2PokemonSeleccionadoAttr.setS(tipo2PokemonSeleccionado);

		Map<String, AttributeValue> registro = Map.of("usuario_log", usuarioLogAttr, "codigo_log", tipoLogAttr,
				"pokemon_busqueda", especiePokemonBusquedaAttr, "tipo1_busqueda", tipo1PokemonBusquedaAttr,
				"tipo2_busqueda", tipo2PokemonBusquedaAttr, "pokemon_seleccion", especiePokemonSeleccionadoAttr,
				"tipo1_seleccion", tipo1PokemonSeleccionadoAttr, "tipo2_seleccion", tipo2PokemonSeleccionadoAttr);
		item = new PutItemRequest("log", registro);
	}

	public Log(String usuarioLog, String tipoLog, String especiePokemonSeleccionado, String tipo1PokemonSeleccionado,
			String tipo2PokemonSeleccionado) {
		AttributeValue usuarioLogAttr = new AttributeValue();
		usuarioLogAttr.setS(usuarioLog);
		AttributeValue tipoLogAttr = new AttributeValue();
		tipoLogAttr.setN(tipoLog);
		AttributeValue especiePokemonSeleccionadoAttr = new AttributeValue();
		especiePokemonSeleccionadoAttr.setS(especiePokemonSeleccionado);
		AttributeValue tipo1PokemonSeleccionadoAttr = new AttributeValue();
		tipo1PokemonSeleccionadoAttr.setS(tipo1PokemonSeleccionado);
		AttributeValue tipo2PokemonSeleccionadoAttr = new AttributeValue();
		tipo2PokemonSeleccionadoAttr.setS(tipo2PokemonSeleccionado);

		Map<String, AttributeValue> registro = Map.of("usuario_log", usuarioLogAttr, "codigo_log", tipoLogAttr,
				"pokemon_seleccion", especiePokemonSeleccionadoAttr, "tipo1_seleccion", tipo1PokemonSeleccionadoAttr,
				"tipo2_seleccion", tipo2PokemonSeleccionadoAttr);
		item = new PutItemRequest("log", registro);
	}

	public Log(String usuarioLog, String tipoLog, String especiePokemonPersonal, String especiePokemonRival,
			String ataque1, String tipoAtaque1, String ataque2, String tipoAtaque2, String ataque3, String tipoAtaque3,
			String ataque4, String tipoAtaque4) {
		AttributeValue usuarioLogAttr = new AttributeValue();
		usuarioLogAttr.setS(usuarioLog);
		AttributeValue tipoLogAttr = new AttributeValue();
		tipoLogAttr.setN(tipoLog);
		AttributeValue especiePokemonPersonalAttr = new AttributeValue();
		especiePokemonPersonalAttr.setS(especiePokemonPersonal);
		AttributeValue especiePokemonRivalAttr = new AttributeValue();
		especiePokemonRivalAttr.setS(especiePokemonRival);

		AttributeValue ataque1Attr = new AttributeValue();
		ataque1Attr.setS(ataque1);
		AttributeValue tipoAtaque1Attr = new AttributeValue();
		tipoAtaque1Attr.setS(tipoAtaque1);

		AttributeValue ataque2Attr = new AttributeValue();
		ataque2Attr.setS(ataque2);
		AttributeValue tipoAtaque2Attr = new AttributeValue();
		tipoAtaque2Attr.setS(tipoAtaque2);

		AttributeValue ataque3Attr = new AttributeValue();
		ataque3Attr.setS(ataque3);
		AttributeValue tipoAtaque3Attr = new AttributeValue();
		tipoAtaque3Attr.setS(tipoAtaque3);

		AttributeValue ataque4Attr = new AttributeValue();
		ataque4Attr.setS(ataque4);
		AttributeValue tipoAtaque4Attr = new AttributeValue();
		tipoAtaque4Attr.setS(tipoAtaque4);

		Map<String, AttributeValue> registro = new HashMap<String, AttributeValue>();
		registro.put("usuario_log", usuarioLogAttr);
		registro.put("codigo_log", tipoLogAttr);
		registro.put("pokemon_personal", especiePokemonPersonalAttr);
		registro.put("pokemon_rival", especiePokemonRivalAttr);
		registro.put("ataque1", ataque1Attr);
		registro.put("tipo_ataque1", tipoAtaque1Attr);
		registro.put("ataque2", ataque2Attr);
		registro.put("tipo_ataque2", tipoAtaque2Attr);
		registro.put("ataque3", ataque3Attr);
		registro.put("tipo_ataque3", tipoAtaque3Attr);
		registro.put("ataque4", ataque4Attr);
		registro.put("tipo_ataque4", tipoAtaque4Attr);

		item = new PutItemRequest("log", registro);
	}

	public Log(String usuarioLog, String tipoLog, String ayuda) {
		AttributeValue usuarioLogAttr = new AttributeValue();
		usuarioLogAttr.setS(usuarioLog);
		AttributeValue tipoLogAttr = new AttributeValue();
		tipoLogAttr.setN(tipoLog);
		AttributeValue ayudaAttr = new AttributeValue();
		ayudaAttr.setS(ayuda);

		Map<String, AttributeValue> registro = Map.of("usuario_log", usuarioLogAttr, "codigo_log", tipoLogAttr, "ayuda",
				ayudaAttr);
		item = new PutItemRequest("log", registro);
	}

	public Log(String usuarioLog, String tipoLog, String ayuda, Integer cantidadAyuda) {
		AttributeValue usuarioLogAttr = new AttributeValue();
		usuarioLogAttr.setS(usuarioLog);
		AttributeValue tipoLogAttr = new AttributeValue();
		tipoLogAttr.setN(tipoLog);
		AttributeValue ayudaAttr = new AttributeValue();
		ayudaAttr.setS(ayuda);
		AttributeValue cantidadAyudaAttr = new AttributeValue();
		cantidadAyudaAttr.setN(cantidadAyuda.toString());

		Map<String, AttributeValue> registro = Map.of("usuario_log", usuarioLogAttr, "codigo_log", tipoLogAttr, "ayuda",
				ayudaAttr, "cantidad_ayuda", cantidadAyudaAttr);
		item = new PutItemRequest("log", registro);

	}

	public PutItemRequest getItem() {
		return item;
	}

	public void setItem(PutItemRequest item) {
		this.item = item;
	}

}
