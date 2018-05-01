(ns convo.engine.action
 "action"
  (:require [clojure.spec.alpha :as spec])
 )
; 
; Create an Action
; determine if the character thinks they can take the action
; Execute the Action 
; succeed / fail action
; update observers of the action (they can change opinions of characters involved in the action)


 (defn perform [action facts]
 	;yield facts
  )