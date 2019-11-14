CREATE TABLE "band" (
                        "id" bigint NOT NULL,
                        "bio" "character varying(255)",
                        "formed_date" date NOT NULL,
                        "address" "character varying(255)",
                        "extra_description" "character varying(255)",
                        "x" double,
                        "y" double,
                        "max_distance" double NOT NULL,
                        "name" "character varying(255)" NOT NULL,
                        "picture_url" "character varying(10000)",
                        "leader_id" bigint
);

CREATE TABLE "band_back_up_members" (
                                        "band_id" bigint NOT NULL,
                                        "back_up_members_id" bigint NOT NULL
);

CREATE TABLE "band_invited" (
                                "band_id" bigint NOT NULL,
                                "invited_id" bigint NOT NULL
);

CREATE TABLE "band_invited_back_up_members" (
                                                "band_id" bigint NOT NULL,
                                                "invited_back_up_members_id" bigint NOT NULL
);

CREATE TABLE "band_occasions" (
                                  "occasions_id" bigint NOT NULL,
                                  "band_id" bigint NOT NULL
);

CREATE TABLE "comment" (
                           "id" bigint NOT NULL,
                           "content" "character varying(255)" NOT NULL,
                           "posted_on" timestamp NOT NULL,
                           "fk_post" bigint,
                           "author_id" bigint NOT NULL
);

CREATE TABLE "conversation" (
                                "id" bigint NOT NULL,
                                "picture_url" "character varying(255)",
                                "title" "character varying(255)"
);

CREATE TABLE "conversation_band" (
                                     "fk_band" bigint,
                                     "fk_conversation" bigint NOT NULL
);

CREATE TABLE "conversation_user" (
                                     "fk_conversation" bigint NOT NULL,
                                     "fk_user" bigint NOT NULL
);

CREATE TABLE "gig" (
                       "id" bigint NOT NULL,
                       "date_time" timestamp NOT NULL,
                       "description" "character varying(255)",
                       "expected_duration" "character varying(255)",
                       "final_deal_achieved" boolean NOT NULL,
                       "gig_type" integer,
                       "address" "character varying(255)",
                       "extra_description" "character varying(255)",
                       "x" double,
                       "y" double,
                       "private_gig" boolean NOT NULL,
                       "proposed_price" integer,
                       "organizer_id" bigint NOT NULL
);

CREATE TABLE "gig_type" (
                            "gig" bigint NOT NULL,
                            "gig_type" "character varying(255)" NOT NULL
);

CREATE TABLE "instrument" (
                              "id" bigint NOT NULL,
                              "name" "character varying(255)",
                              "type" integer
);

CREATE TABLE "instruments" (
                               "instruments_id" bigint NOT NULL,
                               "musician" bigint NOT NULL
);

CREATE TABLE "message" (
                           "id" bigint NOT NULL,
                           "content" "character varying(255)" NOT NULL,
                           "sent_time" timestamp NOT NULL,
                           "fk_sender" bigint NOT NULL,
                           "fk_sender_band" bigint NOT NULL,
                           "fk_conversation" bigint
);

CREATE TABLE "musician" (
                            "bio" "character varying(255)",
                            "public_calendar" boolean NOT NULL,
                            "id" bigint NOT NULL
);

CREATE TABLE "musician_bands" (
                                  "fk_musician" bigint NOT NULL,
                                  "fk_band" bigint NOT NULL
);

CREATE TABLE "musician_gig_history" (
                                        "fk_musician" bigint NOT NULL,
                                        "fk_gig" bigint NOT NULL
);

CREATE TABLE "musician_occasions" (
                                      "musician_id" bigint NOT NULL,
                                      "occasions_id" bigint NOT NULL
);

CREATE TABLE "occasion" (
                            "id" bigint NOT NULL,
                            "description" "character varying(255)",
                            "local_date" date,
                            "personal_occasion" boolean NOT NULL
);

CREATE TABLE "organizer" (
                             "id" bigint NOT NULL,
                             "manager_name" "character varying(255)"
);

CREATE TABLE "people" (
                          "id" bigint NOT NULL,
                          "phone_number" "character varying(255)",
                          "picture_url" "character varying(10000)",
                          "username" "character varying(255)"
);

CREATE TABLE "post" (
                        "id" bigint NOT NULL,
                        "content" "character varying(255)" NOT NULL,
                        "published_on" timestamp NOT NULL,
                        "fk_band" bigint,
                        "fk_user" bigint
);

CREATE TABLE "review" (
                          "id" bigint NOT NULL,
                          "content_of_review_for_band" "character varying(255)",
                          "content_of_review_for_organizer" "character varying(255)",
                          "created" timestamp,
                          "grade_band" integer,
                          "grade_organizer" integer,
                          "author_id" bigint
);

CREATE TABLE "review_gig" (
                              "fk_gig" bigint NOT NULL,
                              "fk_review" bigint NOT NULL
);

