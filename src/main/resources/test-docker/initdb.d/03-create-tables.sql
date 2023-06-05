BEGIN;

CREATE TABLE IF NOT EXISTS public.users
(
    id uuid NOT NULL,
    created_at timestamp without time zone NOT NULL
)

CREATE TABLE IF NOT EXISTS public.areas
(
    id uuid NOT NULL,
    name character varing(50) NOT NULL,
    centric_coordinates geometry NOT NULL, --지역 중심좌표
    boundary_polygon geometry NOT NULL --지역 폴리곤
)

CREATE TABLE IF NOT EXISTS public.activity_areas
(
    user_id uuid NOT NULL,
    reference_area_id uuid NOT NULL, --사용자가 소속된 기준지역
    distance_meters smallint NOT NULL, --상품검색할 기준거리(5, 10, 15km)
    area_ids varchar(132) NOT NULL, --기준지역*기준거리로 구한 활동지역(쉼표로 구분된 최대 4개의 uuid)
    authenticated_at timestamp without time zone --언제 인증했는지, 물품등록할 때는 30일 이내여야 함, 아니면 새로 인증
)

CREATE TABLE IF NOT EXISTS public.goods
(
    id uuid NOT NULL,
    seller_id uuid NOT NULL,
    selling_area_id uuid NOT NULL,
    title character varying(100) NOT NULL,
    descriptoin text NOT NULL,
    created_at timestamp without time zone NOT NULL,
    updated_at timestamp without time zone
)


END;

