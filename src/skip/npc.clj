(ns skip.dmtools.npc
    (:require [clojure.spec.alpha :as s
               skip.dmtools.npc.resouces as nr]))
;; should show as :skip.dmtools.npc/race... etc
;; top level definitions for npc    
(s/def ::race #{::human ::elf ::halfelf ::dwarf ::dragonborn :tiefling ::halfling ::halforc ::gnome ::goliath ::aasimar})
(s/def ::class string?)
(s/def ::subclass string?)
;
(s/def ::sex #{:male :female })
(s/def ::fname string?)
(s/def ::lname string?)
;appearance
(s/def ::appearance-eyes string?)
(s/def ::appearance-hair string?)
(s/def ::appearance-skin string?)
(s/def ::appearance-wearing string?)
(s/def ::appearance-detail string?)
(s/def ::appearance-ht (s/and int? #(> % 0)))
(s/def ::appearance-wt (s/and int? #(> % 0)))

(s/def ::appearance (s/keys :req [::appearance-eyes ::appearance-hair ::appearance-skin ::appearance-wearing]
                            :opt [::appearance-detail ::appearance-ht ::appearance-wt]))
;(s/conform ::appearance ["grey" "red" "fair" "fine silk robes" "has one blue eye and one brown" 65 160])

(s/def ::mannerisms string?)
(s/def ::quirks string?)
(s/def ::bonds string?)
(s/def ::flaws string?)
(s/def ::calm string?)
(s/def ::stress string?)
(s/def ::demeanor string?)
(s/def ::prejudice string?)
(s/def ::faith string?)

(s/def ::rolePlay (s/keys :req[::mannerisms ::quirks ::bonds ::flaws ::calm ::stress ::demeanor]
                          :opt[::prejudice ::faith]))
;;attributes
(s/def ::level (s/and int? #(> % 0) #(< % 21)))
(s/def ::AC (s/and int? #(> % 0) #(< % 21)))
(s/def ::HP (s/and int? #(> % 0) ))
(s/def ::passivePerception (s/and int? #(> % 0) #(< % 40)))
(s/def ::proficiency-bonus (s/and int? #(> % 0)))
;;speed (in "feet") ; multiple instances, different types
(s/def ::speed-move (s/and int? #(> % 0)))
(s/def ::speed-type #{::speed-fly ::speed-swim ::speed-walk} ) 
(s/def ::spd (s/* (s/keys :req[::speed-move ::speed-type] )))

;nested
(s/def ::attributes (s/keys :req[::level ::AC ::HP ::spd ::proficiency-bonus ::passivePerception]))

;; generic modifier
(s/def ::modifier int? )
;; skills 
(s/def ::skillname #{::Athletics
    ::Acrobatics ::SleightOfHand :Stealth
    ::Arcana ::History ::Investigation ::Nature ::Religion
    ::AnimalHandling ::Insight ::Medicine ::Perception ::Survival
    ::Deception ::Intimidation ::Performance ::Persuasion })
;(s/def ::skill (s/cat :name ::skillname :mod ::modifier))
(s/def ::skill (s/tuple ::skillname ::modifier))
;;
(s/def ::trainedSkills (s/* ::skill))

;; ability scores 
(s/def ::abilityabbr #{::Str ::Dex ::Con ::Int ::Wis ::Cha})
(s/def ::abilityscore (s/and int? #(> 21 % 0) #(< % 21)))
;(s/def ::ability (s/cat :abbr ::abilityabbr :score ::abilityscore :mod ::modifier ))
(s/def ::ability (s/tuple ::abilityabbr ::abilityscore ::modifier ))
;;
(s/def :abilityScores (s/* ::ability))
;;(s/explain ::abilityScores [[:Str 18 4] [::dex 13 1]])


(s/def ::npc (s/keys :req [::race ::sex ::fname ::lname ::appearance]
                     :opt [::rolePlay ::attributes ::abilityScores ::trainedSkills]))
; 
;; -----------
; racial templates




;;;; generators

(defn rand-race 
    ([] (rand-nth [::human ::elf ::halfelf ::dwarf ::dragonborn :tiefling ::halfling ::halforc ::gnome ::goliath ::aasimar])))
(defn rand-class 
    ([] (rand-nth [::Barbarian ::Bard ::Cleric ::Druid ::Fighter ::Monk ::Paladin ::Ranger ::Rogue ::Sorcerer ::Warlock ::Wizard
        ::Artificer
        ::Mystic
        ::Ranger-No Spells
        ::Ranger-Redesign
        ::Ranger-Revised
        ::Rune-Scribe])))

(defn rand-subclass 
    ([class] (case class 
        ::Barbarian (rand-nth [::Barb-Ancestral-Guardian ::Barb-Battlerager ::Barb-Berserker ::Barb-Storm-Herald ::Barb-Totem-Warrior ::Barb-Zealot])
        ::Bard      (rand-nth [::Bard-Glamour ::Bard-Lore  ::Bard-Satire ::Bard-Swords ::Bard-Valor ::Bard-Whispers])
        ::Cleric    (rand-nth [::Cleric-Arcana ::Cleric-City ::Cleric-Death ::Cleric-Forge ::Cleric-Grave ::Cleric-Knowledge ::Cleric-Life ::Cleric-Light ::Cleric-Nature ::Cleric-Protection ::Cleric-Tempest ::Cleric-Trickery ::Cleric-War])
        ::Druid     (rand-nth [::Druid-Dreams ::Druid-Land ::Druid-Moon ::Druid-Shepherd ::Druid-Spores ::Druid-Twilight ])
        ::Fighter   (rand-nth [::Fighter-Arcane-Archer ::Fighter-Banneret ::Fighter-Battle-Master ::Fighter-Brute ::Fighter-Cavalier ::Fighter-Champion ::Fighter-Eldritch-Knight ::Fighter-Knight ::Fighter-Samurai ::Fighter-Sharpshooter])
        ::Monk      (rand-nth [::Monk-Drunken-Master  ::Monk-Four-Elements ::Monk-Kensei ::Monk-Long-Death ::Monk-Open-Hand ::Monk-Shadow ::Monk-Sun-Soul ::Monk-Tranquility])
        ::Paladin   (rand-nth [::Paladin-Ancients ::Paladin-Conquest ::Paladin-the-Crown ::Paladin-Devotion ::Paladin-Redemption ::Paladin-Treachery ::Paladin-Vengeance ::Paladin-Oathbreaker])
        ::Ranger    (rand-nth [::Ranger-Beast-Master ::Ranger-Gloom Stalker ::Ranger-Horizon-Walker ::Ranger-Hunter ::Ranger-Monster-Slayer ::Ranger-Primeval-Guardian])        
        ::Rogue     (rand-nth [::Rogue-Arcane-Trickster ::Rogue-Assassin ::Rogue-Inquisitive ::Rogue-Mastermind ::Rogue-Scout ::Rogue-Swashbuckler ::Rogue-Thief])
        ::Sorcerer  (rand-nth [::Sorcerer-Divine-Soul ::Sorcerer-Draconic-Bloodline ::Sorcerer-Phoenix-Sorcery ::Sorcerer-Sea-Sorcery ::Sorcerer-Shadow-Magic† ::Sorcerer-Stone-Sorcery ::Sorcerer-Storm-Sorcery‡ ::Sorcerer-Wild-Magic])
        ::Warlock   (rand-nth [::Warlock-Archfey ::Warlock-Celestial ::Warlock-Fiend ::Warlock-Ghost-in-the-Machine ::Warlock-Great-Old-One ::Warlock-Hexblade ::Warlock-Raven-Queen ::Warlock-Seeker ::Warlock-Undying])
        ::Wizard    (rand-nth [::Wizard-Artificer ::Wizard-Bladesinging ::Wizard-Lore-Mastery ::Wizard-Abjuration ::Wizard-Conjuration ::Wizard-Divination ::Wizard-Enchantment ::Wizard-Evocation ::Wizard-Illusion ::Wizard-Invention ::Wizard-Necromancy ::Wizard-Transmutation ::Wizard-Technomancy ::Wizard-Theurgy ::Wizard-War-Magic])
    )))
(defn rand-sex
    ([] (rand-nth [:male :female])))


;    (s/conform ::npc {::race ::human ::sex :female ::fname "fname" ::lname "lname" 
;        ::appearance ["grey" "red" "fair" "fine silk robes" "scars" 65 160] 
;         ::attributes [3 15 85 [:walk 30 :swim 30] 2 2] 
;         ::abilityScores [[:Str 18 4] [::dex 13 1] [:Con 10 0] [:Int 10 0] [:Wis 10 0] [:Cha 10 0]] 
;         ::trainedSkills [[:Acrobatics 3] [:History 2]]})

(defn genNpc 
    ([] (genNpc (rand-race) (rand-sex)))
    ([race sex] 
        (let [generated (s/conform ::npc {  ::race race 
                                            ::sex sex 
                                            ::fname (genFname race sex) 
                                            ::lname (genLname race)
                                            ::appearance (genAppearance race)
                                            ::rolePlay (genRolePlay)})]
            (if (= generated ::s/invalid)
                (s/explain-data ::npc generated))
                generated)
        ); let
    ) ;arity
); defn


(defn genLname 
    ([] (genLname (rand-race)))
    ([race]  (case race
        ::aasimar  "smith"
        ::dwarf    (rand-nth nr.dwarf-lname)
        ::elf      (rand-nth nr.elf-lname)
;        :highelf  (rand-nth (range (* 4 12) (+ (* 6 12) 5) ))
;        :moonelf  (rand-nth (range (* 4 12) (+ (* 6 12) 5) ))
;        :woodelf  (rand-nth (range (* 4 12) (+ (* 6 12) 5) ))
;        ::drowelf  (rand-nth (range (* 4 12) (+ (* 6 12) 5) ))
        :firbolg  "smith"
        ::halfling (rand-nth nr.halfling-lname)
        ::human    "smith"
        ::dragonborn (rand-nth nr.dragonborn-lname)
        ::gnome    (rand-nth nr.gnome-lname)
        ::goliath  "smith"
        ::halfelf  "smith"
        ::halforc  "smith"
        :tiefling (rand-nth  nr.tiefling-lname)
        :tabaxi   "smith"
      ) ;case
    ) ;arity
) ;fn



(defn genFname
    ([] (genFname (rand-race) (rand-sex))) 
    ([arace] (genFname arace (rand-sex)))
    ([race sex] (case race
        ::aasimar  (get-defaultfname sex)
        ::dwarf    (get-defaultfname sex)
        ::elf      (get-defaultfname sex)
;        :highelf  (rand-nth (range (* 4 12) (+ (* 6 12) 5) ))
;        :moonelf  (rand-nth (range (* 4 12) (+ (* 6 12) 5) ))
;        :woodelf  (rand-nth (range (* 4 12) (+ (* 6 12) 5) ))
;        ::drowelf  (rand-nth (range (* 4 12) (+ (* 6 12) 5) ))
        :firbolg  (get-defaultfname sex)
        ::halfling   (get-defaultfname sex)
        ::human      (get-defaultfname sex)
        ::dragonborn (get-defaultfname sex)
        ::gnome    (get-defaultfname sex)
        ::goliath  (get-defaultfname sex)
        ::halfelf  (get-defaultfname sex)
        ::halforc  (get-defaultfname sex)
        :tiefling (get-defaultfname sex)
        :tabaxi (get-defaultfname sex)
      ) ;case
    ) ;arity
) ;fn

(defn get-defaultfname 
    ([sex] (if (= sex :female) 
                (rand-nth ["sherry"])
                (rand-nth ["steve"]))
    ))
;;; ---- rp ----

(defn genRolePlay 
    ([] (let [
        keys   [::mannerisms ::quirks ::bonds ::flaws ::calm ::stress ::demeanor ::prejudice ::faith]
        values [(rand-nth mannerisms) (rand-nth quirks) (rand-nth bonds) (rand-nth flaws) (rand-nth calm) (rand-nth stress) (rand-nth demeanor) (rand-nth prejudice)  (rand-nth faith)]]
        (s/conform ::rolePlay (zipmap keys values))
        )
    ))
;;; ---- fname ----
;; --------------
;;; appearance
;; --------------
(defn genAppearance 
    ([] (genAppearance ::human))
    ([race] (let [keys [::appearance-eyes ::appearance-hair ::appearance-skin ::appearance-wearing ::appearance-detail ::appearance-ht ::appearance-wt]
                  values [(eyecolor race) (haircolor race) (skincolor race) (wearing) (rand-nth appearance) (height race) (weight race)]]
            

(defn wearing
    ([] (str (rand-nth nr.modifier) (rand-nth nr.clothing)))
)

(defn height
    ([] (height ::human))
    ([race] (case race
               ::aasimar  (rand-nth (range (* 5 12) (+ (* 6 12) 5) ))
               ::dwarf    (rand-nth (range (* 4 12) (* 5 12) ))
               ::elf      (rand-nth (range (* 4 12) (+ (* 6 12) 5) ))
               :highelf  (rand-nth (range (* 4 12) (+ (* 6 12) 5) ))
               :moonelf  (rand-nth (range (* 4 12) (+ (* 6 12) 5) ))
               :woodelf  (rand-nth (range (* 4 12) (+ (* 6 12) 5) ))
               ::drowelf  (rand-nth (range (* 4 12) (+ (* 6 12) 5) ))
               :firbolg  (rand-nth (range (* 7 12) (* 8 12)))
               ::halfling   (rand-nth (range (* 3 12) (* 4 12) ))
               ::human      (rand-nth (range (* 5 12) (+ (* 6 12) 5) ))
               ::dragonborn (rand-nth (range (* 5 12) (* 7 12) ))
               ::gnome    (rand-nth (range (* 3 12) (* 4 12) ))
               ::goliath  (rand-nth (range (* 7 12) (* 8 12) ))
               ::halfelf  (rand-nth (range (* 5 12) (+ (* 6 12) 5) ))
               ::halforc  (rand-nth (range (* 6 12) (+ (* 7 12) 5) ))
               :tiefling   (rand-nth (range (* 5 12) (+ (* 6 12) 5) ))
               :tabaxi     (rand-nth (range (* 4 12) (+ (* 6 12) 5) ))
             )))
 
 (defn weight
    ([] (weight ::human))
    ([race] (case race
               ::aasimar (rand-nth (range 125 250 )) 
               ::dwarf  (rand-nth (range 130 180 ))
               ::elf  (rand-nth (range 100 145 ))
               :highelf  (rand-nth (range 100 145 ))
               :moonelf  (rand-nth (range 100 145 ))
               :woodelf  (rand-nth (range 100 145 ))
               ::drowelf  (rand-nth (range 100 145 ))
               :firbolg  (rand-nth (range 240 300 ))
               ::halfling   (rand-nth (range 40 45 ))
               ::human  (rand-nth (range 125 250 ))
               ::dragonborn  (rand-nth (range 250 320 ))
               ::gnome  (rand-nth (range 40 45 ))
               ::goliath  (rand-nth (range 280 340 ))
               ::halfelf  (rand-nth (range 125 200 ))
               ::halforc  (rand-nth (range 180 250 ))
               :tiefling  (rand-nth (range 125 250 ))
               :tabaxi  (rand-nth (range 125 200 ))
             )))
 
  (defn skincolor
    ([] (skincolor ::human))
    ([race] (case race
               ::aasimar (rand-nth ["grey" "white" "gold" "tan"])
               ::dwarf (rand-nth ["deep brown" "pale and Freckled" "lighl brown"  "deep tan"])
               ::elf (rand-nth ["copper" "bronze" "bluish white"])
               :highelf (rand-nth ["bronze"])
               :moonelf (rand-nth ["alabaster" "alabaster tinged with blue"])
               :woodelf (rand-nth ["copperish" "tan" "green"])
               ::drowelf (rand-nth ["obsidian" "black"])
               :firbolg (rand-nth ["green" "brown" "blonde" ])
               ::halfling  (rand-nth ["tan" "pale" "ruddy"])
               ::human (rand-nth ["light" "pale white" "white" "fair" "light brown" "olive" "brown" "very dark brown" "black"])
               ::dragonborn (rand-nth ["red" "green" "blue" "white" "black" "gold" "silver" "brass" "copper" "bronze" "scarlet" "rust" "gold" "copper-green"])
               ::gnome (rand-nth ["tan" "pale" "brown"])
               ::goliath (rand-nth ["grey" "white"])
               ::halfelf (rand-nth ["light" "pale white" "white" "fair" "light brown" "olive" "brown" "very dark brown" "black" "copper" "bronze" "bluish white"])
               ::halforc (rand-nth ["grey" "green" "light green" "light" "pale white" "white" "fair" "light brown" "olive" "brown" "very dark brown" "black"])
               :tiefling (rand-nth ["light" "pale white" "white" "fair" "light brown" "olive" "brown" "very dark brown" "black" "dark red" "blue" "purple" "dark blue" "violet"])
               :tabaxi (rand-nth ["leopard" "tiger" "panther" "lion"])
             )))
 
 (defn haircolor 
   ([] (haircolor ::human)) 
   ([race] (case race
               ::aasimar (rand-nth ["black" "grey" "white" "gold" "blonde" "red"])
               ::dwarf    (rand-nth ["black" "gray" "brown" "red"])
               ::elf      (rand-nth ["green" "blue" "silver" "white"])
               :highelf  (rand-nth ["copper" "black" "golden blond"])
               :moonelf  (rand-nth ["silver-white" "black" "blue" "blonde" "brown" "red"])
               :woodelf  (rand-nth ["blonde" "brown" "black" "copper-colored"])
               ::drowelf  (rand-nth ["stark white" "pale yellow" "pale silver" ])
               :firbolg  (rand-nth ["green" "black" "brown" "blonde" ])
               ::halfling (rand-nth ["sandy" "straw" "strawberry" "blonde"  "brown"])
               ::human    (rand-nth ["black" "brunette" "dark brown" "medium brown" "light brown" "chestnut" "auburn" "copper" "ginger" "titian" "strawberry blonde" "light blonde" "dark blonde" "golden blonde" "grey" "white"])
               ::dragonborn (str (rand-nth ["red" "green" "blue" "white" "black" "gold" "silver" "brass" "copper" "bronze" "scarlet" "rust" "gold" "copper-green"]) " tinged crest")
               ::gnome "fair"
               ::goliath "none"
               ::halfelf (rand-nth [(haircolor ::human) (haircolor ::elf)])
               ::halforc (rand-nth ["black" (haircolor ::human)])
               :tiefling (rand-nth ["black" "brown" "dark red" "dark blue" "purple"])
               :tabaxi (rand-nth ["grey" "blonde" "orange" "white"])
             ))
 )
 
 (defn eyecolor 
   ([] (eyecolor ::human)) 
   ([race] (case race
               ::aasimar (rand-nth ["black" "grey" "white" "gold" "blue" "yellow" "red"])
               ::dwarf (rand-nth ["black" "gray" "brown"])
               ::elf   (rand-nth ["gold" "silver"])
               :highelf (rand-nth ["golden" "black" "silver"])
               :moonelf (rand-nth ["blue flecked with gold" "green flecked with gold"])
               :woodelf (rand-nth ["green" "brown" "hazel"])
               ::drowelf (rand-nth ["pale lilac" "pale silver" "pale pink" "pale blue" "pale red"])
               :firbolg (rand-nth ["green" "hazel" "brown" "blue" ])
               ::halfling (rand-nth ["brown" "hazel" ])
               ::human (rand-nth ["amber" "blue" "pale blue" "brown" "gray" "green" "hazel" "red" "violet"])
               ::dragonborn (rand-nth ["gold" "black" "red"])
               ::gnome "bright"
               ::goliath (rand-nth ["gold" "white" "black"])
               ::halfelf (rand-nth [(eyecolor ::human) (eyecolor ::elf)])
               ::halforc (rand-nth ["black" (eyecolor ::human)])
               :tiefling (rand-nth ["black" "red" "white" "silver" "gold"])
             ))
 )
 