--
-- PostgreSQL database dump
--

-- Dumped from database version 11.5
-- Dumped by pg_dump version 11.5

-- Started on 2019-10-26 18:47:10

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 198 (class 1259 OID 28734)
-- Name: band; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.band (
    id bigint NOT NULL,
    bio character varying(255),
    formed_date date,
    name character varying(255),
    leader_id bigint
);


--
-- TOC entry 199 (class 1259 OID 28743)
-- Name: band_gigs; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.band_gigs (
    band_id bigint NOT NULL,
    gigs_id bigint NOT NULL
);


--
-- TOC entry 197 (class 1259 OID 28732)
-- Name: band_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.band_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

--
-- TOC entry 2966 (class 0 OID 0)
-- Dependencies: 197
-- Name: band_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

-- TOC entry 200 (class 1259 OID 28746)
-- Name: band_members; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.band_members (
    band_id bigint NOT NULL,
    members_id bigint NOT NULL
);


--
-- TOC entry 202 (class 1259 OID 28751)
-- Name: comment; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.comment (
    id bigint NOT NULL,
    content character varying(255),
    posted_on timestamp without time zone,
    author_id bigint,
    fk_post bigint
);



--
-- TOC entry 201 (class 1259 OID 28749)
-- Name: comment_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.comment_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2967 (class 0 OID 0)
-- Dependencies: 201
-- Name: comment_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--


--
-- TOC entry 204 (class 1259 OID 28759)
-- Name: conversation; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.conversation (
    id bigint NOT NULL,
    name character varying(255)
);


--
-- TOC entry 203 (class 1259 OID 28757)
-- Name: conversation_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.conversation_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2968 (class 0 OID 0)
-- Dependencies: 203
-- Name: conversation_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--


--
-- TOC entry 205 (class 1259 OID 28765)
-- Name: conversation_user; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.conversation_user (
    fk_user bigint NOT NULL,
    fk_conversation bigint NOT NULL
);



--
-- TOC entry 207 (class 1259 OID 28770)
-- Name: gig; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.gig (
    id bigint NOT NULL,
    date_time timestamp without time zone,
    description character varying(255),
    expected_duration character varying(255),
    final_deal_achieved boolean NOT NULL,
    gig_type integer,
    address character varying(255),
    extra_description character varying(255),
    x double,
    y double,
    private_gig boolean NOT NULL,
    proposed_price integer,
    final_band_id bigint,
    organizer_id bigint
);


--
-- TOC entry 206 (class 1259 OID 28768)
-- Name: gig_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.gig_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2969 (class 0 OID 0)
-- Dependencies: 206
-- Name: gig_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--
--
-- TOC entry 208 (class 1259 OID 28779)
-- Name: gig_type; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.gig_type (
    gig bigint NOT NULL,
    gig_type character varying(255) NOT NULL
);

--
-- TOC entry 196 (class 1259 OID 16605)
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 209 (class 1259 OID 28782)
-- Name: instruments; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.instruments (
    musician bigint NOT NULL,
    plays character varying(255) NOT NULL
);


-- TOC entry 210 (class 1259 OID 28785)
-- Name: message; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.message (
    id bigint NOT NULL,
    content character varying(255),
    sent_time timestamp without time zone,
    fk_sender bigint,
    fk_conversation bigint
);


-- TOC entry 211 (class 1259 OID 28790)
-- Name: message_seen; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.message_seen (
    fk_message bigint NOT NULL,
    fk_user bigint NOT NULL
);


--
-- TOC entry 212 (class 1259 OID 28793)
-- Name: musician; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.musician (
    id bigint NOT NULL,
    bio character varying(255)
);



--
-- TOC entry 213 (class 1259 OID 28798)
-- Name: musician_bands; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.musician_bands (
    fk_musician bigint NOT NULL,
    fk_band bigint NOT NULL
);



--
-- TOC entry 214 (class 1259 OID 28801)
-- Name: musician_gig; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.musician_gig (
    fk_musician bigint NOT NULL,
    fk_gig bigint NOT NULL
);



--
-- TOC entry 215 (class 1259 OID 28804)
-- Name: organizer; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.organizer (
    id bigint NOT NULL,
    manager_name character varying(255)
);



--
-- TOC entry 216 (class 1259 OID 28809)
-- Name: post; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.post (
    id bigint NOT NULL,
    content character varying(255),
    published_on timestamp without time zone,
    fk_user bigint,
    fk_band bigint
);



--
-- TOC entry 217 (class 1259 OID 28814)
-- Name: review; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.review (
    id bigint NOT NULL,
    content character varying(255),
    grade integer,
    author_id bigint
);



--
-- TOC entry 218 (class 1259 OID 28819)
-- Name: review_band; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.review_band (
    fk_band bigint NOT NULL,
    fk_review bigint NOT NULL
);


