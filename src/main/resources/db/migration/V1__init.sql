CREATE TABLE public.feed_entities
(
  id      serial PRIMARY KEY       NOT NULL,
  created TIMESTAMP WITH TIME ZONE NOT NULL,
  title   varchar(1000)            NOT NULL,
  text    TEXT                     NOT NULL
);
CREATE UNIQUE INDEX feed_entities_id_uindex
  ON public.feed_entities (id);