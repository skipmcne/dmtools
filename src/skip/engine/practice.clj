;social practice
; To help the player understand the state of the various social practices
; that are currently in play, we
; organised the affordances around the practices which initiated them. For example,
; if the player’s character is in the middle of a dinner-party, and Brown has
; just made a rude remark, there will be two social practices running concurrently:
; the dinner party (providing affordances to Eat, Drink, etc) and the current conversation
; (providing affordances to Disapprove of Brown, Forgive him, etc). The
; affordances are arranged in categories, grouped by the social practice that instantiated
; them, so that the player begins to understand the underlying simulation
; state. The text for each social practice is carefully worded to display its current
; state.
; A social practice describes a type of recurring social situation. Some social
; practices (e.g. a conversation, a meal, a game) only exist for a short time, while
; others (e.g. a family, the moral community) can last much longer.
; A practice coordinates agents via the roles they are playing. For example, a
; greeting practice sees the two participants under the descriptions of greeter and
; recipient.
; The main function of the social practice is to describe the actions the agents
; can do in that situation. A greeting practice, for example, tells the greeter how
; he can greet the recipient. It also tells the recipient the various ways she can
; respond.
; The practice provides the agent with a set of suggested actions, but it is up to
; the agent himself to decide which action to perform, using utility-based reactive
; action selection. This is described in Section IX below.
; In Versu, we allow multiple practices to exist concurrently. During a dinner
; party, for example, there will be multiple practices operating at once:
; – eating and drinking (and commenting on the meal)
; – the conversation about politics
; – the rising flirtation between Frank and Lucy
; – responding to the fact that Mr Quinn has spilled the soup
; Each of these practices provides multiple affordances. The agent’s set of options
; is the union of the affordances from each of the practices he is participating in.
; Some practices are organised into states, so that they can provide different
; affordances in different situations. But a social practice is significantly more
; powerful than a Finite-State Machine, in two main ways. First, each practice
; can store arbitrary persistent data2
; , while the only memory a state machine has
; is the state it is in3
;. Second, the only possible effect of a Finite-State Machine’s
; action is transitioning from one state to another. A Versu action can do much
; more than change the state of the practice: performing an action can result
; in any sentence being added to the world database. The results of adding new
; sentences can be that relationships are updated, new beliefs or desires are formed,
; old practices are deleted or new practices are spawned.

(ns convo.engine.practice
 "practice"
 (:require [clojure.spec.alpha :as spec])
 (:require [convo.engine.agent :as agent])
 )
;;;;;;;
; given a practice and a world, determine return valid actions
(defn getActions [practice fact])


(spec/def ::agents (spec/keys :req [::agent/agent])
(spec/def ::action)
(spec/def ::preonditiions)


(spec/def ::process (spec/keys :req [::agents ::last-name ::email]
                         :opt [::phone]))
(defrecord process )