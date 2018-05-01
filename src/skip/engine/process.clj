(defmacro process [agents action preconditions postconditions]
	(if ~preconditions
		(do ~action
			~postconditions)))