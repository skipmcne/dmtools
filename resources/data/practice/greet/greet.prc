(:process greet
:agents (X Y)
:action ("Greet")
:preconditions  [(X.in!L and Y.in!L)]     // They must be co-located
:postconditions [(text "[X] says ’Hi’ to [Y obj]")
    	(update_conversation.L.Actor.Y.respond_to_greet)
    	(insert process.respond_to_greet.Y.X)
    	(delete self)]
)
