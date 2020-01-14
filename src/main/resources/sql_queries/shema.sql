CREATE TABLE public.band (
                             id bigint NOT NULL,
                             bio character varying(255),
                             formed_date timestamp without time zone NOT NULL,
                             address character varying(255),
                             extra_description character varying(255),
                             x double precision,
                             y double precision,
                             max_distance double precision NOT NULL,
                             name character varying(255) NOT NULL,
                             picture_url character varying(10000),
                             leader_id bigint
);


CREATE TABLE public.band_acceptable_gig_types (
                                                  band_id bigint NOT NULL,
                                                  acceptable_gig_types character varying(255)
);


CREATE TABLE public.band_back_up_members (
                                             band_id bigint NOT NULL,
                                             back_up_members_id bigint NOT NULL
);


CREATE TABLE public.band_gigs (
                                  band_id bigint NOT NULL,
                                  gigs_id bigint NOT NULL
);


CREATE SEQUENCE public.band_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.band_id_seq OWNED BY public.band.id;

CREATE TABLE public.band_invitation_gigs (
                                             band_id bigint NOT NULL,
                                             invitation_gigs_id bigint NOT NULL
);


CREATE TABLE public.band_invited (
                                     band_id bigint NOT NULL,
                                     invited_id bigint NOT NULL
);


CREATE TABLE public.band_invited_back_up_members (
                                                     band_id bigint NOT NULL,
                                                     invited_back_up_members_id bigint NOT NULL
);


CREATE TABLE public.band_members (
                                     band_id bigint NOT NULL,
                                     members_id bigint NOT NULL
);


CREATE TABLE public.band_occasions (
                                       band_id bigint NOT NULL,
                                       occasions_id bigint NOT NULL
);


CREATE TABLE public.band_posts (
                                   band_id bigint NOT NULL,
                                   posts_id bigint NOT NULL
);


CREATE TABLE public.comment (
                                id bigint NOT NULL,
                                content character varying(255) NOT NULL,
                                posted_on timestamp without time zone NOT NULL,
                                author_id bigint NOT NULL
);


CREATE SEQUENCE public.comment_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.comment_id_seq OWNED BY public.comment.id;

CREATE TABLE public.conversation (
                                     id bigint NOT NULL,
                                     picture_url character varying(255),
                                     title character varying(255),
                                     band_id bigint
);


CREATE SEQUENCE public.conversation_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.conversation_id_seq OWNED BY public.conversation.id;

CREATE TABLE public.conversation_messages (
                                              conversation_id bigint NOT NULL,
                                              messages_id bigint NOT NULL
);


CREATE TABLE public.conversation_participants (
                                                  conversation_id bigint NOT NULL,
                                                  participants_id bigint NOT NULL
);


CREATE TABLE public.gig (
                            id bigint NOT NULL,
                            date_time timestamp without time zone NOT NULL,
                            description character varying(255),
                            expected_duration character varying(255),
                            final_deal_achieved boolean NOT NULL,
                            gig_type integer,
                            address character varying(255),
                            extra_description character varying(255),
                            x double precision,
                            y double precision,
                            name character varying(255),
                            private_gig boolean NOT NULL,
                            proposed_price integer,
                            organizer_id bigint NOT NULL
);


CREATE SEQUENCE public.gig_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.gig_id_seq OWNED BY public.gig.id;

CREATE TABLE public.gig_reviews (
                                    gig_id bigint NOT NULL,
                                    reviews_id bigint NOT NULL
);


CREATE TABLE public.instrument (
                                   id bigint NOT NULL,
                                   name character varying(255),
                                   type integer
);


CREATE SEQUENCE public.instrument_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.instrument_id_seq OWNED BY public.instrument.id;

CREATE TABLE public.message (
                                id bigint NOT NULL,
                                content character varying(255) NOT NULL,
                                sent_time timestamp without time zone NOT NULL,
                                sender_id bigint,
                                sender_band_id bigint
);


CREATE SEQUENCE public.message_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.message_id_seq OWNED BY public.message.id;

CREATE TABLE public.musician (
                                 id bigint NOT NULL,
                                 bio character varying(255),
                                 public_calendar boolean
);


CREATE TABLE public.musician_instruments (
                                             musician_id bigint NOT NULL,
                                             instruments_id bigint NOT NULL
);


CREATE TABLE public.musician_occasions (
                                           musician_id bigint NOT NULL,
                                           occasions_id bigint NOT NULL
);


CREATE TABLE public.musician_past_gigs (
                                           musician_id bigint NOT NULL,
                                           past_gigs_id bigint NOT NULL
);


CREATE TABLE public.musician_posts (
                                       musician_id bigint NOT NULL,
                                       posts_id bigint NOT NULL
);


