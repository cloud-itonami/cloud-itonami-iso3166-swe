(ns statute.facts
  "General-law compliance catalog for Sweden (SWE) -- extends this repo's
  existing `marketentry.facts` (public-procurement market-entry only,
  narrow scope) with a second, orthogonal catalog of statutes a company
  generally must track for compliance. Mirrors
  cloud-itonami-iso3166-jpn/-usa/-esp's `statute.facts` (ADR-2607141700,
  cloud-itonami-compliance-fact-federation).

  Every entry cites an OFFICIAL riksdagen.se (Sveriges riksdag / Swedish
  Parliament, the Svensk författningssamling database) URL -- never
  fabricated. A law not in this table has NO spec-basis, full stop;
  extend `catalog`, do not invent an id/url. Title, SFS number, and
  utfärdad (issued) date for every entry below were independently
  WebFetch-verified against the live riksdagen.se page on 2026-07-15
  (unlike government.se, which returned HTTP 403 to WebFetch, riksdagen.se
  rendered directly).")

(def catalog
  "iso3 -> vector of statute entries."
  {"SWE"
   [{:statute/id "swe.aktiebolagslag-2005-551"
     :statute/title "Aktiebolagslag (2005:551) (Swedish Companies Act)"
     :statute/jurisdiction "SWE"
     :statute/kind :law
     :statute/law-number "SFS 2005:551"
     :statute/url "https://www.riksdagen.se/sv/dokument-och-lagar/dokument/svensk-forfattningssamling/aktiebolagslag-2005551_sfs-2005-551/"
     :statute/url-provenance :official-riksdagen-se
     :statute/enacted-date "2005-06-16"
     :statute/retrieved-at "2026-07-15"
     :statute/topic #{:corporate-governance :incorporation}}
    {:statute/id "swe.dataskyddslagen-2018-218"
     :statute/title "Lag (2018:218) med kompletterande bestämmelser till EU:s dataskyddsförordning (Swedish Data Protection Act)"
     :statute/jurisdiction "SWE"
     :statute/kind :law
     :statute/law-number "SFS 2018:218"
     :statute/url "https://www.riksdagen.se/sv/dokument-och-lagar/dokument/svensk-forfattningssamling/lag-2018218-med-kompletterande-bestammelser_sfs-2018-218/"
     :statute/url-provenance :official-riksdagen-se
     :statute/enacted-date "2018-04-19"
     :statute/retrieved-at "2026-07-15"
     :statute/topic #{:data-protection :privacy}}
    {:statute/id "swe.arbetsmiljolag-1977-1160"
     :statute/title "Arbetsmiljölag (1977:1160) (Work Environment Act)"
     :statute/jurisdiction "SWE"
     :statute/kind :law
     :statute/law-number "SFS 1977:1160"
     :statute/url "https://www.riksdagen.se/sv/dokument-och-lagar/dokument/svensk-forfattningssamling/arbetsmiljolag-19771160_sfs-1977-1160/"
     :statute/url-provenance :official-riksdagen-se
     :statute/enacted-date "1977-12-19"
     :statute/retrieved-at "2026-07-15"
     :statute/topic #{:labor :employment}}]})

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
      :note (str "cloud-itonami-iso3166-swe statute.facts Wave 0 (ADR-2607141700): "
                 (count (get catalog "SWE")) " SWE statutes seeded with an "
                 "official riksdagen.se citation. Extend "
                 "`statute.facts/catalog`, never fabricate a law-id or URL.")})))

(defn by-topic [iso3 topic]
  (filterv #(contains? (:statute/topic %) topic) (spec-basis iso3)))
