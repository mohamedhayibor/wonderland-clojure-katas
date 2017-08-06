(ns alphabet-cipher.coder)

(def alphabet-chars
  (mapv char (range 97 (+ 97 26))))

(def alphabet
  (mapv str alphabet-chars))

(defn shift-by-one
  "takes the last and insert at the begginning shifting everything."
  [coll]
  (vec (concat (rest coll) (vector (first coll)))))

(defn alphabet-cipher
  "Takes alphabet then returns the 2d array representation of cipher"
  [coll]
  (vec (take 26 (iterate shift-by-one coll))))

(defn seed-msg-letter-set
  "Takes a seed (keyword) and a msg, sequential inputs for
  encryption and decryption.
  Note: the letter seed is always first."
  [seed msg]
  ;; Todo: will be more specific about the string length later,
  ;; works for now
  (if (> (count seed) (count msg))
    (mapv str seed (apply str (repeat (count seed) msg)))
    (mapv str (apply str (repeat (count msg) seed)) msg)))

(defn char-encoding
  "Takes a letter seed and message and returns char encoding"
  [char-s char-m]
  (get-in (alphabet-cipher alphabet) [(- (int char-m) 97)  (- (int char-s) 97)]))

(defn encode
  "encrypts letter by letter using keyword and message"
  [seed msg]
  ;; todo: refactor for threading
  (apply str (map #(char-encoding (first %) (second %)) (map seq (seed-msg-letter-set seed msg)))))

(defn char-decoding
  "Takes a seed and cypher char and returns char message"
  [char-s char-c]
  ;; todo: make more readable with ->>
  (if (pos? (- (int char-c) (int char-s)))
    (char (+ (- (int char-c) (int char-s)) 97))
    (char (+ 97 (- (+ (int char-c) 26) (int char-s))))))

(defn decipher
  "Takes a cipher and seed then returns original"
  [cipher msg]
  ;; todo: make more readable with ->>
  (apply str (map #(char-decoding (first %) (second %)) (map seq (seed-msg-letter-set cipher msg)))))

(def letter-debugging
  (map (juxt char identity #(- % 97)) (range 97 (+ 97 26))))