CREATE TABLE public.occasion (
                                 id bigint NOT NULL,
                                 description character varying(255),
                                 local_date_time timestamp without time zone,
                                 personal_occasion boolean
);


CREATE SEQUENCE public.occasion_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.occasion_id_seq OWNED BY public.occasion.id;

CREATE TABLE public.organizer (
                                  id bigint NOT NULL,
                                  manager_name character varying(255)
);


CREATE TABLE public.person (
                               id bigint NOT NULL,
                               phone_number character varying(255),
                               picture_url character varying(10000),
                               username character varying(255)
);


CREATE TABLE public.post (
                             id bigint NOT NULL,
                             content character varying(255) NOT NULL,
                             published_on timestamp without time zone NOT NULL
);


CREATE TABLE public.post_comments (
                                      post_id bigint NOT NULL,
                                      comments_id bigint NOT NULL
);


CREATE SEQUENCE public.post_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.post_id_seq OWNED BY public.post.id;

CREATE TABLE public.review (
                               id bigint NOT NULL,
                               content_of_review_for_band character varying(255),
                               content_of_review_for_organizer character varying(255),
                               created timestamp without time zone,
                               grade_band integer,
                               grade_organizer integer,
                               author_id bigint
);


CREATE SEQUENCE public.review_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.review_id_seq OWNED BY public.review.id;

CREATE TABLE public.system_person (
                                      id bigint NOT NULL,
                                      email character varying(255) NOT NULL,
                                      locked boolean,
                                      password_hash character varying(255) NOT NULL,
                                      verified boolean
);


CREATE SEQUENCE public.system_person_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.system_person_id_seq OWNED BY public.system_person.id;

CREATE TABLE public.system_person_roles (
                                            system_person_id bigint NOT NULL,
                                            roles integer
);


ALTER TABLE ONLY public.band ALTER COLUMN id SET DEFAULT nextval('public.band_id_seq'::regclass);

ALTER TABLE ONLY public.comment ALTER COLUMN id SET DEFAULT nextval('public.comment_id_seq'::regclass);

ALTER TABLE ONLY public.conversation ALTER COLUMN id SET DEFAULT nextval('public.conversation_id_seq'::regclass);

ALTER TABLE ONLY public.gig ALTER COLUMN id SET DEFAULT nextval('public.gig_id_seq'::regclass);

ALTER TABLE ONLY public.instrument ALTER COLUMN id SET DEFAULT nextval('public.instrument_id_seq'::regclass);

ALTER TABLE ONLY public.message ALTER COLUMN id SET DEFAULT nextval('public.message_id_seq'::regclass);

ALTER TABLE ONLY public.occasion ALTER COLUMN id SET DEFAULT nextval('public.occasion_id_seq'::regclass);

ALTER TABLE ONLY public.post ALTER COLUMN id SET DEFAULT nextval('public.post_id_seq'::regclass);

ALTER TABLE ONLY public.review ALTER COLUMN id SET DEFAULT nextval('public.review_id_seq'::regclass);

ALTER TABLE ONLY public.system_person ALTER COLUMN id SET DEFAULT nextval('public.system_person_id_seq'::regclass);

ALTER TABLE ONLY public.band
    ADD CONSTRAINT band_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.comment
    ADD CONSTRAINT comment_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.conversation
    ADD CONSTRAINT conversation_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.gig
    ADD CONSTRAINT gig_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.instrument
    ADD CONSTRAINT instrument_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.message
    ADD CONSTRAINT message_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.musician
    ADD CONSTRAINT musician_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.occasion
    ADD CONSTRAINT occasion_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.organizer
    ADD CONSTRAINT organizer_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.person
    ADD CONSTRAINT person_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.post
    ADD CONSTRAINT post_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.review
    ADD CONSTRAINT review_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.system_person
    ADD CONSTRAINT system_person_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.conversation_messages
    ADD CONSTRAINT uk_163cga9b82cbl0obssg58gkvm UNIQUE (messages_id);

ALTER TABLE ONLY public.system_person
    ADD CONSTRAINT uk_2xhnpmsmb9ghar5ova4h874fs UNIQUE (email);

ALTER TABLE ONLY public.band_posts
    ADD CONSTRAINT uk_5y3vx4t3toft2g8u7f7748bof UNIQUE (posts_id);

ALTER TABLE ONLY public.musician_posts
    ADD CONSTRAINT uk_8slgkv4eww96cd261jptu4vnm UNIQUE (posts_id);

ALTER TABLE ONLY public.organizer
    ADD CONSTRAINT uk_f1o7jsm70nknkeqq4c2dfsa1u UNIQUE (manager_name);

