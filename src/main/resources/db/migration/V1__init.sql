CREATE TABLE public.feed_entries
(
  id      SERIAL PRIMARY KEY       NOT NULL,
  created TIMESTAMP WITH TIME ZONE NOT NULL,
  title   VARCHAR(1000)            NOT NULL,
  link    VARCHAR(1000)            NOT NULL,
  text    TEXT                     NOT NULL
);
CREATE UNIQUE INDEX feed_entities_id_uindex
  ON public.feed_entries (id);

CREATE UNIQUE INDEX feed_entities_link_uindex
  ON public.feed_entries (link);