CREATE TABLE "system_person" (
                                 "id" bigint NOT NULL,
                                 "email" "character varying(255)" NOT NULL,
                                 "locked" boolean,
                                 "password_hash" "character varying(255)" NOT NULL,
                                 "verified" boolean
);

CREATE TABLE "system_person_roles" (
                                       "system_person_id" bigint NOT NULL,
                                       "roles" integer
);

ALTER TABLE "conversation_user" ADD FOREIGN KEY ("fk_user") REFERENCES "people" ("id");

ALTER TABLE "musician_bands" ADD FOREIGN KEY ("fk_band") REFERENCES "musician" ("id");

ALTER TABLE "band_back_up_members" ADD FOREIGN KEY ("back_up_members_id") REFERENCES "musician" ("id");

ALTER TABLE "instruments" ADD FOREIGN KEY ("instruments_id") REFERENCES "instrument" ("id");

ALTER TABLE "post" ADD FOREIGN KEY ("fk_band") REFERENCES "band" ("id");

ALTER TABLE "band_back_up_members" ADD FOREIGN KEY ("band_id") REFERENCES "band" ("id");

ALTER TABLE "band_invited_back_up_members" ADD FOREIGN KEY ("invited_back_up_members_id") REFERENCES "musician" ("id");

ALTER TABLE "comment" ADD FOREIGN KEY ("fk_post") REFERENCES "post" ("id");

ALTER TABLE "post" ADD FOREIGN KEY ("fk_user") REFERENCES "musician" ("id");

ALTER TABLE "review_gig" ADD FOREIGN KEY ("fk_gig") REFERENCES "gig" ("id");

ALTER TABLE "band_invited" ADD FOREIGN KEY ("invited_id") REFERENCES "musician" ("id");

ALTER TABLE "musician_gig_history" ADD FOREIGN KEY ("fk_musician") REFERENCES "musician" ("id");

ALTER TABLE "musician_occasions" ADD FOREIGN KEY ("occasions_id") REFERENCES "occasion" ("id");

ALTER TABLE "musician_bands" ADD FOREIGN KEY ("fk_musician") REFERENCES "band" ("id");

ALTER TABLE "comment" ADD FOREIGN KEY ("author_id") REFERENCES "people" ("id");

ALTER TABLE "review" ADD FOREIGN KEY ("author_id") REFERENCES "people" ("id");

ALTER TABLE "message" ADD FOREIGN KEY ("fk_sender_band") REFERENCES "band" ("id");

ALTER TABLE "conversation_band" ADD FOREIGN KEY ("fk_conversation") REFERENCES "conversation" ("id");

ALTER TABLE "band_invited_back_up_members" ADD FOREIGN KEY ("band_id") REFERENCES "band" ("id");

ALTER TABLE "band_occasions" ADD FOREIGN KEY ("occasions_id") REFERENCES "occasion" ("id");

ALTER TABLE "message" ADD FOREIGN KEY ("fk_sender") REFERENCES "people" ("id");

ALTER TABLE "message" ADD FOREIGN KEY ("fk_conversation") REFERENCES "conversation" ("id");

ALTER TABLE "musician_occasions" ADD FOREIGN KEY ("musician_id") REFERENCES "musician" ("id");

ALTER TABLE "band" ADD FOREIGN KEY ("leader_id") REFERENCES "musician" ("id");

ALTER TABLE "review_gig" ADD FOREIGN KEY ("fk_review") REFERENCES "review" ("id");

ALTER TABLE "band_invited" ADD FOREIGN KEY ("band_id") REFERENCES "band" ("id");

ALTER TABLE "system_person_roles" ADD FOREIGN KEY ("system_person_id") REFERENCES "system_person" ("id");

ALTER TABLE "conversation_user" ADD FOREIGN KEY ("fk_conversation") REFERENCES "conversation" ("id");

ALTER TABLE "gig_type" ADD FOREIGN KEY ("gig") REFERENCES "band" ("id");

ALTER TABLE "band_occasions" ADD FOREIGN KEY ("band_id") REFERENCES "band" ("id");

ALTER TABLE "conversation_band" ADD FOREIGN KEY ("fk_band") REFERENCES "band" ("id");

ALTER TABLE "musician_gig_history" ADD FOREIGN KEY ("fk_gig") REFERENCES "gig" ("id");

ALTER TABLE "instruments" ADD FOREIGN KEY ("musician") REFERENCES "musician" ("id");

ALTER TABLE "gig" ADD FOREIGN KEY ("organizer_id") REFERENCES "organizer" ("id");

ALTER TABLE "people" ADD FOREIGN KEY ("id") REFERENCES "musician" ("id");

ALTER TABLE "people" ADD FOREIGN KEY ("id") REFERENCES "organizer" ("id");

ALTER TABLE "people" ADD FOREIGN KEY ("id") REFERENCES "system_person" ("id");
