(ns culture.facts
  "Country-level regional-culture catalog for Sweden (SWE) -- national
  dishes, protected products, beverages, crafts, festivals and heritage
  sites, per ADR-2607171400 addendum 2 (cloud-itonami-municipality-
  culture-catalog Wave 1, in com-junkawasaki/root). Sibling namespace to
  `marketentry.facts` / `statute.facts` (ADR-2607141700); city-level
  counterparts live in the cloud-itonami-municipality-* repos.

  Catalog is keyed by UPPERCASE ISO3 (mirrors `statute.facts`); entries
  carry no :culture/municipality (that attribute is city-level only).

  Every entry cites a source URL that was actually fetched and read on
  :culture/retrieved-at -- never fabricated. Summaries state only what the
  cited source confirms. An item not in this table has NO spec-basis, full
  stop; extend `catalog`, do not invent an id/url.")

(def catalog
  "iso3 -> vector of culture entries."
  {"SWE"
   [{:culture/id "swe.dish.kottbullar"
     :culture/name "Köttbullar"
     :culture/country "SWE"
     :culture/kind :dish
     :culture/summary "Swedish meatballs, considered a national dish in Sweden, popularized in the mid-1800s with the advent of meat grinders and traditionally served with gravy, potatoes and lingonberry jam."
     :culture/url "https://en.wikipedia.org/wiki/Meatball"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "swe.dish.surstromming"
     :culture/name "Surströmming"
     :culture/country "SWE"
     :culture/kind :dish
     :culture/summary "Lightly salted, fermented Baltic Sea herring traditional to Swedish cuisine since at least the 16th century."
     :culture/url "https://en.wikipedia.org/wiki/Surstr%C3%B6mming"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "swe.dish.kanelbulle"
     :culture/name "Kanelbulle"
     :culture/country "SWE"
     :culture/kind :dish
     :culture/summary "Swedish version of the cinnamon roll, distinctively flavored with cardamom; 4 October is promoted nationally as Kanelbullens dag (Cinnamon Roll Day) since 1999."
     :culture/url "https://en.wikipedia.org/wiki/Cinnamon_roll"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "swe.beverage.akvavit"
     :culture/name "Akvavit"
     :culture/country "SWE"
     :culture/kind :beverage
     :culture/summary "Scandinavian distilled spirit flavored with caraway and/or dill; a staple of the traditional Swedish midsummer celebration dinner."
     :culture/url "https://en.wikipedia.org/wiki/Akvavit"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "swe.craft.dala-horse"
     :culture/name "Dala horse"
     :culture/name-local "Dalahäst"
     :culture/country "SWE"
     :culture/kind :craft
     :culture/summary "Traditional carved, painted wooden statuette of a horse originating in the Swedish province of Dalarna; a symbol of both the region and Sweden overall."
     :culture/url "https://en.wikipedia.org/wiki/Dala_horse"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "swe.festival.midsummer"
     :culture/name "Midsummer"
     :culture/name-local "Midsommar"
     :culture/country "SWE"
     :culture/kind :festival
     :culture/summary "Midsummer holds such cultural significance in Sweden that proposals have been made to celebrate the National Day of Sweden then instead of on 6 June."
     :culture/url "https://en.wikipedia.org/wiki/Midsummer"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "swe.festival.saint-lucys-day"
     :culture/name "Saint Lucy's Day"
     :culture/name-local "Lucia"
     :culture/country "SWE"
     :culture/kind :festival
     :culture/summary "Celebrated in Sweden on 13 December with a girl dressed in white with a candle crown bringing saffron buns and coffee; the modern celebration regained popularity after a Stockholm newspaper sponsored an official Lucia competition in 1927."
     :culture/url "https://en.wikipedia.org/wiki/Saint_Lucy%27s_Day"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "swe.heritage.birka-hovgarden"
     :culture/name "Birka and Hovgården"
     :culture/country "SWE"
     :culture/kind :heritage
     :culture/summary "Two sites in Sweden forming a Viking Age archaeological complex, inscribed on the UNESCO World Heritage List in 1993."
     :culture/url "https://en.wikipedia.org/wiki/Birka_and_Hovg%C3%A5rden"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "swe.heritage.visby"
     :culture/name "Hanseatic Town of Visby"
     :culture/country "SWE"
     :culture/kind :heritage
     :culture/summary "Well-preserved Hanseatic-era town on the island of Gotland, Sweden, inscribed as a UNESCO World Heritage Site in 1995."
     :culture/url "https://en.wikipedia.org/wiki/Visby"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}]})

(defn spec-basis [iso3] (get catalog iso3))

(defn coverage
  ([] (coverage (keys catalog)))
  ([iso3s]
   (let [have (filter catalog iso3s)
         missing (remove catalog iso3s)]
     {:requested (count iso3s)
      :covered (count have)
      :covered-jurisdictions (vec (sort have))
      :missing-jurisdictions (vec (sort missing))
      :note (str "cloud-itonami-iso3166-swe culture catalog "
                 "(ADR-2607171400 addendum 2, Wave 1): " (count (get catalog "SWE"))
                 " SWE entries, each with a fetched-and-read citation. "
                 "Extend `culture.facts/catalog`, never fabricate an id/url.")})))

(defn by-kind [iso3 kind]
  (filterv #(= (:culture/kind %) kind) (spec-basis iso3)))
