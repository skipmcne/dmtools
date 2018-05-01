

(defn parse-prc-file
  "Translates  .prc file to a practice object "
  [file]
	(with-open [input (reader file)]
	     (let [lines (line-seq input)
	           cfg   (process-cfg-lines lines)]
	      (trampoline next-line lines {:cfg cfg :depth 0})))
	  (.toString output))

;
;The first argument to trampoline is a function which trampoline invokes on the rest of the argument list.
; If that function returns another function, trampoline calls that in turn, repeating until a function returns something aside from a function.
;
(defn next-line
  "Handles a line in an prc file, dispatches if it is a header, list, other specially case."
  [[line & rest] {:keys [properties? depth] :as state}]
  (handle-end-lines [line state]
    (condp re-seq line 
      #":PROPERTIES:" (partial properties-line rest state)
      #"^\*+ " (partial parse-header (cons line rest) state)
      #"^(\s+[+*-] |[+-] )" (partial ulist-line (cons line rest) state)
      (do
        (println (html [:p (create-links line)]))
        (partial next-line rest state)))))


(defn header-string
"Format a header line into an HTML string"
[stars line id cfg]
  (html [(keyword (str "h" stars))
         {:id (str "head-" id) :class "folder"}
         (markup-todos (.substring line stars) cfg)]))

(defn parse-header
  "Handles a header line in an org file."
  [[line & rest] {:keys [depth ids cfg] :as state}]
  (let [stars (count-stars line)
        id (gensym)
        closings (inc (- depth stars))]
    (doseq [index (range closings)]
      (println "</div></div>"))
    (println "<div>")
    (println (header-string stars line id cfg))
    (println (str "<div class=\"foldable\" id=\"body-" id "\">"))
    (partial next-line rest
             (assoc state
               :depth stars
               :ids (cons id (drop closings ids))))))