--
-- TOC entry 219 (class 1259 OID 28822)
-- Name: review_gig; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.review_gig (
    fk_gig bigint NOT NULL,
    fk_review bigint NOT NULL
);


--
-- TOC entry 220 (class 1259 OID 28825)
-- Name: review_musician; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.review_musician (
    fk_musician bigint NOT NULL,
    fk_review bigint NOT NULL
);


--
-- TOC entry 221 (class 1259 OID 28828)
-- Name: review_organizer; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.review_organizer (
    fk_organizer bigint NOT NULL,
    fk_review bigint NOT NULL
);



--
-- TOC entry 222 (class 1259 OID 28831)
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    id bigint NOT NULL,
    email character varying(255),
    password_hash character varying(255),
    phone_number character varying(255),
    musician_id bigint,
    organizer_id bigint
);


--
-- TOC entry 2780 (class 2604 OID 28737)
-- Name: band id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.band ALTER COLUMN id SET DEFAULT nextval('public.band_id_seq'::regclass);


--
-- TOC entry 2781 (class 2604 OID 28754)
-- Name: comment id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.comment ALTER COLUMN id SET DEFAULT nextval('public.comment_id_seq'::regclass);


--
-- TOC entry 2782 (class 2604 OID 28762)
-- Name: conversation id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.conversation ALTER COLUMN id SET DEFAULT nextval('public.conversation_id_seq'::regclass);


--
-- TOC entry 2783 (class 2604 OID 28773)
-- Name: gig id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.gig ALTER COLUMN id SET DEFAULT nextval('public.gig_id_seq'::regclass);


--
-- TOC entry 2785 (class 2606 OID 28742)
-- Name: band band_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.band
    ADD CONSTRAINT band_pkey PRIMARY KEY (id);


--
-- TOC entry 2789 (class 2606 OID 28756)
-- Name: comment comment_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.comment
    ADD CONSTRAINT comment_pkey PRIMARY KEY (id);


--
-- TOC entry 2791 (class 2606 OID 28764)
-- Name: conversation conversation_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.conversation
    ADD CONSTRAINT conversation_pkey PRIMARY KEY (id);


--
-- TOC entry 2793 (class 2606 OID 28778)
-- Name: gig gig_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.gig
    ADD CONSTRAINT gig_pkey PRIMARY KEY (id);


--
-- TOC entry 2795 (class 2606 OID 28789)
-- Name: message message_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.message
    ADD CONSTRAINT message_pkey PRIMARY KEY (id);


--
-- TOC entry 2797 (class 2606 OID 28797)
-- Name: musician musician_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.musician
    ADD CONSTRAINT musician_pkey PRIMARY KEY (id);


--
-- TOC entry 2799 (class 2606 OID 28808)
-- Name: organizer organizer_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.organizer
    ADD CONSTRAINT organizer_pkey PRIMARY KEY (id);


--
-- TOC entry 2801 (class 2606 OID 28813)
-- Name: post post_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.post
    ADD CONSTRAINT post_pkey PRIMARY KEY (id);


--
-- TOC entry 2803 (class 2606 OID 28818)
-- Name: review review_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.review
    ADD CONSTRAINT review_pkey PRIMARY KEY (id);


--
-- TOC entry 2787 (class 2606 OID 28840)
-- Name: band_gigs uk_nyt88b01uk7d13ofxeuw06byw; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.band_gigs
    ADD CONSTRAINT uk_nyt88b01uk7d13ofxeuw06byw UNIQUE (gigs_id);


--
-- TOC entry 2805 (class 2606 OID 28838)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- TOC entry 2810 (class 2606 OID 28861)
-- Name: band_members fk16r4cxs4q8qf5ia7oe2elgsgw; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.band_members
    ADD CONSTRAINT fk16r4cxs4q8qf5ia7oe2elgsgw FOREIGN KEY (band_id) REFERENCES public.band(id);


--
-- TOC entry 2807 (class 2606 OID 28846)
-- Name: band_gigs fk3vuo54n4b9wa1mi5in81q5f3d; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.band_gigs
    ADD CONSTRAINT fk3vuo54n4b9wa1mi5in81q5f3d FOREIGN KEY (gigs_id) REFERENCES public.gig(id);


--
-- TOC entry 2830 (class 2606 OID 28961)
-- Name: review_band fk4jqo1iqkdqin21tqytytmpp2a; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.review_band
    ADD CONSTRAINT fk4jqo1iqkdqin21tqytytmpp2a FOREIGN KEY (fk_review) REFERENCES public.review(id);


--
-- TOC entry 2814 (class 2606 OID 28881)
-- Name: conversation_user fk6h34tx8k7sgrsahsh6ccfefig; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.conversation_user
    ADD CONSTRAINT fk6h34tx8k7sgrsahsh6ccfefig FOREIGN KEY (fk_user) REFERENCES public.users(id);


