package Pokemon;

import org.neo4j.driver.Config;

import DB.Dynamo;
import DB.Neo;

public interface Constantes {
	public static final Neo DBNeo = new Neo("neo4j+s://0c0cab02.databases.neo4j.io", "neo4j", "Instance01",
			Config.defaultConfig());
	public static final Dynamo DBDynamo = new Dynamo("ASIA435WYFFXQRYUO56E", "8R1EwKv8dE8FqbJtkSoE7rImhlGxfZxw5WktpurC",
			"FwoGZXIvYXdzEDIaDJ+y4wBRXq0c0QDT0SLKAQfdv8GsRLoHQ28iUdkmP/PNsH4Ru6ob5PLArYOQ0vmKCr6Zef6Nl3sjMN2knHPYEb66xGs8kBeJBX9h/1PlQGwlzemCqpGe2YlF0R9NlpS/EAhgnImLO8tDM69GoS9aQ7UtXdwsX64RpqXWWrzo3e5YWvhFSeOCiwZUamXezFYlvGBBZhJMMfpJm5Yd082jZ4NbaRP1wF6vdsht+dFxhlgE0+LBJ7FFwwc1xPjYBRngslRHnj4BZaWqH6IX/4D1jzhqrwyQj8fRiWkouvuTowYyLfw93dy/KdczqmJEQdKJmvB2sBHf4F6dDHKuTbdXJATkX83srGvSYH2TNOMA0Q==");
}
