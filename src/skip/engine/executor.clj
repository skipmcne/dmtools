(ns convo.engine
 "engine-handles simloop... Player hooks TBD"
  (:require [engine.action :as action]
  			[clojure.spec.alpha :as spec]
  			[engine.narrator :as narrator]
  			[engine.executor :as executor]
  			[engine.agent :as agent]
  			[engine.practice :as practice]
  			)
 )
;;;;;;;
; Processes -> provide -> Actions
; Actions -> are Performed by -> Agents
; Agents -> modify -> Facts
; Facts -> instantiate -> Processes
;;;;;;;;

;;;;;;
; INIT
; given a scene,
; Instantiate processes 
; Instantiate characters
;
;
; given the processes, get the Actions they provide
; for each Agent, provide Actions that apply 
;
; Actions are presented to Player for "decision"
;
; Agent performs a chosen Action (which they perform)
; Action updates World Facts and instantiates processes
;
; Facts are provided to narrator for "narration"
;
;;;;;;


;(defn function [state]
;  (let [new-state (do-something state)]
;    (function new-state)))

(loop [iter (int 1) facts practices actions agents narration]
	(if (> iter Scene.limit)
		(Narrator.endScene)
		(recur (inc iter) (StepSim facts practices actions agents narration)
 )

(defn stepSim [facts practices actions agents narration]
	(let [actions  		 (processProvideActions practices facts)
		  chosen_actions (agentChooseAction agents actions)

	      newFacts 	  	 (performActions actions facts)
	      updated_agents (observeActions agents newFacts)]
	(narrator.narrate [actions chosen_actions newFacts updated_agents]))
	;return newFacts practices actions agents narration
)

(defn processProvideActions [practices facts] 
	; yield actions
	(mapcat (practice.getActions % facts) practices)
	)

(defn agentChooseAction [agents actions]
	;let chosen_actions 
	(mapcat (agent.filterActions % actions) agents)
)

(defn performActions [actions facts]
	;let chosen_actions 	
	(mapcat (action.perform % facts) actions)	
)

(defn observeActions [agents facts]
	;let chosen_actions 	
	(mapcat (agent.observe % facts) agents)	
)