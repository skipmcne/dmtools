
(ns clj-spec-playground
  (:require [clojure.string :as str]
            [clojure.spec :as s]
            [clojure.test.check.generators :as gen]))


(s/def ::id keyword?)
(s/def ::desc string?)
(s/def :action/type #{:chase :escort :rescue :steal})
(s/def :story/action(s/keys :req [::id :action/type] 
                            :opt [::desc]))

(s/valid? :story/action     
  {::id :chase_1
   :action/type :chase
   ::desc "hero pursues villian"})

(defrecord Action [id action-type desc])

(s/def :scene/type #{:introduction :dialog :hook})
(s/def :story/scene(s/keys :req [::id :scene/type] 
                            :opt [::desc]))
                            
(s/valid? :story/scene
  {::id :introduction_1
   :scene/type :introduction
   ::desc "introduction of character"})
(defrecord Scene [id scene-type desc])

(s/def :story/event/type #{:event/action :event/scene})

(defmulti story-event-type  :story/event/type)
(defmethod story-event-type :event/action [_]
  (s/keys :req [:event/type :event/timestamp :story/action]))
(defmethod story-event-type :event/scene [_]
  (s/keys :req [:event/type :event/timestamp :story/scene]))

(s/def :story/event (s/multi-spec story-event-type :story/event/type))

(s/valid? :story/event 
 {:story/event/type :event/action
   :event/timestamp 1463970123000
   :story/action {::id :chase_1
                   :action/type :chase
                  ::desc "hero pursues villian"}})


(s/def :story/event (s/keys :req 
                            :opt ))
(s/def :story/plot string?)
(s/def :story/villain string?)
(s/def :story/setting string?)
(s/def :story/theme? boolean?)


;(00 :Sequence nil 01 nil "Initial" "The setup.")
                            