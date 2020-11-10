package girardi.reis.anderson.simplifiedcalendarapi.infrastructure.configuration;

import girardi.reis.anderson.simplifiedcalendarapi.infrastructure.repository.EventRepository;
import girardi.reis.anderson.simplifiedcalendarapi.infrastructure.repository.EventRepositoryCustomImpl;
import io.r2dbc.postgresql.PostgresqlConnectionConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.r2dbc.core.DatabaseClient;

import static java.util.Arrays.asList;

@Configuration
@EnableR2dbcRepositories(basePackages = "girardi.reis.anderson.simplifiedcalendarapi.infrastructure.repository")
public class R2dbcPostgresqlConfiguration extends AbstractR2dbcConfiguration {

  @Value("${datasource.host}")
  private String host;
  @Value("${datasource.port}")
  private int port;
  @Value("${datasource.database}")
  private String database;
  @Value("${datasource.username}")
  private String username;
  @Value("${datasource.password}")
  private String password;

  @Bean
  @Override
  public PostgresqlConnectionFactory connectionFactory() {
    return new PostgresqlConnectionFactory(PostgresqlConnectionConfiguration
        .builder()
        .host(host)
        .database(database)
        .username(username)
        .password(password)
        .port(port)
        .build());
  }

  @Bean
  public CommandLineRunner commandLineRunner(EventRepository eventRepository) {
    return args -> {
      DatabaseClient databaseClient = DatabaseClient.create(connectionFactory());
      asList( "DROP TABLE IF EXISTS event CASCADE;",
              "CREATE TABLE event "                                                                       +
              "( "                                                                                        +
                      "	id                        bigserial not null constraint event_pk primary key, "   +
                      "	name                      varchar(255) not null, "                                +
                      "	start_date_time           timestamp not null, "                                   +
                      "	duration smallint         not null, "                                             +
                      "	frequency_type            varchar(25), "                                          +
                      "	end_recurrence_date       date, "                                                 +
                      "	number_of_occurrences     integer, "                                              +
                      "	occurs_on_sunday 	      boolean default false not null, "                       +
                      "	occurs_on_monday 	      boolean default false not null, "                       +
                      "	occurs_on_tuesday 	      boolean default false not null, "                       +
                      "	occurs_on_wednesday       boolean default false not null, "                       +
                      "	occurs_on_thursday 	      boolean default false not null, "                       +
                      "	occurs_on_friday 	      boolean default false not null, "                       +
                      "	occurs_on_saturday 	      boolean default false not null, "                       +
                      "	parent_event_id           bigint constraint parent_event_id_fk references event " +
              ");",
              "ALTER TABLE event owner to \"simplified-calendar\";")
          .forEach(s ->
              databaseClient
                  .sql(s)
                  .fetch()
                  .rowsUpdated()
                  .block()
          );
    };
  }
}
