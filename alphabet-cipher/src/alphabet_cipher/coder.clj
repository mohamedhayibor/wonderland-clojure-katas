(ns alphabet-cipher.coder)

(def alphabet-chars
  (mapv char (range 97 (+ 97 26))))

(def alphabet
  (mapv str alphabet-chars))

(defn shift-by-one
  "takes the last and insert at the begginning shifting everything."
  [coll]
  (vec (concat (vector (peek coll)) (butlast coll))))


(defn alphabet-cipher
  "Takes alphabet then returns the 2d array representation of cipher"
  [coll]
  (take 28 (iterate shift-by-one coll)))

(defn seed-and-msg
  "Takes a seed (keyword) and a msg, then returns the inputs for
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
  (let [x (int char-s)
        y (int char-m)
        cipher (alphabet-cipher alphabet)]
   (get-in cipher [(- y 97)  (- x 97)])))


(defn encode [keyword message]
  "encodeme")

(defn decode [keyword message]
  "decodeme")

(defn decipher [cipher message]
  "decypherme")
