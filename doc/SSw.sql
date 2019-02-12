CREATE TABLE "club" (
	"id" serial NOT NULL,
	"name" character varying NOT NULL,
	"based" TIMESTAMP,
	"stadium_id" int NOT NULL,
	"created" TIMESTAMP NOT NULL,
	"updated" TIMESTAMP NOT NULL,
	CONSTRAINT club_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "country" (
	"id" serial NOT NULL,
	"name" character varying NOT NULL,
	"region_id" int NOT NULL,
	"created" TIMESTAMP NOT NULL,
	"updated" TIMESTAMP NOT NULL,
	CONSTRAINT country_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "city" (
	"id" serial NOT NULL,
	"name" character varying NOT NULL,
	"country_id" int NOT NULL,
	"created" TIMESTAMP NOT NULL,
	"updated" TIMESTAMP NOT NULL,
	CONSTRAINT city_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "stadium" (
	"id" serial NOT NULL,
	"name" character varying NOT NULL,
	"capacity" int NOT NULL,
	"address" character varying NOT NULL,
	"based" TIMESTAMP,
	"city_id" int NOT NULL,
	"created" TIMESTAMP,
	"updated" TIMESTAMP NOT NULL,
	CONSTRAINT stadium_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "user_account" (
	"id" serial NOT NULL,
	"name" character varying NOT NULL,
	"email" character varying NOT NULL,
	"password" character varying NOT NULL,
	"country_id" int NOT NULL,
	"fun_organisation_id" int NOT NULL,
	"phone" character varying NOT NULL,
	"birthday" TIMESTAMP NOT NULL,
	"type" character varying NOT NULL,
	"created" TIMESTAMP NOT NULL,
	"updated" TIMESTAMP NOT NULL,
	CONSTRAINT user_account_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "ticket" (
	"id" serial NOT NULL,
	"name" character varying NOT NULL,
	"sector" character varying NOT NULL,
	"row" character varying NOT NULL,
	"seat" character varying NOT NULL,
	"price" DECIMAL(12,2) NOT NULL,
	"presence" BOOLEAN NOT NULL,
	"event_id" int NOT NULL,
	"created" TIMESTAMP NOT NULL,
	"updated" TIMESTAMP NOT NULL,
	CONSTRAINT ticket_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "partner_contract" (
	"id" serial NOT NULL,
	"club_id" int NOT NULL,
	"partner_id" int NOT NULL,
	"date_signing" TIMESTAMP NOT NULL,
	"contract_term" TIMESTAMP NOT NULL,
	"contract_value" DECIMAL(12,2) NOT NULL,
	"created" TIMESTAMP NOT NULL,
	"updated" TIMESTAMP NOT NULL,
	CONSTRAINT partner_contract_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "fun_organisation" (
	"id" serial NOT NULL,
	"name" character varying NOT NULL,
	"club_id" int NOT NULL,
	"city_id" int NOT NULL,
	"deposit" DECIMAL(12,2) NOT NULL,
	"created" TIMESTAMP NOT NULL,
	"updated" TIMESTAMP NOT NULL,
	CONSTRAINT fun_organisation_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "partner" (
	"id" serial NOT NULL,
	"name" character varying NOT NULL,
	"created" TIMESTAMP NOT NULL,
	"updated" TIMESTAMP NOT NULL,
	CONSTRAINT partner_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "region" (
	"id" serial NOT NULL,
	"name" character varying NOT NULL,
	"created" TIMESTAMP NOT NULL,
	"updated" TIMESTAMP NOT NULL,
	CONSTRAINT region_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "season_ticket" (
	"id" serial NOT NULL,
	"name" character varying NOT NULL,
	"sector" character varying NOT NULL,
	"row" character varying NOT NULL,
	"seat" character varying NOT NULL,
	"price" DECIMAL(12,2) NOT NULL,
	"date" TIMESTAMP NOT NULL,
	"presence" BOOLEAN NOT NULL,
	"created" TIMESTAMP NOT NULL,
	"updated" TIMESTAMP NOT NULL,
	CONSTRAINT season_ticket_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "event" (
	"id" serial NOT NULL,
	"name" character varying NOT NULL,
	"date" TIMESTAMP NOT NULL,
	"time" TIMESTAMP NOT NULL,
	"stadium_id" int NOT NULL,
	"created" TIMESTAMP NOT NULL,
	"updated" TIMESTAMP NOT NULL,
	CONSTRAINT event_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "club_2_owner" (
	"user_account_id" int NOT NULL,
	"club_id" int NOT NULL
) WITH (
  OIDS=FALSE
);



CREATE TABLE "player_detail" (
	"id" int NOT NULL,
	"type" character varying NOT NULL,
	"created" TIMESTAMP NOT NULL,
	"updated" TIMESTAMP NOT NULL,
	CONSTRAINT player_detail_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "event_2_season_ticket" (
	"event_id" int NOT NULL,
	"season_ticket_id" int NOT NULL
) WITH (
  OIDS=FALSE
);



ALTER TABLE "club" ADD CONSTRAINT "club_fk0" FOREIGN KEY ("stadium_id") REFERENCES "stadium"("id");

ALTER TABLE "country" ADD CONSTRAINT "country_fk0" FOREIGN KEY ("region_id") REFERENCES "region"("id");

ALTER TABLE "city" ADD CONSTRAINT "city_fk0" FOREIGN KEY ("country_id") REFERENCES "country"("id");

ALTER TABLE "stadium" ADD CONSTRAINT "stadium_fk0" FOREIGN KEY ("city_id") REFERENCES "city"("id");

ALTER TABLE "user_account" ADD CONSTRAINT "user_account_fk0" FOREIGN KEY ("country_id") REFERENCES "country"("id");
ALTER TABLE "user_account" ADD CONSTRAINT "user_account_fk1" FOREIGN KEY ("fun_organisation_id") REFERENCES "fun_organisation"("id");

ALTER TABLE "ticket" ADD CONSTRAINT "ticket_fk0" FOREIGN KEY ("event_id") REFERENCES "event"("id");

ALTER TABLE "partner_contract" ADD CONSTRAINT "partner_contract_fk0" FOREIGN KEY ("club_id") REFERENCES "club"("id");
ALTER TABLE "partner_contract" ADD CONSTRAINT "partner_contract_fk1" FOREIGN KEY ("partner_id") REFERENCES "partner"("id");

ALTER TABLE "fun_organisation" ADD CONSTRAINT "fun_organisation_fk0" FOREIGN KEY ("club_id") REFERENCES "club"("id");
ALTER TABLE "fun_organisation" ADD CONSTRAINT "fun_organisation_fk1" FOREIGN KEY ("city_id") REFERENCES "city"("id");




ALTER TABLE "event" ADD CONSTRAINT "event_fk0" FOREIGN KEY ("stadium_id") REFERENCES "stadium"("id");

ALTER TABLE "club_2_owner" ADD CONSTRAINT "club_2_owner_fk0" FOREIGN KEY ("user_account_id") REFERENCES "user_account"("id");
ALTER TABLE "club_2_owner" ADD CONSTRAINT "club_2_owner_fk1" FOREIGN KEY ("club_id") REFERENCES "club"("id");

ALTER TABLE "player_detail" ADD CONSTRAINT "player_detail_fk0" FOREIGN KEY ("id") REFERENCES "user_account"("id");

ALTER TABLE "event_2_season_ticket" ADD CONSTRAINT "event_2_season_ticket_fk0" FOREIGN KEY ("event_id") REFERENCES "event"("id");
ALTER TABLE "event_2_season_ticket" ADD CONSTRAINT "event_2_season_ticket_fk1" FOREIGN KEY ("season_ticket_id") REFERENCES "season_ticket"("id");
