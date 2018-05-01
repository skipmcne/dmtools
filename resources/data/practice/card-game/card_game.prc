process.greet.X(agent).Y(agent)
    action "Greet"
    preconditions
    // They must be co-located
    	X.in!L and Y.in!L
    postconditions
   		text "[X] says ’Hi’ to [Y obj]"
end
