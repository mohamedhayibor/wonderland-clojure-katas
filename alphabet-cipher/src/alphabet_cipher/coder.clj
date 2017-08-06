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


(defn seed-msg-seq
  "Takes a seed (keyword) and a msg, sequential inputs for
  encryption and decryption.
  Note: the letter seed is always first."
  [seed msg]
  ;; Todo: will be more specific about the string length later,
  ;; works for now
  (if (> (count seed) (count msg))
    (map str seed (apply str (repeat (count seed) msg)))
    (map str (apply str (repeat (count msg) seed)) msg)))


(defn char-encoding
  "Takes a letter seed and message and returns char encoding"
  [char-s char-m]

  (get-in (alphabet-cipher alphabet) [(- (int char-m) 97)  (- (int char-s) 97)]) )


(defn encode [keyword message]
  "encodeme")

(defn decode [keyword message]
  "decodeme")

(defn decipher [cipher message]
  "decypherme")

; letter debugg
(def letter-debugging
  (map (juxt char identity #(- % 97)) (range 97 (+ 97 26))))
