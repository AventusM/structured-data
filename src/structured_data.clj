 (ns structured-data)

;Testi

(let [y (* 5 2)]
  (+ y y)
  )

(defn
  do-a-thing
  [x]
  (let [sum (+ x x)]
    (Math/pow sum sum)); summa potenssiin summa
  )

(do-a-thing 1)
(do-a-thing 2)
(do-a-thing 3)

(defn
  spiff
  [v]
  (let [a (get v 0) ;ol. v on vektori, parametrejä ei tarvita
        b (get v 2)]
    (+ a b)
    )
  )

(spiff [1 2 3])
(spiff [1 2 3 4 5 6])

;Allaolevat NullPointerException
;(spiff [1 2])
;(spiff [])

(defn
  cutify
  [v]
  (conj v "<3")
  )

(cutify [])
(cutify [1 20 93])
(cutify ["a" "b" "c"])

(defn
  spiff-destructuring
  [v]
  (let [[eka _ kolmas] v]; indeksit 0 ja 2. Vektorin toinen jäsen on varsinaisesti käyttämätön
    (+ eka kolmas)
  ))

(spiff-destructuring [1 2 3])

(defn rectangle [bottom-left top-right]
  [bottom-left top-right])

(defn
  width
  [rectangle]
  (let [[[x1 y1] [x2 y2]] rectangle]
    (- x2 x1))
  )

(width (rectangle [1 1] [5 1]))
(width (rectangle [1 1] [1 1]))
(width (rectangle [3 1] [10 4]))


(defn
  height
  [rectangle]
  (let [[[x1 y1] [x2 y2]] rectangle]
    (- y2 y1))
  )

(height (rectangle [1 1] [5 1]))
(height (rectangle [1 1] [5 5]))
(height (rectangle [0 0] [2 3]))

(defn
  square?
  [rectangle]
  (let  [[[x1 y1] [x2 y2] ] rectangle]
    (if (= (- x2 x1) (- y2 y1))
      true
      false))
  )

(square? (rectangle [1 1] [2 2]))
(square? (rectangle [1 1] [2 3]))

(defn
  area
  [rectangle]
  (let [[[x1 y1] [x2 y2]] rectangle]
    (* (- x2 x1) (- y2 y1)))
  )

(area (rectangle [1 1] [5 1]))
(area (rectangle [0 0] [1 1]))
(area (rectangle [0 0] [4 3]))
(area (rectangle [3 1] [10 4]))

(defn point [x y]
  [x y])

(defn
  contains-point?
  [rectangle point]
  (let [[[x1 y1] [x2 y2]] rectangle]
       (let [[x3 y3] point] ;Nested let
         (and (<= x1 x3 x2) (<= y1 y3 y2)
              true) ;Kokeilu olla käyttämättä falsea toikin toivotun tuloksen
          ))
  )

(contains-point? (rectangle [0 0] [2 2])
                 (point 1 1))
(contains-point? (rectangle [0 0] [2 2])
                 (point 2 1))
(contains-point? (rectangle [0 0] [2 2])
                 (point -3 1))
(contains-point? (rectangle [1 1] [2 2])
                 (point 1 1))
(contains-point? (rectangle [1 1] [1 1])
                 (point 1 1))
(contains-point? (rectangle [0 0] [2 2])
                 (point -2 1))
(contains-point? (rectangle [0 0] [2 2])
                 (point 5 5))

(defn
  contains-rectangle?
  [outer inner]
  (let [[[x1 y1] [x2 y2]] outer
        [[u1 v1] [u2 v2]] inner]
    (and (<= x1 u1 u2 x2) (<= y1 v1 v2 y2)))
  )

(contains-rectangle? (rectangle [0 0] [3 3])
                     (rectangle [1 1] [2 2]))


;Oma tarkistusmetodi
(defn
  books-authors
  [book]
  (:authors book)
  )
;Oma tarkistusmetodi

;(books-authors cities)

(defn
  title-length
  [book]
  (count (:title book))
  )

;(title-length cities)
;(title-length wild-seed)
;(title-length little-schemer)



(defn
  author-count
  [book]
  (count (:authors book))
  )

;(author-count cities)
;(author-count wild-seed)
;(author-count little-schemer)

(defn
  multiple-authors?
  [book]
  (if (< 1 (count (:authors book)))
    true
    false)
  )

;(multiple-authors? cities)
;(multiple-authors? wild-seed)
;(multiple-authors? little-schemer)

;ALL OF THE ABOVE PASS
;ALL OF THE ABOVE PASS

;(let [original [1 2 3 4]
;      new      (assoc original 2 "foo")]
;  new) => [1 2 "foo" 4]


;Eka tehtävä, missä piti käyttää enemmän ajattelua.
(defn
  add-author
  [book new-author]
  ;1. Extract authors from the book
  ;2. conj to the extracted list
  ;3. assoc to original book-keyword :authors
  (let [old-authors (:authors book) ;Kaarisulkeet, muut eivät toteuta haluttua lopputulosta
        new-authors (conj old-authors new-author)
        replaced-authors (assoc book :authors new-authors)]
    replaced-authors
    )
  )

;(add-author little-schemer {:name "Gerald J. Sussman"}) TOIMII
(add-author {:authors [{:name "Juhana"}]} {:name "Jani"})

(defn
  alive?
  [author]
  (not (contains? author :death-year))
  )

;(alive? china)
;(alive? octavia)

;element-lengths apufunktio
(defn
  element-length
  [item]
  (count item))

(defn
  element-lengths
  [collection]
  (map element-length collection)
  )

;(element-lengths ["foo" "bar" "" "quux"])
;(element-lengths ["x" [:a :b :c] {:y 42}])

