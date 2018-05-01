(ns skip.dmtools.adventure
    (:gen-class)
    (:require [clojure.spec.alpha :as s]))

;; should show as :skip.dmtools.npc/race... etc
;; top level definitions for npc
(s/def ::theme #{:ActionAdventure :Comedy :Espionage :Horror :Mystery :Revenge :Romance})

(s/def ::Goal #{:Acquire
    :Assess
    :Clear
    :Destroy
    :Discover
    :Escort
    :Establish
    :Escape
    :Find
    :Foil
    :Hide
    :Parley
    :Pursue
    :Retrieve
    :Rescue
    :Slay
    :Stop 
    :Win
    })
(s/def ::Area #{:dungeon :wilderness :information :item :colony 
    :resource :site :challenge :NPC :fate :location :phenomenon :wilderness
    :disaster :army :fortification :caravan :operation})
(s/def ::goal (s/cat :lbl ::goalLbl 
                     :area ::arealbl  
                     :desc string?)

;(s/conform ::goal 
[:Acquire :dungeon "Acquire treasure ." ])
[:Assess :dungeon "learn the extent of a dungeon."]
[:Clear :dungeon "Clear a ruin so it can be rebuilt and reoccupied." ])
[:Destroy :dungeon "Destroy a magical threat inside the dungeon." ])
[:Discover :dungeon "Discover why a villain is interested in the dungeon." ])
[:Escape :dungeon "Escape from captivity in the dungeon." ])
[:Find :dungeon "Locate a dungeon of interest." ])
[:Foil :dungeon "Foil a villains evil scheme." ])
[:Hide :dungeon "Hide from a threat outside the dungeon." ])
[:Parley :dungeon "Parley with a villain in the dungeon." ])
[:Pursue :dungeon "Pursue fleeing foes taking refuge in the dungeon." ])
[:Retrieve :dungeon "Retrieve a stolen item hidden in the dungeon." ])
[:Rescue :dungeon "Rescue a captive." ])
[:Slay :dungeon "Slay a dragon or some other challenging monster." ])
[:Stop :dungeon "Stop the dungeon inhabitants from raiding the surface world." ])
[:Win :dungeon "Win a bet or complete a rite of passage by surviving in the dungeon for a certain amount of time." ])

[:Find :information "Find information needed for a special purpose." ])
[:Find :item "Find a particular item for a specific purpose." ])
[:Find :colony "Find a place to establish a colony." ])
[:Find :resource "Find a natural resource." ])
[:Find :site "Locate a site of interest." ])
[:Find :challenge "Find the source of strange occurrences in a haunted house or other location." ])
[:Find :NPC "Find an NPC who disappeared in the area." ])

[:Discover :fate "Discover the fate of a previous adventuring party." ])
[:Discover :location "Discover the nature and origin of a strange location." ])
[:Discover :phenomenon "Discover the nature and origin of a strange phenomenon." ])
[:Discover :wilderness "Discover the fate of a missing group of explorers." ])

[:Interfere :operation "Interfere with the operation of a business." ])

[:Rescue :disaster  "Rescue a character, monster, or object from a natural or unnatural disaster. " ])
[:Assess :disaster "Assess the scope of a natural or unnatural disaster." ])

[:Assess :army "Assess the size of an approaching army." ])
[:Assess :wilderness "Map a new land." ])

[:Arrive :wilderness "Arrive at a destination without being seen by the vill ain's forces. " ])
[:Escort :wilderness "Escort an NPC to a destination ." ])
[:Establish :wilderness "Establish trade with a distant town." ])
[:Escape :wilderness "Escape the reign of a tyrant." ])
[:Find :wilderness "Find an object that was lost in the wi ld s." ])
[:Hunt :wilderness "Hunt a specific monster." ])
[:Obtain :wilderness "Obtain information from a reclusive hermit." ])
[:Pursue :wilderness "Pursue fleeing foes." ])
[:Protect :wilderness "Protect a wilderness site from attackers." ]) 
[:Return :wilderness "Return home from a distant place." ])
[:Stop :wilderness "Stop monsters from raiding caravans and farms." ])

[:Seize :fortification "Seize control of a fortified location such as a fortress, town, or ship." ])
[:Retrieve :fortification "Retrieve an object from inside a secure location in a settlement." ])
[:Infiltrate :fortification "Infiltrate a fortified location." ])
[:Rescue :fortification "Break a prisoner out of a jail or prison camp." ])
[:Escape :fortification "Escape from a jail or prison camp." ])
[:Protect :fortification "Defend a location from attackers." ])

[:Protect :caravan "Protect a caravan traveling to a distant town ." ])
[:Retrieve :caravan "Retrieve an object from a caravan." ])
[:Salvage :caravan "Salvage an object or goods from a lost vessel or caravan." ])

[:Navigate :challenge "Successfully travel through an obstacle course to gain recognition or reward ." ])

[:Punish :villain "Bring the villain to justice." ])

[:Vindicate :person " Clear the name of an innocent NPC." ]) 
[:Protect :person " Protect or hide an NPC." ])
[:Overthrow :person " Overthrow a tyrant." ])

[:Protect :object " Protect an object." ])
[:Discover :challenge " Discover the nature and origin of a strange phenomenon that might be the villain's doing." ])
[:Find :fugitive " Find a wanted fugitive." ])
[:Discover :conspiracy " Uncover a conspiracy to overthrow a ruler." ])
[:Negotiate :challenge " Negotiate peace between enemy nations or feuding families." ])
[:Secure :challenge " Secure aid from a ruler or council." ])
[:Redeem :challenge " Help a villain find redemption." ])
[:Parley :challenge "Parley with a villain." ])
[:Smuggle :challenge "Smuggle weapons to rebel forces." ])
[:Interfere :smugglers "Stop a band of smugglers." ])
[:Discover :intelligence "Gather intelligence on an enemy force." ])
[:Win :challenge "Win a tournament." ])
[:Discover :challenge " Determine the villain's identity." ])
[:Find :challenge "Locate a stolen item." ])
[:Navigate :challenge "Make sure a wedding goes off without a hitch." ]) 

(s/def ::calls #{:dyingdelivery :grimnecessity :herooffended :LegendandRumor :MistakenIdentity :OldEnemy :oldFriend :pressingButtons})
(s/def ::setting #{:alternatePlane :city :distantLand :homeTown :ontheroad :onthesea :TorturousTerrain :Underground :UnderSea})
(s/def ::threat #{})
(s/def ::tropes #{})
(s/def ::suprise #{})
