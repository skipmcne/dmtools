(ns convo.engine.emotions
 "emotions"
  (:require [clojure.spec.alpha :as spec])
 )

;; emotional state of a character contains :

(spec/def ::emotions #{:amusement :contempt :contentment :embarrassment :excitement :guilt :pride :relief :satisfaction :pleasure :shame})
(spec/def ::pos-int-lt100 (spec/and int? #(> % 0) #(< % 101)))
;(s/def ::emotion { :emotions :pos-int-lt100})
(spec/def ::emotion (spec/keys :req [::emotions ::pos-intlt100]))
; Contempt,
; Contentment,
; Embarrassment,
; Excitement,
; Guilt,
; Pride in achievement,
; Relief,
; Satisfaction,
; Sensory pleasure,
; Shame

;
;;;;;;
;; These can be felt at differing levels [integer]
;; They decay over time
;; Emotional state can drive "portrait changes", or other ways of displaying to players
;; 
;;
;;;;;;