ALTER TABLE ONLY public.band
    ADD CONSTRAINT uk_glix9c4w5lo4ag0o37mpyi8qn UNIQUE (name);

ALTER TABLE ONLY public.post_comments
    ADD CONSTRAINT uk_gq9be62nx9c9hc0uyhakey771 UNIQUE (comments_id);

ALTER TABLE ONLY public.person
    ADD CONSTRAINT uk_n0i6d7rc1hqkjivk494g8j2qd UNIQUE (username);

ALTER TABLE ONLY public.band_occasions
    ADD CONSTRAINT uk_owko55dvda5qfv0uv7oj1xd57 UNIQUE (occasions_id);

ALTER TABLE ONLY public.band_members
    ADD CONSTRAINT fk16r4cxs4q8qf5ia7oe2elgsgw FOREIGN KEY (band_id) REFERENCES public.band(id);

ALTER TABLE ONLY public.gig_reviews
    ADD CONSTRAINT fk3cxbf2y0amve2n44mcmhvoem1 FOREIGN KEY (reviews_id) REFERENCES public.review(id);

ALTER TABLE ONLY public.band_invitation_gigs
    ADD CONSTRAINT fk3gyrlueo9r42d50y5q1n0oos2 FOREIGN KEY (band_id) REFERENCES public.band(id);

ALTER TABLE ONLY public.band_gigs
    ADD CONSTRAINT fk3vuo54n4b9wa1mi5in81q5f3d FOREIGN KEY (gigs_id) REFERENCES public.gig(id);

ALTER TABLE ONLY public.band_back_up_members
    ADD CONSTRAINT fk6dcxyw0tkb8ppaasr3g1xb74a FOREIGN KEY (back_up_members_id) REFERENCES public.musician(id);

ALTER TABLE ONLY public.message
    ADD CONSTRAINT fk6p9dy39ram5rqdl84nciff92j FOREIGN KEY (sender_id) REFERENCES public.person(id);

ALTER TABLE ONLY public.musician_past_gigs
    ADD CONSTRAINT fk74ldhne2nlqtoatnvepb8f7cy FOREIGN KEY (past_gigs_id) REFERENCES public.gig(id);

ALTER TABLE ONLY public.band_back_up_members
    ADD CONSTRAINT fk7e5n8gw8mni6bskck2b0xtvjw FOREIGN KEY (band_id) REFERENCES public.band(id);

ALTER TABLE ONLY public.band_invited_back_up_members
    ADD CONSTRAINT fk88ohnr65x1uc8qquxityql68c FOREIGN KEY (invited_back_up_members_id) REFERENCES public.musician(id);

ALTER TABLE ONLY public.band_posts
    ADD CONSTRAINT fk8i0bxhn0icvbdx3pjcnffhu5o FOREIGN KEY (posts_id) REFERENCES public.post(id);

ALTER TABLE ONLY public.review
    ADD CONSTRAINT fk9vlorwoewq1eo18a3a74v6hv5 FOREIGN KEY (author_id) REFERENCES public.person(id);

ALTER TABLE ONLY public.gig_reviews
    ADD CONSTRAINT fkahfp6wv9cxux4rkx35ft59usg FOREIGN KEY (gig_id) REFERENCES public.gig(id);

ALTER TABLE ONLY public.band_invited
    ADD CONSTRAINT fkdflpbkp40qfgbv8olljxw0kco FOREIGN KEY (invited_id) REFERENCES public.musician(id);

ALTER TABLE ONLY public.band_gigs
    ADD CONSTRAINT fke55r8o3nshoutfg41thur01yl FOREIGN KEY (band_id) REFERENCES public.band(id);

ALTER TABLE ONLY public.musician_occasions
    ADD CONSTRAINT fked7qrdgv2agaygt89bfd85fop FOREIGN KEY (occasions_id) REFERENCES public.occasion(id);

ALTER TABLE ONLY public.band_invitation_gigs
    ADD CONSTRAINT fkfnafj6vsax6rncdlgr1en257y FOREIGN KEY (invitation_gigs_id) REFERENCES public.gig(id);

ALTER TABLE ONLY public.conversation_participants
    ADD CONSTRAINT fkfoph5mdhqoqgity1mf6ys41t1 FOREIGN KEY (participants_id) REFERENCES public.person(id);

ALTER TABLE ONLY public.message
    ADD CONSTRAINT fkhkai4hpyd3sv391q8usifj3gt FOREIGN KEY (sender_band_id) REFERENCES public.band(id);

ALTER TABLE ONLY public.band_invited_back_up_members
    ADD CONSTRAINT fkicwdiig1i3nehhh1btiebtpvw FOREIGN KEY (band_id) REFERENCES public.band(id);

