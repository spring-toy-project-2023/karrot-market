-- This script was generated by the ERD tool in pgAdmin 4.
-- Please log an issue at https://redmine.postgresql.org/projects/pgadmin4/issues/new if you find any bugs, including reproduction steps.
BEGIN;

CREATE TABLE IF NOT EXISTS public.activity_areas
(
    user_id bigint NOT NULL,
    reference_area_id integer NOT NULL,
    distance_meters smallint NOT NULL,
    emd_area_ids integer NOT NULL,
    authenticated_at timestamp without time zone,
    CONSTRAINT pk_activity_areas PRIMARY KEY (user_id, reference_area_id)
);

CREATE TABLE IF NOT EXISTS public.block_users
(
    user_id bigint NOT NULL,
    target_id bigint NOT NULL,
    created_at timestamp without time zone NOT NULL,
    CONSTRAINT pk_block_users PRIMARY KEY (user_id, target_id)
);

CREATE TABLE IF NOT EXISTS public.categories
(
    id integer NOT NULL,
    name character varying(20) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT pk_categories PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.chat_messages
(
    id bigint NOT NULL,
    chat_room_id bigint NOT NULL,
    sender_id bigint,
    message character varying(500) COLLATE pg_catalog."default" NOT NULL,
    read_or_not boolean NOT NULL,
    created_at timestamp without time zone NOT NULL,
    CONSTRAINT pk_chat_messages PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.chat_room
(
    id bigint NOT NULL,
    goods_id bigint NOT NULL,
    buyer_id bigint NOT NULL,
    created_at timestamp without time zone NOT NULL,
    CONSTRAINT pk_chat_room PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.emd_areas
(
    id integer NOT NULL,
    sigg_area_id integer NOT NULL,
    adm_code character varying(10) COLLATE pg_catalog."default" NOT NULL,
    name character varying(50) COLLATE pg_catalog."default" NOT NULL,
    geom geometry NOT NULL,
    location geometry NOT NULL,
    version timestamp without time zone NOT NULL,
    CONSTRAINT pk_emd_areas PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.evaluation_items
(
    id integer NOT NULL,
    score numeric(3, 1) NOT NULL,
    text character varying(20) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT pk_evaluation_items PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.files
(
    id bigint NOT NULL,
    uploader_id bigint NOT NULL,
    type character varying(50) COLLATE pg_catalog."default" NOT NULL,
    name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    created_at timestamp without time zone NOT NULL,
    CONSTRAINT pk_files PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.follow_category
(
    user_id bigint NOT NULL,
    category_id integer NOT NULL,
    follow_boolean boolean
);

CREATE TABLE IF NOT EXISTS public.follow_users
(
    user_id bigint NOT NULL,
    target_id bigint NOT NULL,
    created_at timestamp without time zone NOT NULL,
    CONSTRAINT pk_follow_users PRIMARY KEY (user_id, target_id)
);

CREATE TABLE IF NOT EXISTS public.goods
(
    id bigint NOT NULL,
    seller_id bigint NOT NULL,
    selling_area_id integer NOT NULL,
    category_id integer NOT NULL,
    title character varying(100) COLLATE pg_catalog."default" NOT NULL,
    status status_type NOT NULL,
    sell_price integer,
    price_get_offer character varying(255) COLLATE pg_catalog."default" NOT NULL,
    view_count integer NOT NULL,
    description text COLLATE pg_catalog."default" NOT NULL,
    refreshed_at timestamp without time zone NOT NULL,
    created_at timestamp without time zone NOT NULL,
    CONSTRAINT pk_goods PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.goods_images
(
    goods_id bigint NOT NULL,
    file_id bigint NOT NULL,
    upload_order integer,
    is_thumbnail boolean,
    CONSTRAINT pk_goods_images PRIMARY KEY (goods_id, file_id)
);

CREATE TABLE IF NOT EXISTS public.notification_keywords
(
    id bigint NOT NULL,
    register_id bigint NOT NULL,
    keyword character varying(10) COLLATE pg_catalog."default" NOT NULL,
    created_at timestamp without time zone NOT NULL,
    CONSTRAINT pk_notification_keywords PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.notifications
(
    id bigint NOT NULL,
    receiver_id bigint NOT NULL,
    message character varying(255) COLLATE pg_catalog."default" NOT NULL,
    read_or_not boolean NOT NULL,
    type noti_type NOT NULL,
    reference_id bigint NOT NULL,
    created_at timestamp without time zone NOT NULL,
    CONSTRAINT pk_notifications PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.price_offers
(
    id bigint NOT NULL,
    offerer_id bigint NOT NULL,
    goods_id bigint NOT NULL,
    offered_price integer NOT NULL,
    accept_or_not boolean,
    created_at timestamp without time zone NOT NULL,
    CONSTRAINT pk_price_offers PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.sido_areas
(
    id integer NOT NULL,
    adm_code character varying(2) COLLATE pg_catalog."default" NOT NULL,
    name character varying(50) COLLATE pg_catalog."default" NOT NULL,
    version character varying(20) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT pk_sido_areas PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.sigg_areas
(
    id integer NOT NULL,
    sido_area_id integer NOT NULL,
    adm_code character varying(5) COLLATE pg_catalog."default" NOT NULL,
    name character varying(50) COLLATE pg_catalog."default" NOT NULL,
    version timestamp without time zone NOT NULL,
    CONSTRAINT pk_sigg_areas PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.transaction_reviews
(
    id bigint NOT NULL,
    author_id bigint NOT NULL,
    goods_id bigint NOT NULL,
    message character varying(255) COLLATE pg_catalog."default" NOT NULL,
    evaluation_items integer NOT NULL,
    created_at timestamp without time zone NOT NULL,
    rating smallint NOT NULL GENERATED ALWAYS AS IDENTITY ( MINVALUE 1 MAXVALUE 5 ),
    CONSTRAINT pk_transaction_reviews PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.users
(
    id bigint NOT NULL,
    role users_role_type NOT NULL,
    -- name
    -- email
    mobile_number character varying(11) COLLATE pg_catalog."default" NOT NULL,
    activated boolean NOT NULL,
    rating_score numeric(3, 1) NOT NULL,
    created_at timestamp without time zone NOT NULL,
    CONSTRAINT pk_users PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.wish_lists
(
    id integer NOT NULL,
    register_id bigint NOT NULL,
    goods_id bigint NOT NULL,
    created_at timestamp without time zone NOT NULL,
    CONSTRAINT pk_wish_lists PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS public.activity_areas
    ADD CONSTRAINT fk_emd_areas_to_activity_areas_1 FOREIGN KEY (reference_area_id)
    REFERENCES public.emd_areas (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;


ALTER TABLE IF EXISTS public.activity_areas
    ADD CONSTRAINT fk_users_to_activity_areas_1 FOREIGN KEY (user_id)
    REFERENCES public.users (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;


ALTER TABLE IF EXISTS public.block_users
    ADD CONSTRAINT fk_users_to_block_users_1 FOREIGN KEY (user_id)
    REFERENCES public.users (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;


ALTER TABLE IF EXISTS public.block_users
    ADD CONSTRAINT fk_users_to_block_users_2 FOREIGN KEY (target_id)
    REFERENCES public.users (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;


ALTER TABLE IF EXISTS public.chat_messages
    ADD CONSTRAINT "FK_chat_messages_TO_users_1" FOREIGN KEY (sender_id)
    REFERENCES public.users (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;


ALTER TABLE IF EXISTS public.chat_messages
    ADD CONSTRAINT "FK_chat_messages_TO_chat_room_1" FOREIGN KEY (chat_room_id)
    REFERENCES public.chat_room (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;


ALTER TABLE IF EXISTS public.chat_room
    ADD CONSTRAINT "FK_chat_room_TO_goods_1" FOREIGN KEY (goods_id)
    REFERENCES public.goods (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;


ALTER TABLE IF EXISTS public.chat_room
    ADD CONSTRAINT "FK_chat_room_TO_users_1" FOREIGN KEY (buyer_id)
    REFERENCES public.users (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;


ALTER TABLE IF EXISTS public.follow_category
    ADD CONSTRAINT "FK_follow_category_TO_users_1" FOREIGN KEY (user_id)
    REFERENCES public.users (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;


ALTER TABLE IF EXISTS public.follow_category
    ADD CONSTRAINT "FK_follow_category_TO_category_1" FOREIGN KEY (category_id)
    REFERENCES public.categories (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;


ALTER TABLE IF EXISTS public.follow_users
    ADD CONSTRAINT fk_users_to_follow_users_1 FOREIGN KEY (user_id)
    REFERENCES public.users (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;


ALTER TABLE IF EXISTS public.follow_users
    ADD CONSTRAINT fk_users_to_follow_users_2 FOREIGN KEY (target_id)
    REFERENCES public.users (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;


ALTER TABLE IF EXISTS public.goods_images
    ADD CONSTRAINT fk_files_to_goods_images_1 FOREIGN KEY (file_id)
    REFERENCES public.files (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;


ALTER TABLE IF EXISTS public.goods_images
    ADD CONSTRAINT fk_goods_to_goods_images_1 FOREIGN KEY (goods_id)
    REFERENCES public.goods (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;

ALTER TABLE IF EXISTS public.goods
    ADD CONSTRAINT fk_goods_to_emd_areas_1 FOREIGN KEY (selling_area_id)
    REFERENCES public.emd_areas (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;

ALTER TABLE IF EXISTS public.notification_keywords
    ADD CONSTRAINT "FK_notification_keywords_TO_users_1" FOREIGN KEY (register_id)
    REFERENCES public.users (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;


ALTER TABLE IF EXISTS public.notifications
    ADD CONSTRAINT "FK_notofication_TO_users_1" FOREIGN KEY (receiver_id)
    REFERENCES public.users (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;


ALTER TABLE IF EXISTS public.price_offers
    ADD CONSTRAINT "FK_price_offers_TO_users_1" FOREIGN KEY (offerer_id)
    REFERENCES public.users (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;


ALTER TABLE IF EXISTS public.price_offers
    ADD CONSTRAINT "FK_price_offers_TO_goods_1" FOREIGN KEY (goods_id)
    REFERENCES public.goods (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;


ALTER TABLE IF EXISTS public.transaction_reviews
    ADD CONSTRAINT "FK_transaction_reviews_TO_evaluation_items_1" FOREIGN KEY (evaluation_items)
    REFERENCES public.evaluation_items (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;


ALTER TABLE IF EXISTS public.transaction_reviews
    ADD CONSTRAINT "FK_transaction_reviews_TO_users_1" FOREIGN KEY (author_id)
    REFERENCES public.users (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;


ALTER TABLE IF EXISTS public.transaction_reviews
    ADD CONSTRAINT "FK_transaction_reviews_TO_goods_1" FOREIGN KEY (goods_id)
    REFERENCES public.goods (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;


ALTER TABLE IF EXISTS public.wish_lists
    ADD CONSTRAINT "FK_wish_lists_TO_users_1" FOREIGN KEY (register_id)
    REFERENCES public.users (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;


ALTER TABLE IF EXISTS public.wish_lists
    ADD CONSTRAINT "FK_wish_lists_TO_goods_1" FOREIGN KEY (goods_id)
    REFERENCES public.goods (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;

END;

