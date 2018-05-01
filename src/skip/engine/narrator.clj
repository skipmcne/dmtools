(ns convo.engine.narrator
 "narrator"
  (:require [clojure.spec.alpha :as spec])
 );narrator

			  [actions chosen_actions newFacts updated_agents]
(defn narrate [av_actions perf_actions facts actors]
	; given a set of 'new' actions 
	; and a "narration style" pick the items to say
	; then emit lines (including dialog / facts / actor internals (
	; e.g. Andy reluctantly did x)) 
	; or Amy said "hello" eagerly...  
	(let )
	(map filterAction actions)


	)



(defn filterAction [actions]
	; not all actions are equivalent in the eyes of all narrators
	; filter the set of actions by the ones that involve the characters we are interested in for this 
	; step / chapter / etc.
	
	)