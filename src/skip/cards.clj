(ns cards
    (:gen-class)
    (:require [clojure.spec.alpha :as s]))
  

(def suit? #{:club :diamond :heart :spade})
(def rank? (into #{:jack :queen :king :ace} (range 2 11)))
(def deck (for [suit suit? rank rank?] [rank suit]))

(s/def ::card (s/tuple rank? suit?))
(s/def ::hand (s/* ::card))

(s/def ::name string?)
(s/def ::score int?)
(s/def ::player (s/keys :req [::name ::score ::hand]))

(s/def ::players (s/* ::player))
(s/def ::deck (s/* ::card))
(s/def ::game (s/keys :req [::players ::deck]))