--
-- TOC entry 2828 (class 2606 OID 28951)
-- Name: post fk6rnvwoaexn5iau5stuup73min; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.post
    ADD CONSTRAINT fk6rnvwoaexn5iau5stuup73min FOREIGN KEY (fk_band) REFERENCES public.band(id);


--
-- TOC entry 2838 (class 2606 OID 29001)
-- Name: users fk7suwmm4ci77orddpxbo7m6mr9; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT fk7suwmm4ci77orddpxbo7m6mr9 FOREIGN KEY (musician_id) REFERENCES public.musician(id);


--
-- TOC entry 2823 (class 2606 OID 28926)
-- Name: musician_bands fk7uqw4d682ci6srckaw21esjb4; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.musician_bands
    ADD CONSTRAINT fk7uqw4d682ci6srckaw21esjb4 FOREIGN KEY (fk_band) REFERENCES public.band(id);


--
-- TOC entry 2812 (class 2606 OID 28871)
-- Name: comment fk8o85t6k37w03wv2rps0xdr4t2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.comment
    ADD CONSTRAINT fk8o85t6k37w03wv2rps0xdr4t2 FOREIGN KEY (fk_post) REFERENCES public.post(id);


--
-- TOC entry 2829 (class 2606 OID 28956)
-- Name: review fk9o91rotu09ywxerf1evksnxhd; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.review
    ADD CONSTRAINT fk9o91rotu09ywxerf1evksnxhd FOREIGN KEY (author_id) REFERENCES public.users(id);


--
-- TOC entry 2834 (class 2606 OID 28981)
-- Name: review_musician fkavhlnnssxp7766bm48xvsh5d5; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.review_musician
    ADD CONSTRAINT fkavhlnnssxp7766bm48xvsh5d5 FOREIGN KEY (fk_review) REFERENCES public.review(id);


--
-- TOC entry 2827 (class 2606 OID 28946)
-- Name: post fkb5apboerattpbrgavnsyg3gbf; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.post
    ADD CONSTRAINT fkb5apboerattpbrgavnsyg3gbf FOREIGN KEY (fk_user) REFERENCES public.musician(id);


--
-- TOC entry 2836 (class 2606 OID 28991)
-- Name: review_organizer fkbbmyktfcff4n73v112f6g8v06; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.review_organizer
    ADD CONSTRAINT fkbbmyktfcff4n73v112f6g8v06 FOREIGN KEY (fk_review) REFERENCES public.review(id);


--
-- TOC entry 2833 (class 2606 OID 28976)
-- Name: review_gig fkbleam6r7jmiomx6e0jl83x4b5; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.review_gig
    ADD CONSTRAINT fkbleam6r7jmiomx6e0jl83x4b5 FOREIGN KEY (fk_gig) REFERENCES public.gig(id);


--
-- TOC entry 2815 (class 2606 OID 28886)
-- Name: gig fkblu99fquif1tal1ugvl0uvf2w; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.gig
    ADD CONSTRAINT fkblu99fquif1tal1ugvl0uvf2w FOREIGN KEY (final_band_id) REFERENCES public.band(id);


--
-- TOC entry 2808 (class 2606 OID 28851)
-- Name: band_gigs fke55r8o3nshoutfg41thur01yl; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.band_gigs
    ADD CONSTRAINT fke55r8o3nshoutfg41thur01yl FOREIGN KEY (band_id) REFERENCES public.band(id);


--
-- TOC entry 2839 (class 2606 OID 29006)
-- Name: users fke821f4ah3ehklsrtkvuuh9ap6; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT fke821f4ah3ehklsrtkvuuh9ap6 FOREIGN KEY (organizer_id) REFERENCES public.organizer(id);


--
-- TOC entry 2835 (class 2606 OID 28986)
-- Name: review_musician fkhwvu91ulgxqm33xml00g6tnpk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.review_musician
    ADD CONSTRAINT fkhwvu91ulgxqm33xml00g6tnpk FOREIGN KEY (fk_musician) REFERENCES public.musician(id);


--
-- TOC entry 2822 (class 2606 OID 28921)
-- Name: message_seen fkie0t31o4o6c75s7qkcawxjofj; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.message_seen
    ADD CONSTRAINT fkie0t31o4o6c75s7qkcawxjofj FOREIGN KEY (fk_message) REFERENCES public.message(id);


--
-- TOC entry 2811 (class 2606 OID 28866)
-- Name: comment fkir20vhrx08eh4itgpbfxip0s1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.comment
    ADD CONSTRAINT fkir20vhrx08eh4itgpbfxip0s1 FOREIGN KEY (author_id) REFERENCES public.users(id);


