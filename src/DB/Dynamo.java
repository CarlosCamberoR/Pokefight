package DB;

import java.util.List;
import java.util.Map;

import javax.swing.DefaultListModel;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicSessionCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.PutItemResult;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;

import Objeto.Log;
import Objeto.Pokemon;
import Objeto.Tipo;

public class Dynamo {
	private AWSCredentials credentials;
	private AmazonDynamoDB client;
	private DynamoDB dynamoDB;

	public Dynamo(String accessKey, String secretKey, String sessionToken) {

		credentials = new BasicSessionCredentials(accessKey, secretKey, sessionToken);

		client = AmazonDynamoDBClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(credentials))
				.withRegion("us-east-1").build();

		dynamoDB = new DynamoDB(client);
		try {
			client.listTables();
			System.out.println("Conexión a Dynamo exitosa");

		} catch (Exception e) {
			System.out.println("Conexión a Dynamo fallida");
		}

	}

	/*
	 * Método que permite obtener los nombres de todos los pokemons
	 */
	public DefaultListModel<String> obtenerPokemonsTienda() {

		DefaultListModel<String> pokemons = new DefaultListModel<String>();

		ScanRequest scanRequest = new ScanRequest().withTableName("tienda");

		ScanResult result = client.scan(scanRequest);
		for (Map<String, AttributeValue> item : result.getItems()) {
			pokemons.addElement(item.get("Pokemon").getS());
		}
		while (result.getLastEvaluatedKey() != null) {
			scanRequest.setExclusiveStartKey(result.getLastEvaluatedKey());
			result = client.scan(scanRequest);
			for (Map<String, AttributeValue> item : result.getItems()) {
				pokemons.addElement(item.get("Pokemon").getS());
			}
		}

		return pokemons;

	}

	/*
	 * Método que permite obtener los datos de una especie dada.
	 */
	public Pokemon getElementByName(String especie) {
		Table table = dynamoDB.getTable("tienda");
		Tipo t1 = new Tipo();
		Tipo t2 = new Tipo();

		QuerySpec spec = new QuerySpec().withKeyConditionExpression("Pokemon = :n")
				.withValueMap(new ValueMap().withString(":n", especie));
		Item item = table.query(spec).iterator().next();

		String tipo1 = item.getString("Type1");
		if (tipo1 != null && !tipo1.equals("")) {
			t1 = new Tipo(tipo1);
		}
		String tipo2 = item.getString("Type2");
		if (tipo2 != null && !tipo2.equals("")) {
			t2 = new Tipo(tipo2);
		}

		List<Tipo> tipos = List.of(t1, t2);

		Pokemon pokemon = new Pokemon(item.getString("Pokemon"), tipos, item.getString("Gif"));
		return pokemon;
	}

	public void insertarLogTienda(Log item) {
		PutItemResult respuesta = client.putItem(item.getItem());
		System.out.println(respuesta.getSdkHttpMetadata().getHttpStatusCode());
	}

}
