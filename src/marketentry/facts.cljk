(ns marketentry.facts "Sweden market-entry catalog.")
(def catalog
  {"SWE" {:name "Sweden"
          :owner-authority "Upphandlingsmyndigheten / e-Avrop"
          :legal-basis "LOU/LUF; EU procurement directives"
          :national-spec "e-Avrop / TendSign supplier registration + org.nr"
          :provenance "https://www.upphandlingsmyndigheten.se/"
          :required-evidence ["Organisationsnummer record"
                              "e-procurement registration record"
                              "Bolagsverket extract"
                              "Authorized-representative record"]
          :rep-owner-authority "contracting authorities / UHMY"
          :rep-legal-basis "EU establishment or authorized representative for many procedures"
          :rep-provenance "https://www.upphandlingsmyndigheten.se/"
          :corporate-number-owner-authority "Bolagsverket / Skatteverket"
          :corporate-number-legal-basis "Organisationsnummer"
          :corporate-number-provenance "https://www.bolagsverket.se/"}})

(defn spec-basis [iso3] (get catalog iso3))
(defn coverage
  ([] (coverage (keys catalog)))
  ([iso3s]
   (let [have (filter catalog iso3s) missing (remove catalog iso3s)]
     {:requested (count iso3s) :covered (count have)
      :covered-jurisdictions (vec (sort have))
      :missing-jurisdictions (vec (sort missing))
      :note "R0 catalog seed"})))
(defn required-evidence-satisfied? [iso3 submitted]
  (when-let [{:keys [required-evidence]} (spec-basis iso3)]
    (= (count required-evidence) (count (filter (set submitted) required-evidence)))))
(defn evidence-checklist [iso3] (:required-evidence (spec-basis iso3) []))
(defn rep-spec-basis [iso3]
  (when-let [sb (spec-basis iso3)]
    (when (:rep-owner-authority sb)
      (select-keys sb [:rep-owner-authority :rep-legal-basis :rep-provenance]))))
(defn corporate-number-spec-basis [iso3]
  (when-let [sb (spec-basis iso3)]
    (when (:corporate-number-owner-authority sb)
      (select-keys sb [:corporate-number-owner-authority :corporate-number-legal-basis :corporate-number-provenance]))))