--
-- TOC entry 2825 (class 2606 OID 28936)
-- Name: musician_gig fkirap7svpq9wy3ga0mpuvyyyqe; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.musician_gig
    ADD CONSTRAINT fkirap7svpq9wy3ga0mpuvyyyqe FOREIGN KEY (fk_gig) REFERENCES public.gig(id);


--
-- TOC entry 2820 (class 2606 OID 28911)
-- Name: message fkjaumr8d07s0iitdhieitt0pki; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.message
    ADD CONSTRAINT fkjaumr8d07s0iitdhieitt0pki FOREIGN KEY (fk_conversation) REFERENCES public.conversation(id);


--
-- TOC entry 2819 (class 2606 OID 28906)
-- Name: message fkjfw5kowvm9sn7d5e1lmrm1jsa; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.message
    ADD CONSTRAINT fkjfw5kowvm9sn7d5e1lmrm1jsa FOREIGN KEY (fk_sender) REFERENCES public.users(id);


--
-- TOC entry 2809 (class 2606 OID 28856)
-- Name: band_members fkjrmqdg70rbatabrq4t8nml5fx; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.band_members
    ADD CONSTRAINT fkjrmqdg70rbatabrq4t8nml5fx FOREIGN KEY (members_id) REFERENCES public.musician(id);


--
-- TOC entry 2806 (class 2606 OID 28841)
-- Name: band fkldtwkrjs8ytfsbfiy1biy5kgc; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.band
    ADD CONSTRAINT fkldtwkrjs8ytfsbfiy1biy5kgc FOREIGN KEY (leader_id) REFERENCES public.musician(id);


--
-- TOC entry 2832 (class 2606 OID 28971)
-- Name: review_gig fkm9f7b32824jxb8dfdwmfu6bqb; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.review_gig
    ADD CONSTRAINT fkm9f7b32824jxb8dfdwmfu6bqb FOREIGN KEY (fk_review) REFERENCES public.review(id);


--
-- TOC entry 2824 (class 2606 OID 28931)
-- Name: musician_bands fkmpmntcvug40w7upuq7b1the0v; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.musician_bands
    ADD CONSTRAINT fkmpmntcvug40w7upuq7b1the0v FOREIGN KEY (fk_musician) REFERENCES public.musician(id);


--
-- TOC entry 2837 (class 2606 OID 28996)
-- Name: review_organizer fkmuwxi1qulgy9xj3shybpll8cd; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.review_organizer
    ADD CONSTRAINT fkmuwxi1qulgy9xj3shybpll8cd FOREIGN KEY (fk_organizer) REFERENCES public.organizer(id);


--
-- TOC entry 2821 (class 2606 OID 28916)
-- Name: message_seen fkobdubwltyhe0s8tiemdm9e67l; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.message_seen
    ADD CONSTRAINT fkobdubwltyhe0s8tiemdm9e67l FOREIGN KEY (fk_user) REFERENCES public.users(id);


--
-- TOC entry 2813 (class 2606 OID 28876)
-- Name: conversation_user fkovyg0r9rqc2k5veg245juqqc3; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.conversation_user
    ADD CONSTRAINT fkovyg0r9rqc2k5veg245juqqc3 FOREIGN KEY (fk_conversation) REFERENCES public.conversation(id);


--
-- TOC entry 2817 (class 2606 OID 28896)
-- Name: gig_type fkpnvb9f1py3lrqorl8awpsog99; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.gig_type
    ADD CONSTRAINT fkpnvb9f1py3lrqorl8awpsog99 FOREIGN KEY (gig) REFERENCES public.band(id);


--
-- TOC entry 2831 (class 2606 OID 28966)
-- Name: review_band fkr2w6jnnodvif91akeyigeaxbd; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.review_band
    ADD CONSTRAINT fkr2w6jnnodvif91akeyigeaxbd FOREIGN KEY (fk_band) REFERENCES public.band(id);


--
-- TOC entry 2826 (class 2606 OID 28941)
-- Name: musician_gig fksf6fipfrf2kix5fr5h3ui2um0; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.musician_gig
    ADD CONSTRAINT fksf6fipfrf2kix5fr5h3ui2um0 FOREIGN KEY (fk_musician) REFERENCES public.musician(id);


--
-- TOC entry 2818 (class 2606 OID 28901)
-- Name: instruments fktm7q9fak9no6yb717t3fov3j6; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.instruments
    ADD CONSTRAINT fktm7q9fak9no6yb717t3fov3j6 FOREIGN KEY (musician) REFERENCES public.musician(id);


--
-- TOC entry 2816 (class 2606 OID 28891)
-- Name: gig fktnn3afxkrgso413v2r3f8lx0c; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.gig
    ADD CONSTRAINT fktnn3afxkrgso413v2r3f8lx0c FOREIGN KEY (organizer_id) REFERENCES public.organizer(id);


-- Completed on 2019-10-26 18:47:11

--
-- PostgreSQL database dump complete
--