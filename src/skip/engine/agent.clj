; action overrides
; emotional state
(ns convo.engine.agent
 "agent / actor / character"
  (:require [clojure.spec.alpha :as spec])
 )

(spec/def ::firstName string?)
(spec/def ::lastName string?)

(spec/def ::topic keyword?) ;; easiest for now, need to make discoverable
(spec/def ::fact string?) ;; easiest for now, figure out detail (how to use in sentences) later
(spec/def ::stmt string?)
(spec/def ::confidence int?)

(spec/def ::kbelief (spec/keys :req [::topic ::fact ::stmt ::confidence])) 
(spec/def ::kthing  (spec/keys :req [::topic ::fact ::stmt ::confidence]))
(spec/def ::kperson (spec/keys :req [::topic ::fact ::stmt ::confidence])) 

(spec/def ::kbeliefs (spec/coll-of [::kbelief]))
(spec/def ::kthings  (spec/coll-of [::kthing]))
(spec/def ::kpeople  (spec/coll-of [::kperson]))

(spec/def ::knowlege (spec/keys :req [::kbeliefs ::kthings ::kpeople ])) ; map??
;; 
(spec/def ::dex int?)
(spec/def ::str int?)
(spec/def ::con int?)
(spec/def ::int int?)
(spec/def ::wis int?)
(spec/def ::cha int?)
(spec/def ::abilities (spec/keys :req [::str ::dex ::con ::int ::wis ::cha]))
;;
(spec/def ::arcana int?)
(spec/def ::stealth int?)
(spec/def ::history int?)
(spec/def ::culture int?)
(spec/def ::skills    (spec/keys :req [::arcana ::stealth ::history ::culture ]))
;;
(spec/def ::phy int?)
(spec/def ::mag int?)
(spec/def ::env int?)
(spec/def ::defenses  (spec/keys :req [::phy ::mag ::env]))
;;
(spec/def ::daze int?)
(spec/def ::blind int?)
(spec/def ::prone int?)
(spec/def ::disad int?)
(spec/def ::diseased int?)
(spec/def ::negStatus (spec/keys :req [::dazed ::diseased ::disad ::prone ::blind]))
;;
(spec/def ::aid int?)
(spec/def ::bless int?)
(spec/def ::benStatus (spec/keys :req [::aid ::bless ]))
;(spec/def ::saves :req [::phy ::mag ::env])
;;
(spec/def ::emotions #{:amusement :contempt :contentment :embarrassment :excitement :guilt :pride :relief :satisfaction :pleasure :shame})
(spec/def ::amt int?)
(spec/def ::emotion (spec/keys :req [::emotions ::amt]))

(spec/def ::actor (spec/keys
 :req [::actorId ::firstName ::lastName ::abilities ::skills ::defenses ::negStatus ::benStatus]
 :opt [::localNPC ::localKnowlege ::knowlege ]
  ))


(defn defBelief [topicId factCategory statement conf]
	(let [b1 {::topic topicId ::fact factCategory ::stmt statement ::confidence conf}]
		(if (spec/valid? ::kbelief b1)
			b1
			nil
			)))
;(def b2 (defBelief :monestary "location" "the StoneWard Monestary is outside town" 90))
;(def b1 (defBelief :monestary "haunted" "the Monestary is Haunted" 80))
;(def b1 (defBelief :person1 "favorable" "they are nice enoug" 80))

(defn addknowledgePerson [kb topicId factCategory statement conf]
	(let [kp (defBelief topic factCategory statement conf)]
		(if (!= nil kp)

		; save it somewhere?
		)
	)
)
(defn addknowledgeThings [topicId factCategory statement conf]
	(let [kp (defBelief topic factCategory statement conf)]
		(if (!= nil kp)
		; save it somewhere?
		)
	)
)
(defn addbelief [topicId factCategory statement conf]
	(let [kp (defBelief topic factCategory statement conf)]
		(if (!= nil kp)
		assoc
		; save it somewhere?
		)
	)
)

; update agents opinion of other Actors 
(defn observeActor 	[Agent facts]
)
; update agents knowlege of the world 
(defn observeFacts 	[Agent facts]
)

; pick an action from the list of actions to do 
(defn filterActions [Agent actions]
)

; make a new character 
(defmacro char [charid describe greet askedNPC askedLoKnow]
	{charid }
)

(defn say [character stmt]
	(println (get character :firstName) "said" stmt)
)
;
(defmacro AskLocalKnowledge [KnowStmtList]
		(EvaluateStmtList NPCStmtList)
)
;
(defmacro AskedAboutNPC [NPCStmtList]
	(EvaluateStmtList NPCStmtList)
)

(defn DCRoll [charactor ability die]
	(rand-int die)
)
(defmacro EvaluateStmtList [stmtList]
	(let [defaultStmt (first stmtList)
		  DCstmtList (rest stmtList)
		  ;DCRoll (DCRoll Actor  20) ; need modifier / whatever
		  ]
	 (do 
	    ;evaluate default "say"
	    ;DC roll
	    ;then evlauate DCStmtList
	    )
	)
)