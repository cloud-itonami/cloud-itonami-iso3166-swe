(ns statute.facts-test
  (:require [clojure.string :as str]
            [clojure.test :refer [deftest is]]
            [statute.facts :as facts]))

(deftest swe-has-spec-basis
  (let [sb (facts/spec-basis "SWE")]
    (is (= 3 (count sb)))
    (is (every? #(str/starts-with? (:statute/url %) "https://www.riksdagen.se/") sb))
    (is (every? :statute/law-number sb))))

(deftest unknown-jurisdiction-has-no-spec-basis
  (is (nil? (facts/spec-basis "ATL")))
  (is (nil? (facts/spec-basis "ZZZ"))))

(deftest coverage-is-honest
  (let [c (facts/coverage ["SWE" "JPN" "ATL"])]
    (is (= 3 (:requested c)))
    (is (= 1 (:covered c)))
    (is (= ["ATL" "JPN"] (:missing-jurisdictions c)))))

(deftest by-topic-filters
  (is (= ["swe.arbetsmiljolag-1977-1160"]
         (mapv :statute/id (facts/by-topic "SWE" :labor))))
  (is (empty? (facts/by-topic "SWE" :environment)))
  (is (empty? (facts/by-topic "ATL" :labor))))
