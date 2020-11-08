create table event
(
	event_id bigint serial UNIQUE,
	name varchar(255) not null,
	start_date_time timestamp not null,
	duration smallint not null,

	frequency_type varchar(25),
	end_recurrence_date_time timestamp,
	number_of_occurrences integer,

	occurs_on_sunday 	boolean default false not null,
	occurs_on_monday 	boolean default false not null,
	occurs_on_tuesday 	boolean default false not null,
	occurs_on_wednesday boolean default false not null,
	occurs_on_thursday 	boolean default false not null,
	occurs_on_friday 	boolean default false not null,
	occurs_on_saturday 	boolean default false not null,

	parent_event_id bigint
		constraint parent_event_id_fk
			references event
);

alter table event owner to "simplified-calendar";


