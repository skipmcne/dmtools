; social practice comes with a set of norms: things the participants are expected to do.


process.interrogate.X(agent).Y(agent)
    action "Interrogate"
    preconditions
    // They must be co-located
    	X.in!L and Y.in!L
    postconditions
    	text "[X] says ’Hi’ to [Y obj]"
end
