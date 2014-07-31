(ns eliteclj.commander)

(def galcop-elite-ratings
  [[0 "Harmless"],
   [8 "Mostly Harmless"],
   [16 "Poor"],
   [32 "Average"],
   [64 "Above Average"],
   [128 "Competent"],
   [512 "Dangerous"],
   [2560 "Deadly"],
   [6400 "Elite"]])

(defn create-commander
  [name]
  {:name name
   :credits 100
   :legal-status :clean
   :kills 0
   :current-system "Lave"
   :elite-rating "Harmless"
   :right-on-commander (fn [] "Right on, Commander!")})

(defn evaluate-ranking [commander]
  (let [kills (:kills commander)
        rating (last (filter #(>= kills (first %)) galcop-elite-ratings))]
    (if (and (= (mod kills 256) 0) (> kills 0))
      ((:right-on-commander commander)))
    (merge commander { :elite-rating (second rating)})))

(defn confirmed-kill [commander]
  (evaluate-ranking (merge commander {:kills (inc (:kills commander))})))
