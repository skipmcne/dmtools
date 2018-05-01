(ns skip.dmtools.npc
    (:gen-class)
    (:require [clojure.spec.alpha :as s]))
    
(s/conform ::spd [:walk 30])
(s/conform ::spd [:walk 30 :swim 30])

(s/conform ::appearance (zipmap [::eyes ::hair ::skin ::wearing ::detail ::ht ::wt ] ["grey" "red" "fine silk robes" "has one blue eye and one brown" 65 160]))
(s/conform ::appearance {::eyes "grey" ::hair "red" ::skin "fair"  ::wearing "fine silk robes" ::detail "has one blue eye and one brown" ::ht 65 ::wt 160})
(s/conform ::rolePlay (zipmap [::eyes ::hair ::skin ::wearing ::detail ::ht ::wt ] ["mannerisms" "quirks" "bonds" "flaws" "calm" "stress" "demeanor" "prejudice" "faith"]))

(s/explain ::attributes [3 15 85 [:walk 30]  2  2 ])
(s/conform ::attributes [3 15 85 [:walk 30 :swim 30] 2  2 ])

(s/conform ::skill [:Acrobatics 3])
(s/explain ::trainedSkills [[:Acrobatics 3] [:History 2]])

(s/conform ::ability [:Str 18 3])

(s/conform ::npc {::race :elf ::sex :female ::fname "fname" ::lname "lname" 
    ::appearance ["grey" "red" "fair" "fine silk robes" "scars" 65 160]})
(s/conform ::npc {::race :human ::sex :female ::fname "fname" ::lname "lname" 
       ::appearance ["grey" "red" "fair" "fine silk robes" "scars" 65 160] 
       ::attributes [3 15 85 [:walk 30 :swim 30] 2 2]})
(s/conform ::npc {::race :human ::sex :female ::fname "fname" ::lname "lname" 
       ::appearance ["grey" "red" "fair" "fine silk robes" "scars" 65 160] 
        ::attributes [3 15 85 [:walk 30 :swim 30] 2 2] 
        ::abilityScores [[:Str 18 4] [:Dex 13 1] [:Con 10 0] [:Int 10 0] [:Wis 10 0] [:Cha 10 0]] 
        ::trainedSkills [[:Acrobatics 3] [:History 2]]})

