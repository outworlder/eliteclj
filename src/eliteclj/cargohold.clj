(ns eliteclj.cargohold)

(defn create-cargohold [capacity]
  { :items []
    :capacity capacity})

(defn add-item [cargohold item]
  (merge cargohold { :items (cons (:items cargohold) item)}))