(defn
  second-elements
  [collection]
  (let [second-element (fn [element] (get element 1))]
    (map second-element collection))
  )

;Harvinaista herkkua... eka yritys toimii
(second-elements [[1 2] [2 3] [3 4]])
(second-elements [[1 2 3 4] [1] ["a" "s" "d" "f"]])


;KIRJAT
;KIRJAT










(def china {:name "China Miéville", :birth-year 1972})
(def octavia {:name "Octavia E. Butler"
              :birth-year 1947
              :death-year 2006})
(def friedman {:name "Daniel Friedman" :birth-year 1944})
(def felleisen {:name "Matthias Felleisen"})

(def cities {:title "The City and the City" :authors #{china}})
(def wild-seed {:title "Wild Seed", :authors #{octavia}})
(def embassytown {:title "Embassytown", :authors #{china}})
(def little-schemer {:title "The Little Schemer"
                     :authors #{friedman, felleisen}})

(def books [cities, wild-seed, embassytown, little-schemer])








;KIRJAT
;KIRJAT


(defn
  titles
  [books]
  (map :title books)
  )

;(titles [cities])
;(titles books)

(defn stars [n] (apply str (repeat n "*")))

;(stars 2)

(defn
  monotonic?
  [a-seq]
  (or (apply <= a-seq) (apply >= a-seq))
  )

(monotonic? [1 2 3])
(monotonic? [0 1 10 11])
(monotonic? [3 2 0 -3])
(monotonic? [3 2 2])
(monotonic? [1 2 1 0])

(defn
  toggle
  [a-set elem]
  (if (contains? a-set elem)
    ;LUE TEHTÄVÄNANTO DISJ EKAKSI!!!
    (disj a-set elem)
    (conj a-set elem)
    )
  )

(toggle #{:a :b :c} :d)
(toggle #{:a :b :c} :a)

(defn
  contains-duplicates?
  [a-seq]
  (if (> (count a-seq) (count (set a-seq)))
    true
    false)
  )

(contains-duplicates? [1 1 2 3 -40])
(contains-duplicates? [1 2 3 -40])
(contains-duplicates? [1 2 3 "a" "a"])

;Tulostuksen ulkonäkö on oikein vaikka tehtävänannossa tunnutaan oikaisevan
(defn
  old-book->new-book
  [book]
  ;1. Turn vector into a set
  ;2. assoc aforementioned set into the original book
  (let [in-set-authors (set (book :authors))]
    (assoc book :authors in-set-authors))
  )

;(old-book->new-book {:title "The Little Schemer"
;                     :authors [friedman, felleisen]})
;(old-book->new-book {:title "Wild Seed", :authors [octavia]})


(defn
  has-author?
  [book author]
  ;Trial & error
  (contains? (get book :authors) author)
  )

(has-author? cities china)
(has-author? cities felleisen)
(has-author? little-schemer felleisen)

(defn
  authors
  [books]
  (apply clojure.set/union (map :authors books))
  )

;(authors books)

(defn
  all-author-names
  [books]
  (set (map :name (authors books)))
  )

;(all-author-names books)
;(all-author-names [embassytown])
;(all-author-names {})
;(all-author-names [])
;(all-author-names ())

;APUFUKTIO - muuta anonyymiksi???
;(defn
;  birth-year-known?
;  [author]
;  (contains? author :birth-year)
;  )
;APUFUKTIO - muuta anomyymiksi???

;KAVERI AUTTOI
;(defn
;  author->string
;  [author]
;  (let [name (get author :name)
;        birth (get author :birth-year)
;        death (get author :death-year)]
;    (str name
;      (if (not birth)
;        ""
;        (str " (" birth " - " death ")")))))

;LOPPUTULOS
(defn
  author->string
  [author]
  (let [name (:name author)
        birth (:birth-year author)
        death (:death-year author)]
    (str name
         (if (not birth)
           ""
           (str " (" birth " - " death ")")))))

;ALKUPERÄINEN - TOIMII OK
;(defn
;  author->string
;  [author]
;  (let [authors-name (:name author)
;        authors-birth-date (:birth-year author)
;        authors-death-date (:death-year author)]
;    (if (birth-year-known? author)
;    (str authors-name " (" authors-birth-date " - " authors-death-date ")")
;    (str authors-name)))
;  )


;(author->string felleisen)
;(author->string china)
;(author->string octavia)

(defn
  authors->string
  [authors]
  (apply str (interpose ", " (map author->string authors)))
  )

;(map author->string (:authors little-schemer))

;(authors->string (:authors little-schemer))
;(authors->string #{octavia})
;(authors->string #{})
;(authors->string #{octavia, friedman})


(defn
  book->string
  [book]
  (let [book-name (get book :title)                          ;(:title book)
        books-authors (authors->string (get book :authors))] ;(:authors book)
    (str book-name ", written by " books-authors ".")
    )
  )

;Broken test -> ilman pistettä yksi onnistuu / yksi failaa. Pisteellä toisinpäin-.-

;(book->string little-schemer)


(defn
  books->string
  [books]
  (cond
    (= 0 (count books)) "No books."
    (= 1 (count books)) (apply str "1 book. " (interpose " " (map book->string books)))
    :else (apply str (count books) " books. " (interpose " " (map book->string books)))
    ))

(books->string [])
(books->string [cities])
(books->string [little-schemer, cities, wild-seed])

;ALL OF THE ABOVE PASS
;ALL OF THE ABOVE PASS


;(filter pos? [-4 5 -2 1])

(defn
  books-by-author
  [author books]
  )

(books-by-author china books)



(defn author-by-name [name authors]
  :-)

(defn living-authors [authors]
  :-)

(defn has-a-living-author? [book]
  :-)

(defn books-by-living-authors [books]
  :-)

; %________%
