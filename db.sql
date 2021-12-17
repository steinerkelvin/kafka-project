CREATE TABLE public.t_vaccine (
	id SERIAL,
	name varchar NULL,
	min_temp double precision NULL,
	max_temp double precision NULL,
	CONSTRAINT t_vaccine_pk PRIMARY KEY (id)
);