ALTER TABLE ONLY public.band_occasions
    ADD CONSTRAINT fkie3dpxffji4yvfj2wu6eeumo5 FOREIGN KEY (occasions_id) REFERENCES public.occasion(id);

ALTER TABLE ONLY public.musician_posts
    ADD CONSTRAINT fkio6laa164qm7g0mx3hbywpjfh FOREIGN KEY (posts_id) REFERENCES public.post(id);

ALTER TABLE ONLY public.musician_posts
    ADD CONSTRAINT fkj7g8tp9xk9jebw0ib19kme52w FOREIGN KEY (musician_id) REFERENCES public.musician(id);

ALTER TABLE ONLY public.conversation_participants
    ADD CONSTRAINT fkjot5c0f9ococ0amkh0bfbpln8 FOREIGN KEY (conversation_id) REFERENCES public.conversation(id);

ALTER TABLE ONLY public.band_members
    ADD CONSTRAINT fkjrmqdg70rbatabrq4t8nml5fx FOREIGN KEY (members_id) REFERENCES public.musician(id);

ALTER TABLE ONLY public.comment
    ADD CONSTRAINT fkk266y4652y4vae1kd0j2y3hry FOREIGN KEY (author_id) REFERENCES public.person(id);

ALTER TABLE ONLY public.band_posts
    ADD CONSTRAINT fkkb5t5bkmsih5ip47w9a3mjtpn FOREIGN KEY (band_id) REFERENCES public.band(id);

ALTER TABLE ONLY public.band_acceptable_gig_types
    ADD CONSTRAINT fkkbfbhmyr6a7nhso4rxulaukm6 FOREIGN KEY (band_id) REFERENCES public.band(id);

ALTER TABLE ONLY public.musician_occasions
    ADD CONSTRAINT fkkbgu35gt2u59ar1xxlqlee9se FOREIGN KEY (musician_id) REFERENCES public.musician(id);

ALTER TABLE ONLY public.band
    ADD CONSTRAINT fkldtwkrjs8ytfsbfiy1biy5kgc FOREIGN KEY (leader_id) REFERENCES public.musician(id);

ALTER TABLE ONLY public.post_comments
    ADD CONSTRAINT fkmws3vvhl5o4t7r7sajiqpfe0b FOREIGN KEY (post_id) REFERENCES public.post(id);

ALTER TABLE ONLY public.band_invited
    ADD CONSTRAINT fkn4yfe27kl47rva9ix4ctmt961 FOREIGN KEY (band_id) REFERENCES public.band(id);

ALTER TABLE ONLY public.conversation
    ADD CONSTRAINT fknmixyhj58h46netmb8e9msxx7 FOREIGN KEY (band_id) REFERENCES public.band(id);

ALTER TABLE ONLY public.system_person_roles
    ADD CONSTRAINT fkoe0ewxst5gt37aef552y4sukl FOREIGN KEY (system_person_id) REFERENCES public.system_person(id);

ALTER TABLE ONLY public.conversation_messages
    ADD CONSTRAINT fkowwk6j0v3ydi001gu4m5lb39d FOREIGN KEY (conversation_id) REFERENCES public.conversation(id);

ALTER TABLE ONLY public.musician_instruments
    ADD CONSTRAINT fkp0n0s0f7jq4h24fulbu461xj9 FOREIGN KEY (musician_id) REFERENCES public.musician(id);

ALTER TABLE ONLY public.musician_past_gigs
    ADD CONSTRAINT fkp8u7s61wj8p67qjiuswbcv5wk FOREIGN KEY (musician_id) REFERENCES public.musician(id);

ALTER TABLE ONLY public.band_occasions
    ADD CONSTRAINT fkpsk82ue74rpoanpfl7b9hqtwy FOREIGN KEY (band_id) REFERENCES public.band(id);

ALTER TABLE ONLY public.post_comments
    ADD CONSTRAINT fkrvgf8o4dg5kamt01me5gjqodf FOREIGN KEY (comments_id) REFERENCES public.comment(id);

ALTER TABLE ONLY public.conversation_messages
    ADD CONSTRAINT fksrkwqqihpmo60bbny5xibdx3r FOREIGN KEY (messages_id) REFERENCES public.message(id);

ALTER TABLE ONLY public.gig
    ADD CONSTRAINT fktnn3afxkrgso413v2r3f8lx0c FOREIGN KEY (organizer_id) REFERENCES public.organizer(id);

ALTER TABLE ONLY public.musician_instruments
    ADD CONSTRAINT fktoel2xnna1k4qqoed1a8ey2g1 FOREIGN KEY (instruments_id) REFERENCES public.instrument(id);
